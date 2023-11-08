package component;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 * @Controller 标识该类是一个控制器Controller, 通常这个类是一个Servlet
 */
@Controller
public class UserAction {

    @Resource
    private UserService userService;

    public void sayOk() {
        System.out.println("UserAction 的sayOk()");
        System.out.println("userAction 装配的 userService属性=" + userService);
        userService.hi();
    }
}
