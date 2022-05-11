package me.programmers.first;

import java.util.*;

public class Quest18 {

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> state = new HashMap();

        for (String pt : participant) {
            if (state.containsKey(pt)) {
                state.put(pt, state.get(pt)+1);
            } else {
                state.put(pt, 1);;
            }
        }

        for (String cmp : completion) {
            if (state.containsKey(cmp))
                state.put(cmp, state.get(cmp) - 1);
        }

        for (String name : state.keySet()) {
            if (state.get(name) > 0)
                answer = name;
        }

        return answer;
    }

    // 속도 느림....
    public String solution2(String[] participant, String[] completion) {
        String answer = "";
        List<String> participantList = new ArrayList<>();

        Collections.addAll(participantList, participant);
        for (int i=0; i<completion.length; i++) {
            String tmpCompletion = completion[i];
            for (int j=0; j<participantList.size(); j++) {
                if (participantList.get(j).equals(tmpCompletion)) {
                    participantList.remove(j);
                    break;
                }
            }
        }
        answer = participantList.get(0);
        return answer;
    }


    public static void main(String[] args) {
        Quest18 quest18 = new Quest18();

        // params : ["leo", "kiki", "eden"] [], expected : "leo"
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(">> expected : leo > result : " + quest18.solution(participant, completion));

        // params : ["leo", "kiki", "eden"] [], expected : "leo"
        String[] participant1 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion1 = {"josipa", "filipa", "marina", "nikola"};
        System.out.println(">> expected : vinko > result : " + quest18.solution(participant1, completion1));

        // params : ["leo", "kiki", "eden"] [], expected : "leo"
        String[] participant2 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion2 = {"stanko", "ana", "mislav"};
        System.out.println(">> expected : mislav > result : " + quest18.solution(participant2, completion2));

    }
}
