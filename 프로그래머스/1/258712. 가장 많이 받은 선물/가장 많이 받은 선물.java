import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int[] answer = new int[friends.length];
        
        Map<String, Integer> fMap = new HashMap<String, Integer>();
        int[][] giveAndTakeMap = new int[friends.length][friends.length];
        int index = 0;
        for(String friend : friends) {
            fMap.put(friend, index++);
        }
        
        //주고 받은 배열 생성
        for(String gift: gifts) {
            String[] element = gift.split(" ");
            String from = element[0];
            String to = element[1];
            
            giveAndTakeMap[fMap.get(from)][fMap.get(to)]++;
        }
        
        //선물 지수 계산
        int[][] jisu = new int[friends.length][2];
        int[] toSum = new int[friends.length];
        int[] fromSum = new int[friends.length];
        for(int i = 0; i < friends.length; i++) {
            for(int j = 0; j < friends.length; j++) {
                if(i == j) {continue;}
                toSum[i] += giveAndTakeMap[i][j];
            }
        }
        
        for(int i = 0; i < friends.length; i++) {
            for(int j = 0; j < friends.length; j++) {
                fromSum[i] += giveAndTakeMap[j][i];
            }
        }
        
        //지수 계산
        for(int i = 0; i < jisu.length; i++) {
            jisu[i][1] = toSum[i] - fromSum[i];
        }
        
        for(int i = 0; i <friends.length; i++) {
            for(int j = 0; j < friends.length; j++) {
                if(i == j) {
                    continue;
                }
                // 둘다 주고받은 기록이 없는 경우 또는 같다면
                if((giveAndTakeMap[i][j] == 0 && giveAndTakeMap[j][i] == 0)
                  || (giveAndTakeMap[i][j] == giveAndTakeMap[j][i])) {
                    //선물 지수 같은 경우
                    if(jisu[i][1] == jisu[j][1]) {continue;}
                    
                    //선물지수 같지 않은 경우
                    if(jisu[i][1] > jisu[j][1]) {
                        answer[i]++;
                    }else {
                        answer[j]++;
                    }
                }
                //둘다 주고 받은 기록이 있는 경우
                else if(giveAndTakeMap[i][j] != 0 || giveAndTakeMap[j][i] != 0) {
                    if(giveAndTakeMap[i][j] > giveAndTakeMap[j][i]) {
                        answer[i]++;
                    }else {
                        answer[j]++;
                    }
                }
            }
        }
        
        for(int ans : answer) {
            System.out.print(ans + " ");
        }
        
        // 가장 많이 받을 친구의 선물 수 계산
        int maxGifts = Arrays.stream(answer).max().orElse(0);
        return maxGifts / 2;
    }
}