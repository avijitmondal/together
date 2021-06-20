package io.github.avijitmondal.together;

import io.github.avijitmondal.together.data.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TogetherApplication extends Application {

	private final static Logger logger = LogManager.getLogger(TogetherApplication.class);
	private ConfigurableApplicationContext context;
	private Parent rootNode;

	public static void main(String[] args) {
		SpringApplication.run(TogetherApplication.class, args);
		logger.info("Executing main...");
        Application.launch(TogetherApplication.class, args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		logger.debug("executing init");
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TogetherApplication.class);

		context = builder.run(getParameters().getRaw().toArray(new String[0]));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));

		loader.setControllerFactory(context::getBean);

		rootNode = loader.load();
	}

	@Override
	public void start(Stage stage) throws Exception {
		logger.info("Starting application...");
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle(Constants.APPLICATION_TITLE);
		stage.getIcons().add(new Image("/images/logo/android-icon-144x144.png"));
		stage.setResizable(false);
		stage.setOnCloseRequest(event -> Platform.exit());

		stage.setScene(new Scene(rootNode, Constants.LOGIN_WINDOW_WIDTH, Constants.LOGIN_WINDOW_HEIGHT));

		stage.centerOnScreen();

		stage.show();
	}

	@Override
	public void stop() throws Exception {
		logger.debug("Stopping application...");
		context.close();
	}
}
