
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

public class BlockchainDecorator<T> {
    private Blockchain<T> blockchain;

    public BlockchainDecorator(Blockchain<T> blockchain) {
        this.blockchain = blockchain;
    }

    public void addTransaction(Transaction<T> transaction, Block<T> lastBlock) {
        List<Transaction<T>> transactions = new ArrayList<>(lastBlock.getTransactions());
        transactions.add(transaction);
        MyBlock<T> newBlock = new MyBlock<>(lastBlock.getHash(), transactions);
        newBlock.setNonce(new Random().nextLong());
        newBlock.calculateHash();
        blockchain.addBlock((List<Transaction<T>>) newBlock);
    }

    public void addBlock(Block<T> block) {
        blockchain.addBlock((List<Transaction<T>>) block);
    }
}

