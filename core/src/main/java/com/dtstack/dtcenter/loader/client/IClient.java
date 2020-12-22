package com.dtstack.dtcenter.loader.client;

import com.dtstack.dtcenter.loader.IDownloader;
import com.dtstack.dtcenter.loader.dto.ColumnMetaDTO;
import com.dtstack.dtcenter.loader.dto.SqlQueryDTO;
import com.dtstack.dtcenter.loader.dto.Table;
import com.dtstack.dtcenter.loader.dto.source.ISourceDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @company: www.dtstack.com
 * @Author ：Nanqi
 * @Date ：Created in 09:27 2020/1/13
 * @Description：客户端接口
 */
public interface IClient<T> {
    /**
     * 获取 连接
     *
     * @param source
     * @return
     * @throws Exception
     */
    Connection getCon(ISourceDTO source);

    /**
     * 校验 连接
     *
     * @param source
     * @return
     * @throws Exception
     */
    Boolean testCon(ISourceDTO source);

    /**
     * 执行查询
     *
     * @param source
     * @param queryDTO 必填项 sql
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> executeQuery(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 执行查询，无需结果集
     *
     * @param source
     * @param queryDTO 必填项 sql
     * @return
     * @throws Exception
     */
    Boolean executeSqlWithoutResultSet(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 返回所有的表字段名称
     * 是否视图表，默认不过滤
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    List<String> getTableList(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 根据schema/db获取表
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    List<String> getTableListBySchema(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 返回字段 Java 类的标准名称
     * 字段名若不填则默认全部
     *
     * @param source
     * @param queryDTO 必填项 表名
     * @return
     * @throws Exception
     */
    List<String> getColumnClassInfo(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取表字段属性
     * 字段名若不填则默认全部, 是否过滤分区字段 不填默认不过滤
     *
     * @param source
     * @param queryDTO 必填项 表名
     * @return
     * @throws Exception
     */
    List<ColumnMetaDTO> getColumnMetaData(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 根据自定义sql获取表字段属性
     *
     * @param source
     * @param queryDTO 必填项 sql
     * @return
     * @throws Exception
     */
    List<ColumnMetaDTO> getColumnMetaDataWithSql(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取flinkSql字段名称
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    List<ColumnMetaDTO> getFlinkColumnMetaData(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取表注释
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    String getTableMetaComment(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取预览数据
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    List<List<Object>> getPreview(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取对应的downloader
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    IDownloader getDownloader(ISourceDTO source, SqlQueryDTO queryDTO) throws Exception;

    /**
     * 获取所有的 DB
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    List<String> getAllDatabases(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取建表语句
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    String getCreateTableSql(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取分区字段
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    List<ColumnMetaDTO> getPartitionColumn(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取表信息
     *
     * @param source
     * @param queryDTO
     * @return
     * @throws Exception
     */
    Table getTable(ISourceDTO source, SqlQueryDTO queryDTO);

    /**
     * 获取当前使用的数据库
     *
     * @param source 数据源信息
     * @return 当前使用的数据库
     * @throws Exception
     */
    String getCurrentDatabase(ISourceDTO source);

    /**
     * 创建数据库
     *
     * @param source 数据源信息
     * @param dbName 需要创建的数据库
     * @param comment 库注释信息
     * @return 创建结果
     * @throws Exception
     */
    Boolean createDatabase(ISourceDTO source, String dbName, String comment);

    /**
     * 判断数据库是否存在
     *
     * @param source 数据源信息
     * @param dbName 数据库名称
     * @return 判断结果
     * @throws Exception
     */
    Boolean isDatabaseExists(ISourceDTO source, String dbName) ;

    /**
     * 判断该数据库中是否存在该表
     *
     * @param source 数据源信息
     * @param tableName 表名
     * @param dbName 库名
     * @return 判断结果
     * @throws Exception
     */
    Boolean isTableExistsInDatabase(ISourceDTO source, String tableName, String dbName);

}
