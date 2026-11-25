import java.util.*;

// 1 3 5 7
// 2 2 6 8

// 1 vs 2 b win +1
// 3 vs 2 b lose
// 3 vs 6 b win +1
// 5 vs 8 b win +1
// 7 vs 2 b lose

class Solution {
    int[] teamA;
    int[] teamB;

    public int solution(int[] A, int[] B) {
        teamA = A;
        teamB = B;
        Arrays.sort(teamA);
        Arrays.sort(teamB);

        int answer = 0;

        int idxA = 0;
        int idxB = 0;
        while(idxB < teamB.length){
            int gamerA = teamA[idxA];
            int gamerB = teamB[idxB];

            if (gamerA >= gamerB){
                idxB++;
                continue;
            }

            idxA++;
            idxB++;
            answer++;
        }

        return answer;
    }

}
