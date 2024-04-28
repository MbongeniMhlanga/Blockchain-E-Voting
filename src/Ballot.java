/**
 * @author Mbongeni Mhlanga
 * @version Mini Project
 *
 */
import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Ballot<T> {
    private List<Transaction<T>> votes;

    public Ballot() {
        this.votes = new ArrayList<>();
    }

    public void addVote(Transaction<T> vote) {
        votes.add(vote);
    }

    public int getNumberOfVotes() {
        return votes.size();
    }
}
