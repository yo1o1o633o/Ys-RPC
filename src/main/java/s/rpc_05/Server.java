package s.rpc_05;

import s.entity.User;
import s.service.UserService;
import s.service.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

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
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream dataInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream dataOutputStream = new ObjectOutputStream(outputStream);

            String className = dataInputStream.readUTF();
            String methodName = dataInputStream.readUTF();
            Class[] params = (Class[]) dataInputStream.readObject();
            Object[] args = (Object[]) dataInputStream.readObject();

            Class<?> clazz = Class.forName(className);

            clazz = UserServiceImpl.class;
            Method method = clazz.getMethod(methodName, params);

            Object o = method.invoke(clazz.newInstance(), args);

            dataOutputStream.writeObject(o);
            dataOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
