import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Server {
	private Vector<ClientInformation> clientInformationVector;
	private int maxPlayers = 2;
	private ServerSocket server;
	private HashMap<Socket, PrintWriter> outStreams = new HashMap<Socket, PrintWriter>();
	private int userCount = 0;
	private int waitingCount = 0;
	private Vector<Question> questionVector;
	private QuestionCreator questionCreator = null;

	public Server(int port) {
		System.out.println("Server Opened.");
		questionVector = new Vector<Question>();
		for (int i = 0; i < 10; i++) {
			questionCreator = new QuestionCreator();
			questionVector.add(questionCreator.getQuestion());
			clientInformationVector = new Vector<ClientInformation>();
		}
		listen(port);
	}

	private void listen(int port) {
		// TODO Auto-generated method stub
		try {
			server = new ServerSocket(port);
			while (userCount < maxPlayers) {
				Socket clientSocket = server.accept();
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream());
				outStreams.put(clientSocket, out);
				System.out.println("server acik");
				new ServerThread(this, clientSocket);
				userCount++;
				System.out.println(userCount);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void broadcastQuestion() {
		Iterator<Socket> socketIterator = outStreams.keySet().iterator();
		while (socketIterator.hasNext()) {
			Socket s = socketIterator.next();
			PrintWriter out = outStreams.get(s);
			for (int i = 0; i < 10; i++) {
				out.println(questionVector.get(i).parseForNetworking());
			}
			out.flush();
		}
	}

	public void broadcastNotification(String message) {
		Iterator<Socket> socketIterator = outStreams.keySet().iterator();
		while (socketIterator.hasNext()) {
			Socket s = socketIterator.next();
			PrintWriter out = outStreams.get(s);
			out.println(message);
			out.flush();
		}
	}

	public void broadcastStart() {
		waitingCount++;
		System.out.println(waitingCount);
		if (waitingCount == maxPlayers) {
			Iterator<Socket> socketIterator = outStreams.keySet().iterator();
			while (socketIterator.hasNext()) {
				Socket s = socketIterator.next();
				PrintWriter out = outStreams.get(s);
				out.println("START_GAME");
				out.flush();
			}
			waitingCount = 0;
		}
	}

	private int gameCount = 0;

	public void broadcastNewGame() {
		if (gameCount < 9) {
			gameCount++;
			sendTime();
			System.out.println(gameCount);
			sendClientScore();
			Iterator<Socket> socketIterator = outStreams.keySet().iterator();
			while (socketIterator.hasNext()) {
				Socket s = socketIterator.next();
				PrintWriter out = outStreams.get(s);
				out.println("START_OTHER_GAME");
				out.flush();
			}
			waitingCount = 0;
			notFoundCount = 0;
		}
		else{
			Iterator<Socket> socketIterator = outStreams.keySet().iterator();
			while (socketIterator.hasNext()) {
				Socket s = socketIterator.next();
				PrintWriter out = outStreams.get(s);
				out.println("FINISH_GAME");
				out.flush();
			}
		}
	}

	private int sentClientScore = 0;

	public void setClientInformation(String clientName) {
		clientInformationVector.add(new ClientInformation(clientName));
		System.out.println(clientName);
	}

	public void sendClientScore() {
		Iterator<Socket> socketIterator = outStreams.keySet().iterator();
		while (socketIterator.hasNext()) {
			Socket s = socketIterator.next();
			PrintWriter out = outStreams.get(s);
			out.println("Score/"
					+ clientInformationVector.get(sentClientScore)
							.getClientScore()
					+ "/"
					+ clientInformationVector.get(sentClientScore)
							.getClientName());
			sentClientScore++;
			out.flush();
		}
		sentClientScore = 0;
	}

	int notFoundCount = 0;

	public void notFound() {
		notFoundCount++;
		if (notFoundCount == maxPlayers)
			broadcastNewGame();
	}

	public void setClientScore(String clientName) {
		for (int i = 0; i < clientInformationVector.size(); i++) {
			if (clientInformationVector.get(i).getClientName()
					.equals(clientName)) {
				clientInformationVector.get(i).setClientScore();
			}
		}
	}

	private int time;
	private boolean flagTime = true;

	public void sendTime() {
		time = 40;
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				if (time > 0) {
					Iterator<Socket> socketIterator = outStreams.keySet()
							.iterator();
					while (socketIterator.hasNext()) {
						Socket s = socketIterator.next();
						PrintWriter out = outStreams.get(s);
						out.println("TIME/" + time);
						out.flush();
					}
					time--;
				} else {
					broadcastNewGame();
				}
			}
		};
		if (flagTime) {
			Timer timer = new Timer();
			timer.schedule(timerTask, 0, 1000);
			flagTime = false;
		}
	}
	
	public void calculateMaximumScores() {
		int max = 0;
		int index = 0;
		String winner;
		String looser;
		for (int i = 0; i < clientInformationVector.size(); i++) {
			if (max < clientInformationVector.get(i).getClientScore()) {
				max = clientInformationVector.get(i).getClientScore();
				index = i;
			}
		}
		System.out.println("Yenen "+index);
		winner = "WINNER/" + clientInformationVector.get(index).getClientName()
				+ "/" + clientInformationVector.get(index).getClientScore();
		Iterator<Socket> socketIterator = outStreams.keySet().iterator();
		while (socketIterator.hasNext()) {
			Socket s = socketIterator.next();
			PrintWriter out = outStreams.get(s);
			out.println(winner);
			out.flush();
		}
		Iterator<Socket> socketIterator2 = outStreams.keySet().iterator();
		while (socketIterator2.hasNext()) {
			Socket s = socketIterator2.next();
			PrintWriter out2 = outStreams.get(s);
			for (int i = 0; i < clientInformationVector.size(); i++) {
				if(i!=index){
					looser = "LOOSER/" + clientInformationVector.get(index).getClientName()
							+ "/" + clientInformationVector.get(index).getClientScore();
					System.out.println(looser);
					out2.println(looser);
					out2.flush();
				}
			}		
		}
		
	}

	public int getCountOfPlayers() {
		return userCount;
	}

	public static void main(String[] args) {
		Server server = new Server(1554);
	}
}
