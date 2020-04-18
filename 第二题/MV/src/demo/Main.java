package demo;

import java.awt.EventQueue;
 
import javax.swing.JFrame;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.runtime.RuntimeUtil;


public class Main {
	
	public static void main(String[] args) {
		
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
	            "vlc"); 
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new MyFrame();
				//设置关闭方式
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}