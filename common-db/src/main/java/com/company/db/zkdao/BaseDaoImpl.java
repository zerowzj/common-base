package com.company.db.zkdao;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 基础Dao实现
 *
 * @author wangzhj
 */
public abstract class BaseDaoImpl {

    private String zkAddress;

    private CuratorFramework client;

    @PostConstruct
    private void init() {
        client = CuratorFrameworkFactory.newClient(zkAddress, new ExponentialBackoffRetry(1000, 3));
        client.start();
    }

    @PreDestroy
    private void destroy() {
        if (client != null) {
            client.close();
        }
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }
}