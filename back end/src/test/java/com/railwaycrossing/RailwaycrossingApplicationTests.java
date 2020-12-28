package com.railwaycrossing;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.controller.BalusterController;
import com.railwaycrossing.controller.InstructionController;
import com.railwaycrossing.entity.Instruction;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class RailwaycrossingApplicationTests {

    @Resource
    BalusterService balusterService;

    @Resource
    BalusterController balusterController;

    @Resource
    CrossingService crossingService;

    @Resource
    InstructionService instructionService;

    @Resource
    InstructionController instructionController;

    @Resource
    LocomotiveService locomotiveService;

    @Resource
    RailwayBlinkerService railwayBlinkerService;

    @Resource
    RoadBlinkerService roadBlinkerService;

    @Resource
    UserService userService;

    @Test
    void testBaluster() throws UpdateException {
        JSONObject message = new JSONObject();
        //message.put("page",2);
        //message.put("limit",5);
        balusterService.updateStatusByCrossingMode(2,false);
        //System.out.println(jsonObject);
    }

    @Test
    void testInstruct() throws Exception {
        JSONObject message = new JSONObject();
        message.put("instructionId",1);
        //message.put("crossingId",1);
        //message.put("locomotiveId",1);
        message.put("instructionContent", "允许通过");
        //message.put("instructionTime",new Date());
        //message.put("passTime",10);
        //message.put("valid",1);
        JSONObject jsonObject = instructionController.listByCondition(message);
        System.out.println(jsonObject);
    }

    @Test
    void testBaluster2() {
        JSONObject message = new JSONObject();
        message.put("crossingId", 1);
        balusterController.queryPageByCondition(message);
    }

}
