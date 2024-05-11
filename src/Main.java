import java.util.List;
import java.util.UUID;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main<T> extends Application {
    // Attributes
    Stage stage;
    Scene logScene;
    Scene regScene;
    Scene homeScene;
    Scene scene;
    Scene votePageScene;
    Scene officerScene;
    Blockchain<T> blockchain = new Blockchain<>();
    Transaction<T> transaction;
    @SuppressWarnings("rawtypes")
    Ballot ballot;

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        ballot = new Ballot();

        // Create the login scene
        Login loginPane = new Login(stage, votePageScene, officerScene);
        logScene = new Scene(loginPane , 400, 400);

        // Create the registration scene
        Register registerPane = new Register(stage, logScene);
        regScene = new Scene(registerPane);

        // Create the voting page scene
        VotingPage votingPage = new VotingPage(stage, votePageScene, ballot);
        votePageScene = new Scene(votingPage, 400, 400);

        // Create the officer scene
        VoteOfficer officePane = new VoteOfficer(stage, votePageScene, ballot);
        officerScene = new Scene(officePane);

        // Create the vote status scene
        VoteStatus voteStatusPane = new VoteStatus(stage, regScene);
        Scene voteStatusScene = new Scene(voteStatusPane);

        // Set background colour to light blue
        regScene.getRoot().setStyle("-fx-background-color: lavender;");
        logScene.getRoot().setStyle("-fx-background-color: lavender;");
        votePageScene.getRoot().setStyle("-fx-background-color: lavender;");
        voteStatusScene.getRoot().setStyle("-fx-background-color: lavender;");

        // Set initial scene to registration
        stage.setScene(regScene);
        stage.setWidth(600); // Set the width to 800 pixels
        stage.setHeight(400); // Set the height to 600 pixels
        stage.setTitle("Registration");
        stage.show();

      

    }
}
