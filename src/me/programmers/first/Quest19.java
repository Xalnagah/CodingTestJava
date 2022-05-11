package me.programmers.first;

import java.util.*;

public class Quest19 {

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> workList = new ArrayList<>();
        int curr = 0;
        for (int i=0; i<progresses.length; i++) {
            int tmp = 100-progresses[i];
            int dayCnt = tmp / speeds[i];
            if (tmp % speeds[i] > 0) {
                ++dayCnt;
            }

            if (curr > dayCnt) {
                dayCnt = curr;
            }
            workList.add(dayCnt);
            curr = dayCnt;

        }
        Map<Integer, Integer> workMap = new HashMap<>();
        for (int work : workList) {
            if (workMap.containsKey(work)) {
                workMap.put(work, workMap.get(work) + 1);
            } else {
                workMap.put(work, 1);
            }
        }
        answer = new int[workMap.size()];
        Object[] keys = workMap.keySet().stream().sorted().toArray();

        for(int i=0; i< keys.length; i++) {
            answer[i] = workMap.get(keys[i]);
        }
        /*
        Object[] values = workMap.values().toArray();
        for (int i=0; i< values.length; i++) {
            answer[i] = (int) values[i];
        }
        */

//        workList.forEach(v -> {
//            System.out.print(v + ", ");
//        });
//        System.out.println();

        return answer;
    }

    public static void main(String[] args) {
        Quest19 quest19 = new Quest19();

        // [93, 30, 55]	[1, 30, 5]	[2, 1]
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] result = quest19.solution(progresses, speeds);
        System.out.print(">> expected : [2, 1] > result : [");
        Arrays.stream(result).forEach(r-> {
            System.out.print(r + ", ");
        });
        System.out.println("]");

        // [95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        int[] result2 = quest19.solution(progresses2, speeds2);
        System.out.print(">> expected : [1, 3, 2] > result : [");
        Arrays.stream(result2).forEach(r-> {
            System.out.print(r + ", ");
        });
        System.out.println("]");
    }
}
