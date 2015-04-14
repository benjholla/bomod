package bomod.demos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.OSUtils;
import bomod.demos.spock.Spock;

/**
 * Java application wrapper around Spock Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class SpockDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public SpockDemo() {
		init();
	}

	private void init() {
		Spock applet = new Spock();
		// initialize the applet with default color scheme
		applet.init();
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Spock Demo");
		
		if(OSUtils.isWindows()){
			setSize(Spock.MAXWIDTH + (Spock.XADD * 4), Spock.MAXHEIGHT + (Spock.YADD * 2));
		} else {
			setSize(Spock.MAXWIDTH + (Spock.XADD * 2), Spock.MAXHEIGHT + 70);
		}

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SpockDemo app = new SpockDemo();
				app.setVisible(true);
			}
		});
	}

}
