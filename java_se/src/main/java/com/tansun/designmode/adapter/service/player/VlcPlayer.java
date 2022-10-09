package com.tansun.designmode.adapter.service.player;

import com.tansun.designmode.adapter.service.AdvancedMediaPlayer;

/**
 * @ClassName VlcPlayer
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:40
 * @Copyright © 2020 阿里巴巴
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
