class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int len = p.length(); // 비교할 문자열의 길이
        long pValue = Long.parseLong(p); // p를 숫자로 변환
        
        for (int i = 0; i <= t.length() - len; i++) { // 루프 조건 수정
            String sub = t.substring(i, i + len); // 부분 문자열 추출
            long subValue = Long.parseLong(sub); // 부분 문자열을 숫자로 변환
            
            if (subValue <= pValue) { // 조건에 맞으면 카운트 증가
                answer++;
            }
        }
        
        return answer;
    }
}