package me.programmers.first;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Quest05 {

    public static String[] solution(String[] record) {
        List<String> answer = new ArrayList();
        Map<String, String> users = new HashMap<>();
        List<Map> logs = new ArrayList();
        for (String text : record) {
            String[] data = text.split(" ");
            Map<String, String> log = new HashMap<>();
            switch (data[0]) {
                case "Enter":
                    users.put(data[1], data[2]);
                    log.put("id", data[1]);
                    log.put("status", data[0]);
                    logs.add(log);
                    break;
                case "Leave":
                    log.put("id", data[1]);
                    log.put("status", data[0]);
                    logs.add(log);
                    break;
                case "Change":
                    users.put(data[1], data[2]);
                    break;
                default:
                    break;
            }
        }

        for (Map log : logs) {
            String text = users.get(log.get("id")) + "님이";
            if (log.get("status").equals("Enter")) {
                text += " 들어왔습니다.";
            } else if (log.get("status").equals("Leave")) {
                text += " 나갔습니다.";
            }
            answer.add(text);
        }
        return answer.toArray(String[]::new);
    }

    public static void main(String[] args) {

        // record : ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]
        // result : ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
        String[] logData = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        Arrays.stream(Quest05.solution(logData)).forEach(System.out::println);
    }
}
