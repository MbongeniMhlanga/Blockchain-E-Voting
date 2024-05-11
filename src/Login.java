import java.util.List;

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
    private RadioButton userRadio;
	FileHandler fhandler = new FileHandler();

    /**
     * VotingPane Constructor
     * @param officerScene 
     */
    @SuppressWarnings("unchecked")
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
            //String userEmail = txtEmail.getText();
            //String userPass = txtPassword.getText();

            // Checking if the fields are empty
          //  if (userEmail.isEmpty() || userPass.isEmpty()) {
               // System.out.println("Please enter both email and password.");
               // return;
           // }
            
            
            
            
            
            try {
		        List<User> users = fhandler.readUsers();
		        String userEmail = txtEmail.getText();
	            String userPass = txtPassword.getText();
 Ballot ballot = new Ballot();
		        // Find the user in the list with the matching username
		        User user = users.stream()
		                         .filter(u -> u.getContact().equals(userEmail))
		                         .findFirst()
		                         .orElse(null);

		        if (user != null && user.getCreatePass().equals(userPass)) {
		            System.out.println("Login successful!");
		            // Proceed with opening the user's role-specific page
		         // Check user's role and open the corresponding page
					if ("Voter".equals(user.getGroup())) {
						VotingPage gui = new VotingPage(v2Stage, officerScene, ballot);
					
						Scene welcomeScene = new Scene(gui, 300, 200);
						Stage welcomeStage = new Stage();
						welcomeStage.setScene(welcomeScene);
						welcomeStage.setTitle("Welcome Page");
						welcomeStage.show();
					} else if ("VoteOfficer".equals(user.getGroup())) {
						VoteOfficer gui = new VoteOfficer(v2Stage, officerScene, ballot);
						Scene welcomeScene = new Scene(gui, 300, 200);
						Stage welcomeStage = new Stage();
						welcomeStage.setScene(welcomeScene);
						welcomeStage.setTitle("Vote Officer");
						welcomeStage.show();
					}
					
		        } else {
		            System.out.println("Invalid credentials!");
		
		            
		            
		            
		            
					
				} 
			} catch (Exception ex) {
				ex.printStackTrace();
			}

            // Reading user information from the database.txt file
//          //  try (BufferedReader reader = new BufferedReader(new FileReader("database.txt"))) {
//               // String line;
//                boolean found = false;
//                while ((line = reader.readLine()) != null) {
//                    // Splitting the line into fields
//                    String[] fields = line.split(",");
//                    // Checking if email and password match
//                    if (fields.length == 5 && fields[2].equals(userEmail) && fields[3].equals(userPass)) {
//                        // If the user is found, set found to true and break the loop
//                        found = true;
//                        System.out.println("User Logged In as: " + fields[4]);
//                        if (fields[4].equals("Voter")) {
//                            // Redirect to the voting page
//                            // Assuming votePageScene is already defined
//                            stage.setScene(votePageScene);
//                            stage.setTitle("Voter Page");
//                            stage.show();
//                        } else if (fields[4].equals("VoteOfficer")) {
//                            // Redirect to the vote officer page
//                            // Assuming officerScene is already defined
//                            stage.setScene(officerScene);
//                            stage.setTitle("Vote Officer Page");
//                            stage.show();
//                        }
//                        break;
//                    }
//                }
//                if (!found) {
//                    System.out.println("Incorrect Login Credentials");
//                }
//
//            } catch (IOException ex) {
//                // Handle file reading errors
//                ex.printStackTrace();
//            }
            
            
            
            
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
    
    public UserType getUserType() {
        if (userRadio.isSelected()) {
            return UserType.VOTER;
        } else {
            return UserType.VOTE_OFFICER;
        }
    }
}
