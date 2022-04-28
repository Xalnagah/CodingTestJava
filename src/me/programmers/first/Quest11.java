package me.programmers.first;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Quest11 {

    List<List<String>> positionList;

    public int solution(int n, String[] data) {
        int answer = 0;

        String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};
        int memCnt = members.length;
        positionList = new ArrayList<>();

        makePosition(members, 0);

        answer = positionList.stream()
                .filter(postion -> chkPosition(postion, data))
                .collect(Collectors.toList())
                .size();

        return answer;
    }

    public void makePosition(String[] members, int startIdx) {
        int len = members.length;
        if (startIdx == len -1) {
            List<String> mLs = new ArrayList<>();
            for (String m : members) {
                mLs.add(m);
            }
            positionList.add(mLs);
            return;
        }

        for (int i=startIdx; i<len; i++) {
            swap(members, startIdx, i);
            makePosition(members, startIdx + 1);
            swap(members, startIdx, i);
        }
    }

    public void swap(String[] members, int a, int b) {
        String tmp = members[a];
        members[a] = members[b];
        members[b] = tmp;
    }

    public boolean chkPosition(List<String> position, String[] filters) {

        boolean result = false;

        for (String filter : filters) {
            String member = filter.substring(0, 1);
            String target = filter.substring(2, 3);
            String comp = filter.substring(3, 4);

            int mIdx = position.indexOf(member);
            int tIdx = position.indexOf(target);
            int num = Integer.parseInt(filter.substring(4, 5));

            int between = Math.abs(mIdx - tIdx);
            switch (comp) {
                case ">":
                    result = result || (between > num);
                    break;
                case "<":
                    result = result || (between < num);
                    break;
                default: // "="
                    result = result || (between == 1);
                    break;
            }
        }

        return result;
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
