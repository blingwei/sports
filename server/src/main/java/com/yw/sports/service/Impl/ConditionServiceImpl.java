package com.yw.sports.service.Impl;

import com.yw.sports.bean.responseBean.ConditionResponse;
import com.yw.sports.dao.ConditionMapper;
import com.yw.sports.pojo.Condition;
import com.yw.sports.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */
@Service
public class ConditionServiceImpl implements ConditionService {
    @Autowired
    private ConditionMapper conditionMapper;


    @Override
    public void submitCondition(Condition condition) {
        conditionMapper.insert(condition);
    }

    @Override
    public List<ConditionResponse> getAllConditions() {
        return conditionMapper.selectAllCondition();
    }

    @Override
    public ConditionResponse getConditionById(int id) {
        return conditionMapper.getConditionById(id);
    }
}
