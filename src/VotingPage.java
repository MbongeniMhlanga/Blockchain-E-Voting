import java.util.ArrayList;
//import java.util.Date;
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
/**
 * @author Mbongeni Mhlanga
 * @version Mini Project
 *
 */
public class VotingPage<T> extends GridPane {
    Stage stage;
    Scene voteStatusScene;
    Button btnSubmit;
   // private List<Transaction<T>> pendingTransactions = new ArrayList<>();
    private Blockchain<T> blockchain = new Blockchain<>(); // Define the blockchain variable

	String voterId;

    @SuppressWarnings({ "unchecked" })
    public VotingPage(Stage stage, Scene voteStatusScene) {
        this.stage = stage;
        this.voteStatusScene = voteStatusScene;
        blockchain = new Blockchain<>(); // Initialize the blockchain

        // Register stakeholders with stakes for Proof of Stake
        blockchain.registerStake("stakeholder1", 100);
        blockchain.registerStake("stakeholder2", 200);

        // Create a Label for the title
        Label lblTitle = new Label("Let's Vote");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Create a ComboBox to select the party
        ComboBox<String> partyComboBox = new ComboBox<>();
        partyComboBox.getItems().addAll("ANC", "DA", "EFF", "IFP", "COPE", "PAC", "VF+", "NFP", "ACTIONSA");
        partyComboBox.setPromptText("Select Party");

        // Create a Submit Vote button
         btnSubmit = new Button("Submit Vote");
        btnSubmit.setOnAction(e -> {
            String selectedParty = partyComboBox.getValue();
            if (selectedParty != null) {
                // Create a transaction representing the vote
               voterId = UUID.randomUUID().toString();
                System.out.println("Voter ID: " + voterId);
                // Pass selectedParty as the data parameter
                Transaction<String> voteTransaction = new Transaction<>(voterId, selectedParty, selectedParty);
                System.out.println("Vote submitted for: " + selectedParty);
                // Further process the transaction, such as broadcasting it to the blockchain network
                boolean success = broadcastTransaction((Transaction<T>) voteTransaction);
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
        vbox.getChildren().addAll(lblTitle, partyComboBox, btnSubmit);

        // Add VBox to GridPane
        this.add(vbox, 0, 0);
    }

    /**
     * broadcastTransactions Method broadcasts pending transactions to the blockchain
     */
    private boolean broadcastTransaction(Transaction<T> transaction) {
        System.out.println("Broadcasting transaction: " + transaction);
        List<Block<T>> blockList = new ArrayList<>();
        
        String previousHash = blockList.isEmpty() ? "0" : blockList.get(blockList.size() - 1).getHash();
        List<Transaction<T>> transactions = new ArrayList<>();
        transactions.add(transaction);
        Block<T> block = new Block<>(previousHash, transactions);
        String stakeholderAddress = UUID.randomUUID().toString();
        blockchain.registerStake(stakeholderAddress, 100); // Register stakeholder with 100 stakes
        blockchain.addBlock(transactions); // Add the list of transactions to the blockchain
        blockList.add(block); // Add the block to the blockList
        
        // Output receiver, sender, timestamp, and data of the transaction
        //System.out.println("Sender: " + transaction.getSender());
        //System.out.println("Receiver: " + transaction.getReceiver());
        //System.out.println("Timestamp: " + new Date(transaction.getTimestamp()));
        //System.out.println("Data: " + transaction.getData());
        
        return true; // Placeholder return value
    }
    
    /**
	 * @return the voterId
	 */
	public String getVoterId() {
		return voterId;
	}

	/**
	 * @param voterId the voterId to set
	 */
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}


}
