package me.programmers.first;

import java.util.ArrayList;
import java.util.List;

public class Quest16 {

    public int solution(int[] nums) {
        int answer = 0;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
//        System.out.println("Hello Java");
        boolean[] chkPosition = new boolean[nums.length];
        for (int i=0; i<nums.length-2; i++) {
            for (int j=i+1; j<nums.length-1; j++) {
                for (int k=j+1; k<nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (chkPrimeNumber(sum)) {
                        System.out.printf("[%d,%d,%d]를 이용해서 %d을 만들 수 있습니다.%n", nums[i], nums[j], nums[k], sum);
                        answer++;
                    }
                }
            }
        }
        return answer;
    }



    public boolean chkPrimeNumber(int num) {
        boolean result = false;
        for (int n=2; n<num; n++) {
            int calc = num % n;
            if (calc == 0) {
                result = true;
            }
        }
        return !result;
    }


    public static void main(String[] args) {
        Quest16 quest16 = new Quest16();

        // input : [1,2,3,4]    > expected : 1
        int[] numbers1 = {1,2,3,4};
//        System.out.println(">>> expected : 1 > result : " + quest16.solution(numbers1));

        // input : [1,2,7,6,4]    > expected : 4
        int[] numbers2 = {1,2,7,6,4};
        System.out.println(">>> expected : 4 > result : " + quest16.solution(numbers2));
    }
}
