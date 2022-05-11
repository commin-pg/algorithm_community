import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class commin2 {
    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 
     * 차량의 대수는 1대 이상 10,000대 이하 == > 1 <= i <= 10000
     * routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점
     * routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점
     * ==> 최대 10000대의 차량이 진입하고 나간지점은 최대 20000번
     * 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다. ===> -30,000 <= 진입지점, 진출지점 <=30,000 ===> Integer 가능
     * 
     * 
     * 최소 몇 대의 카메라를 설치해야 하는지를 return
     * 
     * - 예제 데이터에 대해 이해
     * 
     * 파악하기
     * 모든 지점에 카메라가 있는 경우가 최악. 
     * 욕심쟁이 
     * 카메라가 설치될수 있는 최저값은 -30000
     * 
     * 
     * > 단서
     * 
     */

    commin2() {
        int routes [][] = {{-15,5}, {4,10}, {-20,-15}, {-25,-10}};// ==> 총 4대의 차량이 진입/진출 함
        // int routes [][] = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};// ==> 총 4대의 차량이 진입/진출 함
        int result = solution(routes);
        System.out.println(result);
    }

    class Car implements Comparable<Car>{
        int in;
        int out;
        Car(int in , int out){
            this.in = in;
            this.out = out;
        }
        @Override
        public int compareTo(commin2.Car o) {
            return out - o.out;
        }
    }

    public int solution(int[][] routes) {
        int answer = 0;
        List<Car> cars = new ArrayList<>();
        int cameraPosition = -30000;
        for(int i=0;i<routes.length;i++){
            cars.add(new Car(routes[i][0],routes[i][1]));
        }

        Collections.sort(cars); // out을 기준으로 오름차순!

        // Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1])); 요런 좋은게 있넹

        for(Car car : cars){
            if(cameraPosition < car.in){
                cameraPosition = car.out;
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        new commin2();
    }
}
