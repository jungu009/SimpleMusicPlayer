package cn.jungu009.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;

import cn.jungu009.util.PlayerControl;

/**
 * ��������������
 * @author jungu009
 *
 */
public class PlayerView extends Frame{

	/** ����������ĳߴ� λ����Ϣ */
	private int frameWidth = 400;
	private int frameHeight = 350;
	private int frameX = (this.getToolkit().getScreenSize().width - frameWidth) / 2;
	private int frameY = (this.getToolkit().getScreenSize().height - frameHeight) / 2;
	
	/** ������������������ */
	private Panel control;
	private Button play;
	private Button stop;
	
	
	public PlayerView() {
		init();
		addListener();
	}
	
	/**
	 * ��ʼ���������
	 */
	private void init() {
		initFrame();
		initControlPanel();
	}
	
	/**
	 * ��ʼ������
	 */
	private void initFrame() {
		this.setBounds(frameX, frameY, frameWidth, frameHeight);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	/**
	 * ��ʼ���������
	 */
	private void initControlPanel() {
		/** control �ĳߴ� �� λ�� */
		int controlWidth = frameWidth;
		int controlHeight = frameHeight / 3;
		int controlX = 0;
		int controlY = controlHeight * 2;		
		
		control = new Panel();
		
		control.setLayout(null);
		control.setVisible(true);
		control.setBounds(controlX, controlY, controlWidth, controlHeight);
		control.setBackground(Color.GRAY);
		this.add(control);
		
		
		initControlButton(control);
		
	}
	
	/**
	 * ��ʼ�����п�������еİ�ť
	 * @param control �������
	 */
	private void initControlButton(Panel control) {
		/** ��ť�Ĵ�С�ͳߴ� */
		int buttonWidth = 70;
		int buttonHeight = 70;
		int playX = (control.getSize().width) / 2 - 100;
		int playY = (control.getSize().height - buttonHeight) / 2; 
		int stopX = (control.getSize().width) / 2 + 30;
 		int stopY = playY;
		
		/** ���Ű�ť�ĳ�ʼ�� */
		play = new Button("Play/Pause");
		play.setBounds(playX, playY, buttonWidth, buttonHeight);
		control.add(play);
		
		/** ֹͣ��ť�ĳ�ʼ�� */
		stop = new Button("Stop");
		stop.setBounds(stopX, stopY, buttonWidth, buttonHeight);
		control.add(stop);
		
		
	}
	
	/**
	 * ��Ӽ���
	 */
	private void addListener() {
		Mylistener ml = new Mylistener();
		this.addWindowListener(ml);  // ���������ڵļ���
		play.addActionListener(ml);  // ���Ű�ť��Ӽ���
		stop.addActionListener(ml);  // ֹͣ��ť��Ӽ���
	}
	
	
	/** 
	 * �ڲ������� 
	 * 
	 */
	private class Mylistener extends WindowAdapter implements ActionListener {
		/** ���ſ��ƵĹ����� */
		private PlayerControl pc = new PlayerControl();
		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Object e = ((EventObject) event).getSource();
			
			/** ���Ű�ť�ļ��� */
			if(e == play) {
				pc.play(System.getProperty("user.dir") + "\\music.wav");
			}else if(e == stop){
				System.out.println("button stop");  // test
				pc.stop();
			}
			
		}

		/** ���ڼ��� */
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		
		
		
	}  // Mylistener
	
} // PlayerView
