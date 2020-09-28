How to run

1. Import project to Eclipse/IntelliJ
2. Reconfigure JDK
+ IntelliJ: Project Settings -> Modules -> Modules SDK. Looks for JDK version
+ Eclipse: Right Click -> Build Path -> Configure Build Path -> Looks for JDK version
3. Add external lib
+ Go to https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.27.2.1/
+ Download sqlite-jdbc-3.27.2.1.jar
+ Add external lib (similar to step #2)
4. Edit config.properties
+ Edit connection.string to *.db <This is the physical sqlite database path>.
+ Edit student.file.path to students.txt <This is the student text file used to prelauch the database>.
+ max.student.threshold <Maximum number of students. This can be modified flexibly but effective only in console. UI was hardcoded with 5 screens>
+ max.personality.threshold <Maximum number of personalities. Same above>
+ Other alert properties
5. Run

***NOTE***
The project can be run through console directly.
However, extenal libs (sqlite-jdbc-3.27.2.1.jar) cannot be added this way. Java projects normally relies on Maven, Ant, Graddle or Docker to add the jar file. Therefore, you can just run it at your own risks.

1. Cd to the outer folder just before <this folder>/core/Main.java
2. javac core/Main.java
3. java core.Main

Only the UI and console text shall be lauched. No DB connection though.
