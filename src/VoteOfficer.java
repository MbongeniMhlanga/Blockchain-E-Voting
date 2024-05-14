import java.util.Map;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class VoteOfficer extends GridPane {
    private Ballot<MyTransactions> ballot;
    private GridPane myPane;
 

    private Label lblVotes;
    private Label lblResults;
    private Label lblTitle;
    @FXML
    private TextArea txtVotes, txtResults;
    @FXML
    private Button btnVotes, btnResults;
    private VotingPage<?> votingPage;

   

    public VoteOfficer(User myUser) {
        this.ballot = Ballot.getInstance();
        officerGUI();
        votingPage = new VotingPage<>(myUser);
        
        registerVoteListener();
    }

    public void officerGUI() {
    	
        myPane = new GridPane();
        myPane.setHgap(10);
        myPane.setVgap(10);

        lblVotes = new Label("All Votes:");
        lblResults = new Label("Votes per Party:");
        lblTitle = new Label("Outcome");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        txtVotes = new TextArea("");
        txtVotes.setPrefRowCount(5);
        txtResults = new TextArea("");
        txtResults.setPrefRowCount(5);

        btnVotes = new Button("Votes");
        btnResults = new Button("Results");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(lblTitle, lblVotes, txtVotes, btnVotes, lblResults, txtResults, btnResults);
        myPane.getChildren().addAll(vbox);
        getChildren().addAll(myPane);
    }

    private void registerVoteListener() {
        votingPage.btnSubmit.setOnAction(e -> {
            String selectedParty = votingPage.getSelectedParty();
            if (selectedParty != null) {
                // Create a transaction representing the vote
                String voterId = UUID.randomUUID().toString(); // Generate a unique voter ID
                MyTransactions voteTransaction = new MyTransactions(voterId, selectedParty);

                // Add the vote to the ballot
                ballot.addVote(voteTransaction);

                // Update the GUI to show the selected party
                txtVotes.appendText(selectedParty + "\n");
            }
        });
        
        btnVotes.setOnAction(e -> {
        	
            // Ensure that the ballot is properly initialized
            if (ballot == null) {
                System.out.println("Ballot is not properly initialized.");
                txtVotes.appendText("Ballot is not properly initialized.");
                return;
            }

    

            // Retrieve number of votes
            int numberOfVotes = ballot.getNumberOfVotes();
            System.out.println("Number of votes: " + numberOfVotes);
            txtVotes.appendText("Number of votes: " + numberOfVotes + "\n");
            
            

            // Retrieve votes map
            Map<String, String> votesMap = ballot.getVotesMap();
        	System.out.println("Votes map: " + votesMap); // Add this line

            // Display retrieved votes in the TextArea
            for (Map.Entry<String, String> entry : votesMap.entrySet()) {
                String voterId = entry.getKey();
                String selectedParty = entry.getValue();
                System.out.println("Voter ID: " + voterId + ", Selected party: " + selectedParty);
                txtVotes.appendText("Voter ID: " + voterId + ", Selected party: " + selectedParty + "\n");
            
            }
        });
    }
}
