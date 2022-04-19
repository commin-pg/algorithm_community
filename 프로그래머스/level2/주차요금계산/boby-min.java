package min.dev.edu.algorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class KakaoParkingBill {

	@Test
	public void call() {

		int[] fees = { 180, 5000, 10, 600 };
		String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
				"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };

		for(int test : parkingBillSolution(fees, records)) {
			System.out.println(test);
		}
		
		int[] fees2 = { 1, 461, 1, 10 };
		String[] records2 = { "00:00 1234 IN" };

		for(int test : parkingBillSolution(fees2, records2)) {
			System.out.println(test);
		}
		
	}

	private int[] parkingBillSolution(int[] fees, String[] records) {

		int[] answer = {};
		
		String[] tempRecord;
		Integer tempCarNumber;
		List<Integer> tempTime = null;
		Map<Integer, List<Integer>> calcRecord = new HashMap<Integer, List<Integer>>();

		// 자동차 번호별 그룹핑
		for(String record : records) {
			tempRecord = record.split(" ");
			tempCarNumber = Integer.parseInt(tempRecord[1]);
			
			if(calcRecord.containsKey(tempCarNumber)){
				
				tempTime = calcRecord.get(tempCarNumber);
				
				
				tempTime.add(timeChange(tempRecord[0]));
			}else {
				
				tempTime = new ArrayList<Integer>();
				tempTime.add(timeChange(tempRecord[0]));
			}
			
			calcRecord.put(tempCarNumber, tempTime);
		}
		
		answer = new int[calcRecord.size()];
		int index = 0;
		

		// 키로 정렬
		List<Integer> keySet = new ArrayList<>(calcRecord.keySet());
		Collections.sort(keySet);
		
		for(Integer test : keySet) {
			
			int sumTime = 0;
			List<Integer> time = calcRecord.get(test);
			
			// 홀수면 출차기록 없으므로 1440분 출차기록 추가
			if(time.size() % 2 == 1) {
				time.add(1439);
			}
			
			for(int i = time.size() - 1; i > -1; i -= 2) {
				sumTime += time.get(i) - time.get(i - 1);
			}
			
			answer[index] = parkingCalc(fees, sumTime);
			index++;
			
		}
		
		return answer;
	}
	
	
	private int parkingCalc(int[] fees, int sumTime) {
		
		System.out.println("sumTime 확인 ===> " + sumTime);
		
		double calc = 0.0;
				
		if(sumTime < fees[0]) {
			return fees[1];
		} else {
			calc = fees[1] + Math.ceil(( sumTime + 0.0 - fees[0] ) / fees[2]) * fees[3];
		}
		
		return (int) calc;
	}
	
	private int timeChange(String timeStr) {
		String[] time = timeStr.split(":");
		
		return (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]) + 1;
	}

}
