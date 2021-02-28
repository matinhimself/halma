package Models;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class UpperPanel extends JPanel {
	
	
	public int pY;
	public int pX;
	public JLabel exitIcon;
	public JFrame parentFrame;
	
	public UpperPanel(boolean closeAppWithCloseIcon) {
		
		exitIcon = new JLabel();
		ImageIcon icon = new ImageIcon(
			((new ImageIcon("./assets/exit_icon.png")).getImage()).getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH));
		exitIcon.setIcon(icon);
		setBackground(UIManager.getColor("Button.borderColor"));
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(exitIcon);
		
		exitIcon.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (closeAppWithCloseIcon) {
					parentFrame.dispose();
				}
				else {
					System.exit(0);
				}
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// Gets and stores x,y 
				pX = me.getX();
				pY = me.getY();
			}
		});
		
		
	}




}
