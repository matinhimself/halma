package Core;

import Frames.MainMenu;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Main {
	public static void main(String[] args) {
		FlatDarculaLaf.install();
		MainMenu menu = new MainMenu();

		menu.setVisible(true);
	}

}
