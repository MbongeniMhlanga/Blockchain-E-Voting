import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * @author Mbongeni Mhlanga
 * @version Mini Project
 *
 */
public class Login extends GridPane {
    // Variables
    GridPane myPane;
    Stage stage;
    Scene votePageScene;
    Scene officerScene;
    Button btnLogin;
    TextField txtEmail;// For Login Credentials
    private Label lblPassword, lblEmail, lblTitle;
    PasswordField txtPassword;
    private RadioButton userRadio, adminRadio;

    /**
     * VotingPane Constructor
     * @param officerScene 
     */
    public Login(Stage v2Stage, Scene votePageScene, Scene officerScene) {

        stage = v2Stage;
        this.votePageScene = votePageScene;
        this.officerScene = officerScene;

        // GridPane
        myPane = new GridPane();
        setAlignment(Pos.CENTER);
        setHgap(25);
        setVgap(5);

        // Labels
        // For the Login credentials
        lblEmail = new Label("Email ");
        lblPassword = new Label("Password ");
        // Title
        lblTitle = new Label("Welcome, Login");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;"); // Styling for the title

        // Button
        btnLogin = new Button("Login");

        // TextFields
        // For the Login credentials
        txtEmail = new TextField("");

        // PasswordField
        txtPassword = new PasswordField();

        // Adding to GridPane
        myPane.add(lblTitle, 0, 0, 2, 1); // Spanning two columns for full width
        myPane.add(lblEmail, 0, 1);
        myPane.add(txtEmail, 1, 1);
        myPane.add(lblPassword, 0, 2);
        myPane.add(txtPassword, 1, 2);
        myPane.add(btnLogin, 0, 3);
        // Adding The Children to the GridPane
        getChildren().addAll(myPane);

        // Button For Logging In
        btnLogin.setOnAction(e -> {
            String userEmail = txtEmail.getText();
            String userPass = txtPassword.getText();

            // Checking if the fields are empty
            if (userEmail.isEmpty() || userPass.isEmpty()) {
                System.out.println("The Fields Are Please Enter Correct Credentials!!!!!!!");
                return;
            }

            // Reading user information from the database.txt file
            try (BufferedReader reader = new BufferedReader(new FileReader("database.txt"))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    // Splitting the line into fields
                    String[] fields = line.split(",");
                    // Checking if email and password match
                    if (fields.length == 5 && fields[2].equals(userEmail) && fields[3].equals(userPass)) {

                        // If the user is found, set found to true and break the loop
                        found = true;
                        System.out.println("User Logged In as: " + fields[4]);

                        break;
                    }
                }
                if (found) {
                    // Navigate to voter page
                    System.out.println("User Logged In as: " + userEmail);
                    if(userRadio.isSelected()) {
                    	 stage.setScene(votePageScene);
                    	 stage.show();
                    	 
                    // Navigate to Vote Officer page
                    }else {
                    	stage.show();
                    }
                   
                } else {
                    System.out.println("Incorrect Login Credentials");
                }

            } catch (IOException ex) {
                // Handle file reading errors
                ex.printStackTrace();
            }
        });
    }
}
