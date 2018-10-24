package com.young.pact.api.service.rest.common;

import java.util.List;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.result.rest.common.BankAccountResult;

/**
 * @描述 : 青春界各部门下银行账号
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月26日 下午1:59:37
 */
public interface BankAccountService {
    
    /**
    * @Title: listBankAccount
    * @Description: TODO( 获取青春界各部门下银行账号 )
    * @author GuoXiaoPeng
    * @return 银行账号集合
    * @throws 异常
     */
    ApiResult<List<BankAccountResult>> listBankAccount();
}
