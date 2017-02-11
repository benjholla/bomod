# Buffer Overflow Module Resource Mirror


This directory contains is a mirror of the [original Buffer Overflow Module](http://nsfsecurity.pr.erau.edu/bom/) created by [NSF Award No. 0113627](http://www.nsf.gov/awardsearch/showAward?AWD_ID=0113627). This mirror is provided with permission of the lead author Dr. Susan Gerhart in response to the now defunct [http://nsfsecurity.pr.erau.edu](http://nsfsecurity.pr.erau.edu/) subdomain. An index of the original Buffer Overflow Module resources is provided below.

**Note:** The materials in this mirror were recovered from the last recorded update of the [nsfsecurity.pr.erau.edu/bom/index.html](https://web.archive.org/web/20140210111231/http://nsfsecurity.pr.erau.edu/bom/index.html),  [nsfsecurity.pr.erau.edu/bom\_docs.html](http://nsfsecurity.pr.erau.edu/bom_docs/bom_docs.html), and [nsfsecurity.pr.erau.edu/bom\_docs/Demos/script.html](https://web.archive.org/web/20130708184753/http://nsfsecurity.pr.erau.edu/bom_docs/Demos/script.html) pages, as seen by the Internet Archive project. The remaining materials were kindly provided by Dr. Susan Gerhart.

## Introduction

### How do buffer overflow attacks work?

A buffer overflow results from programming errors and testing failures and is common to all operating systems. These flaws permit attacking programs to gain control over other computers by sending long strings with certain patterns of data. Over half of the security advisories from [CERT](www.cert.org/), e.g. the August 2001 "Code Red", trace to this widespread weakness of the software industry. This module of Java applets is developed to educate student and industrial programmers to avoid the practices that cause buffer overflows as one step controlling the continuing effects of this avoidable problem.

The buffer overflow interactive module and demos may be used by an instructor knowledgeable about runtime environments and C programming in classes such as: operating systems, C/C++ and assembly programming, compiler and software tools, surveys of programming languages, and computer security.

**Download the [Complete Module](bomod.zip)**. After downloading, unzip the file then click on "bomod.exe" to run. The complete download (~20 Mb) consists of:

- An Interactive Authorware piece ("bomod.exe" for Windows)
- Buffer overflow demos (requires Java enabled browser)
- Presentation-Ready PowerPoints (requires PowerPoint)
- Defense tools
- Exercises
- Online Demos
- Quizzes
- A Readme file explaining locations of the included components

**Buffer Overflow Demos:** The demos are Java applets and require Java 1.3 (or higher) runtime environment.

You can download all of the demos in one archived file to run locally on your computer: [BOallDemos.tar](BOallDemos.tar) (660 kb) or [BOallDemos.zip](BOallDemos.zip) (120 kb). The demos are included in the [complete download](bomod.zip).

**Or view each demo online:** Simply click on a link below to open the desired demo, then click the animation buttons at the top of the screen. You may need to wait a moment for the applet to load. For help using the demos, download a PowerPoint presentation on [How to use the Demo applets](HowToUseApplet.pdf).

**Note**: The original *Stacks*, *Spock*, *Smasher*, *StackGuard*, and *Jumps* applets are no longer supported by most modern browsers and the original applet HTML pages could not be recovered for this mirror. Please see the [forked version of these modules](../README.md) if you wish to run them in a Java 1.7+ environment. The *its4demo* and *bodemo* outputs are linked below.

- **Stacks**: An introduction to the way languages like C use stack frames to store local variables, pass variables from function to function by value and by reference, and also return control to the calling subroutine when the called subroutine exits. 
- **Spock**: Demonstrates what is commonly called a "variable attack" buffer overflow, where the target is data. 
- **Smasher**: Demonstrates a "stack attack," more commonly referred to as "stack smashing." 
- **StackGuard**: This demo shows how the StackGuard compiler can help prevent "stack attacks." 
- [bodemo](bodemo.txt): This is a mock attack on a linux system demonstrating how an attacker can get a root shell. 
- [its4demo](its4demo.txt): Shows the output of ITS4, a static analyzer, on two different C programs. 
- **Jumps**: Shows how stacks are used to keep track of subroutine calls.

**Demo Structure**: Each buffer overflow demo uses an abstract machine with a small memory (displayed on the right side of the screen) showing the run-time stack.

For the abstract machine, there are some assumptions the user should be made aware of. (1) all library functions, such as gets() and puts(), are compiled inline and therefore don't require a function call, (2) allocation for a function's parameters and for its local variables takes place at the same time, and the return address is put on the stack last, (3) the stack grows "up" in memory, (4) it does not necessarily reflect the workings of any architecture or C compiler.

**Instructor's guide to the module**: [View Instructors' Guide for Demonstrating Buffer Overflows](instructor_guide.md), with tips and info on presenting the module. This guide is included in the complete download.

**Download specific presentations, quizzes, and defense tools** at Buffer Overflow Resources. These resources are included in the complete module download.

**Background reading**: For basic prerequisite knowledge of buffer overflows a good reading is [http://www.rsasecurity.com/rsalabs/technotes/buffer/buffer_overflow.html](http://www.rsasecurity.com/rsalabs/technotes/buffer/buffer_overflow.html). 

**Contacts**: For questions, comments, or general feedback to Dr. Susan Gerhart ([gerharts@erau.edu](mailto:gerharts@erau.edu)).

**Feedback**: We would like to hear from you about how you used the buffer overflow materials. Please use our Buffer Overflow [feedback form](#) to submit feedback online, or go to the Feedback index to report on your use of other modules in this series.

**Copyright Notice**: The content of this module and all associated materials are Copyright (c) 2002, Susan Gerhart, Jan Hogle, Jedidiah Crandall, except otherwise noted. For information on distribution permission and requirements, please contact Dr. Susan Gerhart at [gerharts@erau.edu](mailto:gerharts@erau.edu).

## Buffer Overflow Resources

### Ready-to-Use Presentations

- Introduction to Buffer Overflow: [(PDF)](BODefensesTable.pdf) [(PowerPoint)](BODefensesTable.ppt)
- Software Engineering Lifecycle: [(PDF)](BO_SE_Lifecycle.pdf) [(PowerPoint)](BO_SE_Lifecycle.ppt)
- Buffer Overflow Defenses: [(PDF)](BODefenses.pdf) [(PowerPoint)](BODefenses.ppt)
- Case Study: Code Red: [(PDF)](CaseStudyCodeRed.pdf) [(PowerPoint)](CaseStudyCodeRed.ppt)
- For C Programmers: [(PDF)](ForCProgrammers.pdf) [(PowerPoint)](ForCProgrammers.ppt)

###  Defense Tools

- Buffer Overflow Defenses Table: [(PDF)](BODefenses.pdf) [(MSWord)](BODefenses.doc)
- Buffer Overflow Checklists: [(PDF)](BOChecklists.pdf) [(MSWord)](BOChecklists.doc)
- Buffer Overflow Points: [(PDF)](BOPoints.pdf) [(MSWord)](BOPoints.doc)
- Exception Handling: [(PDF)](ExceptionHandling.pdf) [(MSWord)](ExceptionHandling.doc)

###  Suggested Exercises

- Scavenger Hunt: [(PDF)](bo_scavenger_hunt.pdf) [(MSWord)](bo_scavenger_hunt.doc)
- Exercises/Labs: [(PDF)](bo_labs_exercises.pdf) [(MSWord)](bo_labs_exercises.doc)

### Quizzes

- Buffer Overflow Introduction: [(PDF)](QuizForIntroToBO.pdf) [(PowerPoint)](QuizForIntroToBO.ppt)
- Buffer Overflow Defenses & Causes: [(PDF)](QuizForBODefenses.pdf) [(PowerPoint)](QuizForBODefenses.ppt)
- For C Programmers: [(PDF)](QuizForCProgrammers.pdf) [(PowerPoint)](QuizForCProgrammers.ppt)

### Papers

- Susan L. Gerhart, Jan G. Hogle, Jedidiah R. Crandall. *Explaining the Buffer Overflow Problem: Instructional Design and Evaluation in Information Security Education*. [(PDF)](BufferEd.pdf)
- Jedidiah R. Crandall, Susan L. Gerhart, Jan G. Hogle. *Driving Home the Buffer Overflow Problem: A Training Module for Programmers and Managers*. [(PDF)](ncisse2002.pdf) [(PowerPoint)](ncisse2002.ppt)
