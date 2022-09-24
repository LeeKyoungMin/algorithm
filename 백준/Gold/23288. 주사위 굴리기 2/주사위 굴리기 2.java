
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, k;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};//up, right, down, left
    static int[] dy = {0, 1, 0, -1};//up, right, down, left

    public static void main(String[] args) throws IOException {
        init();

        Dice dice = new Dice();
        int x = 0, y = 0;
        int dir = 1;//start direction = right
        int answer = 0;

        while(k-- > 0) {
            if(!valid(x + dx[dir], y + dy[dir]))
                dir = reverseDir(dir);

            x = x + dx[dir];
            y = y + dy[dir];

            dice.rollDice(dir);

            answer += getScore(x, y);
            dir = getNextDir(dice.down, board[x][y], dir);
        }

        System.out.println(answer);

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = pi(st.nextToken());
        m = pi(st.nextToken());
        k = pi(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = pi(st.nextToken());
            }
        }
    }

    public static boolean valid(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m) return false;
        else return true;
    }

    public static int reverseDir(int dir) {
        return (dir+2)%4;
    }

    public static int getScore(int x, int y) {
        class Node {
            int x, y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        //bfs
        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));

        int num = board[x][y];
        int count = 1;
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextX = now.x + dx[dir];
                int nextY = now.y + dy[dir];
                if (valid(nextX, nextY) && !visited[nextX][nextY] && board[nextX][nextY] == num) {
                    visited[nextX][nextY] = true;
                    count++;
                    queue.add(new Node(nextX, nextY));
                }
            }
        }

        return num * count;
    }

    public static int getNextDir(int diceBottom, int boardNum, int dir) {
        if(diceBottom > boardNum) {
            return (dir+1)%4;
        }
        else if(diceBottom < boardNum) {
            return (dir+3)%4;
        }
        else return dir;
    }

    public static int pi(String str) {
        return Integer.parseInt(str);
    }

    static class Dice{
        int up, down, left, right, front, back;
        public Dice() {
            up = 1; down = 6; left = 4; right = 3; front = 5; back = 2;
        }

        public void rollDice(int dir) {
            if(dir == 0) toNorth();
            else if(dir == 1) toEast();
            else if(dir == 2) toSouth();
            else toWest();
        }

        public void toEast() {
            int save = up;
            up = left;
            left = down;
            down = right;
            right = save;
        }

        public void toWest() {
            int save = up;
            up = right;
            right = down;
            down = left;
            left = save;
        }

        public void toNorth() {
            int save = up;
            up = front;
            front = down;
            down = back;
            back = save;
        }

        public void toSouth() {
            int save = up;
            up = back;
            back = down;
            down = front;
            front = save;
        }

//        public void print() {
//            System.out.println("  " + back + "  ");
//            System.out.println(left + " " + up + " " + right);
//            System.out.println("  " + front + "  ");
//        }
    }

}
