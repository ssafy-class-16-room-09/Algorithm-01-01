#완탐으로 풀어야함
import sys
N=int(input())
board=[]
for _ in range(N):
    board.append(list(map(int,input().split())))
# print(board)

visited=[False]*N
count=0



mins=sys.maxsize
def dfs(depth, sumcount,cur):
    global mins 
    # sum이 최소보다 커지거나 depth가 0이 아닌데 들어가는게 0인 경우 -> 1은 맨 마지막으로 들어와야함
    if(sumcount>mins) or (depth!=0 and depth!=N and cur==0 ):
        return
    #마지막 깊이까지 오고 0으로 들어오는 경우
    if(depth==N and cur==0):
        mins=sumcount

    for j in range(len(board[0])):
        if(visited[j]==True or board[cur][j]==0):
            continue
        #들어가는 자리 True
        visited[j]=True
        #재귀 호출
        dfs(depth+1,sumcount+board[cur][j],j)
        #왔던곳 자리 false
        visited[j]=False
        
    

dfs(0,0,0)
print(mins)