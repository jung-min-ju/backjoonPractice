
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Character, List<Character>> tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        tree = new HashMap<>(N);

        for(int i=0; i<N; i++){
            String s = br.readLine();
            tree.put(s.charAt(0), new LinkedList<>()); // 노드가 이미 존재할 경우 새 리스트를 생성하는 것을 방지
            tree.get(s.charAt(0)).add(s.charAt(2)); // 왼쪽 자식 노드 추가
            tree.get(s.charAt(0)).add(s.charAt(4)); // 오른쪽 자식 노드 추가
        }

        System.out.println(preOrder('A'));
        sb.setLength(0);
        System.out.println(inOrder('A'));
        sb.setLength(0);
        System.out.println(postOrder('A'));

    }

    static private StringBuilder preOrder(Character now){
        if(now.equals('.')) return sb;
        List<Character> child = tree.get(now);
        sb.append(now);
        preOrder(child.get(0));
        preOrder(child.get(1));

        return sb;
    }


    static private StringBuilder inOrder(Character now){
        if(now.equals('.')) return sb;
        List<Character> child = tree.get(now);
        inOrder(child.get(0));
        sb.append(now);
        inOrder(child.get(1));

        return sb;
    }


    static private StringBuilder postOrder(Character now) {
        if(now.equals('.')) return sb;
        List<Character> child = tree.get(now);
        postOrder(child.get(0));
        postOrder(child.get(1));
        sb.append(now);

        return sb;
    }


}