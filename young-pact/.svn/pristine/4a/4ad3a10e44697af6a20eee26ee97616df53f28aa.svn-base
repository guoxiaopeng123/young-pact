package com.young.pact.api.service.rest.rentbase;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.rentbase.RentBaseParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentcustomer.RentCustomerParam;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;


/**
 * @description : 托出合同
 * @author : guoxiaopeng
 * @date : 2018年8月1日 上午9:50:40
 */
public interface RentBaseService {
    /**
    * @Title: savePactRedis
    * @Description: TODO( 保存合同)
    * @author GuoXiaoPeng
    * @param parm 合同信息
    * @return 缓存的key
    */
    ApiResult<String> savePactRedis(RentPactParam parm);
    /**
    * @Title: getPactRedis
    * @Description: TODO(根据缓存的key查询合同信息 )
    * @author GuoXiaoPeng
    * @param redisKey 缓存的key
    * @return 合同信息
    */
    ApiResult<RentPactResult> getPactRedis(String redisKey);
    /**
    * @Title: saveRentPact
    * @Description: TODO( 保存托出合同 )
    * @author GuoXiaoPeng
    * @param param 托出合同信息
    * @return 返回结果
    */
    ApiResult<String> saveRentPact(RentBaseParam param);
    /**
    * @Title: listRentBase
    * @Description: TODO(分页查询托出合同管理列表 )
    * @author GuoXiaoPeng
    * @param param 查询条件
    * @return 托出合同列表信息
    * @throws
    */
    ApiResult<PageBean<RentBaseResult>> listRentBase(RentBaseParam param);
    /**
    * @Title: listRentBase
    * @Description: TODO( 托出合同详情)
    * @author GuoXiaoPeng
    * @param rentBaseParam 托出合同编码
    * @return 托出合同详情信息
    * @throws
    */
    ApiResult<RentPactResult> getRentBase(RentBaseParam rentBaseParam);
    /**
    * @Title: auditRentBase
    * @Description: TODO(申请审核托出合同 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return 返回结果
    * @throws
    */
    
    @SuppressWarnings("rawtypes")
    ApiResult auditRentBase(RentBaseParam param);
    /**
    * @Title: reviewPassRentBase
    * @Description: TODO(审核通过托出合同 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return 返回结果
    * @throws
    */
    @SuppressWarnings("rawtypes")
    ApiResult reviewPassRentBase(RentBaseParam param);
    /**
    * @Title: reviewDismissalRentBase
    * @Description: TODO(审核驳回托出合同 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return 返回结果
    * @throws
    */
    @SuppressWarnings("rawtypes")
    ApiResult reviewDismissalRentBase(RentBaseParam param);
    /**
    * @Title: updateRentBase
    * @Description: TODO( 修改托出合同)
    * @author GuoXiaoPeng
    * @param pactParam 托出合同信息
    * @return 返回结果
    * @throws
     */
    ApiResult<Boolean> updateRentBase(RentPactParam pactParam);
    /**
    * @Title: updateRentCustomer
    * @Description: TODO(修改托出合同租客 )
    * @author GuoXiaoPeng
    * @param rentCustomerParam 托出合同租客信息
    * @return 返回结果
    * @throws
    */
    @SuppressWarnings("rawtypes")
    ApiResult updateRentCustomer(RentCustomerParam rentCustomerParam);
    
    /**
    * @Title: deleteRentBase
    * @Description: TODO(删除托出合同 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同 编码
    * @return 返回结果
    * @throws
    */
    @SuppressWarnings("rawtypes")
    ApiResult deleteRentBase(RentBaseParam param);
    /**
    * @Title: getPropertyAddress
    * @Description: TODO( 根据托出合同编码查询托出合同物业地址)
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return  物业地址
    * @throws 异常
     */
    ApiResult<RentBaseResult> getPropertyAddress(String rentPactCode);
    
    /**
    * @Title: getPayeeByRentPactCode
    * @Description: TODO( 根据托出合同编号查询租客姓名，收款账户，收款银行，开户行，电话 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编号
    * @return 租客姓名，收款账户，收款银行，开户行，电话 
    * @throws 异常
     */
    ApiResult<FinanceReceiptPayResult> getPayeeByRentPactCode(String rentPactCode);
    /**
    * @Title: setDelayDate
    * @Description: TODO( 设置延期时间 )
    * @author GuoXiaoPeng
    * @param param 托出合同信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<Boolean> setDelayDate(RentBaseParam param);
}
