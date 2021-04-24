package s.rpc_03;

import s.entity.User;
import s.service.UserService;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

class Stub {
    static UserService getStub() {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            User user = null;
            try {
                Socket socket = new Socket("127.0.0.1", 12000);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeInt(1);

                socket.getOutputStream().write(byteArrayOutputStream.toByteArray());
                socket.getOutputStream().flush();


                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                int returnId = dataInputStream.readInt();
                String name = dataInputStream.readUTF();

                System.out.println("查询到数据：id" + returnId + ", 名字：" + name);
                user = new User(returnId, name, 20);
                dataOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return user;
        };
        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, invocationHandler);
        return (UserService)o;
    }
}
