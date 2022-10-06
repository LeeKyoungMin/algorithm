
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Fish {
	int num;
	int x;
	int y;
	int dir;
	int isAlive;

	Fish(int num, int x, int y, int dir, int isAlive) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.isAlive = isAlive;
	}
}

public class Main {

	static int N, result;
	static int[][] map = new int[4][4];
	static int[][] dirArr = new int[4][4];
	static int[] dirX = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 위, 왼대각선, 왼쪽 등
	static int[] dirY = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 위, 왼대각선, 왼쪽 등
	static Fish[] fish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		fish = new Fish[17];

		for (int i = 0; i < 4; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				map[i][j] = pi(input[j * 2]);
				int num = pi(input[j * 2]); // 0,2,4,6
				int dir = pi(input[j * 2 + 1]) - 1; // 1,3,5,7

				fish[num] = new Fish(num, i, j, dir, 1);
			}
		}

		int sx = 0, sy = 0;
		int sd = fish[map[0][0]].dir;
		int eat = map[0][0];
		fish[map[0][0]].isAlive = 0; // 물고기 죽음
		map[0][0] = -1; // 상어가 있는 위치 -1
		// print(numberArr);
		// print(dirArr);
		
		dfs(sx, sy, sd, eat);
		
		System.out.println(result);

	}

	private static void dfs(int sx, int sy, int sd, int eat) {
		result = Math.max(result, eat);

		int[][] tempMap = new int[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, map.length);
		}

		// fish 배열 복사
		Fish[] tempFish = new Fish[fish.length];
		for (int i = 1; i <= 16; i++)
			tempFish[i] = new Fish(fish[i].num, fish[i].x, fish[i].y, fish[i].dir, fish[i].isAlive);

		//물고기 이동  
		moveFish();
		
		for (int i = 1; i < 4; i++) {
			int nx = sx + dirX[sd] * i;
			int ny = sy + dirY[sd] * i;
			
			if(isValid(nx, ny) && map[nx][ny] != 0) {
				int eatFish = map[nx][ny];
				int nd = fish[eatFish].dir;
				map[sx][sy] = 0;
				map[nx][ny] = -1;
				fish[eatFish].isAlive = 0;
				
				dfs(nx, ny, nd, eatFish+eat);
				
				fish[eatFish].isAlive = 1;
				map[sx][sy] = -1;
				map[nx][ny] = eatFish;
			}
		}
		
		// 맵 상태, 물고기 정보 되돌리기 
		for(int j = 0; j < map.length; j++)
			System.arraycopy(tempMap[j], 0, map[j], 0, map.length);

		for(int i=1; i<=16; i++)
			fish[i] = new Fish(tempFish[i].num, tempFish[i].x, tempFish[i].y, tempFish[i].dir, tempFish[i].isAlive);

	}

	private static void moveFish() {
		for (int i = 1; i < 17; i++) {
			if (fish[i].isAlive == 0)
				continue;

			int cnt = 0;
			int dir = fish[i].dir; // 현재 물고기 방
			int nx = 0, ny = 0; // 물고기가 이동할

			while (cnt < 8) {
				dir %= 8;
				fish[i].dir = dir;

				nx = fish[i].x + dirX[dir];
				ny = fish[i].y + dirY[dir];

				if (isValid(nx, ny) && map[nx][ny] != -1) {
					if (map[nx][ny] == 0) { // 물고기 비어있을때
						map[fish[i].x][fish[i].y] = 0;
						fish[i].x = nx;
						fish[i].y = ny;
						map[nx][ny] = i;
					} else { // 물고기 비어있지 않을때
						int changeFish = fish[map[nx][ny]].num;
						fish[changeFish].x = fish[i].x;
						fish[changeFish].y = fish[i].y;
						map[fish[changeFish].x][fish[changeFish].y] = changeFish;

						// 현재 물고기 위치 변경
						fish[i].x = nx;
						fish[i].y = ny;
						map[nx][ny] = i;
					}
					break;
				} else {
					dir++;
					cnt++;
				}
			}
		}
	}

	private static boolean isValid(int nx, int ny) {
		return (nx >= 0 && nx < 4 && ny >= 0 && ny < 4);
	}

	private static int pi(String str) {
		return Integer.parseInt(str);
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
