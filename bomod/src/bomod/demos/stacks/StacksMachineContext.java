package bomod.demos.stacks;

import java.awt.Color;

import bomod.MachineContext;

/**
 * Machine context specific to the Stacks applet
 * 
 * Original source by Jedidiah Crandall Java 1.7 compatibility modifications and
 * style changes by Ben Holland
 * 
 * @author Jedidiah Crandall <crandaj@erau.edu>
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class StacksMachineContext extends MachineContext {

	public int PCStart;
	public int PCStop;
	public boolean bNeedInput;
	public int InputStart;
	public String sSaveIt;
	public String sExplanation;

	StacksMachineContext() {
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
		Code[0] = new LineOfCode("void foo_1(char *p_my_parameter)", CodeColor1);
		Code[1] = new LineOfCode("{", CodeColor1);
		Code[2] = new LineOfCode("  *p_my_parameter = 'F';", CodeColor1);
		Code[3] = new LineOfCode("}", CodeColor1);
		Code[4] = new LineOfCode("", CodeColor1);
		Code[5] = new LineOfCode("void foo_2(char my_parameter)", CodeColor2);
		Code[6] = new LineOfCode("{", CodeColor2);
		Code[7] = new LineOfCode("  char my_local_variable = 'E';", CodeColor2);
		Code[8] = new LineOfCode("  my_parameter = 'V';", CodeColor2);
		Code[9] = new LineOfCode("  foo_1(&my_local_variable);", CodeColor2);
		Code[10] = new LineOfCode("}", CodeColor2);
		Code[11] = new LineOfCode("", CodeColor2);
		Code[12] = new LineOfCode("void foo_3(char my_parameter)", CodeColor4);
		Code[13] = new LineOfCode("{", CodeColor4);
		Code[14] = new LineOfCode("  char my_local_variable = 'Q';", CodeColor4);
		Code[15] = new LineOfCode("  foo_2(my_local_variable);", CodeColor4);
		Code[16] = new LineOfCode("  foo_1(&my_parameter);", CodeColor4);
		Code[17] = new LineOfCode("}", CodeColor4);
		Code[18] = new LineOfCode("", CodeColor4);
		Code[19] = new LineOfCode("void main()", CodeColor3);
		Code[20] = new LineOfCode("{", CodeColor3);
		Code[21] = new LineOfCode("  foo_3('A');", CodeColor3);
		Code[22] = new LineOfCode("}", CodeColor3);
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
			sExplanation = "This is a demo of how stacks are used to call functions and pass stuff around.";
			break;
		case 2:
			PCStart = 0x00;
			PCStop = 0x17;
			HighlightedLine = 21;
			sExplanation = "Now main is going to call foo_3()";
			break;
		case 3:
			PCStart = 0x2C;
			PCStop = 0x2C;
			Memory[0x17].Contents = "X";
			HighlightedLine = 13;
			sExplanation = "The stack now contains room for foo_3()'s data and a return pointer to main()";
			break;
		case 4:
			PCStart = 0x2C;
			PCStop = 0x38;
			HighlightedLine = 15;
			sExplanation = "foo_3() is going to call foo_2() and pass my_local_variable by value";
			break;
		case 5:
			PCStart = 0x42;
			PCStop = 0x42;
			Memory[0x38].Contents = "X";
			HighlightedLine = 6;
			sExplanation = "Look at the stack now that we're a couple of functions deep";
			break;
		case 6:
			PCStart = 0x42;
			PCStop = 0x51;
			HighlightedLine = 8;
			sExplanation = "Notice that my_parameter for foo_2() gets changed but my_local_variable for foo_1() doesn't";
			break;
		case 7:
			PCStart = 0x51;
			PCStop = 0x5D;
			HighlightedLine = 9;
			sExplanation = "Now foo_2() calls foo_1() but passes my_local_variable by reference";
			break;
		case 8:
			PCStart = 0x18;
			PCStop = 0x18;
			Memory[0x5D].Contents = "X";
			HighlightedLine = 1;
			sExplanation = "We're now a few functions deep, but the stack will keep track of where we've been";
			break;
		case 9:
			PCStart = 0x18;
			PCStop = 0x2B;
			HighlightedLine = 2;
			sExplanation = "Notice how my_local_variable in foo_2() got changed";
			break;
		case 10:
			PCStart = 0x2B;
			PCStop = 0x2B;
			HighlightedLine = 3;
			sExplanation = "foo_1()'s stack space now will be deallocated and we'll use the return pointer to go back to foo_2()";
			break;
		case 11:
			PCStart = 0x5D;
			PCStop = 0x5D;
			Memory[0x2B].Contents = "";
			HighlightedLine = 10;
			sExplanation = "foo_2() is done as well so it's stack space is destroyed and we return to foo_3()";
			break;
		case 12:
			PCStart = 0x38;
			PCStop = 0x41;
			Memory[0x5D].Contents = "";
			HighlightedLine = 16;
			sExplanation = "Now foo_3() calls foo_1() and passes my_parameter by reference";
			break;
		case 13:
			PCStart = 0x18;
			PCStop = 0x18;
			Memory[0x41].Contents = "X";
			HighlightedLine = 1;
			sExplanation = "foo_1() doesn't know who calls it, it just takes care of business and returns control";
			break;
		case 14:
			PCStart = 0x18;
			PCStop = 0x2B;
			HighlightedLine = 2;
			sExplanation = "foo_3()'s local variable gets changed now";
			break;
		case 15:
			PCStart = 0x2B;
			PCStop = 0x2B;
			HighlightedLine = 3;
			sExplanation = "This time foo_1() returns control to foo_3()";
			break;
		case 16:
			PCStart = 0x41;
			PCStop = 0x41;
			Memory[0x2B].Contents = "";
			HighlightedLine = 17;
			sExplanation = "foo_3()'s stack space is destroyed and now we go back to main()";
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
			TheStack[1] = new MemorySpot("Q", StackContentsColor, CodeColor4);
			TheStack[2] = new MemorySpot("$", ReturnPointerColor, CodeColor3);
			Memory[0xC0] = TheStack[0];
			Memory[0xC1] = TheStack[1];
			Memory[0xC2] = TheStack[2];
			break;
		case 4:
			break;
		case 5:
			TheStack[3] = new MemorySpot("Q", StackContentsColor, CodeColor2);
			TheStack[4] = new MemorySpot("E", StackContentsColor, CodeColor2);
			TheStack[5] = new MemorySpot("$", ReturnPointerColor, CodeColor4);
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			Memory[0xC5] = TheStack[5];
			break;
		case 6:
			TheStack[3].Contents = "V";
			Memory[0xC3] = TheStack[3];
			break;
		case 7:
			break;
		case 8:
			TheStack[6] = new MemorySpot("&", StackContentsColor, CodeColor1);
			TheStack[7] = new MemorySpot("$", ReturnPointerColor, CodeColor2);
			Memory[0xC6] = TheStack[6];
			Memory[0xC7] = TheStack[7];
			break;
		case 9:
			TheStack[4].Contents = "F";
			Memory[0xC4] = TheStack[4];
			break;
		case 10:
			break;
		case 11:
			TheStack[6].Contents = "";
			TheStack[6].FGColor = StackContentsColor;
			TheStack[6].BGColor = MemoryDefaultBackgroundColor;
			TheStack[7].Contents = "";
			TheStack[7].FGColor = StackContentsColor;
			TheStack[7].BGColor = MemoryDefaultBackgroundColor;
			Memory[0xC6] = TheStack[6];
			Memory[0xC7] = TheStack[7];
			break;
		case 12:
			TheStack[3].Contents = "";
			TheStack[3].FGColor = MemoryDefaultForegroundColor;
			TheStack[3].BGColor = MemoryDefaultBackgroundColor;
			TheStack[4].Contents = "";
			TheStack[4].FGColor = MemoryDefaultForegroundColor;
			TheStack[4].BGColor = MemoryDefaultBackgroundColor;
			TheStack[5].Contents = "";
			TheStack[5].FGColor = MemoryDefaultForegroundColor;
			TheStack[5].BGColor = MemoryDefaultBackgroundColor;
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			Memory[0xC5] = TheStack[5];
			break;
		case 13:
			TheStack[3].Contents = "&";
			TheStack[3].FGColor = StackContentsColor;
			TheStack[3].BGColor = CodeColor1;
			TheStack[4].Contents = "$";
			TheStack[4].FGColor = ReturnPointerColor;
			TheStack[4].BGColor = CodeColor4;
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			break;
		case 14:
			TheStack[0].Contents = "F";
			Memory[0xC0] = TheStack[0];
			break;
		case 15:
			break;
		case 16:
			TheStack[3].Contents = "";
			TheStack[3].FGColor = MemoryDefaultForegroundColor;
			TheStack[3].BGColor = MemoryDefaultBackgroundColor;
			TheStack[4].Contents = "";
			TheStack[4].FGColor = MemoryDefaultForegroundColor;
			TheStack[4].BGColor = MemoryDefaultBackgroundColor;
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			break;
		case 17:
			TheStack[0].Contents = "";
			TheStack[0].FGColor = MemoryDefaultForegroundColor;
			TheStack[0].BGColor = MemoryDefaultBackgroundColor;
			TheStack[1].Contents = "";
			TheStack[1].FGColor = MemoryDefaultForegroundColor;
			TheStack[1].BGColor = MemoryDefaultBackgroundColor;
			TheStack[2].Contents = "";
			TheStack[2].FGColor = MemoryDefaultForegroundColor;
			TheStack[2].BGColor = MemoryDefaultBackgroundColor;
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