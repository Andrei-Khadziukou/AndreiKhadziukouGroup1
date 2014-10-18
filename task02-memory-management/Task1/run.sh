#/bin/sh

javac -cp ".:../lib/*" MemoryEater.java &&
java -XX:SurvivorRatio=5 -Xss2m -Xmx1024m -cp ".:../lib/*" MemoryEater 
