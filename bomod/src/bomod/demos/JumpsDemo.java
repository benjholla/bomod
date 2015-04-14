package bomod.demos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.demos.jumps.Jumps;

/**
 * Java application wrapper around Jumps Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class JumpsDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public JumpsDemo() {
		init();
	}

	private void init() {
		setTitle("BOMod Jumps Demo");
		setSize(770, 490);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JumpsDemo app = new JumpsDemo();
				Jumps applet = new Jumps();
				applet.init();
				app.add(applet, BorderLayout.CENTER);
				app.setVisible(true);
			}
		});
	}

}