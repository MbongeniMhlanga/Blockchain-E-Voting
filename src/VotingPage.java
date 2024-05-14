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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VotingPage<T> extends GridPane {
    Stage stage;
    Scene voteStatusScene;
    Button btnSubmit;
    ComboBox<String> partyComboBox;
    Ballot<?> ballot;
    Blockchain<T> blockchain = new Blockchain<>(); // Define the blockchain variable
    private String lastBlockHash; // Store the hash of the last block
    String voterId;



	public VotingPage(User myUser) {
        // Get the shared Ballot instance
        Ballot<?> sharedBallot = Ballot.getInstance();
        // Use the sharedBallot instance
        this.ballot = sharedBallot;
        lastBlockHash = "0";
        setUp();
        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setUp() {
        blockchain = new Blockchain<>(); // Initialize the blockchain
     

        // Register stakeholders with stakes for Proof of Stake
        blockchain.registerStake("stakeholder1", 100);
        blockchain.registerStake("stakeholder2", 200);

        // Load the image
        Image image = new Image("file:img/IECLOGOLOCKUP.png"); // Adjust the file path and name accordingly

        // Create an ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the width of the image
        imageView.setFitHeight(100); // Set the height of the image

        // Create a Label for the title
        Label lblTitle = new Label("Let's Vote");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Create a ComboBox to select the party
        partyComboBox = new ComboBox<>();
        partyComboBox.getItems().addAll("ANC", "DA", "EFF", "IFP", "COPE", "PAC", "VF+", "NFP", "ACTIONSA");
        partyComboBox.setPromptText("Select Party");

        // Create a Submit Vote button
        btnSubmit = new Button("Submit Vote");
        btnSubmit.setOnAction(e -> {
            String selectedParty = partyComboBox.getValue();
             voterId = UUID.randomUUID().toString();
            int votingChoice = partyComboBox.getSelectionModel().getSelectedIndex() + 1;
            if (selectedParty != null) {
                // Cast vote using Ballot instance
                ballot.castVote(voterId, selectedParty);
                System.out.println("Vote submitted for: " + selectedParty);

                VoteStatus vStatus = new VoteStatus();
                Scene newScene = new Scene(vStatus, 300, 300);
                Stage statusStage = new Stage();
                statusStage.setScene(newScene);
                statusStage.setTitle("Vote Status");
                statusStage.show();
            } else {
                System.out.println("Please select a party.");
            }

            Transaction<String> trans = new Transaction(voterId, selectedParty, votingChoice);
            broadcastTransaction((Transaction<String>) trans);
        });

        // Create a VBox to hold the components
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(imageView, lblTitle, partyComboBox, btnSubmit);

        // Add VBox to GridPane
        this.add(vbox, 0, 0);
    }

    @SuppressWarnings("unchecked")
    boolean broadcastTransaction(Transaction<String> transaction) {
        // Get the blockchain instance from MyBlock
        Blockchain<T> blockchain = (Blockchain<T>) MyBlock.holder;

        // Create a new block with the transaction
        List<Transaction<T>> transactions = new ArrayList<>();
        transactions.add((Transaction<T>) transaction);
        Block<T> block = new Block<>(lastBlockHash, transactions); // Use lastBlockHash here

        // Register stakeholder and add block to the blockchain
        String stakeholderAddress = UUID.randomUUID().toString();
        blockchain.registerStake(stakeholderAddress, 100); // Register stakeholder with 100 stakes
        blockchain.addBlock(transactions); // Add the block to the blockchain

        // Update lastBlockHash with the hash of the newly added block
        lastBlockHash = block.getHash();

        // Checking if the blockchain is valid
        if (blockchain.isChainValid()) {
            System.out.println(blockchain);
            System.out.println("Blockchain is Valid");
        } else {
            System.out.println("Blockchain is Not Valid");
        }

        return true; // Placeholder return value
    }


    public String getSelectedParty() {
        return partyComboBox.getValue();
    }

    @SuppressWarnings("unchecked")
    public Ballot<T> getBallot() {
        return (Ballot<T>) this.ballot;
    }

    public void setBallot(Ballot<T> ballot) {
        this.ballot = ballot;
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
