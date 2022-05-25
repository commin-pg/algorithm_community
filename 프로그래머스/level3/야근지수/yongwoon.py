# 파이썬에서는 우선순위 큐를 힙이라는 자료구조로 지원하고 있고 최소 트리만을 지원하고 있다. 그래서 최대값을 확인하려 할 때는 (-)음수로 해서 접근한다.
# 1) wokrs 리스트를 heap으로 바꿔준다. 
# 2) n번 만큼 반복문을 실행하고 가장 큰(가장 작은) 값을 계속 더해주며 힙 구조의 상태 변화를 유지하면서 값을 빼주고 더해준다.
# 3) 마지막으로 나온 리스트를 제곱하여 더해주면 끝!

# 처음에는 정렬을 통해 접근을 했으나 반복문이 돌 때마다 정렬을 하는 것은 n**2logn 만큼 시간복잡도가 생긴다. 따라서 우선순위 큐로 힙 자료구조를 통해 시간 복잡도를 줄일 수 있었다. 힙의 푸시, 팝 연산은 O(logn) 으로서 최종 시간 복잡도는 O(n*logn)으로 예상한다
import heapq


def solution(n, works):
    if sum(works) < n:
        return 0

    heap = []
    for i in works:
        heapq.heappush(heap, -i)

    for _ in range(n):
        val = heapq.heappop(heap)
        val += 1
        heapq.heappush(heap, val)

    answer = sum(list(map(lambda x: x**2, heap)))

    return answer