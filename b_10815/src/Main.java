import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int cartNum[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());
        cartNum = new int[count];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            cartNum[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cartNum);

        count = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            int target = Integer.parseInt(st.nextToken());
            System.out.print(findHave(target) + " ");
        }
    }

    public static int findHave(int target) {
        int start=0, mid=0;
        int end = cartNum.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (cartNum[mid] == target) {
                break;
            }
            if (cartNum[mid] < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        Boolean result = cartNum[mid] == target;
        return result.compareTo(false);
    }
}