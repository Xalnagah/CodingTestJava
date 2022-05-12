package me.programmers.first;

public class Quest23 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {

        Quest23 quest23 = new Quest23();

        // n : 5, lost : [2, 4], reserve : [1, 3, 5], return : 5
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        System.out.println(">> expected : 5 > result : " + quest23.solution(5, lost, reserve));

        // n : 5, lost : [2, 4], reserve : [3], return : 4
        int[] lost1 = {2, 4};
        int[] reserve1 = {3};
        System.out.println(">> expected : 4 > result : " + quest23.solution(5, lost1, reserve1));

        // n : 3, lost : [3], reserve : [1], return : 2
        int[] lost2 = {3};
        int[] reserve2 = {1};
        System.out.println(">> expected : 2 > result : " + quest23.solution(3, lost2, reserve2));
    }
}
