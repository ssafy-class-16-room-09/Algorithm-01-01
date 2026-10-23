import java.util.*;

class Solution {
    
    //RULE이 true면 안된다는 뜻
    static boolean[][][] RULE;
    static boolean[] visited;
    static int ANS;    
    
    public int solution(int n, String[] data) {
        RULE = new boolean[8][8][8];
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',0);
        map.put('C',1);
        map.put('F',2);
        map.put('J',3);
        map.put('M',4);
        map.put('N',5);
        map.put('R',6);
        map.put('T',7);
        
        for(int i = 0; i < n; i++){
            String d = data[i];
            int from = map.get(d.charAt(0));
            int to = map.get(d.charAt(2));
            char c = d.charAt(3);
            int num = Integer.parseInt(d.charAt(4)+"");
            
            switch (c){
                case '<':
                    for(int j = num; j < 8; j++){
                        RULE[from][to][j] = true;
                        RULE[to][from][j] = true;
                    }
                    break;
                case '=':
                    for(int j = 0; j < 8; j++) {
                        if (j==num) continue;
                        RULE[from][to][j] = true;
                        RULE[to][from][j] = true;
                    }
                    break;
                case '>':
                    for(int j = 0; j <= num; j++){
                        RULE[from][to][j] = true;
                        RULE[to][from][j] = true;
                    }
                    break;                    
            }            
        }
        
        ANS = 0;
        //각각을 첫 줄 세우며 재귀호출
        for(int i = 0; i < 8; i++){
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            
            visited = new boolean[8];
            visited[i] = true;
            
            recursive(0,temp);
        } 
        return ANS;
    }
    
    void recursive(int idx, List<Integer> temp) {
        if (idx == 7 && temp.size()==8) {            
            ANS++;
            return;
        }
    
        for (int to = 0; to < 8; to++) {
            if (visited[to]) continue;
    
            boolean possible = true;
    
            for (int i = 0; i < temp.size(); i++) {
                int from = temp.get(i);
    
                if (RULE[from][to][idx - i]) {
                    possible = false;
                    break;
                }
            }
    
            if (!possible) continue;
    
            List<Integer> temp2 = new ArrayList<>(temp);
            temp2.add(to);

            visited[to] = true;
            recursive(idx + 1, temp2);
            visited[to] = false;
        }
    }
}
