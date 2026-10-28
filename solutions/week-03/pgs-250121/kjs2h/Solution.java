import java.io.*;
import java.util.*;

// 데이터를 구조화하여 관리하기 위한 Data 클래스 정의
class Data {
    int code;   // 제조 번호
    int date;   // 제조일
    int max;    // 최대 수량
    int remain; // 잔여 수량
    
    Data(int c, int d, int m, int r){
        this.code = c;
        this.date = d;
        this.max = m;
        this.remain = r;
    }
    // @Override
    // public String toString(){
    //      return code+" | "+remain+"/"+max+"\t"+date;
    // }
}

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // 조건에 맞는 데이터들을 담을 리스트 생성
        List<Data> datas = new ArrayList<>();
        
        // 주어진 2차원 배열 데이터를 순회하며 객체로 변환
        for(int[] ds: data){
            int c = ds[0];
            int d = ds[1];
            int m = ds[2];
            int r = ds[3];    
            
            // ext 조건에 따라 val_ext보다 작은 데이터만 리스트에 수집
            switch(ext){
                case "code":
                    if (c < val_ext) datas.add(new Data(c, d, m, r));                    
                    break;
                case "date":
                    if (d < val_ext) datas.add(new Data(c, d, m, r));                    
                    break;
                case "maximum":
                    if (m < val_ext) datas.add(new Data(c, d, m, r));
                    break;
                case "remain":
                    if (r < val_ext) datas.add(new Data(c, d, m, r));
                    break;
                default:                    
                    break;
            }
        }
        // for(Data d: datas) System.out.println(d);
        
        // sort_by 기준에 따라 리스트 정렬 분기 처리
        switch(sort_by){
            case "code":
                datas = datas.stream()
                    .sorted(Comparator.comparingInt(d -> d.code))
                    .toList();
                break;
            case "date":
                datas = datas.stream()
                    .sorted(Comparator.comparingInt(d -> d.date))
                    .toList();                
                break;
            case "maximum":
                datas = datas.stream()
                    .sorted(Comparator.comparingInt(d -> d.max))
                    .toList();
                break;
            case "remain":
                datas = datas.stream()
                    .sorted(Comparator.comparingInt(d -> d.remain))
                    .toList();
                break;
            default:                    
                break;                    
        }                                    
        
        // 정렬 및 필터링이 완료된 리스트를 반환용 2차원 배열로 변환
        int[][] answer = new int[datas.size()][4];
        for(int i = 0; i < datas.size(); i++){
            answer[i][0] = datas.get(i).code;
            answer[i][1] = datas.get(i).date;
            answer[i][2] = datas.get(i).max;
            answer[i][3] = datas.get(i).remain;
        } 
        
        return answer;
    }
}