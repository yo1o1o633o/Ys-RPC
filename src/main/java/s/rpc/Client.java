package s.rpc;

import s.service.UserService;

import java.io.*;
import java.net.Socket;

/**
 * @author shuai.yang
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 10000);

            PrintWriter bufferedWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String method = "findById";
//            Class<?>[] parameterTypes = new Class[2];
            Class clazz = UserService.class;

            bufferedWriter.write(method + "\n");
//            bufferedWriter.write(Arrays.toString(parameterTypes) + "\n");
            bufferedWriter.write(clazz + "\n");
            bufferedWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println("服务端返回数据" + s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
