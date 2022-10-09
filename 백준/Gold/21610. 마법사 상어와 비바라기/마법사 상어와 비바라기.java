import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Cloud{
	int x;
	int y;
	
	Cloud(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, M, cnt;
	static int[][] map;
	static int[] dx = {0,-1,-1,-1,0,1,1,1}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dy = {-1,-1,0,1,1,1,0,-1}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[][] info;
	static boolean[][] visited;
	static Queue<Cloud> cloud;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = pi(in[0]);
		M = pi(in[1]);
		map = new int[N][N];
		info = new int[M][2];
		visited = new boolean[N][N];
		cloud = new LinkedList<Cloud>();
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = pi(input[j]);
				visited[i][j] = false;
			}
		}
		
		for(int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 2; j++) {
				info[i][j] = pi(input[j]);
			}
		}
		
		
		cloud.add(new Cloud(N-1, 0));
		cloud.add(new Cloud(N-1, 1));
		cloud.add(new Cloud(N-2, 0));
		cloud.add(new Cloud(N-2, 1));
		//print(map);
		
		for(int i = 0; i < info.length; i++) {
			int dir = info[i][0]-1;
			int speed = info[i][1];
			
			moveCloud(dir, speed);
			
			copyCloud();
			
			createCloud();
		}
		
		System.out.println(calculateCloud());
	}

	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	private static void moveCloud(int dir, int speed) {
		for(int i = 0; i < cloud.size(); i++) {
			Cloud queue = cloud.poll();
			int x = queue.x;
			int y = queue.y;
			
			int s = speed % N;
			//System.out.println("x : " + x + "  y : "+y + "  s : " + s + " speed : " + speed);
			int _x = (N + x + dx[dir] * s)%N;
			int _y = (N + y + dy[dir] * s)%N;
			
			//System.out.println("_x : " + _x + "  _y : " + _y);
			visited[_x][_y] = true;
			map[_x][_y] += 1;
			
			cloud.add(new Cloud(_x, _y));
		}
	}
	
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	private static void copyCloud() {
		
		while(!cloud.isEmpty()) {
			int count = 0;
			Cloud queue = cloud.poll();
			
			int x = queue.x;
			int y = queue.y;
			
			for(int i = 0; i < 4; i++) {
				int _x = x + dx[i*2 + 1];
				int _y = y + dy[i*2 + 1];
				
				if(isValid(_x, _y) && map[_x][_y] > 0) {
					count++;
				}
			}
			map[x][y] += count;
		}
	}
	
	private static void createCloud() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] >= 2) {
					cloud.add(new Cloud(i, j));
					map[i][j] -= 2;
				}
			}
		}
		visited = new boolean[N][N];
	}
	
	private static int calculateCloud() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}
	
	private static int pi(String str) {
		return Integer.parseInt(str);
	}
	
	private static boolean isValid(int x, int y) {
		return (0 <= x && x < N) && (0 <= y && y < N);
	}
	
	private static void initVisited(boolean[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	private static void printVisited(boolean[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}
		System.out.println();
	}
	
	private static void print(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}
		System.out.println();
	}
}
