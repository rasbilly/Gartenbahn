package GUI;

import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Hauptmenu extends JFrame {

	private JPanel contentPane;
	Button weiche1, weiche2, weiche3, signal;
	JLabel zug1, zug2, zug3;
	
	public Hauptmenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gartenbahn Steuerung");
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		buttons();

	}
	
	public static void main(String[] args) {
		new Hauptmenu();
	}

	
	
	public void buttons() {
		
		weiche1 = new Button("Weiche 1");
		weiche1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		weiche1.setBounds(62, 229, 70, 22);
		contentPane.add(weiche1);
		
		Button weiche2 = new Button("Weiche 2");
		weiche2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		weiche2.setBounds(62, 197, 70, 22);
		contentPane.add(weiche2);
		
		Button weiche3 = new Button("Weiche 3");
		weiche3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		weiche3.setBounds(62, 161, 70, 22);
		contentPane.add(weiche3);
		
		Button signal = new Button("Signal");
		signal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		signal.setBounds(62, 117, 70, 22);
		contentPane.add(signal);
		
		
	}
}
