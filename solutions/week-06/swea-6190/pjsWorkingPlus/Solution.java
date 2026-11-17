import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            // n개 숫자

            for(int n = 0; n < N ; n++){
            	arr[n] = Integer.parseInt(st.nextToken());
            }

            int num = -1;
            int max = -1;
            // n개 숫자를 Ai * Ai+1 숫자 곱
			for(int n = 0 ; n < N-1 ; n++){
                for(int z = n+1 ; z< N ; z++){

                    num = arr[n] * arr[z];
                    String s = Integer.toString(num);

                    int[] numArr = new int[s.length()];
                    // 숫자곱 문장열 -> 수배열 변환
                    for (int i = 0; i < s.length(); i++) {
                        numArr[i] = s.charAt(i) - '0';
                    }

                    // 단조 수열 여부 확인
                    boolean isIncremental = true;
                    for(int j = 0; j<s.length() -1 ; j++){
                        // 단조 증가를 위반할 경우
                        if(numArr[j]>numArr[j+1]){
                            isIncremental=false;
                            break;
                        }

                    }
				
                    
                    // 단조 증가 수열 이면서, 기존 max 값보다 큰지?
                    if(isIncremental){
                        max = Math.max(max, num);
                    }

                }
            	
            } 
            
            System.out.println("#"+test_case+" "+max);
		}
	}
}