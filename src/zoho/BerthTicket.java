package zoho;

import train.Ticket;

public class BerthTicket extends Ticket{
	private String childName;
	private int childAge;
	private boolean hasChild;
	
	public BerthTicket(String name, int age, String gender, String bookingStatus) {
		super(name, age, gender, bookingStatus);
	}

}
