import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VoteStatus extends StackPane {
    Stage stage;
    Scene regScene; // Reference to the login scene

    public VoteStatus(Stage vStatusStage, Scene regScene) {
        stage = vStatusStage;
        this.regScene = regScene;

        // Create the label for the confirmation message
        Label lblConfirmation = new Label("Vote Casted \u2713"); // Unicode character for tick symbol
       String show;
       //Label lblVoterID = new Label(show.getVoterId());
        lblConfirmation.setFont(new Font("Arial", 24));
        lblConfirmation.setTextFill(Color.BLACK);

        // Create a circle around the tick symbol
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setFill(Color.ALICEBLUE); // Set the circle color
        circle.setStroke(Color.BLACK); // Set the circle border color
        circle.setStrokeWidth(2); // Set the circle border width

        // Stack the label on top of the circle
        StackPane.setAlignment(lblConfirmation, Pos.CENTER);
        StackPane.setAlignment(circle, Pos.CENTER);
        getChildren().addAll(circle, lblConfirmation);

     // Create a login button
        Button btnRegister = new Button("Register");
        // Button For Logging In
        btnRegister.setOnAction(e -> {
        	Register registerPane = new Register(stage, regScene); // Assuming stage and regScene are properly initialized
        	Scene regScene1 = new Scene(registerPane); // Initialize regScene here
            stage.setScene(regScene1); // Navigate to the register scene
            stage.setTitle("Register"); // Set the title to "Register" if needed
            stage.show();
        });

        // Create an exit button
        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            // Close the stage when the button is clicked
            stage.close();
        });

        // Add the login and exit buttons to a VBox
        VBox buttonsBox = new VBox(10, btnRegister, btnExit);
        buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsBox.setPadding(new Insets(10));

        // Add the VBox to the StackPane
        getChildren().add(buttonsBox);

        // Apply CSS to set the background color
        setStyle("-fx-background-color: lavender;");

        // Set the size of the StackPane
        setPrefSize(300, 200);
    }
}
