package com.young.pact.manager.rentbase.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.tools.common.util.date.DateUtil;
import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.customer.api.domain.param.rpc.CustomerRpcParam;
import com.young.customer.api.domain.param.rpc.DemandRpcParam;
import com.young.customer.api.service.rpc.CustomerRpcService;
import com.young.customer.api.service.rpc.DemandRpcService;
import com.young.pact.common.constant.DictionaryConsts;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
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
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.product.api.domain.param.rpc.room.RoomVacantParam;
import com.young.product.api.service.rpc.room.RoomRpcService;
import com.young.system.api.domain.result.rpc.DictionaryResult;
import com.young.system.api.service.rpc.DictionaryRpcService;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午2:56:56
 */
@SuppressWarnings("all")
@Component("rentBaseManager")
public class RentBaseManagerImpl implements RentBaseManager {
    private static Log LOG = LogFactoryImpl.getLog(RentBaseManagerImpl.class);
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
    private DemandRpcService demandRpcService;
    private RoomRpcService roomRpcService;
    private CustomerRpcService customerRpcService;
    private DictionaryRpcService dictionaryRpcService;
    private CommonTradingRecordDao commonTradingRecordDao;
    @Override
    public void savePactRedis(String key, RentPactDO rentPactDO) {
        try {
            redisClient.setObject(key, RedisConsts.PURCHASE_TIME, rentPactDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        }
    }

    @Override
    public RentPactVO getPactRedis(String key) {
        try {
            if (redisClient.exists(key)) {
                RentPactDO rentPactDO = redisClient.getObject(key);
                RentPactVO rentPactVO = new RentPactVO();
                //合同收支(集合)
                RentBaseVO rentBaseVO =new RentBaseVO();
                BeanUtils.copyProperties(rentPactDO.getPact(), rentBaseVO);
                rentPactVO.setPact(rentBaseVO);
                //合同收支(集合)
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
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION + key, e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }

    @Override
    public boolean saveRentBase(final RentRoomDO rentRoomDO,
            final RentCustomerDO rentCustomerDO,
            final List<RentCommonOwnerDO> commonOwnerDOList, 
            final RentBaseDO rentBaseDO, final List<CommonCostRuleDO> commonCostRuleDOs,
            final List<FinanceReceiptPayDO> receiptPayList, 
            final List<CommonPicDO> commonPicDOList,
            final List<CommonMeterReadDO> commonMeterReadDOList, 
            final List<CommonGoodsDO> commonGoodsDOList, 
            final List<CommonBelongerDO> commonBelongerDOList) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
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
                    if(null!=commonOwnerDOList&&commonOwnerDOList.size()>0){
                        int commonOwnerId = commonOwnerDao.savaCommonOwner(commonOwnerDOList);
                        if(commonOwnerId <=0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存托出合同
                    Long rentBaseId = rentBaseDao.saveRentBase(rentBaseDO);
                    if (rentBaseId <= 0) {
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存托出合同收支规则
                    if(null!=commonCostRuleDOs&&commonCostRuleDOs.size()>0){
                        int costRuleFlag = commonCostRuleDao.saveCommonCostRule(commonCostRuleDOs);
                        if (costRuleFlag <= 0) {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存托进合同收支
                    int receiptPayFlag = financeReceiptPayDao.saveFinanceReceiptPay(receiptPayList);
                    if(receiptPayFlag <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存合同照片
                    int commonPicFlag = commonPicDao.saveCommonPic(commonPicDOList);
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
                    for (CommonGoodsDO commonGoodsDO : commonGoodsDOList) {
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
                    for (CommonMeterReadDO commonMeterReadDO : commonMeterReadDOList) {
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
                    /********************未填充              start***********************/
                    //1 客源状态变更为签约中
                    DemandRpcParam demandRpcParam = new DemandRpcParam();
                    demandRpcParam.setDemandCode(rentCustomerDO.getDemandCode());
                    demandRpcParam.setDealState(2);
                    ApiResult<Boolean> demandResult = demandRpcService.updateDealState(demandRpcParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(demandResult.getCode())){
                        LOG.error("添加托出合同客源状态修改失败,需求编码：" + demandRpcParam.getDemandCode());
                        status.setRollbackOnly();
                        return false;
                    }
                    //2 房间状态变更为签约中
                    RoomParam roomParam = new RoomParam(); 
                    roomParam.setModifiedName(rentBaseDO.getCreateName());
                    roomParam.setIp(rentBaseDO.getIp());
                    roomParam.setRoomCode(rentRoomDO.getRoomCode());
                    roomParam.setStockState(4);
                    ApiResult roomResult = roomRpcService.updateRoomBase(roomParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
                        LOG.error("添加托出合同房间状态变更为签约中修改失败,房间编码：" + roomParam.getRoomCode());
                        status.setRollbackOnly();
                        return false;
                    }
                    /********************未填充              end***********************/
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
                }
            }
        });
    }
    
    @Override
    public PageBean<RentBaseVO> listParam(RentBaseQuery query) {
        try {
            Integer count =  rentBaseDao.count(query);
            PageBean<RentBaseVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<RentBaseVO> rentBaseVOs = rentBaseDao.listParam(query);
                page.addAll(rentBaseVOs);
            }
            return page;
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_RENTBASE_TABLE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTBASE_TABLE_ERROR,e);
        }
    }
    
    @Override
    public RentPactVO getRentBaseByCode(String rentPactCode,FinanceReceiptPayQuery financeReceiptPayQuery) {
        try {
            RentPactVO rentPactVO = new RentPactVO();
            RentBaseVO rentBaseVO = rentBaseDao.getRentBaseByCode(rentPactCode);
            rentPactVO.setPact(rentBaseVO);
            if(null != rentBaseVO){
                List<CommonCostRuleVO> listCommonCostRule = commonCostRuleDao.listCommonCostRule(rentBaseVO.getRentPactCode());
                List<CommonPicVO> urlList = commonPicDao.listCommonPic(rentBaseVO.getRentPactCode());
                List <FinanceReceiptPayVO> financeReceiptPayVOs = new ArrayList<>();
                rentPactVO.setPactReceiptPayList(listCommonCostRule);
                rentPactVO.setUrlList(urlList);
                rentPactVO.setPact(rentBaseVO);
            }
            return rentPactVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENT_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENT_BASE_ERROR,e);
        }
    }

    @Override
    public boolean updateRenterByCode(final CustomerOwnerDO customerOwnerDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Long customerFlag = rentCustomerDao.updateRentCustomerByPactCode(customerOwnerDO.getRenter());
                     if(customerFlag >0){
                        if(null!= customerOwnerDO.getCohabitantList() && customerOwnerDO.getCohabitantList().size()>0){
                            int commonOwnerFlag = commonOwnerDao.updateCommonOwner(customerOwnerDO.getCohabitantList());
                            if(commonOwnerFlag >0){
                                return true;
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }else{
                            return true;
                        }
                    }else{
                        status.setRollbackOnly();
                        return false;
                    }
                } catch (PactManagerExcepion e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_RENT_CUSTOMER_BY_PACTCODE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENT_CUSTOMER_BY_PACTCODE_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_RENT_CUSTOMER_BY_PACTCODE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENT_CUSTOMER_BY_PACTCODE_ERROR, e);
                }
            }
        });
    }

    @Override
    public boolean updateRentBaseStateByCode(final RentBaseDO rentBaseDO,final RentBaseVO pact) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    
                    Long rentBaseId = rentBaseDao.updateRentBaseStateByCode(rentBaseDO);
                    if(rentBaseId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    if(3==rentBaseDO.getState()){
                        /********************** 根据合同编码修改收支生效状态为已生效，合同审核通过时调用  start*****************************/
                        FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setPactCode(rentBaseDO.getRentPactCode());
                        financeReceiptPayDO.setModifiedName(rentBaseDO.getModifiedName());
                        financeReceiptPayDO.setIp(rentBaseDO.getIp());
                        int financeReceiptCount = financeReceiptPayDao.updateValidByCode(financeReceiptPayDO);
                        if(financeReceiptCount<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                        /********************** 根据合同编码修改收支生效状态为已生效，合同审核通过时调用  end*****************************/
                        RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(rentBaseDO.getRentPactCode());
                        /********************* 保存交易记录    start*************************/
                        RentBaseVO rentBaseVO = rentBaseDao.getRentBaseByCode(rentBaseDO.getRentPactCode());
                        CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
                        belongerQuery.setPactCode(rentBaseDO.getRentPactCode());
                        belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
                        CommonTradingRecordDO commonTradingRecordDO = new CommonTradingRecordDO();
                        commonTradingRecordDO.setRoomCode(rentRoomVO.getRoomCode());
                        commonTradingRecordDO.setPactCode(rentBaseDO.getRentPactCode());
                        commonTradingRecordDO.setType(PactTypeConsts.RENT);
                        commonTradingRecordDO.setPrice(rentBaseVO.getPrice());
                        commonTradingRecordDO.setDate(rentBaseVO.getSigningTime());
                        commonTradingRecordDO.setUserName(commonBelongerVO.getUserName());
                        commonTradingRecordDO.setDeptName(commonBelongerVO.getDeptName());
                        commonTradingRecordDO.setCreateName(rentBaseDO.getModifiedName());
                        commonTradingRecordDO.setIp(rentBaseDO.getIp());
                        Long tradingRecordId = commonTradingRecordDao.saveTradingRecord(commonTradingRecordDO);
                        if(tradingRecordId <= 0){
                            status.setRollbackOnly();
                            return false;
                        }
                        /********************* 保存交易记录    end*************************/
                        //1.房间状态由签约中变更为已租
                        RentRoomDO rentRoomDO = new RentRoomDO();
                        rentRoomDO.setModifiedName(rentBaseDO.getModifiedName());
                        rentRoomDO.setIp(rentBaseDO.getIp());
                        rentRoomDO.setRoomCode(rentRoomVO.getRoomCode());
                        rentRoomDO.setStockState(2);
                        Integer roomCount = rentRoomDao.updateRoomByRoomCode(rentRoomDO);
                        if(roomCount<0){
                            status.setRollbackOnly();
                            return false;
                        }
                        //2.客源状态由签约中变更为已成交
                        RentCustomerVO rentCustomerVO = rentCustomerDao.getRentCustomerByPactCode(rentBaseDO.getRentPactCode());
                        DemandRpcParam demandRpcParam = new DemandRpcParam();
                        demandRpcParam.setDemandCode(rentCustomerVO.getDemandCode());
                        demandRpcParam.setDealState(1);
                        demandRpcParam.setModifiedName(rentBaseDO.getModifiedName());
                        demandRpcParam.setIp(rentBaseDO.getIp());
                        ApiResult<Boolean> demandResult = demandRpcService.updateDealState(demandRpcParam);
                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(demandResult.getCode())){
                            status.setRollbackOnly();
                            return false;
                        }
                        //3.将合同客源的数据与该客源同步更新
                        CustomerRpcParam customerRpcParam  = new CustomerRpcParam();
                        customerRpcParam.setCustomerCode(rentCustomerVO.getCustomerCode());
                        customerRpcParam.setCustomerName(rentCustomerVO.getName());
                        if(0==rentCustomerVO.getSex()){
                            customerRpcParam.setCustomerSex(StringConsts.WOMAN);
                        }else if(1==rentCustomerVO.getSex()){
                            customerRpcParam.setCustomerSex(StringConsts.MAN);
                        }
                        customerRpcParam.setCustomerTel(rentCustomerVO.getTel());
                        customerRpcParam.setSpareTel(rentCustomerVO.getSpareTel());
                        customerRpcParam.setIdType(rentCustomerVO.getIdType());
                        customerRpcParam.setIdNum(rentCustomerVO.getIdCode());
                        customerRpcParam.setBirthday(DateUtil.formatDate(rentCustomerVO.getBirthday()));
                        customerRpcParam.setAddress(rentCustomerVO.getAddress());
                        customerRpcParam.setWechat(rentCustomerVO.getWechat());
                        customerRpcParam.setNatives(rentCustomerVO.getNativePlance());
                        List<String> typecodeList = new ArrayList<>(); 
                        typecodeList.add(DictionaryConsts.MARITAL_STATUS);
                        ApiResult<Map<String, List<DictionaryResult>>> dictionaryResult = dictionaryRpcService.listDictionary(typecodeList);
                        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(dictionaryResult.getCode())){
                            Map<String, List<DictionaryResult>> map = dictionaryResult.getData();
                            List<DictionaryResult> list = map.get(DictionaryConsts.MARITAL_STATUS);
                            for(DictionaryResult dictionary:list){
                                String value = dictionary.getValue();
                                String valueName = dictionary.getValueName();
                                if(valueName.equals(rentCustomerVO.getMaritalStatus())){
                                    customerRpcParam.setIsMarried(Integer.parseInt(value));
                                }
                            }
                        }
                        customerRpcParam.setLable(rentCustomerVO.getLabel());
                        customerRpcParam.setIncome(rentCustomerVO.getMonthlyIncome());
                        ApiResult<Boolean> customerResult = customerRpcService.updateCustomer(customerRpcParam);
                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(customerResult.getCode())){
                            LOG.info("修改客源信息出错,操作对象" + JsonUtil.toJson(customerRpcParam));
                            status.setRollbackOnly();
                            return false;
                        }
                        //4.房态状态同步更新
                        RoomParam roomParam = new RoomParam();
                        roomParam.setModifiedName(rentBaseDO.getModifiedName());
                        roomParam.setIp(rentBaseDO.getIp());
                        roomParam.setRoomCode(rentRoomVO.getRoomCode());
                        roomParam.setStockState(2);
                        ApiResult roomResult = roomRpcService.updateRoomBase(roomParam);
                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
                            LOG.info("房态状态同步更新出错,操作对象" + JsonUtil.toJson(roomParam));
                            status.setRollbackOnly();
                            return false;
                        }
                        //5.为该房间计算并存储空置时间
                        RoomVacantParam roomVacantParam = new RoomVacantParam(); 
                        roomVacantParam.setRoomCode(rentRoomVO.getRoomCode());
                        roomVacantParam.setModifiedName(rentBaseDO.getModifiedName());
                        roomVacantParam.setEndTime(rentBaseDO.getStartTime());
                        roomVacantParam.setIp(rentBaseDO.getIp());
                        ApiResult roomVacantResult = roomRpcService.updateRoomVacant(roomVacantParam);
                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomVacantResult.getCode())){
                            LOG.error("房间计算并存储空置时间出错,操作对象" + JsonUtil.toJson(roomVacantParam));
                            status.setRollbackOnly();
                            return false;
                        }
                        //6.生成摊销记录
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_RENT_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENT_BASE_ERROR,e);
                }
            }
        });
    }
    @Override
    public boolean deleteRentBaseByCode(final RentBaseDO rentBaseDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(rentBaseDO.getRentPactCode());
                    //删除托出房间
                    rentRoomDao.removeRentRoom(rentBaseDO.getRentPactCode());
                    RentCustomerVO rentCustomerVO = rentCustomerDao.getRentCustomerByPactCode(rentBaseDO.getRentPactCode());
                    //删除租客共同居住人
                    commonOwnerDao.removeCommonOwner(rentCustomerVO.getRenterCode());
                    //删除租客
                    rentCustomerDao.removeRentCustomer(rentBaseDO.getRentPactCode());
                    //逻辑删除托出合同
                    Long flag = rentBaseDao.deleteRentBaseByCode(rentBaseDO);
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
                    //1.将客源成交状态由签约中变更为“原签约状态”
                    RentBaseQuery rentBaseQuery = new RentBaseQuery();
                    rentBaseQuery.setCustomerCode(rentCustomerVO.getCustomerCode());
                    int  rentBaseCustomerCount= rentBaseDao.countRentBaseByCustomer(rentBaseQuery);
                    DemandRpcParam demandRpcParam = new DemandRpcParam();
                    demandRpcParam.setDemandCode(rentCustomerVO.getDemandCode());
                    if(rentBaseCustomerCount<=0){
                        demandRpcParam.setDealState(0);
                    }else{
                        demandRpcParam.setDealState(2);
                    }
                    ApiResult<Boolean> demandResult = demandRpcService.updateDealState(demandRpcParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(demandResult.getCode())){
                        status.setRollbackOnly();
                        return false;
                    }
                    //2.将房间状态由签约中变更为未租
                    RoomParam roomParam = new RoomParam();
                    roomParam.setModifiedName(rentBaseDO.getModifiedName());
                    roomParam.setIp(rentBaseDO.getIp());
                    roomParam.setRoomCode(rentRoomVO.getRoomCode());
                    roomParam.setStockState(1);
                    ApiResult roomResult = roomRpcService.updateRoomBase(roomParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.DELETE_RENT_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.DELETE_RENT_BASE_ERROR,e);
                }
            }
        });
    }
    @Override
    public boolean updateRentBaseByCode(final RentBaseDO rentBaseDO,final List<CommonCostRuleDO> 
    commonCostRuleDOList, final List<FinanceReceiptPayDO> financeReceiptPayDOList, 
        final List<CommonPicDO> commonPicDOList,final CommonBelongerDO commonBelongerDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Long rentBaseId = rentBaseDao.updateRentBaseByCode(rentBaseDO);
                    if(rentBaseId>0){

                        // 对收支规则的 增删改
                        Map<String, Object> commonCostRuleMap = new HashMap<>();
                        commonCostRuleMap.put("pactCode", rentBaseDO.getRentPactCode());
                        commonCostRuleMap.put("commonCostRuleList", commonCostRuleDOList);
                        commonCostRuleDao.removeNotCommonCostRule(commonCostRuleMap);// 删除
                        if(null != commonCostRuleDOList && commonCostRuleDOList.size() > 0){
                            commonCostRuleDao.updateCommonCostRule(commonCostRuleDOList);// 修改
                            List<CommonCostRuleDO> commonCostRuleDOs = new ArrayList<>();
                            for (CommonCostRuleDO commonCostRuleDO : commonCostRuleDOList) {
                                if(null == commonCostRuleDO.getId() || !(commonCostRuleDO.getId() > 0)){
                                    commonCostRuleDOs.add(commonCostRuleDO);
                                }
                            }
                            if(commonCostRuleDOs.size() > 0){
                                commonCostRuleDao.saveCommonCostRule(commonCostRuleDOs);// 添加
                            }
                        }
                        // 对收支的 增删改
                        Map<String, Object> financeReceiptPayMap = new HashMap<>();
                        financeReceiptPayMap.put("pactCode", rentBaseDO.getRentPactCode());
                        financeReceiptPayMap.put("financeReceiptPayList", financeReceiptPayDOList);
                        financeReceiptPayMap.put("modifiedName", rentBaseDO.getModifiedName());
                        financeReceiptPayMap.put("ip", rentBaseDO.getIp());
                        financeReceiptPayDao.removeNotFinanceReceiptPay(financeReceiptPayMap);
                        if(null != financeReceiptPayDOList && financeReceiptPayDOList.size() > 0){
                            financeReceiptPayDao.updateFinanceReceiptPay(financeReceiptPayDOList);
                            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                            for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOList) {
                                if(null == financeReceiptPayDO.getId() || !(financeReceiptPayDO.getId() > 0)){
                                    financeReceiptPayDOs.add(financeReceiptPayDO);
                                }
                            }
                            if(financeReceiptPayDOs.size() > 0){
                                financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOs);
                            }
                        }
                        // 对图片的 增删改
                        Map<String, Object> commonPicMap = new HashMap<>();
                        commonPicMap.put("pactCode", rentBaseDO.getRentPactCode());
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
                        // 修改签约人
                        int commonBelongerId = commonBelongerDao.updateCommonBelonger(commonBelongerDO);
                        if(commonBelongerId<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                        return true;
                    }else{
                        status.setRollbackOnly();
                        return false;
                    }
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.DELETE_RENT_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.DELETE_RENT_BASE_ERROR,e);
                }
            }
        });
    }
    
    @Override
    public RentBaseVO getRentBaseByCode(String rentPactCode) {
        try {
            RentBaseVO rentBaseVO = rentBaseDao.getRentBaseByCode(rentPactCode);
            return rentBaseVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENT_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENT_BASE_ERROR,e);
        }
    }
    
    @Override
    public boolean setDelayDate(RentBaseDO rentBaseDO) {
        try {
            Long count = rentBaseDao.updateRentBaseByCode(rentBaseDO);
            if(count>0){
                return true;
            }
            return false;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.RENTBASE_SET_DELAYDATE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.RENTBASE_SET_DELAYDATE_ERROR,e);
        }
    }
    /**
    * @Title: getRoomRpcService
    * @Description: TODO( 查看登录人有没有查看托出合同详情的权限 )
    * @author GuoXiaoPeng
    * @return 有返回true，没有返回false
    * @throws 异常
     */
    public boolean getPermissions(RentBaseQuery query) {
        try {
            RentBaseVO rentBaseVO = rentBaseDao.getPermissions(query);
            if(null != rentBaseVO){
                return true;
            }
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_RENT_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENT_BASE_ERROR,e);
        }
    }
    
    @Override
    public CommonBelongerVO getRentAfterBelonger(String pactCode) {
        try {
            CommonBelongerVO commonBelongerVO = rentBaseDao.getRentAfterBelonger(pactCode);
            return commonBelongerVO;
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_COMMON_BELONGER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_COMMON_BELONGER_ERROR,e);
        }
    }
    @Override
    public List<RentBaseVO> listUnsubmitted(RentBaseQuery rentBaseQuery) {
        try {
            return rentBaseDao.listUnsubmitted(rentBaseQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNSUBMITTED_RENTBASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNSUBMITTED_RENTBASE_ERROR,e);
        }
    }
    
    @Override
    public List<RentBaseVO> listUnReview(RentBaseQuery rentBaseQuery) {
        try {
            return rentBaseDao.listUnReview(rentBaseQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNREVIEW_RENTBASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNREVIEW_RENTBASE_ERROR,e);
        }
    }
    
    @Override
    public List<RentBaseVO> listRentBaseByParam(RentBaseQuery rentBaseQuery) {
        try {
            return rentBaseDao.listRentBaseByParam(rentBaseQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENTBASE_BYPARAM_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENTBASE_BYPARAM_ERROR,e);
        }
    }
    
    @Override
    public List<RentBaseVO> listRentExpir() {
        try {
            return rentBaseDao.listRentExpir();
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENTBASE_EXPIR_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENTBASE_EXPIR_ERROR,e);
        }
    }
    public RedisClient getRedisClient() {
        return redisClient;
    }
    
    public TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
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

    public DemandRpcService getDemandRpcService() {
        return demandRpcService;
    }

    public void setDemandRpcService(DemandRpcService demandRpcService) {
        this.demandRpcService = demandRpcService;
    }

    public RoomRpcService getRoomRpcService() {
        return roomRpcService;
    }

    public void setRoomRpcService(RoomRpcService roomRpcService) {
        this.roomRpcService = roomRpcService;
    }

    public CustomerRpcService getCustomerRpcService() {
        return customerRpcService;
    }

    public void setCustomerRpcService(CustomerRpcService customerRpcService) {
        this.customerRpcService = customerRpcService;
    }

    public DictionaryRpcService getDictionaryRpcService() {
        return dictionaryRpcService;
    }

    public void setDictionaryRpcService(DictionaryRpcService dictionaryRpcService) {
        this.dictionaryRpcService = dictionaryRpcService;
    }

    public CommonTradingRecordDao getCommonTradingRecordDao() {
        return commonTradingRecordDao;
    }

    public void setCommonTradingRecordDao(CommonTradingRecordDao commonTradingRecordDao) {
        this.commonTradingRecordDao = commonTradingRecordDao;
    }
    
}
