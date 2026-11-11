n = int(input())
arr = list(map(int, input().split()))

#음수끼리 곱했을때 (음수가 2개이면 됌
arr.sort()
# print(arr)

#앞에 두개가 음수이면 --?
if(arr[0]<0 and arr[1]<0):
    #맨 뒤가 양수일때 --+
    if(arr[-1]>0):
        #앞에 두개가 크면 -->++
        if(arr[0]*arr[1]>arr[-1]*arr[-2]):
            #앞에 두개 곱하기 맨 뒤 --+
            result=arr[0]*arr[1]*arr[-1]
        else:
            #뒤에 3개 곱하기 +++
            result=arr[-1]*arr[-2]*arr[-3]
    else:
        #맨 뒤가 음수일때 --- =>전체가 음수이기 때문에 뒤에부터 3개 곱함
        result=arr[-1]*arr[-2]*arr[-3]
else:
    #전부 양수이거나 -+ 이러면 뒤에부터 3개 +++
    result=arr[-1]*arr[-2]*arr[-3]
# Please write your code here.
print(result)
