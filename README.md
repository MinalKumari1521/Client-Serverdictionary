# Client-Serverdictionary
# Multithreaded Dictionary Server Implemented through Socket Programming in Java
This repository contains the code of the Java based Multithreaded Dictionary Server-Client Application. This is implemented through Socket Programming. Swing is used to implement the GUI for the server, client and the dictionary.

# Context

The server, being multithreaded, can support multiple concurrent clients and is able to handle multiple requests at the same time. A GUI based client application is also provided which can connect with the server and provide some operations. Server supports multiple operations like searching words, adding words and definitions of the words, updating word’s definition and deletion of words and their meanings. The server is designed in a way to handle same requests from different clients at the same time without raising clashes. The server and client are both equipped with any failures that may happen during the course of their operation. A custom protocol has also been setup to establish communication between the client and server.

# Components

My project has three components. 
1.	Multithreaded Dictionary Server Application (GUI) that provides multithreading per connection (threads per connection)
2.	Dictionary Client Application (GUI)
3.	Dictionary (File and GUI).

GUI based Server Application first launches the Server’s GUI window. That window asks for the port to start the server on. Once it receives the port, it starts the server and launches a main thread on which the server listens. This means that the server application can be extended to include multiple servers features to launch different servers on different ports at the same time from the same GUI.  That main thread listens for connections. Once it receives a connection request from a client, it processes that connection request and launches a child thread that is dedicated for that connection. The client then can perform different operations which are. 
1.	Search the word
2.	Add a new word and its meaning to the dictionary 
3.	Add a new meaning to a word that is already available in the dictionary. 
4.	Update the meanings of the word. 
5.	Delete the word.
Each operation is followed by saving the changes to the file. This is done so that every client always has the most up-to-date information even when that information that is just recently added or updated. The protocol of communication is implemented using Strings. All the operations and the data are passed through strings to the server and the server sends the status of the operation and the result through the strings as well. The operation and the data are sent as a single string to eliminate the mismatch of operations and data. Both, server and client, are programmed to segregate the control part and data part from the strings. Finally, when the clients want to disconnect, it can just close its application which lets the server know that client has closed the connection, so server also closes the connections from its side and deletes the thread that it had associated with the client. 
