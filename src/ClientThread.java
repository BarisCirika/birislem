import java.io.BufferedReader;
import java.io.IOException;

public class ClientThread extends Thread {

	private Client client;
	private BufferedReader in;
	private String message;

	ClientThread(Client client, BufferedReader in) {
		this.client = client;
		this.in = in;
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				message = in.readLine();
				if(message!=null){
					
				if(message.equals("START_GAME")){
					client.createGame();
				}
				else if(message.equals("START_OTHER_GAME")){
					client.doAnotherQuestion();
				}
				else if(message.length()>=5 && message.substring(0,5).equals("Score")){
					String a[] = message.split("/");
					if(a[2].equals(client.getName())){
						System.out.println("scora dŸstŸ"+ client.getName());
						System.out.println("score= "+ a[1]);
						client.setScore(a[1]);
					}
				}
				else if(message.length()>4 && message.substring(0,4).equals("TIME")){
					String a[] = message.split("/");
					client.setGameTime(a[1]);
				}
				else if(message.equals("FINISH_GAME")){
					client.getFinishScreen();
				}
				else if(message.length()>6 && message.substring(0,6).equals("WINNER")){
					String a[] = message.split("/");
					if(client.getName().equals(a[1])){
						client.displayWinner("WIN", a[2]);
					}
				}
				else if(message.length()>6 && message.substring(0,6).equals("LOOSER")){
					String a[] = message.split("/");
					if(client.getName().equals(a[1])){
						client.displayWinner("LOST", a[2]);
					}
				}
				else
					client.setQuestion(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
