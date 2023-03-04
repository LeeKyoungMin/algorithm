import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 0;
		int cnt = 0;

		for (int i = 1; i <= n; i++) {
			int input = sc.nextInt();
			count = 0;
			
			for (int j = 1; j <= input; j++) {
				if (input % j == 0) {
					count++;
				}
			}
			if(count == 2) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
