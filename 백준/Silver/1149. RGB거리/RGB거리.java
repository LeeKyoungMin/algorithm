import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R = 0;
    static int G = 1;
    static int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3]; // N x 3 크기의 배열 생성
        int[][] dp = new int[N][3];
        
        // 배열 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][R] = arr[0][0];
        dp[0][G] = arr[0][1];
        dp[0][B] = arr[0][2];
        
        // DP 배열 업데이트
        for (int i = 1; i < N; i++) {
            dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + arr[i][R];
            dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + arr[i][G];
            dp[i][B] = Math.min(dp[i-1][G], dp[i-1][R]) + arr[i][B];
        }

        // 최소 비용 출력
        int result = Math.min(Math.min(dp[N - 1][R], dp[N - 1][G]), dp[N - 1][B]);
        System.out.println(result);
    }
}