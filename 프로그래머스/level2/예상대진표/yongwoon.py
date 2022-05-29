import math


def solution(n, a, b):
    answer = int(math.log2(n))  # 지수의 크기가 곧 최대 라운드 수
    if a > b:  # a가 더 작은수로 만든다.
        a, b = b, a

    if a % 2 == 1 and b == a + 1:  # a가 홀수이고 a,b 가 연속 된 수일 때는 바로 경기
        return 1

    left = 1
    right = n
    for _ in range(answer):
        mid = (left + right) // 2
        if a <= mid and b > mid:  # 가운데를 기준으로 떨어져 있다면
            return answer

        elif a > mid:  # 둘 다 중간 보다 위에 있다면
            left = mid + 1
            right = right

        elif b <= mid:  # 둘 다 중간보다 아래
            left = left
            right = mid

        answer -= 1

    return answer