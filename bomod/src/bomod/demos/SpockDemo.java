package bomod.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bomod.DemoApplet;
import bomod.OSUtils;
import bomod.demos.spock.Spock;

/**
 * Java application wrapper around Spock Applet demo
 * 
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class SpockDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public SpockDemo(boolean presentationMode) {
		init(presentationMode);
	}

	private void init(boolean presentationMode) {
		Spock applet = new Spock();
		
		// add applet to the application window
		add(applet, BorderLayout.CENTER);
		setTitle("BOMod Variable Attack Interactive Demo");
		
		if(OSUtils.isWindows()){
			setSize(Spock.MAXWIDTH + (Spock.XADD * 4), Spock.MAXHEIGHT + (Spock.YADD * 2));
		} else {
			setSize(Spock.MAXWIDTH + (Spock.XADD * 2), Spock.MAXHEIGHT + 70);
		}
		
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
			applet.setParameter(DemoApplet.CODE_COLOR2_PARAM, new Color(255, 0, 255).darker()); // dark purple
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
				
				SpockDemo app;
				
				if(n==1){
					// presentation mode color scheme
					app = new SpockDemo(true);
				} else {
					app = new SpockDemo(false);
				}
				
				app.setVisible(true);
			}
		});
	}

}
