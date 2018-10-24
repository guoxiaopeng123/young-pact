package com.young.pact.service.commongoods.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsPicResult;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.service.rest.commongoods.CommonGoodsService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commongoods.CommonGoodsQuery;
import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.manager.commongoods.CommonGoodsManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;

/**
 * 
* @ClassName: CommonGoodsServiceImpl
* @Description: 公共物品
* @author LiuYuChi
* @date 2018年8月14日 上午10:55:33
*
 */
@Service("commonGoodsService")
public class CommonGoodsServiceImpl implements CommonGoodsService{
	
	private static final Log LOG = LogFactory.getLog(CommonGoodsServiceImpl.class);
	private CommonGoodsManager commonGoodsManager;
	private OperateLogRpc operateLogRpc;
	
	@Override
	public ApiResult<Boolean> insertGoods(CommonGoodsParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
		try {
			if (param != null) {
				CommonGoodsDO commonGoodsDO = new CommonGoodsDO();
				List<CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
				if(null!=param.getCommonGoodsPicList()){
				    BeanUtils.copyListProperties(param.getCommonGoodsPicList(), commonGoodsPicDOs, CommonGoodsPicDO.class);
				}
				BeanUtils.copyProperties(param, commonGoodsDO);
				for(CommonGoodsPicDO commonGoodsPicDO:commonGoodsPicDOs){
				    commonGoodsPicDO.setCreateName(param.getCreateName());
				    commonGoodsPicDO.setIp(param.getIp());
				}
				commonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
				boolean flag = commonGoodsManager.insertGoods(commonGoodsDO);
				if (flag) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, StringConsts.OPERATELOG_GOODS_SERVICETYPE);
                    map.put(NormalConstant.SERVICECODE, commonGoodsDO.getPactCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                    map.put(NormalConstant.OPERATEPIN, commonGoodsDO.getCreateName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_GOODS_DATA);
                    operateLogRpc.saveOperateLog(map);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            	apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            	apiResult.setData(true);
				}else{
					apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
					apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
				}
			}else{
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
	            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

	@Override
	public ApiResult<CommonGoodsResult> getGoods(Long id) {
		ApiResult<CommonGoodsResult> apiResult = new ApiResult<CommonGoodsResult>();
		try {
			GoodsVO goodsVO = commonGoodsManager.getGoods(id);
			if (null != goodsVO) {
				CommonGoodsResult commonGoodsResult = new CommonGoodsResult();
				List<CommonGoodsPicResult> commonGoodsPicResults = new ArrayList<>();
				BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(), commonGoodsPicResults, CommonGoodsPicResult.class);
				BeanUtils.copyProperties(goodsVO, commonGoodsResult);
				commonGoodsResult.setCommonGoodsPicList(commonGoodsPicResults);
				apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            	apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            	apiResult.setData(commonGoodsResult);
			}else{
				apiResult.setCode(PactCommonEnum.NO_GOODS_INFO.getCode());
				apiResult.setMessage(PactCommonEnum.NO_GOODS_INFO.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

	@Override
	public ApiResult<PageBean<CommonGoodsResult>> listGoods(CommonGoodsParam param) {
		ApiResult<PageBean<CommonGoodsResult>> apiResult = new ApiResult<>();
		List<CommonGoodsResult> commonGoodsResults = new ArrayList<>();
		try {
			if (null != param) {
				CommonGoodsQuery query = new CommonGoodsQuery();
				BeanUtils.copyProperties(param, query);
				PageBean<GoodsVO> pageBean = commonGoodsManager.listGoods(query);
				if (pageBean.getTotalItem()>0) {
					PageBean<CommonGoodsResult> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
		            page.setTotalItem(pageBean.getTotalItem());
		            if(null != page && null != page.getData()){
		                for(GoodsVO goodsVO:pageBean.getData()){
		                    CommonGoodsResult commonGoodsResult = new CommonGoodsResult();
		                    List<CommonGoodsPicResult> commonGoodsPicResults = new ArrayList<>();
		                    BeanUtils.copyProperties(goodsVO,commonGoodsResult);
		                    BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(),commonGoodsPicResults , CommonGoodsPicResult.class);
                            commonGoodsResult.setCommonGoodsPicList(commonGoodsPicResults);
		                    commonGoodsResults.add(commonGoodsResult);
		                }
		            }
		            BeanUtils.copyListProperties(commonGoodsResults, page.getData(), CommonGoodsResult.class);
		            apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
		            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
		            apiResult.setData(page);
				}else{
					apiResult.setCode(CommonEnum.NO_CONTENT.getCode());
					apiResult.setMessage(CommonEnum.NO_CONTENT.getMessage());
				}
			}else{
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
	            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

	@Override
	public ApiResult<Boolean> updateGoods(CommonGoodsParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<>();
		try {
			if (null != param) {
				CommonGoodsDO commonGoodsDO = new CommonGoodsDO();
				List<CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
				BeanUtils.copyListProperties(param.getCommonGoodsPicList(), commonGoodsPicDOs, CommonGoodsPicDO.class);
				BeanUtils.copyProperties(param, commonGoodsDO);
				for(CommonGoodsPicDO commonGoodsPicDO:commonGoodsPicDOs){
				    commonGoodsPicDO.setCreateName(param.getModifiedName());
				    commonGoodsPicDO.setModifiedName(param.getModifiedName());
				    commonGoodsPicDO.setIp(param.getIp());
				}
				commonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
				boolean flag = commonGoodsManager.updateGoods(commonGoodsDO);
				if (flag) {
				     Map<String, Object> map = new HashMap<String, Object>();
				     map.put(NormalConstant.SERVICETYPE, StringConsts.OPERATELOG_GOODS_SERVICETYPE);
                     map.put(NormalConstant.SERVICECODE, commonGoodsDO.getId());
                     map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                     map.put(NormalConstant.OPERATEPIN, commonGoodsDO.getModifiedName());
                     map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_GOODS_DATA);
	                 operateLogRpc.saveOperateLog(map);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
		            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
		            apiResult.setData(true);
				}else{
					apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
					apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
				}
			}else{
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
	            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
			}
		}  catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}
	

	@Override
	public ApiResult<Boolean> delGoods(CommonGoodsParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<>();
		try {
		    if(null == param){
		        apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
		        apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
		        return apiResult;
		    }
		    CommonGoodsDO commonGoodsDO = new CommonGoodsDO();
		    BeanUtils.copyProperties(param, commonGoodsDO);
			boolean flag = commonGoodsManager.delGoods(commonGoodsDO);
			if (flag) {
			    Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, StringConsts.OPERATELOG_GOODS_SERVICETYPE);
                map.put(NormalConstant.SERVICECODE, param.getId());
                map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                map.put(NormalConstant.OPERATEPIN, param.getCreateName());
                map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_GOODS_DATA);
                operateLogRpc.saveOperateLog(map);
				apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            apiResult.setData(true);
			}else{
				apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}


	public CommonGoodsManager getCommonGoodsManager() {
		return commonGoodsManager;
	}

	public void setCommonGoodsManager(CommonGoodsManager commonGoodsManager) {
		this.commonGoodsManager = commonGoodsManager;
	}

    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }

    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
    }
}
