import java.util.LinkedList;
import java.util.Queue;

class MapArray {
	int x;
	int y;

	MapArray(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {

	public static int solution(int[][] maps) {
		int answer = -1;

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length; j++) {
				if (maps[i][j] == 1) {
					maps[i][j] = 0;
				} else {
					maps[i][j] = -1;
				}
			}
		}

		Queue<MapArray> queue = new LinkedList<>();
		int[] dx = {1, 0, -1, 0};
	    int[] dy = {0, 1, 0, -1};
		int N = maps.length;
		int M = maps[0].length;

		boolean[][] visited = new boolean[N][M];

		queue.add(new MapArray(maps.length - 1, maps[0].length - 1));
		visited[maps.length - 1][maps[0].length - 1] = true;

		while (!queue.isEmpty()) {
			MapArray c = queue.poll();

			if(c.x == 0 && c.y == 0) {
				answer = maps[0][0] + 1;

				return answer;
			}
			
			for (int i = 0; i < 4; i++) {
				int _x = dx[i] + c.x;
				int _y = dy[i] + c.y;

				// 범위 벗어나는지 확
				if (!isValid(_x, _y, N, M))
					continue;
				if (visited[_x][_y])
					continue;
				if (isBlock(maps, _x, _y))
					continue;

				visited[_x][_y] = true;
				maps[_x][_y] = maps[c.x][c.y] + 1;
				queue.add(new MapArray(_x, _y));
			}
		}
		return -1;
	}

	private static boolean isValid(int x, int y, int N, int M) {
		return (0 <= x && x < N) && (0 <= y && y < M);
	}

	private static boolean isBlock(int[][] map, int x, int y) {
		return (map[x][y] == -1 ? true : false);
	}

}
