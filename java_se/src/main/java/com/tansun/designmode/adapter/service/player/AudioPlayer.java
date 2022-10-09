package com.tansun.designmode.adapter.service.player;

import com.tansun.designmode.adapter.MediaAdapter;
import com.tansun.designmode.adapter.service.MediaPlayer;

/**
 * @ClassName AudioPlayer
 * @Description 音频播放器
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:44
 * @Copyright © 2020 阿里巴巴
 */
public class AudioPlayer implements MediaPlayer {

    // 媒体适配器
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {

        //播放 mp3 音乐文件的内置支持
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if(audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else if(audioType.equalsIgnoreCase("cctv")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else{
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}
