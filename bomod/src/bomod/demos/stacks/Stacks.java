package bomod.demos.stacks;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import bomod.DemoApplet;

/**
 * Main source file for the Stacks applet
 * 
 * Original source by Jedidiah Crandall Java 1.7 compatibility modifications and
 * style changes by Ben Holland
 * 
 * @author Jedidiah Crandall <crandaj@erau.edu>
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class Stacks extends DemoApplet {

	private static final long serialVersionUID = 1L;

	protected Button bPlay, bStop, bStepForward, bReset;
	protected Checkbox cbStep;
	protected TextField typingArea;
	protected static final String PLAY = "play";
	protected static final String STOP = "stop";
	protected static final String STEPFORWARD = "stepforward";
	protected static final String STEPBACK = "stepback";
	protected static final String RESET = "reset";

	private StacksMachineContext m;
	private int PlayDelay;

	public Stacks(){
		super();
	}
	
	public void init() {
		super.init();

		m = new StacksMachineContext();
		makeContentPane();
		bStop.setEnabled(false);
		bPlay.setEnabled(true);
		bReset.setEnabled(false);

		// load settings
		PlayDelay = (Integer) parameters.get(PLAY_DELAY_PARAM);
		m.BackgroundColor = (Color) parameters.get(BACKGROUND_COLOR_PARAM);
		m.HintTextColor = (Color) parameters.get(HINT_TEXT_COLOR_PARAM);
		m.MemoryDefaultForegroundColor = (Color) parameters.get(MEMORY_DEFAULT_FORGROUND_COLOR_PARAM);
		m.MemoryDefaultBackgroundColor = (Color) parameters.get(MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM);
		m.DefaultCodeColor = (Color) parameters.get(DEFAULT_CODE_COLOR_PARAM);
		m.ConsoleBackgroundColor = (Color) parameters.get(CONSOLE_BACKGROUND_COLOR_PARAM);
		m.ConsoleTextColor = (Color) parameters.get(CONSOLE_TEXT_COLOR_PARAM);
		m.CodeColor1 = (Color) parameters.get(CODE_COLOR1_PARAM);
		m.CodeColor2 = (Color) parameters.get(CODE_COLOR2_PARAM);
		m.CodeColor3 = (Color) parameters.get(CODE_COLOR3_PARAM);
		m.CodeColor4 = (Color) parameters.get(CODE_COLOR4_PARAM);
		m.StackContentsColor = (Color) parameters.get(STACK_CONTENTS_COLOR_PARAM);
		m.ReturnPointerColor = (Color) parameters.get(RETURN_POINTER_COLOR_PARAM);

		m.Reset();
	}

	void makeContentPane() {
		cbStep = new Checkbox("Program Counter Delay");
		cbStep.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		cbStep.setState(true);

		bPlay = new Button("Play");
		bPlay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		bPlay.setActionCommand(PLAY);

		bStop = new Button("Stop");
		bStop.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		bStop.setActionCommand(STOP);

		bStepForward = new Button("Step Forward");
		bStepForward.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		bStepForward.setActionCommand(STEPFORWARD);

		bReset = new Button("Reset");
		bReset.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		bReset.setActionCommand(RESET);

		bPlay.addActionListener(this);
		bStop.addActionListener(this);
		bStepForward.addActionListener(this);
		bReset.addActionListener(this);

		typingArea = new TextField(15);
		typingArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		typingArea.addKeyListener(this);
		typingArea.setEnabled(false);
		
		Label label = new Label("Input:");
		label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

		add(cbStep);
		add(bPlay);
		add(bStop);
		add(bStepForward);
		add(bReset);
		add(label);
		add(typingArea);
	}

	public static final int MAXWIDTH = 750;
	public static final int MAXHEIGHT = 420;
	public static final int XADD = 10;
	public static final int YADD = 40;
	public static final int XCODEADD = XADD + 20;
	public static final int YCODEADD = YADD + 20;
	public static final int CODESPACING = 16;
	public static final int HIGHLIGHTWIDTH = 360;
	public static final int XMEMADD = XADD + 460;
	public static final int YMEMADD = YADD + 124;
	public static final int MEMSPACING = 16;
	public static final int XOUTPUTADD = XMEMADD;
	public static final int YOUTPUTADD = YCODEADD - 8;
	public static final int OUTPUTWIDTH = 256;
	public static final int OUTPUTHEIGHT = 16 * 5;
	public static final int OUTPUTSPACING = 16;

	boolean bMemoryOnly = false;

	public void paint(Graphics g) {
		if (!bMemoryOnly) {
			super.paint(g);
			g.setColor(m.BackgroundColor);
			g.fillRect(XADD, YADD, MAXWIDTH, MAXHEIGHT);
		} else {
			bMemoryOnly = false;
		}
		update(g);
	}

	public void update(Graphics g) {
		Font f2 = new Font("Arial", Font.BOLD, 12);
		g.setFont(f2);

		g.setColor(m.BackgroundColor);
		g.fillRect(XADD + 10, YADD + MAXHEIGHT - 40, MAXWIDTH - 10, 30);
		g.setColor(m.HintTextColor);
		g.drawString(m.sExplanation, XADD + 10, YADD + MAXHEIGHT - 20);

		Font f = new Font("Monospaced", Font.BOLD, 16);
		g.setFont(f);

		int Loop;
		for (Loop = 0; Loop < m.NumCodeLines; Loop++) {
			if (Loop == m.HighlightedLine) {
				g.setColor(m.Code[Loop].FGColor);
				g.fillRect(XCODEADD, YCODEADD + Loop * CODESPACING - (CODESPACING * 3 / 4), HIGHLIGHTWIDTH, CODESPACING);
				g.setColor(m.BackgroundColor);
			} else {
				g.setColor(m.BackgroundColor);
				g.fillRect(XCODEADD, YCODEADD + Loop * CODESPACING - (CODESPACING * 3 / 4), HIGHLIGHTWIDTH, CODESPACING);
				g.setColor(m.Code[Loop].FGColor);
			}
			g.drawString(m.Code[Loop].s, XCODEADD, YCODEADD + Loop * CODESPACING);
		}

		int XLoop, YLoop;
		String sHex;

		g.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		for (XLoop = 0; XLoop < 17; XLoop++) {
			g.setColor(Color.gray);
			g.drawLine(XMEMADD + XLoop * MEMSPACING, YMEMADD, XMEMADD + XLoop * MEMSPACING, YMEMADD + 256);
		}
		for (YLoop = 0; YLoop < 17; YLoop++) {
			g.setColor(Color.gray);
			g.drawLine(XMEMADD, YMEMADD + YLoop * MEMSPACING, XMEMADD + 256, YMEMADD + YLoop * MEMSPACING);
		}
		for (XLoop = 0; XLoop < 16; XLoop++) {
			sHex = Integer.toHexString(XLoop);
			sHex = sHex.toUpperCase();
			g.setColor(Color.gray);
			g.drawString(sHex, XMEMADD + XLoop * MEMSPACING + (MEMSPACING / 4), YMEMADD - (MEMSPACING / 4));
		}
		for (YLoop = 0; YLoop < 16; YLoop++) {
			sHex = Integer.toHexString(YLoop);
			sHex = sHex.toUpperCase();
			g.setColor(Color.gray);
			g.drawString(sHex, XMEMADD - (MEMSPACING * 3 / 4), YMEMADD + YLoop * MEMSPACING + (MEMSPACING * 3 / 4 + 1));
		}

		for (XLoop = 0; XLoop < 16; XLoop++)
			for (YLoop = 0; YLoop < 16; YLoop++) {
				g.setColor(m.Memory[YLoop * 16 + XLoop].BGColor);
				g.fillRect(XMEMADD + 1 + XLoop * MEMSPACING, YMEMADD + 1 + YLoop * MEMSPACING, 15, 15);
				g.setColor(m.Memory[YLoop * 16 + XLoop].FGColor);
				g.drawString(m.Memory[YLoop * 16 + XLoop].Contents, XMEMADD + XLoop * MEMSPACING + (MEMSPACING / 4), YMEMADD + YLoop * MEMSPACING + (MEMSPACING * 3 / 4 + 1));
			}

		g.setColor(m.ConsoleBackgroundColor);
		g.fillRect(XOUTPUTADD, YOUTPUTADD, OUTPUTWIDTH, OUTPUTHEIGHT);

		g.setColor(m.ConsoleTextColor);
		for (Loop = 0; Loop < 5; Loop++) {
			g.drawString(m.Output[Loop], XOUTPUTADD + 2, YOUTPUTADD + Loop * OUTPUTSPACING + (OUTPUTSPACING * 3 / 4));
		}

	}

	public class JedsTimer {
		private class Runner implements Runnable {
			public void run() {
				// while(false); // while false? ~BH
			}
		}

		public void DelayABit() {
			Runner r = new Runner();
			Thread t = new Thread(r);
			t.run();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}

	int UserInputIndex;
	boolean bWasPlaying = false;

	public void keyTyped(KeyEvent e) {
		char c = Character.toUpperCase(e.getKeyChar());
		e.consume(); // prevent the character from actually being typed, we will set it ourselves
		if ((c == '\n') && (typingArea.isEnabled())) {
			if (bWasPlaying) {
				bWasPlaying = false;
				bPlay.setEnabled(false);
				bStop.setEnabled(true);
				bReset.setEnabled(false);
				bStepForward.setEnabled(false);
				bPlaying = true;
				MyPlayer.Play();
			} else if (!bStepping){
				StepOnce();
			}
		} else if (((c >= 'A') && (c <= 'Z'))) {
			if ((UserInputIndex - m.InputStart) < 25) {
				m.sSaveIt = m.sSaveIt += c;
				m.Output[1] = m.Output[1] += c;
				m.Memory[UserInputIndex].Contents = ("" + c);
				UserInputIndex++;
			} else {
				typingArea.setEnabled(false);
			}
			bMemoryOnly = true;
			repaint();
		}
		typingArea.setText(m.sSaveIt.toUpperCase());
		typingArea.setCaretPosition(typingArea.getText().length());
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void StepOnce() {
		boolean ThisIsNotTheEnd;

		bStepping = true;
		typingArea.setEnabled(false);
		ThisIsNotTheEnd = m.StepForward();

		for (PC = m.PCStart; PC <= m.PCStop; PC++) {
			if (PC != m.PCStart) {
				m.Memory[PC - 1].Contents = " ";
			}
			m.Memory[PC].Contents = "*";
			bMemoryOnly = true;
			update(this.getGraphics());
			if (cbStep.getState()) {
				JedsTimer MyTimer = new JedsTimer();
				MyTimer.DelayABit();
			}
		}
		if (m.bNeedInput) {
			if (bPlaying) {
				bPlaying = false;
				bWasPlaying = true;
				bPlay.setEnabled(true);
				bStepForward.setEnabled(true);
				bStop.setEnabled(false);
				bReset.setEnabled(true);
			}
			m.sSaveIt = "";
			UserInputIndex = m.InputStart;
			typingArea.setEnabled(true);
			typingArea.requestFocus();
			m.bNeedInput = false;
		}
		m.Execute();

		bMemoryOnly = true;
		repaint();
		typingArea.repaint();
		if (!ThisIsNotTheEnd) {
			if (bPlaying) {
				bPlaying = false;
			}
			bPlay.setEnabled(false);
			bStop.setEnabled(false);
			bStepForward.setEnabled(false);
			bReset.setEnabled(true);
			bReset.requestFocus();
		}
		bStepping = false;
	}

	boolean bPlaying = false;
	boolean bStepping = false;
	boolean bDelaying = false;

	int PC = 0;
	Thread t;

	public class JedsPlayer {
		private class Runner implements Runnable {
			public void run() {
				bDelaying = true;
				while (bPlaying) {
					if (!bStepping)
						StepOnce();
					try {
						Thread.sleep(PlayDelay);
					} catch (Exception e) {
					}
				}
				bDelaying = false;
			}
		}

		public void Play() {
			Runner r = new Runner();
			t = new Thread(r);
			try {
				t.start();
			} catch (Exception e) {
			}
		}
	}

	JedsPlayer MyPlayer = new JedsPlayer();

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(PLAY)) {
			if (!bPlaying) {
				bPlaying = true;
				bPlay.setEnabled(false);
				bStop.setEnabled(true);
				bReset.setEnabled(false);
				bStepForward.setEnabled(false);
				MyPlayer.Play();
				bStop.requestFocus();
			}
		} else if (e.getActionCommand().equals(STOP)) {
			if (bPlaying) {
				t.interrupt();
				bPlaying = false;
				bPlay.setEnabled(true);
				bStop.setEnabled(false);
				bStepForward.setEnabled(true);
				bReset.setEnabled(true);
				bPlay.requestFocus();
			}
		} else if (e.getActionCommand().equals(STEPFORWARD)) {
			bReset.setEnabled(true);
			if (!bStepping)
				StepOnce();
		} else if (e.getActionCommand().equals(STEPBACK)) {
		} else if (e.getActionCommand().equals(RESET)) {
			if (bPlaying) {
				bPlaying = false;
			}
			m.Reset();
			typingArea.setText("");
			typingArea.repaint();
			bMemoryOnly = true;
			repaint();
			bPlay.setEnabled(true);
			bStop.setEnabled(false);
			bStepForward.setEnabled(true);
			bReset.setEnabled(false);
		}
	}
}
