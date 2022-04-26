package me.programmers.first;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Quest06 {

    // 각각의 Log에서 시작과 종료 지점의 millisecond 시간을 기준으로 각 Log의 데이터를 확인하는 방식 - 간결하고 빠름.
    public static int solution(String[] lines) {
        int answer = 0;

        List<Integer> millisList = new ArrayList<>();
        for (String line : lines) {
            Map<String, Integer> log = parseToMillis(line);
            millisList.add(log.get("start"));
            millisList.add(log.get("end"));
        }

        Collections.sort(millisList);

        for (int logTime : millisList) {
            int start = logTime;
            int end = logTime + 999;

            int cnt = 0;
            for (String line : lines) {
                Map<String, Integer> log = parseToMillis(line);
                if (end < log.get("start") || log.get("end") < start)
                    continue;
                cnt++;
            }
            answer = Integer.max(answer, cnt);
        }

        return answer;
    }

    public static Map<String, Integer> parseToMillis(String line) {
        String[] data = line.substring(11, line.length()-1).split(" ");
        String[] time = data[0].split(":");

        double elapsed = Double.parseDouble(data[1]) * 1000;

        int hour = Integer.parseInt(time[0]) * 60 * 60 * 1000;
        int min = Integer.parseInt(time[1]) * 60 * 1000;
        double sec = Double.parseDouble(time[2]) * 1000;

        int endTime = hour + min + (int) sec;
        int startTime = endTime - (int) elapsed + 1;

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("start", startTime);
        resultMap.put("end", endTime);
        return resultMap;
    }


    // 날짜 데이터를 사용하여 millisecond 단위로 증가시켜 사용하는 단순하지만 느린 방식. - 가독성 위주
    public static int solution2(String[] lines) {
        int answer = 0;
        List<Map> lineList = new ArrayList<>();
        for (String line : lines) {
            String[] logData = line.split(" ");
            LocalDateTime endLog = LocalDateTime.of( LocalDate.parse(logData[0]), LocalTime.parse(logData[1]));
            Double elapsedTime = Double.parseDouble(logData[2].substring(0, logData[2].length()-1)) * 1000;
            LocalDateTime startLog = endLog.minus(Duration.ofMillis(elapsedTime.longValue()));
            LocalDateTime checkStart = endLog.minus(Duration.ofMillis(elapsedTime.longValue()-1));

            Map<String, Object> logMap = new HashMap<>();
            logMap.put("start", startLog);
            logMap.put("checkStart", checkStart);
            logMap.put("end", endLog);
            logMap.put("elapsed", elapsedTime.longValue());

            lineList.add(logMap);
        }

        /*
        List<Map> lineList = Arrays.stream(lines).map(line -> {
            String[] logData = line.split(" ");
            LocalDateTime endLog = LocalDateTime.of( LocalDate.parse(logData[0]), LocalTime.parse(logData[1]));
            Double elapsedTime = Double.parseDouble(logData[2].substring(0, logData[2].length()-1)) * 1000;
            LocalDateTime startLog = endLog.minus(Duration.ofMillis(elapsedTime.longValue()));
            LocalDateTime checkStart = endLog.minus(Duration.ofMillis(elapsedTime.longValue()-1));

            Map<String, Object> logMap = new HashMap<>();
            logMap.put("start", startLog);
            logMap.put("checkStart", checkStart);
            logMap.put("end", endLog);
            logMap.put("elapsed", elapsedTime.longValue());

            return logMap;
        }).collect(Collectors.toList());
        */

        // 시작시간
        LocalDateTime start = (LocalDateTime) lineList.get(0).get("start");
        LocalDateTime end = (LocalDateTime) lineList.get(lineList.size()-1).get("end");
        while (start.isBefore(end) || start.isEqual(end)) {
            // 1초 간격
            LocalDateTime from  = start.plus(Duration.ofMillis(1L));
            LocalDateTime to  = start.plusSeconds(1L);
            // 최대 처리수 조회
            int cnt = calculate(from, to, lineList);
            answer = Integer.max(answer, cnt);
            // 1ms 증가
            start = from;
        }
        return answer;
    }

    public static int calculate(LocalDateTime start, LocalDateTime end, List<Map> logs) {
        int result = 0;
        for (Map log : logs) {
            LocalDateTime startLog = (LocalDateTime) log.get("checkStart");
            LocalDateTime endLog = (LocalDateTime) log.get("end");
            if (start.isEqual(endLog) || end.isEqual(startLog))
                result++;
            else if (start.isAfter(endLog) || end.isBefore(startLog))
                continue;
            else result++;
        }
        return result;
    }

    public static int calculate2(LocalDateTime start, LocalDateTime end, String[] logs) {

        int result = 0;

        for (String log : logs) {
            String[] logData = log.split(" ");
            LocalDateTime endLog = getDateTime(log);
            Double runningTime = Double.parseDouble(logData[2].substring(0, logData[2].length()-1)) * 1000;
            LocalDateTime startLog = endLog.minus(Duration.ofMillis(runningTime.longValue()-1));

            if (start.isEqual(endLog) || end.isEqual(startLog))
                result++;
            else if (start.isAfter(endLog) || end.isBefore(startLog))
                continue;
            else result++;

        }
        return result;
    }

    public static LocalDateTime getDateTime(String log) {
        String[] logData = log.split(" ");
        return LocalDateTime.of( LocalDate.parse(logData[0]), LocalTime.parse(logData[1]));
    }

    public static void main(String[] args) {

        String[] sample01 = {
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };
        System.out.println("expected : 1 > result : " + Quest06.solution2(sample01));


        String[] sample01_02 = {
                "2016-09-15 23:59:59.999 0.001s"
        };
        System.out.println("expected : 1 > result : " + Quest06.solution2(sample01_02));


        String[] sample02 = {
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };
        System.out.println("expected : 2 > result : " + Quest06.solution2(sample02));
        String[] sample03 = {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };
        System.out.println("expected : 7 > result : " + Quest06.solution2(sample03));
    }



}
