package me.programmers.first;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Quest10_1 {

    class Pixel {
        int m;
        int n;
        int cd;
        public Pixel(int m, int n) {
            this.m = m;
            this.n = n;
        }
        public Pixel(int m, int n, int cd) {
            this.m = m;
            this.n = n;
            this.cd = cd;
        }
    }


    Map<Integer, Integer> pxCntMap = new HashMap<>();
    boolean[][] chkPx;
    int maxSizeOfOneArea = 0;

    int[] calcM = {-1, 1, 0, 0};
    int[] calcN = {0, 0, -1, 1};


    public int[] solution(int m, int n, int[][] picture) {

        int numberOfArea = 0;


        // 초기화
        chkPx = new boolean[m][n];

        for (int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] > 0 && !chkPx[i][j]) {
//                    System.out.println(">> px value : " + picture[i][j] + " > i : " + i + " > j : " + j);
                    pixelCount(i, j, picture);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = pxCntMap.size();
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public void pixelCount(int i, int j, int[][] picture) {
        if (chkPx[i][j]) return;
        else if (picture[i][j] == 0) {
            chkPx[i][j] = true;
            return;
        }

        Pixel startPx = new Pixel(i, j, picture[i][j]);
        Queue<Pixel> q = new LinkedList<>();
        q.add(startPx);

        chkPx[i][j] = true;
        int cnt = 1;

        while (!q.isEmpty()) {

            Pixel px = q.poll();

            // px.m-1, px.n
            // px.m+1, px.n
            // px.m, px.n-1
            // px.m, px.n+1
            for (int c=0; c<4; c++) {
                int m = px.m + calcM[c];
                int n = px.n + calcN[c];

                if (m >=0 && m < picture.length && n >= 0 && n < picture[0].length) {
                    if (!pxCntMap.containsKey(picture[m][n])) {
                        pxCntMap.put(picture[m][n], 0);
                    }
                    if(startPx.cd == picture[m][n] && !chkPx[m][n]) {
                        q.add(new Pixel(m, n, picture[n][n]));
                        chkPx[m][n] = true;
                        cnt++;
                    }
                }
            }
        }
        if (pxCntMap.containsKey(startPx.cd)) {
            int maxCnt = Integer.max(pxCntMap.get(startPx.cd), cnt);
            pxCntMap.put(startPx.cd, maxCnt);
        } else {
            pxCntMap.put(startPx.cd, cnt);
        }

        maxSizeOfOneArea = Integer.max(maxSizeOfOneArea, pxCntMap.get(startPx.cd));
    }

    public static void main(String[] args) {
        Quest10_1 quest10 = new Quest10_1();

        // expected : [4, 5]
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[] result = quest10.solution(6, 4, picture);
        System.out.println("expected : [4, 5] >> result : [" + result[0] + "][" + result[1] + "]");

    }
}
