package io.github.avijitmondal.together.controller;

import io.github.avijitmondal.together.data.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by avijit
 */
@Component
public class LoginController implements IRootController, Initializable {

    private final Logger logger = LogManager.getLogger(LoginController.class);

    /**
     *
     */
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private BorderPane loginPane;

    private double xOffset;
    private double yOffset;

    /**
     *
     * @throws IOException
     */
    public void loginButtonAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        logger.debug("Login button clicked with username [" + username + "]");
        if (null == username || "".equals(username.trim())) {
            logger.error("Username is empty");
        } else if (null == password || "".equals(password.trim())) {
            logger.error("Password is empty");
        } else {
            try {
                logger.debug("Authenticating user");
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    public void registerButtonAction() throws IOException {
        logger.debug("Loading registration window");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.debug("initialize");
        /* Drag and Drop */
        loginPane.setOnMousePressed(event -> {
            xOffset = ((Stage) usernameField.getScene().getWindow()).getX() - event.getScreenX();
            yOffset = ((Stage) usernameField.getScene().getWindow()).getY() - event.getScreenY();
            loginPane.setCursor(Cursor.CLOSED_HAND);
        });
        loginPane.setOnMouseDragged(event -> {
            ((Stage) usernameField.getScene().getWindow()).setX(event.getScreenX() + xOffset);
            ((Stage) usernameField.getScene().getWindow()).setY(event.getScreenY() + yOffset);
        });
        loginPane.setOnMouseReleased(event -> {
            loginPane.setCursor(Cursor.DEFAULT);
        });

        int numberOfSquares = 30;
        while (numberOfSquares > 0) {
            generateAnimation();
            numberOfSquares--;
        }
    }

    /*
	 * This method is used to generate the animation on the login window, It
	 * will generate random ints to determine the size, speed, starting points
	 * and direction of each square.
     */
    /**
     *
     */
    public void generateAnimation() {
        Random random = new Random();
        int sizeOfSqaure = random.nextInt(50) + 1;
        int speedOfSqaure = random.nextInt(10) + 5;
        int startXPoint = random.nextInt(Constants.LOGIN_WINDOW_HEIGHT);
        int startYPoint = random.nextInt(Constants.LOGIN_WINDOW_WIDTH);
        int direction = random.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle rectangle = new Rectangle();

        switch (direction) {
            case 1:
                // MOVE LEFT TO RIGHT
                rectangle = new Rectangle(0, startYPoint, sizeOfSqaure, sizeOfSqaure);
                moveXAxis = new KeyValue(rectangle.xProperty(), Constants.LOGIN_WINDOW_WIDTH - sizeOfSqaure);
                break;
            case 2:
                // MOVE TOP TO BOTTOM
                rectangle = new Rectangle(startXPoint, 0, sizeOfSqaure, sizeOfSqaure);
                moveYAxis = new KeyValue(rectangle.yProperty(), Constants.LOGIN_WINDOW_HEIGHT - sizeOfSqaure);
                break;
            case 3:
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                rectangle = new Rectangle(startXPoint, 0, sizeOfSqaure, sizeOfSqaure);
                moveXAxis = new KeyValue(rectangle.xProperty(), Constants.LOGIN_WINDOW_WIDTH - sizeOfSqaure);
                moveYAxis = new KeyValue(rectangle.yProperty(), Constants.LOGIN_WINDOW_HEIGHT - sizeOfSqaure);
                break;
            case 4:
                // MOVE BOTTOM TO TOP
                rectangle = new Rectangle(startXPoint, Constants.LOGIN_WINDOW_HEIGHT - sizeOfSqaure, sizeOfSqaure, sizeOfSqaure);
                moveYAxis = new KeyValue(rectangle.xProperty(), 0);
                break;
            case 5:
                // MOVE RIGHT TO LEFT
                rectangle = new Rectangle(Constants.LOGIN_WINDOW_HEIGHT - sizeOfSqaure, startYPoint, sizeOfSqaure, sizeOfSqaure);
                moveXAxis = new KeyValue(rectangle.xProperty(), 0);
                break;
            case 6:
                // MOVE RIGHT TO LEFT, BOTTOM TO TOP
                rectangle = new Rectangle(startXPoint, 0, sizeOfSqaure, sizeOfSqaure);
                moveXAxis = new KeyValue(rectangle.xProperty(), Constants.LOGIN_WINDOW_WIDTH - sizeOfSqaure);
                moveYAxis = new KeyValue(rectangle.yProperty(), Constants.LOGIN_WINDOW_HEIGHT - sizeOfSqaure);
                break;

            default:
                logger.debug("default");
        }

        rectangle.setFill(Constants.BASE_COLOR);
        rectangle.setOpacity(0.1);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSqaure * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        loginPane.getChildren().add(loginPane.getChildren().size() - 1, rectangle);
    }

    /**
     * Terminates Application
     */
    public void closeSystem() {
        logger.debug("closeSystem");
        Platform.exit();
        System.exit(0);
    }

    /**
     * Minimize Application
     */
    public void minimizeWindow() {
        logger.debug("minimizeWindow");
        ((Stage) usernameField.getScene().getWindow()).setIconified(true);
    }

    @Override
    public void onVisible() {
        //TODO : implement me
    }
}
