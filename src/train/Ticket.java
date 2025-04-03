package train;
public class Ticket {
	private static int increasingPnr =0;
	private int pnrNo=1;
	private String name;
	private int age;
	private String gender;
	private String bookingStatus;
	private String childName;
	private int childAge;
	private boolean hasChild;

	public Ticket(String name, int age, String gender, String bookingStatus) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.bookingStatus = bookingStatus;
		this.pnrNo=++increasingPnr;
	}

	// this is for ladies with child
	public boolean isHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public int getChildAge() {
		return childAge;
	}

	public void setChildAge(int childAge) {
		this.childAge = childAge;
	}

	// getter and setters
	public int getPnrNo() {
		return pnrNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
}
