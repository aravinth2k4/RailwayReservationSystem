package zoho;
import java.util.Scanner;
public class Practice {
	public static void main(String[] args) {
		//look and Say Pattern
		Scanner sc=new Scanner(System.in);
		int n=5;
		for (int i=0;i<n;i++) {
			System.out.print(i+1+" ");
		}
		System.out.println();
		for(int i=0;i<n-1;i++) {
			int value=n*(i+2);
			int temp=value;
			for (int j=0;j<n;j++) {
				System.out.print(temp+" ");
				temp--;
			}
			System.out.println();
		}
	}
}