package s.controller;

import s.proxy.UserProxy;
import s.service.UserService;
import s.service.UserServiceImpl;
import s.service.UserServiceProxyImpl;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        UserService userServiceProxy = new UserServiceProxyImpl();
        userServiceProxy.findById(1);

        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserService userService = new UserServiceImpl();
        UserProxy userProxy = new UserProxy(userService);
        UserService service = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), userProxy);
        service.findById(1);
    }
}
