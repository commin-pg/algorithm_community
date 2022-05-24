package com.jackson.algorithms;

import java.util.Arrays;
import java.util.Collections;

/*
* Prorgammers : https://programmers.co.kr/learn/courses/30/lessons/12927
* */
public class OvertimeWorkRate {


    public static void main(String[] args) {
        // write your code here
        int[] ex1 = new int[]{4,3,3};
        int[] ex2 = new int[]{2,1,2};
        int[] ex3 = new int[]{1,1};
        int[] ex4 = new int[]{4,4,4};
        int[] ex5 = new int[]{5,6,6};

        System.out.println(solution3(4, ex1) == 12);
        System.out.println(solution3(1, ex2) == 6);
        System.out.println(solution3(3, ex3) == 0);
        System.out.println(solution3(5, ex4) == 17);
        System.out.println(solution3(5, ex5) == 16*3);
    }

    private static long solution4(int n, int[] works){
        long answer = 0;

        // 1 length가 1인 경우 빠르게 처리해주자
        if(works.length == 1){
            works[0] = works[0] - n;
            if(works[0] < 0) return 0;
            return (long) works[0] * works[0];
        }

        // 내림차순으로 정렬
        reverseSort(works);

        // 우선순위 큐로 만들어준 뒤
        // 0번째 인자에서 하나씩 빼줌
        while (n > 0){
            priorityQueue(works);
            if(works[0] == 0) break;
            works[0]--;
            n--;
        }

        // 제곱해서 더해줍니다.
        for (int work: works) answer += (long) work * work;

        return answer;
    }

    public static long solution(int n, int[] works) {
        long answer = 0;
        reverseSort(works);

        for(int i = 0; i < works.length-1; i++){
            System.out.println("n : " + n);
            System.out.println("i : " + i);
            Arrays.stream(works).forEach(System.out::print);
            System.out.println();
            if(n == 0) break;
            if(works[0] == 0)
            while (works[i] >= works[i+1]){
                works[i]--;
                n--;
                if(n == 0) break;
                System.out.println("inner n : " + n);
                Arrays.stream(works).forEach(System.out::print);
                System.out.println();
            }
            if(i == works.length-2){
                if(works[i+1] == 0)break;
                while (works[i] != works[i+1]){
                    works[i+1]--;
                    n--;
                    System.out.println("second inner inner n : " + n);
                    Arrays.stream(works).forEach(System.out::print);
                    System.out.println();
                }
                i = -1;
                System.out.println("second inner n : " + n);
                Arrays.stream(works).forEach(System.out::print);
                System.out.println();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(" ======== DONE ======== n : " + n);

        for(int work : works) answer += (long) work * work;

        return answer;
    }


    public static long solution2(int n, int[] works) {
        long answer = 0;
        reverseSort(works);
        outerloop:
        for(int i = 0; i < works.length-1; i++){
//            reverseSort(works);
//            System.out.println("n : " + n);
//            System.out.println("i : " + i);
//            Arrays.stream(works).forEach(System.out::print);
//            System.out.println();
            if(n == 0) break;
            if(works[0] == 0) break;
            while (works[i] >= works[i+1]){
                if(works[i] == 0) break;
                works[i]--;
                n--;
                if (n == 0) break outerloop;
//                System.out.println("inner n : " + n);
//                Arrays.stream(works).forEach(System.out::print);
//                System.out.println();
            }
            if(i == works.length - 2){
                reverseSort(works);
                i = -1;
            }
        }

//        System.out.println(" ======= LAST ======= ");
//        Arrays.stream(works).forEach(System.out::print);

        for(int work : works) answer += (long) work * work;

        System.out.println(" ======== DONE ======== n : " + n);
        System.out.println("result : " + answer);

        return answer;
    }

    public static long solution3(int n, int[] works) {
        long answer = 0;

        if(works.length == 1){
            works[0] = works[0] - n;
            return (long) works[0] * works[0];
        }


        outerloop:
        while(n > 0){
            reverseSort(works);
//            System.out.println("n : " + n);
//            Arrays.stream(works).forEach(System.out::print);
//            System.out.println();
            if(works[0] == 0) break;
            while (works[0] >= works[1]) {
                if(works[0] == 0) break;
                works[0]--;
                n--;
                if(n == 0)break outerloop;
//                System.out.println("inner n : " + n);
//                Arrays.stream(works).forEach(System.out::print);
//                System.out.println();
            }
        }

        for(int work : works) answer += (long) work * work;

//        System.out.println(" ======== DONE ======== n : " + n);
//        System.out.println("result : " + answer);
        return answer;
    }

    public static void reverseSort(int[] arr){
        Arrays.sort(arr);
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
