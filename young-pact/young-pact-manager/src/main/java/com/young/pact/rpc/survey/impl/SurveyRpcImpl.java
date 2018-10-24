package com.young.pact.rpc.survey.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.house.api.domain.result.rpc.survey.SurveyListRpcResult;
import com.young.house.api.service.rpc.survey.SurveyRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.survey.SurveyRpc;

/**
 * @描述 : 实勘
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 下午8:08:05
 */
@Component("surveyRpc")
public class SurveyRpcImpl implements SurveyRpc {
    private static final Log LOG = LogFactory.getLog(SurveyRpcImpl.class);
    private SurveyRpcService surveyRpcService;
    @Override
    public SurveyListRpcResult getSurvey(String surveyCode) {
        ApiResult<SurveyListRpcResult> result = surveyRpcService.getSurvey(surveyCode);
        if (CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())) {
            return result.getData();
        }else{
            LOG.error(ErrorPactConsts.QUERY_SURVEY_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_SURVEY_ERROR);
        }
    }
    public SurveyRpcService getSurveyRpcService() {
        return surveyRpcService;
    }
    public void setSurveyRpcService(SurveyRpcService surveyRpcService) {
        this.surveyRpcService = surveyRpcService;
    }
}
