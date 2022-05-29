def find_parent(parent:[], x):
    if parent[x] == x:
        return x
    return find_parent(parent, parent[x])

def union_parent(parent:[], a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

def compare_parent(parent:[], a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a == b:
        return False
    return True

def solution(n, costs):
    answer = 0
    count = 0
    parent = [i for i in range(n)]
    costs.sort(key= lambda x: x[2])
    for i in costs:
        if compare_parent(parent, i[0], i[1]):
            answer += i[2]
            count += 1
            union_parent(parent, i[0], i[1])

        if count == n-1:
            break

    return answer