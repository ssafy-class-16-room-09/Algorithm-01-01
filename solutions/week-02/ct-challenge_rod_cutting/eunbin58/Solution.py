#입출력
n = int(input())
profit = [0]+list(map(int, input().split()))
# Please write your code here.

#profit에 1부터 순서대로 제일 수익이 높은걸 넣음
for i in range(1,n+1):
    # max[i]=profit[i]
    #i가 4라면 1+3,2+2까지 value가 되면서 원래 있는 값보다 수익이 높으면 대체
    for j in range(1,i//2+1):
        value=profit[j]+profit[i-j]
        if(value>profit[i]):
            profit[i]=value
print(profit[n])
