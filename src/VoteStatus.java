import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */

public class VoteStatus extends StackPane {
	Stage stage = new Stage();

    /**
     * Vote Status Constructor
     */
    public VoteStatus() {
      
       statusGUI();
    }
    
    public void statusGUI() {
    	
    	 // Create the label for the confirmation message
        Label lblCast= new Label("Vote Casted \u2713" ); // Unicode character for tick symbol
       //Label lblVoterID = new Label(show.getVoterId());
        lblCast.setFont(new Font("Arial", 24));
        lblCast.setTextFill(Color.BLACK);

        // Create a circle around the tick symbol
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setFill(Color.ALICEBLUE); // Set the circle color
        circle.setStroke(Color.BLACK); // Set the circle border color
        circle.setStrokeWidth(2); // Set the circle border width

        // Stack the label on top of the circle
        StackPane.setAlignment(lblCast, Pos.CENTER);
        StackPane.setAlignment(circle, Pos.CENTER);
        getChildren().addAll(circle, lblCast);

        // Create an exit button
        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
        	
            // Close the stage when the button is clicked
           System.exit(0);
        });

        // Add the login and exit buttons to a VBox
        VBox btnBox = new VBox(10, btnExit);
        btnBox.setAlignment(Pos.BOTTOM_RIGHT);
        btnBox.setPadding(new Insets(10));

        // Add the VBox to the StackPane
        getChildren().add(btnBox);

        // Apply CSS to set the background color
        setStyle("-fx-background-color: lavender;");

        // Set the size of the StackPane
        setPrefSize(300, 200);
    }
}
