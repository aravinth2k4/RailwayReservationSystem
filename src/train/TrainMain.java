package train;

import java.util.Scanner;

//date 28 march 2025 (5 days)
public class TrainMain {
	public static void main(String args[]) {
		Train train = new Train();
		Scanner sc = new Scanner(System.in);
		System.out.println("   🚆 !!! Welcome to Railway Reservation System !!! 🚆");
		try {
			while (true) {
				System.out.println("=====================================");
				System.out.println("|        🎟️ Ticket Booking Menu      |");
				System.out.println("=====================================");
				System.out.printf("| %-30s | %s |\n", "📌 1. Book a Ticket", "➜");
				System.out.printf("| %-30s | %s |\n", "📌 2. Cancel a Ticket", "➜");
				System.out.printf("| %-30s | %s |\n", "📌 3. View Booked Tickets", "➜");
				System.out.printf("| %-30s | %s |\n", "📌 4. View a Specific Ticket", "➜");
				System.out.printf("| %-30s | %s |\n", "📌 5. View Available Tickets", "➜");
				System.out.printf("| %-30s | %s |\n", "📌 6. Exit", "🚪");
				System.out.println("=====================================");
				System.out.println("👉 Please enter your choice: ");
				int i = sc.nextInt();
				if (i == 1) {
					train.booking();
				} else if (i == 2) {
					train.cancel();
				} else if (i == 3) {
					train.bookedSeats();
				} else if (i == 4) {
					train.specificSeat();
				} else if (i == 5) {
					train.availableSeat();
				} else
					break;
			}
		}
		//finally is used here because if an exception occured in the top 
		finally {
			sc.close();
		}
	}
}
