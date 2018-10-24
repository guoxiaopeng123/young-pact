package com.young.pact.rpc.dictionary;

import java.util.List;
import java.util.Map;

import com.young.system.api.domain.result.rpc.DictionaryResult;

/**
 * @描述 : 数据字典
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月20日 下午8:30:03
 */
public interface DictionaryRpc {
    /**
    * @Title: listDictionary
    * @Description: TODO( 根据类型code集合查询数据字典 )
    * @author GuoXiaoPeng
    * @param typecodeList 类型code集合
    * @return 数据字典
     */
    Map<String, List<DictionaryResult>> listDictionary(List<String> typecodeList) ;
    /**
    * @Title: getDictionaryResult
    * @Description: TODO( )
    * @author GuoXiaoPeng
    * @param key
    * @param value
    * @param map
    * @return
    * @throws
     */
    DictionaryResult getDictionaryResult(String key,String value,Map<String, List<DictionaryResult>> map);
}
