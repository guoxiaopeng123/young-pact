package com.young.pact.manager.rentturn.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.common.util.DateUtil;
import com.young.customer.api.domain.param.rpc.DemandRpcParam;
import com.young.customer.api.service.rpc.DemandRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.dao.commoncostrule.CommonCostRuleDao;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.dao.commontradingrecord.CommonTradingRecordDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.rentbase.RentBaseDao;
import com.young.pact.dao.rentcommonowner.CommonOwnerDao;
import com.young.pact.dao.rentcustomer.RentCustomerDao;
import com.young.pact.dao.rentroom.RentRoomDao;
import com.young.pact.dao.rentturn.RentTurnDao;
import com.young.pact.dao.rentturncost.RentTurnCostDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commongoods.CommonGoodsPicVO;
import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicVO;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.domain.rentturn.RentTurnDO;
import com.young.pact.domain.rentturn.RentTurnProtocolDO;
import com.young.pact.domain.rentturn.RentTurnProtocolVO;
import com.young.pact.domain.rentturn.RentTurnQuery;
import com.young.pact.domain.rentturn.RentTurnVO;
import com.young.pact.domain.rentturncost.RentTurnCostDO;
import com.young.pact.domain.rentturncost.RentTurnCostVO;
import com.young.pact.manager.rentturn.RentTurnManager;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午9:01:33
 */
@Component("rentTurnManager")
public class RentTurnManagerImpl implements RentTurnManager {

    private static Log LOG = LogFactoryImpl.getLog(RentTurnManagerImpl.class);
    private RentTurnDao rentTurnDao;
    private RedisClient redisClient;
    private PlatformTransactionManager transactionManager;
    private RentBaseDao rentBaseDao;
    private CommonBelongerDao commonBelongerDao;
    private CommonCostRuleDao commonCostRuleDao;
    private CommonGoodsDao commonGoodsDao;
    private CommonGoodsPicDao commonGoodsPicDao;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonMeterReadPicDao commonMeterReadPicDao;
    private CommonPicDao commonPicDao;
    private FinanceReceiptPayDao financeReceiptPayDao;
    private RentRoomDao rentRoomDao;
    private RentCustomerDao rentCustomerDao;
    private CommonOwnerDao commonOwnerDao;
    private RentTurnCostDao rentTurnCostDao;
    private DemandRpcService demandRpcService;
    private CommonTradingRecordDao commonTradingRecordDao;
    @Override
    public void save(RentTurnProtocolDO rentTurnProtocolDO, String redisKey) {
        try {
            redisClient.setObject(redisKey, RedisConsts.RENT_TIME, rentTurnProtocolDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }

    @Override
    public RentTurnProtocolVO getRentTurnProtocolRedis(String redisKey) {
        RentTurnProtocolVO rentTurnProtocolVO = new RentTurnProtocolVO();
        try {
            if(redisClient.exists(redisKey)){
                RentTurnProtocolDO rentTurnProtocolDO = redisClient.getObject(redisKey);
                //转租信息
                RentTurnVO rentTurnVO = new RentTurnVO();
                BeanUtils.copyProperties(rentTurnProtocolDO.getRentTurnDO(),rentTurnVO );
                rentTurnProtocolVO.setRentTurnVO(rentTurnVO);
                //收支(集合)
                List<FinanceReceiptPayVO> financeReceiptPayVOs = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolDO.getFinanceReceiptPayDOs(), financeReceiptPayVOs, FinanceReceiptPayVO.class);
                rentTurnProtocolVO.setFinanceReceiptPayVOs(financeReceiptPayVOs);
                //转让费用(集合)
                List<RentTurnCostVO> rentTurnCostVOs = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolDO.getRentTurnCostDOs(), rentTurnCostVOs, RentTurnCostVO.class);
                rentTurnProtocolVO.setRentTurnCostVOs(rentTurnCostVOs);
                //抄表信息(集合)
                List<CommonMeterReadVO> commonMeterReadVOs = new ArrayList<>();
                List<CommonMeterReadDO> commonMeterReadDOs = rentTurnProtocolDO.getMeterReadList();
                for(CommonMeterReadDO commonMeterReadDO: commonMeterReadDOs){
                    CommonMeterReadVO commonMeterReadVO = new CommonMeterReadVO();
                    List<CommonMeterReadPicVO> commonMeterReadPicVOs = new ArrayList<>();
                    BeanUtils.copyListProperties(commonMeterReadDO.getCommonMeterReadPicList(), commonMeterReadPicVOs, CommonMeterReadPicVO.class);
                    BeanUtils.copyProperties(commonMeterReadDO, commonMeterReadVO);
                    commonMeterReadVO.setCommonMeterReadPicList(commonMeterReadPicVOs);
                    commonMeterReadVOs.add(commonMeterReadVO);
                }
                //物品信息(集合)
                List<GoodsVO> goodsVOs = new ArrayList<>();
                List<CommonGoodsDO> commonGoodsDOs = rentTurnProtocolDO.getGoodsList();
                for(CommonGoodsDO commonGoodsDO: commonGoodsDOs){
                    GoodsVO goodsVO = new GoodsVO();
                    List<CommonGoodsPicVO> commonGoodsPicVOs = new ArrayList<>();
                    BeanUtils.copyListProperties(commonGoodsDO.getCommonGoodsPicList(), commonGoodsPicVOs, CommonGoodsPicVO.class);
                    BeanUtils.copyProperties(commonGoodsDO, goodsVO);
                    goodsVO.setCommonGoodsPicList(commonGoodsPicVOs);
                    goodsVOs.add(goodsVO);
                }
                rentTurnProtocolVO.setCommonMeterReadVOs(commonMeterReadVOs);
                rentTurnProtocolVO.setGoodsVOs(goodsVOs);    
                //协议照片(集合)**/
                List<CommonPicVO> commonPicVOs = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolDO.getUrlList(), commonPicVOs, CommonPicVO.class);
                rentTurnProtocolVO.setCommonPicVOs(commonPicVOs);
                return rentTurnProtocolVO;
            }else{
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
        }
    }
    
    @Override
    public void saveRentTurnPactRedis(String key, RentPactDO rentPactDO) {
        try {
            redisClient.setObject(key, RedisConsts.PURCHASE_TIME, rentPactDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        }
    }
    
    @Override
    public RentPactVO getPactRedis(String redisKey) {
        try {
            if (redisClient.exists(redisKey)) {
                RentPactDO rentPactDO = redisClient.getObject(redisKey);
                RentPactVO rentPactVO = new RentPactVO();
                //合同收支(集合)
                RentBaseVO rentBaseVO =new RentBaseVO();
                BeanUtils.copyProperties(rentPactDO.getPact(), rentBaseVO);
                rentPactVO.setPact(rentBaseVO);
                //合同收支规则(集合)
                List<CommonCostRuleVO> pactReceiptPayList = new ArrayList<>();
                BeanUtils.copyListProperties(rentPactDO.getPactReceiptPayList(), pactReceiptPayList, CommonCostRuleVO.class);
                rentPactVO.setPactReceiptPayList(pactReceiptPayList);
                //收支(集合)
                List<FinanceReceiptPayVO> receiptPayList = new ArrayList<>();
                BeanUtils.copyListProperties(rentPactDO.getReceiptPayList(), receiptPayList, FinanceReceiptPayVO.class);
                rentPactVO.setReceiptPayList(receiptPayList);
                //合同照片(集合)
                List<CommonPicVO> urlList = new ArrayList<>();;
                BeanUtils.copyListProperties(rentPactDO.getUrlList(), urlList, CommonPicVO.class);
                rentPactVO.setUrlList(urlList);
                return rentPactVO;
            } else {
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION + redisKey, e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }
    
    @Override
    public boolean saveRentTurn(final RentTurnProtocolDO rentTurnProtocolDO, final RentPactDO rentPactDO, 
            final RentRoomDO rentRoomDO, final RentCustomerDO rentCustomerDO,final List<RentCommonOwnerDO> cohabitantList, 
            final List<CommonMeterReadDO> newPactCommonMeterReadList,final List<CommonGoodsDO> newPactCommonGoodsDOs, 
            final List<CommonBelongerDO> commonBelongerDOList) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    /******************************************原条件，变条件账单，账单规则处理  start *******************************************/
                    RentTurnDO rentTurnDO = rentTurnProtocolDO.getRentTurnDO();
                    //原条件转租要复制大于转租时间老的合同的账单
                    if(StringConsts.ORIGINAL_CONDITION.equals(rentTurnDO.getNature()) ){
                        FinanceReceiptPayQuery receiptPayQuery = new FinanceReceiptPayQuery();
                        receiptPayQuery.setPactCode(rentTurnDO.getPirmaryPactCode());
                        Date date = DateUtil.getDayByDay(rentTurnDO.getReletTime(), 1);
                        receiptPayQuery.setCostTimeStr(DateUtil.formatDate(date));
                        List<FinanceReceiptPayVO> financeReceiptPayVOs = null;
                        financeReceiptPayVOs = financeReceiptPayDao.listReceiptPayByDate(receiptPayQuery);
                        List<CommonCostRuleVO> oldCommonCostRuleVOs = commonCostRuleDao.listCommonCostRule(rentTurnDO.getPirmaryPactCode());
                        List<CommonCostRuleDO> newCommonCostRuleVOs = new ArrayList<>();
                        if(null!=oldCommonCostRuleVOs&&oldCommonCostRuleVOs.size()>0){
                            BeanUtils.copyListProperties(oldCommonCostRuleVOs, newCommonCostRuleVOs, CommonCostRuleDO.class);
                        }
                        if(null!=newCommonCostRuleVOs&&newCommonCostRuleVOs.size()>0){
                            for(CommonCostRuleDO commonCostRuleDO:newCommonCostRuleVOs){
                                commonCostRuleDO.setPactCode(rentPactDO.getPact().getRentPactCode());
                                commonCostRuleDO.setIp(rentPactDO.getPact().getIp());
                                commonCostRuleDO.setCreateName(rentPactDO.getPact().getCreateName());
                            }
                            int saveCommonCostRule = commonCostRuleDao.saveCommonCostRule(newCommonCostRuleVOs);
                            if(saveCommonCostRule<=0){
                                status.setRollbackOnly();
                                return false;
                            }
                        }
                        if(null!=financeReceiptPayVOs&&financeReceiptPayVOs.size()>0){
                            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                            BeanUtils.copyListProperties(financeReceiptPayVOs, financeReceiptPayDOs, FinanceReceiptPayDO.class);
                            for(FinanceReceiptPayDO financeReceiptPayDO:financeReceiptPayDOs){
                                financeReceiptPayDO.setPactCode(rentPactDO.getPact().getRentPactCode());
                                financeReceiptPayDO.setIp(rentPactDO.getPact().getIp());
                                financeReceiptPayDO.setCreateName(rentPactDO.getPact().getCreateName());
                            }
                            int financeReceiptPayCount = financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOs );
                            if(financeReceiptPayCount<=0){
                                status.setRollbackOnly();
                                return false;
                            }
                        }
                    }else if(StringConsts.VARIABL_CONDITION.equals(rentTurnDO.getNature()) ){
                        if(null!=rentPactDO.getPactReceiptPayList()&&rentPactDO.getPactReceiptPayList().size()>0){
                            int costRuleFlag = commonCostRuleDao.saveCommonCostRule(rentPactDO.getPactReceiptPayList());
                            if(costRuleFlag<=0){
                                status.setRollbackOnly();
                                return false;
                            }
                            // 保存托出合同收支
                            if(null!=rentPactDO.getReceiptPayList()&&rentPactDO.getReceiptPayList().size()>0){
                                int receiptPayFlag = financeReceiptPayDao.saveFinanceReceiptPay(rentPactDO.getReceiptPayList());
                                if(receiptPayFlag<=0){
                                    status.setRollbackOnly();
                                    return false;
                                }
                            }
                        }else{
                            // 保存托出合同收支
                            if(null!=rentPactDO.getReceiptPayList()&&rentPactDO.getReceiptPayList().size()>0){
                                int receiptPayFlag = financeReceiptPayDao.saveFinanceReceiptPay(rentPactDO.getReceiptPayList());
                                if(receiptPayFlag<=0){
                                    status.setRollbackOnly();
                                    return false;
                                }
                            }
                        }
                    }
                    /******************************************原条件，变条件账单，账单规则处理  end *******************************************/
                    // 保存托出房间
                    Long rentRoomId = rentRoomDao.saveRentRoom(rentRoomDO);
                    if (rentRoomId <= 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存托出租客
                    Long rentCustomerId = rentCustomerDao.saveRentCustomer(rentCustomerDO);
                    if (rentCustomerId <= 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存托出租客共同居住人
                    if(null!=cohabitantList&&cohabitantList.size()>0){
                        int commonOwnerId = commonOwnerDao.savaCommonOwner(cohabitantList);
                        if (commonOwnerId <= 0) {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存托出合同
                    Long rentBaseId = rentBaseDao.saveRentBase(rentPactDO.getPact());
                    if (rentBaseId <= 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存合同照片
                    int commonPicFlag = commonPicDao.saveCommonPic(rentPactDO.getUrlList());
                    if (commonPicFlag <= 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存合同责任人信息
                    int belongerFlag = commonBelongerDao.saveCommonBelonger(commonBelongerDOList);
                    if (belongerFlag <= 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存合同物品信息
                    for (CommonGoodsDO commonGoodsDO : newPactCommonGoodsDOs) {
                        Long goodsId = commonGoodsDao.saveCommonGoods(commonGoodsDO);
                        if (goodsId > 0) {
                            for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                                commonGoodsPicDO.setGoodsId(goodsId);
                            }
                            // 保存物品图片
                            
                            int goodsPicFlag = commonGoodsPicDao.saveCommonGoodsPic(commonGoodsDO.getCommonGoodsPicList());
                            if (!(goodsPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存合同抄表信息
                    for (CommonMeterReadDO commonMeterReadDO : newPactCommonMeterReadList) {
                        Long meterReadId = commonMeterReadDao.saveCommonMeterRead(commonMeterReadDO);
                        if (meterReadId > 0) {
                            for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                                commonMeterReadPicDO.setMeterReadId(meterReadId);
                            }
                            // 保存抄表图片
                            int meterReadPicFlag = commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                            if (!(meterReadPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    //转租协议
                    Long rentTurnId = rentTurnDao.saveRentTurn(rentTurnProtocolDO.getRentTurnDO());
                    if(rentTurnId <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int protocolFinanceReceiptPayId = financeReceiptPayDao.saveFinanceReceiptPay(rentTurnProtocolDO.getFinanceReceiptPayDOs());
                    //转租协议收支
                    if(protocolFinanceReceiptPayId < 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //转租协议转让费用
                    int rentTurnCostId = rentTurnCostDao.saveRentTurnCost(rentTurnProtocolDO.getRentTurnCostDOs());
                    if(rentTurnCostId < 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存协议照片
                    int protocolCommonPicFlag = commonPicDao.saveCommonPic(rentTurnProtocolDO.getUrlList());
                    if(protocolCommonPicFlag <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存协议物品信息
                    for (CommonGoodsDO commonGoodsDO : rentTurnProtocolDO.getGoodsList()) {
                        Long goodsId = commonGoodsDao.saveCommonGoods(commonGoodsDO);
                        if (goodsId > 0) {
                            for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                                commonGoodsPicDO.setGoodsId(goodsId);
                            }
                            // 保存物品图片
                            int goodsPicFlag = commonGoodsPicDao.saveCommonGoodsPic(commonGoodsDO.getCommonGoodsPicList());
                            if (!(goodsPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存协议抄表信息
                    for (CommonMeterReadDO commonMeterReadDO : rentTurnProtocolDO.getMeterReadList()) {
                        Long meterReadId = commonMeterReadDao.saveCommonMeterRead(commonMeterReadDO);
                        if (meterReadId > 0) {
                            for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                                commonMeterReadPicDO.setMeterReadId(meterReadId);
                            }
                            // 保存抄表图片
                            int meterReadPicFlag = commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                            if (!(meterReadPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    //1客源状态变更为签约中(新合同的客源)
                    RentBaseDO newPact = rentPactDO.getPact();
                    DemandRpcParam demandRpcParam = new DemandRpcParam(); 
                    demandRpcParam.setDemandCode(rentCustomerDO.getDemandCode());
                    demandRpcParam.setDealState(2);
                    demandRpcParam.setIp(newPact.getIp());
                    demandRpcParam.setModified(newPact.getCreate());
                    demandRpcParam.setModifiedName(rentPactDO.getPact().getCreateName());
                    ApiResult<Boolean> demandResult = demandRpcService.updateDealState(demandRpcParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(demandResult.getCode())){
                        LOG.info("保存转租协议，客源状态变更为签约中失败，需求编码："+ demandRpcParam.getDemandCode());
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_RENTER_TURN_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_RENTER_TURN_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_RENTER_TURN_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_RENTER_TURN_ERROR, e);
                }
            }
        });
    }
    
    @Override
    public PageBean<RentTurnVO> listParam(RentTurnQuery query) {
        try {
            Integer count =  rentTurnDao.count(query);
            PageBean<RentTurnVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<RentTurnVO> rentTurnVOs = rentTurnDao.listParam(query);
                page.addAll(rentTurnVOs);
            }
            return page;
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_RENTTURN_TABLE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTTURN_TABLE_ERROR,e);
        }
    }
    
    @Override
    public RentTurnVO getRentTurnByReletCode(String reletCode) {
        try {
            RentTurnVO rentTurnVO = rentTurnDao.getRentTurnByReletCode(reletCode);
            return rentTurnVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENTTURNCOST_DETAIL_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTTURNCOST_DETAIL_ERROR,e);
        }
    }
    
    @Override
    public boolean updateRentTurnStateByCode(final RentTurnDO rentTurnDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Long flag = rentTurnDao.updateRentTurnStateByCode(rentTurnDO);
                    if(flag<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同审核通过
                    if(3==rentTurnDO.getState()){
                        /***********************新合同审核通过后在合同列表显示并且审核状态改为审核通过  start****************/
                        RentBaseDO newRentBaseDO = new RentBaseDO();
                        newRentBaseDO.setRentPactCode(rentTurnDO.getNewPactCode());
                        newRentBaseDO.setIsEffective(0);
                        newRentBaseDO.setState(3);
                        newRentBaseDO.setModifiedName(rentTurnDO.getModifiedName());
                        newRentBaseDO.setIp(rentTurnDO.getIp());
                        Long newPactId = rentBaseDao.updateRentBaseByCode(newRentBaseDO);
                        /***********************新合同审核通过后在合同列表显示  end****************/
                        if(newPactId<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                        /**********************把新合同的账单is_valid设置成1已生效 根据合同编码修改收支生效状态为已生效，合同审核通过时调用  start*****************************/
                        FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setPactCode(rentTurnDO.getNewPactCode());
                        financeReceiptPayDO.setModifiedName(rentTurnDO.getModifiedName());
                        financeReceiptPayDO.setIp(rentTurnDO.getIp());
                        int financeReceiptCount = financeReceiptPayDao.updateValidByCode(financeReceiptPayDO);
                        if(financeReceiptCount < 0){
                            status.setRollbackOnly();
                            return false;
                        }
                        /**********************把新合同的账单is_valid设置成1已生效  根据合同编码修改收支生效状态为已生效，合同审核通过时调用  end*****************************/
                        /**********************把协议的的账单is_valid设置成1已生效  根据协议编码修改收支生效状态为已生效，合同审核通过时调用  start*****************************/
                        FinanceReceiptPayDO protocolFinanceReceiptPayDO = new FinanceReceiptPayDO();
                        protocolFinanceReceiptPayDO.setPactCode(rentTurnDO.getReletCode());
                        protocolFinanceReceiptPayDO.setModifiedName(rentTurnDO.getModifiedName());
                        protocolFinanceReceiptPayDO.setIp(rentTurnDO.getIp());
                        int protocolFinanceReceiptCount = financeReceiptPayDao.updateValidByCode(protocolFinanceReceiptPayDO);
                        if(protocolFinanceReceiptCount < 0){
                            status.setRollbackOnly();
                            return false;
                        }
                        /**********************把协议的的账单is_valid设置成1已生效 根据协议编码修改收支生效状态为已生效，合同审核通过时调用  end*****************************/
                        /***************老合同处理 start  *******************/
                        //原合同解约状态由"未解约"变更为"转租解约"
                        RentBaseDO oldRentBaseDO = new RentBaseDO();
                        oldRentBaseDO.setRentPactCode(rentTurnDO.getPirmaryPactCode());
                        oldRentBaseDO.setCancelState(5);
                        oldRentBaseDO.setModifiedName(rentTurnDO.getModifiedName());
                        oldRentBaseDO.setIp(rentTurnDO.getIp());
                        Long oldPactId = rentBaseDao.updateRentBaseByCode(oldRentBaseDO);
                        if(oldPactId<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                        //将原合同截止时间以后（不含当天）的账单都删除
                        boolean oldreceiptyFlag = deleteOldPactFinanceReceiptPay(rentTurnDO);
                        if(!oldreceiptyFlag){
                            status.setRollbackOnly();
                            return false;
                        }
                        /********************* 保存交易记录    start*************************/
                        RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(rentTurnDO.getNewPactCode());
                        RentBaseVO rentBaseVO = rentBaseDao.getRentBaseByCode(rentTurnDO.getNewPactCode());
                        CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
                        belongerQuery.setPactCode(rentTurnDO.getNewPactCode());
                        belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
                        CommonTradingRecordDO commonTradingRecordDO = new CommonTradingRecordDO();
                        commonTradingRecordDO.setRoomCode(rentRoomVO.getRoomCode());
                        commonTradingRecordDO.setPactCode(rentTurnDO.getReletCode());
                        commonTradingRecordDO.setType(PactTypeConsts.RENT_SUBLET);
                        commonTradingRecordDO.setPrice(rentBaseVO.getPrice());
                        commonTradingRecordDO.setDate(rentBaseVO.getSigningTime());
                        commonTradingRecordDO.setUserName(commonBelongerVO.getUserName());
                        commonTradingRecordDO.setDeptName(commonBelongerVO.getDeptName());
                        commonTradingRecordDO.setCreateName(rentTurnDO.getModifiedName());
                        commonTradingRecordDO.setIp(rentTurnDO.getIp());
                        Long tradingRecordId = commonTradingRecordDao.saveTradingRecord(commonTradingRecordDO);
                        if(tradingRecordId <= 0){
                            status.setRollbackOnly();
                            return false;
                        }
                        /********************* 保存交易记录    end*************************/
                        /***************老合同处理  end*******************/
                        //1客源状态变更为已成交(新合同的客源)
                        RentCustomerVO rentCustomer= rentCustomerDao.getRentCustomerByPactCode(newRentBaseDO.getRentPactCode());
                        DemandRpcParam demandRpcParam = new DemandRpcParam(); 
                        demandRpcParam.setDemandCode(rentCustomer.getDemandCode());
                        demandRpcParam.setDealState(1);
                        demandRpcParam.setIp(rentTurnDO.getIp());
                        demandRpcParam.setModifiedName(rentTurnDO.getModifiedName());
                        ApiResult<Boolean> demandResult = demandRpcService.updateDealState(demandRpcParam);
                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(demandResult.getCode())){
                            LOG.info("保存转租协议，客源状态变更为已成交失败，需求编码："+ demandRpcParam.getDemandCode());
                            status.setRollbackOnly();
                            return false;
                        }                        
                        return true;
                    }else{
                        return true;
                    }
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_TURN_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_TURN_BASE_ERROR,e);
                }
            }
            /**
            * @Title: deleteOldFinanceReceiptPay
            * @Description: TODO(将大于转租日期的老合同的账单都删除)
            * @author GuoXiaoPeng
            * @param rentTurnDO 老合同编码+转租日期
            * @return 成功或失败
             */
            private boolean deleteOldPactFinanceReceiptPay(RentTurnDO rentTurnDO) {
                List<FinanceReceiptPayVO> financeReceiptPayVOs = null;
                try {
                    FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
                    financeReceiptPayQuery.setPactCode(rentTurnDO.getPirmaryPactCode());
                    Date date = DateUtil.getDayByDay(rentTurnDO.getReletTime(), 1);
                    financeReceiptPayQuery.setCostTimeStr(DateUtil.formatDate(date));
                    financeReceiptPayVOs = financeReceiptPayDao.listReceiptPayByDate(financeReceiptPayQuery);
                    if(null!=financeReceiptPayVOs&&financeReceiptPayVOs.size()>0){
                        FinanceReceiptPayDO oldFinanceReceiptPayDO = new FinanceReceiptPayDO();
                        oldFinanceReceiptPayDO.setPactCode(rentTurnDO.getPirmaryPactCode());
                        oldFinanceReceiptPayDO.setCostTime(date);
                        oldFinanceReceiptPayDO.setModifiedName(rentTurnDO.getModifiedName());
                        oldFinanceReceiptPayDO.setIp(rentTurnDO.getIp());
                        oldFinanceReceiptPayDO.setPactCode(rentTurnDO.getPirmaryPactCode());
                        financeReceiptPayDao.removeOldPactFinanceReceiptPay(oldFinanceReceiptPayDO);
                        return true;
                    }else{
                        return true;
                    }
                } catch (DaoException e) {
                    LOG.error(ErrorPactConsts.QUERY_RECEIPTPAY_BYPACT_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.QUERY_RECEIPTPAY_BYPACT_ERROR,e);
                } catch (Exception e) {
                    LOG.error(ErrorPactConsts.UPDATE_TURN_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_TURN_BASE_ERROR,e);
                }
            }
        });
    }
    
    @Override
    public boolean updateRentTurnByReletCode(final RentTurnDO rentTurnDO,
            final List<FinanceReceiptPayDO> financeReceiptPayDOList, 
            final List<RentTurnCostDO> rentTurnCostDOs,
            final List<CommonMeterReadDO> commonMeterReadDOs,
            final List<CommonGoodsDO> commonGoodsDOs, 
            final List<CommonPicDO> commonPicDOList,final List<CommonBelongerDO> commonBelongerDOs) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(rentTurnDO.getPirmaryPactCode());
                    Long rentTurnId = rentTurnDao.updateRentTurn(rentTurnDO);
                    if(rentTurnId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    if(null!=financeReceiptPayDOList&&financeReceiptPayDOList.size()>0){
                        // 对收支的 增删改
                        Map<String, Object> financeReceiptPayMap = new HashMap<>();
                        financeReceiptPayMap.put("pactCode", rentTurnDO.getReletCode());
                        financeReceiptPayMap.put("financeReceiptPayList", financeReceiptPayDOList);
                        financeReceiptPayMap.put("modifiedName", rentTurnDO.getModifiedName());
                        financeReceiptPayMap.put("ip", rentTurnDO.getIp());
                        financeReceiptPayDao.removeNotFinanceReceiptPay(financeReceiptPayMap);
                        if(null != financeReceiptPayDOList && financeReceiptPayDOList.size() > 0){
                            financeReceiptPayDao.updateFinanceReceiptPay(financeReceiptPayDOList);
                            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                            for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOList) {
                                if(null == financeReceiptPayDO.getId() || !(financeReceiptPayDO.getId() > 0)){
                                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                                        financeReceiptPayDO.setCostState(1);// 待收款
                                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                                        financeReceiptPayDO.setCostState(4);// 待付款
                                    }
                                    financeReceiptPayDO.setHouseCode(rentRoomVO.getHouseCode());
                                    financeReceiptPayDO.setRoomCode(rentRoomVO.getRoomCode());
                                    financeReceiptPayDO.setAddress(rentRoomVO.getAddress());
                                    financeReceiptPayDOs.add(financeReceiptPayDO);
                                }
                            }
                            if(financeReceiptPayDOs.size() > 0){
                                financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOs);
                            }
                        }
                    }
                    Long removeRentTurnCost = rentTurnCostDao.removeRentTurnCost(rentTurnDO.getReletCode());
                    if(removeRentTurnCost < 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    if(null != rentTurnCostDOs && rentTurnCostDOs.size() > 0){
                        int saveRentTurnCost = rentTurnCostDao.saveRentTurnCost(rentTurnCostDOs);
                        if(saveRentTurnCost<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 对图片的 增删改
                    Map<String, Object> commonPicMap = new HashMap<>();
                    commonPicMap.put("pactCode", rentTurnDO.getReletCode());
                    commonPicMap.put("commonPicList", commonPicDOList);
                    commonPicDao.removeNotCommonPic(commonPicMap);
                    if(null != commonPicDOList && commonPicDOList.size() > 0){
                        commonPicDao.updateCommonPic(commonPicDOList);
                        List<CommonPicDO> commonPicDOs = new ArrayList<>();
                        for (CommonPicDO commonPicDO : commonPicDOList) {
                            if(null == commonPicDO.getId() || !(commonPicDO.getId() > 0)){
                                commonPicDOs.add(commonPicDO);
                            }
                        }
                        if(commonPicDOs.size() > 0){
                            commonPicDao.saveCommonPic(commonPicDOs);
                        }
                    }
                    int commonBelongerId = commonBelongerDao.batchUpdateCommonBelonger(commonBelongerDOs);
                    if(commonBelongerId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    /*************************************新合同    start******************************************/
                    if(StringConsts.ORIGINAL_CONDITION.equals(rentTurnDO.getNature())){
                        RentBaseDO newPact = new RentBaseDO();
                        newPact.setStartTime(DateUtil.getDayByDay(rentTurnDO.getReletTime(), 1));
                        newPact.setModifiedName(rentTurnDO.getModifiedName());
                        newPact.setIp(rentTurnDO.getIp());
                        newPact.setRentPactCode(rentTurnDO.getNewPactCode());
                        rentBaseDao.updateRentBaseByCode(newPact);
                        /*************************删除新合同的收支    start*********************************/
                        FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setPactCode(rentTurnDO.getNewPactCode());
                        financeReceiptPayDO.setModifiedName(rentTurnDO.getModifiedName());
                        financeReceiptPayDO.setIsDelete(1);
                        financeReceiptPayDO.setIp(rentTurnDO.getIp());
                        financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPayDO);
                        /*************************删除新合同的收支    end*********************************/
                        FinanceReceiptPayQuery receiptPayQuery = new FinanceReceiptPayQuery();
                        receiptPayQuery.setPactCode(rentTurnDO.getPirmaryPactCode());
                        Date date = DateUtil.getDayByDay(rentTurnDO.getReletTime(), 1);
                        receiptPayQuery.setCostTimeStr(DateUtil.formatDate(date));
                        List<FinanceReceiptPayVO> financeReceiptPayVOs = null;
                        financeReceiptPayVOs = financeReceiptPayDao.listReceiptPayByDate(receiptPayQuery);
                        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
                            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                            BeanUtils.copyListProperties(financeReceiptPayVOs, financeReceiptPayDOs, FinanceReceiptPayDO.class);
                            for(FinanceReceiptPayDO f:financeReceiptPayDOs){
                                f.setPactCode(rentTurnDO.getNewPactCode());
                                f.setIp(rentTurnDO.getIp());
                                f.setCreateName(rentTurnDO.getModifiedName());
                            }
                            int financeReceiptPayCount = financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOs);
                            if(financeReceiptPayCount < 0){
                                status.setRollbackOnly();
                                return false;
                            }
                        }
                    }
                   /************************************新合同    end ******************************************/
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_RENT_TURN_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENT_TURN_ERROR,e);
                }
            }
        });
    }
    
    @Override
    public boolean removeRentTurnByReletCode(final RentTurnDO rentTurnDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    /**********************转租协议相关表信息删除  start********************************/
                    Long rentTurnCount=rentTurnDao.removeRentTurnByReletCode(rentTurnDO);
                    if(rentTurnCount<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                    financeReceiptPayDO.setIp(rentTurnDO.getIp());
                    financeReceiptPayDO.setModifiedName(rentTurnDO.getModifiedName());
                    financeReceiptPayDO.setPactCode(rentTurnDO.getReletCode());
                    int protocolFinanceReceiptPay = financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPayDO);
                    if(protocolFinanceReceiptPay<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    Long removeRentTurnCost = rentTurnCostDao.removeRentTurnCost(rentTurnDO.getReletCode());
                    if(removeRentTurnCost<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int protocolCommonMeterRead = commonMeterReadDao.removeCommonMeterRead(rentTurnDO.getReletCode());
                    if(protocolCommonMeterRead<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int removeCommonGoods = commonGoodsDao.removeCommonGoods(rentTurnDO.getReletCode());
                    if(removeCommonGoods<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int protocolCommonPic = commonPicDao.removeCommonPic(rentTurnDO.getReletCode());
                    if(protocolCommonPic<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int protocolBelonger = commonBelongerDao.removeCommonBelonger(rentTurnDO.getReletCode());
                    if(protocolBelonger<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    /**********************转租协议相关表信息删除  end********************************/
                    /**********************新合同相关表信息删除  start********************************/
                    RentBaseDO rentBaseDO =new RentBaseDO();
                    rentBaseDO.setIp(rentTurnDO.getIp());
                    rentBaseDO.setModifiedName(rentTurnDO.getModifiedName());
                    rentBaseDO.setRentPactCode(rentTurnDO.getNewPactCode());
                    Long newRentBaseByCode = rentBaseDao.deleteRentBaseByCode(rentBaseDO);
                    if(newRentBaseByCode<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    FinanceReceiptPayDO newfinanceReceiptPayDO = new FinanceReceiptPayDO();
                    newfinanceReceiptPayDO.setIp(rentTurnDO.getIp());
                    newfinanceReceiptPayDO.setModifiedName(rentTurnDO.getModifiedName());
                    newfinanceReceiptPayDO.setPactCode(rentTurnDO.getNewPactCode());
                    int newFinanceReceiptPay = financeReceiptPayDao.removeFinanceReceiptPay(newfinanceReceiptPayDO);
                    if(newFinanceReceiptPay<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int removeCommonCostRule = commonCostRuleDao.removeCommonCostRule(rentTurnDO.getNewPactCode());
                    if(removeCommonCostRule<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int newCommonMeterRead = commonMeterReadDao.removeCommonMeterRead(rentTurnDO.getNewPactCode());
                    if(newCommonMeterRead<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int newCommonGoods = commonGoodsDao.removeCommonGoods(rentTurnDO.getNewPactCode());
                    if(newCommonGoods<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int newCommonPic = commonPicDao.removeCommonPic(rentTurnDO.getNewPactCode());
                    if(newCommonPic<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int newBelonger = commonBelongerDao.removeCommonBelonger(rentTurnDO.getNewPactCode());
                    if(newBelonger<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    RentCustomerVO rentCustomerVO = rentCustomerDao.getRentCustomerByPactCode(rentTurnDO.getNewPactCode());
                    rentCustomerDao.removeRentCustomer(rentTurnDO.getNewPactCode());
                    commonOwnerDao.removeCommonOwner(rentCustomerVO.getRenterCode());
                    /**********************新合同相关表信息删除  end********************************/
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.DELETE_RENT_TURN_ERROR , e);
                    throw new PactManagerExcepion(ErrorPactConsts.DELETE_RENT_TURN_ERROR , e);
                }
            }
        });
    }
    @Override
    public List<RentTurnVO> listRentTurnByParam(RentTurnQuery rentTurnQuery) {
        try {
            return rentTurnDao.listRentTurnByParam(rentTurnQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENT_TURN_BYPARAM_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENT_TURN_BYPARAM_ERROR , e);
        }
    }
    
    @Override
    public List<RentTurnVO> listUnsubmitted(RentTurnQuery rentTurnQuery) {
        try {
            return rentTurnDao.listUnsubmitted(rentTurnQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENT_TURN_UNSUBMITTED_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENT_TURN_UNSUBMITTED_ERROR , e);
        }
    }
    
    @Override
    public List<RentTurnVO> listUnReview(RentTurnQuery rentTurnQuery) {
        try {
            return rentTurnDao.listUnReview(rentTurnQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENT_TURN_UNREVIEW_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENT_TURN_UNREVIEW_ERROR , e);
        }
    }
    
    @Override
    public boolean getPermissions(RentTurnQuery query) {
        try {
            RentTurnVO rentTurnVO = rentTurnDao.getPermissions(query);
            if(null == rentTurnVO ){
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.GET_PACT_RENT_TERMINATION_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PACT_RENT_TERMINATION_ERROR , e);
        }
    }
    private TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
    }

    public RentTurnDao getRentTurnDao() {
        return rentTurnDao;
    }

    public void setRentTurnDao(RentTurnDao rentTurnDao) {
        this.rentTurnDao = rentTurnDao;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public RentBaseDao getRentBaseDao() {
        return rentBaseDao;
    }

    public void setRentBaseDao(RentBaseDao rentBaseDao) {
        this.rentBaseDao = rentBaseDao;
    }

    public CommonBelongerDao getCommonBelongerDao() {
        return commonBelongerDao;
    }

    public void setCommonBelongerDao(CommonBelongerDao commonBelongerDao) {
        this.commonBelongerDao = commonBelongerDao;
    }

    public CommonCostRuleDao getCommonCostRuleDao() {
        return commonCostRuleDao;
    }

    public void setCommonCostRuleDao(CommonCostRuleDao commonCostRuleDao) {
        this.commonCostRuleDao = commonCostRuleDao;
    }

    public CommonGoodsDao getCommonGoodsDao() {
        return commonGoodsDao;
    }

    public void setCommonGoodsDao(CommonGoodsDao commonGoodsDao) {
        this.commonGoodsDao = commonGoodsDao;
    }

    public CommonGoodsPicDao getCommonGoodsPicDao() {
        return commonGoodsPicDao;
    }

    public void setCommonGoodsPicDao(CommonGoodsPicDao commonGoodsPicDao) {
        this.commonGoodsPicDao = commonGoodsPicDao;
    }

    public CommonMeterReadDao getCommonMeterReadDao() {
        return commonMeterReadDao;
    }

    public void setCommonMeterReadDao(CommonMeterReadDao commonMeterReadDao) {
        this.commonMeterReadDao = commonMeterReadDao;
    }

    public CommonMeterReadPicDao getCommonMeterReadPicDao() {
        return commonMeterReadPicDao;
    }

    public void setCommonMeterReadPicDao(CommonMeterReadPicDao commonMeterReadPicDao) {
        this.commonMeterReadPicDao = commonMeterReadPicDao;
    }

    public CommonPicDao getCommonPicDao() {
        return commonPicDao;
    }

    public void setCommonPicDao(CommonPicDao commonPicDao) {
        this.commonPicDao = commonPicDao;
    }

    public FinanceReceiptPayDao getFinanceReceiptPayDao() {
        return financeReceiptPayDao;
    }

    public void setFinanceReceiptPayDao(FinanceReceiptPayDao financeReceiptPayDao) {
        this.financeReceiptPayDao = financeReceiptPayDao;
    }

    public RentRoomDao getRentRoomDao() {
        return rentRoomDao;
    }

    public void setRentRoomDao(RentRoomDao rentRoomDao) {
        this.rentRoomDao = rentRoomDao;
    }

    public RentCustomerDao getRentCustomerDao() {
        return rentCustomerDao;
    }

    public void setRentCustomerDao(RentCustomerDao rentCustomerDao) {
        this.rentCustomerDao = rentCustomerDao;
    }

    public CommonOwnerDao getCommonOwnerDao() {
        return commonOwnerDao;
    }

    public void setCommonOwnerDao(CommonOwnerDao commonOwnerDao) {
        this.commonOwnerDao = commonOwnerDao;
    }

    public RentTurnCostDao getRentTurnCostDao() {
        return rentTurnCostDao;
    }

    public void setRentTurnCostDao(RentTurnCostDao rentTurnCostDao) {
        this.rentTurnCostDao = rentTurnCostDao;
    }

    public DemandRpcService getDemandRpcService() {
        return demandRpcService;
    }

    public void setDemandRpcService(DemandRpcService demandRpcService) {
        this.demandRpcService = demandRpcService;
    }

    public CommonTradingRecordDao getCommonTradingRecordDao() {
        return commonTradingRecordDao;
    }

    public void setCommonTradingRecordDao(CommonTradingRecordDao commonTradingRecordDao) {
        this.commonTradingRecordDao = commonTradingRecordDao;
    }

   

}
