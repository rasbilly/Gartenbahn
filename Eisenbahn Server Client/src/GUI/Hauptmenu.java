package GUI;

import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GPIO.Signal;
import GPIO.Weichen;
import Programme.ProgrammHandler;
import ServerHandler.Log;

import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

public class Hauptmenu extends JFrame {

	private JPanel contentPane;
	public static Button butWeiche1, butWeiche2, butWeiche3, butSignal, butPro1, butPro2, butPro3, butAu;
	public static JLabel zug1, zug2, zug3;
	public static Canvas canvasAbschnitt1, canvasAbschnitt2, canvasAbschnitt3, canvasAbschnitt3_1, canvasAbschnitt4,
			canvasAbschnitt4_1, canvasAbschnitt5, canvasAbschnitt6, canvasAbschnitt6_2, canvasAbschnitt6_3,
			canvasAbschnitt7;
	public static Canvas canvasWeiche1_oben, canvasWeiche1_unten, canvasWeiche2_oben, canvasWeiche2_unten,
			canvasWeiche3_oben, canvasWeiche3_unten;
	private JLabel labelProgamme, lblKonsole, lblLokschuppen;

	public static PositionZuege ps = new PositionZuege();

	public Hauptmenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gartenbahn Steuerung");
		// setExtendedState(MAXIMIZED_BOTH);
		setBounds(0, 0, 1631, 870);
		contentPane = new JPanel();
		setContentPane(contentPane);

		zug();
		abschnitte();
		buttons();
		// console();
		hintergrund();

		getContentPane().setLayout(null); // contentPane.setLayout(null);

		JLabel gleisplan = new JLabel(
				new ImageIcon(getClass().getResource("/resources/Gleisanlage_mit_Tags_voll.png")));
		gleisplan.setBounds(0, 0, 1631, 668);
		contentPane.add(gleisplan);
	}

	// TODO aktueller sinn? testen
	private void hintergrund() {
		// JLabel gleisplan = new JLabel(new
		// ImageIcon("resources/Gleisanlage_mit_Tags_voll.png"));
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO Scrollbar
	// TODO console auslesen über log mit liste???
	private void console() {
		final JTextArea area = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setBounds(1294, 350, 311, 300);
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
		lblLokschuppen.setBounds(46, 679, 179, 33);
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
		zug1.setIcon(new ImageIcon(getClass().getResource("/resources/annaRechts.gif")));
		contentPane.add(zug1);

		zug2 = new JLabel("");
		zug2.setBounds(453, 680, 220, 127);
		zug2.setIcon(new ImageIcon(getClass().getResource("/resources/zug2rechts.gif")));
		contentPane.add(zug2);

		zug3 = new JLabel("");
		zug3.setBounds(683, 680, 220, 127);
		zug3.setIcon(new ImageIcon(getClass().getResource("/resources/zug3links.gif")));
		contentPane.add(zug3);
	}

	public void abschnitte() {
		canvasWeiche1_oben = new Canvas();
		canvasWeiche1_oben.setBounds(902, 116, 13, 13);
		canvasWeiche1_oben.setBackground(Color.RED);
		canvasWeiche1_oben.setVisible(false);
		contentPane.add(canvasWeiche1_oben);

		canvasWeiche1_unten = new Canvas();
		canvasWeiche1_unten.setBounds(902, 141, 13, 13);
		canvasWeiche1_unten.setBackground(Color.RED);
		canvasWeiche1_unten.setVisible(false);
		contentPane.add(canvasWeiche1_unten);

		canvasWeiche2_oben = new Canvas();
		canvasWeiche2_oben.setBounds(477, 141, 13, 13);
		canvasWeiche2_oben.setBackground(Color.RED);
		canvasWeiche2_oben.setVisible(false);
		contentPane.add(canvasWeiche2_oben);
		canvasWeiche2_unten = new Canvas();
		canvasWeiche2_unten.setBounds(477, 116, 13, 13);
		canvasWeiche2_unten.setBackground(Color.RED);
		canvasWeiche2_unten.setVisible(false);
		contentPane.add(canvasWeiche2_unten);

		canvasWeiche3_oben = new Canvas();
		canvasWeiche3_oben.setBounds(515, 564, 13, 13);
		canvasWeiche3_oben.setBackground(Color.RED);
		canvasWeiche3_oben.setVisible(false);
		contentPane.add(canvasWeiche3_oben);
		canvasWeiche3_unten = new Canvas();
		canvasWeiche3_unten.setBounds(515, 591, 13, 13);
		canvasWeiche3_unten.setBackground(Color.RED);
		canvasWeiche3_unten.setVisible(false);
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
				try {
					Weichen.WEICHEN.schalteWeiche1('t');
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(),"! Weiche 1 konnte nicht geschaltet werden!" , e1);
				}
			}
		});
		contentPane.add(butWeiche1);

		Button butWeiche2 = new Button("Weiche 2");
		butWeiche2.setBounds(421, 186, 70, 22);
		butWeiche2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Weichen.WEICHEN.schalteWeiche2('t');
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(),"! Weiche 2 konnte nicht geschaltet werden!" , e1);
				}
			}
		});
		contentPane.add(butWeiche2);

		Button butWeiche3 = new Button("Weiche 3");
		butWeiche3.setBounds(421, 519, 70, 22);
		butWeiche3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Weichen.WEICHEN.schalteWeiche3('t');
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(),"! Weiche 3 konnte nicht geschaltet werden!" , e1);
				}
			}
		});
		contentPane.add(butWeiche3);

		butSignal = new Button("Signal");
		butSignal.setBounds(421, 64, 70, 22);
		butSignal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Signal.SIGNAL.schalteSignal('t');
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(),"Signal konnte nicht geschaltet werden!" , e1);
				}
			}
		});
		contentPane.add(butSignal);

		butPro1 = new Button("Programm 1");
		butPro1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Log.Track(getClass().getName(), "GUI Button 1 gedrückt");
				ProgrammHandler.INSTANCE.programmWaehlen(1);
			}
		});
		butPro1.setBounds(1400, 130, 100, 22);
		contentPane.add(butPro1);

		butPro2 = new Button("Programm 2");
		butPro2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log.Track(getClass().getName(), "GUI Button 2 gedrückt");
				ProgrammHandler.INSTANCE.programmWaehlen(2);
			}
		});
		butPro2.setBounds(1400, 180, 100, 22);
		contentPane.add(butPro2);

		butPro3 = new Button("Programm 3");
		butPro3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log.Track(getClass().getName(), "GUI Button 3 gedrückt");
				ProgrammHandler.INSTANCE.programmWaehlen(3);
			}
		});
		butPro3.setBounds(1400, 220, 100, 22);
		contentPane.add(butPro3);

		butAu = new Button("Programm Automatik");
		butAu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log.Track(getClass().getName(), "GUI Button Automatik gedrückt");
				ProgrammHandler.INSTANCE.programmWaehlen(0);
			}
		});
		butAu.setBounds(1400, 250, 100, 22);
		contentPane.add(butAu);
	}
}
