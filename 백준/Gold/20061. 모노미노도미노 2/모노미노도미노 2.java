
import java.io.*;

public class Main {

	static int N;
	static boolean[][] blue = new boolean[4][6];
	static boolean[][] green = new boolean[6][4];
	static int score;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			int x = Integer.parseInt(input[1]);
			int y = Integer.parseInt(input[2]);

			stack(t, x, y);
			remove();
			move();

		}

		bw.write(score + "\n");
		bw.write(getBlockCount() + "\n");
		bw.flush();

	}

	public static void stack(int t, int x, int y) {

		if (t == 1) {
			for (int i = 5; i >= 0; i--) {
				if (!green[i][y]) {  // green[i][y] = false이면

					// i번째 행보다 위의 행들 중 동일 열에 true가 있는지 확인
					boolean flag = true;
					for (int r = 0; r < i; r++) {
						if (green[r][y]) {
							flag = false;
							break;
						}
					}

					if (flag) {
						green[i][y] = true;
						break;
					}
				}
			}

			for (int i = 5; i >= 0; i--) {
				if (!blue[x][i]) {

					boolean flag = true;
					for (int c = 0; c < i; c++) {
						if (blue[x][c]) {
							flag = false;
							break;
						}
					}
					if (flag) {
						blue[x][i] = true;
						break;
					}
				}
			}
		}

		if (t == 2) { // 1x2 블럭
			// green 영역에 블럭 배치
			for (int i = 5; i >= 0; i--) {
				if (!green[i][y] && !green[i][y + 1]) {
					boolean flag = true;
					for (int r = 0; r < i; r++) {
						if (green[r][y] || green[r][y + 1]) {
							flag = false;
							break;
						}
					}
					if (flag) {
						green[i][y] = true;
						green[i][y + 1] = true;
						break;
					}
				}
			}

			// blue 영역에 블럭 배치
			for (int i = 5; i >= 1; i--) {
				if (!blue[x][i] && !blue[x][i - 1]) {

					boolean flag = true;
					for (int c = 0; c < i - 1; c++) {
						if (blue[x][c]) {
							flag = false;
							break;
						}
					}
					if (flag) {
						blue[x][i] = true;
						blue[x][i - 1] = true;
						break;
					}
				}
			}
		}

		if (t == 3) { // 2x1 타일
			// green 영역에 블럭 배치
			for (int i = 5; i >= 1; i--) {
				if (!green[i][y] && !green[i - 1][y]) {

					boolean flag = true;
					for (int r = 0; r < i - 1; r++) {
						if (green[r][y]) {
							flag = false;
							break;
						}
					}

					if (flag) {
						green[i][y] = true;
						green[i - 1][y] = true;
						break;
					}
				}
			}

			// blue 영역에 블럭 배치
			for (int i = 5; i >= 0; i--) {
				if (!blue[x][i] && !blue[x + 1][i]) {
					boolean flag = true;
					for (int c = 0; c < i; c++) {
						if (blue[x][c] || blue[x + 1][c]) {
							flag = false;
							break;
						}
					}

					if (flag) {
						blue[x][i] = true;
						blue[x + 1][i] = true;
						break;
					}
				}
			}
		}
	}

	public static void remove() {

		// green 영역 블럭 제거
		for (int i = 0; i < 6; i++) {

			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (!green[i][j]) {
					flag = false;
					break;
				}
			}

			if (flag) {
				score++;

				for (int k = i - 1; k >= 0; k--) {
					for (int j = 0; j < 4; j++) {
						green[k + 1][j] = green[k][j];
					}
				}
				// 0번째 행 비우기
				for (int j = 0; j < 4; j++) {
					green[0][j] = false;
				}
			}
		}

		// blue 영역 블럭 제거
		for (int i = 0; i < 6; i++) {

			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (!blue[j][i]) {
					flag = false;
					break;
				}
			}

			if (flag) {
				score++;

				for (int k = i - 1; k >= 0; k--) {
					for (int j = 0; j < 4; j++) {
						blue[j][k + 1] = blue[j][k];
					}
				}
				// 0번째 열 비우기
				for (int j = 0; j < 4; j++) {
					blue[j][0] = false;
				}
			}
		}

	}

	public static void move() {

		// green 영역 연한 부분 블록 있으면 이동
		int cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j]) {
					cnt++;
					break;
				}
			}
		}

		if (cnt > 0) {
			for (int i = 5 - cnt; i >= 0; i--) {
				for (int j = 0; j < 4; j++) {
					green[i + cnt][j] = green[i][j];
				}
			}
			for (int i = 0; i < cnt; i++) {
				for (int j = 0; j < 4; j++) {
					green[i][j] = false;
				}
			}
		}

		// blue 영역 연한 부분 블록 있으면 이동
		cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (blue[j][i]) {
					cnt++;
					break;
				}
			}
		}

		if (cnt > 0) {
			for (int i = 5 - cnt; i >= 0; i--) {
				for (int j = 0; j < 4; j++) {
					blue[j][i + cnt] = blue[j][i];
				}
			}
			for (int i = 0; i < cnt; i++) {
				for (int j = 0; j < 4; j++) {
					blue[j][i] = false;
				}
			}
		}

	}

	public static int getBlockCount() {

		int cnt = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j])
					cnt++;
				if (blue[j][i])
					cnt++;

			}
		}

		return cnt;
	}

}