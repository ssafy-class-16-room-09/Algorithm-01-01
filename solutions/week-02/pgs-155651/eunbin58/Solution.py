def solution(book_time):
    answer = 0
    room=[]
    #모든 배열을 전부 분으로 치환
    #시작 시간 -10
    for i in range(len(book_time)):
        #입실
        book_time[i][0]=int(book_time[i][0][:2])*60+int(book_time[i][0][3:])-10
        #퇴실
        book_time[i][1]=int(book_time[i][1][:2])*60+int(book_time[i][1][3:])
    
    #끝나는 시간을 내림차순으로 sort
    book_time=sorted(book_time,key=lambda x:-x[1])
    
    #시작 하나 넣어주기
    room.append(book_time[0])
    #뒤부터 room에 append
    #for book_time
    #퇴실이 입실 보다 크면 append
    #아니면 입실 변경
    for i in range(1,len(book_time)):
        #cur: 새로 넣을 것
        cur=book_time[i]
        #book 기존 시작 시간
        for book in range(len(room)):            
            if(room[book][0]<cur[1]):
                #다 비교후에 넣을 곳이 없으면 넣어야함
                if(book==len(room)-1):
                    room.append(cur)
            else:
                room[book][0]=cur[0]
                break
    # print("전체",room)
    answer=len(room)
    
    
    
    
    return answer