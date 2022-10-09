package com.tansun.designmode.adapter;

import com.tansun.designmode.adapter.service.AdvancedMediaPlayer;
import com.tansun.designmode.adapter.service.MediaPlayer;
import com.tansun.designmode.adapter.service.TVUPlayer;
import com.tansun.designmode.adapter.service.player.CCTVPlayer;
import com.tansun.designmode.adapter.service.player.Mp4Player;
import com.tansun.designmode.adapter.service.player.VlcPlayer;

/**
 * @ClassName MediaAdapter
 * @Description 媒体适配器
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:42
 * @Copyright © 2020 阿里巴巴
 */
public class MediaAdapter implements MediaPlayer {


    // 先进的媒体播放器
    AdvancedMediaPlayer advancedMusicPlayer;

    // 影视播放器
    TVUPlayer tvuPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("cctv")){
            tvuPlayer = new CCTVPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }else if(audioType.equalsIgnoreCase("cctv")){
            tvuPlayer.CCTVPlayer();
        }
    }
}
