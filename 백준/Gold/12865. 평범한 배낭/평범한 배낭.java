
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = to(st.nextToken());
		int K = to(st.nextToken());
		int[][] arr = new int[N+1][2];
		int[][] dp = new int[N+1][K+1];
		
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = to(st.nextToken());
			arr[i][1] = to(st.nextToken());
		}
		
//		print(arr);
		
		int result = 0;
		
//		for(int i = 0; i < arr.length-1; i++) {
//			if(arr[i][0] + arr[i+1][0] <= K) {
//				result = Math.max(arr[i][1], arr[i][1] + arr[i+1][1]);
//			}
//		}
//		
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < K+1; j++) { //K 무게 
				//비교 무게 - 물품의 무게가 0보다 큰 경우
				if(j - arr[i][0] >= 0) {
					dp[i][j] = Math.max(dp[i-1][j], arr[i][1] + dp[i-1][j-arr[i][0]]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}

	private static int to(String str) {
		return Integer.parseInt(str);
	}
	
	private static void print(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}
	}
}
