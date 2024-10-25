import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    char value;
    Node left;
    Node right;

    Node(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class Main {

    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new Node[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parentValue = st.nextToken().charAt(0);
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);

            //부모 노드가 없으면 생성
            if(tree[parentValue - 'A'] == null) {
                tree[parentValue - 'A'] = new Node(parentValue);
            }
            //왼쪽 자식이 존재한다면
            if(leftValue != '.') {
                tree[leftValue - 'A'] = new Node(leftValue);
                tree[parentValue - 'A'].left = tree[leftValue - 'A'];
            }
            //오른쪽 자식이 존재한다면
            if(rightValue != '.') {
                tree[rightValue - 'A'] = new Node(rightValue);
                tree[parentValue - 'A'].right = tree[rightValue - 'A'];
            }
        }

        // 전위 순회
        preOrder(tree[0]);
        System.out.println();

        // 중위 순회
        midOrder(tree[0]);
        System.out.println();

        // 후위 순회
        postOrder(tree[0]);
        System.out.println();
    }

    private static void postOrder(Node node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value);
    }

    private static void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void midOrder(Node node) {
        if (node == null) return;
        midOrder(node.left);
        System.out.print(node.value);
        midOrder(node.right);
    }
}
