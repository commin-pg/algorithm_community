import java.util.Arrays;

public class khm_commin {

    // return answer
    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 입국심사를 기다리는 사람 수 n
     * 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times
     * 
     * 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return
     * 
     * 제한조건
     * 입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 ===>
     * 각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하
     * 심사관은 1명 이상 100,000명 이하 ==> times의 길이
     * 
     * ===> 이중 for문 쓰면 안됨.
     * ==> n이 100만, 걸리는 시간 100만 100만 * 100만.. 헙
     * 
     * - 예제 데이터에 대해 이해
     * 
     * 
     * 파악하기
     * - 가능한 최대, 최소 정답에 맞는 데이터를 직접 생성
     * 
     * - 키워드가 되는 단어들을 체크
     * > 단서
     * 모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.
     * 
     */

    khm_commin() {
        int[] times = { 7, 10 };
        solution(6, times);
    }

    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = (long) n * times[times.length - 1]; // 최악 시간
        while (left <= right) {
            long mid = (left + right) / 2;
            long person = 0;// 심사원한테 심사를 받은 사람 수
            for (int time : times) {
                person = person + (mid / time);
                // if (person >= n)
                //     break;
            }
            if (person >= n) { // 심사를 다 받음
                answer = mid;
                right = mid - 1; // 현재 답인 시간보다 작은 범위에서 다시 시작, 다시 이곳으로 들어온다면 무조건 작은 시간.
            } else { // 심사를 다 받지 않음.
                left = mid + 1;
            }
        }
        return answer;
    }
    }

    public static void main(String[] args) {
        new khm_commin();
    }
}
