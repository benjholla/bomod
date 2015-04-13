package bomod;

import java.awt.Color;

/**
 * Parent class for all machine contexts
 * 
 * Original source by Jedidiah Crandall Java 1.7 compatibility modifications and
 * style changes by Ben Holland
 * 
 * @author Jedidiah Crandall <crandaj@erau.edu>
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class MachineContext {
	public int NumCodeLines;
	public int HighlightedLine;
	public int Step;
	public int StackSize;

	public Color BackgroundColor = new Color(0, 0, 128);
	public Color CodeColor1 = new Color(255, 255, 0);
	public Color CodeColor2 = new Color(128, 255, 64);
	public Color CodeColor3 = new Color(255, 0, 255);
	public Color CodeColor4 = new Color(0, 255, 255);
	public Color StackContentsColor = new Color(0, 0, 0);
	public Color ReturnPointerColor = new Color(0, 0, 0);

	public class MemorySpot {
		public MemorySpot(String ContentsInit, Color FGColorInit, Color BGColorInit) {
			Contents = ContentsInit;
			FGColor = FGColorInit;
			BGColor = BGColorInit;
		}

		public String Contents;
		public Color FGColor;
		public Color BGColor;
	}

	public MemorySpot[] Memory = new MemorySpot[256];

	public class LineOfCode {
		public LineOfCode(String sInit, Color FGColorInit) {
			s = sInit;
			FGColor = FGColorInit;
		}

		public String s;
		public Color FGColor;
	}

	public LineOfCode[] Code = new LineOfCode[30];

	public String[] Output = new String[5];
	public MemorySpot[] TheStack = new MemorySpot[64];

}
