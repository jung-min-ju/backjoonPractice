import java.io.IOException;

public class Output {
    public static void printPath(){
        System.out.print("출발점, 목적지, 경유지의 수 : ");
    }

    public static void printPath(int minDistance, int start, int end){
        if(minDistance==-1){
            System.out.println(start+"에서 "+end+"까지 경로가 존재하지 않습니다.");
            return;
        }
        System.out.println("경로의 거리 = " + minDistance);
    }

}
