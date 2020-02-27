package com.dtstack.dtcenter.common.loader.rdbms.mysql;

import com.dtstack.dtcenter.common.loader.rdbms.common.AbsRdbmsClient;
import com.dtstack.dtcenter.common.loader.rdbms.common.ConnFactory;

/**
 * @company: www.dtstack.com
 * @Author ：Nanqi
 * @Date ：Created in 14:00 2020/2/27
 * @Description：MySQL 客户端
 */
public class MysqlClient extends AbsRdbmsClient {
    @Override
    protected ConnFactory getConnFactory() {
        return new MysqlConnFactory();
    }
}
