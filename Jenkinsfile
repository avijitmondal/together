pipeline {
  agent any
  stages {
    stage('Build modules') {
      parallel {
        stage('alive') {
          steps {
            echo 'Building together-alive'
          }
        }

        stage('auth-center') {
          steps {
            echo 'Building together-auth-center'
          }
        }

        stage('config') {
          steps {
            echo 'Building together-config'
          }
        }

        stage('conversation') {
          steps {
            echo 'Building together-conversation'
          }
        }

        stage('core') {
          steps {
            echo 'Building together-core'
          }
        }

        stage('desktop') {
          steps {
            echo 'Building together-desktop'
          }
        }

        stage('discovery') {
          steps {
            echo 'Building together-discovery'
          }
        }

        stage('ftp') {
          steps {
            echo 'Building together-ftp'
          }
        }

        stage('gateway') {
          steps {
            echo 'Building together-gateway'
          }
        }

        stage('mobile') {
          steps {
            echo 'Building together-mobile'
          }
        }

        stage('status') {
          steps {
            echo 'Building together-status'
          }
        }

        stage('sync') {
          steps {
            echo 'Building together-sync'
          }
        }

        stage('user') {
          steps {
            echo 'Building together-user'
          }
        }

        stage('web') {
          steps {
            echo 'Building together-web'
          }
        }

      }
    }

    stage('Build together') {
      steps {
        echo 'Building together'
      }
    }

  }
}