package com.young.pact.manager.commongoods.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commongoods.CommonGoodsPicVO;
import com.young.pact.domain.commongoods.CommonGoodsQuery;
import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.manager.commongoods.CommonGoodsManager;

/**
 * 
* @ClassName: CommonGoodsManagerImpl
* @Description: 公共物品
* @author LiuYuChi
* @date 2018年8月14日 上午11:29:30
*
 */
@Component("commonGoodsManager")
public class CommonGoodsManagerImpl implements CommonGoodsManager{

	private static final Log LOG = LogFactory.getLog(CommonGoodsManagerImpl.class);
	private PlatformTransactionManager transactionManager;
	private CommonGoodsDao commonGoodsDao;
	private CommonGoodsPicDao commonGoodsPicDao;
	
	@Override
	public boolean insertGoods(final CommonGoodsDO commonGoodsDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {
        			Long flag = commonGoodsDao.saveCommonGoods(commonGoodsDO);
        			if (flag >0) {
        				if (commonGoodsDO.getCommonGoodsPicList()!=null&&commonGoodsDO.getCommonGoodsPicList().size()>0) {
        					List<CommonGoodsPicDO> commonGoodsPicList = commonGoodsDO.getCommonGoodsPicList();
        					for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsPicList) {
        						commonGoodsPicDO.setGoodsId(flag);
							}
        					int flagP = commonGoodsPicDao.saveCommonGoodsPic(commonGoodsPicList);
        					if (flagP>0) {
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
        		} catch (Exception e) {
        			LOG.error(ErrorPactConsts.INSERT_COMMON_GOODS_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.INSERT_COMMON_GOODS_ERROR,e);
        		}
            }
        });
	}

	@Override
	public GoodsVO getGoods(Long id) {
		try {
			return commonGoodsDao.getGoods(id);
		} catch (Exception e) {
			LOG.error(ErrorPactConsts.GET_COMMON_GOODS_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_COMMON_GOODS_ERROR,e);
		}
	}

	@Override
	public boolean updateGoods(final CommonGoodsDO commonGoodsDO) {
		TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
        	@Override
            public Boolean doInTransaction(TransactionStatus status) {
        		try {
        			int flag = commonGoodsDao.updateGoods(commonGoodsDO);
        			if (flag <= 0) {
        			    status.setRollbackOnly();
                        return false;
					}
        			int delCount = commonGoodsPicDao.removeGoodsByGoodId(commonGoodsDO.getId());
                    if(delCount <=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    
                    int goodPicCount =  commonGoodsPicDao.saveCommonGoodsPic(commonGoodsDO.getCommonGoodsPicList());
                    if(goodPicCount<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
        		} catch (Exception e) {
        			LOG.error(ErrorPactConsts.UPDATE_COMMON_GOODS_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_COMMON_GOODS_ERROR,e);
        		}
        	}
        });
	}

	

	@Override
	public PageBean<GoodsVO> listGoods(CommonGoodsQuery query) {
		try {
            Integer count =  commonGoodsDao.countGoods(query.getPactCode());
            PageBean<GoodsVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<GoodsVO> goodsList = commonGoodsDao.listGoods(query);
                List <Long> goodsIds = new ArrayList<>();
                if(null!=goodsList&&goodsList.size()>0){
                    for (GoodsVO goodsVO :goodsList) {
                        goodsIds.add(goodsVO.getId());
                    }
                    List<CommonGoodsPicVO> commonGoodsPicVOs = commonGoodsPicDao.listGoodsPicByGdIds(goodsIds);
                    for (GoodsVO goodsVO :goodsList) {
                        List<CommonGoodsPicVO> commonGoodsPicList = findgoodsPicList(goodsVO.getId(),commonGoodsPicVOs);
                        goodsVO.setCommonGoodsPicList(commonGoodsPicList );
                    }
                }
                page.addAll(goodsList);
            }
            return page;
		} catch (Exception e) {
			LOG.error(ErrorPactConsts.LIST_COMMON_GOODS_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_COMMON_GOODS_ERROR,e);
		}
	}
	
    /**
    * @Title: findgoodsPicList
    * @Description: TODO( 根据物品id从物品图片集合中筛选出某一物品的所有物品图片 )
    * @author GuoXiaoPeng
    * @param id 物品唯一标识
    * @param commonGoodsPicVOs 物品图片集合
    * @return 某一物品的所有物品图片
     */
    private List<CommonGoodsPicVO> findgoodsPicList(Long id, List<CommonGoodsPicVO> commonGoodsPicVOs) {
        List<CommonGoodsPicVO> list = new ArrayList<>();
        if(null!=commonGoodsPicVOs&&commonGoodsPicVOs.size()>0){
            for(CommonGoodsPicVO commonGoodsPicVO:commonGoodsPicVOs){
                if(id.longValue()==commonGoodsPicVO.getGoodsId().longValue()){
                    list.add(commonGoodsPicVO);
                }
            }
        }
        Collections.sort(list, new Comparator<CommonGoodsPicVO>() {
            @Override
            public int compare(CommonGoodsPicVO o1, CommonGoodsPicVO o2) {
                return o1.getSort()-o2.getSort();
            }
        });
        return list;
    }

    @Override
	public List<GoodsVO> listGood(String pactCode) {
		try {
			return commonGoodsDao.listCommonGoodsByPactCode(pactCode);
		} catch (Exception e) {
			LOG.error(ErrorPactConsts.LIST_COMMON_GOODS_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_COMMON_GOODS_ERROR,e);
		}
	}


	public TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
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

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

    @Override
    public boolean delGoods(CommonGoodsDO commonGoodsDO) {
        try {
            int flag = commonGoodsDao.delGoods(commonGoodsDO);
            if (flag >0) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.DELETE_COMMON_GOODS_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.DELETE_COMMON_GOODS_ERROR,e);
        }
    }
}
