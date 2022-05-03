
import java.util.Arrays;

public class ImmigrationPass {


	public long solution(int n, int[] times) {
        
		long answer = 0;
		
		// 최악의 시간을 구하기 위해 sorting
		Arrays.sort(times);
        
		long min = 0;
		long mid = 0;
		long max = (long) n * times[times.length - 1];
		
		long devide = 0;
		
		while(min <= max) {
			mid = (min + max) / 2;
			devide = 0;
			
			for(int i = 0; i < times.length; i++) {
				devide += mid / times[i];
			}
			
			if(devide < n) {
				min = mid + 1;
			} else {
				max = mid - 1;
				answer = mid;
			}
		}
		
		return answer;
    }
	
//	public long solution(int n, int[] times) {
//        
//		long answer = 0;
//		
//		// 최악의 시간을 구하기 위해 sorting
//		Arrays.sort(times);
//        
//		double badTime = (n * times[times.length - 1]) / times.length;
//		double sum = 0.0;
//		
//		while(true) {
//			
//			sum = 0.0;
//			
//			for(int i = 0; i < times.length; i++) {
//				sum += badTime / times[i];
//			}
//			
//			if(sum < (n + 1)) {
//				answer = (long) badTime;
//				break;
//			} else {
//				badTime -= 1;
//			}
//		}
//		
//		return answer;
//    }
	
}
