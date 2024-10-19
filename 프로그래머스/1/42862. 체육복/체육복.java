import java.util.Arrays;

class Solution {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[] total = new int[n+1];
        Arrays.fill(total, 1);

        for(int i = 0; i < lost.length; i++){
            total[lost[i]]--;
        }

        for(int i = 0; i < reserve.length; i++){
            total[reserve[i]]++;
        }

        for(int i = 1; i < total.length; i++){
            if(total[i] == 1){
                continue;
            }else if(total[i] == 2){
                if(i == total.length - 1){
                    continue;
                }else{
                    if(total[i+1] == 0){
                        total[i] = 1;
                        total[i+1] = 1;
                        continue;
                    }
                }
            }else {
                if(i == total.length - 1){
                    continue;
                }else{
                    if(total[i+1] == 2){
                        total[i] = 1;
                        total[i+1] = 1;
                        continue;
                    }
                }
            }
        }

        for(int i = 1; i < total.length; i++){
            if(total[i] >= 1){
                answer++;
            }
        }

        return answer;
    }
}