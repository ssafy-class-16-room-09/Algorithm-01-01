import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int cntMinus = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] < 0)
                cntMinus++;
        } 
        
        Arrays.sort(arr);

        //정렬했으니 오른쪽에서 3개 곱한게 최대일 수 있음 (음수가 1개 이하라면)
        int max = (arr[n-1] * arr[n-2] * arr[n-3]);
        
        //만약 음수가 2개 이상이면 음수 2개 곱해서 양수 되는 경우 값이 클수도 있으므로 고려해야 함
        if (cntMinus >= 2)
            max = Math.max((arr[0] * arr[1] * arr[n-1]),(arr[n-1] * arr[n-2] * arr[n-3]));
        System.out.println(max);
    }
}