package com.tansun.designmode.templatemethod;

/**
 * 通常的模板方法模式中会设计一个abstract的抽象方法，交给它的子类实现，这个方法称为模板方法。
 * 而钩子方法，是对于抽象方法或者接口中定义的方法的一个空实现，也是模板方法模式的一种实现方式。
 */
public abstract class AbastractCourse {

    public final void createCourse(){
        //1.发布预习资料
        postPreResoucse();

        //2.制作课件PPT
        createPPT();

        //3.在线直播
        liveVideo();

        //4.上传课后资料
        postResource();

        //5.布置作业
        postHomework();

        if(needCheckHomework()){
            checkHomework();
        }
    }


    protected abstract void checkHomework();

    //钩子方法
    protected boolean needCheckHomework(){return true;}

    protected void postHomework(){
        System.out.println("布置作业");
    }

    protected void postResource(){
        System.out.println("上传课后资料");
    }

    protected void liveVideo(){
        System.out.println("直播授课");
    }

    protected void createPPT(){
        System.out.println("制作课件");
    }

    protected void postPreResoucse(){
        System.out.println("发布预习资料");
    }

}