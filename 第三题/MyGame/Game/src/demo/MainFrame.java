package demo;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import socket.Client;

public class MainFrame {
	
	private static JFrame f = new JFrame("主界面");
	private static JFrame r = new JFrame("排行榜");
	private static String name = null;
			
	public static void main(String[] args) {
		Button b1 = new Button("排行榜");
        Button b2 = new Button("开始游戏");
        b1.setSize(100, 20);
        b2.setSize(100, 20);
        JPanel panel = new JPanel();
        panel.add(b1);
        panel.add(b2);
		
        //
        TextField tf = new TextField(20);
        panel.add(tf);
        Button b3 = new Button("确认用户名");
        panel.add(b3);
        
		f.pack();
        FlowLayout t = new FlowLayout();
        f.setBounds(10, 10, 500, 300);
        f.setResizable(false);// 禁止改变Frame大小
        f.setLayout(t);
        f.add(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        b1.addActionListener(new ActionListener() {           //排行榜
            @Override
            public void actionPerformed(ActionEvent e) {
            	r.pack();
            	r.setBounds(10, 10, 500, 1000);
                r.setResizable(false);// 禁止改变Frame大小
                r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		r.setContentPane(new Ranking());
        		r.setVisible(true);
            }
        });
 
        b2.addActionListener(new ActionListener() {            //开始游戏
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(name == null) {
        			JOptionPane.showMessageDialog(f, "请先确认输入用户名");
        			return;
        		}
        		PanelGame g = new PanelGame();
        		g.init(name);
            }
        });
        
        b3.addActionListener(new ActionListener() {          //名字输入

            @Override
            public void actionPerformed(ActionEvent e) {

                //获取文本框中的文本
                String tf_str = tf.getText().trim();
                if(tf_str.length() > 10) {
                	JOptionPane.showMessageDialog(f, "用户名过长");
                	return;
                }
                name = tf_str;
            }
        });
        
	}
}


class Ranking extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<player> arr = new ArrayList<player>();
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.BOLD, 20));
		
		int x = 32, y = 32;
		Client client = new Client();
		arr = client.getData();
		
		System.out.println("kankan");
		for(player i:arr) {
			System.out.println(i.name + " " + i.score);
		}
		
		g.drawString("姓名" + "   " + "成绩",  x, y);
		
		for(player i:arr) {
			y = y + 40;
			g.drawString(i.name + "   " + i.score, x, y);
		}
	}
}
