
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */
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
	private ComboBox<String> partyComboBox;
	private List<String> parties; // Declare the list of parties

	public VoteOfficer(User myUser) {
		this.ballot = Ballot.getInstance();
		officerGUI();
		// votingPage = new VotingPage<>(myUser);
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
		partyComboBox = new ComboBox<>();
		parties = Arrays.asList("ANC", "DA", "EFF", "IFP", "COPE", "PAC", "VF+", "NFP", "ACTIONSA"); // Initialize the
																										// list of
																										// parties
		setPartyComboBoxItems(parties); // Populate the partyComboBox with the list of parties
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(lblTitle, lblVotes, txtVotes, btnVotes, lblResults, txtResults, btnResults);
		myPane.getChildren().addAll(vbox);
		getChildren().addAll(myPane);
	}

	private void registerVoteListener() {
		btnResults.setOnAction(event -> {
			// Ensure that the ballot is properly initialized
			if (ballot == null) {
				System.out.println("Ballot is not properly initialized.");
				txtResults.appendText("Ballot is not properly initialized.");
				return;
			}

			// Retrieve votes map
			Map<String, String> votesMap = ballot.getVotesMap();

			// Create a HashMap to store the count of votes for each party
			Map<String, Integer> partyVoteCount = new HashMap<>();

			// Initialise vote count for each party to zero
			for (String party : partyComboBox.getItems()) {
				partyVoteCount.put(party, 0);
			}

			// Count votes for each party
			for (String selectedParty : votesMap.values()) {
				if (partyVoteCount.containsKey(selectedParty)) {
					int currentCount = partyVoteCount.get(selectedParty);
					partyVoteCount.put(selectedParty, currentCount + 1);
				}
			}

			txtResults.clear(); // Clear previous results
			txtResults.appendText("Votes per party\n");
			for (Map.Entry<String, Integer> entry : partyVoteCount.entrySet()) {
				String party = entry.getKey();
				int voteCount = entry.getValue();
				txtResults.appendText(party + ": " + voteCount + "\n");
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
			System.out.println("Votes map: " + votesMap); 

			// Display retrieved votes in the TextArea
			for (Map.Entry<String, String> entry : votesMap.entrySet()) {
				String voterId = entry.getKey();
				String selectedParty = entry.getValue();
				System.out.println("Voter ID: " + voterId + ", Selected party: " + selectedParty);
				txtVotes.appendText("Voter ID: " + voterId + ", Selected party: " + selectedParty + "\n");

			}
		});
	}

	public void setPartyComboBoxItems(List<String> parties) {
		partyComboBox.getItems().addAll(parties);
	}
}
