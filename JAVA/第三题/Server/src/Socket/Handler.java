package Socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

public  class Handler implements Runnable {

    static ArrayList<player> arr = new ArrayList<player>();

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public String echo(String msg) {
        return msg + "\nend";
    }

    private void solve(StringBuilder msg) throws IOException {
        PrintWriter pw = getWriter(socket);     //�����
        if (msg.toString().equals("getRanking")) {
            if (arr == null)
                pw.println(echo(""));                  //�������յ�����
            else {
                msg = new StringBuilder();
                for (player i : arr) {
                    msg.append(i.name).append(" ").append(i.score).append("\n");
                }
                System.out.println("�ظ����ݣ�" + msg);
                pw.println(echo(msg.toString()));
            }
        } else {                                             //��������
            String[] t = msg.toString().split(" ");
            int inset = 0;
            for(player i:arr) {
                if(i.name.equals(t[0])) {
                    int a = Integer.parseInt(t[1]);
                    i.score = (Math.max(a, i.score));
                    inset = 1;
                    break;
                }
            }
            if(inset == 0) {
                arr.add(new player(t[0], t[1]));
            }
            arr.sort(Comparator.comparing(o -> o.name));
            pw.println(echo(""));
        }
        System.out.println("�ظ��ɹ�");
    }

    public void run() {
        System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            BufferedReader br = getReader(socket);  //������
            StringBuilder msg;
            while (true) {          //����
                msg = new StringBuilder(br.readLine());
                System.out.println("�ӵ����ݣ�" + msg);
                if (msg.toString().equals("bye")) break;
                solve(msg);
            }
            System.out.println("�Ͽ�");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
