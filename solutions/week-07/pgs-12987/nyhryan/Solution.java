import java.util.*;

class Solution {
    record Teammate(int idx, int score) {}
    
    static int[] aScores, bScores;
    public int solution(int[] A, int[] B) {
        aScores = A;
        bScores = B;
        
        Comparator cmp = Comparator.comparingInt(Teammate::score);
        Deque<Teammate> aTeam = new ArrayDeque<>(A.length);
        Deque<Teammate> bTeam = new ArrayDeque<>(A.length);
        
        List<Teammate> temp = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            temp.add(new Teammate(i, aScores[i]));
        }
        Collections.sort(temp, cmp);
        aTeam.addAll(temp);
        
        temp.clear();
        for (int i = 0; i < A.length; i++) {
            temp.add(new Teammate(i, bScores[i]));
        }
        Collections.sort(temp, cmp);
        bTeam.addAll(temp);
        
        int score = 0;
        int iter = 0;
        List<Teammate> aLeft = new ArrayList<>();
        while (!aTeam.isEmpty() && !bTeam.isEmpty()) {
            Teammate a = aTeam.poll();
            Teammate b = bTeam.poll();
            
            if (b.score() > a.score()) {
                score += 1;
            }
            else if (b.score() <= a.score()) {
                aTeam.addFirst(a);
            }
        }

        return score;
    }
}