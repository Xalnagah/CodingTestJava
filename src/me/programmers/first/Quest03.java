package me.programmers.first;

public class Quest03 {
    public static String solution(String new_id) {
        //1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
        new_id = new_id.toLowerCase();
        System.out.println("1. 단계 : " + new_id);
        //2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
//        String answer = new_id.replaceAll("^[a-zA-Z-_.]", "");
        String answer = "";

        answer = new_id.replaceAll("[^a-z0-9-_.]", "");
        System.out.println("2. 단계 : " + answer);

        //3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        answer = answer.replaceAll("[.]{2,}", ".");
        System.out.println("3. 단계 : " + answer);

        //4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        answer = answer.replaceAll("^[.]|[.]$", "");
        System.out.println("4. 단계 : " + answer);

        //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        answer = (answer.length() == 0) ? "a" : answer;
        System.out.println("5. 단계 : " + answer);

        //6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        //만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if (answer.length()>=16) {
            System.out.println(answer.length());
            System.out.println(answer);
            answer = answer.substring(0, 15);
            System.out.println(answer);
            answer = answer.replaceAll("[.]$", "");
        }
        System.out.println("6. 단계 : " + answer);
        //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if (answer.length() <= 2) {
            System.out.println("tmp : " + answer.length());
            for (int i = answer.length(); i < 3; i++) {
                answer += answer.charAt(answer.length()-1);
            }
        }
        System.out.println("7. 단계 : " + answer);

        return answer;
    }

    public static void main(String[] args) {
        // case 1. expected "bat.y.abcdefghi"
        System.out.println("expected : bat.y.abcdefghi -> answer : " + Quest03.solution("...!@BaT#*..y.abcdefghijklm"));

        // case 2.expected 	"z--"
        System.out.println("expected : z-- -> answer : " + Quest03.solution("z-+.^."));

        // case 3.expected  "aaa"
        System.out.println("expected : aaa -> answer : " + Quest03.solution("=.="));

        // case 4.expected  "123_.def"
        System.out.println("expected : 123_.def -> answer : " + Quest03.solution("123_.def"));

        // case 5.expected  "abcdefghijklmn"
        System.out.println("expected : abcdefghijklmn -> answer : " + Quest03.solution("abcdefghijklmn.p"));
    }

}
