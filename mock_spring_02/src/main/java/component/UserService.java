package component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */

@Component
public class UserService {
    @Autowired
    private UserDAO userDAO;
}
