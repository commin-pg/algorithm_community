import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class commin_05 {
    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값
     * 
     * 
     * 
     * > 변수
     * N 시간, 1000000 >= N
     * 
     * 
     * - 예제 데이터에 대해 이해
     * 
     * 파악하기
     * N 시간동안 야근을 하고나서 남은 일의 양을 모두 제곱해서 더했을때 최소값을 구하면됨.
     * 근데 제한사항을 보니까 이중 포문 쓰면 안됨
     * 
     * 남은시간만큼 가장 큰 일(work가 가장 큰 값)을 기준으로 줄임
     * 
     * 
     * > 단서
     * 
     */
    public commin_05() {
        int works[] = { 4, 3, 3 };
        int n = 4;
        solution(n, works);

    }

    class WorkQ implements Comparable<WorkQ> {
        public int work;

        public WorkQ(int work) {
            this.work = work;
        }

        public int minus() {
            return --this.work;
        }

        public int pow() {
            return this.work * this.work;
        }

        @Override
        public int compareTo(WorkQ o) {
            return o.work - this.work;
        }
    }

    public long solution(int n, int[] works) {
        long answer = 0;
        // List<WorkQ> workList = new ArrayList<>();
        PriorityQueue<Integer> workList = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer w : works) {
            workList.add(w);
        }

        while (n > 0) {

            // Collections.sort(workList);

            int q = workList.poll();
            if (q == 0)
                break;
            workList.add(q - 1);
            n--;
        }

        for (Integer q : workList) {
            answer += q * q;
        }

        // System.err.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        new commin_05();
    }
}
