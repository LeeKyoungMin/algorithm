import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class goosle{
	int x;
	int y;
	goosle(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static String[][] map;
	static int N, M, cnt;
	static int min = Integer.MAX_VALUE;
	static int resultX, resultY;
	
	static Stack<goosle> stackR = new Stack<>();
	static Stack<goosle> stackB = new Stack<>();
	
	static boolean[][][][] checked; 
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		// N : 세로, M : 가
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new String[N][M];
		checked = new boolean[N][M][N][M];
		
		for(int i = 0; i < N; i++) {
			String[] line = br.readLine().split("");
			for(int j = 0; j < line.length; j++) {
				map[i][j] = line[j];
				if(line[j].equals("R")) {
					stackR.add(new goosle(i, j));
				}
				if(line[j].equals("B")) {
					stackB.add(new goosle(i, j));
				}
			} //for
		} //for
		
		goosle r = stackR.pop();
		
		goosle b = stackB.pop();
		
		move(r.x, r.y, b.x, b.y, 0);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	public static void move(int rx, int ry, int bx, int by, int cnt) {
		
//		System.out.println(rx + " " + ry + " " + bx + " " + by + " " + cnt);
		
		if(cnt > 10) return;
		
		//파란 구슬이 구멍에 들어갔을때
		if(map[bx][by].equals("O")) return;
		
		if(map[rx][ry].equals("O")) {
			min = Math.min(min, cnt);
			return;
		}
		
		checked[rx][ry][bx][by] = true;
		
		for(int i = 0; i < 4; i++) {
			int nRx = rx;
			int nRy = ry;
			int nBx = bx;
			int nBy = by;
			
			//빨간구슬 이동
			while(!map[nRx+dx[i]][nRy+dy[i]].equals("#")) {
				nRx += dx[i];
				nRy += dy[i];
				if(map[nRx][nRy].equals("O")) break;
			}
			//파란구슬 이동
			while(!map[nBx+dx[i]][nBy+dy[i]].equals("#")) {
				nBx += dx[i];
				nBy += dy[i];
				if(map[nBx][nBy].equals("O")) break;
			}
			
			//파란구슬과 빨간구슬이 만났을때 
			if(nRx == nBx && nRy == nBy && !map[nRx][nRy].equals("O")) {
				
				int red_move = Math.abs(nRx - rx) + Math.abs(nRy - ry);
				int blue_move = Math.abs(nBx - bx) + Math.abs(nBy - by);
				//파란공이 더 가까울때
				if(red_move > blue_move) {
					nRx -= dx[i];
					nRy -= dy[i];
				}else {
					nBx -= dx[i];
					nBy -= dy[i];
				}
			}
			
			if(!checked[nRx][nRy][nBx][nBy]) {
				move(nRx, nRy, nBx, nBy, cnt+1);
			}
		}
		checked[rx][ry][bx][by] = false;
	}
	
}
