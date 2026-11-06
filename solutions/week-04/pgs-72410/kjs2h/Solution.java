
import java.util.*;

class Solution {
    
    static int S;
    static int dc, ic, rc;
    static String[] M;
    static int MIN;
    
    public int solution(int[] picks, String[] minerals) {
        
        S = 0;
        dc = picks[0];
        ic = picks[1];
        rc = picks[2];
        M = minerals;
        MIN = Integer.MAX_VALUE;
        dfs(0);
                
        return MIN;
    }
        
    void dfs(int idx){   
        
        //기저 조건           
        if (idx >= M.length || dc + ic + rc == 0){
            // System.out.println(MIN+" |vs| "+S+" "+idx);            
            MIN = Math.min(MIN,S);            
            return;
        }  
        
        //분기 처리                          
        
        //곡괭이 선택      
        
        //다이아 곡괭이 사용 가능하면 사용
        if (dc > 0){
            int w = 0;      
            for(int i = 0; i < Math.min(5, M.length - idx); i++){
                w += work(1, idx + i);    
            }        
            dc -= 1;                                
            S += w;
            dfs(idx + 5);      
            S -= w;
            dc += 1;
        }
        
        //철 곡괭이 사용 가능하면 사용
        if (ic > 0){
            int w = 0;      
            for(int i = 0; i < Math.min(5, M.length - idx); i++){
                w += work(2, idx + i);    
            }        
            ic -= 1;                                
            S += w;
            dfs(idx + 5);      
            S -= w;
            ic += 1;
        }
    
        //돌 곡괭이 사용 가능하면 사용
        if (rc > 0){
            int w = 0;      
            for(int i = 0; i < Math.min(5, M.length - idx); i++){
                w += work(3, idx + i);    
            }        
            rc -= 1;                                
            S += w;
            dfs(idx + 5);      
            S -= w;
            rc += 1;
        }          
    }
    
    int work(int selected, int idx){        
        String curr = M[idx];
        
        if ( (selected == 2 && curr.equals("diamond")) || (selected == 3 && curr.equals("iron")) ) return 5;
        if (selected == 3 && curr.equals("diamond")) return 25;
        else return 1;        
    }        
}
