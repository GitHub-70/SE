package com.tansun.designmode.responsibility;

/**
 * 责任链模式测试
 *      其中 HandlerEvent 也采用了 模板方法加钩子方法的组合
 *      模板方法:
 *
 */
public class AskfForLeaveTest {

    public static void main(String[] args) {
        // 创建请假条实例
        AskForLeaveEvent askForLeaveEvent = new AskForLeaveEvent("张三", 3, "调休");

        /** 创建审批者实例 */
        GroupLeader groupLeader = new GroupLeader();

        // 小明向领导提交 请假条
        groupLeader.handlerAndSubmitEvent(askForLeaveEvent, groupLeader);
    }
}
