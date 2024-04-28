import java.util.List;

import acsse.csc03a3.Block;
//import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

/**
 * @author Mbongeni Mhlanga
 * @version Mini Project
 * @param <T>
 * @param <T>
 *
 */
public class Miner<T> extends Block<T> {

	//private double reward;
	private String hash;
	private int nonce;
	/**
	 * Miner Constructor
	 * @param previousHash
	 * @param transactions
	 */
	public Miner(String previousHash, List<Transaction<T>> transactions) {
		super(previousHash, transactions);
	}
	
	/**
	 * mineBlock Method
	 * @param prefix
	 * @return
	 */
	public String mineBlock(int prefix) {
	    String prefixString = new String(new char[prefix]).replace('\0', '0');
	    while (!hash.substring(0, prefix).equals(prefixString)) {
	        nonce++;
	        hash = calculateHash();
	    }
	    return hash;
	}
	
}
