import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GetUserName extends JFrame {

	private JLabel lblName;
	private JTextField txtName;
	private JButton btnOK;
	private JPanel panel;
	private String name;
	public GetUserName() {
		lblName = new JLabel();
		lblName.setText("Please Enter User Name");
		txtName = new JTextField(10);
		btnOK = new JButton("OK");
		panel = new JPanel();
		panel.setBorder(new TitledBorder("Login"));
		panel.setLayout(new FlowLayout());
		panel.add(lblName);
		panel.add(txtName);
		panel.add(btnOK);
		setLayout(new GridLayout());
		add(panel);
		setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = new Dimension(100, 100);
		setResizable(false);
		setSize(d);
		pack();

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!txtName.getText().isEmpty()) {
					name = txtName.getText();
					name = name.toUpperCase();
					Client client = new Client(name);
					setVisibleFalse();
				}
			}
		});
		
	}
	public void setVisibleFalse(){
		this.setVisible(false);
	}
	public static void main(String[] args) {
		GetUserName getUserName = new GetUserName();
	}
}
