package com.dtstack.dtcenter.loader.client.sql;

import com.dtstack.dtcenter.loader.cache.pool.config.PoolConfig;
import com.dtstack.dtcenter.loader.client.ClientCache;
import com.dtstack.dtcenter.loader.client.IClient;
import com.dtstack.dtcenter.loader.client.ITable;
import com.dtstack.dtcenter.loader.dto.SqlQueryDTO;
import com.dtstack.dtcenter.loader.dto.source.LibraSourceDTO;
import com.dtstack.dtcenter.loader.source.DataSourceType;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

/**
 * Libra table测试
 *
 * @author ：wangchuan
 * date：Created in 10:14 上午 2020/12/7
 * company: www.dtstack.com
 */
public class LibraTableTest {

    // 构建client
    private static final ITable client = ClientCache.getTable(DataSourceType.LIBRA.getVal());

    // 构建数据源信息
    private static final LibraSourceDTO source = LibraSourceDTO.builder()
            .url("jdbc:postgresql://172.16.101.246:5432/postgres")
            .username("postgres")
            .password("abc123")
            .schema("public")
            .poolConfig(new PoolConfig())
            .build();

    /**
     * 数据准备
     */
    @BeforeClass
    public static void setUp () {
        IClient client = ClientCache.getClient(DataSourceType.LIBRA.getVal());
        SqlQueryDTO queryDTO = SqlQueryDTO.builder().sql("drop table if exists loader_test_libra_table").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("drop view if exists libra_test_view").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("create table loader_test_libra_table (id int)").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("drop table if exists loader_test_libra_table_2").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("create table loader_test_libra_table_2 (id int)").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("drop table if exists loader_test_libra_table_5").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("create table loader_test_libra_table_5 (id int)").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("insert into loader_test_libra_table_5 values (1),(2),(1),(2),(1),(2),(1),(2),(1),(2),(1),(2)").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
        queryDTO = SqlQueryDTO.builder().sql("create view libra_test_view as select * from loader_test_libra_table_5").build();
        client.executeSqlWithoutResultSet(source, queryDTO);
    }

    /**
     * 重命名表
     */
    @Test
    public void dropTable () {
        ITable client = ClientCache.getTable(DataSourceType.LIBRA.getVal());
        Boolean dropCheck = client.dropTable(source, "loader_test_libra_table");
        Assert.assertTrue(dropCheck);
    }

    /**
     * 重命名表
     */
    @Test
    public void renameTable () {
        client.executeSqlWithoutResultSet(source, "drop table if exists loader_test_libra_table_3");
        Boolean renameCheck = client.renameTable(source, "loader_test_libra_table_2", "loader_test_libra_table_3");
        Assert.assertTrue(renameCheck);
    }

    /**
     * 重命名表
     */
    @Test
    public void alterParamTable () {
        ITable client = ClientCache.getTable(DataSourceType.LIBRA.getVal());
        Map<String, String> params = Maps.newHashMap();
        params.put("comment", "test");
        Boolean alterParamCheck = client.alterTableParams(source, "loader_test_libra_table_5", params);
        Assert.assertTrue(alterParamCheck);
    }

    /**
     * 重命名表
     */
    @Test
    public void getTableSize () {
        ITable tableClient = ClientCache.getTable(DataSourceType.LIBRA.getVal());
        Long tableSize = tableClient.getTableSize(source, "public", "loader_test_libra_table_5");
        System.out.println(tableSize);
        Assert.assertTrue(tableSize != null && tableSize > 0);
    }

    /**
     * 判断表是否是视图 - 是
     */
    @Test
    public void tableIsView () {
        ITable client = ClientCache.getTable(DataSourceType.LIBRA.getVal());
        Boolean check = client.isView(source, null, "libra_test_view");
        Assert.assertTrue(check);
    }

    /**
     * 判断表是否是视图 - 否
     */
    @Test
    public void tableIsNotView () {
        ITable client = ClientCache.getTable(DataSourceType.LIBRA.getVal());
        Boolean check = client.isView(source, null, "wangchuan_test5");
        Assert.assertFalse(check);
    }
}
