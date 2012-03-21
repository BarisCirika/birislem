import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameOperator {

	private int time;
	private ClientInterface clientInterface;
	private Client client = null;
	private Question question = null;
	private double total = 0;
	private String operator;
	private int countButton = 0;
	private int i = 0;
	private Timer timer = null;
	private TimerTask timerTask = null;
	private double[] numbers;
	private MyActionListener myActionListener = null;
	private MyOperatorListener myOperatorListener = null;

	public GameOperator(Question question, final Client client, String name) {
		if(question==null)
		 System.exit(0);
		clientInterface = new ClientInterface(question, name);
		this.question = question;
		this.client = client;
		numbers = new double[2];

		myActionListener = new MyActionListener(clientInterface, this.question,
				this);
		myOperatorListener = new MyOperatorListener(this, myActionListener);

		clientInterface.btnGiven1.addActionListener(myActionListener);
		clientInterface.btnGiven2.addActionListener(myActionListener);
		clientInterface.btnGiven3.addActionListener(myActionListener);
		clientInterface.btnGiven4.addActionListener(myActionListener);
		clientInterface.btnGiven5.addActionListener(myActionListener);
		clientInterface.btnGiven6.addActionListener(myActionListener);

		clientInterface.btnMidResult1.addActionListener(myActionListener);
		clientInterface.btnMidResult2.addActionListener(myActionListener);
		clientInterface.btnMidResult3.addActionListener(myActionListener);
		clientInterface.btnMidResult4.addActionListener(myActionListener);
		clientInterface.btnMidResult5.addActionListener(myActionListener);

		clientInterface.btnOperatorPlus.addActionListener(myOperatorListener);
		clientInterface.btnOperatorMines.addActionListener(myOperatorListener);
		clientInterface.btnOperatorMultiply
				.addActionListener(myOperatorListener);
		clientInterface.btnOperatorDevide.addActionListener(myOperatorListener);

		//doTimerTask();

	}
//	public void doTimerTask(){
//		time=40;
//		timerTask = new TimerTask() {
//
//			@Override
//			public void run() {
//				if (time > 0) {
//					clientInterface.lblRemainTime.setText("Remaining Time: "
//							+ time);
//					time--;
//				} else {
//					client.doNotify(false);
//					clientInterface.lblRemainTime.setText("Remaining Time: 0");
//					clearLabels();
//					timerTask.cancel();
//					timer.cancel();
//				}
//			}
//		};
//		timer = new Timer();
//		timer.schedule(timerTask, 0, 1000);
//	}

	private Timer Timer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void doNotify(double selectedNumber) {
		if (i == 0) {
			numbers[0] = selectedNumber;
			i++;
			clientInterface.lblFirst.setText(Double.toString(selectedNumber));
		} else if (i == 1) {
			numbers[1] = selectedNumber;
			clientInterface.lblSecond.setText(Double.toString(selectedNumber));
			if (operator == "+")
				total = numbers[0] + numbers[1];
			else if (operator == "-")
				total = numbers[0] - numbers[1];
			else if (operator == "*")
				total = numbers[0] * numbers[1];
			else if(operator == "/") {
				if (numbers[1] != 0)
					total = numbers[0] / numbers[1];
				else
					total = 0;
			}
			clientInterface.lblTotal.setText(Double.toString(total));
			this.operator = " ";
			i = 0;
			countButton++;
			myActionListener.changeDoNotAllowChooseNumbersTwice();
			showAssignPrivateButton();
		}
		if (myActionListener.getCountPrivateButton() == 4
				&& myActionListener.getCountUsedButton() == 0) {
			if (total == Integer.parseInt(clientInterface.lblFindIt.getText())) {
				this.client.doNotify(true);
				clearLabels();
			}
		}
	}

	public void doNotify(String operator) {
		this.operator = operator;
		clientInterface.lblOperator.setText(operator);
	}

	public ClientInterface getClientInterface() {
		return clientInterface;
	}

	private void showAssignPrivateButton() {
		if (countButton == 1) {
			clientInterface.btnMidResult1.setVisible(true);
			clientInterface.btnMidResult1.setText(Double.toString(total));
		} else if (countButton == 2) {
			clientInterface.btnMidResult2.setVisible(true);
			clientInterface.btnMidResult2.setText(Double.toString(total));
		} else if (countButton == 3) {
			clientInterface.btnMidResult3.setVisible(true);
			clientInterface.btnMidResult3.setText(Double.toString(total));
		} else if (countButton == 4) {
			clientInterface.btnMidResult4.setVisible(true);
			clientInterface.btnMidResult4.setText(Double.toString(total));
		} else if (countButton == 5) {
			clientInterface.btnMidResult5.setVisible(true);
			clientInterface.btnMidResult5.setText(Double.toString(total));
		}
		clearLabels();
	}

	public int getCountButton() {
		return countButton;
	}
	public void setCountButton(int countButton) {
		this.countButton = countButton;
	}
	private void clearLabels() {
		clientInterface.lblFirst.setText("");
		clientInterface.lblOperator.setText("");
		clientInterface.lblSecond.setText("");
		clientInterface.lblTotal.setText("");
	}
	public void arrangeCounts(){
		myActionListener.changeDoNotAllowChooseNumbersTwice();		
		myActionListener.setCountPrivateButton(0);
		myActionListener.setCountUsedButton(6);
		countButton=0;
		total=0;
		i=0;
		clearLabels();
	}
}
