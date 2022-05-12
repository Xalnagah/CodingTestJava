package me.programmers.first;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Quest21 {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        answer = new int[commands.length];
        for (int i=0; i<commands.length; i++) {
            answer[i] = getNumber(array, commands[i]);
        }
        return answer;
    }

    public int getNumber(int[] array, int[] command) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int startIdx = command[0]-1;
        int lastIdx = command[1] -1;
        int targetIdx = command[2]-1;
        for (int i=startIdx; i<=lastIdx; i++) {
            queue.add(array[i]);
        }
        int[] resultArr = new int[queue.size()];
        for (int j=0; j<resultArr.length; j++) {
            int tmp = queue.poll();
            resultArr[j] = tmp;
        }

        return resultArr[targetIdx];
    }

    public static void main(String[] args) {

        Quest21 quest21 = new Quest21();
        // array : [1, 5, 2, 6, 3, 7, 4], commands : [[2, 5, 3], [4, 4, 1], [1, 7, 3]], result : [5, 6, 3]
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] result = quest21.solution(array, commands);
        System.out.print(">> expected : [5, 6, 3] > result : [");
        Arrays.stream(result).forEach(r-> {
            System.out.print(r + ", ");
        });
        System.out.println("]");
    }
}
