package forT;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Karl Rules!
 * 2023/11/7
 * now File Encoding is UTF-8
 */
public class FORt {
    @Test
    public void test01() throws SQLException {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate.xml");
        System.out.println("ioc=" + ioc);
        DataSource bean = ioc.getBean(DataSource.class);
        Connection connection = bean.getConnection();
        System.out.println("connection=" + connection);
        connection.close();

    }
}
