import java.util.List;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MyBlock<T> extends Block<T> {
    public MyBlock(String previousHash, List<Transaction<T>> transactions) {
        super(previousHash, transactions);
    }

    public String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest((getPreviousHash() + getTransactions().toString()).getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}


