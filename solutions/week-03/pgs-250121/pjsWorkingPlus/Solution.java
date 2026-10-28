import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        // 해결 방식
        // 1. ext 타입을 기준 삼아 val_ext 이하인 것들만 필터링, 충족 Data들로 리스트 생성
        // 2. sort_by로 오름차순 정렬
        
        // 필터링을 통과한 Data들을 담을 변수
        List<Data> answerList=new ArrayList<>();
        int N=data.length;
        int[][] answer = null;
        Data tempData=null;
        
        // 만약 필터링 기준이 date라면 편한 연산처리를 위해 yyyymmdd 형식을 0년1월1일로 부터 얼마나 멀리 떨어졌는지로 연산
        if(ext.equals("date")){
            // 문자열 비교는 ==이 아니라 equals
            val_ext=Data.convertDateToAcc(val_ext/10000,val_ext/100%100,val_ext%100);  
        }
        
        // 리스트에 담을 데이터 필터링 순회
        for(int idx=0;idx<N;idx++){    
            tempData=new Data(data[idx][0],data[idx][1],data[idx][2],data[idx][3]);
            // 현재 배정할 데이터가 ext 타입 기준의 val_ext와 같거나 크면 필터링
            if(tempData.getValue(ext)>=val_ext){
                continue;
            }
            // 필터링 충족 Data는 리스트에 추가
            answerList.add(tempData);
        }
        
        // 필터링 조건 충족한 것들만 오름차순 정렬
        answerList.sort((d1,d2)->{
            return Integer.compare(d1.getValue(sort_by),d2.getValue(sort_by));
        });
        
        // 정답 answer배열에 복사
        answer=new int[answerList.size()][4];
        for(int i=0;i<answerList.size();i++){
            answer[i][0]=answerList.get(i).code;
            answer[i][1]=answerList.get(i).date;
            answer[i][2]=answerList.get(i).max;
            answer[i][3]=answerList.get(i).remain;
        }
        
        return answer;
    }
}

class Data{
    int code;
    int date; // 20301231 형태 그대로 저장
    int max;
    int remain;
    int year; // 2030
    int month; // 12
    int day; // 31
    int dateAcc; // 0월 1월 1일을 1로 기준 잡아 얼마나 떨어진 날인지 보이는 변수 (ex) 650321 
    
    static int[] daysInMonth={0,31,29,31,30,31,30,31,31,30,31,30,31};
    
    public Data(int c,int d,int m,int r){
        this.code=c;
        this.date=d;
        this.max=m;
        this.remain=r;
        this.year=d/10000;
        this.month=d/100%100;
        this.day=d%100;
        this.dateAcc=convertDateToAcc(year,month,day);
        
        // System.out.printf("code %2d, date %8d max %3d remain %3d year %4d month %2d day %2d dateAcc %7d\n",code,date,max,remain,year,month,day, dateAcc);
    }
    
    // data 객체에 원하는 type 문자열로 값을 요청하면 해당 값을 반환하는 getter
    public int getValue(String type){
        int result=-1;
        switch(type){
                case("code"):{
                    result=code;
                    break;
                }
                case("date"):{
                    result=dateAcc;
                    break;
                }
                case("maximum"):{
                    result=max;
                    break;
                }
                case("remain"):{
                    result=remain;
                    break;
                }
        }
        return result;
    }
    
    // 0년1월1일로부터 얼마나 멀리 떨어졌는지로 연산, 헬퍼 메서드
    public static int convertDateToAcc(int y,int m,int d){
        int result=0;
        result=(y-1)*365;
        for(int month=1;month<m;month++){
            result+=daysInMonth[month];
        }
        result+=d;
        return result;
    }
}