import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */

public class Register extends StackPane {

    // Variables
    FileHandler fhandler = new FileHandler();
    User user;
    Stage stage = new Stage();
    Scene logScene;
    Alert alert = new Alert(AlertType.INFORMATION);
    GridPane myPane;
    Button btnRegister;
    Button btnLogin;
    private TextField txtName, txtEmail, txtSurname;
    private Label lblName, lblEmail, lblSurname, lblCreatePass, lblReEnterPass, lblQuestion;

    /**
     * VotingPane Constructor
     */
    public Register(User myUser) {

        // GridPane
        myPane = new GridPane();
        myPane.setAlignment(Pos.CENTER);
        myPane.setHgap(10);
        myPane.setVgap(10);

        // Load the image
        Image image = new Image("file:img/IEClogo.png"); // Adjust the file path and name accordingly

        // Create an ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the width of the image
        imageView.setFitHeight(100); // Set the height of the image

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

        // ComboBox for selecting user role
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Voter", "VoteOfficer");
        roleComboBox.setValue("Select"); // Set default value

        // Adding to GridPane
        myPane.add(imageView, 0, 0, 2, 1); // Add the ImageView to span two columns
        myPane.add(lblTitle, 0, 1, 2, 1); // Add the title to span two columns
        myPane.add(lblName, 0, 2);
        myPane.add(txtName, 1, 2);
        myPane.add(lblSurname, 0, 3);
        myPane.add(txtSurname, 1, 3);
        myPane.add(lblEmail, 0, 4);
        myPane.add(txtEmail, 1, 4);
        myPane.add(lblCreatePass, 0, 5);
        myPane.add(txtCreatePass, 1, 5);
        myPane.add(lblReEnterPass, 0, 6);
        myPane.add(txtReEnterPass, 1, 6);
        myPane.add(roleComboBox, 1, 7); // Add the ComboBox
        myPane.add(btnRegister, 0, 8);
        myPane.add(lblQuestion, 0, 9);
        myPane.add(btnLogin, 1, 9);

        // Adding The Children to the GridPane
        getChildren().addAll(myPane);


        // Button For Registering In
        btnRegister.setOnAction(e -> {
            String userName = txtName.getText();
            String userSurname = txtSurname.getText();
            String email = txtEmail.getText();
            String createPass = txtCreatePass.getText();
            String reEnterPass = txtReEnterPass.getText();
            String role = roleComboBox.getValue();

            //Initialising a user
            User user = new User(userName, userSurname, email, createPass, role);

            // Check if the user already exists
            if (fhandler.userExists(user)) {
                // User already exists, show alert
                alert.setTitle("Registration");
                alert.setHeaderText(null);
                alert.setContentText(user.getUserName() + " already exists in the system.");

                // Display the alert dialog
                alert.showAndWait();
                return; // Stop further processing
            }

            // Validate if all fields are filled
            if (user != null) {
                // Validate if passwords match
                if (!createPass.equals(reEnterPass)) {
                    // Handle password mismatch
                    System.out.println("Passwords do not match.");
                    return;
                }
                // Set the title and content text
                alert.setTitle("Registration");
                alert.setHeaderText(null);
                alert.setContentText(user.getUserName() + " has registered successfully");

                // Display the alert dialog
                alert.showAndWait();
                System.out.println(user.getUserName() + " has registered successfully");

                // Write the user to file
                fhandler.writeUser(user);

                // Show login scene
                Login log = new Login();
                Scene loginScene = new Scene(log, 300, 300);
                Stage logStage = new Stage();
                logStage.setScene(loginScene);
                logStage.setTitle("Login");
                logStage.show();
                loginScene.getRoot().setStyle("-fx-background-color: lavender;");
            }
        });


        // Button For Logging In
        btnLogin.setOnAction(e -> {
            Login log = new Login();
            Scene loginScene = new Scene(log, 300, 300);
            Stage logStage = new Stage();
            logStage.setScene(loginScene);
            logStage.setTitle("Login");
            logStage.show();
            loginScene.getRoot().setStyle("-fx-background-color: lavender;");
        });
    }

    public String getName() {
        return txtName.getText();
    }

    public String getSurname() {
        return txtSurname.getText();
    }

}
