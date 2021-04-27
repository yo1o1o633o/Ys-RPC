package s.rpc_05;

import s.entity.User;
import s.service.UserService;
import s.service.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟服务端
 * @author shuai.yang
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12002);
            while (true) {
                Socket socket = serverSocket.accept();
                process(socket);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void process(Socket socket) {
        try {
            // 获取输入输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream dataOutputStream = new ObjectOutputStream(outputStream);
            // 按顺序获取输入输出流中的数据
            String className = dataInputStream.readUTF();
            String methodName = dataInputStream.readUTF();
            Class[] params = (Class[]) dataInputStream.readObject();
            Object[] args = (Object[]) dataInputStream.readObject();

            Class<?> clazz = Class.forName(className);

            clazz = UserServiceImpl.class;
            // 基于反射机制, 根据传入的类,方法,参数类型进行调用
            Method method = clazz.getMethod(methodName, params);
            Object o = method.invoke(clazz.newInstance(), args);
            // 输出方法的处理结果写入输出流
            dataOutputStream.writeObject(o);
            dataOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
