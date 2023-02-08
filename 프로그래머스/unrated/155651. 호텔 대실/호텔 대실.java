import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Time{
	int x, y;
	Time(int x, int y){
		this.x = x;
		this.y = y;
	}
}


class Solution {
    
     public static int toMinute(String time) {
		StringTokenizer stk = new StringTokenizer(time, ":");
		int hour = Integer.parseInt(stk.nextToken());
		int minute = Integer.parseInt(stk.nextToken());
		return hour * 60 + minute;
	}

	public static int solution(String[][] book_time) {
		int answer = 0;
		List<Time> bookTime = new ArrayList<>();

		for (int i = 0; i < book_time.length; i++) {
			int start_time = toMinute(book_time[i][0]);
			int end_time = toMinute(book_time[i][1]);
			bookTime.add(new Time(start_time, end_time));
		}

		Collections.sort(bookTime, (o1, o2) -> {
			if (o1.x == o2.x)
				return o1.y - o2.y;
			else
				return o1.x - o2.x;
		});

		List<Integer> endTimeList = new ArrayList<>();

		for (Time book : bookTime) {
			boolean isOk = false;

			Collections.sort(endTimeList);

			for (int i = 0; i < endTimeList.size(); i++) {
				int endTime = endTimeList.get(i) + 10;
				if (book.x >= endTime) {
					endTimeList.set(i, book.y);
					isOk = true;
					break;
				}
			}

			if (!isOk) {
				endTimeList.add(book.y);
			}
		}

		return endTimeList.size();
	}

}