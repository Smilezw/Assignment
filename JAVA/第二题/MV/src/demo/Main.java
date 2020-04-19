package demo;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.runtime.RuntimeUtil;
public class Main {

    static MyFrame frame;
    
    public static void main(String[] args) { 
    		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
	            "vlc"); 
    	   frame = new MyFrame();
           frame.setVisible(true);
    }
    

}