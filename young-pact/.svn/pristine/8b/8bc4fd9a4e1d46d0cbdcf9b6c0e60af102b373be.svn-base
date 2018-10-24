package com.young.pact.manager.commongoods;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsQuery;
import com.young.pact.domain.commongoods.GoodsVO;

/**
 * 
* @ClassName: CommonGoodsManager
* @Description: 公共物品
* @author LiuYuChi
* @date 2018年8月14日 上午11:24:50
*
 */
public interface CommonGoodsManager {
	
	/**
	 * 
	* @Title: insertGoods
	* @Description: 新增物品信息
	* @author LiuYuChi
	* @param commonGoodsDO
	* @return
	* @throws
	 */
	boolean insertGoods(CommonGoodsDO commonGoodsDO);
	
	/**
	 * 
	* @Title: getGoods
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author LiuYuChi
	* @param id
	* @return
	* @throws
	 */
	GoodsVO getGoods(Long id);
	
	/**
	 * 
	* @Title: updateGoods
	* @Description: 修改物品
	* @author LiuYuChi
	* @param commonGoodsDO
	* @return
	* @throws
	 */
	boolean updateGoods(CommonGoodsDO commonGoodsDO);
	
	/**
	 * 
	* @Title: listGoods
	* @Description: 物品列表
	* @author LiuYuChi
	* @param pactCode
	* @return
	* @throws
	 */
	PageBean<GoodsVO> listGoods(CommonGoodsQuery commonGoodsQuery);
	
	/**
	 * 
	* @Title: listGood
	* @Description: 查询物品集合
	* @author LiuYuChi
	* @param pactCode
	* @return
	* @throws
	 */
	List<GoodsVO> listGood(String pactCode);
	/**
	* @Title: delGoods
	* @Description: TODO( 删除物品以及图片 )
	* @author GuoXiaoPeng
	* @param commonGoodsDO 合同编码和物品id
	* @return 成功true，失败false
	* @throws 异常
	 */
    boolean delGoods(CommonGoodsDO commonGoodsDO);
}
