javac -d bin hello/Hello.java
jar cfm hello.jar MANIFEST.MF -C bin .
java -jar hello.jar
