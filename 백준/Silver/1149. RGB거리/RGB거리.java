import java.io.*;
import java.util.*;

public class Main {

	static int R = 0;
	static int G = 1;
	static int B = 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		int[][] arr = new int[n][3];
		int[][] d = new int[n][3];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		d[0][R] = arr[0][0];
		d[0][G] = arr[0][1];
		d[0][B] = arr[0][2];

		for (int i = 1; i < n; i++) {

			d[i][R] = Math.min(d[i - 1][G], d[i - 1][B]) + arr[i][R];
			d[i][G] = Math.min(d[i - 1][R], d[i - 1][B]) + arr[i][G];
			d[i][B] = Math.min(d[i - 1][R], d[i - 1][G]) + arr[i][B];
		}

		int max = Math.min(Math.min(d[n - 1][R], d[n - 1][G]), d[n - 1][B]);

		System.out.println(max);
	}

}
