package algorithm_community;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Commin2 {

//	제한사항
//	섬의 개수 n은 1 이상 100 이하입니다.
//	costs의 길이는 ((n-1) * n) / 2이하입니다.
//	임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
//	같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
//	모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
//	연결할 수 없는 섬은 주어지지 않습니다.

	@Test
	public void test() {
		int[][] costs = { { 0, 1, 1 }, // 0번 섬 - 1번 섬 costs[0][0] - [0][1]
				{ 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		assertEquals(4, solution(4, costs));
	}

	Map<Integer, Integer> nodeSet = null;
	Map<Integer, Integer> rankSet = null;

	public int solution(int n, int[][] costs) {
		int answer = 0;

		// 파스칼 알고리즘
		// 1. 개발 집합으로 초기화 Map , Key : Root Node, Value : Node Name
		nodeSet = initSet(n);
		rankSet = rankSet(n);

		// 2. 간선 정렬
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}

		});

		// 3. 사이클이 생기는지 확인후 안생기면 연결
		for (int[] costArr : costs) {
			int node1 = costArr[0];
			int node2 = costArr[1];
			int cost = costArr[2];
			if (find(node1) != find(node2)) {
				union(node1, node2);
				answer += cost;
			}
		}

		return answer;
	}
	
	public int find(int node) {
		// Path Compression 
		if(nodeSet.get(node) != node) {
			nodeSet.put(node, find(nodeSet.get(node)));
		}
		return nodeSet.get(node);
	}
	
	public void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		// union-by-rank 기법 (O(1))
		if(rankSet.get(root1)> rankSet.get(root2)) {
			nodeSet.put(root2, root1);
		}else {
			nodeSet.put(root1,root2);
			if(rankSet.get(root1) == rankSet.get(root2)) {
				rankSet.put(root2, rankSet.get(root2) + 1);
			}
		}
	}

	public Map<Integer, Integer> initSet(int n) {

		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++)
			resultMap.put(i, i);

		return resultMap;
	}

	public Map<Integer, Integer> rankSet(int n) {

		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++)
			resultMap.put(i, 0);

		return resultMap;
	}

}
