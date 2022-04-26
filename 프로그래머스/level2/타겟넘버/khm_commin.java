
class khm_commin {
    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 사용할 수 있는 숫자가 담긴 배열 numbers
     * 타겟 넘버 target
     * 타겟 넘버를 만드는 방법의 수를 return
     * 
     * 
     * - 예제 데이터에 대해 이해
     * numbers = 4, 1, 2, 1
     * target = 4
     * 
     * 파악하기
     * - 가능한 최대, 최소 정답에 맞는 데이터를 직접 생성
     * 
     * - 키워드가 되는 단어들을 체크
     * > 단서
     * n개의 음이 아닌 정수들 > 음수는 안들어옴.
     * 정수들을 순서를 바꾸지 않고 > 순서를 바꾸지 않음
     * 더하거나 빼서 > 더하기와 뺄셈만 이용
     * 더하기 빼기를 담는 배열의 크기는 numbers 와 같음
     * 모든 경우의 수를 구하고, 답이 target일때의 카운트만 하면 됨.
     * 깊이 우선 탐색으로 찾으면 될 듯.
     * 
     */

    public khm_commin() {
        // int[] numbers = { 1, 1, 1, 1, 1 };
        // int target = 3;
        int[] numbers = { 4, 1, 2, 1 };
        int target = 4;

        solution(numbers, target);
    }

    int answer = 0;

    public void dfs(int dgree, int sum, int[] numbers, int target) {
        if (dgree == numbers.length) {
            if (target == sum)
                answer++;
        } else {
            dfs(dgree + 1, sum + numbers[dgree], numbers, target);
            dfs(dgree + 1, sum - numbers[dgree], numbers, target);
        }
    }

    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        new khm_commin();
    }
}