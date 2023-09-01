package service.implement;

import com.common.HelloService;
import com.common.UserService;
import domain.User;

/**
 * @Date 2023/8/30 13:27
 * @Author XiaoHu
 * @Description
 **/
public class UserServiceImpl implements  UserService {

    @Override
    public String getUser(User user) {
        return user.toString();
    }
}
