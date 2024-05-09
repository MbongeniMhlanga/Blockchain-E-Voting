import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;

public class Register extends StackPane {
    // Variables
    Stage stage;
    Scene logScene;
    Scene regScene;

    GridPane myPane;
    Button btnRegister;
    Button btnLogin;
    private TextField txtName, txtEmail, txtSurname;// For Registering Credentials
    private Label lblName, lblEmail, lblSurname, lblCreatePass, lblReEnterPass, lblQuestion;

    private RadioButton userRadio, adminRadio;

    /**
     * VotingPane Constructor
     */
    public Register(Stage vStage, Scene logScene) {
        stage = vStage;
        this.logScene = logScene;

        // GridPane
        myPane = new GridPane();
        myPane.setAlignment(Pos.CENTER);
        myPane.setHgap(10);
        myPane.setVgap(10);

        // Labels
        Label lblTitle = new Label("Register To Vote");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;"); // Styling for the title
        lblName = new Label("Name");
        lblSurname = new Label("Surname");
        lblEmail = new Label("Email");
        lblCreatePass = new Label("Create Password");
        lblReEnterPass = new Label("Re-Enter Password");
        lblQuestion = new Label("Already have an account? Login");
        lblQuestion.setTextFill(Color.NAVY);

        // Buttons
        btnRegister = new Button("Register");
        btnLogin = new Button("Login");

        // TextFields
        txtName = new TextField("");
        txtSurname = new TextField("");
        txtEmail = new TextField("");

        // Passwords Fields
        PasswordField txtCreatePass = new PasswordField();
        PasswordField txtReEnterPass = new PasswordField();

        // ToggleGroup for radio buttons
        ToggleGroup group = new ToggleGroup();
        userRadio = new RadioButton("Voter");
        userRadio.setToggleGroup(group);
        adminRadio = new RadioButton("Vote Officer");
        adminRadio.setToggleGroup(group);

        // Adding to GridPane
        myPane.add(lblTitle, 0, 0, 2, 1); // Spanning two columns for full width
        myPane.add(lblName, 0, 1);
        myPane.add(txtName, 1, 1);
        myPane.add(lblSurname, 0, 2);
        myPane.add(txtSurname, 1, 2);
        myPane.add(lblEmail, 0, 3);
        myPane.add(txtEmail, 1, 3);
        myPane.add(lblCreatePass, 0, 4);
        myPane.add(txtCreatePass, 1, 4);
        myPane.add(lblReEnterPass, 0, 5);
        myPane.add(txtReEnterPass, 1, 5);
        myPane.add(btnRegister, 0, 7);
        myPane.add(lblQuestion, 0, 8);
        myPane.add(btnLogin, 1, 8);
        myPane.add(userRadio, 0, 6);
        myPane.add(adminRadio, 1, 6);

        // Adding The Children to the GridPane
        getChildren().addAll(myPane);

        // Button For Registering In
        btnRegister.setOnAction(e -> {
            String userName = txtName.getText();
            String userSurname = txtSurname.getText();
            String contact = txtEmail.getText();
            String createPass = txtCreatePass.getText();
            String reEnterPass = txtReEnterPass.getText();

            // Validate if all fields are filled
            if (userName.isEmpty() || userSurname.isEmpty() || contact.isEmpty() || createPass.isEmpty()
                    || reEnterPass.isEmpty()) {
                // Handle empty fields
                System.out.println("Please fill in all fields.");
                return;
            }

            // Validate if passwords match
            if (!createPass.equals(reEnterPass)) {
                // Handle password mismatch
                System.out.println("Passwords do not match.");
                return;
            }

            // Get selected role
            String role;
            if (adminRadio.isSelected()) {
                role = "Vote Officer";
            } else {
                role = "Voter";
            }

            // Write user information to the text file along with role
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt", true))) {
                writer.write(userName + "," + userSurname + "," + contact + "," + createPass.hashCode() + "," + role);
                writer.newLine(); // Add a new line for the next entry
                System.out.println(role + ":" + userName + " has registered successfully.");

                // Switching to the login scene after successful registration
                stage.setScene(logScene);
            } catch (IOException ex) {
                // Handle file writing errors
                System.err.println("Error writing to file: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        // Button For Logging In
        btnLogin.setOnAction(e -> {
            stage.setScene(logScene);
            //stage.show();
        });
    }
    
    public String getName() {
    	return txtName.getText() ;
    }
    
    public String getSurname() {
    	return txtSurname.getText() ;
    }
}
