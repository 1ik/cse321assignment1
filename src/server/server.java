package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	// server has a ServerSocket;
	ServerSocket socketserver;

	public server(int port) {
		try {
			socketserver = new ServerSocket(port); // <- creating server with the given port
			System.out.println("CSE321 ECHO server The strongest server in this world, has been created!!"); // <-success!!
		} catch (IOException e) {
			e.printStackTrace();
			System.err.printf("creating server with port %d failed", port);
			System.out.println();
		}
	}

	public void start() throws IOException {
		while (true) {
			// waiting for clients.
			Socket clientConnection = socketserver.accept();
			// we got a client !! , let's serve !!
			// introduce a bufferedreader with its inputstream to listen what
			// client says.
			BufferedReader in = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));

			// and to send him stuff.. catch his outputstream.
			PrintStream out = new PrintStream(clientConnection.getOutputStream());

			String request = "";
			// as long as there are stuffs to read we read it.
			while ((request = in.readLine()) != null) {
				// valid request gets printed.
				System.out.println("Received : " + request);

				// give him the best gift sending back his own request unchanged
				// !!
				out.println(request);
			}
		}

	}
}
