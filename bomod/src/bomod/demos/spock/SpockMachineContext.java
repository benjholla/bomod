package bomod.demos.spock;

import java.awt.Color;

import bomod.MachineContext;

/**
 * Machine context specific to the Spock applet
 * 
 * Original source by Jedidiah Crandall Java 1.7 compatibility modifications and
 * style changes by Ben Holland
 * 
 * @author Jedidiah Crandall <crandaj@erau.edu>
 * @author Benjamin Holland <bholland@iastate.edu>
 */
public class SpockMachineContext extends MachineContext {

	public int PCStart;
	public int PCStop;
	public boolean bNeedInput;
	public int InputStart;
	public String sSaveIt;
	public String sExplanation;

	SpockMachineContext() {
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
		Code[1] = new LineOfCode("#include <string.h>", DefaultCodeColor);
		Code[2] = new LineOfCode("", DefaultCodeColor);
		Code[3] = new LineOfCode("int check_password()", CodeColor1);
		Code[4] = new LineOfCode("{", CodeColor1);
		Code[5] = new LineOfCode("  char correct_password = 'F';", CodeColor1);
		Code[6] = new LineOfCode("  char input[8];", CodeColor1);
		Code[7] = new LineOfCode("", CodeColor1);
		Code[8] = new LineOfCode("  gets(input);", CodeColor1);
		Code[9] = new LineOfCode("  if (!strcmp(input, \"SPOCKSUX\"))", CodeColor1);
		Code[10] = new LineOfCode("    correct_password = 'T';", CodeColor1);
		Code[11] = new LineOfCode("  return (correct_password == 'T');", CodeColor1);
		Code[12] = new LineOfCode("}", CodeColor1);
		Code[13] = new LineOfCode("", CodeColor1);
		Code[14] = new LineOfCode("void main()", CodeColor2);
		Code[15] = new LineOfCode("{", CodeColor2);
		Code[16] = new LineOfCode("  puts(\"Enter Password:\");", CodeColor2);
		Code[17] = new LineOfCode("  if (check_password())", CodeColor2);
		Code[18] = new LineOfCode("    puts(\"Hello, Dr. Bones.\");", CodeColor2);
		Code[19] = new LineOfCode("  else", CodeColor2);
		Code[20] = new LineOfCode("    puts(\"Access denied.\");", CodeColor2);
		Code[21] = new LineOfCode("}", CodeColor2);
		NumCodeLines = 22;

		int Loop;
		for (Loop = 0; Loop < 256; Loop++) {
			if (Loop <= 0x3C) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor2);
			} else if (Loop <= 0x6A) {
				Memory[Loop] = new MemorySpot("", StackContentsColor, CodeColor1);
			} else {
				Memory[Loop] = new MemorySpot("", MemoryDefaultForegroundColor, MemoryDefaultBackgroundColor);
			}
		}
		return true;
	}

	String Junk = new String("!:<{}\"'.^$!$#!*@^(*~][],.<}][*!&@%$*(#(*%%$!^$##!$@(#%#^^%$%%(&*',/?");

	public boolean StepForward() {
		Step++;
		bNeedInput = false;
		InputStart = 0;
		switch (Step) {
		case 1:
			PCStart = 0x00;
			PCStop = 0x00;
			HighlightedLine = 15;
			sExplanation = "This is a demo of how security measures can be circumvented using a buffer overflow";
			break;
		case 2:
			PCStart = 0x00;
			PCStop = 0x08;
			HighlightedLine = 16;
			sExplanation = "This just prints something to the screen";
			break;
		case 3:
			PCStart = 0x08;
			PCStop = 0x17;
			HighlightedLine = 17;
			sExplanation = "main() will now call check_password()";
			break;
		case 4:
			PCStart = 0x3D;
			PCStop = 0x3D;
			HighlightedLine = 4;
			sExplanation = "check_password() makes some stack space for it's local variables and also a return pointer back into main()";
			break;
		case 5:
			PCStart = 0x3D;
			PCStop = 0x48;
			HighlightedLine = 8;
			bNeedInput = true;
			InputStart = 0xC0;
			sExplanation = "Now is where you can use the text box above to give input to the program and click 'Play' or 'Step Forward' to resume";
			break;
		case 6:
			PCStart = 0x48;
			PCStop = 0x51;
			HighlightedLine = 9;
			sExplanation = "The program now checks to see if you entered \"SPOCKSUX\"";
			break;
		case 7:
			String sPass;
			if (sSaveIt.length() >= 8)
				sPass = sSaveIt.substring(0, 8);
			else
				sPass = "";

			if (sPass.compareTo("SPOCKSUX") == 0) {
				PCStart = 0x51;
				PCStop = 0x5E;
				HighlightedLine = 10;
				sExplanation = "You entered the right password, but can you get logged in another way?";
			} else {
				Memory[0x51].Contents = " ";
				Step = 8;
				PCStart = 0x5E;
				PCStop = 0x6A;
				HighlightedLine = 11;
				sExplanation = "You didn't enter the right password, but do you need to?";
			}
			break;
		case 8:
			PCStart = 0x5E;
			PCStop = 0x6A;
			HighlightedLine = 11;
			Memory[0xC8].Contents = "T";
			sExplanation = "Try, instead, overflowing the buffer to get logged in as Dr. Bones";
			break;
		case 9:
			PCStart = 0x6A;
			PCStop = 0x6A;
			HighlightedLine = 12;
			sExplanation = "check_password() is done, so it will deallocate its stack space and return contorl to main()";
			break;
		case 20:
			PCStart = 0x17;
			PCStop = 0x28;
			HighlightedLine = 18;
			sExplanation = "You're now logged in as Dr. Bones";
			break;
		case 30:
			Memory[0x17].Contents = "";
			PCStart = 0x29;
			PCStop = 0x3C;
			HighlightedLine = 20;
			sExplanation = "You didn't get logged in";
			break;
		case 21:
		case 31:
			Memory[0x28].Contents = " ";
			PCStart = 0x3C;
			PCStop = 0x3C;
			HighlightedLine = 21;
			return false;
		case 41:
			sExplanation = "You cobbled up the machine because the return pointer to main() was corrupted";
			return false;
		}
		return true;
	}

	public void Execute() {
		switch (Step) {
		case 1:
			break;
		case 2:
			Output[0] = "Enter Password:";
			break;
		case 3:
			Memory[0x17].Contents = "X";
			break;
		case 4:
			TheStack[0] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[1] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[2] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[3] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[4] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[5] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[6] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[7] = new MemorySpot(" ", StackContentsColor, CodeColor1);
			TheStack[8] = new MemorySpot("F", StackContentsColor, CodeColor1);
			TheStack[9] = new MemorySpot("$", ReturnPointerColor, CodeColor2);
			StackSize = 10;
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
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			Memory[0xC8].Contents = "T";
			break;
		case 8:
			break;
		case 9:
			int Loop;
			if (Memory[0xC8].Contents.compareTo("T") == 0) {
				Step = 19;
			} else {
				Step = 29;
			}
			if (Memory[0xC9].Contents.compareTo("$") != 0) {
				Step = 39;
			}
			for (Loop = 0xC0; Loop < 256; Loop++) {
				Memory[Loop].Contents = "";
				Memory[Loop].FGColor = MemoryDefaultForegroundColor;
				Memory[Loop].BGColor = MemoryDefaultBackgroundColor;
			}
			Memory[0x6A].Contents = " ";
			Memory[0x17].Contents = "*";
			break;
		case 20:
			Output[2] = "Hello, Dr. Bones.";
			Memory[0x17].Contents = " ";
			break;
		case 30:
			Output[2] = "Access denied.";
			Memory[0x17].Contents = " ";
			break;
		case 40:
			for (Loop = 0x00; Loop <= 0xFF; Loop++) {
				Memory[Loop].Contents = Junk.substring(Loop % Junk.length(), Loop % Junk.length() + 1);
			}
			Output[2] = "Segmentation fault.";
			break;
		}
	}

	public boolean StepBack() {
		return true;
	}

}