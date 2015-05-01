## Developed by @PLovababu
## About the Application.
This is basically to demo the Spring 3.x Embedded database feature with Hiberante ORM.
Here i have used H2 type inMemory database (Spring supports H2, HSQL, Derby)
## Tech Stack.
1. Spring 3.2.10 and Transaction.
2. Spring Embedded database , H2 Type.
3. Hibernate ORM 4.2.10-Final.
4. Lombok
5. Gradle build tool.
6. Used IntelliJ IDEA for development (Optional for you.)

## PreRequisites.
1. JAVA 1.8 required.
2. Gradle must be installed and set the GRADLE_HOME
   https://gradle.org/docs/current/userguide/installation.html

## How to RUN.
 get into the root directory.
 ./gradlew clean test
   --To run the test cases.
 ./gradlew clean build
   --To prepare the runnable jar. This task also executes the Test cases under test package.

 For eclipse lovers.
 ./gradlew eclipse
   --Creates the .project and .classpath files, so that you can import to eclipse.

##How to run from IDE.
 Open the file SampleAppTest.java and Right click run as JUnit application.
