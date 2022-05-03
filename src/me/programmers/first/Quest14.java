package me.programmers.first;

public class Quest14 {
    public int solution(int[] a, int[] b) {
        int answer = 0;

        for (int i=0; i<a.length; i++) {
            int calc = a[i] * b[i];
            answer += calc;
        }

        return answer;
    }

    public static void main(String[] args) {
        Quest14 quest14 = new Quest14();

        // input : [1,2,3,4], [-3,-1,0,2],  expected : 3
        int[] a1 = {1,2,3,4};
        int[] b1 = {-3,-1,0,2};
        System.out.println(">> expected : 3 > result : " + quest14.solution(a1, b1));

        // input : [-1,0,1], [1,0,-1],  expected : -2
        int[] a2 = {-1,0,1};
        int[] b2 = {1,0,-1};
        System.out.println(">> expected : -2 > result : " + quest14.solution(a2, b2));
    }
}
