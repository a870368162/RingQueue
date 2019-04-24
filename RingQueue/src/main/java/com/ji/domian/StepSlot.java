package com.ji.domian;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Title:插槽
 * Description:
 * Author:吉
 * Since:2017/3/17
 * Version:1.1.0
 */
public class StepSlot {
    private ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();

    ConcurrentLinkedQueue<Task> getTasks() {
        return tasks;
    }

    /**
     * 向槽内添加任务
     *
     * @param task 任务
     */
    int addTask(Task task) {
        tasks.add(task);
        return task.getIndex();
    }

    /**
     * 删除槽内某个taskId的任务
     *
     * @param taskId 任务id
     * @return 成功/失败
     */
    void reomve(int taskId) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == taskId) {
                it.remove();
            }
        }
    }
}
