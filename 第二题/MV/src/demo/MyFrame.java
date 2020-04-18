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
	//��ǩ
	private JLabel label;
	//�ļ�ѡ����
	private JFileChooser chooser;
	//����Ŀ��   �߶�
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
		
		//��Ƶ�Ľ���
		
		//������ǩ
		label = new JLabel();
		//���õ�������
		add(label);
		
		//���� �ļ�ѡ����
		chooser = new JFileChooser();
		//�Ե�ǰĿ¼����һ���ļ�Ŀ¼ʵ����Ȼ������Ϊ��ǰ�Ĺ���Ŀ¼
		chooser.setCurrentDirectory(new File("."));
		
		//�����˵�
		JMenuBar menuBar = new JMenuBar();
		//���ò˵���
		setJMenuBar(menuBar);
		//�˵�ѡ��
		JMenu menu = new JMenu("File");
		//���õ��˵�����
		menuBar.add(menu);
		//ѡ�� �˵� ��  �ر�
		JMenuItem open  = new JMenuItem("OpenImage");
		//���õ�File ѡ����
		menu.add(open);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//���ؽ��
				int result = chooser.showOpenDialog(null);
				//�ж�ѡ�е��ļ�����
				if (result == JFileChooser.APPROVE_OPTION) {
					//��ȡ·��
					String name = chooser.getSelectedFile().getPath();
					//����ͼƬ
					label.setIcon(new ImageIcon(name));
				}
				
			}
		});
		
		//��Ƶ������
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