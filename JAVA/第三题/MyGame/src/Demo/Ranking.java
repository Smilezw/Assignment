package Demo;

import Socket.Client;
import Socket.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ranking{
    /**
     *
     */
    private static ArrayList<player> arr = new ArrayList<>();
    private static BufferedImage image = new BufferedImage(400, 800, BufferedImage.TYPE_INT_RGB);
    private static Graphics g = image.createGraphics();
    private static MyCanvas drawArea = new MyCanvas();
    private static int b = 0;

    public JFrame f = new JFrame("���а�");
    public static Timer timer = new Timer(5000, new timerListener());

    static class timerListener implements ActionListener {   //�����Ϸ�Ƿ�������  ��ȡ��̬���а�
        public void actionPerformed(ActionEvent e){
            /*
            ��ȡ����
             */
            Client client = new Client();
            arr = client.getData();
            paintArea();
        }
    }        //��ʱ����

    public void init(){
        drawArea.setPreferredSize(new Dimension(300, 700));
        if(b == 0) {
            f.add(drawArea);
            b = 1;
        }

        f.setResizable(false);// ��ֹ�ı�Frame��С
        f.setBounds(0, 0, 400,700 );
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setAlwaysOnTop(true);
        f.setVisible(true);
        timer.start();
    }

    private static void paintArea(){
        System.out.println("print");
        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 800);   //��ɫ���
        int x = 32, y = 32;
        g.setColor(Color.black);
        g.setFont(new Font("Microsoft YaHei Mono", Font.BOLD, 20));
        g.drawString("����" + "   " + "�ɼ�",  x, y);
        for(player i:arr) {
            y = y + 40;
            String st = i.name + "   " + i.score;
            g.drawString(st, x, y);
        }
        drawArea.repaint();
    }

    private static class MyCanvas extends JPanel{
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void paint(Graphics g){
            g.drawImage(image, 0, 0, null);
        }
    }
}

