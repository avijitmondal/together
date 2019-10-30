node {
   def mvnHome
   def scannerHome
   stage('Preparation') {
      git url: 'https://github.com/avijitmondal/together-server.git', branch: 'monolithic_to_microservice'
      // Maven tool.
      // ** NOTE: 'MVN-3' Maven tool must be configured in the global configuration.
      mvnHome = tool 'MVN-3'
      scannerHome = tool 'SonarQubeScanner';

   }
   stage('Build') {
      // Compile the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" clean compile -f together-modules.xml'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" clean compile/)
         }
      }
   }
   stage('Analysis') {
      // Analyse the source code
      withEnv(["SONAR_HOME=$scannerHome"]) {
         withSonarQubeEnv(installationName: 'SonarQubeServer', credentialsId: 'sonarqube') {
            sh "${SONAR_HOME}/bin/sonar-scanner"
         }
      }
   }
   stage('Package') {
      // Package the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" clean package -f together-modules.xml'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" clean package -f together-modules.xml/)
         }
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archiveArtifacts '**/target/*.jar'
   }
}
