
	
	/*
	 * n은 작업시간, n 1시간은 works의 원소 1만큼 처리 가능.
	 * works의 총 합을 구한뒤 n만큼 뺀 후 works의 길이만큼 분배를 하면 최소 야근지수가 구해짐.
	 * 만약 n이 works의 총합보다 크면 야근을 안하므로 0 리턴.
	 * 
	 * 위와 같이 풀 경우 works [8, 1, 1], n 4인 경우 해결 가능한가? 해결 불가함.
	 * 해결하기 위해서는 분배 시 기존 works의 원소보다 분배값이 큰지 작은지를 판별해야 함.
	 * 판별 기준은 기존값보다 작거나 같은 경우까지는 분배가능임.
	 * 
	 * */
	public long solution(int n, int[] works) {
        long answer = 0;
        int[] result = new int[works.length];
        int index = 0;
        
        int worksSum = Arrays.stream(works).parallel().reduce(0, (a, b) -> a + b);

        int overTime = worksSum - n;
        int overAver = overTime / works.length;
        
        // 만약 n이 works의 총합보다 크면 야근을 안 하므로 0 리턴.
        if(overTime <= 0) return 0;
        
        // 제출 후 실패나서 추가한 코드, 처음부터 순회를 하면 loop가 너무 커서 발생하는 듯.
        for (int i = 0; i < works.length; i++) {
        	result[i] = works[i] < overAver ? works[i] : overAver;
    		overTime -= result[i];
        }
        
        while(true) {

        	// overTime 의 분배가 끝났으면 종료
        	if(overTime <= 0) break;
        	
        	if(result[index] < works[index]) {
        		result[index]++;
        		overTime--;
        	}
        	
        	// index 가 works의 길이와 같은 0으로 변경해서 다시 순회함.
        	if(index == works.length - 1) index = 0;
        	else index++;
        }
       
        
        for(int arg : result) {
        	answer += arg * arg;
        }
        
        return answer;
    }
