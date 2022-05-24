package com.jackson.algorithms;

import java.util.Arrays;

public class Carpet {

    public static void main(String[] args) {
        int[] ex1 = {4,3};
        int[] ex2 = {3,3};
        int[] ex3 = {8,6};

        int[] result1 = solution(10, 2);
        int[] result2 = solution(8, 1);
        int[] result3 = solution(24, 24);

        System.out.println(Arrays.equals(result1, ex1));
        System.out.println(Arrays.equals(result2, ex2));
        System.out.println(Arrays.equals(result3, ex3));
    }

    /*
     * 전체 격자의 개수 = brown + yellow = x(가로) * y(세로)
     * 브라운 = 2x + 2(y-2) = 2x + 2y - 4 = 2(x + y -2)
     * */

    public static int[] solution(int brown, int yellow) {
        int x;
        int y =3;
        int sum = brown+yellow;

        outerloop:
        for(x = 3; sum > x; x++){
            for(y = 3; sum >= x*y; y++){
                if(sum == x*y && brown == (2*x+2*y-4))
                {
                    break outerloop;
                }
            }
        }
        return new int[]{y,x};
    }
}
