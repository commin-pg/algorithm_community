import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class khm_commin2 {

    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 그림의 크기를 나타내는 m, n
     * m × n 크기의 2차원 배열 picture
     * 제한조건
     * 1 <= m, n <= 100 ===> 100 * 100 = 10000
     * 2^31 - 1 ===> Integer Max -1 ===> 원소에 Integer를 써도 됨.
     * picture의 원소 중 값이 0인 경우는 색칠하지 않는 영역 ====> 0은 셈을 하지 않는다는 조건!
     * ** 0은 셈을 하지 않지만 일단 Visit 처리만 하고, GroupCnt 를 늘리지 않으면 됨.
     * 
     * 
     * - 예제 데이터에 대해 이해
     * 
     * 
     * 파악하기
     * - 가능한 최대, 최소 정답에 맞는 데이터를 직접 생성
     * 
     * - 키워드가 되는 단어들을 체크
     * > 단서
     * 그림의 난이도를 영역의 수로 정의 (영역이란 상하좌우로 연결된 같은 색상의 공간을 의미한다.)
     * 아파트단지 찾는거랑 똑같음 dfs 로 풀자.
     */
    public khm_commin2() {
        System.out.println("Hello!");
        int m = 6;
        int n = 4;
        int[][] picture = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
                { 0, 0, 0, 3 } };
        solution(m, n, picture);

    }

    // int groupCnt = 0;
    // int max = Integer.MIN_VALUE;

    int dx[] = { 1, 0, -1, 0 }; // 우-상-좌-하 탐색
    int dy[] = { 0, 1, 0, -1 }; // 우-상-좌-하 탐색
    int visit[][] = null;
    int groupCnt = 0;
    boolean zero =false;
    int [][] picture =null;


    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        this.picture = picture;

        visit = new int[m][n]; // 0 : 방문한적 없음, 1:방문한적 있음.

        List<Integer> group = new ArrayList<>();

        for (int x = 0; x < picture.length; x++) {
            for (int y = 0; y < picture[x].length; y++) {
                if (visit[x][y] == 1) // 방문한곳이라면 건너띄기
                    continue;
                groupCnt = 0;
                zero =false;
                dfs(x, y, m, n);
                if(!zero)
                group.add(groupCnt);
            }
        }

        numberOfArea = group.size();
        maxSizeOfOneArea = Collections.max(group);

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public void dfs(int x, int y, int maxX, int maxY) {
        groupCnt++; // 처음 방문한거니까..
        visit[x][y] = 1; // 방문처리
        if(picture[x][y] == 0 && !zero){
            zero=true;
        }

        for (int k = 0; k < 4; k++) { // 4방향 탐색
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= maxX || ny >= maxY)// 탐색불가지역
                continue;
            if(visit[nx][ny] == 1)
                continue;
            if(picture[x][y] != picture[nx][ny])
                continue;
        
            dfs(nx, ny, maxX, maxY);
        }
    }

    public static void main(String[] args) {
        new khm_commin2();
    }
}
