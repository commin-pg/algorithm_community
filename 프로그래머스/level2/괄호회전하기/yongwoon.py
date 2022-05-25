# 해당 문자열이 올바른 괄호인지 확인하는 함수를 만들고,
# 큐 자료구조를 사용하여 문자열의 길이만큼 popleft(), append()한 돌리는 문자를 확인하고 true면 answer += 1 
from collections import deque


def correct_close(s):
    d = {
        ']' : '[',
        '}' : '{',
        ')' : '('
    }
    s_lst = list()
    for i in s:
        if s_lst and i in d and d[i] in s_lst:
            s_lst.pop()
        else:
            s_lst.append(i)

    if not s_lst:
        return True
        
    return False

def solution(s):
    answer = 0
    q = deque(s)
    for _ in range(len(s)):
        if correct_close(q):
            answer += 1

        w = q.popleft()
        q.append(w)

    return answer