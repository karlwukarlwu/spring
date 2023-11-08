package example.service;

import example.DAO.GoodsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Karl Rules!
 * 2023/11/7
 * now File Encoding is UTF-8
 */
@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;
//  这个是没有事务的
    public void buyGoods(int userId, int goodsId, int amount) {

        //输出购买的相关信息
        System.out.println("用户购买信息 userId=" + userId
                + " goodsId=" + goodsId + " 购买数量=" + amount);

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById(userId);
        //2. 减少用户的余额
        goodsDao.updateBalance(userId, price * amount);
        //3. 减少库存量
        goodsDao.updateAmount(goodsId, amount);

        System.out.println("用户购买成功~");

    }
//    这个是加了事务的
//    为了这个事务生效 要在xml中配置
@Transactional(propagation = Propagation.REQUIRES_NEW)
//@Transactional 加上这个即开始事务 后面的propagation = Propagation.REQUIRES_NEW是事务的传播行为
//什么是事务的传播机制？
//
public void buyGoodsByTx(int userId, int goodsId, int amount) {


    //输出购买的相关信息
    System.out.println("用户购买信息 userId=" + userId
            + " goodsId=" + goodsId + " 购买数量=" + amount);

    //1.得到商品的价格
    Float price = goodsDao.queryPriceById(userId);
    //2. 减少用户的余额
    goodsDao.updateBalance(userId, price * amount);
    //3. 减少库存量
    goodsDao.updateAmount(goodsId, amount);

    System.out.println("用户购买成功~");

}
}
