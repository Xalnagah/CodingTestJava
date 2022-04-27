package me.programmers.first;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;

public class Quest09 {

    public int solution(int[][] board, int[] moves) {

        int answer = 0;

        List<Integer> bucket = new ArrayList<>();
        for (int move : moves) {
            int moveIdx = move - 1;
            for (int i=0; i<board.length; i++) {
                int item = board[i][moveIdx];
                if (item > 0) {
                    if (bucket.size() > 0 && bucket.get(bucket.size()-1) == item) {
                        bucket.remove(bucket.size()-1);
                        answer += 2;
                    } else {
                        bucket.add(item);
                    }
                    board[i][moveIdx] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Quest09 quest09 = new Quest09();

        // board : [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]
        // moves : [1,5,3,5,1,2,1,4]
        // expected : 4
        int[][] board ={{0, 0, 0, 0, 0},{0, 0, 1, 0, 3},{0, 2, 5, 0, 1},{4, 2, 4, 4, 2},{3, 5, 1, 3, 1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(">>> expected : 4 >>> result : " + quest09.solution(board, moves));

    }
}
