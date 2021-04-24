package s.rpc_04;

import s.entity.User;
import s.service.UserService;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.Socket;

class Stub {
    static UserService getStub() {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            User user = null;
            try {
                Socket socket = new Socket("127.0.0.1", 12001);

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outStream.writeUTF(method.getName());
                outStream.writeObject(method.getParameterTypes());
                outStream.writeObject(args);
                outStream.flush();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                user = (User) inputStream.readObject();

                System.out.println("查询到数据：" + user);
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
