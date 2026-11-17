import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] points = new int[n];
        int[] subSum = new int[1000001];
        Set<Integer> set = new HashSet<>();
        int x=-1;

        // 주어진 점들
        for (int i = 0; i < n; i++) {
            x =sc.nextInt();
            points[i] = x;
            set.add(x);
        }

        Arrays.sort(points);
        
        // 기저 조건
        // 1번째까지 부분 합은 숫자 0이 주어진 점인지에 따라 다름
        if(set.contains(0))
            subSum[0]=1;


        // 1~N까지, subSum[n]에 담긴 값은 0~n까지 수 중에 주어진 점의 갯수 합
        for(int i = 1 ; i < 1000001; i++){
            subSum[i] = subSum[i-1];
            if(set.contains(i))
                subSum[i]++;
            
        }

        // 주어진 범위들 마다 점의 갯수 구하기
        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // a < b
            // 범위가 a~b 일때, 사이에 속한 점의 수는 
            // (0~b까지 속한 점의 갯수) - (0~a까지 속한 점의 갯수)
            // 범위가 점으로 주어질 경우
            if(b == a && set.contains(a)){
                System.out.println(1);    
            }
            // 범위의 출발이 0 일경우
            else if(a == 0){
                System.out.println(subSum[b]);
            }
            else
                System.out.println(subSum[b]-subSum[a-1]);
        }
    }
}

// 슬라이딩 윈도우로 풀려고 했던 흔적

// import java.util.*;

// public class Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int q = sc.nextInt();
//         int[] points = new int[n];
        
//         Set<Integer> set =new HashSet<>();
//         int x = -1;
//         // 주어진 점들
//         for (int i = 0; i < n; i++) {
//             x =sc.nextInt();
//             points[i] = x;
//             set.add(x);
//         }
        
//         // 더 앞선 범위, 더 짧은 범위
//         PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
//             if(a[0]!=b[0])
//                 return Integer.compare(a[0],b[0]);
//             else
//                 return Integer.compare(a[1],b[1]);
//         });

        
//         // 범위를 pq에 담기
//         for (int i = 0; i < q; i++) {
//             int a = sc.nextInt();
//             int b = sc.nextInt();
//             pq.offer(new int[]{a,b});
//             // Please write your code here.
//         }
        
//         ArrayDeque<Integer> dq = new ArrayDeque<>();
        
//         int subSum = 0;
//         int[] tempRange = null;
//         int idx = 0;
//         int answer = 0;
//         dq.offer(-1);

//         // 범위를 모두 뽑기
//         while(!pq.isEmpty()){
//             tempRange = pq.poll();
//             // System.out.println(tempRange[0]+" "+tempRange[1]);
//             while(idx > tempRange[1]){
//                 if(set.contains(dq.peekLast())){
//                     answer--;
//                 }
//                 dq.removeLast();
//                 idx--;
//             }

//             // // 슬라이딩 윈도우의 tail을 뽑은 범위의 right까지 옮기기
//             while(idx<=tempRange[1]){
//                 if(set.contains(idx)){   
//                     answer ++;
//                 }
//                 dq.offer(idx++);
//             }

//             // // 슬라이딩 윈동우의 head를 뽑은 범위의 left까지 옮기기
//             while(dq.peekFirst() < tempRange[0]){
//                 if(set.contains(dq.peekFirst())){
//                     answer--;
//                 }
//                 dq.poll();
//             }




//             // System.out.println(dq);

//             System.out.println(answer);
//         }
        

//     }
// }
