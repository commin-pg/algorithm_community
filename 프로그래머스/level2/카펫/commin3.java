
// import org.junit.jupiter.api.Test;

class commin3 {
    /*
     * 가로 : 노가 + 2
     * 세로 : 노세 + 2
     * 카펫의 가로 길이는 세로 길이와 같거나 ===> 가로 == 세로
     * 또는
     * 카펫의 가로 길이는 세로길이보다 김 ===> 가로 > 세로
     * 
     * ===> 가로 >= 세로
     */
    // @Test
    // public void test() {
    // int brown = 10;
    // int yellow = 2;
    // int result[] = solution(brown, yellow);
    // for (int i : result) {
    // System.out.println(i);
    // }
    // }

    commin3() {
        int brown = 5000;
        int yellow = 2000000;
        int result[] = solution(brown, yellow);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        // 정사각형 블록 N 개로 만들수있는 사각형의 x, y 하나만 구하면 답을 구할 수 있음
        // Brown의 개수도 정해졌으니까, 가로 X 세로 - yellow 했을때 brown이 나오는게 답.

        int row = 1; // 가로가 한줄일때
        boolean loop = true;
        while (loop) {
            row++;
            if (yellow % row != 0 || yellow < row) {
                continue;
            }
            int[] candis = { yellow / row + 2, row + 2 };
            if (brown == candis[0] * candis[1] - yellow) {
                answer = candis;
                loop = false;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        new commin3();
    }

}