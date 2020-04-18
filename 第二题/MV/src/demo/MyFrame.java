package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MyFrame extends JFrame {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//标签
	private JLabel label;
	//文件选择器
	private JFileChooser chooser;
	//窗体的宽度   高度
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400 ;
	
	private EmbeddedMediaPlayerComponent playerComponent = new EmbeddedMediaPlayerComponent();; 
	
	public EmbeddedMediaPlayer getMediaPlayer(){
        return playerComponent.getMediaPlayer();
    }
	
	public MyFrame() {
		// TODO Auto-generated constructor stub
		setTitle("Demo");
		setSize(WIDTH, HEIGHT);
		
		//视频的界面
		
		//创建标签
		label = new JLabel();
		//放置到容器中
		add(label);
		
		//创建 文件选择器
		chooser = new JFileChooser();
		//以当前目录生成一个文件目录实例，然后设置为当前的工作目录
		chooser.setCurrentDirectory(new File("."));
		
		//创建菜单
		JMenuBar menuBar = new JMenuBar();
		//设置菜单栏
		setJMenuBar(menuBar);
		//菜单选项
		JMenu menu = new JMenu("File");
		//放置到菜单栏中
		menuBar.add(menu);
		//选项 菜单 打开  关闭
		JMenuItem open  = new JMenuItem("OpenImage");
		//放置到File 选型中
		menu.add(open);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//返回结果
				int result = chooser.showOpenDialog(null);
				//判断选中的文件对象
				if (result == JFileChooser.APPROVE_OPTION) {
					//获取路径
					String name = chooser.getSelectedFile().getPath();
					//设置图片
					label.setIcon(new ImageIcon(name));
				}
				
			}
		});
		
		//视频播放器
		JMenuItem videoItem = new JMenuItem("PlayerVideo");
		menu.add(videoItem);
		
		videoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int v=chooser.showOpenDialog(null);
				if(v==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					add(playerComponent);
					getMediaPlayer().playMedia(file.getAbsolutePath());
				}
			}

		});
		
		
		JMenuItem exit = new JMenuItem("exit");
		menu.add(exit);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}
}