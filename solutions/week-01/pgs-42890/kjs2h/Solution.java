import java.util.*;

class Solution {
    static int ANS;
    static int MAXI;
    static List<List<Integer>> IDXLIST;
    
    public int solution(String[][] relation) {
        // 답
        ANS = 0;
        // column의 개수
        MAXI = relation[0].length;
        // List<>의 List
        IDXLIST = new ArrayList<>();        
        // List<>의 List안의 List
        List<Integer> idxs = new ArrayList<>();
        // 모든 부분집합 만들기
        subset(0, idxs);
        
        // 만든 모든 부분집합에 대해 정렬 기준 수립 (column의 개수)
        IDXLIST.sort((a, b) -> a.size() - b.size());
        
        // 후보 List 만들고
        List<List<Integer>> candidate = new ArrayList<>();        
        StringBuilder sb = new StringBuilder();
        
        // 만든 보든 부분집합 순회하면서
        for(List<Integer> IDX : IDXLIST){                                                            
            
            // 최소인 지 확인하는 flag
            boolean minimal = true;
            // 최소가 아니라면 (이미 후보 키에 존재하면) 무시하자
            for(List<Integer> key: candidate){
                if(IDX.containsAll(key)){
                    minimal = false;
                    break;
                }
            }            
            if(!minimal) continue;
            
            // HashSet 만들고
            Set<String> set = new HashSet<>();            
            // 주어진 reltation 하나마다
            for(String[] r : relation){
                // sb 초기화하고
                sb.setLength(0);                
                // IDX = 만든 모든 부분집합 중 1개
                for(int i : IDX){
                    // i = 그 집합 중 1개의 column마다
                    // 구분자를 추가하며 sb에 추가해 준 다음
                    sb.append(r[i]).append('|');
                }                
                // HashSet에 넣고
                set.add(sb.toString());
            }
            // HashSet의 size()가 relation의 전체 길이와 같다면? 
            // 만든 column의 집합이 키들을 구분한다
            if(set.size() == relation.length){
                // 그럼 후보 키에 IDX를 복사해서 넣자
                candidate.add(new ArrayList<>(IDX));
                ANS++;
            }
        }
        
        return ANS;
    }
    
    
    void subset(int idx, List<Integer> idxs){
        
        if(idx == MAXI){
            IDXLIST.add(new ArrayList<>(idxs));
            return;
        }
        //idx를 추가하고
        idxs.add(idx);
        //뻗어나가기
        subset(idx + 1, idxs);
        //돌아와서 idx 삭제하고
        idxs.remove(idxs.size() - 1);
        //뻗어나가기
        subset(idx + 1, idxs);
    }
}