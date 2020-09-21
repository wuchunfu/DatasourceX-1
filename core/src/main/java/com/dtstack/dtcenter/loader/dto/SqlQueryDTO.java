package com.dtstack.dtcenter.loader.dto;

import com.dtstack.dtcenter.loader.dto.filter.Filter;
import com.dtstack.dtcenter.loader.enums.EsCommandType;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @company: www.dtstack.com
 * @Author ：Nanqi
 * @Date ：Created in 14:40 2020/2/26
 * @Description：查询信息
 */
@Data
@Builder
public class SqlQueryDTO {
    /**
     * 查询 SQL
     */
    private String sql;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表名称(正则)
     */
    private String tableNamePattern;

    /**
     * 表类型 部分支持，建议只使用 view 这个字段
     * {@link java.sql.DatabaseMetaData#getTableTypes()}
     */
    private String[] tableTypes;

    /**
     * 字段名称
     */
    private List<String> columns;

    /**
     * 分区字段:值,用于hive分区预览数据、hbase自定义查询
     */
    private Map<String, String> partitionColumns;

    /**
     * 是否需要视图表，默认 false 不过滤
     */
    private Boolean view;

    /**
     * 是否过滤分区字段，默认 false 不过滤
     */
    private Boolean filterPartitionColumns;

    /**
     * 预览条数，默认100
     */
    private Integer previewNum;

    /**
     * 预编译字段
     * todo 修改executeQuery方法为支持预编译
     * time :2020-08-04 15:49:00
     */
    private List<Object> preFields;

    /**
     * executorQuery查询超时时间,单位：秒
     */
    private Integer queryTimeout;

    /**
     * mongodb，executorQuery 分页查询，开始行
     */
    private Integer startRow;

    /**
     * mongodb，executorQuery 分页查询，限制条数
     */
    private Integer limit;

    /**
     * hbase过滤器，用户hbase自定义查询
     */
    private List<Filter> hbaseFilter;

    /**
     * Elasticsearch 命令, 定义es操作类型
     * <b><b/>
     * 1. INSERT  (Default)
     * 2. UPDATE
     * 3. DELETE
     * 4. BULK
     */
    private EsCommandType esCommandType;

    public Boolean getView() {
        if (ArrayUtils.isEmpty(getTableTypes())) {
            return Boolean.TRUE.equals(view);
        }

        return Arrays.stream(getTableTypes()).filter( type -> "VIEW".equalsIgnoreCase(type)).findFirst().isPresent();
    }

    public Integer getPreviewNum() {
        if (this.previewNum == null){
            return 100;
        }
        return previewNum;
    }

    public Boolean getFilterPartitionColumns() {
        return Boolean.TRUE.equals(filterPartitionColumns);
    }


}