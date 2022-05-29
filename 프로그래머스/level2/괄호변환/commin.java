import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class commin {
//	
//	매개변수 설명
//	p는 '(' 와 ')' 로만 이루어진 문자열이며 길이는 2 이상 1,000 이하인 짝수입니다.
//	문자열 p를 이루는 '(' 와 ')' 의 개수는 항상 같습니다.
//	만약 p가 이미 "올바른 괄호 문자열"이라면 그대로 return 하면 됩니다.

	@Test
	public void test1() {
		assertEquals(solution(""), "");
	}

	@Test
	public void test2() {
		assertEquals("(()())()", solution("(()())()"));
	}

	@Test
	public void test3() {
		assertEquals("()", solution(")("));
	}

	@Test
	public void test4() {
		assertEquals("()(())()", solution("()))((()"));
	}

	public String solution(String p) {
		String answer="";
		try {
			answer = dfs(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	public String dfs(String str) throws Exception {
//      1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
		if (str.equals(""))
			return "";
		if (check(str))
			return str;
//      2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
		String u = createU(str);
		String v = str.substring(u.length(), str.length());

		if (check(u)) {
//          3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
//          3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
			return u + dfs(v);
		} else {
//          4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
//          4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
//          4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
//          4-3. ')'를 다시 붙입니다. 
//          4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
//          4-5. 생성된 문자열을 반환합니다.
			return "(" + dfs(v) + ")" + reverseU(u.substring(1, u.length() - 1));
		}
	}

	public String reverseU(String s) {
		StringBuilder sb = new StringBuilder();
		for (Character c : s.toCharArray()) {
			switch (c) {
			case ')':
				sb.append('(');
				break;
			case '(':
				sb.append(')');
				break;
			}
		}
		return sb.toString();
	}

	public String createU(String s) throws Exception{
		
		int start = 0;
		int end = 2;
		int limit = s.length();
		do {
			if(end > limit) throw new Exception();
			if(isBalance(s.substring(start,end))) break;
			else end=end+2;
		}while(true);
		
		
		return s.substring(start,end);
	}
	
	public boolean isBalance(String s) {
		int left = 0;
		int right = 0;
		for(Character c : s.toCharArray()) {
			if(c==')') right++;
			if(c=='(') left++;
		}
		return left == right;
	}

	public boolean check(String s) {
		while (true) {
			if (s.contains("()")) {
				s = s.replace("()", "");
			} else {
				break;
			}
		}
		return s.isEmpty();
	}

}
