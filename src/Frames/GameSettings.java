package Frames;

import javax.swing.JFrame;
import com.formdev.flatlaf.FlatDarculaLaf;

import Models.UpperPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GameSettings extends JFrame{
	JRadioButton rdb4player;
	JRadioButton rdb2player;
	boolean twoPlayer;
	private JTextField txtPlayer_1;
	private JTextField txtPlayer_3;
	private JTextField txtPlayer_2;
	private JTextField txtPlayer_4;
	private JLabel lblPlayer_3;
	private JLabel lblPlayer_4;
	private JSpinner spnBoardSize;
	private JSpinner spnPiecesCount;
	
	@SuppressWarnings("deprecation")
	public GameSettings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		
		UpperPanel upperPanel = new UpperPanel(false);
		upperPanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {
	
				setLocation(getLocation().x + me.getX() - upperPanel.pX, 
						getLocation().y + me.getY() - upperPanel.pY);
			}
		});
		getContentPane().add(upperPanel, BorderLayout.NORTH);
		
		JPanel centredPanel = new JPanel();
		getContentPane().add(centredPanel, BorderLayout.CENTER);
		centredPanel.setLayout(null);
		
		rdb2player = new JRadioButton("2 Player");
		rdb2player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdb4player.setSelected(false);
				rdb2player.setSelected(true);
				twoPlayer = true;
				txtPlayer_3.setEnabled(false);
				txtPlayer_4.setEnabled(false);
				lblPlayer_3.setForeground(Color.GRAY);
				lblPlayer_4.setForeground(Color.GRAY);
			}
		});
		rdb2player.setSelected(true);
		rdb2player.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		rdb2player.setBounds(80, 16, 82, 38);
		centredPanel.add(rdb2player);
		
		rdb4player = new JRadioButton("4 Player");
		rdb4player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdb2player.setSelected(false);
				rdb4player.setSelected(true);
				twoPlayer = false;
				txtPlayer_3.setEnabled(true);
				txtPlayer_4.setEnabled(true);
				lblPlayer_3.setForeground(Color.LIGHT_GRAY);
				lblPlayer_4.setForeground(Color.LIGHT_GRAY);
				
			}
		});
		rdb4player.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		rdb4player.setBounds(171, 16, 100, 38);
		centredPanel.add(rdb4player);
		

		JLabel lblPlayerNames = new JLabel("Names :");
		lblPlayerNames.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblPlayerNames.setBounds(20, 61, 82, 16);
		centredPanel.add(lblPlayerNames);
		
		JLabel lblPlayers = new JLabel("Players :");
		lblPlayers.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblPlayers.setBounds(20, 6, 82, 16);
		centredPanel.add(lblPlayers);
		
		JLabel lblPlayer_1 = new JLabel("Player 1 :");
		lblPlayer_1.setBounds(50, 107, 52, 16);
		centredPanel.add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 2 :");
		lblPlayer_2.setBounds(231, 107, 52, 16);
		centredPanel.add(lblPlayer_2);
		
		lblPlayer_3 = new JLabel("Player 3 :");
		lblPlayer_3.setForeground(Color.GRAY);
		lblPlayer_3.setBounds(50, 149, 52, 16);
		centredPanel.add(lblPlayer_3);
		
		lblPlayer_4 = new JLabel("Player 4 :");
		lblPlayer_4.setForeground(Color.GRAY);
		lblPlayer_4.setBounds(231, 149, 52, 16);
		centredPanel.add(lblPlayer_4);
		FlatDarculaLaf.install();
		
		
		txtPlayer_1 = new JTextField();
		txtPlayer_1.setBounds(114, 102, 100, 26);
		centredPanel.add(txtPlayer_1);
		txtPlayer_1.setColumns(10);
		
		txtPlayer_3 = new JTextField();
		txtPlayer_3.setEnabled(false);
		txtPlayer_3.setColumns(10);
		txtPlayer_3.setBounds(114, 144, 100, 26);
		centredPanel.add(txtPlayer_3);
		
		txtPlayer_2 = new JTextField();
		txtPlayer_2.setColumns(10);
		txtPlayer_2.setBounds(288, 102, 100, 26);
		centredPanel.add(txtPlayer_2);
		
		txtPlayer_4 = new JTextField();
		txtPlayer_4.setEnabled(false);
		txtPlayer_4.setColumns(10);
		txtPlayer_4.setBounds(288, 144, 100, 26);
		centredPanel.add(txtPlayer_4);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<String> names = new ArrayList<String>();
					
					String name_1 = ( (!txtPlayer_1.getText().equals(""))  ? txtPlayer_1.getText() : "Player 1" );
					String name_2 = ( (!txtPlayer_2.getText().equals("")) ? txtPlayer_2.getText() : "Player 2" );
					
					names.add(name_1);
					names.add(name_2);
					
					
					
					if(rdb4player.isSelected()) {
						String name_3 =( (!txtPlayer_3.getText().equals("")) ? txtPlayer_3.getText() : "Player 3" );
						String name_4 =( (!txtPlayer_4.getText().equals("")) ? txtPlayer_4.getText() : "Player 4" );

						names.add(name_3);
						names.add(name_4);
						

						
					}
					
					int boardSize = (Integer) spnBoardSize.getValue();
					int piecesSize = (Integer) spnPiecesCount.getValue();

					if(piecesSize<boardSize){
						new Board(rdb4player.isSelected() , names , boardSize , piecesSize);
					} else {
						JOptionPane.showMessageDialog(null, "Pieces count should be less than the board size");
					}


					
				} catch (Exception wrongInput) {
					JOptionPane.showMessageDialog(null, wrongInput);
					wrongInput.printStackTrace();
				}
			}
		});
		btnStart.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		btnStart.setBounds(134, 284, 172, 38);
		centredPanel.add(btnStart);
		
		JLabel lblBoardSize = new JLabel("Board size :");
		lblBoardSize.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblBoardSize.setBounds(20, 202, 82, 16);
		centredPanel.add(lblBoardSize);
		
		JLabel lblPiecesCount = new JLabel("Pieces count :");
		lblPiecesCount.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblPiecesCount.setBounds(243, 202, 118, 16);
		centredPanel.add(lblPiecesCount);
		
		spnBoardSize = new JSpinner();
		spnBoardSize.setModel(new SpinnerNumberModel(12, 8, 20, 2));
		spnBoardSize.setBounds(114, 230, 68, 26);
		centredPanel.add(spnBoardSize);
		

		spnPiecesCount = new JSpinner();
		spnPiecesCount.setModel(new SpinnerNumberModel(new Integer(1), (int) 1, null, new Integer(1)));
		spnPiecesCount.setBounds(350, 230, 68, 26);
		centredPanel.add(spnPiecesCount);
		

		setBounds(0, 0, 440, 360);
		setLocationRelativeTo(null);
	}
}
