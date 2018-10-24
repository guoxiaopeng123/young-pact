package com.young.pact.rpc.dept.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.sso.api.service.rpc.dept.DeptRpcService;

/**
 * @描述 : 部门Rpc
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月26日 下午3:46:03
 */
@Component("deptRpc")
public class DeptRpcImpl implements DeptRpc {
    private static final Log LOG = LogFactory.getLog(DeptRpcImpl.class);
    private DeptRpcService deptRpcService;
    @Override
    public List<String> listCodeByDeptCode(String deptCode) {
        ApiResult<List<String>> result = deptRpcService.listCodeByDeptCode(deptCode);
        List<String> list = new ArrayList<>();
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
            list = result.getData();
        }else{
            LOG.error(ErrorPactConsts.LIST_DEPT_CODE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_DEPT_CODE_ERROR);
        }
        return list;
    }
    public DeptRpcService getDeptRpcService() {
        return deptRpcService;
    }
    public void setDeptRpcService(DeptRpcService deptRpcService) {
        this.deptRpcService = deptRpcService;
    }
}
