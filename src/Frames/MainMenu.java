package Frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Models.UpperPanel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private void destroyer() {
		setVisible(false);
	}

	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setUndecorated(true);
	
		UpperPanel upperPanel = new UpperPanel(false);
		// Action listener to move whole Frame by dragging mouse 
		upperPanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {
	
				setLocation(getLocation().x + me.getX() - upperPanel.pX, 
						getLocation().y + me.getY() - upperPanel.pY);
			}
		});
		
		getContentPane().add(upperPanel, BorderLayout.NORTH);

		MainPanel centralPanel = new MainPanel();



		getContentPane().add(centralPanel, BorderLayout.CENTER);
		

//		pack();
		setBounds(0, 0, 400, 600);
		setLocationRelativeTo(null);
	}
	
	private class MainPanel extends JPanel {
		public MainPanel() {
			setLayout(null);
			

			JButton btnPlay = new JButton("Play");
			btnPlay.setSelected(false);
			btnPlay.setForeground(UIManager.getColor("Button.background"));
			btnPlay.setBackground(SystemColor.menu);
			btnPlay.setFont(new Font("Ubuntu Light", Font.PLAIN, 34));
			
			btnPlay.setFocusPainted(false);

			btnPlay.setBorderPainted(false);
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					GameSettings gameSettings = new GameSettings();
					gameSettings.setVisible(true);
					destroyer();
				}
			});
			
			btnPlay.setIcon(null);
			btnPlay.setBounds(107, 392, 186, 57);
			add(btnPlay);
			
			JButton btnExit = new JButton("Exit");
			btnExit.setForeground(SystemColor.text);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnExit.setFont(new Font("Ubuntu Light", Font.PLAIN, 34));
			btnExit.setBackground(UIManager.getColor("Actions.Red"));
			btnExit.setBounds(107, 461, 186, 57);
			add(btnExit);


			
			
			
			ImageIcon playIcon = new ImageIcon(
					((new ImageIcon("./assets/Logo.png")).getImage()).getScaledInstance( 200 , 200 , java.awt.Image.SCALE_SMOOTH));
			JLabel lblLogo = new JLabel(playIcon);
			lblLogo.setBounds(6, 16, 376, 290);
			add(lblLogo);
			
			JLabel lblLogoType = new JLabel("Halma");
			lblLogoType.setForeground(Color.WHITE);
			lblLogoType.setFont(new Font("Ubuntu", Font.PLAIN, 57));
			lblLogoType.setBounds(107, 266, 186, 40);
			add(lblLogoType);
			
		}

	}
}
