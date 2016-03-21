package cn.jungu009.util;


/**
 * ���ſ��ƹ�����
 * @author jungu009
 *
 */
public class PlayerControl {
	
	/** �Ƿ񲥷�  
	 * true  ����
	 * false ������  
	 */
	static boolean isPlay = false;
	
	/** �Ƿ�ֹͣ����  
	 * true  ֹͣ 
	 * false ��ֹͣ
	 */
	static boolean isStop = false;
	
	/** �����̼߳��� 
	 *  ��ֵΪ0 ��ʾ ����û�в��ŵ��߳�
	 *  ��new��һ��PlaySounds�߳̾� +1
	 *  ���ٵ���play����ʱ �жϲ����߳��Ƿ�Ϊ0 ��Ϊ0���ٴ���PlaySounds�߳�
	 */
	static int playThreadConut = 0;
	
//	/**
//	 *  ��ż�ж� 
//	 * @param  isPlay Ҫ�ж���ż����
//	 * @return true  ż��
//	 * 		   false ����
//	 */
//	private boolean parity(int isPlay) {
//		boolean bool = true;  // ����
//		if(isPlay % 2 == 0) bool = false; //  isPlay % 2 == 0 ��˵������������⣿����
//		return bool;
//	}
	
	/** 
	 * ������Ƶ�ļ��ĸ�������
	 * @param audioFile Ҫ���ŵ���Ƶ�ļ�
	 */
	public void play(String audioFile) {
		isStop = false; // ����ǲ������˻��߰���ֹͣ��ť  ��ֵ����true
		isPlay = !isPlay; // ��һ�β��Ű�ť�ͷ�תһ�� ʵ�ֲ��Ű�ť�� ����/��ͣ ����
		
		if((playThreadConut) == 0) {   // ���û�������Ĳ����߳̾ʹ���һ�������߳�
			System.out.println("Create a thread");
			new PlaySounds(audioFile).start();
			playThreadConut++;
		}
		System.out.println("Stop is " + isStop);  // test
		System.out.println("Play is " + isPlay);  // test
	} // play
	
	/**
	 * ֹͣ���ŵĸ�������
	 */
	public void stop() {
		isPlay = false;    // ��Ҫֹͣ��ѭ�������˳�
		isStop = true;     // �˳���ѭ�� �����߳�
		System.out.println("i am stop method ");  // test
	}
	
} // PlayerControl
