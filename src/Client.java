
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;


public class Client {

	private GameOperator gameOperator;
	private WaitingScreen waitingScreen;
	private String name;
	private int port = 1554;
	private int userScore;
	private Socket socket;
	private Vector<Question> questionVector;
	private Question question = null;
    private int questionCount=0;
    private FinishScreen finishScreen;
	public Client(String name) {
		questionVector = new Vector<Question>();
		this.name = name;
		
		try {
			socket = new Socket("localhost", port);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			new ClientThread(this,in);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendState("Name/"+name);
		waitingScreen = new WaitingScreen(name);
	}

	public void createGame() {		
			gameOperator = new GameOperator(questionVector.get(questionCount), this, name);	
			waitingScreen.setVisible(false);
			sendState("WAITING_TIME");
	}

	public void doAnotherQuestion() {
		if (questionCount <= 10)
			questionCount++;
		else
			System.out.println("oyun bitti");
		gameOperator.getClientInterface().sentQuestionsToButtons(
				questionVector.get(questionCount), userScore);
		gameOperator.getClientInterface().setMiddleButtonUnvisible();
		//gameOperator.doTimerTask();
		System.out.println("šbŸr soru");
		sendState("WAITING_TIME");
		gameOperator.getClientInterface().lblSession.setText("Game Session: "+(questionCount+1));
		gameOperator.arrangeCounts();

	}
	public void doNotify(boolean state) {
		if (state) {
			System.out.println("BULDUN!");
			sendState("FOUND_QUESTION/"+name);
		} else {
			sendState("NOT_FOUND_QUESTION/"+name);
			//sendState("false");
		}
		gameOperator.arrangeCounts();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuestion(String question) {
		String[] questionArray = question.split(",");
		this.question = new Question(Integer.parseInt(questionArray[0]),
				Integer.parseInt(questionArray[1]),
				Integer.parseInt(questionArray[2]),
				Integer.parseInt(questionArray[3]),
				Integer.parseInt(questionArray[4]),
				Integer.parseInt(questionArray[5]),
				Integer.parseInt(questionArray[6]));
		questionVector.add(this.question);
		if(questionVector.size()==10){
			sendState("WAITING_START");
		}
	}
	
	public void waitOtherGame(){
		sendState("WAITING_OTHER_QUESTION");
	}
	
	public void setScore(String score){
		userScore = Integer.parseInt(score);
		gameOperator.getClientInterface().lblScore.setText("Score: "+userScore);
	}
	public void setGameTime(String time){
		gameOperator.getClientInterface().lblRemainTime.setText("Remaining Time= "+time);
	}
	private boolean flagScreen=true;
	public void getFinishScreen(){
		if(flagScreen){
		finishScreen = new FinishScreen();
		gameOperator.getClientInterface().setVisible(false);
		sendState("WAITING_MAXIMUM_SCORES");
		flagScreen=false;
		}
	}
	public void displayWinner(String status,String score){
		finishScreen.setInformationLabel("YOU "+status+"!!! With "+score+" SCORE!!!");
	}
	
	public GameOperator getGameOperator() {
		return gameOperator;
	}

	public void setGameOperator(GameOperator gameOperator) {
		this.gameOperator = gameOperator;
	}
	public void sendState(String state) {
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream());
			out.println(state);
			System.out.println(state+" client state");
			out.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
