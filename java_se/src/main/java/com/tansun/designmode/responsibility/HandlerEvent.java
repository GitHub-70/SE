package com.tansun.designmode.responsibility;

/**
 * 处理事件抽象类
 */
public abstract class HandlerEvent {

    /** 请假天数临界值 */
    protected static final Integer DAY_ONE = 1;
    protected static final Integer DAY_THREE = 3;
    protected static final Integer DAY_SEVEN = 7;

    // 可以处理的请假天数
    private Integer canHandlerDay;

    // 下一个处理者（此设计的思想是：为了避免请求发起者，与多个请求处理者耦合在一起，
    // 这样设计只需与第一个请求处理者发生耦合）
    protected HandlerEvent nextHandler;


    /**
     * 父类中显式的提供了默认的构造方法（给子类去实现），子类也必须显式的提供默认的构造方法
     * @param canHandlerDay 该用户能够审批的天数
     * @param nextHandler 设置下一个处理者
     */
//    public HandlerEvent(Integer canHandlerDay, HandlerEvent nextHandler) {
//        this.canHandlerDay = canHandlerDay;
//        this.nextHandler = nextHandler;
//    }



    /**
     * 各级领导处理请假条的抽象方法
     * @param askForLeaveEvent
     */
    protected abstract void viewAskForLeaveEvent (AskForLeaveEvent askForLeaveEvent);


    /**
     * 各级领导判断自己是否有权限，作为钩子方法，可让子类去实现
     * @param canHandlerDay
     * @param nextHandler
     * @return
     */
    protected boolean isOrNoPermission(int canHandlerDay, HandlerEvent nextHandler){
        // 默认给处理一天的权限
        if (canHandlerDay <= DAY_ONE){
            return true;
        }
        return false;
    };

    /**
     * 各级领导处理请假条的抽象方法
     * @param askForLeaveEvent
     */
    protected abstract void handlerAskForLeaveEvent (AskForLeaveEvent askForLeaveEvent);



    /**
     * 提交请假条。例如，张三要请假，那么他得进行一个提交，即将请假条提交给他的小组长
     *            若处理不了，他就把张三的请假条，在提交给他的上级领导了，以此类推。。
     *
     * 注意了，该方法我们要声明为final的，这是因为要求子类不能去重写该方法
     *
     */
    public final void handlerAndSubmitEvent(AskForLeaveEvent askForLeaveEvent, HandlerEvent handlerEvent){

        // 该领导进行查阅
        handlerEvent.viewAskForLeaveEvent(askForLeaveEvent);

        // 审批完后，进行判断是否有上级领导，同时请假天数是否超过当前领导可以审批的权限（天数）
        if(!handlerEvent.isOrNoPermission(askForLeaveEvent.getDayNumber(), nextHandler)){

            // 移交下一位领导审批
            handlerEvent = nextHandler;
            nextHandler.handlerAndSubmitEvent(askForLeaveEvent, handlerEvent);
        } else {

            // 该领导进行审批
            handlerEvent.handlerAskForLeaveEvent(askForLeaveEvent);
            // 流程结束
            System.out.println(">>>审批流程结束");
        }


    }



}
