package s.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserProxy implements InvocationHandler {
    private Object object;

    public UserProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理, 执行前...");
        System.out.println(method);
        method.invoke(object, args);
        System.out.println("动态代理, 执行后...");
        return null;
    }
}
