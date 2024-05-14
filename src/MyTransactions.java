/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */

class MyTransactions {
    private String voterId;
    private String selectedParty;

    public MyTransactions(String voterId, String selectedParty) {
        this.voterId = voterId;
        this.selectedParty = selectedParty;
    }
 
	public String getVoterId() {
        return voterId;
    }

    public String getSelectedParty() {
        return selectedParty;
    } 
}
