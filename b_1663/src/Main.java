import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Info {
    long X, Y, Z;

    public Info(long X, long Y, long Z)
    {
        this.X=X;
        this.Y=Y;
        this.Z=Z;
    }

    public long findCnt(String target){
        if ("X".equals(target)) return X;
        return "Y".equals(target) ? Y : Z;
    }
}

public class Main {
    static int type;
    static int N;
    static long K = 0;
    static String target="";
    static Info [] XYZ;
    static String [] base = {"", "X", "YZ", "ZX"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        type = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        K = 0;
        target="";

        if (type == 2) {
            K = Long.parseLong(br.readLine().trim());
        } else if (type == 3) {
            target = br.readLine().trim();
        }

        XYZ = new Info[N+1];
        XYZ[1] = new Info(1,0,0); //X;
        if(N>=2)XYZ[2] = new Info(0,1,1); //YZ;
        if(N>=3)XYZ[3] = new Info(1,0,1); //XZ;


        int firstCnt = 1;
        int secondCnt = 2;
        for(int i=4; i<=N; i++) {
            Info first = XYZ[firstCnt++];
            Info second = XYZ[secondCnt++];
            XYZ[i] = new Info(first.X+second.X, first.Y+second.Y, first.Z+second.Z);
        }

        if(type==1) {
            System.out.println(XYZ[N].X+XYZ[N].Y+XYZ[N].Z);
        }else if(type==2) {
            System.out.println(findK(N, K));
        } else {
            System.out.println(XYZ[N].findCnt(target));
        }
    }

    //firstNum = 본인인덱스-3, secondNum = 본인인덱스-2
    static char findK (int nowIndex, long nowK) {
        if(nowIndex<=3) return base[nowIndex].charAt((int) nowK-1);

        long firstXYZLength = XYZ[nowIndex-3].X+XYZ[nowIndex-3].Y+XYZ[nowIndex-3].Z;
        if(nowK <= firstXYZLength) return findK(nowIndex-3, nowK);
        else return findK(nowIndex-2, nowK - firstXYZLength);
    }
}


/*
1. 문제요약

- 1단계 "XYZ 문자열"은 X로 시작한다.
- 다음 단계의 "XYZ 문자열"은 바로 이전 단계의 "XYZ 문자열"에서 아래와 같은 규칙에 따라 변형되어 만들어진다.
    -X는 YZ로 변형된다.
        -Y는 Z로 변형된다.
            -Z는 X로 변형된다.

[문제 유형]
1 -> N단계의 XYZ 문자열 길이
2 -> N단계의 XYZ에서 K번째 문자
3 -> N단계의 XYZ에서 특정 문자가 몇 번 나타나는가?

2. 입출력
[입력]
- 문제 번호(1,2,3)
- 자연수 N (1<=N<=100)
- 자연수 k(문제 2, k<=N) or x,y,z 셋 중 하나(문제 3)

[출력]
1 -> N단계의 XYZ 문자열 길이
2 -> N단계의 XYZ에서 K번째 문자
3 -> N단계의 XYZ에서 특정 문자가 몇 번 나타나는가?

[테스트케이스]

X : 1 (X=1, Y=0, Z=0)
YZ (X->YZ) : 2 (X=0, Y=1, Z=1)
ZX (Y->Z, Z->X) : 3 (X=1, Y=0, Z=1)
XYZ (Z->X, X->YZ) : 1+2 => 4
YZZX (X->YZ, Y->Z, Z->X) 2+3 => 5
ZXXYZ (Z->X, X->YZ, X->YZ, Y->Z, Z->X) 3+4 => 6
XYZYZZX (X->YZ, Y->Z, Z->X, Y->Z, Z->X, Z->X, X->YZ) => 4+5 => 7
YZZXZXXYZ

규칙1. 변환은 동시다발적으로 발생 가능.

2
7
4
* */

