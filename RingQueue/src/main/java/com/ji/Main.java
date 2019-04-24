package com.ji;

import com.ji.domian.RingQueue;
import com.ji.domian.Task;

/**
 * Title:
 * Description:
 * Author:吉
 * Since:2017/3/17
 * Version:1.1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        RingQueue rq = new RingQueue();
        rq.start();
        add(rq);
        Thread.sleep(6000L);
        remove(rq);
        Thread.sleep(3000L);
        updtae(rq);
    }


    static void add(RingQueue rq) {
        //任务id是1,5s后执行
        Task t = new Task(1, 5) {
            @Override
            public void run() {
                System.out.println("i come from the future");
            }
        };
        System.out.println("插槽位置：" + rq.add(t));
    }

    static void remove(RingQueue rq) {
        //任务id是1,2s后执行
        Task t = new Task(2, 2) {
            @Override
            public void run() {
                System.out.println("i come from the future");
            }
        };
        rq.add(t);
        rq.remove(t.getIndex(), t.getId());
    }


    static void updtae(RingQueue rq) {
        //任务id是1,2s后执行
        Task t = new Task(3, 3) {
            @Override
            public void run() {
                System.out.println("i come from the future");
            }
        };
        rq.add(t);
        //任务id是1,2s后执行
        Task tupdate = new Task(3, 5) {
            @Override
            public void run() {
                System.out.println("i come from the future for replace");
            }
        };
        rq.replaceSlot(t.getIndex(), tupdate);
    }
}
