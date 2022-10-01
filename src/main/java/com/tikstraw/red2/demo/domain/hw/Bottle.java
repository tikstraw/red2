package com.tikstraw.red2.demo.domain.hw;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.bloomfilter.BloomFilterUtil;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Bottle {

    public static void main(String[] args) throws IOException {
        //int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        //int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
        //int[][] grid = {{1,2}};
        //int i = orangesRotting(grid);
        //System.out.println(i);
        //bloom();
        canBeTypedWords("hello world", "ad");
    }

    public static void test1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        ArrayList<Integer> list = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            if ("0".equals(str)) break;
            list.add(Integer.valueOf(str));
        }
        System.out.println(list);

        list.forEach(val -> System.out.println(val / 2));
    }

    public static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        Integer n = Integer.valueOf(br.readLine());
        while ((str = br.readLine()) != null) {
            if (str.isEmpty()) break;
            set.add(Integer.valueOf(str));
        }

        list.addAll(set);
        Collections.sort(list, ((o1, o2) -> o1 - o2));

        list.forEach(System.out::println);


    }

    public static void test3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        long res = Long.parseLong(str.substring(2), 16);
        System.out.println(res);


    }

    public static int orangesRotting(int[][] grid) {

        int min = -1;
        int good = 0;
        Stack<int[]> stack = new Stack<>();
        while (++min > -1) {
            good = 0;
            for (int i = 0; i < grid[0].length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[j][i] == 1) {
                        good++;
                    } else if (grid[j][i] == 2) {
                        toBadAround(i, j, grid, stack);
                    }
                }
            }

            //没有新的
            if (good < 1) {
                break;
            } else if (stack.isEmpty()) {
                //有新的但是 传染不到
                return -1;
            }

            while (!stack.isEmpty()) {
                int[] pop = stack.pop();
                grid[pop[1]][pop[0]] = 2;
            }
        }

        return min;
    }

    public static void toBadAround(int i, int j, int[][] grid, Stack<int[]> stack) {
        getToBadPos(i - 1, j, grid, stack);
        getToBadPos(i, j - 1, grid, stack);
        getToBadPos(i + 1, j, grid, stack);
        getToBadPos(i, j + 1, grid, stack);
    }

    public static void getToBadPos(int i, int j, int[][] grid, Stack<int[]> stack) {
        if (isToBad(i, j, grid)) {
            stack.push(new int[]{i, j});
        }
    }

    public static boolean isToBad(int i, int j, int[][] grid) {
        boolean ok = 0 <= i && i < grid[0].length
                && 0 <= j && j < grid.length;
        if (ok && grid[j][i] == 1) {
            return true;
        }
        return false;
    }

    public static void bloom() {
        int n = 100;
        BitMapBloomFilter bitMap = BloomFilterUtil.createBitMap(n);
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), n, 0.00001);
        for (int i = 0; i < n - 10; i++) {
            bitMap.add(String.valueOf(i));
            bloomFilter.put(i);
        }

        System.out.println(bitMap.contains("10"));
        System.out.println(bloomFilter.mightContain(10));

    }

    public static int canBeTypedWords(String text, String brokenLetters) {
        int state = 0;
        int sum = 0;
        int len = text.length();
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (state==0 && brokenLetters.contains(String.valueOf(c))) {
                state = 1;
            }
            if (' '==c || i == len - 1) {
                if (state == 0) {
                    sum++;
                } else {
                    state = 0;
                }
            }
        }

        System.out.println(sum);
        return sum;
    }


}
