package min.dev.edu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class KakaoMenuRenewal {

	@Test
	public void call() {

		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] course = { 2, 3, 4 };

		for (String arg : solution(orders, course)) {
			System.out.println(arg);
		}

	}

	private String[] solution(String[] orders, int[] course) {
		List<String> answer = new ArrayList<String>();

		Map<String, Integer> checkMenu = new HashMap<String, Integer>();
		List<String> tempList = null;
		int maxValue = 0;

		Combi comb = null;

		for (int courseCnt : course) {
			for (String order : orders) {

				tempList = Arrays.asList(order.split(""));
				Collections.sort(tempList);

				comb = new Combi(tempList.size(), courseCnt);
				comb.combination(tempList.toArray(new String[tempList.size()]), 0, 0, 0);
				ArrayList<String> result = comb.getResult();

				System.out.println(result.toString());

				for (int i = 0; i < result.size(); i++) {
					if (checkMenu.containsKey(result.get(i))) {
						checkMenu.put(result.get(i), checkMenu.get(result.get(i)) + 1);
						
						// 제일 많이 시킨 메뉴 확인용
						if(checkMenu.get(result.get(i)) > maxValue) {
							maxValue = checkMenu.get(result.get(i));
						}
						
					} else {
						checkMenu.put(result.get(i), 1);
					}
				}
			}
			
			for(String key : checkMenu.keySet()) {
				if(maxValue == checkMenu.get(key)) {
					answer.add(key);
				}
			}
			checkMenu.clear();
			maxValue = 0;
		}

		Collections.sort(answer);
		
		return answer.toArray(new String[answer.size()]);
	}

}

class Combi {

	private int n;
	private int r;
	private int[] now;
	private ArrayList<String> result;

	public ArrayList<String> getResult() {
		return result;
	}

	public Combi(int n, int r) {
		this.n = n;
		this.r = r;
		this.now = new int[r];
		this.result = new ArrayList<String>();
	}

	public void combination(String[] arr, int depth, int index, int target) {
		if (depth == r) {
			StringBuilder temp = new StringBuilder();

			for (int i = 0; i < now.length; i++) {
				temp.append(arr[now[i]]);
			}

			result.add(temp.toString());
			return;
		}

		if (target == n)
			return;

		now[index] = target;
		combination(arr, depth + 1, index + 1, target + 1);
		combination(arr, depth, index, target + 1);
	}

}
