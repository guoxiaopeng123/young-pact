package com.young.pact.api.service.rest.declare;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.declare.DeclareParam;
import com.young.pact.api.domain.result.rest.declare.DeclareResult;

/**
 * @描述 : 申报
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午3:34:48
 */
public interface DeclareService {

    /**
     * @Title: saveDeclare
     * @Description: TODO( 保存申报)
     * @author GuoXiaoPeng
     * @param declareParam 申报信息
     * @return 返回结果
     * @throws 异常
      */
     ApiResult<String> saveDeclare(DeclareParam declareParam) ;
     /**
     * @Title: listDeclare
     * @Description: TODO( 分页查询申报列表 )
     * @author GuoXiaoPeng
     * @param declareParam 申报查询条件
     * @return 返回结果
     * @throws 异常
      */
     ApiResult<PageBean<DeclareResult>> listDeclare(DeclareParam declareParam);
     /**
     * @Title: getDeclareByDeclareCode
     * @Description: TODO( 根据申报编码查询申报详情 )
     * @author GuoXiaoPeng
     * @param declareCode 申报编码
     * @return 返回结果
     * @throws 异常
      */
     ApiResult<DeclareResult>  getDeclareByDeclareCode(String declareCode);
     /**
     * @Title: reviewPassRentTurn
     * @Description: TODO( 审核通过申报 )
     * @author GuoXiaoPeng
     * @param param 申报信息
     * @return 返回结果
     * @throws 异常
      */
     ApiResult<Boolean> reviewPassDeclare(DeclareParam param) ;
     /**
     * @Title: reviewDismissalRentTurn
     * @Description: TODO(审核驳回申报)
     * @author GuoXiaoPeng
     * @param param  申报信息
     * @return 返回结果
     * @throws 异常
      */
     ApiResult<Boolean> reviewDismissalDeclare(DeclareParam param) ;
     /**
     * @Title: copyDeclareByDeclareCode
     * @Description: TODO( 复制申报 )
     * @author GuoXiaoPeng
     * @param declareCode 申报编码
     * @return 返回结果
     * @throws 异常
      */
     ApiResult<DeclareResult>  copyDeclareByDeclareCode(String declareCode);
}
