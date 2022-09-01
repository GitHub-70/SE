package com.tansun.designmode.responsibility;

import java.util.Random;

/**
 * 组长经理处理请假类
 */
public class GroupManager extends HandlerEvent {

    /**
     * 查阅请假条
     * @param askForLeaveEvent
     */
    @Override
    protected void viewAskForLeaveEvent(AskForLeaveEvent askForLeaveEvent) {
        System.out.println("GroupManager查阅了:["+askForLeaveEvent.getName()+ "]请假["+askForLeaveEvent.getDayNumber()+"]天，请假原由：["+askForLeaveEvent.getContent()+"]");
    }

    /**
     * 各级领导判断自己是否有权限
     * @param canHandlerDay
     * @param nextHandler
     * @return
     */
    @Override
    protected boolean isOrNoPermission(int canHandlerDay, HandlerEvent nextHandler){
        // 如果当前天数小于等于三天，则有权限
        if (canHandlerDay <= DAY_THREE){
            return true;
        }
        // 否则递交给我的上级领导
        super.nextHandler = new GeneralManager();
        return false;
    }

    /**
     * 处理请假条
     * @param askForLeaveEvent
     */
    @Override
    protected void handlerAskForLeaveEvent(AskForLeaveEvent askForLeaveEvent) {
        System.out.println("GroupManager审批了:["+askForLeaveEvent.getName()+ "]请假["+askForLeaveEvent.getDayNumber()+"]天，请假原由：["+askForLeaveEvent.getContent()+"]");
        Random random = new Random(1);
        if (random.nextInt() > 0.5){

            System.out.println("GroupManager 处理结果：同意");
        } else {
            System.out.println("GroupManager 处理结果：不同意");

        }
    }
}
