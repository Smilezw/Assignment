package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import demo.player;

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
    public static String echo(String msg){
        return "echo:"+msg;
    }
	
    public void upData(player s) {           //更新数据
    	try {
    		socket = new Socket("localhost", port);
    		BufferedReader br=getReader(socket);  //输入流
	        PrintWriter pw=getWriter(socket);     //输出流
	        String msg= s.name + " " + s.score;
	        pw.println(msg);              //发送请求
            while (true) {//得到反馈
            	msg=br.readLine();            //会一直等缓冲区的憨憨函数
            	if(msg.equals(null)) break;
            	if(msg.equals("end"))break;
                System.out.println(msg);
            }
            System.out.println("反馈结束");
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
    
    public ArrayList<player> getData() {                 //请求数据
    	ArrayList<player> t = new ArrayList<player>();
    	try {
    		socket = new Socket("localhost", port);
    		BufferedReader br=getReader(socket);  //输入流
	        PrintWriter pw=getWriter(socket);     //输出流
	        String msg="getRanking";
	        pw.println(msg);              //发送请求
	        System.out.println("反馈开始");
            while (true) {//得到反馈
            	msg=br.readLine();            //会一直等缓冲区的憨憨函数
            	if(msg.equals(null)) break;
            	if(msg.equals("end"))break;
            	if(msg.equals("")) break;
            	String s[] = msg.split(" ");
            	t.add(new player(s[0], s[1]));
                System.out.println(msg);
            }
            System.out.println("反馈结束");
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
    	return (t==null)?null:t;
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException {
    	socket = new Socket("localhost", port);
    	try {
    		BufferedReader br=getReader(socket);  //输入流
	        PrintWriter pw=getWriter(socket);     //输出流
	        
	        String msg=null;
	        @SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
	        
	        while(!(msg = in.nextLine()).equals("bye")){
	        	
	            System.out.println("请求信息 ：" + msg);
	            pw.println(msg);              //发送请求
	            if(msg.equals("bye"))
	                break;
	            
	            while (true) {//得到反馈
	            	msg=br.readLine();            //会一直等缓冲区的憨憨函数
	            	if(msg.equals(null)) break;
	            	if(msg.equals("end"))break;
	                System.out.println(msg);
	            }
	            System.out.println("反馈结束");
	        }
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
}