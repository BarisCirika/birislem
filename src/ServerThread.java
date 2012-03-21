import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Vector;


public class ServerThread extends Thread{
	private Server server;
	private Socket socket;
	private String state;
    public ServerThread(Server server,Socket socket){
    	this.server = server;
    	this.socket = socket;
    	start();
    }
    
	@Override
	public void run() {
		
		while (true) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				state = in.readLine();
				if(state!=null){
					if (state.equals("finish")) {
						server.broadcastNotification("finish");
					} else if (state.equals("WAITING_START")) {
						server.broadcastStart();
					}
					else if(state.equals("WAITING_OTHER_QUESTION")){
						server.broadcastNewGame();
					}
					else if(state.length()>=14 && state.substring(0,14).equals("FOUND_QUESTION")){
						String a[]=state.split("/");
						server.setClientScore(a[1]);
						server.broadcastNewGame();					
					}
					else if(state.length()>=14 && state.substring(0,18).equals("NOT_FOUND_QUESTION")){
						server.notFound();
					}
					else if(state.equals("WAITING_TIME")){
						server.sendTime();
					}
					else if(state.equals("WAITING_MAXIMUM_SCORES")){
						server.calculateMaximumScores();
					}
					else if(state.length()>=4 && state.substring(0,4).equals("Name")){
						String b[]=state.split("/");
						server.setClientInformation(b[1]);
						server.broadcastQuestion();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
