package cn.jungu009.util;


/**
 * 播放控制工具类
 * @author jungu009
 *
 */
public class PlayerControl {
	
	/** 是否播放  
	 * true  播放
	 * false 不播放  
	 */
	static boolean isPlay = false;
	
	/** 是否停止播放  
	 * true  停止 
	 * false 不停止
	 */
	static boolean isStop = false;
	
	/** 播放线程计数 
	 *  初值为0 表示 现在没有播放的线程
	 *  当new了一个PlaySounds线程就 +1
	 *  当再调用play方法时 判断播放线程是否为0 不为0则不再创建PlaySounds线程
	 */
	static int playThreadConut = 0;
	
//	/**
//	 *  奇偶判断 
//	 * @param  isPlay 要判断奇偶的数
//	 * @return true  偶数
//	 * 		   false 奇数
//	 */
//	private boolean parity(int isPlay) {
//		boolean bool = true;  // 奇数
//		if(isPlay % 2 == 0) bool = false; //  isPlay % 2 == 0 据说这个方法有问题？？？
//		return bool;
//	}
	
	/** 
	 * 播放音频文件的辅助方法
	 * @param audioFile 要播放的音频文件
	 */
	public void play(String audioFile) {
		isStop = false; // 如果是播放完了或者按了停止按钮  该值会变成true
		isPlay = !isPlay; // 按一次播放按钮就反转一次 实现播放按钮的 播放/暂停 功能
		
		if((playThreadConut) == 0) {   // 如果没有其他的播放线程就创建一个播放线程
			System.out.println("Create a thread");
			new PlaySounds(audioFile).start();
			playThreadConut++;
		}
		System.out.println("Stop is " + isStop);  // test
		System.out.println("Play is " + isPlay);  // test
	} // play
	
	/**
	 * 停止播放的辅助方法
	 */
	public void stop() {
		isPlay = false;    // 先要停止内循环才能退出
		isStop = true;     // 退出大循环 结束线程
		System.out.println("i am stop method ");  // test
	}
	
} // PlayerControl
