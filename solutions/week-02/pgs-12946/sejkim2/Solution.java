import java.util.*;

class Solution {
    ArrayList<int[]> list = new ArrayList<>();
    int N;

    public ArrayList<int[]> solution(int n) {
        N = n;
        hanoi(N, 1, 2, 3);
        return list;
    }

    void hanoi(int depth, int start, int to, int end) {
        if (depth == 0)
            return;
        hanoi(depth - 1, start, end, to);
        list.add(new int[] { start, end });
        hanoi(depth - 1, to, start, end);
    }
}