package me.programmers.first;

import java.util.*;

public class Quest20 {

    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int s : scoville) {
            queue.add(s);
        }

        while (queue.peek() < K) {
            if (queue.size()<2) return -1;
            int a = queue.poll();
            int b = queue.poll();

            int scvl = a + (b * 2);
            queue.offer(scvl);
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Quest20 quest20 = new Quest20();
        // scoville : [1, 2, 3, 9, 10, 12], k : 7, result : 2
        int[] scoville = {1, 2, 3, 9, 10, 12};
        System.out.println(">> expected : 2 > result : " + quest20.solution(scoville, 7));

    }
}
