# CST Option SpringBoot Web Guide

The CST program will incorporate a web application to help automate and ease the process of assigning students into their preferred Options using Java Enterprise on top of the Spring Boot Baeldung framework. This project will help reduce the time and effort in the CST department when allocating students to Options. The final deliverable will be used by BCIT’s CST Faculty such as Program Head or Program Assistant. The project completion date is December 15, 2017.

## Getting Started

THe JavaRepo is our program side of the project and runs algorithm to place student according to their GPA and priority levels. Keep on reading on instructions to get this JavaRepo up and running!

### Prerequisites

What things you need to install the software and how to install them


* A Java IDE that is able to use Java. We recommend [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows).

* [Java JDK and JRE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  downloaded. (Remember which folder as we will be using it later on)


### Installing

Here are the steps to get a development environment working


Step 1 - Opening and Configuring IDE

```
1. Open IntelliJ (Or any IDE environment)

2. Click on Import Project (Will do configuration later on if any pops up)

3. Find the SpringBoot ->CSTOptions folder from saved destination

4. Click on the CSTOptionsWebApp folder inside CSTOptionsWebApp folder and click OK

5. Select "Import project from external model"

6. Click on Maven and click NEXT

7. Accept default parameters and click NEXT -twice

8. Name the project the default and click FINISH

5. Click File -> Project Structure -> Project (Underneath Project Settings)

6. Press Edit/Add below Project SDK

7. Search for the Java JDK folder previously downloaded and click OK


```

Step 2 - Running the Web Server

```
1. Find the CSTOptionsWebApp folder located on the left side and expand it 

2. Click src -> Main -> Java -> cstOptions

3. Open the Application java class and press the green run button located on the top right of the screen

4. Wait for SpringBoot to load for around a minute

5. Go to your favourite web browser and enter localhost:8082 on the address bar

```

Congrats the program should be running without any error!


## HELP

**Troubleshooting**

```
1.404 ERROR MESSAGE 
Check the url to make sure it is correct 

2. “File name not valid, please select a .csv file to upload” Error Message
	Make sure the filename is correct and extension is in .csv format
    
3. Forgot username or password 
	Click the forgot Username/password link
    
4. Program selection not present 
	Please contact program administrator for any changes or updates
    
5. Springboot crashes
	Check for error message and try rebooting your IDE
    
6. Student not removed from list after user input name
	The spelling is case and letter sensitive, so it will need to be their exact name 
    
7. Student not added to list after user input name
There is a validation check that runs before user input their name where the system will only add names into the database if the student exists in the student list. (Case and letter sensitive)

```


## Tests Done

*JUnit - Test File located in JavaRepo -> Objects -> Test



## Deployment Goal

**Deployed on BCIT's System/Server used for Administrators for next year students batch of students**


## Contribution

Please read [CONTRIBUTING](https://github.com/ACIT-3900/SpringBoot/graphs/contributors) 


## Project Period

**September 2017 - December 2017**


## Authors

* **RT,MB,ML,DC,GT,AA** 

See also the list of [contributors](https://github.com/ACIT-3900/SpringBoot/graphs/contributors) who participated in this project.


## Acknowledgments

* BCIT CST Program Head
* BCIT CIT Program Head

