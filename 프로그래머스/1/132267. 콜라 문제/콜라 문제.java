class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int emptyBottle = 0;
        
        while(true) {
            emptyBottle += n / a * b;
            n = ((n / a) * b) + (n % a);
            if(n  < a) {
                break;
            }
            
        }
        
        return emptyBottle;
    }
}