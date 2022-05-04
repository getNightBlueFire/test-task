# test task

Goal
The goal of this exercise is to create a console application to solve the problem described in the next
section and to do it in a way that shows understanding of programming concepts and structures. Console
application must take one argument — the path to input file and must generate “output.txt” as result.
Source code must be available on any public git repository and it must include valid configuration for some
common build system like Maven or MSBuild.
Rules
Following rules are in effect during the exercise:
o You can use whatever reference material you wish (book, web, …).
o You may use standard libraries, but no other advanced frameworks.
o If you are stuck or you have questions, just ask them.
o You may adjust the input file for testing other cases and include them into the solution with JUnit or
NUnit.
Problem
Background
Bloggersville is served by two bus companies: Posh Bus Company and Grotty Bus Company. Both
companies operate a service from the airport to the central bus stop.
The two companies have decided to produce a joint bus timetable. However, bus travelers find it difficult
to use the timetable because of following reasons:
1. It is difficult to search faster buses in the timetable.
2. Some of the buses run faster than others. For a frequent bus traveler it is better to miss an earlier bus
   in order to catch a faster bus which departs later but reaches its destination sooner.
3. The entries in the timetable are not necessarily in order of departure time.
   Description
   Given the information in the joint timetable, write a program to produce modified timetable, satisfying the
   following requirements:
4. Entries in timetable are separated in two sections by bus company name. In each section entries are in
   order of departure time.
5. Any service longer than an hour shall not be included.
6. Only efficient services shall be added to the timetable. A service is considered efficient if there are no
   other services that are more efficient compared to it. A service is considered more efficient compared to
   the other one:
   o If it starts at the same time and reaches earlier, or
   o If it starts later and reaches at the same time, or
   o If it starts later and reaches earlier.
7. If both companies offer a service having the same departure and arrival times then always choose Posh
   Bus Company over Grotty Bus Company, since Grotty Bus Company busses are not as comfortable as
   those of Posh Bus Company

Input format
The input file has the following format:
<service>
<service> …
<service><EOF>
Each <service> record is on a separate line and will consist of:
o The character string ‘Grotty’ or ‘Posh’ to indicate which company is running the service.
o A space
o The departure time in 24 hours format, represented by 5 characters as ‘HH:MM’
o A space
o The arrival time in 24 hours format, represented by 5 characters as ‘HH:MM’
Consider that time in travel for each service is at least one minute and not longer than 24 hours.
Example of a <service>:
Posh 10:15 11:10
Output format
The services in output timetable shall be in the same format as in the input timetable, with the Posh Bus
Company section first followed by a blank line and the Grotty Bus Company section:
<posh_service>
<posh_service> …
<posh_service>
<blank_line>
<grotty_service>
<grotty_service> …
<grotty_service><EOF>
Example input and output
Given the following data:
Posh 10:15 11:10
Posh 10:10 11:00
Grotty 10:10 11:00
Grotty 16:30 18:45
Posh 12:05 12:30
Grotty 12:30 13:25
Grotty 12:45 13:25
Posh 17:25 18:01
Your program shall produce:
Posh 10:10 11:00
Posh 10:15 11:10
Posh 12:05 12:30
Posh 17:25 18:01
Grotty 12:45 13:25

