# 0~n-1의 노드의 부모의 번호가 무엇인지 찾는 함수
# 부모의 번호를 찾았다면 재귀로 부모의 부모로 거슬러 올라가 최종 선조의 노드를 부모의 번호를 리턴!
def find_parent(parent:[], x):
    if parent[x] == x:
        return x
    return find_parent(parent, parent[x])

# 부모를 연결할 때, 더 작은 수를 부모로 만드는 함수
def union_parent(parent:[], a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

# 부모를 비교하는 함수로서 세개의 노드가 연결이 되는 것을 확인하는 함수
def compare_parent(parent:[], a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a == b: #부모가 같다면, 세개의 노드가 연결이 되어 무한 루프되는...
        return False
    return True

# 우선 자기 자신을 부모로하는 부모 리스트 생성
# (낮은)비용을 기준으로 연결 노드 리스트를 정렬
# 연결 된 노드가 각 각의 부모를 찾아 비교해주고 부모가 다르다면, 연결(union_parent 함수)! 
# 연결할 때에는 비용을 answer에 더해준다. 연결된 노드의 수 업!
# 만약 연결된 노드의 수가 총 노드의 갯수에서 1을 뺸 값과 일치한다면 모든 노드가 연결이 됐다고 보고 반복문 탈출!
# 모든 노드의 연결 된 총 비용의 합을 리턴한다.  
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