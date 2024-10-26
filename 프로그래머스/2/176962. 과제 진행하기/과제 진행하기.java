import java.util.*;

class Solution {

    static Stack<String[]> stack = new Stack<>();
    static List<String> list = new ArrayList<>();
    static Stack<String[]> stack2 = new Stack<>();

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        // plans 배열을 시작 시간 기준으로 정렬
        Arrays.sort(plans, (o1, o2) -> {
            int time1 = parseTime(o1[1]);
            int time2 = parseTime(o2[1]);
            return Integer.compare(time1, time2);
        });

        // 정렬된 계획을 스택에 추가
        for (int i = plans.length - 1; i >= 0; i--) {
            stack.push(plans[i]);
        }

        // 계산 메서드 호출
        calculate();

        // 리스트에 저장된 결과를 배열로 반환
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public void calculate() {
        while (!stack.isEmpty()) {
            String[] currentTask = stack.pop();
            int currentTaskTime = parseTime(currentTask[1]);
            int currentTaskDuration = Integer.parseInt(currentTask[2]);

            // 다음 과제가 있는지 확인
            if (!stack.isEmpty()) {
                String[] nextTask = stack.peek();
                int nextTaskTime = parseTime(nextTask[1]);

                // 현재 과제가 다음 과제 시작 전에 끝나는 경우
                if (currentTaskTime + currentTaskDuration <= nextTaskTime) {
                    list.add(currentTask[0]); // 현재 과제를 완료
                    int remainingTime = nextTaskTime - (currentTaskTime + currentTaskDuration);

                    // 멈춘 과제를 이어서 할 수 있는지 확인
                    while (remainingTime > 0 && !stack2.isEmpty()) {
                        String[] pausedTask = stack2.pop();
                        int pausedTaskDuration = Integer.parseInt(pausedTask[2]);

                        if (remainingTime >= pausedTaskDuration) {
                            list.add(pausedTask[0]);
                            remainingTime -= pausedTaskDuration;
                        } else {
                            pausedTask[2] = String.valueOf(pausedTaskDuration - remainingTime);
                            stack2.push(pausedTask);
                            break;
                        }
                    }
                } else {
                    // 현재 과제가 다음 과제 시작 전에 끝나지 않는 경우
                    int remainingDuration = currentTaskDuration - (nextTaskTime - currentTaskTime);
                    currentTask[2] = String.valueOf(remainingDuration);
                    stack2.push(currentTask);
                }
            } else {
                // 스택이 비어있다면 마지막 과제이므로 결과에 추가
                list.add(currentTask[0]);

                // 멈춘 과제를 모두 완료
                while (!stack2.isEmpty()) {
                    list.add(stack2.pop()[0]);
                }
            }
        }
    }

    // HH:mm 형식의 시간을 분으로 변환하는 메서드
    public int parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
