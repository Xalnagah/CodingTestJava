package me.programmers.first;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Quest02 {
    public static int[] solution(int[] lottos, int[] win_nums) {
        // int[] answer = {};
        Map<Integer, Integer> rankBook = new HashMap<>();
        rankBook.put(6,1);
        rankBook.put(5,2);
        rankBook.put(4,3);
        rankBook.put(3,4);
        rankBook.put(2,5);
        rankBook.put(1,6);
        rankBook.put(0,6);

        int win_cnt = 0;
        int max_cnt = 0;
        for(int l : lottos) {
            if (l == 0) {
                max_cnt += 1;
                continue;
            }
            for(int w : win_nums) {
                if (l == w) {
                    win_cnt += 1;
                }
            }
        }
        max_cnt += win_cnt;
        int[] answer = {rankBook.get(max_cnt), rankBook.get(win_cnt)};
        return answer;
    }

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        // expected : [3, 5]
        Arrays.stream(Quest02.solution(lottos, win_nums)).forEach(a -> System.out.print(a + ", "));
        System.out.println();
        int[] lottos2 = {0, 0, 0, 0, 0, 0};
        int[] win_nums2 = {38, 19, 20, 40, 15, 25};
        // expected : [1, 6]
        Arrays.stream(Quest02.solution(lottos2, win_nums2)).forEach(s -> System.out.print(s + ", "));
        System.out.println();
        int[] lottos3 = {45, 4, 35, 20, 3, 9};
        int[] win_nums3 = {20, 9, 3, 45, 4, 35};
        // expected : [1, 1]
        Arrays.stream(Quest02.solution(lottos3, win_nums3)).forEach(s -> System.out.print(s + ", "));
        System.out.println();
    }
}
