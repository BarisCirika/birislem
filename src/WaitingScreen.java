import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class WaitingScreen extends JFrame{

	private JLabel lblUserName;
	private JPanel panel;
	private String name;
	public WaitingScreen(String name){
		this.name= name;
		lblUserName = new JLabel("Hello "+name+". You are waiting for other users");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(new TitledBorder("Waiting For Other Users"));
		panel.add(lblUserName);
		add(panel);
		setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = new Dimension(100, 100);
		setResizable(false);
		setSize(d);
		pack();
	}
}
