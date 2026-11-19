from itertools import combinations
T=int(input())

for tc in range(1,T+1):
    n=int(input())
    data=list(map(int,input().split()))
    max=-1

    #combinations : 조합을 만들어주는 함수
    combin=list(combinations(data,2))
    for i,j in combin:
        flag=True
        result=str(i*j)
        #for문을 문자열의 마지막 전까지 돌아요
        for k in range(len(result)-1):
            if(int(result[k])>int(result[k+1])):
                flag=False
                break
        if(flag==True and max<int(result)):
            max=int(result)
    print(f"#{tc}",max)