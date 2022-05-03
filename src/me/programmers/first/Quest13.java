package me.programmers.first;

public class Quest13 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;

        for (int i=0; i<absolutes.length; i++) {
            int num = absolutes[i];
            if (!signs[i]) {
                num *= -1;
            }
            answer += num;
        }

        return answer;
    }

    public static void main(String[] args) {
        Quest13 quest13 = new Quest13();
        // input : [4,7,12], [true, false, true]    expected : 9
        int[] absolutes1 = {4,7,12};
        boolean[] signs1 = {true, false, true};
        System.out.println(">> expected : 9 > result : " + quest13.solution(absolutes1, signs1));
        int[] absolutes2 = {1,2,3};
        boolean[] signs2 = {false,false,true};
        System.out.println(">> expected : 0 > result : " + quest13.solution(absolutes2, signs2));

    }
}
