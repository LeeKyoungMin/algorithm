import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			int input = sc.nextInt();
			arr[i] = input;
		}
		
		Arrays.sort(arr);
		
		int M = sc.nextInt();
		
		for(int i = 0; i < M; i++) {
			int isArr = sc.nextInt();
			System.out.println(binarySearch(arr, isArr));
		}
	}
	
	private static int binarySearch(int[] arr, int input) {
		
		int low = 0;
		int high = arr.length-1;
		
		while(low <= high) {
			int mid = (high + low) / 2;
			
			if(arr[mid] == input) return 1;
			
			else if(input > arr[mid]) low = mid + 1;
			else if(input < arr[mid]) high = mid - 1;
		}
		return 0;
	}
}
