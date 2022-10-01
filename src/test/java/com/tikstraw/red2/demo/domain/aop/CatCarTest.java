package com.tikstraw.red2.demo.domain.aop;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CatCarTest {

    @Test
    void getNow() {
        int[] a = {'0', 'A' - 10};
        String hexStr = "AB";
        int sum = 0;
        int n = 0;
        char c;
        for (int i = 0; i < hexStr.length(); i++) {
            c = hexStr.charAt(hexStr.length() - i - 1);
            if ('0' <= c && c <= '9') {
                n = c - a[0];
            } else {
                n = c - a[1];
            }

            sum += n << (i * 4);
        }

        System.out.println("sum = " + sum);

        Arrays.stream(a).forEach(s -> System.out.println("s = " + s));

    }

    @Test
    void testTowSum() {
        int[] res = towSum(new int[]{3, 2, 4}, 6);
        for (int re : res) {
            System.out.println("re = " + re);
        }
    }

    private int[] towSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int val = target - numbers[i];
            if (map.containsKey(val)) {
                return new int[]{map.get(val) + 1, i + 1};
            } else {
                map.put(numbers[i], i);
            }
        }
        throw new IllegalArgumentException("defeat");
    }


    @Test
    public void testMingRand() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,
                25,
                19,
                91,
                32,
                6,
                86,
                54,
                103,
                58,
                45,
                102
        );
        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> o1 - o2);
        treeSet.addAll(list);
        treeSet.forEach(val -> System.out.println(val));

    }


    @Test
    public void testHJ10() {
        String str = "ababc";

        ArrayList<Character> list = new ArrayList<>();
        Integer num = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!list.contains(c)) {
                list.add(c);
                num++;
            }
        }

        System.out.println("num = " + num);

    }

    @Test
    public void testJumpFloor() {
        int steps = getSteps(7);
        System.out.println("steps = " + steps);
    }

    public int getSteps(int a) {
        if (a == 1) return 1;
        if (a == 2) return 2;
        return getSteps(a - 1) + getSteps(a - 2);
    }

    @Test
    public void testMove() {
        String regex = "^([A|W|S|D])([1-9][0-9]?)$";
        Pattern pattern = Pattern.compile(regex);


        String in = "S87;S7;W56;S75;A8;S84;W23;W19;W40;D73;S87;A39;W97;W78;A53;D16;D15;A50;W41;S87;D47;W56;D56;A23;A91;S25;D61;D53;D58;W88;W58;S61;D69;W74;D89;A92;D39;D62;S78;W72;W73;W35;S76;W35;S36;W39;A4;";
        String[] ins = in.split(";");
        int[] xy = {0, 0};
        for (int i = 0; i < ins.length; i++) {
            Matcher m = pattern.matcher(ins[i]);
            if (m.find()) {
                move(xy, m.group(1).charAt(0), Integer.parseInt(m.group(2)));
            }
        }
        System.out.println(xy[0] + "," + xy[1]);
    }

    public void move(int[] xy, char act, int len) {
        if ('A' == act) {
            xy[0] -= len;
        } else if ('D' == act) {
            xy[0] += len;

        } else if ('W' == act) {
            xy[1] += len;

        } else if ('S' == act) {
            xy[1] -= len;
        }
    }

    @Test
    public void testPw() {
        String s = "ABABA123abc";
        System.out.println(pw(s));
    }

    public String pw(String s) {
        if (s.length() <= 8) {
            return "NG";
        }

        int[] typeFlag = {0, 0, 0, 0};
        int typeLevel = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {
                typeFlag[0] = 1;
            } else if ('a' <= c && c <= 'z') {
                typeFlag[1] = 1;
            } else if ('A' <= c && c <= 'Z') {
                typeFlag[2] = 1;
            } else if (' ' == c || c <= '\n') {
                return "NG";
            } else {
                typeFlag[3] = 1;
            }

            //检测重复
            if (i >= 3 && s.lastIndexOf(s.substring(i - 3, i)) > (i - 3)) {
                return "NG";
            }
        }

        for (int i = 0; i < typeFlag.length; i++) {
            typeLevel += typeFlag[i];
        }
        if (typeLevel < 3) {
            return "NG";
        }

        return "OK";
    }

    @Test
    public void testHJ23() {
        String str = "aabcddd";
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Integer min = Collections.min(map.values());

        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (min != map.get(c)) {
                builder.append(c);
            }
        }

        String res = builder.toString();
        System.out.println(res);

    }

    @Test
    public void testIp() {
        String str = "10.0.3.193";
        str = "167773121";
        str = "3868643487";

        if (str.indexOf(".") == -1) {
            String s = Long.toBinaryString(Long.valueOf(str));
            if (s.length() < 32) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 32 - s.length(); i++) {
                    builder.append('0');
                }
                builder.append(s);
                s = builder.toString();
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                String seg = s.substring(8 * i, 8 * i + 8);
                builder.append(Integer.parseInt(seg, 2));
                if (i < 3) {
                    builder.append(".");
                }
            }

            System.out.println(builder.toString());
            return;
        }


        String[] nums = str.split("\\.");
        StringBuilder builder = new StringBuilder();
        for (String num : nums) {
            String s = Integer.toBinaryString(Integer.valueOf(num));
            if (s.length() < 8) {
                for (int i = 0; i < 8 - s.length(); i++) {
                    builder.append('0');
                }
            }
            builder.append(s);
        }

        System.out.println(Long.parseLong(builder.toString(), 2));

    }

    @Test
    public void testA() {
        int n = 10;

        ArrayList<Character> badChars = new ArrayList<>(Arrays.asList('3', '4', '7'));
        ArrayList<Character> needChars = new ArrayList<>(Arrays.asList('2', '5', '6', '9'));
        int sum = 0;
        int state = 0;
        for (int i = 1; i <= n; i++) {
            state = 0;
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++) {
                if (badChars.contains(s.charAt(j))) {
                    state = 1;
                    break;
                }
                if (needChars.contains(s.charAt(j))) {
                    state = 2;
                }
            }

            if (state == 2) {
                sum++;
            }

        }
        System.out.println(sum);

    }

    @Test
    public void test1() {
        int[] args = new int[]{3, 3, 10};

        int n = args[0];
        int m = args[1];
        int k = args[2];
        int doubleN
                = n * 2;
        int res = 0;


        int sum = 3 * (1 << 3);
        if (k > sum) System.out.println("false");

        int pos = k % doubleN;

        if (pos == 0) {
            res = doubleN;
        } else if (pos <= n) {
            res = pos;
        } else {
            res = doubleN + 1 - pos;
        }

        System.out.println(res);
    }

    @Test
    public void test2() {
        int[] nums = new int[]{-6, 0, 2, -2, 3, 0};
        int len = nums.length;
        int step = 0;

        int dp_1 = Math.abs(nums[0] + 1);
        int dp1 = Math.abs(nums[0] - 1);
        int dp_7 = Math.abs(nums[0] + 7);
        int dp7 = Math.abs(nums[0] - 7);


        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            int newDp_1 = Math.min(dp_1 + Math.abs(v - 1), dp1 + Math.abs(v + 1));
            int newDp1 = Math.min(dp_1 + Math.abs(v + 1), dp1 + Math.abs(v - 1));
            int newDp_7 = Math.min(
                    Math.min(dp_1 + Math.abs(v - 7), dp1 + Math.abs(v + 7)),
                    Math.min(dp_7 + Math.abs(v - 1), dp7 + Math.abs(v + 1))
            );

            int newDp7 = Math.min(
                    Math.min(dp_1 + Math.abs(v + 7), dp1 + Math.abs(v - 7)),
                    Math.min(dp_7 + Math.abs(v + 1), dp7 + Math.abs(v - 1))
            );

            dp_1 = newDp_1;
            dp1 = newDp1;
            dp_7 = newDp_7;
            dp7 = newDp7;

        }

        System.out.println(dp7);
    }

    @Test
    public void test3() {
        int[] nums = new int[]{-6, 0, 2, -2, 3, 0};

        for (int i = 1; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length-i; j++) {
                if (nums[j]>nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }

        }

        Arrays.stream(nums).forEach(System.out::println);
    }


    @Test
    public void test4() {
        int[] nums = new int[]{-6, 0, 2, -2, 3, 0};




    }

    public void quick(int[] arr, int low, int high) {
        if (low<high) {
            int pos = part(arr, low, high);
            quick(arr,low, pos-1);
            quick(arr,pos+1, high);
        }
    }

    public int part(int[] arr, int low, int high) {
        int val = arr[high];
        int p = low;
        for (int i = low; i < high; i++) {
            if (arr[i]<val) {
                int tmp = arr[i];
                arr[i] = arr[p];
                arr[p] = tmp;
            }
        }


        return 0;
    }


}