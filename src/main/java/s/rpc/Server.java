package s.rpc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author shuai.yang
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 创建Socket服务端
            ServerSocket serverSocket = new ServerSocket(10000);
            Socket accept = serverSocket.accept();
            System.out.println("客户端链接" + accept.getInetAddress().getCanonicalHostName());
            // 获取输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            // 读取输入流数据
            String method = bufferedReader.readLine();
            System.out.println("接受到客户端数据: " + method);
            String clazz = bufferedReader.readLine();
            System.out.println("接受到客户端数据: " + clazz);

            Object o = Class.forName("s.service.UserServiceImpl").newInstance();
            Method declaredMethod = o.getClass().getDeclaredMethod(method);

            Object invoke = declaredMethod.invoke(o);

            System.out.println(invoke);
//            // 获取输出流
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
//            // 输出流写入数据
//            bufferedWriter.write(1);
//            bufferedWriter.flush();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
