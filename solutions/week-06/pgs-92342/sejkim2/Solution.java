import java.util.*;

class Solution {
    int maxDiff;
    int[] answer;
    int[] ryan;
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        ryan = new int[11];
        maxDiff = -1;
            
        dfs(info, 0, n);
        if (maxDiff == -1)
            return new int[]{-1};
        
        return answer;
    }
    
    //depth : 라운드, n : 남은 화살
    void dfs(int[] info, int depth, int n) {
        if (depth == 11) {
            //만약 안쏜 화살이 있으면 마지막에 다 쏘기 (승리 조건 때문에 가장 낮은 점수를 많이 맞힌 경우가 우승)
            if (n > 0)
                ryan[10] = n;
            int sumA = 0;
            int sumR = 0;
            
            //라이언, 아파치 점수 계산
            for(int i = 0; i<11; i++) {
                if (ryan[i] > info[i])
                    sumR += (10 - i);
                else if (ryan[i] == info[i] && ryan[i] != 0) 
                    sumA += (10 - i);
                else if (ryan[i] < info[i])
                    sumA += (10 - i);
            }
            
            //라이언 승리인 경우 점수 차가 더 크거나 같은 경우에만 갱신
            if (sumR > sumA) {
                //점수 차가 더 크면 라이언의 점수표 갱신
                if (maxDiff < sumR - sumA) {
                    maxDiff = sumR - sumA;
                    ryan2answer();
                }
                // 점수차 같으면 승리 조건 때문에 가장 낮은 점수를 많이 맞춘 경우에만 갱신
                //점수표를 역순으로 돌리면서 라이언이 더 큰게 나오면 answer 갱신하고 종료 
                else if (maxDiff == sumR - sumA) {
                    for(int i = 10; i >= 0; i--) {
                        if (answer[i] == ryan[i]) continue;
                        else if (answer[i] < ryan[i]) {
                            ryan2answer();
                            break;
                        } else break;
                    }    
                }
            }
            return;
        }
        
        //1 ryan win (아파치의 점수 + 1)
        if (info[depth] + 1 <= n) {
            ryan[depth] = info[depth] + 1;
            dfs(info, depth + 1, n - (info[depth] + 1));
        }
        
        //2 draw (아파치의 점수와 같음)
        if (info[depth] <= n) {
            ryan[depth] = info[depth];
            if (info[depth] == 0) 
                dfs(info, depth + 1, n - info[depth]);
            else 
                dfs(info, depth + 1, n - info[depth]);
        }
        
        //3 ryan loss (0)
        ryan[depth] = 0;
        dfs(info, depth + 1, n);
    }
    
    //ryan -> answer로 copy
    void ryan2answer() {
        for(int i = 0; i < 11; i++)
            answer[i] = ryan[i];
    }
}