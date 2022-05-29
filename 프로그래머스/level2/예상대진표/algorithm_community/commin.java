package algorithm_community;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class commin {

//	제한사항
//	N : 21 이상 220 이하인 자연수 (2의 지수 승으로 주어지므로 부전승은 발생하지 않습니다.)
//	A, B : N 이하인 자연수 (단, A ≠ B 입니다.)
	
	@Test
	public void test1() {
		assertEquals(3, solution(2, 4, 7));
	}
	
	public int solution(int n, int a, int b)
    {
        int round = 0;
        /*
         * (1 2) (3 4) ... (N-1 N)
         * (1 2) .... (N/2-1 N/2)
         * */
        while(a!=b) {
        	a = a%2==0? a/2 : a/2+1;
        	b = b%2==0? b/2 : b/2+1;
        	round++;
        }
        
        
        
        return round;
    }
}
