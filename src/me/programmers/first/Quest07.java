package me.programmers.first;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Quest07 {
    public static int solution(String s) {

        Map<String, String> engNumMap = new HashMap<>();
        engNumMap.put("zero", "0");
        engNumMap.put("one", "1");
        engNumMap.put("two", "2");
        engNumMap.put("three", "3");
        engNumMap.put("four", "4");
        engNumMap.put("five", "5");
        engNumMap.put("six", "6");
        engNumMap.put("seven", "7");
        engNumMap.put("eight", "8");
        engNumMap.put("nine", "9");

        Set<String> strings = engNumMap.keySet();
        for (String engName : strings) {
            s = s.replaceAll(engName, engNumMap.get(engName));
        }

        return Integer.parseInt(s);
    }

    public static void main(String[] args) {

        String param1 = "one4seveneight";
        // expected : 1478
        System.out.println(">>> expected : 1478 > result : " + Quest07.solution(param1));

        String param2 = "23four5six7";
        // expected : 234567
        System.out.println(">>> expected : 234567 > result : " + Quest07.solution(param2));

        String param3 = "2three45sixseven";
        // expected : 234567
        System.out.println(">>> expected : 234567 > result : " + Quest07.solution(param3));

        String param4 = "123";
        // expected : 123
        System.out.println(">>> expected : 123 > result : " + Quest07.solution(param4));
    }
}
