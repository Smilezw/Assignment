package Demo;

import Socket.Client;
import Socket.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* 主界面  用户开始的模块
 */

public class MainFrame {
    private static JFrame f = new JFrame("主界面");
    private static Client client = new Client();
    private static Ranking ranking = new Ranking();
    private static PanelGame game = new PanelGame();

    private static Button b = new Button("开始游戏");
    private static Button bo = new Button("确认用户名");
    private static TextField tf = new TextField(20);
    private static String name = null;

    private static Timer timer = new Timer(3000, new timerListener());

    static class timerListener implements ActionListener {   //检测游戏是否在运行  获取动态排行榜
        public void actionPerformed(ActionEvent e){
            if(game.timer.isRunning()) {
                String score = String.valueOf(game.score);
                client.upData(new player(name, score));         //更新成绩
            } else {
                System.out.println("stop game");
                timer.stop();
                Ranking.timer.stop();
                ranking.f.setVisible(false);
                String score = String.valueOf(game.score);
                client.upData(new player(name, score));         //更新成绩
            }
        }
    }

    private void fInit() {
        b.setSize(100, 20);
        JPanel panel = new JPanel();
        panel.add(b);
        panel.add(tf);
        panel.add(bo);
        f.pack();
        FlowLayout t = new FlowLayout();
        f.setBounds(10, 10, 500, 300);
        f.setResizable(false);// 禁止改变Frame大小
        f.setLayout(t);
        f.add(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);// 设置窗体居中
        f.setVisible(true);
    }

    public MainFrame() {
        fInit();
        //开始游戏
        b.addActionListener(e -> {
            if (name == null) {
                JOptionPane.showMessageDialog(f, "请先确认输入用户名");
                return;
            }
            client.upData(new player(name, "0"));
            ranking.init();     //排行榜开始
            game.init();        //游戏开始
            timer.start();      //监听开始
        });

        //名字输入
        bo.addActionListener(e -> {
            //获取文本框中的文本
            String tf_str = tf.getText().trim();
            if (tf_str.length() > 10) {
                JOptionPane.showMessageDialog(f, "用户名过长");
                return;
            }
            name = tf_str;
        });
    }
}
