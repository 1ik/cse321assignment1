package server;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		System.out.println("choose ur option");

		System.out.println("1 - iterative server ");
		System.out.println("2 - concurrent server");
		System.out.println("3 - exit");

		Scanner sc = new Scanner(System.in);

		int choice = sc.nextInt();
		sc.nextLine();

		System.out.println("your choice : " + choice);

		try {

			switch (choice) {
			case 1:
				new server(7777).start();
			case 2:
				new server_concurrent(7777).start();
			case 3:
				System.out.println("goodbye");
			}

		} catch (Exception e) {

		}
	}
}
