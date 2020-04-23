package Socket;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService executorService;//�̳߳�

    public Server() throws IOException{
        int port = 10010;
        serverSocket=new ServerSocket(port);
        //Runtime��availableProcessor()�������ص�ǰϵͳ��CPU��Ŀ.
        //����CPU�̳߳ش�С
        int POOL_SIZE = 10;
        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()* POOL_SIZE);
        System.out.println("����������");
    }

    public void service(){            //����ͻ���
        while(true){
            Socket socket;
            try {
                //���տͻ�����,ֻҪ�ͻ�����������,�ͻᴥ��accept();�Ӷ���������
                socket=serverSocket.accept();
                executorService.execute(new Handler(socket));
                int threadCount = ((ThreadPoolExecutor)executorService).getActiveCount();
                System.out.println("��ǰ��Ծ�߳�����  " + threadCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}