package zoho;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Pattern {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=5;
		int space=0;
		for (int i=n;i>=0;i--) {
			for (int k=0;k<space;k++) {
				System.out.print(" ");
			}
			space++;
			for (int j=0;j<i;j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
}
