package Socket;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService executorService;//线程池

    public Server() throws IOException{
        int port = 10010;
        serverSocket=new ServerSocket(port);
        //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        //单个CPU线程池大小
        int POOL_SIZE = 10;
        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()* POOL_SIZE);
        System.out.println("服务器启动");
    }

    public void service(){            //多个客户端
        while(true){
            Socket socket;
            try {
                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
                socket=serverSocket.accept();
                executorService.execute(new Handler(socket));
                int threadCount = ((ThreadPoolExecutor)executorService).getActiveCount();
                System.out.println("当前活跃线程数：  " + threadCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}