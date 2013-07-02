package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	public static void main(String args []){

		Socket clientsocket = null;
		BufferedReader in = null;
		PrintStream out = null;
		Scanner input = new Scanner(System.in);
		
		//Begging for server name and port number to client.
		System.out.println("enter server name : ");
		String hostname = input.nextLine();
		System.out.println("enter server port number : ");
		int port = input.nextInt();
		
		input.nextLine(); //sarwar sir taught it in cse110 while playing dota.
		
		try {
			//creating connection with server. 
			clientsocket = new Socket(hostname, port);
			
			//getting the outputstream for sending texts to server.
			out = new PrintStream(clientsocket.getOutputStream());
			//getting the inputstream for reading what server says to the client.
			in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(out != null && in != null && clientsocket != null){
			//if everything is okay..
			//keep on reading the stream from socket.
			String response = ""; // <- response of server.
			//sending the first request to server so that in the loop we can begin with reading.
			System.out.println("enter a starting text : ");
			out.println(input.nextLine());
			
			try {
				while((response = in.readLine()) != null){
					//if server sends something that makes sense. we print it.
					System.out.print("server says : ");
					System.out.println(response);
					//Requesting user to send a request to server again . FOR FREE ofcourse.
					System.out.println("please kindly enter a text, pleaaase : ");
					String line = input.nextLine();
					out.println(line);
					
					if(line.equalsIgnoreCase("end-of-session")){
						//client wanna leave.
						System.out.println("goodbye!!");
						in.close();
						out.close();
						clientsocket.close();
						System.exit(1);
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
