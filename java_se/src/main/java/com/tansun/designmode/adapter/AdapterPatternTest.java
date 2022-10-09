package com.tansun.designmode.adapter;

import com.tansun.designmode.adapter.service.player.AudioPlayer;

/**
 * @ClassName AdapterPatternDemo
 * @Description 音频播放器 通过适配器 播放媒体类文件
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 16:06
 * @Copyright © 2020 阿里巴巴
 */
public class AdapterPatternTest {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("cctv", "far far away.cctv");
        audioPlayer.play("avi", "mind me.avi");
    }
}
