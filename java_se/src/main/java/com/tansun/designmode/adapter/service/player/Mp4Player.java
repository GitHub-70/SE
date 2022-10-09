package com.tansun.designmode.adapter.service.player;

import com.tansun.designmode.adapter.service.AdvancedMediaPlayer;

/**
 * @ClassName Mp4Player
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:41
 * @Copyright © 2020 阿里巴巴
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing Mp4 file. Name: "+ fileName);
    }
}
