package com.caveup.lasr;

import com.caveup.lasr.util.QueryUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class QueryUtilTest {

    @Test
    public void parseTest() {
        Map<String, String> a = QueryUtil.parse("populate%5Bimg%5D=%2A&filters%5Bmaterial_type%5D%5B%24eq%5D=2&pagination%5Bpage%5D=1&pagination%5BpageSize%5D=50");
        System.out.println(a);
    }
}