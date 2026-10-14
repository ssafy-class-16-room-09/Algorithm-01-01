import java.util.*;
import java.io.*;

// 가중치 존재 최단 거리 계산은 다익스트라를 보통 사용하지만
// 해당 문제에서는 x가중치 합과 y 가중치 합 각각의 최소값이 경로 가중치 결과값의 최저값을 보장하지 않음
// 다만 음수 가중치는 없기 때문에 먼저 도착하는 경로가 더 적은 경로 가중치 합을 가짐
// dfs로 완탐하여 해당 경로가 가지는 x,y 가중치합 간의 곱 최소치 발견

class Solution
{
    static List<Edge>[] graph=null;
    static int[] visit;
    static int V;
    static int E;
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws Exception
    {
        int T;
        T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int answer=-1;
            init();
            answer=solve();

            System.out.println("#"+test_case+" "+answer);
        }

    }
    static void init() throws IOException{
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        int node1=-1, node2=-1, weightX=-1,weightY=-1;

        graph=new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=1;i<=E;i++){
            st=new StringTokenizer(br.readLine()," ");
            node1=Integer.parseInt(st.nextToken());
            node2=Integer.parseInt(st.nextToken());
            weightX=Integer.parseInt(st.nextToken());
            weightY=Integer.parseInt(st.nextToken());

            graph[node1].add(new Edge(node2,weightX,weightY));
            graph[node2].add(new Edge(node1,weightX,weightY));
        }
//        printG();
    }

    static void printG(){

        for(int i=1;i<=E;i++){
            System.out.println("Start Node1");
            for(Edge temp:graph[i]){
                System.out.println("to "+temp.toNode+" weightX "+temp.weightX+" weightY "+temp.weightY);
            }
        }
    }

    static int solve(){
        visit=new int[V+1];
        min=Integer.MAX_VALUE;
        dfs(1,0,0);

        if(min==Integer.MAX_VALUE){
            return -1;
        }
        else{
            return min;
        }
    }
    static int min;

    static void dfs(int curNode,int sumX,int sumY){

        // 2번노드 도착시
        if(curNode==2){
            int temp=sumX*sumY;
            min=Math.min(min,temp);

            return;
        }

        // 프루닝
        if(min<sumX*sumY){
            return;
        }

        //현재 노드에서 연결된 간선 순회+백트래킹
        for(Edge temp:graph[curNode]){
            //이미 방문한 곳은 넘어감
            if(visit[temp.toNode]==1)
                continue;
            visit[temp.toNode]=1;
            dfs(temp.toNode,sumX+ temp.weightX,sumY+ temp.weightY);
            visit[temp.toNode]=0;
        }
    }
}
class Edge{
    int toNode;
    int weightX;
    int weightY;
    public Edge(int nodeNum,int x,int y){
        this.toNode=nodeNum;
        this.weightX=x;
        this.weightY=y;
    }
}

