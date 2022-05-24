import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class commin_w6 {

    commin_w6() {
        solution("[](){}");
        solution("}]()[{");
        solution("[)(]");
        solution("}}}");
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<String> stack = new Stack<String>();
        for (String c : s.split("")) {
            if (stack.empty() && (c.charAt(0) == '}' || c.charAt(0) == ']' || c.charAt(0) == ')'))
                return false;

            switch (c) {
                case "}":
                    if (stack.peek().equals("{"))
                        stack.pop();
                    break;
                case "]":
                    if (stack.peek().equals("["))
                        stack.pop();
                    break;
                case ")":
                    if (stack.peek().equals("("))
                        stack.pop();
                    break;
                default:
                    stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public String rotation(String s) {
        String result = s.substring(1, s.length()) + s.charAt(0);
        return result;
    }

    public int solution(String s) {
        int answer = 0;
        String rot = s;

        while (true) {
            String tmp = rot;
            rot = rotation(tmp);
            if (isValid(rot)) {
                answer++;
            }
            if (s.equalsIgnoreCase(rot)) {
                break;
            }
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        new commin_w6();
    }
}
