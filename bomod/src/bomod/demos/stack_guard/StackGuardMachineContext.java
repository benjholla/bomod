package bomod.demos.stack_guard;

import java.awt.Color;

import bomod.MachineContext;

/**
 * Machine context specific to the StackGuard applet
 * 
 * Original source by Jedidiah Crandall Java 1.7 compatibility modifications and
 * style changes by Ben Holland
 * 
 * @author Jedidiah Crandall <crandaj@erau.edu>
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class StackGuardMachineContext extends MachineContext {

	public int PCStart;
	public int PCStop;
	public boolean bNeedInput;
	public int InputStart;
	public String sSaveIt;
	public String sExplanation;

	StackGuardMachineContext() {
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

		Code[0] = new LineOfCode("#include <stdio.h>", DefaultCodeColor);
		Code[1] = new LineOfCode("", DefaultCodeColor);
		Code[2] = new LineOfCode("typedef char t_STRING[10];", DefaultCodeColor);
		Code[3] = new LineOfCode("", DefaultCodeColor);
		Code[4] = new LineOfCode("void get_string(t_STRING str)", CodeColor1);
		Code[5] = new LineOfCode("{", CodeColor1);
		Code[6] = new LineOfCode("  gets(str);", CodeColor1);
		Code[7] = new LineOfCode("  puts(\"You entered:\");", CodeColor1);
		Code[8] = new LineOfCode("  puts(str);", CodeColor1);
		Code[9] = new LineOfCode("}", CodeColor1);
		Code[10] = new LineOfCode("", StackContentsColor);
		Code[11] = new LineOfCode("void forbidden_function()", CodeColor2);
		Code[12] = new LineOfCode("{", CodeColor2);
		Code[13] = new LineOfCode("  puts(\"Oh, bother.\");", CodeColor2);
		Code[14] = new LineOfCode("}", CodeColor2);
		Code[15] = new LineOfCode("", StackContentsColor);
		Code[16] = new LineOfCode("void main()", CodeColor3);
		Code[17] = new LineOfCode("{", CodeColor3);
		Code[18] = new LineOfCode("  t_STRING my_string = \"Hello.\";", CodeColor3);
		Code[19] = new LineOfCode("", CodeColor3);
		Code[20] = new LineOfCode("  puts(\"Enter something:\");", CodeColor3);
		Code[21] = new LineOfCode("  get_string(my_string);", CodeColor3);
		Code[22] = new LineOfCode("}", CodeColor3);
		NumCodeLines = 23;

		int Loop;
		for (Loop = 0; Loop < 256; Loop++) {
			if (Loop <= 0x23) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor3);
			} else if (Loop <= 0x43) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor1);
			} else if (Loop <= 0x6A) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor2);
			} else {
				Memory[Loop] = new MemorySpot("", MemoryDefaultForegroundColor, MemoryDefaultBackgroundColor);
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
			HighlightedLine = 17;
			sExplanation = "This is a demo of how the StackGuard compiler protects the return address";
			break;
		case 2:
			PCStart = 0x00;
			PCStop = 0x12;
			HighlightedLine = 20;
			sExplanation = "StackGuard has some overhead, though, and doesn't entirely solve the problem";
			break;
		case 3:
			PCStart = 0x12;
			PCStop = 0x23;
			HighlightedLine = 21;
			sExplanation = "main() will call get_string() now, but notice that there will be an extra byte on the stack";
			break;
		case 4:
			PCStart = 0x24;
			PCStop = 0x24;
			HighlightedLine = 5;
			sExplanation = "The '?' is called a canary, and is some random number whose value the attacker can't foresee";
			break;
		case 5:
			PCStart = 0x24;
			PCStop = 0x2E;
			HighlightedLine = 6;
			bNeedInput = true;
			InputStart = 0xCA;
			sExplanation = "Now is where you can use the text box above to give input to the program and click 'Play' or 'Step Forward' to resume";
			break;
		case 6:
			PCStart = 0x2E;
			PCStop = 0x39;
			HighlightedLine = 7;
			sExplanation = "You can't overwrite the return address without also overwriting the canary";
			break;
		case 7:
			PCStart = 0x39;
			PCStop = 0x43;
			HighlightedLine = 8;
			sExplanation = "Sometimes the canary is also XORed with the return address for added security (and added overhead)";
			break;
		case 8:
			PCStart = 0x43;
			PCStop = 0x43;
			HighlightedLine = 9;
			sExplanation = "Now get_string() checks the integrity of the canary before it uses the return address";
			break;
		case 9:
			PCStart = 0x23;
			PCStop = 0x23;
			HighlightedLine = 22;
			sExplanation = "If the canary was not changed then it's okay to use the return address to give control back to the calling function";
			return false;
		case 40:
			PCStart = 0x43;
			PCStop = 0x43;
			HighlightedLine = 9;
			sExplanation = "If the canary is found to have been changed the best we can do is kill the program, still leaving us open to a denial of service attack";
			Output[4] = "StackGuard check failed.";
			return false;
		}
		return true;
	}

	public void Execute() {
		switch (Step) {
		case 1:
			TheStack[0] = new MemorySpot("H", StackContentsColor, CodeColor3);
			TheStack[1] = new MemorySpot("e", StackContentsColor, CodeColor3);
			TheStack[2] = new MemorySpot("l", StackContentsColor, CodeColor3);
			TheStack[3] = new MemorySpot("l", StackContentsColor, CodeColor3);
			TheStack[4] = new MemorySpot("o", StackContentsColor, CodeColor3);
			TheStack[5] = new MemorySpot(".", StackContentsColor, CodeColor3);
			TheStack[6] = new MemorySpot("", StackContentsColor, CodeColor3);
			TheStack[7] = new MemorySpot("", StackContentsColor, CodeColor3);
			TheStack[8] = new MemorySpot("", StackContentsColor, CodeColor3);
			TheStack[9] = new MemorySpot("", StackContentsColor, CodeColor3);
			Memory[0xC0] = TheStack[0];
			Memory[0xC1] = TheStack[1];
			Memory[0xC2] = TheStack[2];
			Memory[0xC3] = TheStack[3];
			Memory[0xC4] = TheStack[4];
			Memory[0xC5] = TheStack[5];
			Memory[0xC6] = TheStack[6];
			Memory[0xC7] = TheStack[7];
			Memory[0xC8] = TheStack[8];
			Memory[0xC9] = TheStack[9];
			StackSize = 10;
			break;
		case 2:
			Output[0] = "Enter something:";
			break;
		case 3:
			break;
		case 4:
			Memory[0x23].Contents = "X";
			TheStack[10] = new MemorySpot("H", StackContentsColor, CodeColor1);
			TheStack[11] = new MemorySpot("e", StackContentsColor, CodeColor1);
			TheStack[12] = new MemorySpot("l", StackContentsColor, CodeColor1);
			TheStack[13] = new MemorySpot("l", StackContentsColor, CodeColor1);
			TheStack[14] = new MemorySpot("o", StackContentsColor, CodeColor1);
			TheStack[15] = new MemorySpot(".", StackContentsColor, CodeColor1);
			TheStack[16] = new MemorySpot("", StackContentsColor, CodeColor1);
			TheStack[17] = new MemorySpot("", StackContentsColor, CodeColor1);
			TheStack[18] = new MemorySpot("", StackContentsColor, CodeColor1);
			TheStack[19] = new MemorySpot("", StackContentsColor, CodeColor1);
			TheStack[20] = new MemorySpot("?", ReturnPointerColor, CodeColor3);
			TheStack[21] = new MemorySpot("$", ReturnPointerColor, CodeColor3);
			StackSize = 22;
			Memory[0xCA] = TheStack[10];
			Memory[0xCB] = TheStack[11];
			Memory[0xCC] = TheStack[12];
			Memory[0xCD] = TheStack[13];
			Memory[0xCE] = TheStack[14];
			Memory[0xCF] = TheStack[15];
			Memory[0xD0] = TheStack[16];
			Memory[0xD1] = TheStack[17];
			Memory[0xD2] = TheStack[18];
			Memory[0xD3] = TheStack[19];
			Memory[0xD4] = TheStack[20];
			Memory[0xD5] = TheStack[21];
			break;
		case 5:
			break;
		case 6:
			Output[2] = "You entered:";
			break;
		case 7:
			Output[3] = sSaveIt;
			break;
		case 8:
			if (Memory[0xD4].Contents.compareTo("?") != 0) {
				Step = 39;
			}
			Memory[0x43].Contents = "";
			break;
		case 9:
			int Loop;
			for (Loop = 0xCA; Loop < 0xFF; Loop++) {
				Memory[Loop].Contents = "";
				Memory[Loop].FGColor = Color.white;
				Memory[Loop].BGColor = Color.darkGray;
			}
			break;
		}
	}

	public boolean StepBack() {
		return true;
	}

}