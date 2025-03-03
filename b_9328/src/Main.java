import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;


public class Main {

    static class Info {
        int x;
        int y;

        public Info (int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static int [] dirX = {0,0,1,-1};
    static int [] dirY = {-1,1,0,0};
    static String building [][];
    static Set<String> key;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            building = new String[h+2][w+2];
            key = new HashSet<>();

            for(int j=1; j<=h; j++) {
                String s = br.readLine();
                for(int k=1; k<=w; k++){
                    building[j][k]=Character.toString(s.charAt(k-1));
                }
            }

            String s = br.readLine();
            if(s!="0") {
                for(int j=0; j<s.length(); j++) {
                    char c = Character.toUpperCase(s.charAt(j));
                    String upperCase = Character.toString(c);
                    key.add(upperCase);
                }
            }
            System.out.println(steal(h,w));
        }

    }

    static int steal(int h, int w) {
        Queue<Info> queue = new ArrayDeque<>();
        int maxStealing = 0;

        for(int i=1; i<=w; i++) {
            if(building[1][i].equals(".")||key.contains(building[1][i])) queue.add(new Info(1,i));
            if(building[h][i].equals(".")||key.contains(building[h][i])) queue.add(new Info(h,i));
        }

        for(int i=2; i<=h-1; i++) {
            if(building[i][1].equals(".")||key.contains(building[i][1])) queue.add(new Info(i,1));
            if(building[i][w].equals(".")||key.contains(building[i][w])) queue.add(new Info(i,w));
        }

        int startCount = queue.size();

        //2. key 찾기
        for(int i=0; i<startCount; i++) {
            Info info = queue.poll();
            findKey(info.x, info.y, "..");
        }

        for(int i=1; i<=w; i++) {
            if(building[1][i].equals("..")||key.contains(building[1][i])) queue.add(new Info(1,i));
            if(building[h][i].equals("..")||key.contains(building[h][i])) queue.add(new Info(h,i));
        }

        for(int i=2; i<=h-1; i++) {
            if(building[i][1].equals("..")||key.contains(building[i][1])) queue.add(new Info(i,1));
            if(building[i][w].equals("..")||key.contains(building[i][w])) queue.add(new Info(i,w));
        }

        System.out.println("출발점개수 : "+queue.size());

        //3. 2에서 찾은 key로 문 열며 최대 문서의 개수 찾기
        for(int i=0; i<startCount; i++) {
            Info info = queue.poll();
            maxStealing +=findMaxStealing(info.x, info.y,0);
        }

        return maxStealing;
    }

    static void findKey(int x, int y, String s) {
        building[x][y]=s;

        for(int i=0; i<4; i++) {
            int nextX = x+dirX[i];
            int nextY = y+dirY[i];
            if(building[nextX][nextY]==null) continue;
            char nextC = building[nextX][nextY].charAt(0);

            if(building[nextX][nextY].equals("$")) {
                findKey(x+dirX[i], y+dirY[i],"$$");
            }
            else if(building[nextX][nextY].equals(".")) {
                findKey(x+dirX[i], y+dirY[i],"..");
            }
            else if(key.contains(building[nextX][nextY])) {
                findKey(x+dirX[i], y+dirY[i],"..");
            }
            else if(97<=nextC&&nextC<=122) {
                char c = Character.toUpperCase(nextC);
                String upperCase = Character.toString(c);
                key.add(upperCase);
                findKey(x+dirX[i], y+dirY[i],"..");
            }
        }
    }

    static int findMaxStealing(int x, int y, int nowStealing) {
        building[x][y]="-1";

        for(int i=0; i<4; i++) {
            int nextX = x+dirX[i];
            int nextY = y+dirY[i];
            if(building[nextX][nextY]==null||building[nextX][nextY].equals("-1")) continue;
            if(building[nextX][nextY].equals("$$")||building[nextX][nextY].equals("$")) {
                nowStealing = findMaxStealing(x+dirX[i], y+dirY[i], nowStealing+1);
            }
            else if(building[nextX][nextY].equals("..")) nowStealing = findMaxStealing(x+dirX[i], y+dirY[i], nowStealing);
            else if(key.contains(building[nextX][nextY])) nowStealing = findMaxStealing(x+dirX[i], y+dirY[i],nowStealing);
        }

        return nowStealing;
    }
}