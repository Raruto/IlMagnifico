package clients;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	private final int PORT1 = 8235;
	private final int PORT2 = 8236;
	private final String serverAddress = "localhost";
	private String clientName;

	private Socket socket;
	private int serverPort;

	private BufferedReader in;
	private PrintWriter out;

	private Scanner scanner;

	public Client(int clientNumber) {
		if (clientNumber == 1) {
			this.serverPort = this.PORT1;
			this.clientName = "Client1";
		} else if (clientNumber == 2) {
			this.serverPort = this.PORT2;
			this.clientName = "Client2";
		}

		this.initializeConnection();

		this.in = null;
		this.out = null;
		this.scanner = null;
	}

	public void start() {
		try {
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			this.scanner = new Scanner(System.in);

			this.manageCommunication();

		} catch (IOException ioe) {
			System.out.println("Exception during communication. Server probably closed connection.");
		} finally {
			this.terminateConnection();
		}

	}

	private void manageCommunication() throws IOException {
		while (true) {
			String message = this.in.readLine();
			System.out.println(message);

			if (message.equals("attendi")) {
				continue;
			} else if (message.equals("richiedo nome")) {
				this.out.println(this.clientName);
				this.out.flush();
			} else if (message.equals("ascissa e ordinata")) {
				this.out.println(this.scanner.nextInt());
				this.out.flush();

				this.out.println(this.scanner.nextInt());
				this.out.flush();
			} else if (message.equals("direzione")) {
				if(this.scanner.hasNext()){this.scanner.next();}
				out.println(this.scanner.nextLine());
				// this.out.println("su");
				this.out.flush();
			}

			else {
				this.out.println(this.scanner.nextLine());
				this.out.flush();
			}
		}
	}

	private int initializeConnection() {
		this.socket = null;
		try {
			this.socket = new Socket(this.serverAddress, this.serverPort);
		} catch (UnknownHostException uhe) {
			System.out.println("Unknown Host :" + this.serverAddress);
			this.socket = null;
		} catch (IOException ioe) {
			System.out.println("Cant connect to server. Make sure it is running.");
			this.socket = null;
		}

		if (this.socket == null)
			System.exit(-1);

		return 0;
	}

	private int terminateConnection() {
		try {
			this.out.close();
			this.in.close();
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		return 0;
	}
}