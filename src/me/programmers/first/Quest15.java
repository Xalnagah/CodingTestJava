package me.programmers.first;

public class Quest15 {

    public long solution(int w, int h) {
        long answer = 0;
        long a = w;
        long b = h;

        long gcdNum = gcd(a, b);


        long total = a * b;
        answer = total - (a + b - gcdNum);
        return answer;
    }

    public long gcd(long a, long b) {
        long w;
        long h;
        if (a>b) {
            w = a;
            h = b;
        } else {
            w = b;
            h = a;
        }

        long mod = w % h;
        while (mod > 0) {
            w = h;
            h = mod;
            mod = w % h;
        }
        return h;
    }


    public static void main(String[] args) {
        Quest15 quest15 = new Quest15();

        // input : 8, 12     expected : 80
        System.out.println(">> expected : 80 > result : " + quest15.solution(8, 12));
    }
}
