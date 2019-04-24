package com.ji.domian;

import com.ji.structure.AbstractRingQueue;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Title:秒级轮询
 * Description:
 * Author:吉
 * Since:2017/3/17
 * Version:1.1.0
 */
public class Steper implements Runnable {

    private AbstractRingQueue rq;

    private ThreadGroup threadGroup = new ThreadGroup("TaskGroup");
    private ThreadGroup stepGroup = new ThreadGroup("stepGroup");
    private ExecutorService taskPool = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(threadGroup, r);
        }
    });
    private ExecutorService stepPool = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(stepGroup, r);
        }
    });

    public Steper(AbstractRingQueue rq) {
        this.rq = rq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int second = Calendar.getInstance().get(Calendar.MINUTE) * 60 + Calendar.getInstance().get(Calendar.SECOND);
                //获得对应slot
                StepSlot slot = rq.nextStep(second);
                System.out.println("steper 执行了" + second + "|slot大小" + slot.getTasks().size());
                final ConcurrentLinkedQueue<Task> tasks = slot.getTasks();
                stepPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Iterator<Task> it = tasks.iterator();
                        while (it.hasNext()) {
                            Task task = it.next();
                            if (task.getCycle() <= 0) {
                                taskPool.execute(task);
                                it.remove();
                            } else {
                                task.countDown();
                            }
                        }
                    }
                });
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
