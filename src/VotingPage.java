import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VotingPage<T> extends GridPane {
	Stage stage;
	Scene voteStatusScene;
	private List<Block<T>> blockList = new ArrayList<>();
	private Blockchain<T> blockchain = new Blockchain<>(); // Define the blockchain variable

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VotingPage(Stage stage, Scene voteStatusScene) {
		this.stage = stage;
		this.voteStatusScene = voteStatusScene;
		blockchain = new Blockchain<>();; // Initialize the blockchain

		// Create a Label for the title
		Label lblTitle = new Label("Let's Vote");
		lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		// Create a ComboBox to select the party
		ComboBox<String> partyComboBox = new ComboBox<>();
		partyComboBox.getItems().addAll("ANC", "DA", "EFF", "IFP", "COPE", "PAC", "VF+", "NFP", "ACTIONSA");
		partyComboBox.setPromptText("Select Party");

		// Create a Submit Vote button
		Button submitButton = new Button("Submit Vote");
		submitButton.setOnAction(e -> {
			String selectedParty = partyComboBox.getValue();
			if (selectedParty != null) {
				// Create a transaction representing the vote
				String voterId = UUID.randomUUID().toString();
				System.out.println("Voter ID: " + voterId);
				int votingChoice = partyComboBox.getSelectionModel().getSelectedIndex() + 1;
				// Get the index of the selected party and add 1 to make it non-zero based
				Transaction<T> voteTransaction = new Transaction(voterId, selectedParty, votingChoice);
				System.out.println("Vote submitted for: " + selectedParty);
				// Further process the transaction, such as broadcasting it to the blockchain network
				boolean success = broadcastTransaction(voteTransaction);
				if (success) {
					System.out.println("Transaction broadcasted successfully.");
					VoteStatus voteStatus = new VoteStatus(stage, voteStatusScene);
					Scene scene = new Scene(voteStatus);
					stage.setScene(scene); // Navigate to the vote status scene
				} else {
					System.out.println("Failed to broadcast transaction.");
				}
			} else {
				System.out.println("Please select a party.");
			}
		});

		// Create a VBox to hold the components
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(lblTitle, partyComboBox, submitButton);

		// Add VBox to GridPane
		this.add(vbox, 0, 0);
	}

	/**
	 * broadcastTransaction Method of type Boolean
	 * 
	 * @param transaction
	 * @return true
	 */
	private boolean broadcastTransaction(Transaction<T> transaction) {
		System.out.println("Broadcasting transaction: " + transaction);
		
		String previousHash = blockList.isEmpty() ? "0" : blockList.get(blockList.size() - 1).getHash();
		List<Transaction<T>> transactions = new ArrayList<>();
		transactions.add(transaction);
		Block<T> block = new Block<>(previousHash, transactions);
		String stakeholderAddress = UUID.randomUUID().toString();
		blockchain.registerStake(stakeholderAddress, 100); // Register stakeholder with 100 stakes
		blockchain.addBlock(transactions); // Add the list of transactions to the blockchain
		blockList.add(block); // Add the block to the blockList
		
		//System.out.println("Transaction Added to Block & Block Added to Blockchain");
		return true; // Placeholder return value
	}








}
