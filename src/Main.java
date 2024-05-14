import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */
public class Main<T> extends Application {
    // Attributes
    Stage stage;
    Scene logScene;
    Scene regScene;
    Scene officerScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        //ballot = new Ballot();
        User user = new User(null, null, null, null, null);
        // Create the login scene
        Login loginPane = new Login();
        logScene = new Scene(loginPane , 400, 400);

        // Create the registration scene
        Register registerPane = new Register(user);
        regScene = new Scene(registerPane);

        // Set initial scene to registration
        stage.setScene(regScene);
        stage.setWidth(600); // Set the width to 800 pixels
        stage.setHeight(600); // Set the height to 600 pixels
        stage.setTitle("Registration");

        // Set background colour to light blue
        regScene.getRoot().setStyle("-fx-background-color: lavender;");
        logScene.getRoot().setStyle("-fx-background-color: lavender;");
        stage.show();

      

    }
}
