package s.service;

import s.entity.User;

/**
 * @author shuai.yang
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return new User(id, "Yang", 30);
    }
}
