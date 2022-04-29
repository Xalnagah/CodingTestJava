package me.programmers.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Quest11 {

    List<List<String>> positionList;

    int answer;
    String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};

    String[] chkData;

    public int solution(int n, String[] data) {
        answer = 0;
        chkData = data;

        int memCnt = members.length;
        positionList = new ArrayList<>();

//        makePosition(members, 0);
//        makePosition2(members);

//        answer = positionList.stream()
//                .filter(postion -> chkPosition(postion, data))
//                .collect(Collectors.toList())
//                .size();

        return answer;
    }

    public void makePosition(String[] members, int depth) {

        int len = members.length;
        if (depth == len) {
            if (chkPosition(members, chkData)) {
                answer++;
            }
            return;
        }

        for (int i=depth; i<len; i++) {
            swap(members, depth, i);
            makePosition(members, depth + 1);
            swap(members, depth, i);
        }
    }


    public void makePosition2(String[] members, int d, int len) {

    }

    public void swap(String[] members, int a, int b) {
        String tmp = members[a];
        members[a] = members[b];
        members[b] = tmp;
    }

    public boolean chkPosition(String[] position, String[] filters) {

        boolean result = false;

//        System.out.println("-------------------------- ");
        for (String filter : filters) {
            String member = filter.substring(0, 1);
            String target = filter.substring(2, 3);
            String comp = filter.substring(3, 4);
            int num = Integer.parseInt(filter.substring(4, 5));
            int mIdx = Arrays.asList(position).indexOf(member);
            int tIdx = Arrays.asList(position).indexOf(target);

            int between = Math.abs(mIdx - tIdx) -1;
            switch (comp) {
                case ">":
                    result = result || (between > num);
//                    System.out.println(" > " + num + " : " + Arrays.toString(position) + " - " + member + " : " + target + ", between : " + between + ", result : " + result + ", mIdx : " + mIdx + ", tIdx : " + tIdx);
                    break;
                case "<":
                    result = result || (between < num);
//                    System.out.println(" < " + num + " : " + Arrays.toString(position) + " - " + member + " : " + target + ", between : " + between + ", result : " + result);
                    break;
                case "=": // "="
                    result = result || (between == num);
//                    System.out.println(" = " + num + " : " + Arrays.toString(position) + " - " + member + " : " + target + ", between : " + between + ", result : " + result + ", mIdx : " + mIdx + ", tIdx : " + tIdx);
                    break;
                default:
                    break;
            }
        }
        return !result;
    }



    public static void main(String[] args) {

        Quest11 quest11 = new Quest11();

        /*
        2	["N~F=0", "R~T>2"]	3648
        2	["M~C<2", "C~M>1"]	0
         */

        // expect : 3648
        String[] data1 = {"N~F=0", "R~T>2"};
        System.out.println(">>> expected : 3648 > result : " + quest11.solution(2, data1));

        // expect : 0
        String[] data2 = {"M~C<2", "C~M>1"};
        System.out.println(">>> expected : 0 > result : " + quest11.solution(2, data2));
    }
}
