package com.young.pact.manager.pactrenttransfer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.support.json.JSONUtils;
import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.customer.api.service.rpc.DemandRpcService;
import com.young.house.api.service.rpc.house.HouseRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.DateTools;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.dao.commoncostrule.CommonCostRuleDao;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.dao.commontradingrecord.CommonTradingRecordDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.pactrenttransfer.PactRentTransferDao;
import com.young.pact.dao.rentbase.RentBaseDao;
import com.young.pact.dao.rentcommonowner.CommonOwnerDao;
import com.young.pact.dao.rentcustomer.RentCustomerDao;
import com.young.pact.dao.rentroom.RentRoomDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferQuery;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;
import com.young.pact.domain.pactrenttransfer.PactTransferAllVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.manager.pactrenttransfer.PactRentTransferManager;
import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.product.api.domain.param.rpc.room.RoomVacantParam;
import com.young.product.api.service.rpc.room.RoomRpcService;
/**
 * 
* @ClassName: PactRentTransferManagerImpl
* @Description: 调房manager实现类
* @author LiuYuChi
* @date 2018年8月8日 下午1:50:04
*
 */
@Component("pactRentTransferManager")
public class PactRentTransferManagerImpl implements PactRentTransferManager{

	private static final Log LOG = LogFactory.getLog(PactRentTransferManagerImpl.class);
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
    private PactRentTransferDao pactRentTransferDao;
    private RoomRpcService roomRpcService;
	private HouseRpcService houseRpcService;
	private DemandRpcService demandRpcService;
	private CommonTradingRecordDao commonTradingRecordDao;
	
	@Override
	public void saveRedis(Map<String, Object> map, int time, String redisKey) {
		try {
            redisClient.setObject(redisKey, time, map);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
	}

	@Override
	public Map<String, Object> getRedis(String key) {
		try {
            if (redisClient.exists(key)) {
                Map<String, Object> map = redisClient.getObject(key);
                if(null != map && map.size() > 0){
                    return map;
                }
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_GET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_GET_EROR + key, e);
        }
		return null;
	}
	
	@Override
	public boolean saveRentBaseAndTransfer(
			final PactRentTransferDO pactRentTransferDO,
			final List<CommonMeterReadDO> meterReadList,
			final List<CommonGoodsDO> goodsList, 
			final RentRoomDO rentRoomDO,
			final RentCustomerDO rentCustomerDO,
			final List<RentCommonOwnerDO> commonOwnerDOList, 
			final RentBaseDO rentBaseDO,
			final List<CommonCostRuleDO> commonCostRuleDOs,
			final List<FinanceReceiptPayDO> receiptPayList,
			final List<CommonPicDO> commonPactPicList,
			final List<CommonMeterReadDO> commonMeterReadList,
			final List<CommonGoodsDO> commonGoodsList,
			final List<CommonBelongerDO> commonBelongerDOList) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @SuppressWarnings("rawtypes")
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    // 保存托出房间
                    Long rentRoomId = rentRoomDao.saveRentRoom(rentRoomDO);
                    if(rentRoomId<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存托出租客
                    Long rentCustomerId = rentCustomerDao.saveRentCustomer(rentCustomerDO);
                    if(rentCustomerId <0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存托出租客共同居住人
                    if (commonOwnerDOList!=null&&commonOwnerDOList.size()>0) {
                        int commonOwnerId = commonOwnerDao.savaCommonOwner(commonOwnerDOList);
                        if (commonOwnerId < 0) {
                            status.setRollbackOnly();
                            return false;
                        }
                    }

                    // 保存托出合同
                    Long rentBaseId = rentBaseDao.saveRentBase(rentBaseDO);
                    if(rentBaseId<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存托出合同收支规则
                    if(null!=commonCostRuleDOs&&commonCostRuleDOs.size()>0){
                        int costRuleFlag = commonCostRuleDao.saveCommonCostRule(commonCostRuleDOs);
                        if(costRuleFlag < 0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存合同收支
                    int receiptPayFlag = financeReceiptPayDao.saveFinanceReceiptPay(receiptPayList);
                    if(receiptPayFlag<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存合同照片
                    int commonPicFlag = commonPicDao.saveCommonPic(commonPactPicList);
                    if(commonPicFlag <0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存合同责任人信息
                    int belongerFlag = commonBelongerDao.saveCommonBelonger(commonBelongerDOList);
                    if(belongerFlag <0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //保存协议
                    Long transferFlag = pactRentTransferDao.saveTransfer(pactRentTransferDO);
                    if (transferFlag < 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    
                    // 保存合同物品信息
                    for (CommonGoodsDO commonGoodsDO : commonGoodsList) {
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
                    for (CommonMeterReadDO commonMeterReadDO : commonMeterReadList) {
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
                    // 保存协议物品信息
                    for (CommonGoodsDO commonGoodsDO : goodsList) {
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
                    for (CommonMeterReadDO commonMeterReadDO : meterReadList) {
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
                    RoomParam roomParam = new RoomParam();
                    roomParam.setRoomCode(rentRoomDO.getRoomCode());
                    roomParam.setIp(rentRoomDO.getIp());
                    roomParam.setModifiedName(rentRoomDO.getCreateName());
                    roomParam.setStockState(4);
                    //1 房间状态变更为签约中
                    ApiResult roomBaseResult = roomRpcService.updateRoomBase(roomParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomBaseResult.getCode())){
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
                }
            }
        });
	}
	
	@Override
	public PactTransferAllVO getTransfer(String transferCode) {
		try {
			PactRentTransferVO pactRentTransferVO = pactRentTransferDao.getTransfer(transferCode);
			List<CommonPicVO> commonPicList = commonPicDao.listCommonPic(transferCode);
			PactTransferAllVO pactTransferAllVO = new PactTransferAllVO();
			pactTransferAllVO.setCommonPicList(commonPicList);
			pactTransferAllVO.setPactRentTransferVO(pactRentTransferVO);
			return pactTransferAllVO;
		} catch (DaoException e) {
			LOG.error(ErrorPactConsts.GET_PACT_RENT_TRANSFER_ERROR, e);
			throw new PactManagerExcepion(ErrorPactConsts.GET_PACT_RENT_TRANSFER_ERROR,e);
		}
	}
	
	
	@Override
	public boolean updateTransferInfo(final PactRentTransferDO pactRentTransferDO) {
		    TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        //删除托出房间
                        rentRoomDao.removeRentRoom(pactRentTransferDO.getNewPactCode());
                        RentCustomerVO rentCustomerVO = rentCustomerDao.getRentCustomerByPactCode(pactRentTransferDO.getNewPactCode());
                        //删除租客共同居住人
                        commonOwnerDao.removeCommonOwner(rentCustomerVO.getRenterCode());
                        //删除租客
                        rentCustomerDao.removeRentCustomer(pactRentTransferDO.getNewPactCode());
                        //逻辑删除托出合同
                        RentBaseDO rentBaseDO = new RentBaseDO();
                        rentBaseDO.setModifiedName(pactRentTransferDO.getModifiedName());
                        rentBaseDO.setIp(pactRentTransferDO.getIp());
                        rentBaseDO.setRentPactCode(pactRentTransferDO.getNewPactCode());
                        rentBaseDao.deleteRentBaseByCode(rentBaseDO);
                        // 删除合同收支规则
                        commonCostRuleDao.removeCommonCostRule(rentBaseDO.getRentPactCode());
                        FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setIp(rentBaseDO.getIp());
                        financeReceiptPayDO.setModifiedName(rentBaseDO.getModifiedName());
                        financeReceiptPayDO.setPactCode(rentBaseDO.getRentPactCode());
                        financeReceiptPayDO.setIsDelete(1);
                        // 逻辑删除 收支信息
                        financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPayDO);
                        // 删除合同图片
                        commonPicDao.removeCommonPic(rentBaseDO.getRentPactCode());
                        // 删除合同责任人
                        commonBelongerDao.removeCommonBelonger(rentBaseDO.getRentPactCode());
                        // 删除合同物品
                        commonGoodsDao.removeCommonGoods(rentBaseDO.getRentPactCode());
                        // 删除合同抄表
                        commonMeterReadDao.removeCommonMeterRead(rentBaseDO.getRentPactCode());
                        //删除协议
                        pactRentTransferDao.updateTransfer(pactRentTransferDO);
                        //删除协议照片
                        commonPicDao.removeCommonPic(pactRentTransferDO.getTransferCode());
                        //删除协议责任人
                        commonBelongerDao.removeCommonBelonger(pactRentTransferDO.getTransferCode());
                        //删除协议收支
                        FinanceReceiptPayDO financeReceiptPay = new FinanceReceiptPayDO();
                        financeReceiptPay.setIp(pactRentTransferDO.getIp());
                        financeReceiptPay.setModifiedName(pactRentTransferDO.getModifiedName());
                        financeReceiptPay.setPactCode(pactRentTransferDO.getTransferCode());
                        financeReceiptPay.setIsDelete(1);
                        // 逻辑删除 收支信息
                        financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPay);
                        // 删除合同物品
                        commonGoodsDao.removeCommonGoods(pactRentTransferDO.getTransferCode());
                        // 删除合同抄表
                        commonMeterReadDao.removeCommonMeterRead(pactRentTransferDO.getTransferCode());
                        // 交易记录
                        
                        return true;
                    } catch (Exception e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.DEL_PACT_RENT_TRANSFER_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.DEL_PACT_RENT_TRANSFER_ERROR,e);
                    }
                }
            });
	}

	@Override
	public boolean updateTransfer(final PactRentTransferDO pactRentTransferDO,final List<FinanceReceiptPayDO> financeReceiptPayDOs,
			final List<CommonPicDO> commonPicDOList, final List<CommonBelongerDO> commonBelongerDO,final List<FinanceReceiptPayDO> financeReceiptPayList) {
			TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
	        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

	        @Override
	        public Boolean doInTransaction(TransactionStatus status) {
	            try {
	            	int flagPactRentTransfer = pactRentTransferDao.updateTransfer(pactRentTransferDO);
	            	if (flagPactRentTransfer>0) {
	            		// 对收支的 增删改
                        Map<String, Object> financeReceiptPayMap = new HashMap<>();
                        financeReceiptPayMap.put("pactCode", pactRentTransferDO.getTransferCode());
                        financeReceiptPayMap.put("financeReceiptPayList", financeReceiptPayDOs);
                        financeReceiptPayMap.put("modifiedName", pactRentTransferDO.getModifiedName());
                        financeReceiptPayMap.put("ip", pactRentTransferDO.getIp());
                        financeReceiptPayDao.removeNotFinanceReceiptPay(financeReceiptPayMap);
                        if(null != financeReceiptPayDOs && financeReceiptPayDOs.size() > 0){
                            financeReceiptPayDao.updateFinanceReceiptPay(financeReceiptPayDOs);
                            List<FinanceReceiptPayDO> addFinanceReceiptPayDOs = new ArrayList<>();
                            for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOs) {
                                if(null == financeReceiptPayDO.getId() || !(financeReceiptPayDO.getId() > 0)){
                                    addFinanceReceiptPayDOs.add(financeReceiptPayDO);
                                }
                            }
                            if(financeReceiptPayDOs.size() > 0){
                                financeReceiptPayDao.saveFinanceReceiptPay(addFinanceReceiptPayDOs);
                            }
                        }
                        
                        // 对图片的 增删改
                        Map<String, Object> commonPicMap = new HashMap<>();
                        commonPicMap.put("pactCode", pactRentTransferDO.getTransferCode());
                        commonPicMap.put("commonPicList", commonPicDOList);
                        int flagP = commonPicDao.removeNotCommonPic(commonPicMap);
                        if (!(flagP>0)) {
                        	status.setRollbackOnly();
    	                    return false;
						}
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
                        
                        // 修改签约人
                        int flagB =  commonBelongerDao.batchUpdateCommonBelonger(commonBelongerDO);
                        if (!(flagB>0)) {
                        	status.setRollbackOnly();
    	                    return false;
						}
                        
                        //如果是原条件调房并且修改了截止时间,修改新合同的收支
                        if (financeReceiptPayList!=null&&financeReceiptPayList.size()>0) {
                        	//修改收支信息
                        	Map<String, Object> financeReceiptMap = new HashMap<>();
                            financeReceiptMap.put("pactCode", pactRentTransferDO.getTransferCode());
                            financeReceiptMap.put("financeReceiptPayList", financeReceiptPayList);
                            financeReceiptMap.put("modifiedName", pactRentTransferDO.getModifiedName());
                            financeReceiptMap.put("ip", pactRentTransferDO.getIp());
                            financeReceiptPayDao.removeNotFinanceReceiptPay(financeReceiptMap);
                            if(null != financeReceiptPayList && financeReceiptPayList.size() > 0){
                                financeReceiptPayDao.updateFinanceReceiptPay(financeReceiptPayList);
                                List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                                for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOs) {
                                    if(null == financeReceiptPayDO.getId() || !(financeReceiptPayDO.getId() > 0)){
                                        financeReceiptPayDOs.add(financeReceiptPayDO);
                                    }
                                }
                                if(financeReceiptPayDOs.size() > 0){
                                    financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOs);
                                }
                            }
                            //修改新合同信息
                            RentBaseDO rentBaseDO = new RentBaseDO();
                            rentBaseDO.setRentPactCode(pactRentTransferDO.getNewPactCode());
                            rentBaseDO.setIp(pactRentTransferDO.getIp());
                            rentBaseDO.setModifiedName(pactRentTransferDO.getModifiedName());
                            rentBaseDO.setStartTime(DateTools.getLastDay(pactRentTransferDO.getTransferTime(), 1));
                            Long flagRB = rentBaseDao.updateRentBaseByCode(rentBaseDO);
                            if (!(flagRB>0)) {
                            	status.setRollbackOnly();
        	                    return false;
							}
						}
                        
	            		return true;
					}else{
						status.setRollbackOnly();
	                    return false;
					}
	            } catch (DaoException e) {
	                status.setRollbackOnly();
	                LOG.error(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
	                throw new PactManagerExcepion(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
	            } catch (Exception e) {
	                status.setRollbackOnly();
	                LOG.error(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
	                throw new PactManagerExcepion(ErrorPactConsts.SAVE_PACT_RENT_TRANSFER_ERROR, e);
	            }
	        }
        });
    }


	@Override
	public PageBean<PactRentTransferVO> listParam(PactRentTransferQuery query) {
        try {
            Integer count =  pactRentTransferDao.countTransfer(query);
            PageBean<PactRentTransferVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<PactRentTransferVO> transfer = pactRentTransferDao.listPactRentTransfer(query);
                page.addAll(transfer);
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_ERROR,e);
        }
    }

	

	@Override
	public boolean rejectTransferInfo(final PactRentTransferDO pactRentTransferDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

        @Override
        public Boolean doInTransaction(TransactionStatus status) {
        	try {
                int flag = pactRentTransferDao.updateTransfer(pactRentTransferDO);
                if (flag>0) {
                	return true;
				}else{
					status.setRollbackOnly();
                    return false;
				}
            } catch (DaoException e) {
                LOG.error(ErrorPactConsts.REJECT_PACT_RENT_TRANSFER_ERROR, e);
                throw new PactManagerExcepion(ErrorPactConsts.REJECT_PACT_RENT_TRANSFER_ERROR,e);
            }
        	
        }
        });
	}
	

	@Override
	public boolean checkTransfer(final PactRentTransferDO pactRentTransferDO,final FinanceReceiptPayDO oldfinanceReceiptPayDO
			,final FinanceReceiptPayDO newfinanceReceiptPayDO,final FinanceReceiptPayDO transferReceiptPayDO,
			final RentBaseDO newRentBaseDO,final RentBaseDO oldRentBaseDO) {
		
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	 @Override
             public Boolean doInTransaction(TransactionStatus status) {
        		 try {
        				int flag = pactRentTransferDao.updateTransfer(pactRentTransferDO);
        	            if (flag>0) {
        	            	    //根据合同的应收付时间将原合同截止时间以后的（不包含当天）只要状态是“待收款和待付款”的都收支计划都删除。
    	            	        financeReceiptPayDao.delFinanceReceiptPay(oldfinanceReceiptPayDO);
        	            		//修改新收支生效
        	            		financeReceiptPayDao.updateValidByCode(newfinanceReceiptPayDO);
        	            			//协议的收支生效
        	            			int flagT = financeReceiptPayDao.updateValidByCode(transferReceiptPayDO);
        	            			if (flagT>0) {
        	            				//新合同生效
        	            				Long flagR =rentBaseDao.updateRentBaseByCode(newRentBaseDO);
        	            				if (flagR>0) {
        	            					//老合同变到期,变调房解约
        	            					Long flagO = rentBaseDao.updateRentBaseByCode(oldRentBaseDO);
        	            					if (flagO>0) {
        	            					    //交易记录
        	            					    CommonTradingRecordDO commonTradingRecordDO = new CommonTradingRecordDO();
        	            					    //房间编码
        	            					    RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(newRentBaseDO.getRentPactCode());
        	            					    if(null!=rentRoomVO){
        	            					        commonTradingRecordDO.setRoomCode(rentRoomVO.getRoomCode());
        	            					    }
        	            					    //协议编码
        	            					    commonTradingRecordDO.setPactCode(pactRentTransferDO.getTransferCode());
        	            					    //协议类型
        	            					    commonTradingRecordDO.setType(PactTypeConsts.RENT_ROOM);
        	            					    //月租
        	            					    RentBaseVO rentBaseVO = rentBaseDao.getRentBaseByCode(newRentBaseDO.getRentPactCode());
        	            					    if(null!=rentBaseVO){
        	            					        commonTradingRecordDO.setPrice(rentBaseVO.getPrice());
        	            					    }
        	            					    //签约时间
        	            					    PactRentTransferVO pactRentTransferVO = pactRentTransferDao.getTransfer(pactRentTransferDO.getTransferCode());
        	            					    if(null!=pactRentTransferVO){
        	            					        commonTradingRecordDO.setDate(pactRentTransferVO.getSigningTime());
        	            					    }
        	            					    //签约管家和管家部门名称
        	            					    CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
        	                                    belongerQuery.setPactCode(pactRentTransferDO.getTransferCode());
        	                                    belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
        	                                    CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
        	                                    if(null!=commonBelongerVO){
        	                                        commonTradingRecordDO.setUserName(commonBelongerVO.getUserName());
        	                                        commonTradingRecordDO.setDeptName(commonBelongerVO.getDeptName());
        	                                    }
        	                                    //创建人
        	                                    commonTradingRecordDO.setCreateName(pactRentTransferDO.getModifiedName());
        	                                    //ip
        	                                    commonTradingRecordDO.setIp(pactRentTransferDO.getIp());
        	                                    Long tradingRecordId = commonTradingRecordDao.saveTradingRecord(commonTradingRecordDO);
        	                                    if(tradingRecordId > 0){
        	                                        //房间状态由签约中变更为未租
        	                                        RentRoomVO oldPactRentRoomVO = rentRoomDao.getRommByRentCode(pactRentTransferDO.getPirmaryPactCode());
                                                    boolean updateRoomFlag = updateRentRoom(pactRentTransferDO,oldPactRentRoomVO,rentRoomVO,status);
                                                    if(updateRoomFlag){
        	                                            // 房间计算并存储空置时间
        	                                            boolean syncRoomVacantFlag = syncRoomVacant(pactRentTransferDO,oldPactRentRoomVO,rentRoomVO,status);
        	                                            return syncRoomVacantFlag;
        	                                        }
                                                    return updateRoomFlag;
        	                                    }else{
        	                                        status.setRollbackOnly();
                                                    return false;
        	                                    }
											}else{
												status.setRollbackOnly();
												return false;
											}
										}else{
											status.setRollbackOnly();
											return false;
										}
									}else{
										status.setRollbackOnly();
										return false;
									}
        				}else{
        					status.setRollbackOnly();
        	                return false;
        				}
        			} catch (Exception e) {
        			    status.setRollbackOnly();
        				LOG.error(ErrorPactConsts.CHECK_PACT_RENT_TRANSFER_ERROR, e);
        	            throw new PactManagerExcepion(ErrorPactConsts.CHECK_PACT_RENT_TRANSFER_ERROR,e);
        			}
             }
        	/**
        	* @Title: syncRoomVacant
        	* @Description: TODO( 为老，新合同房间计算并存储空置时间)
        	* @author GuoXiaoPeng
        	* @param pactRentTransferDO 协议信息
        	* @param oldPactRentRoomVO 老合同房间信息
        	* @param newPactRentRoomVO 新合同房间信息
        	* @param status 
        	* @return 修改成功返回true，失败返回false
        	* @throws 异常
        	 */
        	@SuppressWarnings("rawtypes")
            private boolean syncRoomVacant(PactRentTransferDO pactRentTransferDO, RentRoomVO oldPactRentRoomVO, RentRoomVO newPactRentRoomVO, TransactionStatus status) {
        	     //为老合同房间计算并存储空置时间
                 RoomVacantParam roomVacantParam = new RoomVacantParam(); 
                 roomVacantParam.setRoomCode(oldPactRentRoomVO.getRoomCode());
                 roomVacantParam.setModifiedName(pactRentTransferDO.getModifiedName());
                 roomVacantParam.setStartTime(pactRentTransferDO.getTransferTime());
                 roomVacantParam.setIp(pactRentTransferDO.getIp());
                 ApiResult roomVacantResult = roomRpcService.updateRoomVacant(roomVacantParam);
                 if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomVacantResult.getCode())){
                     LOG.error("老合同房间计算并存储空置时间出错,操作对象" + JsonUtil.toJson(roomVacantParam));
                     status.setRollbackOnly();
                     return false;
                 }
                 //为新合同房间计算并存储空置时间
                 RoomVacantParam newRoomVacantParam = new RoomVacantParam(); 
                 newRoomVacantParam.setRoomCode(newPactRentRoomVO.getRoomCode());
                 newRoomVacantParam.setModifiedName(pactRentTransferDO.getModifiedName());
                 newRoomVacantParam.setEndTime(pactRentTransferDO.getTransferTime());
                 newRoomVacantParam.setIp(pactRentTransferDO.getIp());
                 ApiResult newPactRoomVacantResult = roomRpcService.updateRoomVacant(newRoomVacantParam);
                 if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(newPactRoomVacantResult.getCode())){
                     LOG.error("新合同房间计算并存储空置时间出错,操作对象" + JsonUtil.toJson(newRoomVacantParam));
                     status.setRollbackOnly();
                     return false;
                 }
                 return true;
            }
           
            /**
        	  * 
        	 * @Title: updateRentRoom
        	 * @Description: TODO( 修改房间状态 )
        	 * @author GuoXiaoPeng
        	 * @param pactRentTransferDO 调房协议信息
        	 * @param oldPactRentRoomVO 老合同托出房间信息
             * @param newPactRentRoomVO 新合同托出房间信息
        	 * @param status 
        	 * @return 修改成功返回true，失败返回false
        	 * @throws DaoException 
        	 * @throws 异常
        	  */
            @SuppressWarnings("rawtypes")
            private boolean updateRentRoom(PactRentTransferDO pactRentTransferDO, RentRoomVO oldPactRentRoomVO, RentRoomVO newPactRentRoomVO, TransactionStatus status) throws DaoException {
                RentRoomDO oldPactRentRoomDO = new RentRoomDO();
                oldPactRentRoomDO.setModifiedName(pactRentTransferDO.getModifiedName());
                oldPactRentRoomDO.setIp(pactRentTransferDO.getIp());
                oldPactRentRoomDO.setRoomCode(oldPactRentRoomVO.getRoomCode());
                oldPactRentRoomDO.setStockState(1);
                Integer oldPactrRomCount = rentRoomDao.updateRoomByRoomCode(oldPactRentRoomDO);
                if(oldPactrRomCount < 0){
                    LOG.info("修改老合同房间状态失败,操作对象：" + JSONUtils.toJSONString(oldPactRentRoomDO));
                    status.setRollbackOnly();
                    return false;
                }
                RoomParam oldPactRomParam = new RoomParam();
                oldPactRomParam.setModifiedName(pactRentTransferDO.getModifiedName());
                oldPactRomParam.setIp(pactRentTransferDO.getIp());
                oldPactRomParam.setRoomCode(oldPactRentRoomVO.getRoomCode());
                oldPactRomParam.setStockState(1);
                ApiResult roomResult = roomRpcService.updateRoomBase(oldPactRomParam);
                if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
                    LOG.info("老合同房态状态同步更新出错,操作对象" + JsonUtil.toJson(oldPactRomParam));
                    status.setRollbackOnly();
                    return false;
                }
                RentRoomDO newPactRentRoomDO = new RentRoomDO();
                newPactRentRoomDO.setModifiedName(pactRentTransferDO.getModifiedName());
                newPactRentRoomDO.setIp(pactRentTransferDO.getIp());
                newPactRentRoomDO.setRoomCode( newPactRentRoomVO.getRoomCode());
                newPactRentRoomDO.setStockState(2);
                Integer newPactrRomCount = rentRoomDao.updateRoomByRoomCode(newPactRentRoomDO);
                if(newPactrRomCount < 0){
                    LOG.info("修改新合同房间状态失败,操作对象：" + JSONUtils.toJSONString(newPactRentRoomDO));
                    status.setRollbackOnly();
                    return false;
                }
                RoomParam newPactRomParam = new RoomParam();
                newPactRomParam.setModifiedName(pactRentTransferDO.getModifiedName());
                newPactRomParam.setIp(pactRentTransferDO.getIp());
                newPactRomParam.setRoomCode(newPactRentRoomVO.getRoomCode());
                newPactRomParam.setStockState(2);
                ApiResult newPactRoomResult = roomRpcService.updateRoomBase(newPactRomParam);
                if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(newPactRoomResult.getCode())){
                    LOG.info("新合同房态状态同步更新出错,操作对象" + JsonUtil.toJson(newPactRomParam));
                    status.setRollbackOnly();
                    return false;
                }
                return true;
            }
        });
	}

	@Override
	public List<PactRentTransferVO> listTransferByParam(PactRentTransferQuery rentTransferQuery) {
	    try {
	        return pactRentTransferDao.listTransferByParam(rentTransferQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_BYPARAM_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_BYPARAM_ERROR,e);
        }
	}
	
	@Override
	public List<PactRentTransferVO> listUnReview(PactRentTransferQuery rentTransferQuery) {
	    try {
            return pactRentTransferDao.listUnReview(rentTransferQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_UNREVIEW_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_UNREVIEW_ERROR,e);
        }
	}
	
	@Override
	public List<PactRentTransferVO> listUnsubmitted(PactRentTransferQuery rentTransferQuery) {
	    try {
            return pactRentTransferDao.listUnsubmitted(rentTransferQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_UNSUBMITTED_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_UNSUBMITTED_ERROR,e);
        }
	}
	
	@Override
    public Boolean aplyCheck(PactRentTransferDO pactRentTransferDO) {
	    try {
            int flag = pactRentTransferDao.updateTransfer(pactRentTransferDO);
            if (flag > 0) {
                return true;
            }
            return false;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.APLYCHECK_PACT_RENT_TRANSFER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.APLYCHECK_PACT_RENT_TRANSFER_ERROR,e);
        }
    }

	@Override
    public boolean getPermissions(PactRentTransferQuery query) {
	    try {
	        PactRentTransferVO pactRentTransferVO = pactRentTransferDao.getPermissions(query);
            if (null == pactRentTransferVO) {
                return false;
            }
            return true;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PACT_RENT_TRANSFER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PACT_RENT_TRANSFER_ERROR,e);
        }
    }
	public TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
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

	public PactRentTransferDao getPactRentTransferDao() {
		return pactRentTransferDao;
	}

	public void setPactRentTransferDao(PactRentTransferDao pactRentTransferDao) {
		this.pactRentTransferDao = pactRentTransferDao;
	}

    public RoomRpcService getRoomRpcService() {
        return roomRpcService;
    }

    public void setRoomRpcService(RoomRpcService roomRpcService) {
        this.roomRpcService = roomRpcService;
    }

    public HouseRpcService getHouseRpcService() {
        return houseRpcService;
    }

    public void setHouseRpcService(HouseRpcService houseRpcService) {
        this.houseRpcService = houseRpcService;
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
