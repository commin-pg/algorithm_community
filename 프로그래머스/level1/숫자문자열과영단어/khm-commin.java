public class Solution {
    // 읽기 [o]
    // 이해 [o]
    // 파악 []
    // result:20억
    static String en_nums[] = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    };

    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(en_nums[i], String.valueOf(i));
            // System.out.println(s);
        }
        answer = Integer.parseInt(s);
        return answer;
    }
}
