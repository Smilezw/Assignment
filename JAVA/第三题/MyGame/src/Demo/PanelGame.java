package Demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.Timer;

import javax.swing.JOptionPane;

/*
*  俄罗斯方块画布
 */


public class PanelGame{
    public int score = 0;
    public Timer timer = new Timer(1000, new timerListener());
    private boolean newBegin;
    private final int ROW = 20;
    private final int COL = 10;
    private final int LEN = 35;
    private final int AREA_WIDTH = LEN*22;
    private final int AREA_HEIGHT = LEN*22;
    private boolean isColor = true;
    private MyCanvas drawArea = new MyCanvas();
    private JFrame f = new JFrame("俄罗斯方块");
    private BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics g = image.createGraphics();
    private int[][] map = new int[COL][ROW];
    private Color[] color = new Color[]{Color.green, Color.red, Color.orange, Color.blue, Color.cyan, Color.yellow, Color.magenta, Color.gray};
    private final int DEFAULT = 7;
    private Color[][] mapColor = new Color[COL][ROW];

    int wordX = LEN*14;
    int wordY = LEN*9;

    private int type, state, x, y, nextType, nextState;
    private int[][][][] shape = new int[][][][]{
            // S的四种翻转状态:
            { { {0,1,0,0}, {1,1,0,0}, {1,0,0,0}, {0,0,0,0} },
                    { {0,0,0,0}, {1,1,0,0}, {0,1,1,0}, {0,0,0,0} },
                    { {0,1,0,0}, {1,1,0,0}, {1,0,0,0}, {0,0,0,0} },
                    { {0,0,0,0}, {1,1,0,0}, {0,1,1,0}, {0,0,0,0} } },
            // Z:
            { { {1,0,0,0}, {1,1,0,0}, {0,1,0,0}, {0,0,0,0} },
                    { {0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0} },
                    { {1,0,0,0}, {1,1,0,0}, {0,1,0,0}, {0,0,0,0} },
                    { {0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0} } },
            // L:
            { { {0,0,0,0}, {1,1,1,0}, {0,0,1,0}, {0,0,0,0} },
                    { {0,0,0,0}, {0,1,1,0}, {0,1,0,0}, {0,1,0,0} },
                    { {0,0,0,0}, {0,1,0,0}, {0,1,1,1}, {0,0,0,0} },
                    { {0,0,1,0}, {0,0,1,0}, {0,1,1,0}, {0,0,0,0} } },
            // J:
            { { {0,0,0,0}, {0,0,1,0}, {1,1,1,0}, {0,0,0,0} },
                    { {0,0,0,0}, {0,1,1,0}, {0,0,1,0}, {0,0,1,0} },
                    { {0,0,0,0}, {0,1,1,1}, {0,1,0,0}, {0,0,0,0} },
                    { {0,1,0,0}, {0,1,0,0}, {0,1,1,0}, {0,0,0,0} } },
            // I:
            { { {0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0} },
                    { {0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0} },
                    { {0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0} },
                    { {0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0} } },
            // O:
            { { {0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0} },
                    { {0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0}  },
                    { {0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0}  },
                    { {0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0}  } },
            // T:
            { { {0,1,0,0}, {1,1,0,0}, {0,1,0,0}, {0,0,0,0} },
                    { {0,0,0,0}, {1,1,1,0}, {0,1,0,0}, {0,0,0,0} },
                    { {0,1,0,0}, {0,1,1,0}, {0,1,0,0}, {0,0,0,0} },
                    { {0,1,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0} } },

    };

    class timerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            // 如果游戏已结束
            if(isGameOver(type, state, x, y)){
                JOptionPane.showMessageDialog(f, "GAME OVER!");
                timer.stop();
                f.setVisible(false);
                return;
            }

            if(check(type, state , x, y+1) ){
                y = y +1;
            }
            else{
                add(type, state, x, y);
                delLine();
                newShape();
            }
            paintArea();
        }
    }

    private void newGame(){
        newBegin = true;
        for(int i = 0; i < COL; i++){
            Arrays.fill(map[i],0);
        }
        score = 0;
    }

    public void init() {
        newGame();
        drawArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        f.add(drawArea);
        JCheckBox gridCB = new JCheckBox("显示网格",true);
        JCheckBox colorCB = new JCheckBox("彩色方块", false);
        gridCB.setBounds(wordX, wordY-LEN,LEN,LEN);
        colorCB.setBounds(wordX, wordY-2*LEN,LEN,LEN);

        f.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        turn();
                        break;
                    case KeyEvent.VK_LEFT:
                        left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        right();
                        break;
                    case KeyEvent.VK_DOWN:
                        down();
                        break;
                }
            }
        });
        newShape();
        timer.start();

        f.pack();
        int screenSizeX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenSizeY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int fSizeX = (int)f.getSize().getWidth();
        int fSizeY = (int)f.getSize().getHeight();
        f.setResizable(false);// 禁止改变Frame大小
        f.setBounds((screenSizeX-fSizeX)/2, (screenSizeY-fSizeY)/2, fSizeX,fSizeY );
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setAlwaysOnTop(true);
        f.setVisible(true);
    }

    private void paintArea(){
        //  默认黑色，填充白色
        g.setColor(Color.white);
        g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
        //  方格线
        //  先画最外围
        g.setColor(Color.gray);
        // 游戏区域的左、上边距
        int LEFT_MARGIN = LEN * 2;
        int UP_MARGIN = LEN;
        for (int offset = 0; offset <= 2; offset++){
            g.drawRect(LEFT_MARGIN -offset, UP_MARGIN -offset, COL*LEN+offset*2, ROW*LEN+offset*2);
        }

        g.setColor(Color.gray);

        // 9条竖线
        for (int i = 1 ; i <= 9; i++){
            g.drawLine(LEFT_MARGIN +LEN*i, UP_MARGIN, LEFT_MARGIN +LEN*i, UP_MARGIN +ROW*LEN);
        }
        // 19条横线
        for(int i = 1; i <= 19; i++){
            g.drawLine(LEFT_MARGIN, UP_MARGIN +LEN*i, LEFT_MARGIN +COL*LEN, UP_MARGIN +LEN*i);
        }

        // 右上角显示下一个shape
        @SuppressWarnings("unused")
        int offset2 = 3;// 边框粗细
        @SuppressWarnings("unused")
        int col = 4;// 右上角方框的列数
        @SuppressWarnings("unused")
        int row = 4;// 行数

        g.setColor(Color.gray);
        g.setFont(new Font("Microsoft YaHei Mono", Font.BOLD, 20));
        g.drawString("下一个：", wordX, LEN*2);
        int nextX = wordX;
        int nextY = LEN*2;
        //暂不画方框
        // for (int offset = 0; offset <= 2; offset++){
        //     g.drawRect(nextX-offset+10, nextY+10-offset, col*LEN+offset*2, row*LEN+offset*2);
        // }
        //画下一次出现的下坠方块
        g.setColor(isColor?color[nextType]:color[DEFAULT]);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (shape[nextType][nextState][i][j]==1) {
                    g.fill3DRect(nextX+10+i*LEN, nextY+10+j*LEN, LEN, LEN,true);
                }
            }
        }
        g.setColor(Color.gray);
        g.setFont(new Font("Times", Font.BOLD, 15));
        g.drawString("玩法：", wordX, wordY+LEN*2);
        g.drawString("上箭头：翻转", wordX, wordY+LEN*3);
        g.drawString("左箭头：左移", wordX, wordY+LEN*4);
        g.drawString("右箭头：右移", wordX, wordY+LEN*5);
        g.drawString("下箭头：下落", wordX, wordY+LEN*6);
        g.setFont(new Font("Times", Font.BOLD, 25));
        g.drawString("得分：" + score, wordX, wordY+LEN*8);
        //画下坠物shape
        g.setColor(isColor?color[type]:color[DEFAULT]);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (shape[type][state][i][j]==1) {
                    g.fill3DRect(LEFT_MARGIN +(x+i)*LEN, UP_MARGIN +(y+j)*LEN, LEN, LEN,true);
                }
            }
        }
        //画背景map
        for(int i = 0; i < COL; i++){
            for(int j = 0; j < ROW; j++){
                if (map[i][j] == 1) {
                    g.setColor(mapColor[i][j]);
                    g.fill3DRect(LEFT_MARGIN +i*LEN, UP_MARGIN +j*LEN, LEN, LEN,true);
                }
            }
        }

        drawArea.repaint();
    }

    private class MyCanvas extends JPanel{
        private static final long serialVersionUID = 1L;

        public void paint(Graphics g){
            g.drawImage(image, 0, 0, null);
        }
    }

    private boolean check(int type, int state, int x, int y){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if ( (shape[type][state][i][j] == 1) && ( (x+i>=COL) || (x+i<0 ) || (y+j>=ROW) || (map[x+i][y+j]==1) ) ) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGameOver(int type, int state, int x, int y){
        return !check(type, state, x, y);
    }

    private void newShape(){
        Random rand = new Random();
        if(newBegin){
            type = rand.nextInt(7);
            state = rand.nextInt(4);
            newBegin = false;
        }
        else{
            type = nextType;
            state = nextState;
        }
        nextType = rand.nextInt(7);
        nextState = rand.nextInt(4);
        x = 3;
        y = 0;
        paintArea();
    }

    private void delLine(){
        boolean flag;
        int addScore = 0;
        for(int j = 0; j < ROW; j++){
            flag = true;
            for( int i = 0; i < COL; i++){
                if (map[i][j]==0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                addScore += 10;
                for(int t = j; t > 0; t--){
                    for(int i = 0; i <COL; i++){
                        map[i][t] = map[i][t-1];
                    }
                }
            }
        }
        score += addScore*addScore/COL;
    }

    private void add(int type, int state, int x, int y){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4 ; j++){
                if((y+j<ROW)&&(x+i<COL)&&(x+i>=0)&&(map[x+i][y+j]==0)){
                    map[x+i][y+j]=shape[type][state][i][j];
                    mapColor[x+i][y+j]=color[isColor?type:DEFAULT];
                }
            }
        }
    }

    private void turn(){
        int tmpState = state;
        state = (state + 1)%4;
        if (!check(type,state, x, y )) {
            state = tmpState; //不能转就什么都不做
        }
        paintArea();
    }

    private void left(){
        if(check(type,state, x-1, y)){
            --x;
        }
        paintArea();
    }

    private void right(){
        if (check(type,state, x+1, y)) {
            ++x;
        }
        paintArea();
    }

    private void down(){
        if (check(type,state, x, y+1)) {
            ++y;
        }
        //如果下不去则固定之
        else{
            add(type, state, x, y);
            delLine();
            newShape();
        }
        paintArea();
    }

}
