import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int input[];
    static int arr[];
    static int inorder[];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        arr = new int[N];
        inorder = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //6
        //10 20 10 30 20 50

        Arrays.fill(arr, 1);
        Arrays.fill(inorder, -1);

        //1. 가장 긴 증가하는 부분 수열 길이 + 그 전 인덱스 기록
        int answer = 0;
        for(int i=0; i<N; i++) {
            answer = Math.max(answer, dp(i));
        }
        System.out.println(answer);

        //2. 가장 긴 증가하는 부분 수열의 끝 지점 찾고 + 출력
        int maxDex=0;
        for(int i=0; i<N; i++) {
            if(arr[i] > arr[maxDex] ) maxDex=i;
        }

        sb=new StringBuilder();
        print(maxDex);
        System.out.println(sb);
    }

    static int dp(int numDex) {
        if(arr[numDex]>0) return arr[numDex];

        for(int i=0; i<=numDex; i++) {
            int ex = dp(i)+1;
            if(input[i] < input[numDex] && ex > arr[numDex]) {
                inorder[numDex] = i; //그 전 순서 저장
                arr[numDex] = ex;
            }
        }
        return arr[numDex];
    }

    static void print(int numDex){
        if(numDex==-1) return;

        print(inorder[numDex]);
        sb.append(input[numDex]+" ");

    }
}

//0 1 2
//0 0 1

