
	
	/**
	 * 탐욕법 풀이
	 * 모든 자동차가 카메라를 만나게 하는 '최소' 대수를 구하기
	 * 
	 * routes[i][0] i번째의 차량 진입 시점
	 * routes[i][1] i번째의 차량 나간 시점
	 * 
	 * -20 ~ -15 / -12 ~ -5 / -14 ~ -13 
	 * 
	 * 새로운 차량이 추가되었을 때 이전 차량이 지나간 구간과 1곳이라도 중복되면
	 * 이미 카메라가 설치된 것으로 취급
	 * 1곳도 중복되지 않으면 신규 카메라 설치가 필요한 것으로 취급
	 * 
	 * */
	public int solution(int[][] routes) {
        int answer = 0;
        
//        Set<Integer> cameraSpot = new HashSet<Integer>();
//        
//        for(int[] camera : routes) {
//        	
//        	boolean spotExist = false;
//        	
//        	if(cameraSpot.contains(camera[0]) && cameraSpot.contains(camera[1])) continue;
//        	
//        	if(!spotExist) {
//        		for(int i = camera[0]; i <= camera[1]; i++) {
//            		
//            		if(cameraSpot.contains(i)) spotExist = true;
//            		else cameraSpot.add(i);
//            	}
//        	}
//        	
//        	if(!spotExist) answer++;
//        }
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] route1, int[] route2) {
                return route1[1] - route2[1];
            }
        });
        
        int cameraSpot = Integer.MIN_VALUE;
        
        for(int[] route : routes) {
        	if(cameraSpot < route[0]) {
        		cameraSpot = route[1];
        		answer++;
        	}
        }
        
        return answer;
    }
	
