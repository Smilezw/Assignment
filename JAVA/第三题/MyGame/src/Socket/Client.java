package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

        /*
        *  �������ϴ�  ��������
         */

public class Client {

    private static Socket socket = null;
    private static int port=10010;

    private static PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    private static BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void upData(player s) {           //��������
        try {
            socket = new Socket("localhost", port);
            BufferedReader br=getReader(socket);  //������
            PrintWriter pw=getWriter(socket);     //�����
            String msg= s.name + " " + s.score;
            pw.println(msg);              //��������
            do {                //�õ�����
                msg = br.readLine();            //��һֱ�Ȼ������ĺ�������
            } while (!msg.equals("end"));
            pw.println("bye");            //****�Ͽ�����
            System.out.println("�Ͽ�");
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<player> getData() {                 //��������
        ArrayList<player> t = new ArrayList<player>();
        try {
            socket = new Socket("localhost", port);
            BufferedReader br=getReader(socket);  //������
            PrintWriter pw=getWriter(socket);     //�����
            String msg="getRanking";
            pw.println(msg);              //��������
            System.out.println("������ʼ");
            while (true) {//�õ�����
                msg=br.readLine();            //��һֱ�Ȼ������ĺ�������
                if(msg.equals("end"))break;
                if(msg.equals("")) break;
                String[] s = msg.split(" ");
                t.add(new player(s[0], s[1]));
            }
            pw.println("bye");             //******�Ͽ�����
            System.out.println("��������");
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}