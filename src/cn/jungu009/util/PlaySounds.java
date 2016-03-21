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
 * �����������߳���
 * @author jungu009
 *
 */
public class PlaySounds extends Thread {

	 private String filename;  // ��Ƶ�ļ�������·��

	 public PlaySounds(String wavfile) {
		 this.filename = wavfile;
	 }
	 
 
	 public void run() {
	
		  File soundFile = new File(filename);  //��Ƶ�ļ��ĳ����ʾ 
		  AudioInputStream audioInputStream = null;  // ��Ƶ������
		  
		  /**
		   * Դ������ �䵱���Ƶ����Դ
		   */
		  SourceDataLine auline = null;  // ����һ��Դ������
		  
		  
		  /**
		   * �����Ƶ�ļ�������
		   */
		  try {
		   	audioInputStream = AudioSystem.getAudioInputStream(soundFile);  // ���ṩ���ļ������Ƶ������
		  } catch (Exception e1) {
		   	e1.printStackTrace();
		   	return;   // ���쳣����ֹ
		  }
		  
		  /**
		   * ������Ƶ�ļ���ʽ��ȡ ���õ�Դ������
		   */
		  
		  AudioFormat format = audioInputStream.getFormat();  // ����������ݵ���Ƶ��ʽ
		  
		  DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);  // ��������Ϣ
		  try {
		   	auline = (SourceDataLine) AudioSystem.getLine(info);     // �����infoƥ�����
		   	auline.open(format);   // �򿪾����ƶ���ʽ����  ʹ�л������ϵͳ��Դ��ÿɲ���
		  } catch (Exception e) {
		   	e.printStackTrace();
		   	return;  // ���쳣����ֹ
		  }
		  auline.start();   // ִ�и��е�����IO
		  
	  /**
	   * ��ȡ��Ƶ�ļ������� 		
	   * д��������
	   */
		  int nBytesRead = 0; 
		  byte[] abData = new byte[1024]; //���ǻ���
		  
		  try {
			  
			while(!PlayerControl.isStop) {
				while (nBytesRead != -1 && PlayerControl.isPlay) {
			    	nBytesRead = audioInputStream.read(abData, 0, abData.length);  // ��ȡ��Ƶ������ ���ŵ�������ȥ
			    	if (nBytesRead >= 0)  
			     	auline.write(abData, 0, nBytesRead);   // ͨ����Դ�����н��������ڵ���Ƶ����д�뵽SourceDataLine
			   	}
				
				if(nBytesRead == -1) {
					PlayerControl.isStop = true;
					PlayerControl.isPlay = false;
				}
				
				System.out.print("");	// Ϊʲôû����� ��ͣ�˾ͻָ�����       ������û�й�ϵ  �����̵߳�ĳ�ֻ���  
				
			}
			
			System.out.println("out");  // test
		   	
		  } catch (IOException e) {
		   	e.printStackTrace();
		   	return;
		  } finally {
		   	auline.drain();  // ���������
		   	auline.close();  // �ر�������
		   	PlayerControl.playThreadConut-- ; // �߳̽��� ������1 
		   	
		   	System.out.println("Number of Thread:" + PlayerControl.playThreadConut);  // test
		   	
		  }
		  
	 } //run 
	 
}