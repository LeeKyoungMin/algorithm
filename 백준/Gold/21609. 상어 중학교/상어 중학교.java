import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Coordinate{
	int x, y;
	Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Block implements Comparator<Block>{
	int x;
	int y;
	int rainbowCnt;
	int blockCnt;
	
	Block(int x, int y, int rainbowCnt, int blockCnt){
		this.x= x;
		this.y = y;
		this.rainbowCnt = rainbowCnt;
		this.blockCnt = blockCnt;
	}

	@Override
	public int compare(Block o1, Block o2) {
		if(o1.blockCnt == o2.blockCnt) {
			if(o1.rainbowCnt == o2.rainbowCnt) {
				if(o1.x == o2.x) {
					return o2.y - o1.y;
				}else {
					return o2.x - o1.x;
				}
			}else {
				return o2.rainbowCnt - o1.rainbowCnt;
			}
		}else {
			return o2.blockCnt - o1.blockCnt;
		}
	}
}

public class Main {

	static int N, M;
	static int[][] map;
	static List<Block> list;
	static boolean blockExist;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Coordinate> queue; 
	static boolean[][] visited;
	static boolean[][] mapVisited;
	static int result;
	static final int remove = -2;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = pi(st.nextToken());
		M = pi(st.nextToken());
		
		map = new int[N][N];
		mapVisited = new boolean[N][N];
		list = new ArrayList<>();
		blockExist = true;
		result = 0;
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = pi(input[j]);
			}
		}
		
		while(true) {
			findBlockGrp();
			if(!blockExist) {
				break;
			}
			removeBlock();
			gravity();
			rotate();
			gravity();
			
			list = new ArrayList<>();
		}
		
		System.out.println(result);
	}
	
	private static void rotate() {
		
		int start = 0;
		int end = N - 1;
		for(int s = start, e = end; s < e; s++, e--) {
			for(int i = s, j = e; i < e; i++, j--) {
				int tmp = map[s][i];
				map[s][i] = map[i][e];
				map[i][e] = map[e][j];
				map[e][j] = map[j][s];
				map[j][s] = tmp;
			}
		}
	}
	
	private static void gravity() {
		for(int i = 0; i < N; i++) {
			eachMove(i);
		}
	}

	private static void eachMove(int y) {
		int x = 0;
		int next = 0;
		int end = N-1;
		
		for(int i = N-2; i >= 0; i--) {
			if(map[i][y] == -1 || map[i][y] == -2) {
				continue;
			}
			keepGoing(i, y);
		}
	}

	private static void keepGoing(int x, int y) {
		int next = x+1;
		while(true) {
			if(next == N) {
				return;
			}
			
			if(map[next][y] == -1) {
				return;
			}
			
			if(map[next][y] != -1 && map[next][y] == -2) {
				int temp = map[next][y];
				map[next][y] = map[x][y];
				map[x][y] = temp;
			}
			x = next;
			next++;
		}
	}
	
	private static void removeBlock() {
		int cnt = 0;
		cnt = removeBlock(list.get(0).x, list.get(0).y);
		//print();
		getScore(cnt);
	}
	
	private static void getScore(int cnt) {
		result += Math.pow(cnt, 2);
	}
	
	private static int removeBlock(int x, int y) {
		visited = new boolean[N][N];
		queue = new LinkedList<>();
		
		queue.add(new Coordinate(x, y));
		visited[x][y] = true;
				
		int target = map[x][y];
		int blockCount = 1;
		map[x][y] = remove;
		
		while(!queue.isEmpty()) {
			Coordinate c = queue.poll();
//			visited[c.x][c.y] = true;
			
			for(int i = 0; i < 4; i++) {
				int _x = c.x + dx[i];
				int _y = c.y + dy[i];
				
				if(isValid(_x, _y) && !visited[_x][_y] && map[_x][_y] != -1 && map[_x][_y] != remove && (map[_x][_y] == target || map[_x][_y] == 0)) {
					////System.out.println("_x : " + _x + " _y : " + _y);
					queue.add(new Coordinate(_x, _y));
					visited[_x][_y] = true;
					map[_x][_y] = remove;
					blockCount++;
				}
			} //for
		} //queue while
		return blockCount;
	}
	
	private static void findBlockGrp() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == -1) {
					continue;
				}
				if(map[i][j] == 0) {
					continue;
				}
				if(map[i][j] == remove) {
					continue;
				}
				
				if(!mapVisited[i][j]) {
					makeBlockList(i, j);
				}
				list.sort(new Comparator<Block>() {

					@Override
					public int compare(Block o1, Block o2) {
						if(o1.blockCnt == o2.blockCnt) {
							if(o1.rainbowCnt == o2.rainbowCnt) {
								if(o1.x == o2.x) {
									return o2.y - o1.y;
								}else {
									return o2.x - o1.x;
								}
							}else {
								return o2.rainbowCnt - o1.rainbowCnt;
							}
						}else {
							return o2.blockCnt - o1.blockCnt;
						}
					}
					
				});
			}
		}
		
		mapVisited = new boolean[N][N];
		
		if(list.size() == 0) {
			blockExist = false;
			return;
		}
	}
	
	private static void makeBlockList(int x, int y) {
		visited = new boolean[N][N];
		queue = new LinkedList<>();
		
		queue.add(new Coordinate(x, y));
		visited[x][y] = true;
		mapVisited[x][y] = true;
		
		int target = map[x][y];
		int blockCount = 1;
		int rainbowCount = 0;
		int initX = x;
		int initY = y;
		
		while(!queue.isEmpty()) {
			Coordinate c = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int _x = c.x + dx[i];
				int _y = c.y + dy[i];
				
				if(isValid(_x, _y) && !visited[_x][_y] && map[_x][_y] != -1 && map[_x][_y] != remove && (map[_x][_y] == target || map[_x][_y] == 0)) {
					////System.out.println("_x : " + _x + " _y : " + _y);
					if(map[_x][_y] == 0) {
						queue.add(new Coordinate(_x, _y));
						visited[_x][_y] = true;
						rainbowCount++;
					}else {
						queue.add(new Coordinate(_x, _y));
						visited[_x][_y] = true;
						mapVisited[_x][_y] = true;
					}
					blockCount++;
				}
			} //for
		} //queue while
		
		if(blockCount < 2) { //블록이 존재하지 않을때 블록찾기 끝낸다.
			return;
		}
		
		list.add(new Block(initX, initY, rainbowCount, blockCount));
	}
	
	private static boolean isValid(int x, int y) {
		return (x >= 0 && x < N) && (y >= 0 && y < N);
	}
	
	private static int pi(String str) {
		return Integer.parseInt(str);
	}

}
