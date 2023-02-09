class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        
        char[] words = s.toCharArray();
        
        for(int i = 0; i < words.length; i++) {
        	for(int j = 0; j < index; j++) {
        		do {
        			words[i]++;
        			if(words[i] > 'z') {
        				words[i] -= 26;
        			}
        		}while(skip.contains(String.valueOf(words[i])));
        	}
        }
        answer = String.valueOf(words);
        
        return answer;
    }
}