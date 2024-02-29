import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException { //J = BNP, S = TIMING;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] stock;
        int JCash = 0, SCash = 0, JStock = 0, SStock = 0, count = 0;
        Boolean []check = new Boolean[2];

        JCash = SCash = Integer.parseInt(br.readLine());
        String s = br.readLine();
        stock = s.split("\\s+");

        //준현이가 첫 날에 매수할 가능성
        if (Integer.parseInt(stock[0]) <= JCash) {
            JStock = JCash / (Integer.parseInt(stock[0]));
            JCash %= (Integer.parseInt(stock[0]));
        }

        //check 불리언 배열 초기화
        for(int i=0; i<check.length; i++){
            check[i] = true;
        }

        for (int i = 1; i < stock.length; i++) {
            int currentStock = Integer.parseInt(stock[i]);
            int exStock = Integer.parseInt(stock[i-1]);
            //J의 BNP 계산
            if (currentStock <= JCash) {
                int newJStock = JCash/ currentStock;
                JStock += newJStock;
                JCash -= newJStock*currentStock;
            }

            //S의 TIMING 계산
            if(exStock>currentStock) {
                if(check[0].equals(false)) count=-1; //매수 조건 = -3
                if(check[0].equals(true)) count--;
                check[0] = true;
                check[1] = false;
            }
            else if(exStock<currentStock) {
                if(check[1].equals(false)) count=1; //매도 조건 = +3
                if(check[1].equals(true)) count++;
                check[0] = false;
                check[1] = true;
            }
            else {
                check[0] = check[1] = true;
                count = 0;
                continue;
            }

            if(count<=-3&&SCash>0){
                int newSStock = SCash/ currentStock;
                SStock += newSStock;
                SCash -= newSStock*currentStock;

            }
            if(count>=3&&SStock>0) {
                SCash += SStock*currentStock;
                SStock =0;
            }
        }
        JCash += JStock * Integer.parseInt(stock[stock.length-1]);
        JStock = 0;
        SCash += SStock*Integer.parseInt(stock[stock.length-1]);

        if (JCash==SCash) System.out.println("SAMESAME");
        else if(JCash>SCash) System.out.println("BNP");
        else System.out.println("TIMING");
    }
}