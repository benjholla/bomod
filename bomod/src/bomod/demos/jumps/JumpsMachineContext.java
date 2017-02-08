package bomod.demos.jumps;

import java.awt.Color;

import bomod.MachineContext;
import bomod.MachineContext.MemorySpot;

/**
 * Machine context specific to the Jumps applet
 * 
 * Original source by Jedidiah Crandall Java 1.7 compatibility modifications and
 * style changes by Ben Holland
 * 
 * @author Jedidiah Crandall <crandaj@erau.edu>
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class JumpsMachineContext extends MachineContext {

	public int PCStart;
	public int PCStop;
	public boolean bNeedInput;
	public int InputStart;
	public String sSaveIt;
	public String sExplanation;

	JumpsMachineContext() {
		Output[0] = new String();
		Output[1] = new String();
		Output[2] = new String();
		Output[3] = new String();
		Output[4] = new String();
		Reset();
	}

	public boolean Reset() {
		Step = 0;
		StackSize = 0;
		HighlightedLine = -1;
		PCStart = 0;
		PCStop = 0;
		bNeedInput = false;
		InputStart = 0xC0;
		sExplanation = "Click 'Play' or 'Step Forward' to begin.";
		sSaveIt = "";
		Output[0] = "";
		Output[1] = "";
		Output[2] = "";
		Output[3] = "";
		Output[4] = "";
		Code[0] = new LineOfCode("Subroutine C:", CodeColor1);
		Code[1] = new LineOfCode("  begin", CodeColor1);
		Code[2] = new LineOfCode("   Do some stuff", CodeColor1);
		Code[3] = new LineOfCode("  end;", CodeColor1);
		Code[4] = new LineOfCode("", CodeColor1);
		Code[5] = new LineOfCode("Subroutine B:", CodeColor2);
		Code[6] = new LineOfCode("  begin", CodeColor2);
		Code[7] = new LineOfCode("    needs space for B's Local Variables", CodeColor2);
		Code[8] = new LineOfCode("    Do some stuff", CodeColor2);
		Code[9] = new LineOfCode("    Call Subroutine C", CodeColor2);
		Code[10] = new LineOfCode("  end;", CodeColor2);
		Code[11] = new LineOfCode("", CodeColor2);
		Code[12] = new LineOfCode("Subroutine A:", CodeColor4);
		Code[13] = new LineOfCode("  begin", CodeColor4);
		Code[14] = new LineOfCode("    needs space for A's Local Variables", CodeColor4);
		Code[15] = new LineOfCode("    Call Subroutine B", CodeColor4);
		Code[16] = new LineOfCode("    Call Subroutine C", CodeColor4);
		Code[17] = new LineOfCode("  end;", CodeColor4);
		Code[18] = new LineOfCode("", CodeColor4);
		Code[19] = new LineOfCode("Start:", CodeColor3);
		Code[20] = new LineOfCode("  begin", CodeColor3);
		Code[21] = new LineOfCode("    Call Subroutine A", CodeColor3);
		Code[22] = new LineOfCode("  end;", CodeColor3);
		NumCodeLines = 23;

		int Loop;
		for (Loop = 0; Loop < 256; Loop++) {
			if (Loop <= 0x17) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor3);
			} else if (Loop <= 0x2B) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor1);
			} else if (Loop <= 0x41) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor4);
			} else if (Loop <= 0x5D) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor2);
			} else {
				Memory[Loop] = new MemorySpot("", StackContentsColor, MemoryDefaultBackgroundColor);
//				Memory[Loop] = new MemorySpot("", MemoryDefaultForegroundColor, MemoryDefaultBackgroundColor);
			}
		}
		return true;
	}

	public boolean StepForward() {
		Step++;
		bNeedInput = false;
		InputStart = 0;
		switch (Step) {
		case 1:
			PCStart = 0x00;
			PCStop = 0x00;
			HighlightedLine = 20;
			sExplanation = "This is a demo of how stacks are used to keep track of subroutine calls";
			break;
		case 2:
			PCStart = 0x00;
			PCStop = 0x17;
			HighlightedLine = 21;
			sExplanation = "Now Start is going to call (or invoke) Subroutine A";
			break;
		case 3:
			PCStart = 0x2C;
			PCStop = 0x2C;
			Memory[0x17].Contents = "X";
			HighlightedLine = 13;
			sExplanation = "The stack now contains room for Subroutine A's data and a return pointer to Start";
			break;
		case 4:
			PCStart = 0x2C;
			PCStop = 0x38;
			HighlightedLine = 15;
			sExplanation = "Subroutine A is going to call Subroutine B";
			break;
		case 5:
			PCStart = 0x42;
			PCStop = 0x42;
			Memory[0x38].Contents = "X";
			HighlightedLine = 6;
			sExplanation = "Look at the stack now that we are a couple of subroutines deep";
			break;
		case 6:
			PCStart = 0x42;
			PCStop = 0x51;
			HighlightedLine = 8;
			sExplanation = "Subroutine B needs eight bytes of temporary storage space";
			break;
		case 7:
			PCStart = 0x51;
			PCStop = 0x5D;
			HighlightedLine = 9;
			sExplanation = "Now Subroutine B calls subroutine C";
			break;
		case 8:
			PCStart = 0x18;
			PCStop = 0x18;
			Memory[0x5D].Contents = "X";
			HighlightedLine = 1;
			sExplanation = "Subroutine C doesn't need any temporary storage space, just a return pointer back to B";
			break;
		case 9:
			PCStart = 0x18;
			PCStop = 0x2B;
			HighlightedLine = 2;
			sExplanation = "The memory spots up on the top of the memory that are colored are the code text";
			break;
		case 10:
			PCStart = 0x2B;
			PCStop = 0x2B;
			HighlightedLine = 3;
			sExplanation = "The '*' is the program counter that tells the computer what instruction to execute";
			break;
		case 11:
			PCStart = 0x5D;
			PCStop = 0x5D;
			Memory[0x2B].Contents = "";
			HighlightedLine = 10;
			sExplanation = "Subroutine B is done now so its stack space will be destroyed and we'll use the return pointer to return to A";
			break;
		case 12:
			PCStart = 0x38;
			PCStop = 0x41;
			Memory[0x5D].Contents = "";
			HighlightedLine = 16;
			sExplanation = "Now Subroutine A calls Subroutine C";
			break;
		case 13:
			PCStart = 0x18;
			PCStop = 0x18;
			Memory[0x41].Contents = "X";
			HighlightedLine = 1;
			sExplanation = "Subroutine C doesn't know who calls it, it just takes care of business and returns control";
			break;
		case 14:
			PCStart = 0x18;
			PCStop = 0x2B;
			HighlightedLine = 2;
			sExplanation = "It uses the return pointer to know where to return control to";
			break;
		case 15:
			PCStart = 0x2B;
			PCStop = 0x2B;
			HighlightedLine = 3;
			sExplanation = "This time Subroutine C returns control to Subroutine A, not Subroutine B";
			break;
		case 16:
			PCStart = 0x41;
			PCStop = 0x41;
			Memory[0x2B].Contents = "";
			HighlightedLine = 17;
			sExplanation = "Subroutine A's stack space is destroyed and now we go back to Start";
			break;
		case 17:
			PCStart = 0x17;
			PCStop = 0x17;
			Memory[0x41].Contents = "";
			HighlightedLine = 22;
			sExplanation = "Click the 'Reset' button to start over again";
			return false;
		}
		return true;
	}

	public void Execute() {
		switch (Step) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			TheStack[0] = new MemorySpot("A", StackContentsColor, CodeColor4);
			TheStack[1] = new MemorySpot("A", StackContentsColor, CodeColor4);
			TheStack[2] = new MemorySpot("$", ReturnPointerColor, CodeColor3);
			Memory[0xC0] = TheStack[0];
			Memory[0xC1] = TheStack[1];
			Memory[0xC2] = TheStack[2];
			break;
		case 4:
			break;
		case 5:
			TheStack[3] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[4] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[5] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[6] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[7] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[8] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[9] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[10] = new MemorySpot("B", StackContentsColor, CodeColor2);
			TheStack[11] = new MemorySpot("$", ReturnPointerColor, CodeColor4);
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			Memory[0xC5] = TheStack[5];
			Memory[0xC6] = TheStack[6];
			Memory[0xC7] = TheStack[7];
			Memory[0xC8] = TheStack[8];
			Memory[0xC9] = TheStack[9];
			Memory[0xCA] = TheStack[10];
			Memory[0xCB] = TheStack[11];
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			TheStack[12] = new MemorySpot("$", ReturnPointerColor, CodeColor2);
			Memory[0xCC] = TheStack[12];
			break;
		case 9:
			break;
		case 10:
			break;
		case 11:
			TheStack[12].Contents = "";
			TheStack[12].FGColor = StackContentsColor;
			TheStack[12].BGColor = MemoryDefaultBackgroundColor;;
			Memory[0xCC] = TheStack[12];
			break;
		case 12:
			TheStack[3].Contents = "";
			TheStack[3].FGColor = MemoryDefaultForegroundColor;
			TheStack[3].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[4].Contents = "";
			TheStack[4].FGColor = MemoryDefaultForegroundColor;
			TheStack[4].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[5].Contents = "";
			TheStack[5].FGColor = MemoryDefaultForegroundColor;
			TheStack[5].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[6].Contents = "";
			TheStack[6].FGColor = MemoryDefaultForegroundColor;
			TheStack[6].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[7].Contents = "";
			TheStack[7].FGColor = MemoryDefaultForegroundColor;
			TheStack[7].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[8].Contents = "";
			TheStack[8].FGColor = MemoryDefaultForegroundColor;
			TheStack[8].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[9].Contents = "";
			TheStack[9].FGColor = MemoryDefaultForegroundColor;
			TheStack[9].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[10].Contents = "";
			TheStack[10].FGColor = MemoryDefaultForegroundColor;
			TheStack[10].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[11].Contents = "";
			TheStack[11].FGColor = MemoryDefaultForegroundColor;
			TheStack[11].BGColor = MemoryDefaultBackgroundColor;;
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			Memory[0xC5] = TheStack[5];
			Memory[0xC6] = TheStack[6];
			Memory[0xC7] = TheStack[7];
			Memory[0xC8] = TheStack[8];
			Memory[0xC9] = TheStack[9];
			Memory[0xCA] = TheStack[10];
			Memory[0xCB] = TheStack[11];
			break;
		case 13:
			TheStack[3].Contents = "$";
			TheStack[3].FGColor = ReturnPointerColor;
			TheStack[3].BGColor = CodeColor4;
			Memory[0xC3] = TheStack[3];
			break;
		case 14:
			break;
		case 15:
			break;
		case 16:
			TheStack[3].Contents = "";
			TheStack[3].FGColor = MemoryDefaultForegroundColor;
			TheStack[3].BGColor = MemoryDefaultBackgroundColor;;
			Memory[0xC3] = TheStack[3];
			break;
		case 17:
			TheStack[0].Contents = "";
			TheStack[0].FGColor = MemoryDefaultForegroundColor;
			TheStack[0].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[1].Contents = "";
			TheStack[1].FGColor = MemoryDefaultForegroundColor;
			TheStack[1].BGColor = MemoryDefaultBackgroundColor;;
			TheStack[2].Contents = "";
			TheStack[2].FGColor = MemoryDefaultForegroundColor;
			TheStack[2].BGColor = MemoryDefaultBackgroundColor;;
			Memory[0xC0] = TheStack[0];
			Memory[0xC1] = TheStack[1];
			Memory[0xC2] = TheStack[2];
			break;
		}
	}

	public boolean StepBack() {
		return true;
	}

}