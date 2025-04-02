package train;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Train {
	Scanner sc = new Scanner(System.in);
	final int totalTickets = 3;
	final int totalRac = 3;
	final int totalWl = 3;

	// Logic Starts
	static int availableConfirmTickets = 3;
	static int availableRacTickets = 3;
	static int availableWlTickets = 3;
	// Collections
	// confirmed tickets will be added here
	static LinkedList<Ticket> bookedTickets = new LinkedList<>();
	static Queue<Ticket> racPersons = new LinkedList<>();
	static Queue<Ticket> wlPersons = new LinkedList<>();
	static HashMap<Integer, Integer> bookedSeats = new HashMap<>();
	static HashMap<Integer, Integer> bookedRac = new HashMap<>();
	static HashMap<Integer, Integer> bookedWl = new HashMap<>();

	// adding seats in the hash map
	public Train() {
		for (int i = 1; i <= totalTickets; i++) {
			bookedSeats.put(i, 0);
		}
		for (int i = 1; i <= totalRac; i++) {
			bookedRac.put(i, 0);
		}
		for (int i = 1; i <= totalWl; i++) {
			bookedWl.put(i, 0);
		}
	}

	// Booking
	void booking() {
		// pending for age >5
		if (availableWlTickets == 0) {
			System.out.println("No Tickets Available");
			return;
		}
		System.out.println("Enter Your Name");
		String name = sc.nextLine();
		System.out.println("Enter Your Age");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Your Gender (M/F)");
		String gender = sc.nextLine().toUpperCase();
		// because child below age 5
		if (age < 5) {
			Ticket ticket = new Ticket(name, age, gender, "Child");
			bookedTickets.add(ticket);
			System.out.println("Ticket booked successfully. No seat has been allocated");
			return;
		}
		int childStatus = 0;
		String childName = null;
		int childAge = 0;
		if (gender.equals("F") && age > 20) {
			System.out.println("Do you have a child below the age of 5? (1-yes/2-no)");
			childStatus = sc.nextInt();
			sc.nextLine();
			if (childStatus == 1) {
				System.out.println("Enter your child name:");
				childName = sc.nextLine();
				System.out.println("Enter your child age:");
				childAge = sc.nextInt();
			}
		}
		// check for seats in available ticket
		// if this becomes true means ticket is booked either in confirm,RAC,waitingList
		boolean seatStatus = false;
		if (availableConfirmTickets > 0) {
			// Logic for preference seats
			int choice;
			if (childStatus == 1 || age > 60) {
				// pending
				choice = 1;
			} else {
				System.out.println("Tickets Available !!!");
				System.out.println("Do you have a berth preference? (1-yes/2-no)");
				choice = sc.nextInt();
				sc.nextLine();
			}
			if (choice == 1) {
				String seatPreference;
				if (childStatus == 1 || age > 60) {
					// pending
					seatPreference = "L";
				} else {
					System.out.println("Enter Your Seat Preference");
					seatPreference = sc.nextLine().toUpperCase();
				}
				while (!seatStatus) {
					// check for lower
					// here if L did'nt get seat ,in the next two for loops it will get seat ,if
					// seat is
					// available
					if (seatPreference.equals("L")) {
						for (int i = 1; i <= totalTickets; i = i + 3) {
							// the seat is available
							if (bookedSeats.get(i) == 0) {
								Ticket ticket = new Ticket(name, age, gender, i + "L");
								if (childStatus == 1) {
									ticket.setChildName(childName);
									ticket.setChildAge(childAge);
									ticket.setHasChild(true);
								}
								bookedTickets.add(ticket);
								seatStatus = true;
								bookedSeats.put(i, 1);
								--availableConfirmTickets;
								System.out.println("Ticket Booked SucccessFully " + "at " + i + "L");
								break;
							}
						}
					}
					if (seatStatus == true)
						return;
					// check for middle
					else if (seatPreference.equals("M") || seatPreference.equals("L") || childStatus == 1) {
						for (int i = 2; i <= totalTickets; i = i + 3) {
							// the seat is available
							if (bookedSeats.get(i) == 0) {
								Ticket ticket = new Ticket(name, age, gender, i + "M");
								if (childStatus == 1) {
									ticket.setChildName(childName);
									ticket.setChildAge(childAge);
									ticket.setHasChild(true);
								}
								bookedTickets.add(ticket);
								seatStatus = true;
								bookedSeats.put(i, 1);
								--availableConfirmTickets;
								System.out.println("Ticket Booked SucccessFully at" + i + "M");
								break;
							}
						}
					}
					if (seatStatus == true)
						return;
					// check for upper
					// if U did'nt get this seat It should get M and L re check
					else if (seatPreference.equals("U") || seatPreference.equals("L") || seatPreference.equals("M")
							|| childStatus == 1) {
						for (int i = 3; i <= totalTickets; i = i + 3) {
							// the seat is available
							if (bookedSeats.get(i) == 0) {
								Ticket ticket = new Ticket(name, age, gender, i + "U");
								if (childStatus == 1) {
									ticket.setChildName(childName);
									ticket.setChildAge(childAge);
									ticket.setHasChild(true);
								}
								bookedTickets.add(ticket);
								seatStatus = true;
								bookedSeats.put(i, 1);
								--availableConfirmTickets;
								System.out.println("Ticket Booked SucccessFully at" + i + "U");
								break;
							}
						}
					}
					if (seatStatus == true)
						return;
					// if no preference seat is available this select any seat by some condition
					// if M did'nt get seat ,re check for L
					if (seatPreference.equals("M")) {
						for (int i = 1; i <= totalTickets; i = i + 3) {
							// the seat is available
							if (bookedSeats.get(i) == 0) {
								Ticket ticket = new Ticket(name, age, gender, i + "L");
								if (childStatus == 1) {
									ticket.setChildName(childName);
									ticket.setChildAge(childAge);
									ticket.setHasChild(true);
								}
								bookedTickets.add(ticket);
								seatStatus = true;
								bookedSeats.put(i, 1);
								--availableConfirmTickets;
								System.out.println("Your Prefered Seat is Not Available !!");
								System.out.println("Ticket Booked SucccessFully at" + i + "L");
								break;
							}
						}
					}
					if (seatStatus == true)
						return;
					// re check for M and L
					if (seatPreference.equals("U")) {
						// first check for M
						for (int i = 2; i <= totalTickets; i = i + 3) {
							// the seat is available
							if (bookedSeats.get(i) == 0) {
								Ticket ticket = new Ticket(name, age, gender, i + "M");
								if (childStatus == 1) {
									ticket.setChildName(childName);
									ticket.setChildAge(childAge);
									ticket.setHasChild(true);
								}
								bookedTickets.add(ticket);
								seatStatus = true;
								bookedSeats.put(i, 1);
								--availableConfirmTickets;
								System.out.println("Your Prefered Seat is Not Available !!");
								System.out.println("Ticket Booked SucccessFully at" + i + "M");
								break;
							}
						}
						if (seatStatus == true)
							return;
						// then check for L
						for (int i = 1; i <= totalTickets; i = i + 3) {
							// the seat is available
							if (bookedSeats.get(i) == 0) {
								Ticket ticket = new Ticket(name, age, gender, i + "L");
								if (childStatus == 1) {
									ticket.setChildName(childName);
									ticket.setChildAge(childAge);
									ticket.setHasChild(true);
								}
								bookedTickets.add(ticket);
								seatStatus = true;
								bookedSeats.put(i, 1);
								--availableConfirmTickets;
								System.out.println("Your Prefered Seat is Not Available !!");
								System.out.println("Ticket Booked SucccessFully at" + i + "L");
								break;
							}
						}
					}
					break;
				}
			}
			// searching seat for the non preference people
			else if (choice == 2) {
				// search for U and M first and then L;
				// checking U
				for (int i = 3; i <= totalTickets; i = i + 3) {
					// the seat is available
					if (bookedSeats.get(i) == 0) {
						Ticket ticket = new Ticket(name, age, gender, i + "U");
						bookedTickets.add(ticket);
						seatStatus = true;
						bookedSeats.put(i, 1);
						--availableConfirmTickets;
						System.out.println("Ticket Booked SucccessFully at" + i + "U");
						break;
					}
				}
				if (seatStatus == false) {
					// checking M
					for (int i = 2; i <= totalTickets; i = i + 3) {
						// the seat is available
						if (bookedSeats.get(i) == 0) {
							Ticket ticket = new Ticket(name, age, gender, i + "M");
							bookedTickets.add(ticket);
							seatStatus = true;
							bookedSeats.put(i, 1);
							--availableConfirmTickets;
							System.out.println("Ticket Booked SucccessFully at" + i + "M");
							break;
						}
					}
				}
				if (seatStatus == false) {
					// checking for L
					for (int i = 1; i <= totalTickets; i = i + 3) {
						// the seat is available
						if (bookedSeats.get(i) == 0) {
							Ticket ticket = new Ticket(name, age, gender, i + "L");
							bookedTickets.add(ticket);
							seatStatus = true;
							bookedSeats.put(i, 1);
							--availableConfirmTickets;
							System.out.println("Ticket Booked SucccessFully at" + i + "L");
							break;
						}
					}
				}
			}
			if (seatStatus == true)
				return;
		}
		// booking tickets for RAC
		else if (availableRacTickets > 0) {
			if (childStatus == 1 || age > 60) {
				System.out.println("No available seats. Do you want to book a seat in RAC? (1-yes/2-no)");
				int option = sc.nextInt();
				sc.nextLine();
				if (option == 2) {
					System.out.println("Thank you for choosing IRCTC. No tickets have been booked.");
					return;
				}
			}
			for (int i = 1; i <= totalRac; i++) {
				if (bookedRac.get(i) == 0) {
					bookedRac.put(i, 1);
					Ticket ticket = new Ticket(name, age, gender, "RAC" + "" + i);
					if (childStatus == 1) {
						ticket.setChildName(childName);
						ticket.setChildAge(childAge);
						ticket.setHasChild(true);
					}
					racPersons.add(ticket);
					seatStatus = true;
					System.out.println("Ticket Booked SucccessFully in RAC" + i);
					--availableRacTickets;
					break;
				}
			}
			if (seatStatus = true)
				return;
		} else if (availableWlTickets > 0) {
			if (childStatus == 1 || age > 60) {
				System.out.println("No available seats. Do you want to book a seat in Waiting List? (1-yes/2-no)");
				int option = sc.nextInt();
				sc.nextLine();
				if (option == 2) {
					System.out.println("Thank you for choosing IRCTC. No tickets have been booked.");
					return;
				}
			}
			for (int i = 1; i <= totalWl; i++) {
				if (bookedWl.get(i) == 0) {
					bookedWl.put(i, 1);
					Ticket ticket = new Ticket(name, age, gender, "WL" + "" + i);
					if (childStatus == 1) {
						ticket.setChildName(childName);
						ticket.setChildAge(childAge);
						ticket.setHasChild(true);
					}
					wlPersons.add(ticket);
					seatStatus = true;
					System.out.println("Ticket Booked SucccessFully in WL" + i);
					availableWlTickets--;
					break;
				}
			}
		}
	}

	void cancel() {
		System.out.println("Enter the PNR number to cancel the ticket");
		int pnr = sc.nextInt();
		// finding whether this PNR is available?
		Ticket cancelTicket = null;
		boolean pnrAvailable = false;
		String currentSeat = null;
		// checking where the pnr is available? may be the first rac may be now in the
		// confirmed status
		int pnrCheck = 0;
		for (Ticket i : bookedTickets) {
			if (i.getPnrNo() == pnr) {
				pnrCheck = 1; // 1 means confirm
				cancelTicket = i;
				break;
			}
		}
		for (Ticket i : racPersons) {
			if (i.getPnrNo() == pnr) {
				pnrCheck = 2; // 2 means rac
				break;
			}
		}
		for (Ticket i : wlPersons) {
			if (i.getPnrNo() == pnr) {
				pnrCheck = 3; // 3 means wl
				break;
			}
		}
		if (pnrCheck == 0) {
			System.out.println("Entered Pnr is not in the DB");
			return;
		}
		// now going to delete and change
		if (pnrCheck == 1) {
			// check for confirmed seats
			if (racPersons.size() != 0) {
				int racSize = racPersons.size();
				Ticket racPerson = racPersons.poll();
				currentSeat = racPerson.getBookingStatus(); // rac1
				racPerson.setBookingStatus(cancelTicket.getBookingStatus());
				bookedTickets.remove(cancelTicket);
				bookedTickets.add(racPerson);
				for (Ticket i : racPersons) {
					// change all the seats
					String temp = i.getBookingStatus();
					i.setBookingStatus(currentSeat);
					currentSeat = temp;
				}
				if (wlPersons.size() != 0) {
					int wlSize = wlPersons.size();
					Ticket wlPerson = wlPersons.poll();
					String wlCurrentSeat = wlPerson.getBookingStatus();
					for (Ticket i : wlPersons) {
						// change all the seats
						String temp = i.getBookingStatus();
						i.setBookingStatus(wlCurrentSeat);
						wlCurrentSeat = temp;
					}
					wlPerson.setBookingStatus("RAC " + racSize);
					racPersons.add(wlPerson);
					bookedWl.put(wlSize, 0);
					availableWlTickets++;
				} else {
					bookedRac.put(racSize, 0);
					availableRacTickets++;
				}
			} else {
				bookedTickets.remove(cancelTicket);
				availableConfirmTickets++;
			}
			System.out.println("Ticket Cancelled SuccessFully");

		}

		else if (pnrCheck == 2) {
			// check for rac
			int racSize = racPersons.size();
			for (Ticket i : racPersons) {
				if (!pnrAvailable) {
					if (i.getPnrNo() == pnr) {
						cancelTicket = i;
						pnrAvailable = true;
						currentSeat = cancelTicket.getBookingStatus();
					}
				} else if (pnrAvailable) {
					String temp = i.getBookingStatus();
					i.setBookingStatus(currentSeat);
					currentSeat = temp;
				}
			}
			racPersons.remove(cancelTicket);
			if (wlPersons.size() != 0) {
				int wlSize = wlPersons.size();
				Ticket wlPerson = wlPersons.poll();
				// changing the seat of first wl to last rac
				String wlCurrentSeat = wlPerson.getBookingStatus();
				wlPerson.setBookingStatus("Rac " + racSize);
				for (Ticket i : wlPersons) {
					// change all the seats
					String temp = i.getBookingStatus();
					i.setBookingStatus(wlCurrentSeat);
					wlCurrentSeat = temp;
				}
				racPersons.add(wlPerson);
				bookedWl.put(wlSize, 0);
				availableWlTickets++;
			} else {
				bookedRac.put(racSize, 0);
				availableRacTickets++;
			}
			System.out.println("Ticket Cancelled SuccessFully");

		} else if (pnrCheck == 3) {
			// check for waiting list
			int wlSize = wlPersons.size();
			for (Ticket i : wlPersons) {
				if (!pnrAvailable) {
					if (i.getPnrNo() == pnr) {
						cancelTicket = i;
						pnrAvailable = true;
						currentSeat = cancelTicket.getBookingStatus();
					}
				} else if (pnrAvailable) {
					String temp = i.getBookingStatus();
					i.setBookingStatus(currentSeat);
					currentSeat = temp;
				}
			}
			wlPersons.remove(cancelTicket);
			bookedWl.put(wlSize, 0);
			availableWlTickets++;
			System.out.println("Ticket Cancelled SuccessFully");

		}
		// checking is there any person in the Rac or Wl
	}

	void bookedSeats() {
		for (Ticket i : bookedTickets) {
			System.out.println("===========================================");
			System.out.println("           🎟️ Booked Ticket Details         ");
			System.out.println("===========================================");
			System.out.println("PNR Number               : " + i.getPnrNo());
			System.out.println("Passenger Name           : " + i.getName());
			System.out.println("Passenger Age            : " + i.getAge());
			System.out.println("Passenger Gender         : " + i.getGender());
			System.out.println("Passenger BookingStatus  : " + i.getBookingStatus());
			if (i.isHasChild()) {
				System.out.println("Child Name               : " + i.getChildName());
				System.out.println("Child Age                : " + i.getChildAge());
			}
			System.out.println("===========================================");
		}

		for (Ticket i : racPersons) {
			System.out.println("===========================================");
			System.out.println("             🚉 RAC Ticket Details         ");
			System.out.println("===========================================");
			System.out.println("PNR Number               : " + i.getPnrNo());
			System.out.println("Passenger Name           : " + i.getName());
			System.out.println("Passenger Age            : " + i.getAge());
			System.out.println("Passenger Gender         : " + i.getGender());
			System.out.println("Passenger BookingStatus  : " + i.getBookingStatus());
			if (i.isHasChild()) {
				System.out.println("Child Name               : " + i.getChildName());
				System.out.println("Child Age                : " + i.getChildAge());
			}
			System.out.println("===========================================");
		}

		for (Ticket i : wlPersons) {
			System.out.println("===========================================");
			System.out.println("           ⏳ Waiting List Details          ");
			System.out.println("===========================================");
			System.out.println("PNR Number               : " + i.getPnrNo());
			System.out.println("Passenger Name           : " + i.getName());
			System.out.println("Passenger Age            : " + i.getAge());
			System.out.println("Passenger Gender         : " + i.getGender());
			System.out.println("Passenger BookingStatus  : " + i.getBookingStatus());
			if (i.isHasChild()) {
				System.out.println("Child Name               : " + i.getChildName());
				System.out.println("Child Age                : " + i.getChildAge());
			}
			System.out.println("===========================================");
		}
	}

	void availableSeat() {
		// checking for available confirm seats
		int l = 0;
		int m = 0;
		int u = 0;
		int rac = 0;
		int wl = 0;
		for (int i = 1; i <= totalTickets; i++) {
			// seat available
			if (bookedSeats.get(i) == 0) {
				if (i % 3 == 2) {
					l++;
				} else if (i % 3 == 1) {
					m++;
				} else if (i % 3 == 0) {
					u++;
				}
			}
		}
		// rac
		for (int i = 1; i <= totalRac; i++) {
			// seat available
			if (bookedRac.get(i) == 0) {
				rac++;
			}
		}
		// wl
		for (int i = 1; i <= totalWl; i++) {
			// seat available
			if (bookedWl.get(i) == 0) {
				wl++;
			}
		}
		System.out.println("=====================================");
		System.out.println("|       🎟️ Ticket Availability       |");
		System.out.println("=====================================");
		System.out.printf("| %-20s | %3d |\n", "Lower Berth 🛏️", l);
		System.out.printf("| %-20s | %3d |\n", "Middle Berth 🛏️", m);
		System.out.printf("| %-20s | %3d |\n", "Upper Berth 🛏️", u);
		System.out.printf("| %-20s | %3d |\n", "RAC Seats 🔄", rac);
		System.out.printf("| %-20s | %3d |\n", "Waiting List ⏳", wl);
		System.out.println("=====================================");
	}

	void specificSeat() {
		System.out.println("=======================================");
		System.out.println("|           🔍 PNR Inquiry            |");
		System.out.println("=======================================");
		System.out.print("| 📌 Enter Your PNR Number: ");
		int pnr = sc.nextInt();
		for (Ticket i : bookedTickets) {
			if (pnr == i.getPnrNo()) {
				System.out.println("=======================================");
				System.out.println("|          🏷️ PNR Ticket Details      |");
				System.out.println("=======================================");
				System.out.printf("| %-25s : %s |\n", "🎫 PNR Number", i.getPnrNo());
				System.out.printf("| %-25s : %s |\n", "👤 Passenger Name", i.getName());
				System.out.printf("| %-25s : %d |\n", "🎂 Passenger Age", i.getAge());
				System.out.printf("| %-25s : %s |\n", "⚤ Passenger Gender", i.getGender());
				System.out.printf("| %-25s : %s |\n", "📌 Booking Status", i.getBookingStatus());

				if (i.isHasChild()) {
					System.out.printf("| %-25s : %s |\n", "👶 Child Name", i.getChildName());
					System.out.printf("| %-25s : %d |\n", "🎂 Child Age", i.getChildAge());
				}

				System.out.println("=======================================");

				return;
			} else {
				System.out.println("\n❌ No such PNR number found in the database.");
				System.out.println("=======================================");
			}
		}
		for (Ticket i : racPersons) {
			if (pnr == i.getPnrNo()) {
				System.out.println("=======================================");
				System.out.println("|          🏷️ PNR Ticket Details      |");
				System.out.println("=======================================");
				System.out.printf("| %-25s : %s |\n", "🎫 PNR Number", i.getPnrNo());
				System.out.printf("| %-25s : %s |\n", "👤 Passenger Name", i.getName());
				System.out.printf("| %-25s : %d |\n", "🎂 Passenger Age", i.getAge());
				System.out.printf("| %-25s : %s |\n", "⚤ Passenger Gender", i.getGender());
				System.out.printf("| %-25s : %s |\n", "📌 Booking Status", i.getBookingStatus());

				if (i.isHasChild()) {
					System.out.printf("| %-25s : %s |\n", "👶 Child Name", i.getChildName());
					System.out.printf("| %-25s : %d |\n", "🎂 Child Age", i.getChildAge());
				}

				System.out.println("=======================================");
				return;
			} else {
				System.out.println("\n❌ No such PNR number found in the database.");
				System.out.println("=======================================");
			}
		}
		for (Ticket i : wlPersons) {
			if (pnr == i.getPnrNo()) {
				System.out.println("=======================================");
				System.out.println("|          🏷️ PNR Ticket Details      |");
				System.out.println("=======================================");
				System.out.printf("| %-25s : %s |\n", "🎫 PNR Number", i.getPnrNo());
				System.out.printf("| %-25s : %s |\n", "👤 Passenger Name", i.getName());
				System.out.printf("| %-25s : %d |\n", "🎂 Passenger Age", i.getAge());
				System.out.printf("| %-25s : %s |\n", "⚤ Passenger Gender", i.getGender());
				System.out.printf("| %-25s : %s |\n", "📌 Booking Status", i.getBookingStatus());

				if (i.isHasChild()) {
					System.out.printf("| %-25s : %s |\n", "👶 Child Name", i.getChildName());
					System.out.printf("| %-25s : %d |\n", "🎂 Child Age", i.getChildAge());
				}

				System.out.println("=======================================");
				return;
			} else {
				System.out.println("\n❌ No such PNR number found in the database.");
				System.out.println("=======================================");
			}
		}
	}

}
