package me.programmers.first;

import java.util.HashMap;
import java.util.Map;

public class Quest17 {

    String[] numbers = {"4", "1", "2"};

    public String solution(int n) {
        String answer = "";

        answer = conversion(n);

        return answer;
    }

    public String conversion(int n) {
        StringBuilder sb = new StringBuilder();
        int currNum = n;
        while (currNum > 0) {
            int tmp = currNum % 3;
            sb.append(numbers[tmp]);
            currNum = currNum / 3;
            if (tmp==0) {
                currNum--;
            }
        }
        return sb.reverse().toString();
    }

    public String conversion3(int n) {
        String result = "";
        while (n>0) {
            int tmp = n % 3;
            result = numbers[tmp] + result;
            n = n / 3 - ( (tmp == 0) ? 1 : 0 );
        }
        return result;
    }


    public static void main(String[] args) {

        Quest17 quest17 = new Quest17();

        // input : 1,   result : 1
        System.out.println(">>> expected : 1 > result : " + quest17.solution(1));
        // input : 2,   result : 2
        System.out.println(">>> expected : 2 > result : " + quest17.solution(2));
        // input : 3,   result : 4
        System.out.println(">>> expected : 4 > result : " + quest17.solution(3));
        // input : 4,   result : 11
        System.out.println(">>> expected : 11 > result : " + quest17.solution(4));
        // input : 5,   result : 12
        System.out.println(">>> expected : 12 > result : " + quest17.solution(5));
        // input : 13,   result : 111
        System.out.println(">>> expected : 111 > result : " + quest17.solution(13));
        // input : 19,   result : 141
        System.out.println(">>> expected : 141 > result : " + quest17.solution(19));
        // input : 25,   result : 221
        System.out.println(">>> expected : 221 > result : " + quest17.solution(25));

    }

}
