package codecata9;

import java.util.*;

public class Quest96 {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT",
                "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT",
                "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(new Solution96().solution(fees, records)));
    }
}

class Solution96 {
    public int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        final int END_OF_DAY = timeToMinutes("23:59");

        //현재 주차 중인 차량의 입차 시각 저장
        Map<String, Integer> parkingLot = new HashMap<>();
        //차량별 총 누적 주차 시간 저장
        Map<String, Integer> totalParkingTime = new TreeMap<>();

        // records를 순회하며 주차 시간 계산
        for (String record: records) {
            String[] parts = record.split(" ");
            int time = timeToMinutes(parts[0]);
            String carNum = parts[1];
            String type = parts[2];

            //모든 차량번호를 totalParkingTime에 미리 등록
            totalParkingTime.putIfAbsent(carNum, 0);

            if(type.equals("IN")) {
                parkingLot.put(carNum, time);
            }else {
                int inTime = parkingLot.remove(carNum);
                int parkingTime = time - inTime;

                totalParkingTime.put(carNum, totalParkingTime.get(carNum) + parkingTime);
            }
        }

        // 출차 기록이 없는 차량 처리
        for(Map.Entry<String, Integer> entry : parkingLot.entrySet()) {
            String carNum = entry.getKey();
            int inTime = entry.getValue();

            int parkingTime = END_OF_DAY - inTime;

            totalParkingTime.put(carNum, totalParkingTime.get(carNum) + parkingTime);
        }

        List<Integer> finalFees = new ArrayList<>();

        for(int totalTime : totalParkingTime.values()) {
            int fee;

            if(totalTime <= defaultTime){
                fee = defaultFee;
            }else {
                int overTime = totalTime - defaultTime;

                int extraFee = (int) Math.ceil((double) overTime/ unitTime) * unitFee;

                fee = defaultFee + extraFee;
            }
            finalFees.add(fee);
        }

        return finalFees.stream().mapToInt(i -> i).toArray();
    }

    // 시각 문자열을 총 분으로 변환하는 헬퍼 메서드
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

}