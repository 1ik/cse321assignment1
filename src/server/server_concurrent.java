package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server_concurrent {
		//server has a ServerSocket;
		ServerSocket socketserver ;
		int clientid = 0;
		
		
		public server_concurrent(int port){
			try {
				socketserver = new ServerSocket(port); // <- creating server with the given port
				System.out.println("CSE321 ECHO server The strongest server in this world, has been created!!"); // <-success!!
				
			} catch (IOException e) {
				e.printStackTrace();
				System.err.printf("creating server with port %d failed", port);
				System.out.println();
			}
		}
		
		
		public void start() throws IOException{
			while(true){
				//waiting for clients.
				Socket clientConnection = socketserver.accept();
				
				//we got a client !! , give it a seperate thread.
				new Thread(new ServiceThread(++clientid, clientConnection)).start();
			}
			
		}
		
		class ServiceThread implements Runnable{
			Socket client;
			int clientId;
			public ServiceThread() {
			}
			
			public ServiceThread(int clientid, Socket socket){
				this.clientId = clientid;
				this.client = socket;
			}

			@Override
			public void run() {
				try{
					//introduce a bufferedreader with its inputstream to listen what client says.
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					
					//and to send him stuff.. catch his outputstream.
					PrintStream out = new PrintStream(client.getOutputStream());
					
					String request = "";
					//as long as there are stuffs to read we read it.
					while((request = in.readLine())  != null){
						//valid request gets printed.
						System.out.printf("received from client %d : %s", this.clientId , request);
						System.out.println();
						
						//give him the best gift sending back his own request unchanged !!
						out.println(request);
					}
					
					
				}catch(Exception e){
					
				}
			}
			
		}
		
}
