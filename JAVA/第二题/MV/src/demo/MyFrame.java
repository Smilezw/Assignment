package demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;  
  
@SuppressWarnings("serial")
public class MyFrame extends JFrame{  
      
    private JPanel contentPane; //������������������ҳ�������  
    private JMenuBar menuBar;   //�˵���  
    private JMenu mnFile,mnSetting,mnHelp;  //�ļ��˵�  
    private JMenuItem mnOpenVideo,mnExit;   //�ļ��˵���Ŀ¼�����������˳�  
    private JPanel panel;   //������������  
    private JProgressBar progress;  //������  
    private JPanel progressPanel;   //����������  
    private JPanel controlPanel;    //���ư�ť����  
    private JButton btnStop,btnPlay,btnPause;   //���ư�ť��ֹͣ�����š���ͣ  
    private JSlider slider;     //�������ƿ�  
    private  Timer  timer ;     
      
      
    EmbeddedMediaPlayerComponent playerComponent;   //ý�岥�������  
      
    public static void main(String[] args) {  
          
    }  
  
    //MainWindow���췽���������������ŵ�������  
    public MyFrame(){  
        setTitle("��Ƶ������");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setBounds(200,80,900,600);  
        contentPane=new JPanel();  
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
        contentPane.setLayout(new BorderLayout(0,0));  
        setContentPane(contentPane);
          
        /*��Ƶ���Ŵ����еĲ˵���*/  
        menuBar=new JMenuBar();  
        setJMenuBar(menuBar);  
          
        mnFile=new JMenu("�ļ�");     //���ò˵���  
        menuBar.add(mnFile);  
        mnSetting=new JMenu("����");  
        menuBar.add(mnSetting);  
        mnHelp=new JMenu("����");  
        menuBar.add(mnHelp);  
          
        mnOpenVideo =new JMenuItem("���ļ�"); //�����ļ��˵���Ŀ¼���ļ�  
        mnFile.add(mnOpenVideo);  
          
        mnExit =new JMenuItem("�˳�");    //�����ļ��˵���Ŀ¼�˳�  
        mnFile.add(mnExit);  
        
      
        //���ļ�  ,���������һ���ļ����ˣ�ֻѡ�������Ƶ�ļ���
        mnOpenVideo.addActionListener(new ActionListener() {  
              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
            	 JFileChooser chooser = new JFileChooser();
                 int v = chooser.showOpenDialog(null);
                 if (v == JFileChooser.APPROVE_OPTION) {
                     File file = chooser.getSelectedFile();                    
                    getMediaPlayer().playMedia(file.getAbsolutePath());
                 }
            }  
        });  
          
        //�˳�  
        mnExit.addActionListener(new ActionListener() {  
              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
            	exitActionPerformed();
            }  
        });  
          
          
        /*���������в��Ž��沿��*/  
        JPanel videoPane=new JPanel();  
        contentPane.add(videoPane, BorderLayout.CENTER);  
        videoPane.setLayout(new BorderLayout(0,0));
          
        playerComponent=new EmbeddedMediaPlayerComponent();  
        videoPane.add(playerComponent);
        
        
          
        /*���������п��Ʋ���*/  
          
        panel=new JPanel();     //ʵ����������������  
        videoPane.add(panel,BorderLayout.SOUTH);  
          
        progressPanel=new JPanel(); //ʵ��������������  
        panel.add(progressPanel, BorderLayout.NORTH);  
  
        //��ӽ�����  
        progress=new JProgressBar();      
        progressPanel.add(progress);  
        //panel.add(progress,BorderLayout.NORTH);  
        progress.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e){     //��������������������Ž���  
                int x=e.getX();  
                getMediaPlayer().setTime((long) ((float)x/progress.getWidth() *getMediaPlayer().getLength()));
                 
            }  
        });  
        progress.setStringPainted(true);  
          
          
        controlPanel=new JPanel();      //ʵ�������ư�ť����  
        panel.add(controlPanel,BorderLayout.SOUTH);  
          
        //���ֹͣ��ť  
        btnStop=new JButton("ֹͣ");  
        btnStop.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                // TODO Auto-generated method stub  
            	 getMediaPlayer().stop();
                if(timer.isRunning()) {
              	  timer.stop();//ֹͣ��
              	
              }
                getProgressBar().setValue(-1);
            }  
        });  
        controlPanel.add(btnStop);  
          
        //��Ӳ��Ű�ť  
        btnPlay=new JButton("����");  
        btnPlay.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                // TODO Auto-generated method stub  
            	 getMediaPlayer().play();
                //�ж�timer�Ƿ��������У�
                if(!timer.isRunning()) {
                	  timer.start(); //������
                }
            }  
        });  
        controlPanel.add(btnPlay);  
          
        //�����ͣ��ť  
        btnPause=new JButton("��ͣ");  
        btnPause.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                // TODO Auto-generated method stub
                //PlayerMain.dispose();
            	 getMediaPlayer().pause();
            }  
        });  
        controlPanel.add(btnPause);  
          
        //����������ƿ�  
        slider=new JSlider();  
        slider.setValue(80);  
        slider.setMaximum(100);  
        slider.addChangeListener(new ChangeListener() {  
              
            @Override  
            public void stateChanged(ChangeEvent e) {  
                // TODO Auto-generated method stub  
               
                getMediaPlayer().setVolume(slider.getValue());
            }  
        });  
        controlPanel.add(slider);  
        
        
        //��Ƶ���������¼�����
        playerComponent.getVideoSurface().addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("**************");
            }
        });
        
        
       timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 long total = getMediaPlayer().getLength();
              
               long curr = getMediaPlayer().getTime();
               float percent = (float) curr / total;
               getProgressBar().setValue((int)(percent*100));
               
            }
        });
      
       timer.start();
        
        
       addWindowListener(new WindowAdapter() { // ���ڼ����˴��ڵĹر��¼�,��ͬ�ڡ��˳���¼���ܡ�
			@Override
			public void windowClosing(WindowEvent e) {
				exitActionPerformed();
			}
		});
    }  
      
    //��ȡ����ý��ʵ����ĳ����Ƶ��  
    public EmbeddedMediaPlayer getMediaPlayer() {  
        return playerComponent.getMediaPlayer();  
    }  
      
    //��ȡ������ʵ��  
    public JProgressBar getProgressBar() {  
        return progress;  
    }   
    
    /**
     * �˳���
     */
    public void exitActionPerformed() {
    	getMediaPlayer().release();
        System.exit(0);
    }
               
}