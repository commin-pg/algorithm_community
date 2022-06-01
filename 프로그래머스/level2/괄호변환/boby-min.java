
import java.util.HashMap;
import java.util.Map;

public class Solution {


	/**
	 * https://programmers.co.kr/learn/courses/30/lessons/60058
	 * 
	 * 예시에서 드는 방식 그대로 풀이 진행함.
	 * 채점에서 실패하는 문제 발생하여 통과하지 못함.
	 * */
	public enum BALANCE_KEY {
		U, V
	}
	
	public final String BRACKET = "()";
	public final String BRACKET_LEFT = "(";
	public final String BRACKET_RIGHT = ")";
	
	/**
	 * https://programmers.co.kr/learn/courses/30/lessons/60058
	 * 
	 * 올바른 괄호문자열 만드는 방식을 벤치마킹함.
	 * */
	public String solution(String p) {
        String answer = "";
        
        // 1. 입력이 빈문자열이면 반환
        if(p == null || p.length() == 0) return p;
        
        // 2. 문자열 w를 u / v 로 분리
        // u는 더이상 분리될 수 없는 '균형잡힌괄호문자열'이어야 함.
        Map<BALANCE_KEY, String> balanceMap = new HashMap<BALANCE_KEY, String>();
        
        balanceMap.put(BALANCE_KEY.V, p);
        
        while(true) {
        	
        	balanceMap = balanceStr(balanceMap.get(BALANCE_KEY.V));
        	
        	// 3. 올바른 문자열이면 그대로 반환, 아니면 완전한 문자열로 만드는 함수 호출
        	if(!checkPerfect(balanceMap.get(BALANCE_KEY.U))) {
        		// 완전한 문자열 만드는 함수 호출
        		makePerfect(balanceMap);
        	}
        	
        	answer += balanceMap.get(BALANCE_KEY.U);
        	
        	if(answer.length() == p.length()) break;
        }
        
        return answer;
    }
	 
	/**
	 * 문자열 u v 로 분리
	 * */
	private Map<BALANCE_KEY, String> balanceStr(String w){
		
		Map<BALANCE_KEY, String> returnMap = new HashMap<BALANCE_KEY, String>();
		
		String u = "";
		String v = "";
		int end = 0;
		int check = 0;
		
		for(String arg : w.split("")) {
			
			end++;
			u += arg;
			
			switch(arg) {
			case "(":
				check++;
				break;
			case ")":
				check--;
				break;
			default:
				break;
			}
			
			if(check == 0) break;
		}
		
		v = w.substring(end);
		
		returnMap.put(BALANCE_KEY.U, u);
		returnMap.put(BALANCE_KEY.V, v);
		
		return returnMap;
	}
	
	/**
	 * 올바른 괄호 문자열 체크
	 * */
	private boolean checkPerfect(String w) {
		
		int wLength = w.length() / 2;
		
		for(int i = 0; i < wLength; i++) {
			w = w.replace(BRACKET, "");
		}
		
		return w.length() == 0 ? true : false;
	}
	
	/**
	 * 올바른 괄호 문자열 만들기
	 * */
	private void makePerfect(Map<BALANCE_KEY, String> balanceMap) {
		
		String balanceStr = "";
		
		// 역순으로 변경하기
		for(String arg : balanceMap.get(BALANCE_KEY.U).split("")) {
			if(BRACKET_LEFT.equals(arg)) {
				balanceStr += BRACKET_RIGHT;
			}else if(BRACKET_RIGHT.equals(arg)) {
				balanceStr += BRACKET_LEFT;
			}else {
				break;
			}
		}
		
		balanceMap.put(BALANCE_KEY.U, balanceStr);
	}
}
