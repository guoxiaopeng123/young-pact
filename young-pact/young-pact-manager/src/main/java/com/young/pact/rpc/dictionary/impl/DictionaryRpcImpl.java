package com.young.pact.rpc.dictionary.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.dictionary.DictionaryRpc;
import com.young.system.api.domain.result.rpc.DictionaryResult;
import com.young.system.api.service.rpc.DictionaryRpcService;

/**
 * @描述 : 数据字典
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月20日 下午8:34:08
 */
@Component("dictionaryRpc")
public class DictionaryRpcImpl implements DictionaryRpc{
    private static final Log LOG = LogFactory.getLog(DictionaryRpcImpl.class);
    private DictionaryRpcService dictionaryRpcService;
    @Override
    public Map<String, List<DictionaryResult>> listDictionary(List<String> typecodeList) {
        Map<String, List<DictionaryResult>> map = new HashMap<String, List<DictionaryResult>>();
        ApiResult<Map<String, List<DictionaryResult>>> apiResult = dictionaryRpcService.listDictionary(typecodeList);
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(apiResult.getCode())){
            map = apiResult.getData();
            return map;
        }else{
            LOG.error(ErrorPactConsts.QUERY_DICTIONARY_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_DICTIONARY_ERROR);
        }
    }

    @Override
    public DictionaryResult getDictionaryResult(String key, String value, Map<String, List<DictionaryResult>> map) {
        DictionaryResult dictionaryResult=new DictionaryResult();
        List<DictionaryResult> list=map.get(key);
        if(null!=list&&list.size()>0){
            for(DictionaryResult dict:list){
                if(value.equals(dict.getValue())){
                    dictionaryResult=dict;
                }
            }
        }
        return dictionaryResult;
    }

    public DictionaryRpcService getDictionaryRpcService() {
        return dictionaryRpcService;
    }

    public void setDictionaryRpcService(DictionaryRpcService dictionaryRpcService) {
        this.dictionaryRpcService = dictionaryRpcService;
    }
}
