package s.service;

import s.entity.User;

/**
 * @author shuai.yang
 */
public interface UserService {
    User findById(Integer id);

    User save();
}
