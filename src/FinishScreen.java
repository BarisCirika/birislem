import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class FinishScreen extends JFrame {

	private JLabel informationLabel=null;
	private JPanel panel=null;
	private Font font=null;
	
	public FinishScreen() {
		font = new Font("ARIAL",Font.PLAIN, 20);
		informationLabel = new JLabel();
		informationLabel.setFont(font);
		informationLabel.setText("!!!PLESAE WAIT!!! CALCULATING SCORES");
		panel = new JPanel();
		panel.setBorder(new TitledBorder("Finish Screen"));
		panel.setLayout(new FlowLayout());
		panel.add(informationLabel);
		add(panel);
		setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = new Dimension(100, 100);
		setResizable(false);
		setSize(d);
		pack();
	}
	
	public JLabel getInformationLabel() {
		return informationLabel;
	}

	public void setInformationLabel(String informationLabel) {
		this.informationLabel.setText(informationLabel);
	}
}
