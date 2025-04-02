package zoho;
import java.io.*;
import java.util.ArrayList;
public class New {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		// TODO Auto-generated method stub
		String a[]=br.readLine().trim().split(" ");
		int n=a.length;
		int arr[]=new int[n];
		for (int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(a[i]);
		}
		int leader=Integer.MIN_VALUE;
		ArrayList<Integer> newarr=new ArrayList<>();
		for (int i=n-1;i>=0;i--) {
			if (arr[i] > leader) {
				leader=arr[i];
				newarr.add(arr[i]);
			}
		}
		for (int i:newarr) {
			System.out.println(i);
		}
		
		
	}
}
