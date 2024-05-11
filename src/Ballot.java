import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Transaction;

/**
 * Represents a ballot containing votes casted by voters.
 * Each vote is represented as a Transaction.
 * @param <T> The type of data contained in the Transaction (e.g., String for party names).
 */
public class Ballot<T> {
    private List<Transaction<T>> votes;

    public Ballot() {
        this.votes = new ArrayList<>();
    }

    public void addVote(Transaction<String> voteTransaction) {
        votes.add((Transaction<T>) voteTransaction);
    }

    public int getNumberOfVotes() {
        return votes.size();
    }

    // Define the castVote method to accept voter ID and selected party
    public void castVote(String voterId, String selectedParty) {
        // Create a transaction representing the vote
        Transaction<String> voteTransaction = new Transaction<>(voterId, selectedParty, selectedParty);
        
        // Add the vote to the list of votes
        addVote(voteTransaction);
    }

    public List<Transaction<T>> getVotes() {
        return votes;
    }
}
