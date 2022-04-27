package me.programmers.first;

import org.w3c.dom.Node;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quest10 {

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        Map<Integer, List<String>> pixelIdxListMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<String> idxList;
                if (!pixelIdxListMap.containsKey(picture[i][j])) {
                    idxList = new ArrayList();
                } else {
                    idxList = pixelIdxListMap.get(picture[i][j]);
                }
                idxList.add(i + ":" + j);
                pixelIdxListMap.put(picture[i][j], idxList);
            }
        }

        pixelIdxListMap.forEach((pn, ls) -> {

            int keyNum = 0;
            for (String idx : ls) {
                String[] idxStr = idx.split(":");
                int i = Integer.parseInt(idxStr[0]);
                int j = Integer.parseInt(idxStr[1]);
                int p = picture[i][j];


                for (String sIdx : ls) {
                    String[] sIdxStr = idx.split(":");
                    int si = Integer.parseInt(idxStr[0]);
                    int sj = Integer.parseInt(idxStr[1]);
                    int sp = picture[si][sj];



                }

                boolean isArea = false;
                if (i > 0) {
                    isArea = isArea || (picture[i-1][j] == picture[i][j]);
                }
                if (j > 0) {
                    isArea = isArea || (picture[i][j-1] == picture[i][j]);
                }

//                picture[i][j];
            }

        });

        // ----------------------------------------------

        Map<String, List<String>> areaMap = new HashMap<>();
        for (int i = 0; i < m; i++) {

            List<String> tmpList = new ArrayList<>();

            for (int j = 0; j < n; j++) {

                String tmpStr = i + ":" + (j) + ":" + picture[i][j];
                boolean chkPixel = false;
                if (i==0 && j==0) {
                    chkPixel = true;
                }
                if (i > 0 && (picture[i-1][j] == picture[i][j])) {
                    chkPixel = true;
                    tmpList.add(i + ":" + (j-1) + ":" + picture[i][j]);
                }
                if (j > 0 && (picture[i][j+1] == picture[i][j])) {

                }
                if ( j < (picture[i].length - 1) && (picture[i][j+1] == picture[i][j])) {
                    chkPixel = true;
                    tmpList.add(i + ":" + (j + 1) + ":" + picture[i][j]);
                }

                if (!tmpList.contains(tmpStr) && chkPixel)
                    tmpList.add(tmpStr);

            }
        }









        // ----------------------------------------------



//        numberOfArea = areaList.size();

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        Quest10 quest10 = new Quest10();

        // expected : [4, 5]
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println("expected : [4, 5] >> result : " + quest10.solution(6, 4, picture));

    }
}
