package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Server {
    private int port=10010;
    private ServerSocket serverSocket;
    private ExecutorService executorService;//线程池
    private final int POOL_SIZE=10;//单个CPU线程池大小
    
    public Server() throws IOException{
        serverSocket=new ServerSocket(port);
        //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        System.out.println("服务器启动");
    }
    
    public void service(){
        while(true){
            Socket socket=null;
            try {
                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
                socket=serverSocket.accept();
                executorService.execute(new Handler(socket));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Server().service();
    }

}

class Handler implements Runnable{
	
	static ArrayList <player> arr = new ArrayList<player>();
	
    private Socket socket;
    public Handler(Socket socket){
        this.socket=socket;
    }
    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    public String echo(String msg){
        return msg + "\nend";
    }
    
    public void run(){
    	System.out.println("New connection accepted "+socket.getInetAddress()+":"+socket.getPort());
	
		try {
            BufferedReader br=getReader(socket);  //输入流
            PrintWriter pw=getWriter(socket);     //输出流
            String msg=null;
            while((msg=br.readLine())!=null){          //接受
            	System.out.println("接到数据：" + msg);
                if(msg.equals("getRanking")) {
                	if(arr == null) pw.println(echo(""));   //表明接收到数据
                	else {
                		msg = "";
                		for(player i:arr) {
                    		msg = msg + i.name + " " + i.score + "\n";
                    	}
                		System.out.println("回复数据：" + msg);
                		pw.println(echo(msg));
                	}
                	
                }
                else {
                	String t[] = msg.split(" ");
                	arr.add(new player(t[0], t[1]));
                	pw.println(echo(""));
                }
                
                System.out.println("回复成功");
                if(msg.equals("bye"))
                    break;
            }
        } catch (IOException e) {
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