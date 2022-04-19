import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Solution {

    /*
     * 문제 읽기 []
     * 
     * 문제 파악 []
     * 
     * 시간복잡도 []
     * 
     */

    class ParkingSystem {
        List<Parking> parkingList = null;

        ParkingSystem() {
            this.parkingList = new ArrayList<>();
        }

        public List<Parking> getParkingList() {
            return parkingList;
        }

        public void process(String[] issue) {
            try {
                Parking parking = this.parkingList.stream()
                        .filter(f -> f.getCarNumber().equalsIgnoreCase(issue[1])).findFirst().get();
                if (issue[2].equalsIgnoreCase("IN")) {
                    parking.setInTime(issue[0]);
                } else { // OUT
                    if (this.parkingList != null) {
                        parking.setOutTime(issue[0]);
                    }
                }

            } catch (Exception e) {
                // 출차기록이 없다면 IN 밖에 없다.
                this.parkingList.add(new Parking(issue[0], issue[1])); // 0 : time, 1 : carNumber
            }

        }

    }

    class Parking implements Comparable<Parking> {
        String carNumber;
        String inTime;
        String outTime;
        int totalTime;
        int fee;

        @Override
        public int compareTo(Parking parking) {
            int thisCarNumber = Integer.parseInt(carNumber);
            int targetCarNumber = Integer.parseInt(parking.getCarNumber());
            // if (this.carNumber.compareTo(parking.getCarNumber()) < 1) {
            // return 1; // x에 대해서는 오름차순
            // } else if (this.carNumber.compareTo(parking.getCarNumber()) == 0) {
            // return 0;
            // }
            // return -1;

            if (thisCarNumber > targetCarNumber) {
                return 1; // x에 대해서는 오름차순
            }
            return -1;
        }

        Parking(String inTime, String carNumber) {
            this.inTime = inTime;
            this.carNumber = carNumber;
        }

        public Integer sumTime() throws ParseException { // 주차 시간 계산
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date date1 = sdf.parse(getInTime());
            Date date2 = sdf.parse(getOutTime());
            cal1.setTime(date1);
            cal2.setTime(date2);
            long date1_l = date1.getTime();
            long date2_l = date2.getTime();
            date.setTime(date2_l - date1_l);
            Calendar cal3 = Calendar.getInstance();
            cal3.setTime(date);
            return (int) TimeUnit.MILLISECONDS.toMinutes(cal3.getTimeInMillis());
        }

        public void addTotalTime(int time) {
            this.totalTime += time;
            this.inTime = null;
            this.outTime = null;
        }

        public int getTotalTime() {
            return totalTime;
        }

        public void ended(Fee feeTable) {
            // 모든 process 가 끝나고 호출 하므로, InTime에 값이 있다면 출차된 기록이없는 놈임. 더해줘야함
            if (this.inTime != null) {
                try {
                    addTotalTime(sumTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // 정산 시작..
            // 5000 + ⌈(334 - 180) / 10⌉ x 600 = 14600
            if (feeTable.getBasicMin() < getTotalTime()) { // 누적시간이 초과하면..
                int totalFee = feeTable.getBasicFee();
                totalFee += Math.ceil((getTotalTime() - feeTable.getBasicMin()) / feeTable.getUnitMin())
                        * feeTable.getUnitFee();
                setFee(totalFee);
            } else { // 누적시간이 이하면..
                setFee(feeTable.getBasicFee());
            }

        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public Integer getFee() {
            return fee;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public String getInTime() {
            return inTime;
        }

        public String getOutTime() {
            if (this.outTime == null) {
                return "23:59";
            }
            return outTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
            try {
                addTotalTime(sumTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    class Fee {
        Integer basicMin; // 기본 시간(분)
        Integer basicFee; // 기본 요금(원)
        double unitMin; // 단위 시간(분)
        Integer unitFee; // 단위 요금(원)

        public Fee(int[] fees) {
            this.basicMin = fees[0];
            this.basicFee = fees[1];
            this.unitMin = fees[2];
            this.unitFee = fees[3];
        }

        public Integer getBasicFee() {
            return basicFee;
        }

        public Integer getBasicMin() {
            return basicMin;
        }

        public Integer getUnitFee() {
            return unitFee;
        }

        public double getUnitMin() {
            return unitMin;
        }

        public void setBasicFee(Integer basicFee) {
            this.basicFee = basicFee;
        }

        public void setBasicMin(Integer basicMin) {
            this.basicMin = basicMin;
        }

        public void setUnitFee(Integer unitFee) {
            this.unitFee = unitFee;
        }

        public void setUnitMin(Integer unitMin) {
            this.unitMin = (double) unitMin;
        }
    }

    Solution(int[] fees, String[] records) {
        Fee fee = new Fee(fees);
        ParkingSystem parkingSystem = new ParkingSystem();
        // 주차장 하루 프로세스
        for (String issues : records) {
            parkingSystem.process(issues.split(" "));
        }
        // 주차장 마감
        List<Parking> copyList = parkingSystem.getParkingList();
        Collections.sort(copyList);

        int[] answer = new int[copyList.size()];

        for (int i = 0; i < copyList.size(); i++) {
            Parking parking = copyList.get(i);
            parking.ended(fee);
            answer[i] = parking.getFee();
        }

        System.out.println();
    }

    // public static void main(String[] args) throws ParseException {
    // int[] fees = { 1, 461, 1, 10 };
    // String[] records = { "00:00 1234 IN" };

    // new Solution(fees, records);
    // // System.out.println(3 / 10.0);

    // // 5000 + ⌈(334 - 180) / 10⌉ x 600 = 14600

    // // System.out.println();
    // }
}
