package com.young.pact.dao.commongoods;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsQuery;
import com.young.pact.domain.commongoods.GoodsVO;

/**
 * 
* @ClassName: CommonGoodsDao
* @Description: TODO( 物业物品 )
* @author HeZeMin
* @date 2018年8月2日 下午10:05:41
*
 */
public interface CommonGoodsDao {
    /**
     * 
    * @Title: saveCommonGoods
    * @Description: TODO( 保存抄表信息)
    * @author HeZeMin
    * @param commonGoodsDO  抄表信息
    * @return   返回id
    * @throws DaoException;
    * @throws   异常
     */
    Long saveCommonGoods(CommonGoodsDO commonGoodsDO) throws DaoException;
    /**
     * 
    * @Title: removeCommonGoods
    * @Description: TODO( 根据合同编码删除物品信息及物品图片)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int removeCommonGoods(String pactCode) throws DaoException;
    /**
     * @Title: listCommonGoodsByPactCode
     * @Description: TODO(根据合同编码查询物品信息集合 )
     * @author GuoXiaoPeng
     * @param pactCode 合同编码
     * @return 物品信息集合 
     * @throws DaoException;
     * @throws 异常
     */
    List<GoodsVO> listCommonGoodsByPactCode(String pactCode) throws DaoException;
    
    /**
     * 
    * @Title: getGoods
    * @Description: 获取物品详情
    * @author LiuYuChi
    * @param id
    * @return
    * @throws DaoException
    * @throws
     */
    GoodsVO getGoods (Long id)throws DaoException;
    
    /**
     * 
    * @Title: updateGoods
    * @Description: 修改物品信息
    * @author LiuYuChi
    * @param commonGoodsDO
    * @return
    * @throws DaoException
    * @throws
     */
    int updateGoods(CommonGoodsDO commonGoodsDO)throws DaoException ;
    
    /**
     * 
    * @Title: delGoods
    * @Description: 根据物品id删除物品和图片信息
    * @author LiuYuChi
    * @param id
    * @return
    * @throws DaoException
    * @throws
     */
    int delGoods(CommonGoodsDO commonGoodsDO) throws DaoException;
    
    /**
     * 
    * @Title: countGoods
    * @Description: 查询物品数量
    * @author LiuYuChi
    * @param pactCode
    * @return
    * @throws DaoException
    * @throws
     */
    int countGoods(String pactCode) throws DaoException;
    
    /**
     * 
    * @Title: listGoods
    * @Description: 分页查询物品列表
    * @author LiuYuChi
    * @param query
    * @return
    * @throws DaoException
    * @throws
     */
    List<GoodsVO> listGoods(CommonGoodsQuery query) throws DaoException;
}
