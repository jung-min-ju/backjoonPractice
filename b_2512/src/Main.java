import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M, maxRegionAssets=0, sumAssets=0;
    static int [] regionAssets;
    static boolean stop;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        regionAssets = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            regionAssets[i] = Integer.parseInt(st.nextToken());
            maxRegionAssets = Math.max(regionAssets[i], maxRegionAssets);
            sumAssets+=regionAssets[i];
        }

        M = Integer.parseInt(br.readLine());
        if(sumAssets<= M) {
            System.out.println(maxRegionAssets);
        }
        else{
            int max = maxRegionAssets;
            maxRegionAssets=0;
            binarySearch(0, max);
            System.out.println(maxRegionAssets);
        }
    }

    static public void binarySearch(int min, int max) {
        if (stop || min > max) return;
        int ave=(min+max)/2;
        if(calculateAssets(ave)) { //예산 범위 안인 경우
            binarySearch(ave+1, max);
        } else{ //예산 범위 벗어날 경우
            binarySearch(min, ave-1);
        }
    }

    static public boolean calculateAssets(int upperAsset) {
        int sum = 0;
        for(int asset : regionAssets){
            if(asset>upperAsset){
                sum+=upperAsset;
                continue;
            }
            sum+=asset;
        }

        if(sum>M) return false;
        if(upperAsset<maxRegionAssets) stop=true;
        else maxRegionAssets=upperAsset;
        return true;
    }
}