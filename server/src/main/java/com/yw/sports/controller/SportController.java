package com.yw.sports.controller;

import com.yw.sports.bean.commanBean.OperateBean;
import com.yw.sports.bean.requestBaen.CreatRecordRequest;
import com.yw.sports.enums.TrendEnum;
import com.yw.sports.pojo.Message;
import com.yw.sports.pojo.Record;
import com.yw.sports.result.Result;
import com.yw.sports.service.MessageService;
import com.yw.sports.service.RecordService;
import com.yw.sports.service.UserService;
import com.yw.sports.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SportController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RecordService recordService;

    @PostMapping("suggest")
    public Result suggest(@NotNull @RequestBody OperateBean operateBean) {
        Message message = new Message();
        message.setSendUserId(userService.getCurrentUser().getId());
        message.setReceiveUserId(operateBean.getId());
        message.setContent(operateBean.getObject() + "");
        messageService.sendMessage(message);
        return ResultFactory.buildSuccessResult(null, null);
    }

    @PostMapping("creatRecord")
    public Result creatRecord(@RequestBody CreatRecordRequest creatRecordRequest) {
        Record record = new Record();
        record.setUserId(userService.getCurrentUser().getId());
        record.setCreateTime(creatRecordRequest.getCreatTime());
        record.setEndTime(creatRecordRequest.getEndTime());
        record.setLine(creatRecordRequest.getLine());
        record.setDistance(creatRecordRequest.getDistance()/1000 + " ");
        record.setCalorie("0");
        recordService.addRecord(record);
        return ResultFactory.buildSuccessResult(null, null);
    }


    @GetMapping("getRecords")
    public Result getRecords(@Param("start") Integer start, @Param("size") Integer size, @Param("id") Integer id) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer userId = userService.getCurrentUser().getId();
        if(id != null){
            userId = id;
        }
        List<Record> records = covertRecords(recordService.getRecordsByUserId(userId, start, size));
        resultMap.put("records", records);
        resultMap.put("nums", recordService.getRecordsNumsByUserId(userId));
        return ResultFactory.buildSuccessResult(null, resultMap);
    }



    public List<Record> covertRecords(@NotNull List<Record> records) {
        for (Record record : records) {
            float cal = Float.parseFloat(record.getCalorie());
            if (cal < (177 * 4.186)) {
                record.setTrend(TrendEnum.FAT.getMessage());
            } else if (cal < (250 * 4.186)) {
                record.setTrend(TrendEnum.NORMAL.getMessage());
            } else if (cal < (480 * 4.186)) {
                record.setTrend(TrendEnum.SLIM.getMessage());
            } else{
                record.setTrend(TrendEnum.OVER.getMessage());
            }
        }
        return records;
    }

}
