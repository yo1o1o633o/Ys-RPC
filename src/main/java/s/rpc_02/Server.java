package s.rpc_02;

import s.entity.User;
import s.service.UserServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12000);
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
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            int id = dataInputStream.readInt();
            UserServiceImpl userService = new UserServiceImpl();
            User user = userService.findById(id);
            dataOutputStream.writeInt(user.getId());
            dataOutputStream.writeUTF(user.getName());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
