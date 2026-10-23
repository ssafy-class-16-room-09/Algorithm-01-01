
#import sys
#sys.stdin = open("input.txt", "r")

T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):

    n=input()
    x,y=0,0
    board = [list(map(int, input().split())) for _ in range(100)]
    #2가 어디있는지 찾기
    for i in range(100):
        if(board[99][i]==2):
            x=i
            y=99

    #2부터 위로 가고 오른쪽이나 왼쪽이 있으면 해당 부분으로 가야함
    #왼쪽 발견시 위가 발견될때까지 왼쪽으로만
    #오른쪽 발견시 위가 발견될때까지 오른쪽으로만
    #아래는 갈 필요x

    while True:
        #첫째칸으로 왔을때
        if(y==0):
            break
        #왼쪽을 찾으면
        if(x>0 and board[y][x-1]==1):
            x=x-1
            y=y
            #왼쪽으로 쭉 감
            while(x>0 and board[y][x-1]==1):
                x=x-1
                y=y
            #모든게 끝나면 위로 다시 감
            x=x
            y=y-1
        #오른쪽을 찾으면
        elif(x<99 and board[y][x+1]==1):
            x=x+1
            y=y
            #오른쪽으로 쭉감
            while (x<99 and board[y][x+1]==1):
                x=x+1
                y=y
            #모든게 끝나면 위로 다시 감
            x=x
            y=y-1
        #위로 갈때
        elif(y>0 and board[y-1][x]==1):
            x=x
            y=y-1

    print(f"#{test_case} {x}")

        

    
    
