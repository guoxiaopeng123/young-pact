package com.young.pact.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.result.rest.common.BankAccountResult;
import com.young.pact.api.service.rest.common.BankAccountService;
import com.young.pact.common.util.BankAccountUtil;

/**
 * @描述 : 青春界各部门下银行账号
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月26日 下午2:02:07
 */
@Service("bankAccountService")
public class BankAccountServiceImpl implements BankAccountService{
    private static final Log LOG = LogFactory.getLog(BankAccountServiceImpl.class); 
    private BankAccountUtil bankAccountUtil;
    @Override
    public ApiResult<List<BankAccountResult>>  listBankAccount() {
        ApiResult<List<BankAccountResult>> result =new ApiResult<>();
        try {
            List<BankAccountResult> banks = new ArrayList<>();
            if(null!=bankAccountUtil.getBanks()){
                BeanUtils.copyListProperties(bankAccountUtil.getBanks(), banks, BankAccountResult.class);
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(banks);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    public BankAccountUtil getBankAccountUtil() {
        return bankAccountUtil;
    }
    public void setBankAccountUtil(BankAccountUtil bankAccountUtil) {
        this.bankAccountUtil = bankAccountUtil;
    }
}
