package com.young.pact.dao.financereceiptpay;

import java.util.List;
import java.util.Map;

import com.young.common.exception.DaoException;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;

/**
 * 
* @ClassName: FinanceReceiptPayDao
* @Description: TODO( 收支)
* @author HeZeMin
* @date 2018年8月2日 下午10:03:26
*
 */
public interface FinanceReceiptPayDao {
    /**
     * 
    * @Title: saveFinanceReceiptPay
    * @Description: TODO( 批量保存收支)
    * @author HeZeMin
    * @param financeReceiptPayDOs   收支信息集合
    * @return   返回插入条数
    * @throws DaoException;
    * @throws   异常
     */
    int saveFinanceReceiptPay(List<FinanceReceiptPayDO> financeReceiptPayDOs) throws DaoException;
    /**
     * 
     * @Title: saveFinanceReceiptPay
     * @Description: TODO( 保存收支)
     * @author HeZeMin
     * @param financeReceiptPayDO   收支信息
     * @return   返回插入条数
     * @throws DaoException;
     * @throws   异常
     */
    Long saveReceiptPay(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException;
    /**
     * 
    * @Title: removeFinanceReceiptPay
    * @Description: TODO( 根据合同编码删除收支信息--逻辑删除)
    * @author HeZeMin
    * @param financeReceiptPayDO   合同编码
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int removeFinanceReceiptPay(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException;
    /**
     * 
    * @Title: listFinanceReceiptPay
    * @Description: TODO( 根据合同编码查询收支集合)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回收支信息集合
    * @throws DaoException;
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listFinanceReceiptPay(String pactCode) throws DaoException;
    
    /**
     * 
    * @Title: getFinanceReceiptPay
    * @Description: TODO( 根据合同编码获取收款人信息)
    * @author GuoXiaoPeng
    * @param financeReceiptPayQuery 查询条件
    * @return 收款人信息
    * @throws DaoException;
    * @throws 异常
     */
    FinanceReceiptPayVO getPayeeReceiptPayByPactCode(FinanceReceiptPayQuery financeReceiptPayQuery)throws DaoException;
    /**
     * 
    * @Title: removeNotFinanceReceiptPay
    * @Description: TODO( 删除 不包含 收支集合数据 的收支！！--逻辑删除)
    * @author HeZeMin
    * @param financeReceiptPayList  收支集合
    * @param pactCode   合同编码
    * @param modifiedName   修改人pin
    * @param ip   IP地址
    * @return   返回删除结果
    * @throws DaoException;
    * @throws   异常
     */
    int removeNotFinanceReceiptPay(Map<String, Object> map) throws DaoException;
    /**
     * 
    * @Title: updateFinanceReceiptPay
    * @Description: TODO( 批量修改收支)
    * @author HeZeMin
    * @param financeReceiptPayDOs   收支集合
    * @return   返回修改结果
    * @throws DaoException;
    * @throws   异常
     */
    int updateFinanceReceiptPay(List<FinanceReceiptPayDO> financeReceiptPayDOs) throws DaoException;
    /**
     * 
     * @Title: countAllFinanceReceiptPay
     * @Description: TODO( 收支管理列表-全部,分页总个数)
     * @author HeZeMin
     * @param query  查询条件
     * @return   返回收支列表
     * @throws DaoException;
     * @throws   异常
     */
    int countAllFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: listAllFinanceReceiptPay
    * @Description: TODO( 收支管理列表-全部)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
    * @throws DaoException;
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listAllFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
     * @Title: countReceivFinanceReceiptPay
     * @Description: TODO( 收支管理列表-应收、应支,分页总个数)
     * @author HeZeMin
     * @param query  查询条件
     * @return   返回收支列表
     * @throws DaoException;
     * @throws   异常
     */
    int countReceivFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: listReceivFinanceReceiptPay
    * @Description: TODO( 收支管理列表-应收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listReceivFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
     * @Title: countNetFinanceReceiptPay
     * @Description: TODO( 收支管理列表-实收、应支,分页总个数)
     * @author HeZeMin
     * @param query  查询条件
     * @return   返回收支列表
     * @throws   异常
     */
    int countNetFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 收支管理列表-实收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listNetFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 根据应收、应支id查询审核通过的实收、实支)
    * @author HeZeMin
    * @param pids   应收、应支 id集合
    * @return   返回实收、实支 金额
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listSumReceiptPay(List<Long> pids) throws DaoException;
    /**
     * 
    * @Title: updateValidByCode
    * @Description: TODO( 根据合同编码修改收支生效状态为已生效，合同审核通过时调用)
    * @author HeZeMin
    * @param financeReceiptPayDO    合同编码
    * @return   返回修改结果
    * @throws   异常
     */
    int updateValidByCode(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException;
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 根据应收id查询应收详情)
    * @author HeZeMin
    * @param id 应收id
    * @return   返回应收详情
    * @throws   异常
     */
    FinanceReceiptPayVO getAnswerCollectDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 根据实收id查询实收详情)
    * @author HeZeMin
    * @param id 实收id
    * @return   返回实收详情
    * @throws   异常
     */
    FinanceReceiptPayVO getRealCollectDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: listRealCollectByPid
    * @Description: TODO( 根据应收id查询实收集合)
    * @author HeZeMin
    * @param pid    应收id
    * @return   返回实收集合
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listRealCollectByPid(Long pid) throws DaoException;
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 根据应支id查询应支详情)
    * @author HeZeMin
    * @param id 应支id
    * @return   返回应支详情
    * @throws   异常
     */
    FinanceReceiptPayVO getAnswerExpendDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 根据实支id查询实支详情)
    * @author HeZeMin
    * @param id 实支id
    * @return   返回实支详情
    * @throws   异常
     */
    FinanceReceiptPayVO getRealExpendDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: listRealExpendByPid
    * @Description: TODO( 根据应支id查询实支集合)
    * @author HeZeMin
    * @param pid 应支id
    * @return   返回实支集合
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listRealExpendByPid(Long pid) throws DaoException;
    
    /**
     * 
     * @Title: countReceivFinanceReceiptPay
     * @Description: TODO( 合同详情-应收、应支,分页总个数)
     * @author HeZeMin
     * @param query  查询条件
     * @return   返回收支列表
     * @throws   异常
     */
    int countReceivReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: listReceivFinanceReceiptPay
    * @Description: TODO( 合同详情-应收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listReceivReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
     * @Title: countNetFinanceReceiptPay
     * @Description: TODO( 合同详情-全部、实收、应支,分页总个数)
     * @author HeZeMin
     * @param query  查询条件
     * @return   返回收支列表
     * @throws   异常
     */
    int countNetReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 合同详情-全部、实收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
    * @throws   异常
     */
    List<FinanceReceiptPayVO> listNetReceiptPay(FinanceReceiptPayQuery query) throws DaoException;
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 合同详情-根据应收id查询应收详情)
    * @author HeZeMin
    * @param id 应收id
    * @return   返回应收详情
    * @throws   异常
     */
    FinanceReceiptPayVO getPactAnswerCollectDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 合同详情-根据实收id查询实收详情)
    * @author HeZeMin
    * @param id 实收id
    * @return   返回实收详情
    * @throws   异常
     */
    FinanceReceiptPayVO getPactRealCollectDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 合同详情-根据应支id查询应支详情)
    * @author HeZeMin
    * @param id 应支id
    * @return   返回应支详情
    * @throws   异常
     */
    FinanceReceiptPayVO getPactAnswerExpendDetail(Long id) throws DaoException;
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 合同详情-根据实支id查询实支详情)
    * @author HeZeMin
    * @param id 实支id
    * @return   返回实支详情
    * @throws   异常
     */
    FinanceReceiptPayVO getPactRealExpendDetail(Long id) throws DaoException;
    /**
    * @Title: listReceiptPayByDate
    * @Description: TODO( 查询某个托出合同的收支，并且收支时间大于转租时间的收支集合)
    * @author GuoXiaoPeng
    * @param financeReceiptPayQuery 合同编码+收支时间
    * @return 收支集合
    * @throws 异常
     */
    List<FinanceReceiptPayVO> listReceiptPayByDate(FinanceReceiptPayQuery financeReceiptPayQuery)throws DaoException;
    /**
    * @Title: removeOldPactFinanceReceiptPay
    * @Description: TODO(删除某个托出合同的收支，并且收支时间大于转租时间的收支 )
    * @author GuoXiaoPeng
    * @param oldFinanceReceiptPayDO 托出合同编码+收支时间
    * @return 记录数
    * @throws 异常
     */
    int removeOldPactFinanceReceiptPay(FinanceReceiptPayDO oldFinanceReceiptPayDO) throws DaoException;
    /**
     * 
    * @Title: updateCostState
    * @Description: TODO( 修改审核状态)
    * @author HeZeMin
    * @param oldFinanceReceiptPayDO 状态信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updateCostState(FinanceReceiptPayDO oldFinanceReceiptPayDO) throws DaoException;
    /**
     * 
    * @Title: getRealAmountByPid
    * @Description: TODO( 根据应收、应支id 查询 实收、实支总和)
    * @author HeZeMin
    * @param pid    应收、应支id
    * @return   返回实收、实支总和
    * @throws   异常
     */
    double getRealAmountByPid(Long pid) throws DaoException;
    /**
     * 
    * @Title: updateFinancePayById
    * @Description: TODO( 根据收支id修改收支信息)
    * @author HeZeMin
    * @param financeReceiptPayDO    收支信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updateFinancePayById(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException;
    
    /**
     * 
    * @Title: delFinanceReceiptPay
    * @Description: 删除某个合同状态为未收或未支并且时间大于合同截止时间的收支
    * @author LiuYuChi
    * @param oldFinanceReceiptPayDO
    * @return 数
    * @throws 异常
     */
    int delFinanceReceiptPay(FinanceReceiptPayDO oldFinanceReceiptPayDO) throws DaoException;
    
    /**
     * 
    * @Title: listFinanceReceiptPayDO
    * @Description: 查询收支集合
    * @author LiuYuChi
    * @param financeReceiptPayQuery
    * @return
    * @throws DaoException
    * @throws
     */
    List<FinanceReceiptPayVO> listFinanceReceiptPayDO(FinanceReceiptPayQuery financeReceiptPayQuery)throws DaoException;
    /**
     * @Title: getPermissions
     * @Description: TODO( 查看登录人有没有某个收支 的权限)
     * @author GuoXiaoPeng
     * @param query 收支id
     * @return 有权限返回true否则false
     * @throws DaoException
     * @throws 异常
      */
    FinanceReceiptPayVO getPermissions(FinanceReceiptPayQuery query)throws DaoException;
    /**
     * @Title: listFinanceReceiptPayByPactCode
     * @Description: TODO( 根据合同编码查询收支集合 )
     * @author GuoXiaoPeng
     * @param financeReceiptPayQuery 查询条件
     * @return  收支集合
     * @throws DaoException
     * @throws 异常
      */
    List<FinanceReceiptPayVO> listFinanceReceiptPayByPactCode(FinanceReceiptPayQuery financeReceiptPayQuery)throws DaoException;
    /**
     * 
    * @Title: countRecPayAmount
    * @Description: TODO( 根据条件查询收支费用 )
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 收支费用 
    * @throws 异常
     */
    Double countRecPayAmount(FinanceReceiptPayQuery query)throws DaoException;
    /**
     * 
    * @Title: getLastRecPay
    * @Description: TODO(查询这个应收的产生的最后一条实收或实支的记录 )
    * @author GuoXiaoPeng
    * @param lastRecPayQuery 查询条件
    * @return 收支费用 
    * @throws DaoException
    * @throws  异常
     */
    FinanceReceiptPayVO getLastRecPay(FinanceReceiptPayQuery lastRecPayQuery)throws DaoException;
}
