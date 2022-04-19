package min.dev.edu.algorithm;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class KakaoNumberString {

	@Test
	public void call() {

		System.out.println(solution("one4seveneight"));
		
	}

	public int solution(String s) {
		
		Map<String, String> number = new HashMap<String, String>();
		
		number.put("zero", "0");
		number.put("one", "1");
		number.put("two", "2");
		number.put("three", "3");
		number.put("four", "4");
		number.put("five", "5");
		number.put("six", "6");
		number.put("seven", "7");
		number.put("eight", "8");
		number.put("nine", "9");
		
		for(String key : number.keySet()) {
			s = s.replaceAll(key, number.get(key));
		}
		
		
		return Integer.parseInt(s);
	}

}
