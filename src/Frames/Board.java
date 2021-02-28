package Frames;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarculaLaf;

import Models.Pieces;
import Models.UpperPanel;

import Models.Pieces.Modes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Board extends JFrame{
	
	short iconSize;
	
	private int pX;
	private int pY;

	// Board myboard = new Board();

	private static JLabel turnName;
	private static JLabel turnIcon;

	public static int boardSize;
	public static int piecesSize;

	private JPanel board;

	private static JLabel lblMoves;

	public static int moves = 0;
	public static boolean is4player;

	public static ImageIcon iconBlue;
	public static ImageIcon iconYellow;
	public static ImageIcon iconGreen;
	public static ImageIcon iconRed;
	public static ImageIcon iconHighlighted;

	public static ArrayList<String> names;

	private static Dimension SCREEN_DIMENSION = (Dimension) Toolkit.getDefaultToolkit().getScreenSize();
	private static Dimension FRAME_DIMENSION = new Dimension((int) SCREEN_DIMENSION.getHeight() - 100,
			(int) SCREEN_DIMENSION.getHeight() - 100);

	public static Modes whooseTurn() {
		short players = (short) (Board.is4player ? 4 : 2);
		short turn = (short) (Board.moves % players);

		if (turn == 0)
			return Modes.BLUE;
		if (turn == 1)
			return Modes.RED;
		if (turn == 2)
			return Modes.GREEN;
		else
			return Modes.YELLOW;
	}

	public Board(boolean is4player, ArrayList<String> names, int boardSize, int piecesSize) {
		this.boardSize = boardSize;
		this.piecesSize = piecesSize;

		this.is4player = is4player;
		this.names = names;

		

		short pieceIconSize = (short) (FRAME_DIMENSION.getHeight() / boardSize * 3 / 4);
		iconBlue = new ImageIcon(((new ImageIcon(".\\assets\\blue_piece.png")).getImage())
				.getScaledInstance(pieceIconSize, pieceIconSize, java.awt.Image.SCALE_SMOOTH));

		iconYellow = new ImageIcon(((new ImageIcon("./assets/yellow_piece.png")).getImage())
				.getScaledInstance(pieceIconSize, pieceIconSize, java.awt.Image.SCALE_SMOOTH));

		iconGreen = new ImageIcon(((new ImageIcon("./assets/green_piece.png")).getImage())
				.getScaledInstance(pieceIconSize, pieceIconSize, java.awt.Image.SCALE_SMOOTH));

		iconRed = new ImageIcon(((new ImageIcon("./assets/red_piece.png")).getImage()).getScaledInstance(pieceIconSize,
				pieceIconSize, java.awt.Image.SCALE_SMOOTH));

		iconHighlighted = new ImageIcon(((new ImageIcon("./assets/highlighted.png")).getImage())
				.getScaledInstance(pieceIconSize / 3, pieceIconSize / 3, java.awt.Image.SCALE_SMOOTH));

		getContentPane().setLayout(new BorderLayout());
		FlatDarculaLaf.install();

		UpperPanel upperPanel = new UpperPanel(false);
		upperPanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {

				setLocation(getLocation().x + me.getX() - upperPanel.pX, getLocation().y + me.getY() - upperPanel.pY);
			}
		});
		getContentPane().add(upperPanel, BorderLayout.PAGE_START);

		JPanel mainContainer = new JPanel();
		getContentPane().add(mainContainer, BorderLayout.CENTER);
		mainContainer.setLayout(null);

		board = new JPanel();
		board.setBounds(200, 0, (int) FRAME_DIMENSION.getHeight(), (int) FRAME_DIMENSION.getHeight());
		board.setLayout(new GridLayout(boardSize, boardSize, 0, 0));
		mainContainer.add(board);

		Pieces.piecesArray = new Pieces[boardSize][boardSize];

		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(null);
		scorePanel.setBounds(0, 0, 200, (int) FRAME_DIMENSION.getHeight());
		;

		turnIcon = new JLabel(iconBlue);
		turnIcon.setBounds(100 - 50, 150, 100, 100);

		turnName = new JLabel(names.get(0) + "'s turn");
		turnName.setHorizontalAlignment(SwingConstants.CENTER);
		turnName.setVerticalAlignment(SwingConstants.CENTER);

		turnName.setFont(new Font("Ubuntu Light", Font.CENTER_BASELINE, 20));
		turnName.setBounds(0, 210, 200, 100);
		scorePanel.add(turnName);

		scorePanel.add(turnIcon);
		resign();

		JButton btnResign = new JButton("Resign Board");
		btnResign.setFont(new Font("Ubuntu Light", Font.CENTER_BASELINE, 17));
		btnResign.setBounds(25, 300, 150, 50);
		btnResign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moves = 0;
				board.removeAll();
				;
				resign();
				Pieces.update();
				updateScorePanel();
			}
		});
		scorePanel.add(btnResign);

		


		lblMoves = new JLabel("Moves : 0");
		lblMoves.setBounds(0, 340, 200, 50);
		lblMoves.setFont(new Font("Ubuntu Light", Font.CENTER_BASELINE, 15));
		lblMoves.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(lblMoves);

		JButton btnScoreBoard = new JButton("Score Board");
		btnScoreBoard.setBounds(50, 400, 100, 30);
		btnScoreBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreBoard scoreBoard = new ScoreBoard();
				scoreBoard.setVisible(true);

			}
		});
		scorePanel.add(btnScoreBoard);


		JButton btnMainMenu = new JButton("Settings");
		btnMainMenu.setBounds(50, 435, 100, 30);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moves = 0;
				setVisible(false);
				dispose();
				GameSettings gameSettings = new GameSettings();
				gameSettings.setVisible(true);
			}
		});
		scorePanel.add(btnMainMenu);


		

		mainContainer.add(scorePanel);
		Pieces.update();

		setUndecorated(true);
		setBounds(0, 0, (int) FRAME_DIMENSION.getWidth() + 200, (int) FRAME_DIMENSION.getHeight() + 25);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void updateScorePanel() {
		short turn = (short) ( moves % (is4player?4:2) );
		lblMoves.setText("Moves : " + moves);
		Board.turnName.setText(names.get(turn) + "'s turn");
		if (turn==0) turnIcon.setIcon(iconBlue);
		if (turn==1) turnIcon.setIcon(iconRed);
		if (turn==2) turnIcon.setIcon(iconGreen);
		if (turn==3) turnIcon.setIcon(iconYellow);


		

	}

	public void resign() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				
				Pieces piece = new Pieces();
				piece.i = i;
				piece.j = j;
				
				boolean isHouse = true;
				
				if ( i + j <= piecesSize - 1) {
					piece.setMode(Modes.BLUE);

				} else if (i + j >= 2 * boardSize - piecesSize  - 1) {
					piece.setMode(Modes.RED);

				} else if (i - j >= boardSize - piecesSize && is4player) {
					piece.setMode(Modes.YELLOW);
					
				} else if (j - i >= boardSize - piecesSize && is4player) {
					piece.setMode(Modes.GREEN);
				}
				else {
					piece.setMode(Modes.NON);
					isHouse = false;
				}
				piece.setSelected(false);
				Pieces.piecesArray[i][j] = piece;
				board.add(piece);
				piece.setbeckground( i , j , isHouse);


				
				
			}
		}
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public int getPiecesSize() {
		return piecesSize;
	}

	public void setPiecesSize(int piecesSize) {
		this.piecesSize = piecesSize;
	}


	
}
