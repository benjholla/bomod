package bomod.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

	public StackGuardDemo(boolean presentationMode) {
		init(presentationMode);
	}

	private void init(boolean presentationMode) {
		StackGuard applet = new StackGuard();
		
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Stack Guard Interactive Demo");
		
		if(OSUtils.isWindows()){
			setSize(StackGuard.MAXWIDTH + (StackGuard.XADD * 4), StackGuard.MAXHEIGHT + (StackGuard.YADD * 2));
		} else {
			setSize(StackGuard.MAXWIDTH + (StackGuard.XADD * 2), StackGuard.MAXHEIGHT + 70);
		}
		
		applet.setParameter(DemoApplet.PLAY_DELAY_PARAM, new Integer(2750));
		applet.setParameter(DemoApplet.STACK_CONTENTS_COLOR_PARAM, Color.BLACK);
		applet.setParameter(DemoApplet.RETURN_POINTER_COLOR_PARAM, Color.BLACK);
		
		if(presentationMode){
			applet.setBackground(Color.WHITE);
			applet.setParameter(DemoApplet.BACKGROUND_COLOR_PARAM, Color.WHITE);
			applet.setParameter(DemoApplet.HINT_TEXT_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.MEMORY_DEFAULT_FORGROUND_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM, Color.WHITE);
			applet.setParameter(DemoApplet.CONSOLE_BACKGROUND_COLOR_PARAM, Color.WHITE);
			applet.setParameter(DemoApplet.CONSOLE_TEXT_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.DEFAULT_CODE_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.CODE_COLOR1_PARAM, Color.GREEN.darker());
			applet.setParameter(DemoApplet.CODE_COLOR2_PARAM, Color.RED.darker());
			applet.setParameter(DemoApplet.CODE_COLOR3_PARAM, new Color(255, 0, 255).darker()); // dark purple
		} else {
			applet.setParameter(DemoApplet.BACKGROUND_COLOR_PARAM, new Color(0, 0, 128));
			applet.setParameter(DemoApplet.CODE_COLOR1_PARAM, Color.GREEN);
			applet.setParameter(DemoApplet.CODE_COLOR2_PARAM, Color.RED);
			applet.setParameter(DemoApplet.CODE_COLOR3_PARAM, new Color(255, 0, 255));
		}
		
		// initialize the applet
		applet.init();
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Object[] options = { "Classic Color Scheme", "Presentation Mode Color Scheme"};
				int n = JOptionPane.showOptionDialog(new JFrame(), "What color scheme would you like to use?",
						"Color Scheme Settings", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[1]);
				
				StackGuardDemo app;
				
				if(n==1){
					// presentation mode color scheme
					app = new StackGuardDemo(true);
				} else {
					app = new StackGuardDemo(false);
				}
				
				app.setVisible(true);
			}
		});
	}

}
