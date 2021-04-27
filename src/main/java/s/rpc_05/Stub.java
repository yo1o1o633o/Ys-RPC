package s.rpc_05;

import s.entity.User;
import s.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * 动态代理类, 返回指定的动态代理对象
 * */
class Stub {
    static UserService getStub(Class clazz) {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            User user = null;
            try {
                Socket socket = new Socket("127.0.0.1", 12002);

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                // RPC调用的类名
                outStream.writeUTF(clazz.getName());
                // RPC调用的方法名
                outStream.writeUTF(method.getName());
                // RPC调用的参数类型
                outStream.writeObject(method.getParameterTypes());
                // RPC调用的参数
                outStream.writeObject(args);
                outStream.flush();
                // RPC调用的返回值
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
