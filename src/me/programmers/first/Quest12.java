package me.programmers.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quest12 {


    List<Integer> numberSet;

    public int solution(int[] numbers) {

        int answer = 0;
        int[] numberSet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        for (int ns : numberSet) {
            boolean chk = false;
            for (int n : numbers) {
                if (ns == n) chk=true;
            }
            if (!chk) answer+=ns;
        }
        return answer;
    }

    public static void main(String[] args) {
        Quest12 quest12 = new Quest12();
        // input : [1,2,3,4,6,7,8,0]   exptected : 14
        int[] numbers1 = {1,2,3,4,6,7,8,0};
        System.out.println(">>  expected : 14 > result : " + quest12.solution(numbers1));
        // input : [5,8,4,0,6,7,9]   exptected : 6
        int[] numbers2 = {5,8,4,0,6,7,9};
        System.out.println(">>  expected : 6 > result : " + quest12.solution(numbers2));

    }
}
