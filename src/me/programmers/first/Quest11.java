package me.programmers.first;

import java.util.*;
import java.util.stream.Collectors;

public class Quest11 {

//    List<List<String>> positionList;

    int answer;
    String[] members;

    String[] data;

    public int solution(int n, String[] data) {
        this.members = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
        this.data = data;
        this.answer = 0;

        makePosition(new String[0], members, 0, members.length);

        return answer;
    }

    public void makePosition(String[] position, String[] members, int depth, int len) {
//        depth = depth+1;
        // members = {"A", "C", "F", "J", "M", "N", "R", "T"};
//        List<String> memberList = new ArrayList<>();
//        memberList.add(members.get(depth-1));
        if (position.length == len) {
            if (!chkPosition(position)) {
//                Arrays.asList(position).forEach(m -> {
//                    System.out.print(m + ", ");
//                });
//                System.out.println();
                answer++;
            }
            return;
        }



//        String[] result = new String[position.length + 1];
//        for (int p=0; p<position.length; p++) {
//            result[p] = position[p];
//        }

        List<String> result = new ArrayList<>();
        for (int p=0; p<position.length; p++) {
            result.add(position[p]);
        }

        for (int i=0; i<members.length; i++) {
            result.add(members[i]);
            List<String> tmpMbrList = new ArrayList<>();
            for (int k=0; k< members.length; k++) {
                if (!result.contains(members[k])) {
                    tmpMbrList.add(members[k]);
                }
            }
            result.forEach(m -> {
                System.out.print(m + ", ");
            });
            System.out.print(" = " + tmpMbrList.size()+ " => " );
            tmpMbrList.forEach(m -> {
                System.out.print(m + ", ");
            });
            System.out.println();

//            String[] rsltParams = result.toArray(String[]::new);
//            String[] mbrParams = tmpMbrList.toArray(String[]::new);
            makePosition(result.toArray(String[]::new), tmpMbrList.toArray(String[]::new), depth, len);
        }






    }



    public boolean chkPosition(String[] position) {

        boolean result = false;

//        System.out.println("-------------------------- ");
        for (String filter : data) {
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
