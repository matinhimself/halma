package Models;

import static Core.Core.highlighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import Core.Core;
import Frames.Board;

public class Pieces extends JButton implements ActionListener{
	
	public enum Modes {
		NON, // Empty Board houses
		
		RED, // Pieces colors
		YELLOW,
		BLUE,
		GREEN,
		
		HIGHLIGHTED // Highlighted := Valid moves for selected piece
	}
	
	public int i;
	public int j;
	
	public static Pieces[][] piecesArray;
	
	
    private Modes mode;
    
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    
    private boolean isSelected;
    
    public Pieces() {
    	super("");
        this.addActionListener(this);
        super.setContentAreaFilled(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }




    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    
	@Override
	public void actionPerformed(ActionEvent e) {
		Pieces piece = (Pieces) e.getSource();
		if (piece.getMode() == Board.whooseTurn() &&
				piece.getMode() != Modes.HIGHLIGHTED) {
			deselect();
			piece.setSelected(true);
			highlighter(piece.i, piece.j, 1);
			
		}
		else if (piece.getMode() == Modes.HIGHLIGHTED && getSelected() != null) {
			piece.setMode(getSelected().getMode());
			Board.moves = Board.moves + 1 ;
			getSelected().setMode(Modes.NON);
			deselect();
			Board.updateScorePanel();
		}
		update();
	}


    private void deselect() {
        for (Pieces[] pieces : piecesArray) {
			for (Pieces piece : pieces) {
				if(piece.isSelected()) {
					piece.setSelected(false);
					piece.setBorder(null);
				}
				if (piece.getMode() == Modes.HIGHLIGHTED) {
					piece.setIcon(null);
					piece.setMode(Modes.NON);
				}
			}
		}
    }
    
    
    
	private static Pieces getSelected() {
		for (Pieces[] pieces : piecesArray) {
			for (Pieces pieces2 : pieces) {
				if (pieces2.isSelected) {
					return pieces2;
				}
			}
		}
		return null;
	}
	
	public void setbeckground(int i, int j , boolean house) {
		if (( j + i )% 2 == 0) {
			if (house){
				this.setBackground(Color.DARK_GRAY.darker());
				
			} else {
				this.setBackground(Color.DARK_GRAY);
			}
			this.setHoverBackgroundColor(Color.DARK_GRAY.brighter());

			// button.setBorder(border);

		} else {

			if (house){
				this.setBackground(Color.LIGHT_GRAY.darker());
			} else {
				this.setBackground(Color.LIGHT_GRAY);
			}

			this.setHoverBackgroundColor(Color.LIGHT_GRAY.darker());

		}
		
	}
    
	public static void update() {
		for (Pieces[] pieces : piecesArray) {
			for (Pieces piece : pieces) {
				if (piece.getMode() == Modes.BLUE) piece.setIcon(Board.iconBlue);
				if (piece.getMode() == Modes.RED) piece.setIcon(Board.iconRed);
				if (piece.getMode() == Modes.GREEN) piece.setIcon(Board.iconGreen);
				if (piece.getMode() == Modes.YELLOW) piece.setIcon(Board.iconYellow);
				if (piece.getMode() == Modes.HIGHLIGHTED) piece.setIcon(Board.iconHighlighted);
				if (piece.getMode() == Modes.NON) piece.setIcon(null);
				if (piece.isSelected()) piece.setBorder(new LineBorder(Color.white, 1, true));
			}
		}
		Core.checkWinner();
	}

}
