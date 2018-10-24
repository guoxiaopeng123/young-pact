package com.young.pact.manager.pactrenttermination.impl;

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
import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.dao.commontradingrecord.CommonTradingRecordDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.pactrenttermination.PactRentTerminationDao;
import com.young.pact.dao.rentbase.RentBaseDao;
import com.young.pact.dao.rentroom.RentRoomDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationAllVO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.manager.pactrenttermination.PactRentTerminationManager;
import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.product.api.domain.param.rpc.room.RoomVacantParam;
import com.young.product.api.service.rpc.room.RoomRpcService;

/**
 * 
* @ClassName: PactRentTerminationManagerImpl
* @Description: 拖出解约协议
* @author LiuYuChi
* @date 2018年8月12日 上午11:48:10
*
 */
@Component("pactRentTerminationManager")
public class PactRentTerminationManagerImpl implements PactRentTerminationManager{

	private static final Log LOG = LogFactory.getLog(PactRentTerminationManagerImpl.class);
	private PactRentTerminationDao pactRentTerminationDao;
	private PlatformTransactionManager transactionManager;
    private CommonBelongerDao commonBelongerDao;
    private CommonGoodsDao commonGoodsDao;
    private CommonGoodsPicDao commonGoodsPicDao;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonMeterReadPicDao commonMeterReadPicDao;
    private CommonPicDao commonPicDao;
    private FinanceReceiptPayDao financeReceiptPayDao;
    private RentRoomDao rentRoomDao;
    private CommonTradingRecordDao commonTradingRecordDao;
    private RentBaseDao rentBaseDao;
    private RoomRpcService roomRpcService;
	@Override
	public boolean insertTermination(
			final PactRentTerminationDO pactRentTerminationDO,
			final List<CommonBelongerDO> commonBelongerDOList,
			final List<CommonPicDO> commonPactPicList, 
			final List<CommonGoodsDO> goodsList,
			final List<CommonMeterReadDO> meterReadList,
			final List<FinanceReceiptPayDO> receiptPayList) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {
					Long flagT = pactRentTerminationDao.insertTermination(pactRentTerminationDO);
					if (flagT>0) {
						int flagB = commonBelongerDao.saveCommonBelonger(commonBelongerDOList);
						if (flagB>0) {
							int flagP = commonPicDao.saveCommonPic(commonPactPicList);
							if (flagP>0) {
								int flagR = financeReceiptPayDao.saveFinanceReceiptPay(receiptPayList);
								if (flagR>0) {
									// 保存合同物品信息
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
                                    // 保存合同抄表信息
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
					LOG.error(ErrorPactConsts.INSERT_PACT_RENT_TERMINATION_ERROR, e);
    	            throw new PactManagerExcepion(ErrorPactConsts.INSERT_PACT_RENT_TERMINATION_ERROR,e);
				}
        		return true;
            }
        });
	}
	
	@Override
	public PageBean<PactRentTerminationVO> listTermination(
			PactRentTerminationQuery query) {
        try {
            Integer count =  pactRentTerminationDao.countTermination(query);
            PageBean<PactRentTerminationVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<PactRentTerminationVO> transfer = pactRentTerminationDao.listTermination(query);
                page.addAll(transfer);
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PACT_RENT_TRANSFER_ERROR,e);
        }
    }
	
	@Override
	public PactRentTerminationAllVO getPactRentTermination(PactRentTerminationQuery query) {
		PactRentTerminationAllVO pactRentTerminationAllVO = new PactRentTerminationAllVO();
		try {
			PactRentTerminationVO pactRentTerminationVO = pactRentTerminationDao.getTermination(query);
			List<CommonPicVO> commonPicVO = commonPicDao.listCommonPic(query.getDissolutionCode());
			List<FinanceReceiptPayVO> financeRecriptList = financeReceiptPayDao.listFinanceReceiptPay(query.getDissolutionCode());
			pactRentTerminationAllVO.setCommonPicVO(commonPicVO);
			pactRentTerminationAllVO.setPactRentTerminationVO(pactRentTerminationVO);
			pactRentTerminationAllVO.setFinanceReceiptPayList(financeRecriptList);
			return pactRentTerminationAllVO;
		} catch (Exception e) {
			 LOG.error(ErrorPactConsts.GET_PACT_RENT_TERMINATION_ERROR, e);
	         throw new PactManagerExcepion(ErrorPactConsts.GET_PACT_RENT_TERMINATION_ERROR,e);
		}
		
	}
	

	@Override
	public boolean updateTermination(
			final PactRentTerminationDO pactRentTerminationDO,
			final List<FinanceReceiptPayDO> financeReceiptPayDOs,
			final List<CommonPicDO> commonPicDOs, final CommonBelongerDO commonBelongerDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {

	            	int flagPactRentT = pactRentTerminationDao.updateTermination(pactRentTerminationDO);
	            	if (flagPactRentT>0) {
	            		// 对收支的 增删改
                        Map<String, Object> financeReceiptPayMap = new HashMap<>();
                        financeReceiptPayMap.put("pactCode", pactRentTerminationDO.getDissolutionCode());
                        financeReceiptPayMap.put("financeReceiptPayList", financeReceiptPayDOs);
                        financeReceiptPayMap.put("modifiedName", pactRentTerminationDO.getModifiedName());
                        financeReceiptPayMap.put("ip", pactRentTerminationDO.getIp());
                        financeReceiptPayDao.removeNotFinanceReceiptPay(financeReceiptPayMap);
                        if(null != financeReceiptPayDOs && financeReceiptPayDOs.size() > 0){
                            financeReceiptPayDao.updateFinanceReceiptPay(financeReceiptPayDOs);
                            List<FinanceReceiptPayDO> financeReceiptPayList = new ArrayList<>();
                            for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOs) {
                                if(null == financeReceiptPayDO.getId() || !(financeReceiptPayDO.getId() > 0)){
                                	financeReceiptPayList.add(financeReceiptPayDO);
                                }
                            }
                            if(financeReceiptPayList.size() > 0){
                                financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayList);
                            }
                        }
                        // 对图片的 增删改
                        Map<String, Object> commonPicMap = new HashMap<>();
                        commonPicMap.put("pactCode", pactRentTerminationDO.getDissolutionCode());
                        commonPicMap.put("commonPicList", commonPicDOs);
                        commonPicDao.removeNotCommonPic(commonPicMap);
                        if(null != commonPicDOs && commonPicDOs.size() > 0){
                            commonPicDao.updateCommonPic(commonPicDOs);
                            List<CommonPicDO> addCommonPicDOs = new ArrayList<>();
                            for (CommonPicDO commonPicDO : commonPicDOs) {
                                if(null == commonPicDO.getId() || !(commonPicDO.getId() > 0)){
                                    addCommonPicDOs.add(commonPicDO);
                                }
                            }
                            if(commonPicDOs.size() > 0){
                                commonPicDao.saveCommonPic(addCommonPicDOs);
                            }
                        }
                        // 修改签约人
                        commonBelongerDao.updateCommonBelonger(commonBelongerDO);
	            		return true;
					}else{
						status.setRollbackOnly();
	                    return false;
					}
				} catch (Exception e) {
					LOG.error(ErrorPactConsts.UPDATE_PACT_RENT_TERMINATION_ERROR, e);
			         throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PACT_RENT_TERMINATION_ERROR,e);
				}
        	}
        });
	}
	
	@Override
	public boolean applyCheck(PactRentTerminationDO pactRentTerminationDO) {
		try {
			int flag = pactRentTerminationDao.updateTermination(pactRentTerminationDO);
			if (flag>0) {
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			LOG.error(ErrorPactConsts.APLY_PACT_RENT_TERMINATION_ERROR, e);
	         throw new PactManagerExcepion(ErrorPactConsts.APLY_PACT_RENT_TERMINATION_ERROR,e);
		}
	}
	

	@Override
	public boolean reject(final PactRentTerminationDO pactRentTerminationDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {
        			int flag = pactRentTerminationDao.updateTermination(pactRentTerminationDO);
        			if (flag > 0) {
        				return true;
					}else{
						status.setRollbackOnly();
	                    return false;
					}
        		} catch (Exception e) {
        			LOG.error(ErrorPactConsts.REJECT_PACT_RENT_TERMINATION_ERROR, e);
        	         throw new PactManagerExcepion(ErrorPactConsts.REJECT_PACT_RENT_TERMINATION_ERROR,e);
        		}
        	}
        });
	}
	

	@Override
	public boolean delTermination(final PactRentTerminationDO pactRentTerminationDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {
        			int flag = pactRentTerminationDao.updateTermination(pactRentTerminationDO);
        			if (flag >0) {
        				FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setIp(pactRentTerminationDO.getIp());
                        financeReceiptPayDO.setIsDelete(1);
                        financeReceiptPayDO.setModifiedName(pactRentTerminationDO.getModifiedName());
                        financeReceiptPayDO.setPactCode(pactRentTerminationDO.getDissolutionCode());
                        int protocolFinanceReceiptPay = financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPayDO);
                        if(protocolFinanceReceiptPay<0){
                            status.setRollbackOnly();
                            return false;
                        }
                        int protocolCommonMeterRead = commonMeterReadDao.removeCommonMeterRead(pactRentTerminationDO.getDissolutionCode());
                        if(protocolCommonMeterRead<0){
                            status.setRollbackOnly();
                            return false;
                        }
                        int removeCommonGoods = commonGoodsDao.removeCommonGoods(pactRentTerminationDO.getDissolutionCode());
                        if(removeCommonGoods<0){
                            status.setRollbackOnly();
                            return false;
                        }
                        int protocolCommonPic = commonPicDao.removeCommonPic(pactRentTerminationDO.getDissolutionCode());
                        if(protocolCommonPic<0){
                            status.setRollbackOnly();
                            return false;
                        }
                        int protocolBelonger = commonBelongerDao.removeCommonBelonger(pactRentTerminationDO.getDissolutionCode());
                        if(protocolBelonger<0){
                            status.setRollbackOnly();
                            return false;
                        }
        				return true;
        			}else{
        				return false;
        			}
        		} catch (Exception e) {
        			LOG.error(ErrorPactConsts.DELETE_PACT_RENT_TERMINATION_ERROR, e);
        	         throw new PactManagerExcepion(ErrorPactConsts.DELETE_PACT_RENT_TERMINATION_ERROR,e);
        		}
            }
        });
	}
	
	@Override
	public boolean check(final PactRentTerminationDO pactRentTerminationDO,final FinanceReceiptPayDO oldFinanceReceiptPayDO,
			 final FinanceReceiptPayDO newFinanceReceiptPayDO,final RentBaseDO rentBaseDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {
        		    Long rentBaseCount = rentBaseDao.updateRentBaseByCode(rentBaseDO);
        		    if(rentBaseCount < 0){
        		        status.setRollbackOnly();
                        return false;
        		    }
					int flag = pactRentTerminationDao.updateTermination(pactRentTerminationDO);
					if (flag > 0) {
    	            	//根据合同的应收付时间将原合同截止时间以后的（不包含当天）只要状态是“待收款和待付款”的都收支计划都删除。
    	            	int flagF = financeReceiptPayDao.delFinanceReceiptPay(oldFinanceReceiptPayDO);
    	            	if (flagF >= 0) {
    	            		//修改新收支生效
    	            		int flagN = financeReceiptPayDao.updateValidByCode(newFinanceReceiptPayDO);
    	            		if (flagN >= 0) {
    	            		    //交易记录
    	            		    CommonTradingRecordDO commonTradingRecordDO = new CommonTradingRecordDO();
    	            		    //房间编码
    	            		    RentRoomVO rentRoomVO = rentRoomDao.getByDissolutionCode(pactRentTerminationDO.getDissolutionCode());
    	            		    if(null!=rentRoomVO){
    	            		        commonTradingRecordDO.setRoomCode(rentRoomVO.getRoomCode());
    	            		    }
    	            		    //协议编码
    	            		    commonTradingRecordDO.setPactCode(pactRentTerminationDO.getDissolutionCode());
    	            		    //合同类型
    	            		    commonTradingRecordDO.setType(PactTypeConsts.RENT_TERMINATION);
    	            		    //签约时间
    	            		    PactRentTerminationQuery query = new PactRentTerminationQuery();
    	            		    query.setDissolutionCode(pactRentTerminationDO.getDissolutionCode());
    	            		    PactRentTerminationVO pactRentTerminationVO = pactRentTerminationDao.getTermination(query);
    	            		    if(null!=pactRentTerminationVO){
    	            		        commonTradingRecordDO.setDate(pactRentTerminationVO.getDissolutionTime());
    	            		    }
    	            		    //管家姓名和管家部门名称
    	            		    CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
    	                        belongerQuery.setPactCode(pactRentTerminationVO.getDissolutionCode());
    	                        belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
    	                        CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
    	                        if(null!=commonBelongerVO){
    	                            commonTradingRecordDO.setUserName(commonBelongerVO.getUserName());
    	                            commonTradingRecordDO.setDeptName(commonBelongerVO.getDeptName());
    	                        }
    	                        //创建人
    	                        commonTradingRecordDO.setCreateName(pactRentTerminationDO.getModifiedName());
    	                        commonTradingRecordDO.setIp(pactRentTerminationDO.getIp());
    	                        Long tradingRecordId = commonTradingRecordDao.saveTradingRecord(commonTradingRecordDO);
    	                        if(tradingRecordId>0){
    	                            //房间状态由签约中变更为未租
    	                            boolean updateRoomFlag = updateRentRoom(pactRentTerminationDO,rentRoomVO,status);
    	                            if(updateRoomFlag){
    	                                // 房间计算并存储空置时间
                                        boolean syncRoomVacantFlag = syncRoomVacant(pactRentTerminationDO,rentRoomVO,status);
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
				} catch (Exception e) {
					LOG.error(ErrorPactConsts.CHECK_PACT_RENT_TERMINATION_ERROR, e);
			        throw new PactManagerExcepion(ErrorPactConsts.CHECK_PACT_RENT_TERMINATION_ERROR,e);
				}
        	}
        	
        	/**
        	* @Title: syncRoomVacant
        	* @Description: TODO( 老合同房间计算并存储空置时间  )
        	* @author GuoXiaoPeng
        	* @param pactRentTerminationDO 解约协议信息
        	* @param oldPactRentRoomVO 老合同房间信息
        	* @param status
        	* @return  修改成功返回true，失败返回false
        	* @throws 异常
        	 */
            @SuppressWarnings("rawtypes")
            private boolean syncRoomVacant(PactRentTerminationDO pactRentTerminationDO, RentRoomVO oldPactRentRoomVO, TransactionStatus status) {
                //为老合同房间计算并存储空置时间
                RoomVacantParam roomVacantParam = new RoomVacantParam(); 
                roomVacantParam.setRoomCode(oldPactRentRoomVO.getRoomCode());
                roomVacantParam.setModifiedName(pactRentTerminationDO.getModifiedName());
                roomVacantParam.setStartTime(pactRentTerminationDO.getDissolutionTime());
                roomVacantParam.setIp(pactRentTerminationDO.getIp());
                ApiResult roomVacantResult = roomRpcService.updateRoomVacant(roomVacantParam);
                if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomVacantResult.getCode())){
                    LOG.error("老合同房间计算并存储空置时间出错,操作对象" + JsonUtil.toJson(roomVacantParam));
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
            * @param pactRentTerminationDO 解约协议信息
            * @param oldPactRentRoomVO 老合同房间信息
            * @param status
            * @return  修改成功返回true，失败返回false
             * @throws DaoException 
            * @throws 异常
             */
            @SuppressWarnings("rawtypes")
            private boolean updateRentRoom(PactRentTerminationDO pactRentTerminationDO, RentRoomVO oldPactRentRoomVO, TransactionStatus status) throws DaoException {
                RentRoomDO oldPactRentRoomDO = new RentRoomDO();
                oldPactRentRoomDO.setModifiedName(pactRentTerminationDO.getModifiedName());
                oldPactRentRoomDO.setIp(pactRentTerminationDO.getIp());
                oldPactRentRoomDO.setRoomCode(oldPactRentRoomVO.getRoomCode());
                oldPactRentRoomDO.setStockState(1);
                Integer oldPactrRomCount = rentRoomDao.updateRoomByRoomCode(oldPactRentRoomDO);
                if(oldPactrRomCount < 0){
                    LOG.info("修改老合同房间状态失败,操作对象：" + JSONUtils.toJSONString(oldPactRentRoomDO));
                    status.setRollbackOnly();
                    return false;
                }
                RoomParam oldPactRomParam = new RoomParam();
                oldPactRomParam.setModifiedName(pactRentTerminationDO.getModifiedName());
                oldPactRomParam.setIp(pactRentTerminationDO.getIp());
                oldPactRomParam.setRoomCode(oldPactRentRoomVO.getRoomCode());
                oldPactRomParam.setStockState(1);
                ApiResult roomResult = roomRpcService.updateRoomBase(oldPactRomParam);
                if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
                    LOG.info("老合同房态状态同步更新出错,操作对象" + JsonUtil.toJson(oldPactRomParam));
                    status.setRollbackOnly();
                    return false;
                }
                return true;
            }
        });
	}

	@Override
	public List<PactRentTerminationVO> listRentTerminationByParam(PactRentTerminationQuery pactRentTerminationQuery) {
	    try {
	        return pactRentTerminationDao.listRentTerminationByParam(pactRentTerminationQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_PACT_RENT_TERMINATION_ERROR, e);
             throw new PactManagerExcepion(ErrorPactConsts.LIST_PACT_RENT_TERMINATION_ERROR,e);
        }
	}
	
	@Override
	public List<PactRentTerminationVO> listUnsubmitted(PactRentTerminationQuery pactRentTerminationQuery) {
	    try {
            return pactRentTerminationDao.listUnsubmitted(pactRentTerminationQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNSUBMITTED_PACT_RENT_TERMINATION_ERROR, e);
             throw new PactManagerExcepion(ErrorPactConsts.LIST_UNSUBMITTED_PACT_RENT_TERMINATION_ERROR,e);
        }
	}
	
	@Override
	public List<PactRentTerminationVO> listUnReview(PactRentTerminationQuery pactRentTerminationQuery) {
	    try {
            return pactRentTerminationDao.listUnReview(pactRentTerminationQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNREVIEW_PACT_RENT_TERMINATION_ERROR, e);
             throw new PactManagerExcepion(ErrorPactConsts.LIST_UNREVIEW_PACT_RENT_TERMINATION_ERROR,e);
        }
	}
	@Override
    public boolean getPermissions(PactRentTerminationQuery query) {
	    try {
	        PactRentTerminationVO pactRentTerminationVO = pactRentTerminationDao.getPermissions(query);
	        if(null == pactRentTerminationVO){
	            return false;
	        }
	        return true;
	    } catch (Exception e) {
            LOG.error(ErrorPactConsts.QUERY_RENTTERMINATION_ERROR, e);
             throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTTERMINATION_ERROR,e);
        }
    }
	public PactRentTerminationDao getPactRentTerminationDao() {
		return pactRentTerminationDao;
	}
	public void setPactRentTerminationDao(
			PactRentTerminationDao pactRentTerminationDao) {
		this.pactRentTerminationDao = pactRentTerminationDao;
	}
	public TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
    }

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public CommonBelongerDao getCommonBelongerDao() {
		return commonBelongerDao;
	}

	public void setCommonBelongerDao(CommonBelongerDao commonBelongerDao) {
		this.commonBelongerDao = commonBelongerDao;
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

    public CommonTradingRecordDao getCommonTradingRecordDao() {
        return commonTradingRecordDao;
    }

    public void setCommonTradingRecordDao(CommonTradingRecordDao commonTradingRecordDao) {
        this.commonTradingRecordDao = commonTradingRecordDao;
    }

    public RentBaseDao getRentBaseDao() {
        return rentBaseDao;
    }

    public void setRentBaseDao(RentBaseDao rentBaseDao) {
        this.rentBaseDao = rentBaseDao;
    }

    public RoomRpcService getRoomRpcService() {
        return roomRpcService;
    }

    public void setRoomRpcService(RoomRpcService roomRpcService) {
        this.roomRpcService = roomRpcService;
    }
}
