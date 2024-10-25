import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
public class Main {

    static int N, M;
    static int cnt;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int n = 1;
    static boolean[] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }

        for(int j = 0; j < N; j++){
            list.add(arr[j]);
        }

        Collections.sort(list);
        perm(arr, 0);
        System.out.println(sb.toString());
    }

    private static void perm(int[] arr, int depth) throws IOException {

        if(depth == M) {
            for(int i = 0; i < depth; i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = list.get(i);
                perm(arr, depth + 1);
                visited[i] = false;
            }
        }
    }

}

