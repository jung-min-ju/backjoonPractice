import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] input = br.readLine().split("");
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> priority = new HashMap<>();
        priority.put('(',0);
        priority.put(')',0);
        priority.put('*',1);
        priority.put('/',1);
        priority.put('+',2);
        priority.put('-',2);

        for(int i=0; i<input.length; i++) {
            char now = input[i].charAt(0);

            if(now=='(') {
                stack.add(now);
                continue;
            }
            if(now==')') {
                while (!stack.isEmpty()) {
                    char pop = stack.pop();
                    if (pop == '(') {
                        break;
                    }
                    System.out.print(pop);
                }

                continue;
            }

            if('A'<=now && now<='Z') {
                System.out.print(now);
                continue;
            }

            if(!stack.isEmpty()) {
                int nowStackTop = priority.get(stack.peek());
                int nowValue = priority.get(now);

                while (nowStackTop<=nowValue&&!stack.isEmpty()) {
                    if(stack.peek().equals('(')) break;
                    System.out.print(stack.pop());
                    if(!stack.isEmpty()) nowStackTop = priority.get(stack.peek());
                }
            }
            stack.add(now);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }

    }

}
