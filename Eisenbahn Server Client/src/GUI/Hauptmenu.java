package GUI;

import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

public class Hauptmenu extends JFrame {

	private JPanel contentPane;
	static Button butWeiche1, butWeiche2, butWeiche3, butSignal;
	JLabel zug1, zug2, zug3;
	public static Canvas canvasAbschnitt1, canvasAbschnitt2, canvasAbschnitt3, canvasAbschnitt3_1, canvasAbschnitt4,
			canvasAbschnitt4_1, canvasAbschnitt5, canvasAbschnitt6, canvasAbschnitt6_2, canvasAbschnitt6_3,
			canvasAbschnitt7;
	public static Canvas canvasWeiche1_oben, canvasWeiche1_unten, canvasWeiche2_oben, canvasWeiche2_unten,
			canvasWeiche3_oben, canvasWeiche3_unten;
	private JLabel labelProgamme,lblKonsole, lblLokschuppen;
	

	public Hauptmenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gartenbahn Steuerung");
		// setExtendedState(MAXIMIZED_BOTH);
		setBounds(0, 0, 1631, 900);
		contentPane = new JPanel();
		setContentPane(contentPane);

		zug();
		abschnitte();
		buttons();
		console();
		hintergrund();
		
		setLayout(null);    //contentPane.setLayout(null);

	}

	private void hintergrund() {
		JLabel gleisplan = new JLabel(new ImageIcon("resources/Gleisanlage_mit_Tags_voll.png"));
		gleisplan.setBounds(0, 0, 1631, 668);
		contentPane.add(gleisplan);
	}

	private void console() {
		final JTextArea area = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setBounds(1294, 350, 311, 500);
		getContentPane().add(scrollPane);
		PrintStream stream = new PrintStream(System.out) {

			@Override
			public void print(String s) {
				area.append(s + "\n");
			}
		};
		System.setOut(stream);

	}

	public void zug() {

		lblLokschuppen = new JLabel("Lokschuppen");
		lblLokschuppen.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLokschuppen.setBounds(46, 679, 153, 33);
		contentPane.add(lblLokschuppen);

		labelProgamme = new JLabel("Programme");
		labelProgamme.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelProgamme.setBounds(1372, 96, 153, 33);
		contentPane.add(labelProgamme);

		lblKonsole = new JLabel("Konsole");
		lblKonsole.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblKonsole.setBounds(1372, 306, 153, 33);
		contentPane.add(lblKonsole);

		zug1 = new JLabel("");
		zug1.setBounds(235, 670, 208, 135);
		zug1.setIcon(new ImageIcon("resources/Zug/annaRechts.gif"));
		contentPane.add(zug1);

		zug2 = new JLabel("");
		zug2.setBounds(453, 680, 220, 127);
		zug2.setIcon(new ImageIcon("resources/Zug/zug2rechts.gif"));
		contentPane.add(zug2);

		zug3 = new JLabel("");
		zug3.setBounds(683, 680, 220, 127);
		zug3.setIcon(new ImageIcon("resources/Zug/zug3links.gif"));
		contentPane.add(zug3);
	}

	public void abschnitte() {
		canvasWeiche1_oben = new Canvas();
		canvasWeiche1_oben.setBounds(902, 116, 13, 13);
		contentPane.add(canvasWeiche1_oben);

		canvasWeiche1_unten = new Canvas();
		canvasWeiche1_unten.setBounds(902, 141, 13, 13);
		contentPane.add(canvasWeiche1_unten);

		canvasWeiche2_oben = new Canvas();
		canvasWeiche2_oben.setBounds(477, 141, 13, 13);
		contentPane.add(canvasWeiche2_oben);
		canvasWeiche2_unten = new Canvas();
		canvasWeiche2_unten.setBounds(477, 116, 13, 13);
		contentPane.add(canvasWeiche2_unten);

		canvasWeiche3_oben = new Canvas();
		canvasWeiche3_oben.setBounds(515, 564, 13, 13);
		contentPane.add(canvasWeiche3_oben);
		canvasWeiche3_unten = new Canvas();
		canvasWeiche3_unten.setBounds(515, 591, 13, 13);
		contentPane.add(canvasWeiche3_unten);

		canvasAbschnitt1 = new Canvas();
		canvasAbschnitt1.setBounds(439, 105, 505, 2);
		contentPane.add(canvasAbschnitt1);

		canvasAbschnitt2 = new Canvas();
		canvasAbschnitt2.setBounds(578, 163, 240, 2);
		contentPane.add(canvasAbschnitt2);

		canvasAbschnitt3 = new Canvas();
		canvasAbschnitt3.setBounds(45, 105, 395, 2);
		contentPane.add(canvasAbschnitt3);

		canvasAbschnitt3_1 = new Canvas();
		canvasAbschnitt3_1.setBounds(45, 105, 2, 365);
		contentPane.add(canvasAbschnitt3_1);

		canvasAbschnitt4 = new Canvas();
		canvasAbschnitt4.setBounds(46, 616, 432, 2);
		contentPane.add(canvasAbschnitt4);

		canvasAbschnitt4_1 = new Canvas();
		canvasAbschnitt4_1.setBounds(45, 470, 2, 148);
		contentPane.add(canvasAbschnitt4_1);

		canvasAbschnitt5 = new Canvas();
		canvasAbschnitt5.setBounds(477, 616, 590, 2);
		contentPane.add(canvasAbschnitt5);

		canvasAbschnitt6 = new Canvas();
		canvasAbschnitt6.setBounds(1060, 616, 220, 2);
		contentPane.add(canvasAbschnitt6);

		canvasAbschnitt6_2 = new Canvas();
		canvasAbschnitt6_2.setBounds(1278, 105, 2, 513);
		contentPane.add(canvasAbschnitt6_2);

		canvasAbschnitt6_3 = new Canvas();
		canvasAbschnitt6_3.setBounds(942, 105, 338, 2);
		contentPane.add(canvasAbschnitt6_3);

		canvasAbschnitt7 = new Canvas();
		canvasAbschnitt7.setBounds(652, 552, 250, 2);
		contentPane.add(canvasAbschnitt7);
	}

	public void buttons() {

		butWeiche1 = new Button("Weiche 1");
		butWeiche1.setBounds(936, 186, 70, 22);
		butWeiche1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canvasWeiche1_oben.getBackground() == Color.RED) {
					// StatusWeichenSignal.schaltenWeiche1Gui('o');
				} else {
					// StatusWeichenSignal.schaltenWeiche1Gui('u');
				}
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
	}
}
