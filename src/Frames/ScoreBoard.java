package Frames;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.formdev.flatlaf.FlatDarculaLaf;

import Models.UpperPanel;
public class ScoreBoard extends JFrame {
	public ScoreBoard() {
		setUndecorated(true);
		FlatDarculaLaf.install();
		setBounds(0, 0, 400, 500);
		JTextPane txtView = new JTextPane();
		StyledDocument doc = txtView.getStyledDocument(); 
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		txtView.setDocument(doc);
		Font font = new Font("Ubuntu Light", Font.BOLD, 13);
		txtView.setFont(font);
		txtView.setEditable(false);
		String text = "";
		BufferedReader myfile=null;


		UpperPanel upperPanel = new UpperPanel(true);
		upperPanel.parentFrame = this;
		// Action listener to move whole Frame by dragging mouse 
		upperPanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {
	
				setLocation(getLocation().x + me.getX() - upperPanel.pX, 
						getLocation().y + me.getY() - upperPanel.pY);
			}
		});

	
		getContentPane().add(upperPanel, BorderLayout.NORTH);

		setLocationRelativeTo(null);
		
		try {
			myfile = new BufferedReader(new FileReader("./data/Records.txt"));
			String str="";
			while ((str = myfile.readLine()) != null) {
				text+=str+"\n\n";
			}
			myfile.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtView.setText(text);
				
		JScrollPane scrollPane = new JScrollPane(txtView);
		scrollPane.setBounds(0, 0, 400, 500);
		getContentPane().add(scrollPane);
		setVisible(true);
	}
	
}
