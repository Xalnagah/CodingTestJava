package me.programmers.first;

public class Quest08 {

    public String solution(int[] numbers, String mainHand) {

        String answer = "";

        HandStatus handStatus = new HandStatus(mainHand);
        for (int num : numbers) {
            handStatus.setSelectNumber(num);
            answer += handStatus.getCurrentHand();
        }

        return answer;
    }


    class HandStatus {
        private int selectNumber;
        private int leftPosition;
        private int rightPosition;

        private String currentHand;
        private int currentPosition;
        private String mainHand;

        public HandStatus(String mainHand) {

            if (mainHand.equals("right"))
                mainHand = "R";
            else if (mainHand.equals("left"))
                mainHand = "L";

            this.mainHand = mainHand;
            this.currentHand = mainHand;
            this.leftPosition = 10;
            this.rightPosition = 12;
        }

        public void setSelectNumber(int num) {
            this.selectNumber = num;
            this.reloadPosition();
        }

        public void reloadPosition() {
            if (this.selectNumber == 0) {
                this.currentPosition = 11;
            } else {
                this.currentPosition = this.selectNumber;
            }

            if (this.selectNumber == 1 || this.selectNumber == 4 || this.selectNumber == 7) {
                this.currentHand = "L";
                this.leftPosition = this.currentPosition;
            } else if (this.selectNumber == 3 || this.selectNumber == 6 || this.selectNumber == 9){
                this.currentHand = "R";
                this.rightPosition = this.currentPosition;
            } else {
                int leftCnt = getMoveCount(leftPosition, this.currentPosition);
                int rightCnt = getMoveCount(rightPosition, this.currentPosition);

                if (leftCnt < rightCnt) {
                    this.currentHand = "L";
                    this.leftPosition = this.currentPosition;
                } else if (leftCnt > rightCnt) {
                    this.currentHand = "R";
                    this.rightPosition = this.currentPosition;
                } else {
                    if (mainHand.equals("L")) {
                        this.currentHand = "L";
                        this.leftPosition = this.currentPosition;
                    } else {
                        this.currentHand = "R";
                        this.rightPosition = this.currentPosition;
                    }
                }
            }
        }


        public String getCurrentHand() {
            return currentHand;
        }

        public int getMoveCount(int start, int end) {
            int swap = 0;
            if (start > end) {
                swap = start;
                start = end;
                end = swap;
            }

            // 세로 이동은 3씩 건너뜀
            int shiftCnt = ((end-1) / 3) - ((start-1) / 3);
//            int shiftCnt = (int) (Math.ceil(Double.valueOf(end) / 3) - Math.ceil(Double.valueOf(start) / 3));

            int movedPosition = start + (3 * (int) shiftCnt);

            return Math.abs(end - movedPosition) + shiftCnt;
        }
    }

    public static void main(String[] args) {

        Quest08 quest08 = new Quest08();
        int[] numbers1 = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String mainHand1 = "right";
        // expected : "LRLLLRLLRRL"
        System.out.println(">>>> expected : LRLLLRLLRRL >> result : " + quest08.solution(numbers1, mainHand1) );

        int[] numbers2 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String startHand2 = "left";
        // expected : "LRLLRRLLLRR"
        System.out.println(">>>> expected : LRLLRRLLLRR >> result : " + quest08.solution(numbers2, startHand2) );

        int[] numbers3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String startHand3 = "right";
        // expected : "LLRLLRLLRL"
        System.out.println(">>>> expected : LLRLLRLLRL >> result : " + quest08.solution(numbers3, startHand3) );

    }


}

