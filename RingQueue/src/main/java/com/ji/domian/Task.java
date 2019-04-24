package com.ji.domian;

import com.ji.structure.AbstractTask;

/**
 * Title:任务体
 * Description:
 * Author:吉
 * Since:2017/3/17
 * Version:1.1.0
 */
public abstract class Task extends AbstractTask {

    public Task(int id, int after) {
        super(id, after);
    }
}
