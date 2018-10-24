package com.young.pact.controller.declare;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.declare.DeclareParam;
import com.young.pact.api.domain.result.rest.declare.DeclareResult;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年10月23日 下午6:25:28
 */
@RestController()
@RequestMapping("/mathCalculator")
public class MathCalculator {

    @RequestMapping(value = "/div", method = RequestMethod.POST)
    public ApiResult<PageBean<DeclareResult>> div(DeclareParam param) throws Exception{
        ApiResult<PageBean<DeclareResult>> result = new ApiResult<>();
        result.setCode("200");
        result.setData(null);
        result.setMessage("success");
        return result;
       
    }
}
