package com.tansun.designmode.responsibility;

/**
 * 请假事件类
 */
public class AskForLeaveEvent {

    // 请假人姓名
    private String name;
    // 请假天数
    private Integer dayNumber;
    // 请假原由
    private String content;

    public AskForLeaveEvent(String name, Integer dayNumber, String content) {
        this.name = name;
        this.dayNumber = dayNumber;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
