# 중복 순열 문제
# list(map(''.join,product(data,repeat=i)))
# ''.join쓰는 법과 map을 더 잘 써야 할 것 같다

from itertools import product
def solution(word):
    answer = 0
    diction=[]
    data=["A","E","I","O","U"]
    
    #나올 수 있는 모든 경우의 수를 구함
    for i in range(1,6):
        comrep=list(map(''.join,product(data,repeat=i)))
        diction+=comrep
    #정렬
    diction.sort()
    #찾기
    answer=diction.index(word)+1
    return answer