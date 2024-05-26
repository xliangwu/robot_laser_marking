package com.caveup.lasr.connection;

import com.caveup.lasr.config.AppConfig;
import com.github.xingshuangs.iot.protocol.s7.enums.EPlcType;
import com.github.xingshuangs.iot.protocol.s7.service.S7PLC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author xw80329
 */
@Component
@Slf4j
public class PlcTemplate implements InitializingBean {

    @Autowired
    private AppConfig appConfig;

    public void writeInt(String address, int val) {
        try {
            S7PLC s7Plc = new S7PLC(EPlcType.S1200, appConfig.getPlcHost());
            s7Plc.setEnableReconnect(true);
            s7Plc.setReceiveTimeout(1000);
            s7Plc.writeInt16(address, (short) val);
            int actualVal = s7Plc.readInt16(address);
            s7Plc.close();
            log.info("finish to write db:{},data:{},actual:{}", address, val, actualVal);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void writeInt(String a1, int val, String a2, int v2, String a3, int v3) {
        try {
            S7PLC s7Plc = new S7PLC(EPlcType.S1200, appConfig.getPlcHost());
            s7Plc.setEnableReconnect(true);
            s7Plc.setReceiveTimeout(1000);
            s7Plc.writeInt16(a1, (short) val);
            s7Plc.writeInt16(a2, (short) v2);
            s7Plc.writeInt16(a3, (short) v3);
            int actualA1 = s7Plc.readInt16(a1);
            int actualA2 = s7Plc.readInt16(a2);
            int actualA3 = s7Plc.readInt16(a3);

            s7Plc.close();
            log.info("finish to write db:{},data:{},actual:{}", a1, val, actualA1);
            log.info("finish to write db:{},data:{},actual:{}", a2, v2, actualA2);
            log.info("finish to write db:{},data:{},actual:{}", a3, v3, actualA3);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public int readInt(String address) {
        try {
            S7PLC s7Plc = new S7PLC(EPlcType.S1200, appConfig.getPlcHost());
            s7Plc.setEnableReconnect(true);
            s7Plc.setReceiveTimeout(1000);
            int status = s7Plc.readInt16(address);
            s7Plc.close();
            log.info("read data from address:{},data:{}", address, status);
            return status;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return -1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(appConfig.getPlcHost(), "Property 'plcHost' should be non-empty");
        Assert.notNull(appConfig.getPlcPort(), "Property 'plcPort' should be non-empty");
    }
}
