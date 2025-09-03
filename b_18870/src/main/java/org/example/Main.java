package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int [] noDupSort;
    static int END;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [] original = new int[N];
        int [] sort = new int[N];
        noDupSort = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int value = Integer.parseInt(st.nextToken());
            original[i] = value;
            sort[i] = value;
        }

        Arrays.sort(sort);
        noDupSort[0]=sort[0];

        END = 0;
        for(int i=1; i<N; i++) {
            if(sort[i]>noDupSort[END]) {
                noDupSort[++END] = sort[i];
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i : original){
            sb.append(binarySearch(i)+" ");
        }

        System.out.println(sb.toString());

    }

    static int binarySearch(int value) {
        int start = 0;
        int end = END;

        while (end>=start) {
            int mid = (start+end)/2;

            if(noDupSort[mid]>value) {
                end=mid-1;
            } else if (noDupSort[mid]<value){
                start=mid+1;
            }
             else {
                 return mid;
            }
        }

        return 0;
    }

}