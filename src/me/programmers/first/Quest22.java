package me.programmers.first;

import java.util.*;

public class Quest22 {

    public int[] solution(int[] answers) {

        int[][] student = {{1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] grades = new int[student.length];

        for (int g=0; g<grades.length; g++) {
            grades[g] = gradeTests(student[g], answers);
        }

        int topGrade = 0;

        for (int i=0; i<grades.length; i++) {
            if (grades[i] > topGrade)
                topGrade = grades[i];
        }

        List<Integer> winners = new ArrayList<>();
        for (int j=0; j<grades.length; j++) {
            if (grades[j] == topGrade)
                winners.add(j+1);
        }
        return winners.stream().mapToInt(w-> w.intValue()).toArray();
    }

    public int[] solution2(int[] answers) {

        int[] grades = new int[3];
        int[] student1 = {1, 2, 3, 4, 5};
        int grade1 = gradeTests(student1, answers);
        grades[0] = grade1;

        int[] student2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int grade2 = gradeTests(student2, answers);
        grades[1] = grade2;

        int[] student3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int grade3 = gradeTests(student3, answers);
        grades[2] = grade3;

        int topGrade = 0;
        for (int i=0; i<grades.length; i++) {
            if (grades[i] > topGrade)
                topGrade = grades[i];
        }

        List<Integer> winners = new ArrayList<>();
        for (int j=0; j<grades.length; j++) {
            if (grades[j] == topGrade)
                winners.add(j+1);
        }
        return winners.stream().mapToInt(w-> w.intValue()).toArray();
    }

    public int gradeTests(int[] student, int[] answers) {
        int grade = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<student.length; i++) {
            queue.add(student[i]);
        }
        for (int j=0; j<answers.length; j++) {
            int marked = queue.poll();

            if (answers[j] == marked) grade++;

            queue.offer(marked);
        }
        return grade;
    }

    public static void main(String[] args) {
        Quest22 quest22 = new Quest22();

        // answers : [1,2,3,4,5], return : 	[1]
        int[] answers = {1, 2, 3, 4, 5};
        int[] result = quest22.solution(answers);
        System.out.print(">> expected : [1] > result : [");
        Arrays.stream(result).forEach(r-> {
            System.out.print(r + ", ");
        });
        System.out.println("]");


        // answers : [1,3,2,4,2], return :	[1,2,3]
        int[] answers2 = {1,3,2,4,2};
        int[] result2 = quest22.solution(answers2);
        System.out.print(">> expected : [1,2,3] > result : [");
        Arrays.stream(result2).forEach(r-> {
            System.out.print(r + ", ");
        });
        System.out.println("]");
    }
}
