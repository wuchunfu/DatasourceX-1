package com.dtstack.dtcenter.loader.client.sql;

import com.dtstack.dtcenter.common.enums.DataSourceClientType;
import com.dtstack.dtcenter.common.exception.DtCenterDefException;
import com.dtstack.dtcenter.loader.client.AbsClientCache;
import com.dtstack.dtcenter.loader.client.IClient;
import com.dtstack.dtcenter.loader.dto.SourceDTO;
import com.dtstack.dtcenter.loader.enums.ClientType;
import org.junit.Test;

/**
 * @company: www.dtstack.com
 * @Author ：Nanqi
 * @Date ：Created in 20:02 2020/5/21
 * @Description：FTP 测试
 */
public class FtpTest {
    private static final AbsClientCache clientCache = ClientType.DATA_SOURCE_CLIENT.getClientCache();
    SourceDTO source = SourceDTO.builder()
            .url("kudu1")
            .hostPort("22")
            .username("root")
            .password("abc123")
            .protocol("sftp")
            .build();

    @Test
    public void testCon() throws Exception {
        IClient client = clientCache.getClient(DataSourceClientType.FTP.getPluginName());
        Boolean isConnected = client.testCon(source);
        if (Boolean.FALSE.equals(isConnected)) {
            throw new DtCenterDefException("连接异常");
        }
    }
}
