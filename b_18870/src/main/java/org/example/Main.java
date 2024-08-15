package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Integer [] original;
    static Integer [] value;
    static Map<Integer, Integer> valueMappedNum;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        init();
        valueMappedNum = new HashMap<>();

        //1. 해당 배열 오름차순 정렬
        Arrays.sort(value);

        //2. HashMap에 초기화
        int cnt=0;
        for(int i=0; i<value.length; i++){
            if(valueMappedNum.containsKey(value[i])) {
                valueMappedNum.put(value[i], cnt++);
            }
        }

        for(int i=0; i<N; i++){
            sb.append(valueMappedNum.get(original[i])+" ");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ````````````````````````````````
        }
        System.out.print(sb);

    }

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        value = new Integer[N];
        original = new Integer[N];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<N; i++){
            original[i] = Integer.parseInt(st.nextToken());
            value[i] = original[i];
        }
    }
}