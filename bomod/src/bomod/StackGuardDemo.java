package bomod;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bomod.stack_guard.StackGuard;

public class StackGuardDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public StackGuardDemo() {
		init();
	}

	private void init() {
		setTitle("BOMod Stack Guard Demo");
		setSize(770, 490);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				StackGuardDemo app = new StackGuardDemo();
				StackGuard applet = new StackGuard();
				applet.init();
				app.add(applet, BorderLayout.CENTER);
				app.setVisible(true);
			}
		});
	}

}
