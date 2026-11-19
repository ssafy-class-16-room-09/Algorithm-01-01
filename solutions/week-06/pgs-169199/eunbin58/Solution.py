from collections import deque
def solution(board):
    answer = 0
    start=(0,0)
    goal=(0,0)
    dx=[1,-1,0,0]
    dy=[0,0,1,-1]
    visit=[[0]*len(board[0]) for _ in range(len(board))]

    for row in range(len(board)):
        for col in range(len(board[0])):
            if(board[row][col]=='G'):
                goal=(col,row)
            elif(board[row][col]=='R'):
                start=(col,row)
    
    q=deque()
    
    # start x, start y, 이전방향 x, 이전 방향y, 여태 몇번 이동했는지
    # 이동방향 : 내가 오른쪽에서 왔으면 그 방향으로 가지 않기 위해 표시
    q.append((start[0],start[1],0,0,0))
    
    flag=False
    result=-1
    
    while q:
        x,y,vx,vy,count=q.popleft()
        
        for i in range(4):
            nx=x+dx[i]
            ny=y+dy[i]

            #내가 왔던 방향이면 continue
            if(vx==dx[i] and vy==dy[i]):
                continue
            #보드 영역 밖이면 continue
            if( nx<0 or ny<0 or nx>=len(board[0]) or ny>=len(board) ):
                continue
            #벽이 있으면 continue
            if(board[ny][nx]=='D'):
                continue
            #한 방향으로 쭉간다
            while( nx>=0 and ny>=0 and nx<len(board[0]) and ny<len(board) and board[ny][nx]!='D'):
                nx=nx+dx[i]
                ny=ny+dy[i]
            #멈춘곳이 start면 continue 
            if(nx-dx[i]==start[0] and ny-dy[i]==start[1]):
                continue
            #멈춤곳이 goal이면 끝
            if(nx-dx[i]==goal[0] and ny-dy[i]==goal[1]):
                flag=True
                break
            #내가 갔던 곳이면 이미 최소로 갔던 곳이여서  continue
            if(visit[ny-dy[i]][nx-dx[i]]!=0):
                continue
            #그 외에는 bfs를 돈다
            q.append((nx-dx[i],ny-dy[i],dx[i]*-1,dy[i]*-1,count+1))
            visit[ny-dy[i]][nx-dx[i]]=count+1
        #flag가 세워지면 끝!
        if flag:
            result=count+1
            break  

    answer=result
    return answer