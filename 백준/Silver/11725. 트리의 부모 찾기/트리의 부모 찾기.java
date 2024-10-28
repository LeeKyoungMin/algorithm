import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
    
        //각 노드의 부모 노드를 저장하는 뱌열
        int[] parent = new int[n+1];
        
        //연결리스트 배열
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        //방문 체크 배열
        boolean[] visited = new boolean[n+1];
        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        
        //BFS 큐
        Queue<Integer> queue = new LinkedList<>();
        
        //루트 노드
        queue.add(1);
        visited[1] = true;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            // 현재 노드에 연결된 다음 노드들 탐색
            for(int next : adj[cur]) {
                if(visited[next]) continue;
            
                visited[next] = true;
                queue.add(next);
                parent[next] = cur;
            }
        }
        
        for(int i = 2; i <= parent.length-1; i++) {
            System.out.println(parent[i]);
        }
     }
}
