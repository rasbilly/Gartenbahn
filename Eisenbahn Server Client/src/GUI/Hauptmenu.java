package GUI;

import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.awt.Color;
import java.awt.Canvas;

public class Hauptmenu extends JFrame {

	private JPanel contentPane;
	Button butWeiche1, weiche2, weiche3, signal;
	JLabel zug1, zug2, zug3;
	public Canvas canvasAbschnitt1, canvasAbschnitt2, canvasAbschnitt3, canvasAbschnitt4, canvasAbschnitt5,
			canvasAbschnitt6, canvasAbschnitt7;

	public Hauptmenu() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gartenbahn Steuerung");
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(0, 0, 1631, 700);
		contentPane = new JPanel();
		setContentPane(contentPane);

		buttons();
		System.out.println("Hallo");
		contentPane.setLayout(null);

		setVisible(true);
	}

	public void buttons() {

		butWeiche1 = new Button("Weiche 1");
		butWeiche1.setBounds(936, 186, 70, 22);
		butWeiche1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(butWeiche1);

		Button butWeiche2 = new Button("Weiche 2");
		butWeiche2.setBounds(421, 186, 70, 22);
		butWeiche2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(butWeiche2);

		Button butWeiche3 = new Button("Weiche 3");
		butWeiche3.setBounds(421, 519, 70, 22);
		butWeiche3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(butWeiche3);

		Button butSignal = new Button("Signal");
		butSignal.setBounds(421, 64, 70, 22);
		butSignal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (butSignal.getBackground() == Color.green) {
					butSignal.setBackground(Color.red);
				} else {
					butSignal.setBackground(Color.green);
				}

				// if(GPIO.Signal.SIGNAL.getStatusSignal()=='g') {
				// butSignal.setBackground(Color.RED);
				// try {
				// GPIO.Signal.SIGNAL.schalteSignal('s');
				// } catch (InterruptedException e1) {
				// e1.printStackTrace();
				// }
				// }else if(GPIO.Signal.SIGNAL.getStatusSignal()=='s'){
				// butSignal.setBackground(Color.GREEN);
				// try {
				// GPIO.Signal.SIGNAL.schalteSignal('g');
				// } catch (InterruptedException e1) {
				// e1.printStackTrace();
				// }
			}
		});
		contentPane.add(butSignal);

		JLabel lblLokschuppen = new JLabel("Lokschuppen");
		lblLokschuppen.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLokschuppen.setBounds(1362, 99, 153, 33);
		contentPane.add(lblLokschuppen);
		
				zug2 = new JLabel("");
				zug2.setBounds(1331, 151, 183, 100);
				zug2.setIcon(new ImageIcon("resources/lok.gif"));
				contentPane.add(zug2);
		
				zug1 = new JLabel("");
				zug1.setBounds(1331, 351, 183, 100);
				zug1.setIcon(new ImageIcon("resources/lok.gif"));
				contentPane.add(zug1);

		canvasAbschnitt1 = new Canvas();
		canvasAbschnitt1.setBounds(439, 105, 505, 2);
		contentPane.add(canvasAbschnitt1);

		canvasAbschnitt2 = new Canvas();
		canvasAbschnitt2.setBounds(578, 163, 240, 2);
		contentPane.add(canvasAbschnitt2);

		canvasAbschnitt6 = new Canvas();
		canvasAbschnitt6.setBounds(1060, 616, 220, 2);
		contentPane.add(canvasAbschnitt6);

		canvasAbschnitt7 = new Canvas();
		canvasAbschnitt7.setBounds(652, 552, 250, 2);
		contentPane.add(canvasAbschnitt7);

		canvasAbschnitt4 = new Canvas();
		canvasAbschnitt4.setBounds(46, 616, 432, 2);
		contentPane.add(canvasAbschnitt4);

		canvasAbschnitt5 = new Canvas();
		canvasAbschnitt5.setBounds(477, 616, 590, 2);
		contentPane.add(canvasAbschnitt5);

		JLabel gleisplan = new JLabel(new ImageIcon(
				"C:\\Users\\tobia\\git\\Gartenbahn\\Eisenbahn Server Client\\resources\\Gleisanlage_mit_Tags_voll.png"));
		gleisplan.setBounds(0, 0, 1631, 668);
		contentPane.add(gleisplan);

	}
}
