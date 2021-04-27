package s.rpc;

import s.service.UserService;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shuai.yang
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 10000);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String method = "findById";
//            Class<?>[] parameterTypes = new Class[2];
            Class clazz = UserService.class;

            bufferedWriter.write(method + "\n");
            bufferedWriter.write(clazz + "\n");
            bufferedWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println("服务端返回数据" + s);

            new ReentrantLock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
