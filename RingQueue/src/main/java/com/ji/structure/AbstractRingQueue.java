package com.ji.structure;

import com.ji.domian.StepSlot;
import com.ji.domian.Task;

/**
 * Title:抽象环形队列
 * Description:
 * Author:吉
 * Since:2017/3/17
 * Version:1.1.0
 */
public abstract class AbstractRingQueue {

    protected StepSlot[] slot = new StepSlot[3600];

    public AbstractRingQueue() {
        for (int i = 0; i < 3600; i++) {
            this.slot[i] = new StepSlot();
        }
        initData();
    }

    /**
     * 获取下个插槽
     *
     * @param slotIndex 插槽位置
     * @return
     */
    public abstract StepSlot nextStep(int slotIndex);

    /**
     * 添加任务
     *
     * @param task 任务
     * @return 所在slot的index
     */
    public abstract int add(Task task);

    /**
     * 删除任务
     *
     * @param taskId    任务id
     * @param slotIndex 插槽位置
     */
    public abstract void remove(int slotIndex, int taskId);

    /**
     * 替换task
     * @param slotIndex 槽位置
     * @param task 任务
     */
    public abstract void replaceSlot(int slotIndex, Task task);

    //初始化数据(根据业务)
    public abstract void initData();

}
