package com.caveup.lasr.connection;

import com.caveup.lasr.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

/**
 * @author xw80329
 */
@Component
@Slf4j
public class TcpSocketTemplate {

    private static final String MARK_DONE = "#MAR#";
    private static final String MARK_FAILED = "#FAL#";
    private static final String MARK_ERROR = "#NF#";

    @Autowired
    private AppConfig appConfig;

    public boolean updateMarkingContent(String pictureName) {
        String message = String.format("#SET#%s##", pictureName);
        try (Socket socket = new Socket(appConfig.getMarkingHost(), appConfig.getMarkingPort());
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream()) {
            socket.setTcpNoDelay(true);
            PrintWriter pw = new PrintWriter(os);
            pw.write(message);
            pw.flush();
            log.info("flush message to socket:{}", message);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String responseText;
            while ((responseText = br.readLine()) != null) {
                if (MARK_DONE.equals(responseText)) {
                    break;
                }

                if (MARK_FAILED.equals(responseText)) {
                    break;
                }

                if (MARK_ERROR.equals(responseText)) {
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }
}
