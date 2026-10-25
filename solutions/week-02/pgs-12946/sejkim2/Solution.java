import java.util.*;

class Solution {
    ArrayList<int[]> list = new ArrayList<>();
    int N;

    public ArrayList<int[]> solution(int n) {
        N = n;

        hanoi(N, 1, 2, 3);
        return list;
    }

    //start에서 시작해서 to를 거쳐 end로 보내야 함
    //1. 위에서부터 n-1개 원반을 end를 거쳐 일단 to로 보냄 (맨아래 원반을 end로 보내야 하므로)
    //2. 맨아래 1개 원반을 end로 바로 보냄
    //3. 1에서 옮겼던 n-1개를 남은 기둥을 거쳐 end로 보냄
    void hanoi(int depth, int start, int to, int end) {
        if (depth == 0)
            return;
        hanoi(depth - 1, start, end, to);
        list.add(new int[] { start, end });
        hanoi(depth - 1, to, start, end);
    }
}
