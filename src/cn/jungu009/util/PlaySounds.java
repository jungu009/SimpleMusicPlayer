package cn.jungu009.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * 播放声音的线程类
 * @author jungu009
 *
 */
public class PlaySounds extends Thread {

	 private String filename;  // 音频文件的完整路径

	 public PlaySounds(String wavfile) {
		 this.filename = wavfile;
	 }
	 
 
	 public void run() {
	
		  File soundFile = new File(filename);  //音频文件的抽象表示 
		  AudioInputStream audioInputStream = null;  // 音频输入流
		  
		  /**
		   * 源数据行 充当其混频器的源
		   */
		  SourceDataLine auline = null;  // 定义一个源数据行
		  
		  
		  /**
		   * 获得音频文件输入流
		   */
		  try {
		   	audioInputStream = AudioSystem.getAudioInputStream(soundFile);  // 从提供的文件获得音频输入流
		  } catch (Exception e1) {
		   	e1.printStackTrace();
		   	return;   // 有异常就终止
		  }
		  
		  /**
		   * 根据音频文件格式获取 有用的源数据行
		   */
		  
		  AudioFormat format = audioInputStream.getFormat();  // 获得声音数据的音频格式
		  
		  DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);  // 数据行信息
		  try {
		   	auline = (SourceDataLine) AudioSystem.getLine(info);     // 获得与info匹配的行
		   	auline.open(format);   // 打开具有制定格式的行  使行获得所有系统资源变得可操作
		  } catch (Exception e) {
		   	e.printStackTrace();
		   	return;  // 有异常就终止
		  }
		  auline.start();   // 执行该行的数据IO
		  
	  /**
	   * 读取音频文件输入流 		
	   * 写到混音器
	   */
		  int nBytesRead = 0; 
		  byte[] abData = new byte[1024]; //这是缓冲
		  
		  try {
			  
			while(!PlayerControl.isStop) {
				while (nBytesRead != -1 && PlayerControl.isPlay) {
			    	nBytesRead = audioInputStream.read(abData, 0, abData.length);  // 读取音频输入流 并放到缓冲中去
			    	if (nBytesRead >= 0)  
			     	auline.write(abData, 0, nBytesRead);   // 通过此源数据行将缓冲区内的音频数据写入到SourceDataLine
			   	}
				
				if(nBytesRead == -1) {
					PlayerControl.isStop = true;
					PlayerControl.isPlay = false;
				}
				
				System.out.print("");	// 为什么没有这句 暂停了就恢复不了       和流有没有关系  还是线程的某种机制  
				
			}
			
			System.out.println("out");  // test
		   	
		  } catch (IOException e) {
		   	e.printStackTrace();
		   	return;
		  } finally {
		   	auline.drain();  // 清空数据行
		   	auline.close();  // 关闭数据行
		   	PlayerControl.playThreadConut-- ; // 线程接收 计数减1 
		   	
		   	System.out.println("Number of Thread:" + PlayerControl.playThreadConut);  // test
		   	
		  }
		  
	 } //run 
	 
}