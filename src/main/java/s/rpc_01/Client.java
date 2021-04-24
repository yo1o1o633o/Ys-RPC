package s.rpc_01;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12000);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(1);

            socket.getOutputStream().write(byteArrayOutputStream.toByteArray());
            socket.getOutputStream().flush();


            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            int id = dataInputStream.readInt();
            String name = dataInputStream.readUTF();

            System.out.println("查询到数据：id" + id + ", 名字：" + name);

            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
