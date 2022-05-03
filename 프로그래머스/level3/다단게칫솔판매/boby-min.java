

import java.util.HashMap;
import java.util.Map;

public class ToothbrushSales {
	
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, Member> memberIncomeMap = new HashMap<String, Member>();
        Member memberBucket = null;
        int loopCnt = enroll.length;
        int calcIncome = 0;
        
        // 멤버 셋팅
        for(int i = 0; i < loopCnt; i++) {
        	
        	memberBucket = new Member(enroll[i], referral[i].equals("-") ? "" : referral[i], i);
        	
        	memberIncomeMap.put(enroll[i], memberBucket);
        }
        
        // 판매금액 정보 처리
        loopCnt = seller.length;
        
        for(int i = 0; i < loopCnt; i++) {
        	
        	// 판매자가 존재하면 처리
        	if(memberIncomeMap.containsKey(seller[i])) {
        		
        		memberBucket = memberIncomeMap.get(seller[i]);
        		calcIncome = memberBucket.setIncome(amount[i] * 100);
        		
        		while(true) {
        			if(!"".equals(memberBucket.getBoss()) && calcIncome != 0) {
            			memberBucket = memberIncomeMap.get(memberBucket.getBoss());
            			calcIncome = memberBucket.setIncome(calcIncome);
            		} else {
            			break;
            		}
        		}
        	}
        }
        
        int[] answer = new int[enroll.length];
        
        for(String key : memberIncomeMap.keySet()) {
        	answer[memberIncomeMap.get(key).getOrder()] = memberIncomeMap.get(key).getAccumulateIncome();
        }
        
        return answer;
    }
}

class Member {
	
	private String me;
	private String boss;
	private int income;
	private int order;
	
	public Member(String me, String boss, int order) {
		this.me = me;
		this.boss = boss;
		this.order = order;
	}
	
	public int setIncome(int income) {
		
		this.income += income - (income / 10);
		
		return income / 10;
	}
	
	public String getBoss() {
		
		return this.boss;
	}
	
	public int getOrder() {
		
		return this.order;
	}
	
	public int getAccumulateIncome() {
		
		return this.income;
	}
}
