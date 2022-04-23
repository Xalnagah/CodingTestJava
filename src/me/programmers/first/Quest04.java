package me.programmers.first;

import java.util.ArrayList;
import java.util.List;

public class Quest04 {

    public static int solution(String s) {

        int answer = s.length();
        for (int i=1; i<=s.length()/2; i++) {
            String zipStr = Quest04.zipFunc2(s, i);
            if (answer > zipStr.length()) {
                answer = zipStr.length();
            }
        }
        return answer;
    }

    // List 활용. - 가독성
    public static String zipFunc(String s, int len) {
        List<String> strList = new ArrayList<>();
        while (s.length() > 0) {
            if (s.length() < len) {
                len = s.length();
            }
            strList.add(s.substring(0, len));
            s = s.substring(len);
        }
        int cnt = 0;
        String tmpStr = "";
        String zipStr = "";
        for (int i=0; i < strList.size(); i++) {
            String str = strList.get(i);
            if (tmpStr.equals(str)) {
                cnt += 1;
            } else if (cnt > 0) {
                if (cnt > 1)
                    zipStr += cnt;
                zipStr += tmpStr;
                tmpStr = str;
                cnt = 1;
            } else {
                tmpStr = str;
                cnt = 1;
            }
            // 마지막 값
            if (i == strList.size()-1) {
                if (cnt > 1)
                    zipStr += cnt;
                zipStr += tmpStr;
            }
        }
        return zipStr;
    }

    // 불필요 코드 제거.
    public static String zipFunc2(String s, int len) {
        int cnt = 0;
        String tmpStr = "";
        String zipStr = "";
        while (s.length() > 0) {
            if (s.length() < len) {
                len = s.length();
            }
            String str = s.substring(0, len);
            s = s.substring(len);
            if (tmpStr.equals(str)) {
                cnt += 1;
            } else if (cnt > 0) {
                if (cnt > 1)
                    zipStr += cnt;
                zipStr += tmpStr;
                tmpStr = str;
                cnt = 1;
            } else {
                tmpStr = str;
                cnt = 1;
            }
            // 마지막 값
            if (s.length() == 0) {
                if (cnt > 1)
                    zipStr += cnt;
                zipStr += tmpStr;
            }
        }
        return zipStr;
    }

    public static void main(String[] args) {
        // aabbaccc => expected : 7
        System.out.println("> " + Quest04.solution("aabbaccc"));
        // ababcdcdababcdcd => expected : 9
        System.out.println("> " + Quest04.solution("ababcdcdababcdcd"));
        // abcabcdede => expected : 8
        System.out.println("> " + Quest04.solution("abcabcdede"));
        // abcabcabcabcdededededede => expected : 14
        System.out.println("> " + Quest04.solution("abcabcabcabcdededededede"));
        // xababcdcdababcdcd => expected : 17
        System.out.println("> " + Quest04.solution("xababcdcdababcdcd"));

    }
}
