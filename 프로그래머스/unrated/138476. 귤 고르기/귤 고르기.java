
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Integer[] tmp = Arrays.stream(tangerine).boxed().toArray(Integer[]::new);
        Arrays.sort(tmp, Comparator.reverseOrder()); // 내림차순
        
//        print(tmp);
        
        int[] array = new int[tmp[0]+1];
        
        for(int i = 0; i < tmp.length; i++) {
        	array[tmp[i]]++;
        }
        
        Integer[] temp = Arrays.stream(array).boxed().toArray(Integer[]::new);
        
        Arrays.sort(temp, Comparator.reverseOrder());
        
//        print(temp);
        
        for(int i = 0; i < temp.length; i++) {
        	if(k <= temp[i]) {
        		answer++;
        		break;
        	}
        	k -= temp[i];
        	answer++;
        }
        
        return answer;
    }
}