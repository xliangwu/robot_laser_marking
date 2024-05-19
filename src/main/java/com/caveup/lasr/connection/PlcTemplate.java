package com.caveup.lasr.connection;

import com.caveup.lasr.config.AppConfig;
import com.github.s7connector.api.DaveArea;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;

/**
 * @author xw80329
 */
@Component
@Slf4j
public class PlcTemplate {

    @Autowired
    private AppConfig appConfig;

    public void writeInt(int val) {
        S7Connector s7Connector = null;
        try {
            s7Connector = initConnect();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt(val);
            byte[] data = new byte[4];
            buffer.get(data, 0, 4);
            s7Connector.write(DaveArea.DB, 1000, 4, data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(s7Connector);
        }
    }

    private S7Connector initConnect() {
        Assert.notNull(appConfig.getPlcHost(), "Property 'plcHost' should be non-empty");
        Assert.notNull(appConfig.getPlcPort(), "Property 'plcPort' should be non-empty");
        return S7ConnectorFactory
                .buildTCPConnector()
                .withHost(appConfig.getPlcHost())
                .withPort(appConfig.getPlcPort())
                .withTimeout(10000)
                .withRack(0)
                .withSlot(1)
                .build();
    }
}
