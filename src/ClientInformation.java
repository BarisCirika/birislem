
public class ClientInformation {

	String clientName;
	int clientScore;
	
	public ClientInformation(String clientName){
		this.clientName = clientName;
		this.clientScore=0;
	}

	public int getClientScore() {
		return clientScore;
	}

	public void setClientScore() {
		this.clientScore+=10;
	}
	
	public String getClientName(){
		return clientName;
	}
	
}
