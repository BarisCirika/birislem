import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class ClientInterface extends JFrame {

	JLabel lblPlayerName = null;
	JLabel lblScore = null;
	JLabel lblFindIt = null;
    JLabel lblRemainTime=null;
    JLabel lblSession=null;
    
	JButton btnGiven1 = null;
	JButton btnGiven2 = null;
	JButton btnGiven3 = null;
	JButton btnGiven4 = null;
	JButton btnGiven5 = null;
	JButton btnGiven6 = null;

	JButton btnOperatorPlus = null;
	JButton btnOperatorMines = null;
	JButton btnOperatorMultiply = null;
	JButton btnOperatorDevide = null;

	JButton btnMidResult1 = null;
	JButton btnMidResult2 = null;
	JButton btnMidResult3 = null;
	JButton btnMidResult4 = null;
	JButton btnMidResult5 = null;

	JLabel lblFirst=null;
	JLabel lblOperator=null;
	JLabel lblSecond=null;
	JLabel lblEqual=null;
	JLabel lblEqual2=null;
	JLabel lblTotal=null;
	Font font=null;
	//JButton btnUndo = null;

	JPanel panelInformation=null;
	JPanel panelNorth = null;
	JPanel panelCenterNorth = null;
	JPanel panelCenterSouth=null;
	JPanel panelSouth = null;
	JPanel panelUndo=null;
	JPanel panelAll = null;
	
	private Question question;
	public ClientInterface(Question question,String name) {
        font = new Font("ARIAL",Font.PLAIN, 16);
		this.question = question;
        lblRemainTime = new JLabel();
        lblRemainTime.setText("Remaining Time= 10");
        lblRemainTime.setFont(font);
        lblSession = new JLabel();
        lblSession.setFont(font);
        lblSession.setText(" Game Session: 1");
        btnGiven1 = new JButton();
        btnGiven1.setActionCommand("btnGiven1");
        
        btnGiven2 = new JButton();        
        btnGiven2.setActionCommand("btnGiven2");
        
        btnGiven3 = new JButton();      
        btnGiven3.setActionCommand("btnGiven3");
        
        btnGiven4 = new JButton();       
        btnGiven4.setActionCommand("btnGiven4");
        
        btnGiven5 = new JButton();       
        btnGiven5.setActionCommand("btnGiven5");
        
        btnGiven6 = new JButton();        
        btnGiven6.setActionCommand("btnGiven6");
        
        lblFindIt = new JLabel();
        lblFindIt.setFont(font);
        
        btnMidResult1 = new JButton();
        btnMidResult1.setActionCommand("btnMidResult1");
        btnMidResult1.setVisible(false); 
        btnMidResult2 = new JButton();
        btnMidResult2.setActionCommand("btnMidResult2");
        btnMidResult2.setVisible(false);
        btnMidResult3 = new JButton();
        btnMidResult3.setActionCommand("btnMidResult3");
        btnMidResult3.setVisible(false);
        btnMidResult4 = new JButton();
        btnMidResult4.setActionCommand("btnMidResult4");
        btnMidResult4.setVisible(false);
        btnMidResult5 = new JButton();
        btnMidResult5.setActionCommand("btnMidResult5");
        btnMidResult5.setVisible(false);
        
        btnOperatorPlus = new JButton();
        btnOperatorPlus.setActionCommand("+");     
        btnOperatorPlus.setText("+");
        
        btnOperatorMines = new JButton();
        btnOperatorMines.setActionCommand("-");
        btnOperatorMines.setText("-");
        
        btnOperatorMultiply = new JButton();
        btnOperatorMultiply.setActionCommand("*");
        btnOperatorMultiply.setText("*");
        
        btnOperatorDevide = new JButton();
        btnOperatorDevide.setActionCommand("/");
        btnOperatorDevide.setText("/");
        
        lblFirst = new JLabel();
        lblFirst.setText("");
        lblOperator = new JLabel();
        lblOperator.setText("");
        lblSecond = new JLabel();
        lblSecond.setText("");
        lblEqual = new JLabel();
        lblEqual.setText("");
        lblEqual2 = new JLabel();

        lblEqual2.setText("?=");
        lblEqual2.setSize(10, 10);
        lblEqual2.setFont(font);
        lblTotal = new JLabel();
        lblTotal.setText("?");
        
        lblPlayerName = new JLabel();
        lblScore = new JLabel();
        lblPlayerName.setText("Player Name: "+name);
        lblPlayerName.setFont(font);
        lblScore.setText("Score= 0");
        lblScore.setFont(font);
        
        panelInformation = new JPanel();
        panelInformation.setBorder(new TitledBorder("Player - Score"));
        panelInformation.setLayout(new GridBagLayout());
        GridBagConstraints gcInformation = new GridBagConstraints();
        gcInformation.fill = GridBagConstraints.HORIZONTAL;
        gcInformation.gridx = 0;
        gcInformation.gridy = 0;
        gcInformation.insets= new Insets(5, 5, 5, 60);
        panelInformation.add(lblPlayerName,gcInformation);
        
        gcInformation.gridx = 4;
        gcInformation.gridy = 0;
        gcInformation.insets= new Insets(5, 5, 5, 60);
        panelInformation.add(lblScore,gcInformation);
        
        gcInformation.gridx = 6;
        gcInformation.gridy = 0;
        gcInformation.insets= new Insets(5, 5, 5, 60);
        panelInformation.add(lblSession,gcInformation);
        
        gcInformation.gridx = 8;
        gcInformation.gridy = 0;
        gcInformation.insets= new Insets(5, 5, 5, 60);
        panelInformation.add(lblRemainTime,gcInformation);
             
      //  btnUndo = new JButton();
      //  btnUndo.setText("Undo");
        
        panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.setBorder(new TitledBorder("Question"));
		panelNorth.add(btnGiven1);
		panelNorth.add(btnGiven2);
		panelNorth.add(btnGiven3);
		panelNorth.add(btnGiven4);
		panelNorth.add(btnGiven5);
		panelNorth.add(btnGiven6);
		panelNorth.add(lblEqual2);
		panelNorth.add(lblFindIt);
		
		panelCenterNorth = new JPanel();
		panelCenterNorth.setBorder(new TitledBorder("Mid Results"));
		panelCenterNorth.setLayout(new FlowLayout());
		panelCenterNorth.add(btnMidResult1);
		panelCenterNorth.add(btnMidResult2);
		panelCenterNorth.add(btnMidResult3);
		panelCenterNorth.add(btnMidResult4);
		panelCenterNorth.add(btnMidResult5);
		
		panelCenterSouth = new JPanel();
		panelCenterSouth.setLayout(new FlowLayout());
		panelCenterSouth.setBorder(new TitledBorder("Operators"));
		panelCenterSouth.add(btnOperatorPlus);
		panelCenterSouth.add(btnOperatorMines);
		panelCenterSouth.add(btnOperatorMultiply);
		panelCenterSouth.add(btnOperatorDevide);
		
		panelSouth = new JPanel();
		panelSouth.setBorder(new TitledBorder("Operation"));
		panelSouth.setLayout(new FlowLayout());
		panelSouth.add(lblFirst);
		panelSouth.add(lblOperator);
		panelSouth.add(lblSecond);
		panelSouth.add(lblEqual);
		panelSouth.add(lblTotal);
		
//		panelUndo = new JPanel();
//		panelUndo.setLayout(new FlowLayout());
//		panelUndo.add(btnUndo);
		
		panelAll = new JPanel();
		panelAll.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		panelAll.setBorder(new TitledBorder("Math Game"));
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets= new Insets(5, 5, 5, 60);
		panelAll.add(panelInformation,gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets= new Insets(5, 5, 5, 60);
		panelAll.add(panelNorth,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets= new Insets(5, 5, 5, 60);
		panelAll.add(panelCenterNorth,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.insets= new Insets(5, 5, 5, 60);
		panelAll.add(panelCenterSouth,gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.insets= new Insets(5, 5, 5, 60);
		panelAll.add(panelSouth,gc);
		
//		gc.gridx = 0;
//		gc.gridy = 5;
//		gc.insets= new Insets(5, 5, 5, 60);
//		//panelAll.add(panelUndo,gc);
		sentQuestionsToButtons(question,0);
		add(panelAll);
		setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = new Dimension(600, 700);	
		setResizable(false);
		setSize(d);
		pack();
	}
	public void sentQuestionsToButtons(Question question,int userScore){
        btnGiven1.setText(Integer.toString(question.getNumber1()));
        btnGiven2.setText(Integer.toString(question.getNumber2()));
        btnGiven3.setText(Integer.toString(question.getNumber3()));
        btnGiven4.setText(Integer.toString(question.getNumber4()));
        btnGiven5.setText(Integer.toString(question.getNumber5()));
        btnGiven6.setText(Integer.toString(question.getNumber6()));
        lblFindIt.setText(Integer.toString(question.getFindIT()));
        lblScore.setText("Score: "+Integer.toString(userScore));
	}
	
	public void setMiddleButtonUnvisible(){
        btnMidResult1.setVisible(false); 
        btnMidResult2.setVisible(false);
        btnMidResult3.setVisible(false);
        btnMidResult4.setVisible(false);
        btnMidResult5.setVisible(false);
        btnGiven1.setVisible(true);
        btnGiven2.setVisible(true);
        btnGiven3.setVisible(true);
        btnGiven4.setVisible(true);
        btnGiven5.setVisible(true);
        btnGiven6.setVisible(true);
	}
	
	public void setInterfaceVisible(){
		this.setVisible(true);
	}
}
