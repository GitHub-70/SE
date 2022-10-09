package com.tansun.designmode.adapter.service;
/**
 * @ClassName MediaPlayer
 * @Description 媒体播放器
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 15:37
 * @Copyright © 2020 阿里巴巴
 */
public interface MediaPlayer {
   /* *
    * @Author 吴槐
    * @Description 根据类型，播放不同类型的文件
    * @Date 15:38 2022/9/22
    * @Param audioType
    * @Param: fileName
    * @return void
    *       
    **/
    public void play(String audioType, String fileName);
}
