package com.company.db.service;


import com.company.db.dao.OrderDao;
import com.company.db.dao.OrderEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

/**
 * <p></p>
 *
 * @author wangzhj
 * @time 2016-12-01 11:23
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao = null;

    @Override
    public void add(Long id, String name) {
        OrderEO demoEO = new OrderEO();
        demoEO.setId(id);
        demoEO.setName(name);
        orderDao.insert(demoEO);
    }

    @Override
    public void get(Long id) {
        OrderEO demoEO = orderDao.get(id);
        logger.info(demoEO.getName());
    }

    @Override
    public void find(Long id) {
        OrderEO demoEO = orderDao.get(id);
        logger.info(demoEO.getName());
    }

    @Override
    public void findLt(Long id, boolean f) {
        Map m = TransactionSynchronizationManager.getResourceMap();
       String str = TransactionSynchronizationManager.getCurrentTransactionName();
        TransactionSynchronizationManager.getSynchronizations();
        OrderEO demoEO = orderDao.findLt(id);
        logger.info(demoEO.getName());
    }
}
