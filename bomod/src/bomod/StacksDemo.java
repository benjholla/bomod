package bomod;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.stacks.Stacks;

public class StacksDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public StacksDemo() {
		init();
	}

	private void init() {
		setTitle("BOMod Stacks Demo");
		setSize(770, 490);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				StacksDemo app = new StacksDemo();
				Stacks applet = new Stacks();
				applet.init();
				app.add(applet, BorderLayout.CENTER);
				app.setVisible(true);
			}
		});
	}

}