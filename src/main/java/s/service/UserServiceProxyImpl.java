package s.service;

import s.entity.User;

/**
 * 静态代理
 */
public class UserServiceProxyImpl implements UserService {

    private UserService userService = new UserServiceImpl();

    @Override
    public User findById() {
        System.out.println("代理类, 前置操作...");
        User user = userService.findById();
        System.out.println("代理类, 后置操作...");
        return user;
    }
}
