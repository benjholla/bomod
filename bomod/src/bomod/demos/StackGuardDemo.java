package bomod.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.DemoApplet;
import bomod.OSUtils;
import bomod.demos.stack_guard.StackGuard;

/**
 * Java application wrapper around Stack Guard Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class StackGuardDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public StackGuardDemo() {
		init();
	}

	private void init() {
		StackGuard applet = new StackGuard();
		// override the default the color scheme
		applet.setParameter(DemoApplet.PLAY_DELAY_PARAM, new Integer(2750));
		applet.setParameter(DemoApplet.BACKGROUND_COLOR_PARAM,new Color(0, 0, 128));
		applet.setParameter(DemoApplet.CODE_COLOR1_PARAM, Color.GREEN);
		applet.setParameter(DemoApplet.CODE_COLOR2_PARAM, Color.RED);
		applet.setParameter(DemoApplet.CODE_COLOR3_PARAM, new Color(255, 0, 255));
		applet.setParameter(DemoApplet.CODE_COLOR4_PARAM, new Color(0, 255, 255));
		applet.setParameter(DemoApplet.STACK_CONTENTS_COLOR_PARAM, new Color(0, 0, 0));
		applet.setParameter(DemoApplet.RETURN_POINTER_COLOR_PARAM, new Color(0, 0, 0));
		// initialize the applet
		applet.init();
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Stack Guard Demo");
		
		if(OSUtils.isWindows()){
			setSize(StackGuard.MAXWIDTH + (StackGuard.XADD * 4), StackGuard.MAXHEIGHT + (StackGuard.YADD * 2));
		} else {
			setSize(StackGuard.MAXWIDTH + (StackGuard.XADD * 2), StackGuard.MAXHEIGHT + 70);
		}

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				StackGuardDemo app = new StackGuardDemo();
				app.setVisible(true);
			}
		});
	}

}
