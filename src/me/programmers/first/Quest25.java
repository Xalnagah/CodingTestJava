package me.programmers.first;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quest25 {
    public int solution(int[][] triangle) {
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < triangle.length; i++) {
            List<Integer> tmpList = new ArrayList<>();
            if (i == 0) {
                tmpList.add(triangle[i][0]);
            } else {
                List<Integer> bfList = new ArrayList<>();
                bfList = answer.get(i-1);
                for (int j=0; j<triangle[i].length; j++) {
                    if (j==0) {
                        tmpList.add(bfList.get(j) + triangle[i][j]);
                    } else if (j >= bfList.size()) {
                        tmpList.add(bfList.get(j - 1) + triangle[i][j]);
                    } else {
                        int lg = Integer.max(bfList.get(j - 1) + triangle[i][j], bfList.get(j) + triangle[i][j]);
                        tmpList.add(lg);
                    }
                }
            }
            answer.add(tmpList);
        }

        return Collections.max(answer.get(answer.size()-1));
    }

    public static void main(String[] args) {
        Quest25 quest25 = new Quest25();
        int[][] triangle = {{7}, {3, 8},{8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(">> expected : 30 > result : " + quest25.solution(triangle));
    }
}
