package com.tansun.designmode.adapter.service;
/**
 * @ClassName AdvancedMediaPlayer
 * @Description 先进的媒体播放器：播放 vlc 和 mp4 格式的文件
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:39
 * @Copyright © 2020 阿里巴巴
 */
public interface AdvancedMediaPlayer {

    public void playVlc(String fileName);

    public void playMp4(String fileName);
}
