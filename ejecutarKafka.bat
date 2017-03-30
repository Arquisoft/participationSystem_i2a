cd D:\Programas\Programming\kafka_2.11-0.10.2.0
start bin\windows\zookeeper-server-start.bat config\zookeeper.properties
timeout /T 5
start bin\windows\kafka-server-start.bat config\server.properties
