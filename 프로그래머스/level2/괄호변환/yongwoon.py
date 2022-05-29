def correct_close(s):
    s_lst = list()
    for i in s:
        if s_lst and i == ')' and '(' in s_lst:
            s_lst.pop()
        else:
            s_lst.append(i)

    if not s_lst:
        return True
        
    return False

def solution(p):
    if correct_close(p):
        return p
    
    answer = ''
    for i in range(1, len(p), 2):
        if len(p[:i+1]) // 2 == p[:i+1].count(')'):
            u, v = p[:i+1], p[i+1:]
            break

    if correct_close(u):
        answer = u + solution(v)
        return answer 

    else:
        answer = '(' + solution(v) + ')'
        for i in list(p[1:len(u)-1]):
            if i == '(':
                answer += ')'
            else:
                answer += '('
        return answer