import java.io.*;
import java.util.*;

class Solution {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] INFO;
    static int APPEACH;
    static List<int[]> ryan;

    public int[] solution(int n, int[] info) throws IOException{
        N=n;
        INFO=info;

        int sum = 0;
        for(int i = 0; i < info.length; i++) if (info[i] != 0) sum += 10-i;
        APPEACH = sum;

        ryan = new ArrayList<>();

        // 파라미터는 순서대로 index, 화살개수, 점수, 그 때 어떻게 쐈는 지
        dfs(0, N, 0, new int[12]);

        // 아래부터 내림차순하고 우수한 것 배출
        if (ryan.size()==0) return new int[]{-1};
        Collections.sort(ryan, (r1, r2) -> {
            for (int i = 11; i >= 0; i--) if (r1[i] != r2[i]) return Integer.compare(r2[i], r1[i]);
            return 0;
        });

        return Arrays.copyOf(ryan.get(0), ryan.get(0).length - 1);
    }

    public void dfs(int idx, int arrow, int score, int[] arrows){
        // ============기저조건===============
        if (arrow < 0) return;

        if (idx == 10){
            // 갱신해야할때에만
            if (score > APPEACH){
                // 남은 화살 전부 0점에 쏘고
                arrows[10] = arrow;
                // 점수 저장한 다음 -- 처음에 그냥 score만 했는데 15,14와 10 2면 10 2가 유리한데 내 코드에서는 15를 저장했음
                arrows[11] = score - APPEACH;
                // list에 담고
                ryan.add(arrows);
            }
            return;
        }
        // ============기저조건===============

        // 무승부는 무조건 라이언의 손해이기 때문에
        // win을 하던 아예 0점을 받아버리자

        // 현재점수와 이기기 위해 소모하는 화살 수(currS, currA)
        int currS = 10 - idx;
        int currA = 0;

        // ============CASE_1===============
        currA = win(idx);
        arrows[idx] = currA;
        // 어피치 점수 있었으면 점수 뺏어버려
        if (INFO[idx] != 0) APPEACH -= currS;

        dfs(idx + 1, arrow - currA, score + currS, Arrays.copyOf(arrows, arrows.length));

        // 어피치 점수 있었으면 점수 돌려줘
        if (INFO[idx] != 0) APPEACH += currS;

        arrows[idx] = 0;
        // ============CASE_1===============

        // ============CASE_2===============
        currA = lose(idx);
        arrows[idx] = currA;

        dfs(idx + 1,arrow-currA, score, Arrays.copyOf(arrows, arrows.length));

        arrows[idx] = 0;
        // ============CASE_2===============

    }

    // 사용한 화살 수와, 얻은 점수를 return
    public int win(int idx){
        int apeach = INFO[idx];
        return apeach+1;
    }

    // 김세진의 가독성을 위한 코드
    public int lose(int idx){
        return 0;
    }
}
