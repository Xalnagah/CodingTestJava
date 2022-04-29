package me.programmers.first;


import java.util.*;

public class Quest10_1 {

    boolean[][] chkPx;

    Map<Integer, Integer> pxCntMap;

    int numberOfArea;
    int maxSizeOfOneArea;

    public int[] solution(int m, int n, int[][] picture) {

        // 초기화
        chkPx = new boolean[m][n];
        pxCntMap = new HashMap<>();
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        int[][] board = new int[picture.length][picture[0].length];
        for(int i=0; i<board.length; i++){
            board[i] = picture[i].clone();
        }


        for (int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0 && !chkPx[i][j]) {
                    searchPixel(i, j, board);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public void searchPixel(int i, int j, int[][] pic) {
        if (chkPx[i][j]) return;
        else if (pic[i][j] == 0) {
            chkPx[i][j] = true;
            return;
        }

        Pixel startPx = new Pixel(i, j, pic[i][j]);
        Queue<Pixel> q = new LinkedList<>();
        q.add(startPx);

        chkPx[i][j] = true;
        int cnt = 1;

        while (!q.isEmpty()) {

            Pixel px = q.poll();
            List<Pixel> tmpPxList = new ArrayList<>();
            // px.m-1, px.n
            if (px.m-1>=0 && !chkPx[px.m-1][px.n])
                tmpPxList.add(new Pixel(px.m-1, px.n, pic[px.m-1][px.n]));
            // px.m+1, px.n
            if (px.m+1<pic.length && !chkPx[px.m+1][px.n])
                tmpPxList.add(new Pixel(px.m+1, px.n, pic[px.m+1][px.n]));
            // px.m, px.n-1
            if (px.n-1>=0 && !chkPx[px.m][px.n-1])
                tmpPxList.add(new Pixel(px.m, px.n-1, pic[px.m][px.n-1]));
            // px.m, px.n+1
            if (px.n+1<pic[0].length && !chkPx[px.m][px.n+1])
                tmpPxList.add(new Pixel(px.m, px.n+1, pic[px.m][px.n+1]));

            for (Pixel tmpPx : tmpPxList) {
                if (!pxCntMap.containsKey(pic[tmpPx.m][tmpPx.n])) {
                    pxCntMap.put(pic[tmpPx.m][tmpPx.n], 0);
                }
                if (startPx.cd == tmpPx.cd && !chkPx[tmpPx.m][tmpPx.n]) {
                    q.add(tmpPx);
                    chkPx[tmpPx.m][tmpPx.n] = true;
                    cnt++;
                }
            }
        }
        if (pxCntMap.containsKey(startPx.cd)) {
            int maxCnt = Integer.max(pxCntMap.get(startPx.cd), cnt);
            pxCntMap.put(startPx.cd, maxCnt);
        } else {
            pxCntMap.put(startPx.cd, cnt);
        }
        numberOfArea++;
        maxSizeOfOneArea = Integer.max(maxSizeOfOneArea, pxCntMap.get(startPx.cd));
    }


    class Pixel {
        int m;
        int n;
        int cd;
        public Pixel(int m, int n, int cd) {
            this.m = m;
            this.n = n;
            this.cd = cd;
        }
    }

    public static void main(String[] args) {
        Quest10_1 quest10 = new Quest10_1();

        // expected : [4, 5]
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[] result = quest10.solution(6, 4, picture);
        System.out.println("expected : [4, 5] >> result : [" + result[0] + "][" + result[1] + "]");
        // expected : [4, 8]
        int[][] picture2 = {{1, 1, 1, 0, 3},{1, 2, 2, 0, 3},{1, 0, 0, 1, 3},{0, 0, 0, 1, 3},{0, 0, 0, 3, 3},{0, 0, 0, 3, 3}};
        int[] result2 = quest10.solution(6, 5, picture2);
        System.out.println("expected : [4, 8] >> result : [" + result2[0] + "][" + result2[1] + "]");

        int[][] picture3 = {
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0},
                {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}
        };
        int[] result3 = quest10.solution(13, 16, picture3);
        System.out.println("expected : [12, 120] >> result : [" + result3[0] + "][" + result3[1] + "]");
    }
}
