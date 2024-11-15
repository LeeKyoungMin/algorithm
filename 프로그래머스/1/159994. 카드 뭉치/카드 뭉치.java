class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0;
        int idx2 = 0;
        
        for (String target : goal) {
            // cards1의 범위를 벗어나지 않고 goal[i]와 cards1[idx1]이 같은 경우
            if (idx1 < cards1.length && target.equals(cards1[idx1])) {
                idx1++;
            } 
            // cards2의 범위를 벗어나지 않고 goal[i]와 cards2[idx2]이 같은 경우
            else if (idx2 < cards2.length && target.equals(cards2[idx2])) {
                idx2++;
            } 
            // 둘 다 만족하지 않는 경우
            else {
                return "No";
            }
        }
        
        return "Yes";
    }
}