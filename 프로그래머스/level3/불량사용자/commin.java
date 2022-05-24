import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class commin {
    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 이벤트 응모자 아이디 목록이 담긴 배열 user_id
     * 불량 사용자 아이디 목록이 담긴 배열 banned_id
     * 
     * 
     * 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한 지 return
     * 
     * user_id 배열의 크기는 1 이상 8 이하
     * user_id 배열 각 원소들의 값은 길이가 1 이상 8 이하인 문자열
     * > 응모한 사용자 아이디들은 서로 중복되지 않습니다.
     * 
     * banned_id 배열의 크기는 1 이상 user_id 배열의 크기 이하 ====> user_id length =5 ? 1 <=
     * banner_id length <= 5
     * 
     * 동일하다면 같은 것으로 처리하여 하나로 ===> Set에 넣으면 됨..?
     * 
     * - 예제 데이터에 대해 이해
     * 
     * 파악하기
     * 불량 사용자 당첨 처리 시 제외
     * 불량 사용자 목록에 매핑된 응모자 아이디를 제재 아이디 라고 부르기
     * 이 때 개인정보 보호을 위해 사용자 아이디 중 일부 문자를 '' 문자로 가려서 전달
     * 아이디 당 최소 하나 이상의 '*' 문자를 사용
     * 
     * > 단서
     * 
     * 별표에는 어떤 알파벳이든 다들어가면되니까 정규표현식으로..!
     * 
     * 전수 검사 해야 하니까 dfs..
     */
    commin() {
        // String [] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        // String [] banned_id = {"fr*d*", "abc1**"};

        String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
        String[] banned_id = { "fr*d*", "*rodo", "******", "******" };

        int i = solution(user_id, banned_id);
        System.out.println(i);
    }

    private Set<String> resultSet = new HashSet<>();
    int[] visit = null;

    public int solution(String[] user_id, String[] banned_id) {
        // 1. 변수 선언
        visit = new int[user_id.length];

        // 깊이 우선 탐색
        dfs(0, "", user_id, banned_id);

        return resultSet.size();
    }

    public boolean isMatchedId(String userId, String bannedId) {
        String bannedPattern = bannedId.replace("*", ".");
        return Pattern.matches(bannedPattern, userId);
    }

    public void dfs(int banIdx, String resultStr, String[] user_id, String[] banned_id) {
        if (banIdx == banned_id.length) { // banned_id lenth 만큼 탐색 했으면, Set 에다가 Sort해서 넣으면 자동으로 중복제거
            String[] resultArr = resultStr.split(",");
            Arrays.sort(resultArr);
            StringBuilder sb = new StringBuilder();
            for (String s : resultArr) {
                sb.append(s);
            }
            resultSet.add(sb.toString());
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            // 종료 조건
            if (visit[i] == 1)
                continue;
            if (!isMatchedId(user_id[i], banned_id[banIdx]))
                continue;

            // 조건 만족 > 깊이우선탐색 계속
            visit[i] = 1; // 탐색 종료

            dfs(banIdx + 1, resultStr + "," + user_id[i], user_id, banned_id);
            visit[i] = 0; // 다시 탐색
        }

    }

    public static void main(String[] args) {
        new commin3();
    }
}
