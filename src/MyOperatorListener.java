import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyOperatorListener implements ActionListener {

	private MyActionListener myActionListener=null;
	private GameOperator gameOperator = null;
	public MyOperatorListener(GameOperator gameOperator,MyActionListener myActionListener) {
		this.gameOperator = gameOperator;
		this.myActionListener=myActionListener;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()=="+")
            gameOperator.doNotify("+");
		else if(e.getActionCommand()=="-")
			gameOperator.doNotify("-");
		else if(e.getActionCommand()=="*")
			gameOperator.doNotify("*");
		else
			gameOperator.doNotify("/");
		
		myActionListener.doNotify();
	}

}
