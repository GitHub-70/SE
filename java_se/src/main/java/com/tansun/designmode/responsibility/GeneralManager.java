package com.tansun.designmode.responsibility;

import java.util.Random;

/**
 * 总经理处理请假类
 */
public class GeneralManager extends HandlerEvent {

    /**
     * 查阅请假条
     * @param askForLeaveEvent
     */
    @Override
    protected void viewAskForLeaveEvent(AskForLeaveEvent askForLeaveEvent) {
        System.out.println("GeneralManager查阅了:["+askForLeaveEvent.getName()+ "]请假["+askForLeaveEvent.getDayNumber()+"]天，请假原由：["+askForLeaveEvent.getContent()+"]");
    }

    /**
     * 各级领导判断自己是否有权限
     * @param canHandlerDay
     * @param nextHandler
     * @return
     */
    @Override
    protected boolean isOrNoPermission(int canHandlerDay, HandlerEvent nextHandler){
        if (canHandlerDay <= DAY_SEVEN){
            return true;
        }
        return false;
    }

    /**
     * 处理请假条
     * @param askForLeaveEvent
     */
    @Override
    protected void handlerAskForLeaveEvent(AskForLeaveEvent askForLeaveEvent) {
        System.out.println("GeneralManager审批了:["+askForLeaveEvent.getName()+ "]请假["+askForLeaveEvent.getDayNumber()+"]天，请假原由：["+askForLeaveEvent.getContent()+"]");
        Random random = new Random(1);
        if (random.nextInt() > 0.5){

            System.out.println("GeneralManager 处理结果：同意");
        } else {
            System.out.println("GeneralManager 处理结果：不同意");

        }
    }
}
