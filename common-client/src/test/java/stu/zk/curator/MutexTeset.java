package stu.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhj on 2017/2/24.
 */
public class MutexTeset {

    public static void main(String[] args) {
        String servers = "127.0.0.1:2181";
        CuratorFramework curator = CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(10000, 3)).connectString(servers).build();
        curator.start();
        final InterProcessMutex lock = new InterProcessMutex(curator, "/global_lock");

        Executor pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i ++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        if(!lock.acquire(3, TimeUnit.SECONDS)){
                            System.out.println(Thread.currentThread().getName()+"失败了！！！");
                            return;
                        }
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally{
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }


    }
}
