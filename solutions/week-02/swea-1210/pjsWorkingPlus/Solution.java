import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;
import java.io.FileInputStream;
 
class Solution
{
    static final int N=100;
    static int[][] map=null;
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws Exception
    {
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int T=Integer.parseInt(br.readLine());
            int answer=-1;
            int x=initMap();
 
            answer=solution(x);
 
            System.out.println("#"+test_case+" "+answer);
        }
    }
 
    static int solution(int x){
        int result=-1;
 
        result=dfs(x,99);
 
        return result;
    }
 
    static int[] rowD={1,-1,0};
    static int[] colD={0,0,-1};
 
    static int dfs(int x,int y){
        if(y==0){
            return x;
        }
        // 현재 위치를 벽으로 만들어 이후에 재방문 못하게 만듦
        map[y][x]=0;
        for(int i=0;i<3;i++){
            int nextX=x+rowD[i];
            int nextY=y+colD[i];
            if(!isRange(nextX,nextY))
                continue;
            if(map[nextY][nextX]==0)
                continue;
             
            return dfs(nextX,nextY);
        }
        return -1;
    }
    
    // 탐색 범위 충족 여부 확인 
    static boolean isRange(int nextX,int nextY){
        if(nextX>=0&&nextX<N&&nextY>=0&&nextY<N)
            return true;
 
        return false;
    }
 
    static int initMap() throws IOException {
        int dest=-1;
        map=new int[100][100];
        StringTokenizer st=null;
        for(int i=0;i<100;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<100;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(i==99&&map[i][j]==2){
                    dest=j;
                }
            }
        }
        //System.out.println(dest);
        return dest;
    }
}