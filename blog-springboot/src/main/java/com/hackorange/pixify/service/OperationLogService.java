package com.hackorange.pixify.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hackorange.pixify.dto.OperationLogDTO;
import com.hackorange.pixify.vo.PageResult;
import com.hackorange.pixify.entity.OperationLog;
import com.hackorange.pixify.vo.ConditionVO;

/**
 * 操作日志服务
 *
 * @author yezhiqiu
 * @date 2021/07/29
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
