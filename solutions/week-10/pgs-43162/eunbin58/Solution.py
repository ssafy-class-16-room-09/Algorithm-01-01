def solution(n, computers):
    # answer = 0
    parent=[]
    def finds(a):
        if(parent[a]==a):
            return a
        return finds(parent[a])
    
    for i in range(n):
        parent.append(i)
    for cur in range(n):
        for index in range(n):
            if(computers[cur][index]==1):
                
                rootA = finds(cur)
                rootB = finds(index)
                
                if(rootA==rootB):
                    continue
                parent[rootB]=rootA
                
    for cur in range(n):
        parent[cur]=finds(cur)
    answer=len(set(parent))
    # print(len(set(parent)))
    print(parent)   
    return answer