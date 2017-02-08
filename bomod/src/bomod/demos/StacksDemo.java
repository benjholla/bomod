package bomod.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bomod.DemoApplet;
import bomod.OSUtils;
import bomod.demos.stacks.Stacks;

/**
 * Java application wrapper around Stacks Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class StacksDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public StacksDemo(boolean presentationMode) {
		init(presentationMode);
	}

	private void init(boolean presentationMode) {
		Stacks applet = new Stacks();
		
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Stacks Interactive Demo");
		
		if(OSUtils.isWindows()){
			setSize(Stacks.MAXWIDTH + (Stacks.XADD * 4), Stacks.MAXHEIGHT + (Stacks.YADD * 2));
		} else {
			setSize(Stacks.MAXWIDTH + (Stacks.XADD * 2), Stacks.MAXHEIGHT + 70);
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
				
				StacksDemo app;
				
				if(n==1){
					// presentation mode color scheme
					app = new StacksDemo(true);
				} else {
					app = new StacksDemo(false);
				}
				
				app.setVisible(true);
			}
		});
	}

}