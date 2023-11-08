package example;

import example.DAO.GoodsDao;
import example.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Karl Rules!
 * 2023/11/7
 * now File Encoding is UTF-8
 */
public class forT {
    @Test
    public void test01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("example_ioc.xml");
        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        Float aFloat = goodsDao.queryPriceById(1);
        System.out.println(aFloat);
    }
    @Test
    public void updateBalance() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("example_ioc.xml");
        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        goodsDao.updateBalance(1, 1.0F);
        System.out.println("减少用户余额成功~");

    }
    @Test
    public void updateAmount() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("example_ioc.xml");
        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        goodsDao.updateAmount(1, 1);
        System.out.println("减少库存成功...");
    }
    @Test
    public void buyGoodsTest() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("example_ioc.xml");
        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoods(1, 1, 1);
    }
    //测试用户购买商品业务 加上了事务
    @Test
    public void buyGoodsByTxTest() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("example_ioc.xml");
        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoodsByTx(1, 1, 1);//这里我们调用的是进行了事务声明的方法
    }
}
