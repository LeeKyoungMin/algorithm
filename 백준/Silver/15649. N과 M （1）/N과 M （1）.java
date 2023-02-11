import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		arr = new int[M];
		int[] array = new int[N];
		for(int i = 0; i < array.length; i++) {
			array[i] = i+1;
		}
		
		permutation(array, N, M, 0);
	}
	
	private static void permutation(int[] array, int N, int M, int index) {
		
		if(M == index) {
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}System.out.println();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[index] = array[i];
				permutation(array, N, M, index+1);
				visited[i] = false;
			}
		}
	}

}
