# CBSE_Spring_VirtualStore

## JDK version
The application must be able to function as intended in JDK 8

## Dev Environment Setup
### Intellij
1. Go to `File > Project Structure` 
2. All subsequent actions are perfomed in the pop up appeared
3. go to `project` tab
    - configure `Project SDK` as 1.8
    - configure `Project Language level` as 8
4. go to `modules` tab
    - select the `src` folder, mark it as `Sources` (blue in color)
5. go to `modules` tab -> `dependencies` tab
    - click the `+` (add icon), then select `Jars or directories`
    - include 5 Spring jars file
        - commons-logging-1.2.1.1.jar
        - spring-beans-5.2.9.RELEASE.jar
        - spring-context-5.2.9.RELEASE.jar
        - spring-core-5.2.9.RELEASE.jar
        - spring-expression-5.2.9.RELEASE.jar
    - The dependencies can be download from following website
        - https://repo.spring.io/release/org/springframework/spring/5.2.9.RELEASE/
        - http://commons.apache.org/proper/commons-logging/download_logging.cgi
6. to run the application, go to `UIBean.java` and run the `main` method

### Others
1. just set JDK version to JDK 8 
2. remember to import the 5 Spring jars as the external libraries
