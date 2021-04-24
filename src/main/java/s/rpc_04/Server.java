package s.rpc_04;

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
            ServerSocket serverSocket = new ServerSocket(12001);
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

            String methodName = dataInputStream.readUTF();
            Class[] params = (Class[]) dataInputStream.readObject();
            Object[] args = (Object[]) dataInputStream.readObject();

            UserService userService = new UserServiceImpl();
            Method method = userService.getClass().getMethod(methodName, params);
            User user = (User) method.invoke(userService, args);

            dataOutputStream.writeObject(user);
            dataOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
