
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

	static int N;
	static int rslt;
	static int[][] nearCnt;
	static int[][] nearEmpty;
	static int[][] arr;
	static Map<Integer, Object> map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] nCnt;
	static int[] eCnt;
	static int[][] result;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = pi(br.readLine());
		map = new LinkedHashMap<>();
		arr = new int[N][N];
		nearEmpty = new int[N][N];
		nearCnt = new int[N][N];
		nCnt = new int[5];
		eCnt = new int[5];
		result = new int[N][N];
		rslt = 0;
		
		for (int i = 0; i < N * N; i++) {
			String[] input = br.readLine().split(" ");
			List<Integer> list = new ArrayList<>();
			for (int k = 1; k < input.length; k++) {
				list.add(pi(input[k]));
			}
			map.put(pi(input[0]), list);
		}
		// printMap();

		put();
		getResult();
		System.out.println(end());
	}

	private static int end() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (result[i][j] == 1) {
					rslt += 1;
				} else if (result[i][j] == 2) {
					rslt += 10;
				} else if (result[i][j] == 3) {
					rslt += 100;
				} else if (result[i][j] == 4) {
					rslt += 1000;
				}
			}
		}
		return rslt;
	}

	private static void getResult() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int _x = i + dx[k];
					int _y = j + dy[k];

					if (isValid(_x, _y)) {
						List<Integer> list = getValue(arr[i][j]);
						if (list.contains(arr[_x][_y])) {
							result[i][j] += 1;
						}
					}
				}
			}
		}
	}

	private static void put() {
		int i = 0;
		for (int key : map.keySet()) {
			if (i == 0) {
				arr[1][1] = key;
			} else {
				calculate(key);
				makeArray();
				decision(key);

				//System.out.println("arr");
				print(arr);
				//System.out.println("nearCnt");
				print(nearCnt);
				//System.out.println("nearEmpty");
				print(nearEmpty);

				nCnt = new int[5];
				nearEmpty = new int[N][N];
				nearCnt = new int[N][N];
				eCnt = new int[5];
				result = new int[N][N];
			}
			i++;
		}
		print(arr);
	}

	private static void decision(int key) {
		//System.out.println("before arr");
		print(arr);
		//System.out.println("nCnt");
		printCnt(nCnt);
		//System.out.println("eCnt");
		printCnt(eCnt);

		for (int i = 4; i >= 0; i--) {
			if (nCnt[i] == 0) {
				continue;
			} else if (nCnt[i] == 1) {
				for (int a = 0; a < N; a++) {
					for (int b = 0; b < N; b++) {
						if (nearCnt[a][b] == i && arr[a][b] == 0) {
							//System.out.println("i : " + i + "  arr: " + arr[a][b] + " key : " + key);
							arr[a][b] = key;
							return;
						}
					}
				}
			} else {
				boolean flag = false;
				for (int c = 4; c >= 0; c--) {
					if (eCnt[c] == 0) {
						continue;
					} else {
						for (int d = 0; d < N; d++) {
							for (int e = 0; e < N; e++) {
								if (nearCnt[d][e] == i && nearEmpty[d][e] == c && arr[d][e] == 0) {
									//System.out.println("d : " + d + " e : " + e + " c : " + c);
									arr[d][e] = key;
									return;
								}
							}
						}

					}
				}
			}
		}
	}

	private static void makeArray() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nCnt[nearCnt[i][j]]++;
				eCnt[nearEmpty[i][j]]++;
			}
		}
	}

	private static void calculate(int key) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int _x = i + dx[k];
					int _y = j + dy[k];

					if (isValid(_x, _y)) {
						////System.out.println("i : " + i + " j : " + j + "  _x : " + _x + " _y : " + _y + "  arr : " + arr[_x][_y]);
						if (arr[_x][_y] == 0) {
							nearEmpty[i][j] += 1;
						} else {
							List<Integer> sList = getValue(key);
							////System.out.println("key : " + key + " sList : " + sList.toString());
							if (sList.contains(arr[_x][_y])) {
								nearCnt[i][j] += 1;
							}
						}
					}
				}
			}
		}
	}

	private static boolean isValid(int x, int y) {
		return (x >= 0 && x < N) && (y >= 0 && y < N);
	}

	private static int pi(String str) {
		return Integer.parseInt(str);
	}

	private static void printCnt(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			//System.out.print(arr[i] + " ");
		}
		//System.out.println();
	}

	private static List<Integer> getValue(int key) {
		return (List) map.get(key);
	}

	private static void printMap() {
		// Iterator iter = map.entrySet().iterator();
		for (int key : map.keySet()) {
			//System.out.println("key : " + key + "  value : " + map.get(key).toString());
		}
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				//System.out.print(arr[i][j] + " ");
			}
			//System.out.println();
		}
		//System.out.println();
	}

}
