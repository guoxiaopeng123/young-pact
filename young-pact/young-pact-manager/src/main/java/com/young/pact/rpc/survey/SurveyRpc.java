package com.young.pact.rpc.survey;

import com.young.house.api.domain.result.rpc.survey.SurveyListRpcResult;

/**
 * @描述 : 实勘RPC
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 下午8:05:43
 */
public interface SurveyRpc {
    /**
    * @Title: getSurvey
    * @Description: TODO( 根据实勘编码查询实勘详情 )
    * @author GuoXiaoPeng
    * @param surveyCode 实勘编码
    * @return 实勘详情
    * @throws 异常
     */
    SurveyListRpcResult getSurvey(String surveyCode); 
}
