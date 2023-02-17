import java.util.Stack;
class Solution {
    public static int[] solution(int[] numbers) {
        int[] answer = {};
        
        int len = numbers.length;
        Stack<Integer> stack = new Stack<>();
        answer = new int[len];
        
        for(int i = 0; i < len; i++) {
        	if(stack.isEmpty() || numbers[i] < numbers[i-1]) {
        		stack.push(i);
        	}else {
        		while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
        			answer[stack.pop()] = numbers[i];
        		}
        		stack.push(i);
        	}
        }
        
        while(!stack.isEmpty()) {
        	answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}