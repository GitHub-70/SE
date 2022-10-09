package com.tansun.designmode.adapter.service.player;

import com.tansun.designmode.adapter.service.TVUPlayer;

/**
 * @ClassName CCTVPlayer
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:58
 * @Copyright © 2020 阿里巴巴
 */
public class CCTVPlayer implements TVUPlayer {
    @Override
    public void CCTVPlayer() {
        System.out.println("Playing CCTV Channel");
    }
}
