package me.programmers.first;

import org.w3c.dom.Node;

import java.sql.Array;
import java.util.*;

public class Quest10 {

    int size;
    boolean[][] chkPixel;

    public int[] solution(int m, int n, int[][] picture) {

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        chkPixel= new boolean[m][n];
        size = 0;

        for (int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                if(picture[i][j] > 0 && !chkPixel[i][j]){
                    numberOfArea += 1;
                    searchPixel(i, j, picture);
                }
                maxSizeOfOneArea = Integer.max(maxSizeOfOneArea, size);
                size = 0;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public void searchPixel(int i, int j, int[][] picture) {
        chkPixel[i][j] = true;
        size += 1;

        int m = picture.length;
        int n = picture[0].length;

        if(0 <= (i+1) && (i+1) < m && 0 <= j && j < n) {
            if(picture[i+1][j] == picture[i][j] && chkPixel[i+1][j] != true){
                searchPixel(i+1, j, picture);
            }
        }
        if(0 <= (i-1) && (i-1) < m && 0 <= j && j < n) {
            if(picture[i-1][j] == picture[i][j] && chkPixel[i-1][j] != true){
                searchPixel(i-1, j, picture);
            }
        }
        if(0 <= i && i < m && 0 <= (j+1) && (j+1) < n) {
            if(picture[i][j+1] == picture[i][j] && chkPixel[i][j+1] != true){
                searchPixel(i, j+1, picture);
            }
        }
        if(0 <= i && i < m && 0 <= (j-1) && (j-1) < n) {
            if(picture[i][j-1] == picture[i][j] && chkPixel[i][j-1] != true){
                searchPixel(i, j-1, picture);
            }
        }

    }

    public static void main(String[] args) {
        Quest10 quest10 = new Quest10();

        // expected : [4, 5]
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[] result = quest10.solution(6, 4, picture);
        System.out.println("expected : [4, 5] >> result : [" + result[0] + ", " + result[1] + "]" );

    }
}
