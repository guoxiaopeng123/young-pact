package com.young.pact.controller.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.result.rest.common.BankAccountResult;
import com.young.pact.api.service.rest.common.BankAccountService;

/**
 * 
* @ClassName: BankAccountController
* @Description: TODO(青春界各部门下银行账号 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:46:29
*
 */
@RestController
@RequestMapping(value = "/bankaccount", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class BankAccountController extends BaseController{
    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog(BankAccountController.class);
    private BankAccountService bankAccountService;
    @RequestMapping(value = "/listBankAccount", method = RequestMethod.GET)
    public ApiResult<List<BankAccountResult>>  listBankAccount(HttpServletRequest request){
            return bankAccountService.listBankAccount();
    }
    public BankAccountService getBankAccountService() {
        return bankAccountService;
    }
    public void setBankAccountService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
}
