import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyActionListener implements ActionListener{

    private ClientInterface clientInterface=null;
    private Question question=null;
    private int countUsedButton=6;
    private int countPrivateButton=0;
    private GameOperator gameOperator = null;
    private int doNotAllowChooseNumbersTwice=0;
    private double selectedNumber = 0;
	public MyActionListener(ClientInterface clientInterface,Question question,
			GameOperator gameOperator){
	    this.clientInterface = clientInterface;	
	    this.question = question;
	    this.gameOperator = gameOperator;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (doNotAllowChooseNumbersTwice < 1) {
			if (e.getActionCommand() == "btnGiven1") {
				clientInterface.btnGiven1.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnGiven1.getText());
				countUsedButton--;
			} else if (e.getActionCommand() == "btnGiven2") {
				clientInterface.btnGiven2.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnGiven2.getText());
				countUsedButton--;
			} else if (e.getActionCommand() == "btnGiven3") {
				clientInterface.btnGiven3.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnGiven3.getText());
				countUsedButton--;
			} else if (e.getActionCommand() == "btnGiven4") {
				clientInterface.btnGiven4.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnGiven4.getText());
				countUsedButton--;
			} else if (e.getActionCommand() == "btnGiven5") {
				clientInterface.btnGiven5.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnGiven5.getText());
				countUsedButton--;
			} else if (e.getActionCommand() == "btnGiven6") {
				clientInterface.btnGiven6.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnGiven6.getText());
				countUsedButton--;
			} else if (e.getActionCommand() == "btnMidResult1") {
				clientInterface.btnMidResult1.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnMidResult1.getText());
				countPrivateButton++;
			} else if (e.getActionCommand() == "btnMidResult2") {
				clientInterface.btnMidResult2.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnMidResult2.getText());
				countPrivateButton++;
			} else if (e.getActionCommand() == "btnMidResult3") {
				clientInterface.btnMidResult3.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnMidResult3.getText());
				countPrivateButton++;
			} else if (e.getActionCommand() == "btnMidResult4") {
				clientInterface.btnMidResult4.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnMidResult4.getText());
				countPrivateButton++;
			} else if (e.getActionCommand() == "btnMidResult5") {
				clientInterface.btnMidResult5.setVisible(false);
				selectedNumber = Double.parseDouble(clientInterface.btnMidResult5.getText());
				countPrivateButton++;
			}
			doNotAllowChooseNumbersTwice++;
		}
		gameOperator.doNotify(selectedNumber);
	}
	public void doNotify(){
		if(doNotAllowChooseNumbersTwice>0)
		doNotAllowChooseNumbersTwice--;
	}
	public void changeDoNotAllowChooseNumbersTwice(){
		doNotAllowChooseNumbersTwice=0;
	}
	public int getCountUsedButton(){
		return countUsedButton;
	}
	public int getCountPrivateButton(){
		return countPrivateButton;
	}
    public void setCountUsedButton(int countUsedButton) {
		this.countUsedButton = countUsedButton;
	}

	public void setCountPrivateButton(int countPrivateButton) {
		this.countPrivateButton = countPrivateButton;
	}
}
