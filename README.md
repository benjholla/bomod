# Buffer Overflow Module (bomod)
This project is a fork (and [mirror](./mirror/README.md)) of the [original Buffer Overflow Module](http://nsfsecurity.pr.erau.edu/bom/) created by [NSF Award No. 0113627](http://www.nsf.gov/awardsearch/showAward?AWD_ID=0113627). The fork provides modifications to the interactive demos that enables the demos to run locally in a modern Java Runtime Environment. The fork also adds the option of a presentation mode color scheme to the demos for low contrast projectors used in classroom environments. The [mirror](./mirror/README.md) contained in this repository is an indexed copy of the materials originally hosted at [http://nsfsecurity.pr.erau.edu](http://nsfsecurity.pr.erau.edu) before the subdomain became inaccessible.

## Module Overview

> This module provides an overview and interactive treatment of the buffer overflow module specifically directed toward educators and students, but also accessible to managers, journalists, and technology analysts.
> 
> The heart of the module is an interactive demonstration of how a few kinds of buffer overflow occur. As you work your way through the interaction, you'll see the inner workings of a simple program with input coming from an outside attacker.
> 
> The module also provides economic background, leads to current defensive techniques, and instructions for its use. The module may be used stand-alone by different types of users or its core demonstration may be used in a classroom setting by an instructor. We call this interactive software a "module" because it's intended to be used as a small part of a course, a software engineer's continuing education, or a non-engineer's introduction to the topic.

Source: [http://nsfsecurity.pr.erau.edu/modules.html](http://nsfsecurity.pr.erau.edu/modules.html)

## Changes to Module
The original demos were created as Java Applets to be viewed through a web browser by educators and students.  At the time this project was originally created deploying the demos as a Java web applet was a simple distribution method, but this is now no longer the case.  Security vulnerabilities involving Java applets were abused so widely that most modern browsers have completely disabled support for Java applets, making it extremely difficult to run the original demos.  This repository contains minor changes to the source to redeploy the demos as a Java application that can be downloaded and run locally on any modern operating system. Additionally, an alternative color scheme was defined to make presentations on low contrast projector's easier to see.

This repository has made the following changes to the original demo source code.

## Change Log

**[Release 4](https://github.com/benjholla/bomod/releases/tag/bomod_r4)**

1. Made code and memory display text bold
2. Added [mirror](./mirror/README.md) of original materials now that the materials are no longer accessible on original host

**[Release 3](https://github.com/benjholla/bomod/releases/tag/bomod_r3)**

1. Parameterized more display settings
2. Added a presentation mode option since the default setting was hard to see on some low contrast projectors (each demo now prompts the user to select between presentation or classic color scheme modes)
3. Bug fix for display glitch on resetting demos

**[Release 2](https://github.com/benjholla/bomod/releases/tag/bomod_r2)**

1. Refined example code to use standard variable/function naming conventions
2. Factored out large amounts of duplicate code into common parent class, bug fixes, style changes, etc.
3. Added capability to override code segment coloring in application wrapper while maintaining applet level features
4. Display fixes for different operating systems

**[Release 1](https://github.com/benjholla/bomod/releases/tag/bomod_r1)**

1. Created an Eclipse project and added source files
2. Added application wrappers around each demo Applet
3. Reorganized source into appropriate packages
4. Replaced deprecated methods with modern equivalents
5. Formatted source code
6. Fixed all warnings
7. Minor display tweaks in demo graphics

## Running Demos
This repository contains a `demos` folder with an executable JAR file for each demo (released previously as a Java web applet). To run a demo simply double click on the corresponding JAR file or invoke it directly from the command line (example: `java -jar SmasherDemo.jar`). At the prompt select either the classic or presentation mode color scheme.

### Classic Color Scheme

This classic mode color scheme is a slightly modified color scheme corresponding to the original release.

![Smasher Demo Screenshot](screenshot_classic_mode.png)

### Presentation Mode Color Color Scheme

The presentation mode color scheme is a color scheme that should lend itself better to presentations on low contrast projectors typically used in a classroom setting.

![Smasher Demo Screenshot](screenshot_presentation_mode.png)

## Demo Descriptions
The following descriptions of each demo are taken from [http://nsfsecurity.pr.erau.edu/bom/](http://nsfsecurity.pr.erau.edu/bom/).  Instructors should read the [Instructors' Guide for Demonstrating Buffer Overflows](./mirror/instructor_guide.md) for additional details.

1. **Jumps**: Shows how stacks are used to keep track of subroutine calls.
2. **Stacks**: An introduction to the way languages like C use stack frames to store local variables, pass variables from function to function by value and by reference, and also return control to the calling subroutine when the called subroutine exits. 
3. **Spock**: Demonstrates what is commonly called a "variable attack" buffer overflow, where the target is data. 
4. **Smasher**: Demonstrates a "stack attack," more commonly referred to as "stack smashing." 
5. **StackGuard**: This demo shows how the StackGuard compiler can help prevent "stack attacks." 

## Simulator Memory Overview
- There are 256 bytes of memory.  Memory is laid out left to right and top to bottom (just like a book).  The first byte of memory is at address 0x00, the second byte is at address 0x01, and the last byte is at address 0xFF.
- A `*` indicates the current position of the program counter (the current instruction to be executed).
- A `X` indicates where a subroutine was called and where the program pointer will return to after the subroutine is finished executing.
- The color of the C code for each subroutine matches the color of the corresponding subroutine memory location.
- A `$` indicates a return pointer to the subroutine with the same color as the `$` address.
- A `?` indicates a stack canary.  If the value of a canary changes, then the stack guard check will fail.
- For additional details see the [Demo Interaction Guide](./mirror/HowToUseApplet.pdf).
