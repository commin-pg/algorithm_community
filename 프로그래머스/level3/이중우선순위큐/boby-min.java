package min.dev.edu.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DoubleQueue {

	@Test
	public void call() {

		String[] oper = {"I 7","I 5","I -5","D -1"};
		
		for(int arg : solution(oper)) {
			System.out.println(arg);
		}
		
		String[] oper2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
		
		for(int arg : solution(oper2)) {
			System.out.println(arg);
		}
		
		String[] oper3 = {"I 16","D 1", "D -1"};
		
		for(int arg : solution(oper3)) {
			System.out.println(arg);
		}
		
	}
	
	public int[] solution(String[] operations) {
        
		int[] answer = {0, 0};
		
        List<Integer> nums = new ArrayList<Integer>();
        String[] splitStr = null;
        
        for(String arg : operations) {
        	splitStr = arg.split(" ");
        	
        	if(splitStr[0].equals("I")) {
        		// oper 가 아예 없는 경우도 있으므로 list에 추가 후 정렬 처리함.
        		nums.add(Integer.parseInt(splitStr[1]));
        		Collections.sort(nums);
        	} else {
        		
        		// 숫자가 비어있는 경우 oper 연산 하지 않음.
        		if(nums.size() == 0) continue;
        		
        		if(splitStr[1].equals("1")) {
        			nums.remove(nums.size() - 1);
        		}else if(splitStr[1].equals("-1")) {
        			nums.remove(0);
        		}else {
        			continue;
        		}
        	}
        }
        
        if(nums.size() != 0) {
        	answer[0] = nums.get(nums.size() - 1);
        	answer[1] = nums.get(0);
        } else {
        	answer[0] = 0;
        	answer[1] = 0;
        }
        
        return answer;
    }
	
//	public int[] solution(String[] operations) {
//        
//		int[] answer = {0, 0};
//		
//        List<Integer> nums = new ArrayList<Integer>();
//        List<OPER_ENUM> opers = new ArrayList<OPER_ENUM>();
//        String[] splitStr = null;
//        
//        for(String arg : operations) {
//        	splitStr = arg.split(" ");
//        	
//        	if(splitStr[0].equals("I")) {
//        		nums.add(Integer.parseInt(splitStr[1]));
//        	} else {
//        		if(splitStr[1].equals("1")) {
//        			opers.add(OPER_ENUM.MAX);
//        		}else if(splitStr[1].equals("-1")) {
//        			opers.add(OPER_ENUM.MIN);
//        		}else {
//        			continue;
//        		}
//        	}
//        }
//        
//        Collections.sort(nums);
//        
//        for(OPER_ENUM oper : opers) {
//        	
//        	if(nums.size() == 0) break;
//        	
//        	switch(oper) {
//        		case MAX:
//        			nums.remove(nums.size() - 1);
//        			break;
//        		case MIN:
//        			nums.remove(0);
//        			break;
//        		default:
//        			continue;
//        	}
//        }
//        
//        if(nums.size() != 0) {
//        	answer[0] = nums.get(nums.size() - 1);
//        	answer[1] = nums.get(0);
//        } else {
//        	answer[0] = 0;
//        	answer[1] = 0;
//        }
//        
//        return answer;
//    }
}
