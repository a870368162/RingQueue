package com.ji.structure;

import java.util.Calendar;

/**
 * Title:抽象任务
 * Description:
 * Author:吉
 * Since:2017/3/17
 * Version:1.1.0
 */
public abstract class AbstractTask implements Runnable {
    //圈数
    private int cycle;

    private int index;

    private int id;


    public AbstractTask(int id, int after) {
        int second = Calendar.getInstance().get(Calendar.MINUTE) * 60 + Calendar.getInstance().get(Calendar.SECOND);
        this.index = (second + after) % 3600;
        this.cycle = after / 3600;
        this.id = id;
    }

    public int getCycle() {
        return this.cycle;
    }

    public void countDown() {
        this.cycle -= 1;
    }

    public int getIndex() {
        return index;
    }

    public int getId() {
        return id;
    }
}
