package bomod;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.spock.Spock;

public class SpockDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public SpockDemo() {
		init();
	}

	private void init() {
		setTitle("BOMod Spock Demo");
		setSize(770, 490);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SpockDemo app = new SpockDemo();
				Spock applet = new Spock();
				applet.init();
				app.add(applet, BorderLayout.CENTER);
				app.setVisible(true);
			}
		});
	}

}
