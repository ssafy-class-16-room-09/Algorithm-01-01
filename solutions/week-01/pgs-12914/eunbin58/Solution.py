def solution(n):
    
    d=[0]*(n+1)
    d[1]=1
    if(n>=2):
        d[2]=2
    for i in range(3,n+1):
        d[i]=d[i-1]+d[i-2]
    # print(d)
    answer = d[n]%1234567
    return answer