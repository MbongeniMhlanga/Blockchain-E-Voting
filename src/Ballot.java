import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ballot<T> {
	private static Ballot<?> instance; // Singleton instance
	private List<MyTransactions> votes;
	private Map<String, String> votesMap; // Create the HashMap here

	Ballot() {
		System.out.println("Ballot constructor called"); // Add this line

		this.votes = new ArrayList<>();
		this.votesMap = new HashMap<>(); // Initialize the HashMap here
	}



    // Method to get the singleton instance (synchronized for thread safety)
    @SuppressWarnings("unchecked")
    public static synchronized Ballot<MyTransactions> getInstance() {
    	if (instance == null) {
    		instance = new Ballot<Object>();
    		System.out.println("New Ballot instance created");
    	} else {
    		System.out.println("Returning existing Ballot instance");
    	}
    	return (Ballot<MyTransactions>) instance;
    }

 // Method to add a vote to the ballot
 	public void addVote(MyTransactions voteTransaction) {
 		votes.add(voteTransaction);
 		votesMap.put(voteTransaction.getVoterId(), voteTransaction.getSelectedParty()); // Update the HashMap here
 		System.out.println("Vote added: " + voteTransaction.getVoterId() + ", " + voteTransaction.getSelectedParty());
 		System.out.println("Vote added to list: " + voteTransaction); // Add this line
 		

 	}



    // Method to cast a vote with voter ID and selected party
    public void castVote(String voterId, String selectedParty) {
        // Create a transaction representing the vote
    	MyTransactions voteTransaction = new MyTransactions(voterId, selectedParty);
        
        // Add the vote to the list of votes
        addVote(voteTransaction);
        
        System.out.println("Vote casted: " + voteTransaction); // Add this line
    

    }

    // Method to get the number of votes
    public int getNumberOfVotes() {
    	System.out.println("Votes list: " + votes); // Add this line

        return votes.size();
    }

 // Method to get the map of votes (voterId -> selectedParty)
 	public Map<String, String> getVotesMap() {
 		System.out.println("Votes map: " + votesMap); // Add this line

 		return votesMap; // Return the existing HashMap
 	}



}
