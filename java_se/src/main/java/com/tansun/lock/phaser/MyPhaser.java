package com.tansun.lock.phaser;

import java.util.concurrent.Phaser;

/**
 * 常用于大任务拆成小任务
 *   小任务间的依赖
 */
public class MyPhaser extends Phaser {

    /**
     * 重新父类的 onAdvance方法
     *
     * @param phase             第几个阶段
     * @param registeredParties 每个阶段要注册完成的人数
     * @return
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) { //在每个阶段执行完成后回调的方法
        switch (phase) {
            case 0:
                System.out.println("人都到齐咯,到齐人数：" + getRegisteredParties() + "，开始点菜：");
                return false;
            case 1:
                System.out.println("饮料点完咯！点完饮料人数：" + getRegisteredParties() + "，开始点主菜：");
                return false;
            case 2:
                System.out.println("主菜点完了！点完主菜人数：" + getRegisteredParties() + "，开始吃饭：");
                return false;
            case 3:
                System.out.println("吃完饭了！吃完人数：" + getRegisteredParties() + "，接下来游玩，或回家");
                return false;
            case 4:
                System.out.println("到达游玩地点！到达人数:" + getRegisteredParties() + "，接下来游玩");
                return false;
            default:
                return true;
        }

    }


}
