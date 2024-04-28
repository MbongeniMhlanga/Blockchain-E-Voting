import acsse.csc03a3.Blockchain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Mbongeni Mhlanga
 * @version Mini Project
 *
 */
public class Main<T> extends Application {
    //Attributes
    Stage stage;
    Scene logScene;
    Scene regScene;
    Scene homeScene;
    Scene scene;
    Scene votePageScene;
    Scene voteStatusScene;
    Scene officerScene;
    Blockchain<T> blockchain = new Blockchain<>();
    Ballot ballot = new Ballot();
    
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage voteStage) throws Exception {
        stage = voteStage;
        
        // Create the voting page scene
        @SuppressWarnings("rawtypes")
		VotingPage votingPage = new VotingPage(stage, votePageScene);
        votePageScene = new Scene(votingPage);
        VoteOfficer officepane = new VoteOfficer(stage,voteStatusScene,ballot);
        officerScene =  new Scene(officepane);


        // Create the login scene
        Login loginPane = new Login(stage, votePageScene, officerScene);
        logScene = new Scene(loginPane);

        // Create the registration scene
        Register registerPane = new Register(stage, logScene);
        regScene = new Scene(registerPane);

      
     // Create the vote status scene
        VoteStatus voteStatusPane = new VoteStatus(stage, regScene);
        voteStatusScene = new Scene(voteStatusPane);


        // Set background color to light blue
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

        // Event handling for navigation
        registerPane.btnLogin.setOnAction(e -> {
            stage.setScene(logScene);
            stage.setTitle("Login");
        });

        loginPane.btnLogin.setOnAction(e -> {
            stage.setScene(votePageScene);
            stage.setTitle("Voter Page");
        });
    }
}
