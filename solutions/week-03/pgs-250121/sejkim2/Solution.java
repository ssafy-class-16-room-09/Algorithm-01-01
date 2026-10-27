import java.util.*;

class Solution {    
    public ArrayList<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<int[]> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); //ext, sort_by를 인덱스로 찾기
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
        
        int idx = map.get(ext);
        int sort = map.get(sort_by);
        
        for(int[] info : data)
            if (info[idx] < val_ext)
                answer.add(info);
        
        Collections.sort(answer, (a, b) -> a[sort] - b[sort]);
        return answer;
    }
}