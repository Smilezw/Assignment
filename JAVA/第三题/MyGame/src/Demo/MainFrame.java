package Demo;

import Socket.Client;
import Socket.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* ������  �û���ʼ��ģ��
 */

public class MainFrame {
    private static JFrame f = new JFrame("������");
    private static Client client = new Client();
    private static Ranking ranking = new Ranking();
    private static PanelGame game = new PanelGame();

    private static Button b = new Button("��ʼ��Ϸ");
    private static Button bo = new Button("ȷ���û���");
    private static TextField tf = new TextField(20);
    private static String name = null;

    private static Timer timer = new Timer(3000, new timerListener());

    static class timerListener implements ActionListener {   //�����Ϸ�Ƿ�������  ��ȡ��̬���а�
        public void actionPerformed(ActionEvent e){
            if(game.timer.isRunning()) {
                String score = String.valueOf(game.score);
                client.upData(new player(name, score));         //���³ɼ�
            } else {
                System.out.println("stop game");
                timer.stop();
                Ranking.timer.stop();
                ranking.f.setVisible(false);
                String score = String.valueOf(game.score);
                client.upData(new player(name, score));         //���³ɼ�
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
        f.setResizable(false);// ��ֹ�ı�Frame��С
        f.setLayout(t);
        f.add(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);// ���ô������
        f.setVisible(true);
    }

    public MainFrame() {
        fInit();
        //��ʼ��Ϸ
        b.addActionListener(e -> {
            if (name == null) {
                JOptionPane.showMessageDialog(f, "����ȷ�������û���");
                return;
            }
            client.upData(new player(name, "0"));
            ranking.init();     //���а�ʼ
            game.init();        //��Ϸ��ʼ
            timer.start();      //������ʼ
        });

        //��������
        bo.addActionListener(e -> {
            //��ȡ�ı����е��ı�
            String tf_str = tf.getText().trim();
            if (tf_str.length() > 10) {
                JOptionPane.showMessageDialog(f, "�û�������");
                return;
            }
            name = tf_str;
        });
    }
}
