package me.programmers.first;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Quest01 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        String[] reports = Arrays.stream(report).distinct().toArray(String[]::new);
        Map<String, Integer> banUser = new HashMap<>();
        for (String r : reports) {
            String t = r.split(" ")[1];
            banUser.put(t, (banUser.containsKey(t)) ? banUser.get(t) + 1 : 1);
        }
        Map<String, Integer> emailMap = new HashMap<>();
        for (String r : reports) {
            String[] arr = r.split(" ");
            if (!emailMap.containsKey(arr[0])) {
                emailMap.put(arr[0], 0);
            }
            if (banUser.get(arr[1]) >= k) {
                emailMap.put(arr[0], emailMap.get(arr[0]) + 1);
            }
            answer[Arrays.asList(id_list).indexOf(arr[0])] = emailMap.get(arr[0]);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        Arrays.stream(Quest01.solution(id_list, report, k)).forEach((s) -> {
            System.out.print(s + ",");
        });
        System.out.println();
        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k2 = 2;
        Arrays.stream(Quest01.solution(id_list2, report2, k2)).forEach((s) -> {
            System.out.print(s + ",");
        });
    }

}
