package bomod.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bomod.DemoApplet;
import bomod.OSUtils;
import bomod.demos.jumps.Jumps;

/**
 * Java application wrapper around Jumps Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class JumpsDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public JumpsDemo(boolean presentationMode) {
		init(presentationMode);
	}

	private void init(boolean presentationMode) {
		Jumps applet = new Jumps();
		
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Jumps Interactive Demo");
		
		if(OSUtils.isWindows()){
			setSize(Jumps.MAXWIDTH + (Jumps.XADD * 4), Jumps.MAXHEIGHT + (Jumps.YADD * 2));
		} else {
			setSize(Jumps.MAXWIDTH + (Jumps.XADD * 2), Jumps.MAXHEIGHT + 70);
		}
		
		if(presentationMode){
			applet.setBackground(Color.WHITE);
			// for presentation mode override the default color scheme
			applet.setParameter(DemoApplet.BACKGROUND_COLOR_PARAM, Color.WHITE);
			applet.setParameter(DemoApplet.HINT_TEXT_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.MEMORY_DEFAULT_FORGROUND_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM, Color.WHITE);
			applet.setParameter(DemoApplet.CONSOLE_BACKGROUND_COLOR_PARAM, Color.WHITE);
			applet.setParameter(DemoApplet.CONSOLE_TEXT_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.CODE_COLOR1_PARAM, Color.RED.darker());
			applet.setParameter(DemoApplet.CODE_COLOR2_PARAM, new Color(255, 0, 255).darker()); // dark purple
			applet.setParameter(DemoApplet.CODE_COLOR3_PARAM, Color.GREEN.darker());
			applet.setParameter(DemoApplet.CODE_COLOR4_PARAM, Color.ORANGE.darker());
			applet.setParameter(DemoApplet.STACK_CONTENTS_COLOR_PARAM, Color.BLACK);
			applet.setParameter(DemoApplet.RETURN_POINTER_COLOR_PARAM, Color.BLACK);
		}
		
		// initialize the applet with default color scheme
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
				
				JumpsDemo app;
				
				if(n==1){
					// presentation mode color scheme
					app = new JumpsDemo(true);
				} else {
					app = new JumpsDemo(false);
				}
				
				app.setVisible(true);
			}
		});
	}

}