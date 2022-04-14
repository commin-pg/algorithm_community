import java.util.HashSet;
import java.util.Set;


class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        Set<Integer> poketmonType = new HashSet<Integer>();

        for(int num : nums){
            poketmonType.add(num);
        }

        int numsHalfLength = nums.length / 2;

        if(poketmonType.size() > numsHalfLength) return numsHalfLength;
        else return poketmonType.size();
    }
}
