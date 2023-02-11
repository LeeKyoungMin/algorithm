import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

class Music implements Comparable<Music>{
	String genres;
	int plays;
	int index;
	
	Music(String genres, int plays, int index){
		this.genres = genres;
		this.plays = plays;
		this.index = index;
	}

	@Override
	public int compareTo(Music o) {
		if(this.plays == o.plays) {
			return this.index - o.index;
		}
		return o.plays - this.plays;
	}
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

		// 장르별 갯수 총합
		HashMap<String, Integer> gMap = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {
			gMap.put(genres[i], gMap.getOrDefault(genres[i], 0) + plays[i]);
		}

		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(gMap.entrySet());

		Collections.sort(list, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}

		});

		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<Music> sortList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			String genre = list.get(i).getKey();
			for (int j = 0; j < genres.length; j++) {
				if (genres[j].equals(genre)) {
					sortList.add(new Music(genre, plays[j], j));
				}
			}
			Collections.sort(sortList);
			if (sortList.size() >= 2) {
				for (int k = 0; k < 2; k++) {
					result.add(sortList.get(k).index);
				}
			}else {
				result.add(sortList.get(0).index);
			}
//        	printList(sortList);
			sortList.clear();
		}

		answer = result.stream().mapToInt(Integer::intValue).toArray();

//        printList(sortList);
//        print(result);

//		System.out.println("genres : " + o.genres + " plays : " + o.plays + " index : " + o.index);
//        printList(list);
		return answer;
    }
}