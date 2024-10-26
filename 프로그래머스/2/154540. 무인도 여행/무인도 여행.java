import java.util.*;

class Solution {
    
    static List<Integer> list = new ArrayList<>();
    static String[][] arr;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static boolean isExist = false;
        
    public int[] solution(String[] maps) {
        int[] answer = {};
        N = maps.length;
        M = maps[0].length();
        arr = new String[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < maps.length; i++) {
            String[] str = maps[i].split("");
            for(int j = 0; j < str.length; j++) {
                arr[i][j] = str[j];
            }
        }
        
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                if(isDigit(arr[i][j]) && !visited[i][j]) {
                    queue.add(new int[]{i, j});
                    int result = bfs(i, j, Integer.parseInt(arr[i][j]));
                    list.add(result);
                }
            }
        }
        
        Collections.sort(list);
        
        return isExist == true ? list.stream().mapToInt(Integer::intValue).toArray() : new int[]{-1};
    }
    
    public int bfs(int i, int j, int value) {
        
        isExist = true;
        int currentSum = value;
        visited[i][j] = true;
        
        while(!queue.isEmpty()) {
            int[] element = queue.poll();
            int nx = element[0];
            int ny = element[1];
            
            for(int n = 0; n < 4; n++) {
                int _x = nx + dx[n];
                int _y = ny + dy[n];
                
                if(!isBoundary(_x, _y)) continue;
                if(arr[_x][_y].equals("X")) continue;
                if(visited[_x][_y]) continue;
            
                currentSum += Integer.parseInt(arr[_x][_y]);
                visited[_x][_y] = true;
                queue.add(new int[]{_x, _y});
            }
        }
        
        return currentSum;
    }
    
    public boolean isBoundary(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < M);
    }
    
    public boolean isDigit(String str) {
        return str != null && str.matches("\\d+");
    }
}