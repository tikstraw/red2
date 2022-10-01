package com.tikstraw.red2.demo.domain.hw;


import java.io.*;
        import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 *
 * If you define many classes, but you must have a class named Main and a public property.
 * The Main class should be the only public class.
 * The Main class must contain a static method (function) named "main"
 * Do not add any package, like "package main"
 *
 * The TestCase is shown below
 * Input : 1 2
 * Output : 3
 */

class Main {

    public static void main(String[] args) {
        //获取输入
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strNums = str.split(",");

        //数据预处理
        int n = 0;
        ListNode head = new ListNode(Integer.valueOf(strNums[0]));
        ListNode p = head;
        for (int i = 1; i < strNums.length; i++) {
            if (i==strNums.length-1) {
                n = Integer.valueOf(strNums[i]);
                break;
            }
            p.next = new ListNode(Integer.valueOf(strNums[i]));
            p = p.next;
        }




        ListNode res = removeNthFromEnd(head, n);
        //ListNode res = head;

        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }

    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        stack.add(p);
        int len = 1;
        while((p=p.next)!=null) {
            stack.add(p);
            len++;
        }
        if(n==0) return head;
        if (n>len ||len==1) {
            return null;
        } else if (len==n) {
            return head.next;
        }



        int i= 0;
        ListNode res = null;
        while(!stack.isEmpty()) {
            res = stack.pop();
            i++;
            if (i==n+1) {
                res.next = res.next.next;
            }
        }

        return res;
    }






}

class ListNode {

    int val;

    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

}

