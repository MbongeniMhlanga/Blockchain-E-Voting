import acsse.csc03a3.Blockchain;
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
	@SuppressWarnings("rawtypes")
	Ballot ballot;

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		ballot = new Ballot();

		// Create the login scene
		Login loginPane = new Login(stage, votePageScene, officerScene);
		logScene = new Scene(loginPane);

		// Create the registration scene
		Register registerPane = new Register(stage, logScene);
		regScene = new Scene(registerPane);

		// Create the voting page scene
		VotingPage votingPage = new VotingPage(stage, votePageScene);
		votePageScene = new Scene(votingPage);

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

		// Event handling for navigation
		registerPane.btnLogin.setOnAction(e -> {
			stage.setScene(logScene);
			stage.setTitle("Login");
		});

		loginPane.btnLogin.setOnAction(e -> {
			// Open the voting page in a new stage/window
			Stage voteStage = new Stage();
			voteStage.setScene(votePageScene);
			voteStage.setTitle("Voter Page");
			voteStage.show();
		});

		votingPage.btnSubmit.setOnAction(e -> {
			// Open the vote status page in a new stage/window
			Stage statusStage = new Stage();
			statusStage.setScene(voteStatusScene);
			statusStage.setTitle("Vote Status");
			statusStage.show();
		});
	}
}
