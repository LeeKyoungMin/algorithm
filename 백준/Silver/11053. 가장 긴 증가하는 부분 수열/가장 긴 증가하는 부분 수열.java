import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;
import java.util.*;

public class Main {

    static int N;
    static HashSet<Integer> set = new HashSet<>();
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // 10 10 20 20 30 50 -> 10 20 30 50
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        String[] inputs = br.readLine().split(" ");
        for(int i = 0; i < inputs.length; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
            dp[i] = 1;
        }

        int max = 1;
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
