import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class khm_commin {

    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 우선순위 큐가 할 연산 operations
     * 
     * 제한사항
     * operations는 길이가 1 이상 1,000,000 이하인 문자열 배열 ===> 배열의 길이가 백만.
     * 원소는 “명령어 데이터” 형식
     * 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제
     * 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시
     * 
     * - 예제 데이터에 대해 이해
     * 
     * 
     * 파악하기
     * - 가능한 최대, 최소 정답에 맞는 데이터를 직접 생성
     * 
     * - 키워드가 되는 단어들을 체크
     * > 단서
     * I 숫자 큐에 주어진 숫자를 삽입합니다
     * D 1 큐에서 최댓값을 삭제합니다.
     * D -1 큐에서 최솟값을 삭제합니다.
     * 
     * Priority Queue 를 써야하나 싶었는데.. 문제를 보니 뭔가 굳이의 느낌.. 추가/삭제의 순서가 중요하지않음..
     * 
     */

    khm_commin() {
        String[] operations = { "I 16", "I 16", "D 1" };
        // String[] operations = {"I -16","I -16","I 16","D 1"};
        solution(operations);
    }

    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        List<Integer> arr = new ArrayList<>();
        for (String operation : operations) {
            String[] ops = operation.split(" ");
            String operand = ops[0];
            int number = Integer.parseInt(ops[1]);
            switch (operand) {
                case "I":
                    arr.add(number);
                    break;
                case "D":
                    if (arr.size() > 0) {
                        if (number > 0) {
                            // 최댓값 삭제
                            Integer max = Collections.max(arr);
                            arr.remove(arr.indexOf(max));
                        } else {
                            // 최솟값 삭제
                            Integer min = Collections.min(arr);
                            arr.remove(arr.indexOf(min));
                        }
                    }

                    break;
            }
        }
        int max = 0;
        int min = 0;
        if(arr.size()>0){
            max = Collections.max(arr);
            min = Collections.min(arr);
        }
        answer[0] = max;
        answer[1] = min;
        return answer;
    }

    public static void main(String[] args) {
        new khm_commin();
    }
}
