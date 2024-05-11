import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VoteOfficer extends StackPane {
     Stage stage;
     Scene loginScene;
     Ballot<?> ballot;

    public VoteOfficer(Stage stage, Scene loginScene, Ballot<?> ballot) {
        this.stage = stage;
        this.loginScene = loginScene;
       this.ballot = ballot;

       
       officerGUI();
       
    }
    
    public void officerGUI() {
    	
    	 // Create the label to display the number of votes
        Label votesLabel = new Label("Number of Votes: " + ballot.getNumberOfVotes());
        votesLabel.setFont(new Font("Arial", 24));
        votesLabel.setTextFill(Color.BLACK);

        // Create a circle to add a decorative element
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setFill(Color.ALICEBLUE); // Set the circle color
        circle.setStroke(Color.BLACK); // Set the circle border color
        circle.setStrokeWidth(2); // Set the circle border width

        // Stack the label on top of the circle
        StackPane.setAlignment(votesLabel, Pos.CENTER);
        StackPane.setAlignment(circle, Pos.CENTER);
        getChildren().addAll(circle, votesLabel);

        // Create a button to allow the vote officer to logout
        Button btnButton = new Button("Logout");
        btnButton.setOnAction(e -> Platform.exit()); // Close the program
        // Add the logout button to the bottom center of the StackPane
        StackPane.setAlignment(btnButton, Pos.BOTTOM_CENTER);
        getChildren().add(btnButton);

        // Apply CSS to set the background color
        setStyle("-fx-background-color: lightgray;");

        // Set the size of the StackPane
        setPrefSize(300, 200);
        
       // List numberOfVotes = ballot.getVotes();
        //System.out.println("Number of votes stored in the ballot: " + numberOfVotes);
    	
    	
    }
}
