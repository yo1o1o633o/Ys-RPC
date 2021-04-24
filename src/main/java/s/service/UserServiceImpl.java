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

    @Override
    public User save() {
        return new User(100000, "保存", 30);
    }
}
