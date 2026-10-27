import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;
	static int cnt;
	static int len;
	static int ans;
	static Set<String>[] visit;
	
    //전략 : 각 cnt마다 set을 두고 가능한 경우의 수를 저장함
    //만약 특정 cnt에서 이미 내가 해본 숫자면 더이상 그 다음 가지를 볼 필요가 없으므로 컷
    //cnt 다 썼으면 현재 숫자가 최대인지 비교해서 갱신
    public static void main(String[] args) throws IOException {
    	int T = Integer.parseInt(br.readLine());
    	for(int test_case = 1; test_case <= T; test_case++)
    	{
    		StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	cnt = Integer.parseInt(st.nextToken());
        	len = new String(N+"").length();
        	visit = new HashSet[cnt+1];
        	for(int i = 0; i<=cnt; i++) 
        		visit[i] = new HashSet<>();
        	ans = 0;
        	dfs(cnt, new String(N+"").toCharArray());
        	bw.write("#"+test_case+" "+ans+"");
        	bw.newLine();
    	}
    	bw.flush();
    }
    
    static void dfs(int cnt, char[] num) {
    	String str = new String(num);
    	if (cnt == 0) {
    		ans = Math.max(ans, Integer.parseInt(str));
    		return;
    	}
        //이미 cnt일 때 내가 구했던 숫자면 더이상 볼 필요 없음 (같은 cnt에서 같은 숫자면 그 다음에 모든 경우의 수가 같아서 탐색할 필요 없음)
    	if (visit[cnt].contains(str))
    		return;
    	visit[cnt].add(str);
    	for(int i = 0; i<len; i++) {
    		for(int j = i + 1; j<len; j++) {
    			swap(num, i, j);
    			dfs(cnt - 1, num);
    			swap(num, i, j);
    		}
    	}
    }
    
    static void swap(char[] num, int i, int j) {
    	char tmp = num[i];
    	num[i] = num[j];
    	num[j] = tmp;
    }
}
