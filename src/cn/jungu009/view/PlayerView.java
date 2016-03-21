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
 * 播放器的主界面
 * @author jungu009
 *
 */
public class PlayerView extends Frame{

	/** 播放器界面的尺寸 位置信息 */
	private int frameWidth = 400;
	private int frameHeight = 350;
	private int frameX = (this.getToolkit().getScreenSize().width - frameWidth) / 2;
	private int frameY = (this.getToolkit().getScreenSize().height - frameHeight) / 2;
	
	/** 播放器界面包含的组件 */
	private Panel control;
	private Button play;
	private Button stop;
	
	
	public PlayerView() {
		init();
		addListener();
	}
	
	/**
	 * 初始化所有组件
	 */
	private void init() {
		initFrame();
		initControlPanel();
	}
	
	/**
	 * 初始化窗口
	 */
	private void initFrame() {
		this.setBounds(frameX, frameY, frameWidth, frameHeight);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	/**
	 * 初始化控制面板
	 */
	private void initControlPanel() {
		/** control 的尺寸 和 位置 */
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
	 * 初始化所有控制面板中的按钮
	 * @param control 控制面板
	 */
	private void initControlButton(Panel control) {
		/** 按钮的大小和尺寸 */
		int buttonWidth = 70;
		int buttonHeight = 70;
		int playX = (control.getSize().width) / 2 - 100;
		int playY = (control.getSize().height - buttonHeight) / 2; 
		int stopX = (control.getSize().width) / 2 + 30;
 		int stopY = playY;
		
		/** 播放按钮的初始化 */
		play = new Button("Play/Pause");
		play.setBounds(playX, playY, buttonWidth, buttonHeight);
		control.add(play);
		
		/** 停止按钮的初始化 */
		stop = new Button("Stop");
		stop.setBounds(stopX, stopY, buttonWidth, buttonHeight);
		control.add(stop);
		
		
	}
	
	/**
	 * 添加监听
	 */
	private void addListener() {
		Mylistener ml = new Mylistener();
		this.addWindowListener(ml);  // 播放器窗口的监听
		play.addActionListener(ml);  // 播放按钮添加监听
		stop.addActionListener(ml);  // 停止按钮添加监听
	}
	
	
	/** 
	 * 内部监听类 
	 * 
	 */
	private class Mylistener extends WindowAdapter implements ActionListener {
		/** 播放控制的工具类 */
		private PlayerControl pc = new PlayerControl();
		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Object e = ((EventObject) event).getSource();
			
			/** 播放按钮的监听 */
			if(e == play) {
				pc.play(System.getProperty("user.dir") + "\\music.wav");
			}else if(e == stop){
				System.out.println("button stop");  // test
				pc.stop();
			}
			
		}

		/** 窗口监听 */
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		
		
		
	}  // Mylistener
	
} // PlayerView
