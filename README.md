# Buffer Overflow Module (bomod)
This project is a mirror (with updates) of the [original Buffer Overflow Module](http://nsfsecurity.pr.erau.edu/bom/) created by [NSF Award No. 0113627](http://www.nsf.gov/awardsearch/showAward?AWD_ID=0113627).

## Module Overview

> This module provides an overview and interactive treatment of the buffer overflow module specifically directed toward educators and students, but also accessible to managers, journalists, and technology analysts.
> 
> The heart of the module is an interactive demonstration of how a few kinds of buffer overflow occur. As you work your way through the interaction, you'll see the inner workings of a simple program with input coming from an outside attacker.
> 
> The module also provides economic background, leads to current defensive techniques, and instructions for its use. The module may be used stand-alone by different types of users or its core demonstration may be used in a classroom setting by an instructor. We call this interactive software a "module" because it's intended to be used as a small part of a course, a software engineer's continuing education, or a non-engineer's introduction to the topic.

Source: [http://nsfsecurity.pr.erau.edu/modules.html](http://nsfsecurity.pr.erau.edu/modules.html)

## Changes to Module
The original demos were created as Java Applets to be viewed through a web browser by educators and students.  At the time this project was originally created deploying the demos as a Java web applet was a simple distribution method, but this is now no longer the case.  Security vulnerabilities involving Java applets were abused so widely that most modern browsers have completely disabled support for Java applets, making it extremely difficult to run the original demos.  This repository contains minor changes to the source to redeploy the demos as a Java application that can be downloaded and run locally on any modern operating system.

This repository has made the following changes to the original demo source code.

1. Created an Eclipse project and added source files
2. Added application wrappers around each demo Applet
3. Reorganized source into appropriate packages
4. Replaced deprecated methods with modern equivalents
5. Formatted source code
6. Fixed all warnings
