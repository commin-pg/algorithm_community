# 브라운과 옐로우 갯수의 합을 구하고 합의 약수를 구한다.
# 제곱근이 정수라면 제곱근이 가로 세로
# 인트가 아니라면 그 양쪽에 있는 약수가 가로 세로의 길이

def solution(brown, yellow):
    s = brown + yellow
    if s**0.5 == int(s**0.5):
        answer = [s**0.5, s**0.5]
    else:
        for i in range(int(s**0.5)+1, s):
            if not s % i and (i-2)*(s//i-2) == yellow: # i가 s의 약수이면서 두 수에서 -2를 한 값의 곱이 yellow가 나와야한다.
                answer = [i, s//i]
                break
            
    return answer