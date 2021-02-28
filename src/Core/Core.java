package Core;

import static Models.Pieces.piecesArray;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import Frames.Board;
import static Models.Pieces.Modes.*;
import Models.Pieces.Modes;



public class Core {
	
	// Highlights a;;
	public static void highlighter(int i, int j, int depth) {
		// checkForJumps(i, j);
		for (int x = -1; x < 2 ; x++) {
			for (int y = -1	; y < 2; y++) {
				jumper(i,j,x,y);
				if ( depth < 2 )
					firstStep(i,j,x,y);
			}
		}
	}

	
	// // Highlights near 8 near houses
	private static void firstStep(int i, int j, int x, int y) {
		try {
			if (piecesArray[i+x][j+y].getMode() == NON)
				piecesArray[i+x][j+y].setMode(HIGHLIGHTED);
		} catch (Exception ignore) {}
	}

	// Highlights availale jumop moves
	private static void jumper(int i, int j, int x, int y){
		try {
			Modes mode = piecesArray[i + x][j + y].getMode();

			if (mode != NON && mode != HIGHLIGHTED) {
				if (piecesArray[i + 2*x ][j + 2*y].getMode() == NON) {
					piecesArray[i + 2*x][j + 2*y].setMode(HIGHLIGHTED);
					highlighter(i + 2*x, j + 2*y, 2);
				}
			}
		} catch (Exception ignore) {}
	}






	
	public static void checkWinner() {
		int boardSize = Board.boardSize;
		int piecesSize = Board.piecesSize;

		boolean redIsWinner = true;
		boolean blueIsWinner = true;
		boolean greenIsWinner = true;
		boolean yellowIsWinner = true;

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {

				if (i + j <= piecesSize - 1 && piecesArray[i][j].getMode() != RED) {
					redIsWinner = false;
				} else if (i + j >= 2 * boardSize - piecesSize - 1 && piecesArray[i][j].getMode() != BLUE) {
					blueIsWinner = false;
				} else if (i - j >= boardSize - piecesSize && Board.is4player
						&& piecesArray[i][j].getMode() != GREEN) {
					greenIsWinner = false;

				} else if (j - i >= boardSize - piecesSize && Board.is4player
						&& piecesArray[i][j].getMode() != YELLOW) {
					yellowIsWinner = false;
				}
			}
		}

		short players = (short) (Board.is4player ? 4 : 2);
		String str = "";
		if (redIsWinner) {
			JOptionPane.showMessageDialog(null, "Red wins !\nScore : " + Board.moves / players);
			str += Board.names.get(0) +  " : " + Board.moves / players  + "\n";
		}
		if (yellowIsWinner && Board.is4player) {
			JOptionPane.showMessageDialog(null, "Yellow wins !\nScore : " + Board.moves / players);
			str += Board.names.get(2) +  " : " + Board.moves / players  + "\n";
		}
		if (blueIsWinner) {
			JOptionPane.showMessageDialog(null, "Blue wins !\nScore : " + Board.moves / players);
			str += Board.names.get(1) +  " : " + Board.moves / players  + "\n";
		}
		if (greenIsWinner && Board.is4player) {
			JOptionPane.showMessageDialog(null, "Green wins !\nScore : " + Board.moves / players);
			str += Board.names.get(3) +  " : " + Board.moves / players + "\n";
		}
		try {
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter("./data/Records.txt", true)); 
            out.write(str); 
            out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}