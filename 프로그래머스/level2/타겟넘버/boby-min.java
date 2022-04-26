
package min.dev.edu.algorithm;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class TargetNumber {

	@Test
	public void call() {

		
		int[] numbers = {4, 1, 2, 1};
		
		System.out.println(solution(numbers, 4));
		
		int[] numbers2 = {1, 1, 1, 1, 1};
		
		System.out.println(solution(numbers2, 3));
		
	}
	
	
	private int solution(int[] numbers, int target) {
        int answer = 0;
        
        // 순열을 구하여 전체합에서 순열의 합을 뺌
        // 연산 후 값이 target 과 동일하면 답을 구할 수 있는 방법임.
        
        TargetCombi comb = null;
        ArrayList<Integer> result = null;
        int numbersAllSum = 0;
        
        // numbes의 길이만큼 loop 처리를 해서 r개의 조합을 구함.
        for(int i = 0; i < numbers.length; i++) {
        	// numbers.length - 1 처리를 하는 이유는 전체 합을 먼저 구해놓기 위해서임.
        	// 수열이 가진 전체 개수만큼의 순열의 합을 우선 구할 수 있음.
        	comb = new TargetCombi(numbers.length, numbers.length - i);
        	comb.combination(numbers, 0, 0, 0);
        	result = comb.getResult();
        	
        	if(i == 0) {
        		numbersAllSum = result.get(0);
        	}
        	
        	for(Integer arg : result) {
        		if((numbersAllSum - arg * 2) == target) answer++;
        		
        	}
        }
        
        
        return answer;
    }
	
}

class TargetCombi {

	private int n;
	private int r;
	private int[] now;
	private ArrayList<Integer> result;

	public ArrayList<Integer> getResult() {
		return result;
	}

	public TargetCombi(int n, int r) {
		this.n = n;
		this.r = r;
		this.now = new int[r];
		this.result = new ArrayList<Integer>();
	}

	public void combination(int[] arr, int depth, int index, int target) {
		
		if (depth == r) {
			Integer temp = 0;

			for (int i = 0; i < now.length; i++) {
				temp += arr[now[i]];
			}

			result.add(temp);
			return;
		}

		if (target == n)
			return;

		now[index] = target;
		combination(arr, depth + 1, index + 1, target + 1);
		combination(arr, depth, index, target + 1);
	}

}
