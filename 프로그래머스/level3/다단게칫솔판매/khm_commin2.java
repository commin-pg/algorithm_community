import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class khm_commin2 {
    /*
     * 읽기
     * - 시간, 메모리 제한
     * - 문제 전체를 꼼꼼히
     * 이해하기
     * - 제공되는 정보(변수) 정리
     * 
     * > 변수
     * 각 판매원의 이름을 담은 배열 enroll
     * 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열 referral
     * 판매량 집계 데이터의 판매원 이름을 나열한 배열 seller
     * 판매량 집계 데이터의 판매 수량을 나열한 배열 amount
     * 
     * 각 판매원이 득한 이익금을 나열한 배열을 return!!
     * 
     * 제한조건
     * enroll의 길이는 1 이상 10,000 이하 ===> 여기에는 민호의 이름은 없다.
     * referral의 길이는 enroll의 길이와 같습니다
     * ==> referral 내에서 i 번째에 있는 이름은 배열 enroll 내에서 i 번째에 있는 판매원을 조직에 참여시킨 사람의 이름
     * referral[1] => 영희
     * enroll[1] => 철수
     * ===> 영희가 철수를 조직에 참여시켰다.
     * 어느 누구의 추천도 없이 조직에 참여한 사람에 대해서는 referral 배열 내에 추천인의 이름이 기입되지 않고 “-“ 가 기입
     * ==> referral[2] => -
     * ==> enroll[2] => 마리
     * ===> 마리는 누구의 추천도 받지않고 조직에 참여했다!
     * 
     * enroll 에 등장하는 이름은 조직에 참여한 순서에 따릅니다
     * 즉, 어느 판매원의 이름이 enroll 의 i 번째에 등장한다면,
     * 이 판매원을 조직에 참여시킨 사람의 이름,
     * 즉 referral 의 i 번째 원소는 이미 배열 enroll 의 j 번째 (j < i) 에 등장했음이 보장됩니다.
     * ===> 나중에 들어온 놈이 추천인이 될수는 없다는 조건.
     * 
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
     * 모든 판매원은 판매에 의하여 발생하는 이익에서 10%를 계산하여 자신을 조짓ㄱ에 참여시킨 추천인에게
     * 배분! 나머지는 자기꺼!
     * 
     * 또, 자기가 추천한 판매원의 이익의 10%까지 자신의 이익이 됨!
     * 
     * 10% 계산할때, 원단위에서 절사. ==> 소수점 첫째자리에서 버림
     * 계산한 금액이 1원 미만. 이익 * 0.1 < 1 ===> 모두 자신이 가짐.
     * 칫솔 판매에서 이익은 개당 100원!
     * 
     * 이익금도 추천인한테 배분해야함!
     * 
     */

    khm_commin2() {
        String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
        String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
        String[] seller = { "young", "john", "tod", "emily", "mary" };
        int[] amount = { 12, 4, 2, 5, 10 };
        System.out.println(solution(enroll, referral, seller, amount));
    }

    class Person {
        String referralName;
        String ownName;
        int wallet;

        public Person(String ownName, String referralName) {
            this.referralName = referralName;
            this.ownName = ownName;
        }

        public void earn(int profit) {
            this.wallet += profit;
        }
    }

    class Seller {
        String sellerName;
        int amount;

        public Seller(String sellerName, int amount) {
            this.sellerName = sellerName;
            this.amount = amount;
        }
    }

    public Person findPerson(String name) {
        if (personList.containsKey(name))
            return personList.get(name);
        else
            return null;
    }

    public void calculate(int profit, Person Own) {

        if (Own == null) {
            return;
        }

        Person referralPerson = null;
        if (!Own.referralName.equals("-")) {
            referralPerson = findPerson(Own.referralName);
        }

        if (profit < 1) {
            Own.earn(profit);
            return;
        } else {
            Double remainProfit = profit * 0.1;
            int remainProfitInt = remainProfit.intValue();
            int myProfit = profit - remainProfitInt;
            Own.earn(myProfit);
            calculate(remainProfitInt, referralPerson);
        }
    }

    private Map<String, Person> personList;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        personList = new HashMap<>(enroll.length);
        List<Person> resultPersonList = new ArrayList<>(enroll.length);
        for (int i = 0; i < enroll.length; i++) {
            Person person = new Person(enroll[i], referral[i]);
            personList.put(enroll[i], person);
            resultPersonList.add(person);
        }
        for (int i = 0; i < seller.length; i++) {
            Person own = findPerson(seller[i]);
            int profit = amount[i] * 100;
            calculate(profit, own);
        }

        return resultPersonList.stream().map(m -> m.wallet).mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        new khm_commin2();
    }
}
