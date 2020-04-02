package com.yw.sports.service;

import com.yw.sports.bean.responseBean.ConditionResponse;
import com.yw.sports.pojo.Condition;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */
@Service
public interface ConditionService {
    void submitCondition(Condition condition);

    List<ConditionResponse> getAllConditions();

    ConditionResponse getConditionById(int id);
}
