
	/*
	 * 가로 길이는 세로 길이와 같거나 큼.
	 * brown 과 yellow 의 합이 총 격자 개수임.
	 * 가로 X 세로 는 총 격자개수이며, (가로 - 2) X (세로 - 2) 는 테두리를 제외한 격자 개수임.
	 * 즉 (가로 - 2) X (세로 - 2) 의 개수는 yellow 와 동일해야 함.
	 * */
	public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int allGrid = brown + yellow;
        
        int width = 0;
        int height = 0;
        
        while(true) {
        	
        	height++;
        	
        	if((allGrid % height) == 0) {
        		
        		width = allGrid / height;
        		
        		if((width - 2) * (height - 2) == yellow) break;
        	} 
        }
        
        answer[0] = width;
        answer[1] = height;
        
        return answer;
    }
