import java.applet.Applet;
import java.awt.*;

// Applet 模式运行  

@SuppressWarnings("serial")
public class Main extends Applet implements Runnable {

	final String s = "This is my string";
	Thread mThread = null;
	int sx;

	public void init() {
		// TODO Auto-generated method stub
		System.out.println("init");
		mThread = new Thread(this);
	}
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString(s, sx, 40);
	}
	
	public void start() {
		System.out.println("start");
		mThread.start();
		try {
			Thread.sleep(50);
		} catch(InterruptedException e) {}
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		System.out.println("stop");
		if(mThread.isAlive())
			mThread.stop();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("run");
			while(true) {
				sx = ++sx%getWidth();
				repaint();
				Thread.sleep(100);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
