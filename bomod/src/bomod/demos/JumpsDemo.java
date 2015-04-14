package bomod.demos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.OSUtils;
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
		Jumps applet = new Jumps();
		// initialize the applet with default color scheme
		applet.init();
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Jumps Demo");
		
		if(OSUtils.isWindows()){
			setSize(Jumps.MAXWIDTH + (Jumps.XADD * 4), Jumps.MAXHEIGHT + (Jumps.YADD * 2));
		} else {
			setSize(Jumps.MAXWIDTH + (Jumps.XADD * 2), Jumps.MAXHEIGHT + 70);
		}

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JumpsDemo app = new JumpsDemo();
				app.setVisible(true);
			}
		});
	}

}