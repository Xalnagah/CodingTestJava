package me.programmers.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Quest23 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        List<Integer> lostList = Arrays.stream(lost)
                                        .boxed()
                                        .sorted()
                                        .collect(Collectors.toList());
        List<Integer> reserveList = Arrays.stream(reserve)
                                            .boxed()
                                            .sorted()
                                            .collect(Collectors.toList());

        for (int l : lost) {
            if (reserveList.contains(l)) {
                lostList.remove((Integer) l);
                reserveList.remove((Integer) l);
                answer++;
            }
        }

        lost = lostList.stream().mapToInt(l -> l).toArray();
        reserve = reserveList.stream().mapToInt(r -> r).toArray();

        boolean[] chkReserve = new boolean[reserve.length];
        for (int i=0; i<lost.length; i++) {

            for (int j=0; j<reserve.length; j++) {
                if (!chkReserve[j]) {
                    if ((lost[i]-1) == reserve[j] || (lost[i]+1) == reserve[j]) {
                        answer++;
                        chkReserve[j] = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }
    public int solution2(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        List<Integer> lostList = new ArrayList<>();
        for (int l : lost) {
            lostList.add(l);
        }
        Collections.sort(lostList);

        List<Integer> reserveList = new ArrayList<>();
        for (int r : reserve) {
            reserveList.add(r);
        }
        Collections.sort(reserveList);
        for (int l : lost) {
            if (reserveList.contains(l)) {
                lostList.remove((Integer) l);
                reserveList.remove((Integer) l);
                answer++;
            }
        }

        lost = lostList.stream().mapToInt(l -> l).toArray();
        reserve = reserveList.stream().mapToInt(r -> r).toArray();

        boolean[] chkReserve = new boolean[reserve.length];
        for (int i=0; i<lost.length; i++) {

            for (int j=0; j<reserve.length; j++) {
                if (!chkReserve[j]) {
                    if ((lost[i]-1) == reserve[j] || (lost[i]+1) == reserve[j]) {
                        answer++;
                        chkReserve[j] = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Quest23 quest23 = new Quest23();

        // n : 5, lost : [2, 4], reserve : [1, 3, 5], return : 5
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        System.out.println(">> expected : 5 > result : " + quest23.solution(5, lost, reserve));

        // n : 5, lost : [2, 4], reserve : [3], return : 4
        int[] lost1 = {2, 4};
        int[] reserve1 = {3};
        System.out.println(">> expected : 4 > result : " + quest23.solution(5, lost1, reserve1));

        // n : 3, lost : [3], reserve : [1], return : 2
        int[] lost2 = {3};
        int[] reserve2 = {1};
        System.out.println(">> expected : 2 > result : " + quest23.solution(3, lost2, reserve2));

        // n : 3, lost : [3], reserve : [1], return : 2
        int[] lost3 = {1, 2, 4};
        int[] reserve3 = {2, 4, 5};
        System.out.println(">> expected : 4 > result : " + quest23.solution(5, lost3, reserve3));

        // n : 3, lost : [3], reserve : [1], return : 2
        int[] lost4 = {1, 2, 4};
        int[] reserve4 = {2, 3, 4, 5};
        System.out.println(">> expected : 4 > result : " + quest23.solution(5, lost4, reserve4));
    }
}
