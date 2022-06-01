
public class Solution {

	/**
	 *  2로 나눌 때 a 가 어디에 큰수에 위치하는지, 작은 위치하는지가 중요함.
	 *  
	 * 	if 1) 다른 구역에 있을 경우 2의 n승의 n + 1이 답. 현재 내 구역을 모두 이겨야만 만날 수 있음.
	 *  if 2) 같은 구역에 있을 경우 다른 구역이 될 때까지 2로 나눠감. 다른 구역에 속하는 순간 if 1)과 답이 동일해짐.
	 *  if 3) 처음부터 나란히 나타나는 경우가 발생할 수 있음. a가 짝수면 -1, 홀수면 +1로 b값이 나란히인지 체크 가능.
	 *  if 4) b의 대진순서가 a보다 빠를 수 있음. 몇 번째에 만나는지가 중요하므로 반대로 치환해서 계산함.
	 * */
	public int solution(int n, int a, int b)
    {
		if(a % 2 == 0 && b + 1 == a) return 1;
		if(a % 2 != 0 && b - 1 == a) return 1;
		if(a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		int first = 0;
		int end = n;
		int mid = (end + first) / 2;
        
        while(true) {
        	
        	if (a <= mid && b > mid) {
        		return getPow(end - first);
            } else if(mid <= a) {
            	// a가 더 큰 경우
            	first = mid;
            	end = n;
            	mid = (end + first) / 2;
            } else {
            	// a가 더 작은 경우
            	first = 0;
            	end = mid;
            	mid = (end + first) / 2;
            }
        }
    }
	
	private int getPow(int mid) {
		
		int answer = 0;
		
		while(true) {
			if(Math.pow(2, answer++) == mid) return answer - 1;
		}
		
	}
}
