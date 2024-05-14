import java.util.ArrayList;
import java.util.List;
import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;

/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */
public class MyBlock<T> extends Blockchain<T> {
    private Blockchain<T> blockchain;
    private List<Block<T>> blocks;
    public MyBlock() {
    blockchain = new  Blockchain<>();
    blocks = new ArrayList<>();
    }

    @SuppressWarnings("rawtypes")
	public static Blockchain<VotingPage> holder = new Blockchain<>();
    
    
    @SuppressWarnings("unchecked")
	public List<Block<?>> getBlockchain() {
        return (List<Block<?>>) blockchain;
    }
    


    public List<Block<T>> getBlocks() {
        return blocks;
    }
    }



