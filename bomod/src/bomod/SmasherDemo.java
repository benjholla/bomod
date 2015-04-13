package bomod;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.smasher.Smasher;

/**
 * Java application wrapper around Smasher Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class SmasherDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public SmasherDemo() {
		init();
	}

	private void init() {
		setTitle("BOMod Smasher Demo");
		setSize(770, 490);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SmasherDemo app = new SmasherDemo();
				Smasher applet = new Smasher();
				applet.init();
				app.add(applet, BorderLayout.CENTER);
				app.setVisible(true);
			}
		});
	}

}