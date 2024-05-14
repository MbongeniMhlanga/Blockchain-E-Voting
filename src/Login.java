import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private RadioButton userRadio;
	FileHandler fhandler = new FileHandler();
	
	  Alert alert = new Alert(AlertType.INFORMATION);

      

    /**
     * VotingPane Constructor
     * @param officerScene 
     */
    public Login() {

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
            try {
		        List<User> users = fhandler.readUsers();
		        String userEmail = txtEmail.getText();
	            String userPass = txtPassword.getText();
              
		        // Find the user in the list with the matching username
		        User user = users.stream()
		                         .filter(u -> u.getEmail().equals(userEmail))
		                         .findFirst()
		                         .orElse(null);

		        if (user != null && user.getCreatePass().equals(userPass)) {
		        	
		        	// Set the title and content text
		            alert.setTitle("Login");
		            alert.setHeaderText(null); // Optional header text
		            alert.setContentText(user.email + " has Logged in successfully");

		            // Display the alert dialog
		            alert.showAndWait();
		            System.out.println("Login successful!");
		            // Proceed with opening the user's role-specific page
		         // Check user's role and open the corresponding page
					if ("Voter".equals(user.getRole())) {
						@SuppressWarnings("rawtypes")
						VotingPage voting = new VotingPage(user);
						Scene votingScene = new Scene(voting, 300, 250);
						Stage votingStage = new Stage();
						votingStage.setScene(votingScene);
						votingStage.setTitle("Voting Page");
						votingScene.getRoot().setStyle("-fx-background-color: lavender;");
						votingStage.show();
						
					} else if ("VoteOfficer".equals(user.getRole())) {
						VoteOfficer officer = new VoteOfficer(user);
						Scene offScene = new Scene(officer, 800, 500);
						Stage offStage = new Stage();
						offStage.setScene(offScene);
						offStage.setTitle("Vote Officer");
						offScene.getRoot().setStyle("-fx-background-color: lavender;");
						offStage.show();
					}
					
		        } else {
		        	// Set the title and content text
		            alert.setTitle("Login");
		            alert.setHeaderText(null); // Optional header text
		            alert.setContentText("Invalid credentials! , please enter the correct details");

		            // Display the alert dialog
		            alert.showAndWait();
		            System.out.println("Invalid credentials!");
					
				} 
			} catch (Exception ex) {
				ex.printStackTrace();
			}  
        });

    }
    
    // Accessors and Mutators
    public String getEmail() {
        return txtEmail.getText();
    }

    public String getPassword() {
        return txtPassword.getText();
    }

    public RadioButton getUserRadio() {
        return userRadio;
    }

    public void setEmail(String email) {
        txtEmail.setText(email);
    }

    public void setPassword(String password) {
        txtPassword.setText(password);
    }

    public void setUserRadio(RadioButton userRadio) {
        this.userRadio = userRadio;
    }
    
}
