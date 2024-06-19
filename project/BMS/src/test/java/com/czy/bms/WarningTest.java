package com.czy.bms;

import com.czy.bms.request.WariningReq;
import com.czy.bms.service.WarningService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WarningTest {

    @Resource
    private WarningService warningService;

    @Test
    public void warningTest() {
        List<WariningReq> list = new ArrayList<>();
        WariningReq req1 = new WariningReq();
        WariningReq req2 = new WariningReq();
        WariningReq req3 = new WariningReq();
        req1.setCarId((short) 1);
        req1.setWarningId(String.valueOf(1));
        req1.setSignal("{\"Mx\":12.0,\"Mi\":0.6}");
        req2.setCarId((short) 2);
        req2.setWarningId(String.valueOf(2));
        req2.setSignal("{\"Ix\":12.0,\"Ii\":11.7}");
        req3.setCarId((short) 3);
        req2.setWarningId(null);
        req3.setSignal("{\"Mx\":11.0,\"Mi\":9.6,\"Ix\":12.0,\"Ii\":11.7}");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        System.out.println(warningService.getWarnings(list));
    }

}
