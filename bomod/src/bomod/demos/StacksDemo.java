package bomod.demos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.OSUtils;
import bomod.demos.stacks.Stacks;

/**
 * Java application wrapper around Stacks Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class StacksDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public StacksDemo() {
		init();
	}

	private void init() {
		Stacks applet = new Stacks();
		// initialize the applet with default color scheme
		applet.init();
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Stacks Demo");
		
		if(OSUtils.isWindows()){
			setSize(Stacks.MAXWIDTH + (Stacks.XADD * 4), Stacks.MAXHEIGHT + (Stacks.YADD * 2));
		} else {
			setSize(Stacks.MAXWIDTH + (Stacks.XADD * 2), Stacks.MAXHEIGHT + 70);
		}

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				StacksDemo app = new StacksDemo();
				app.setVisible(true);
			}
		});
	}

}