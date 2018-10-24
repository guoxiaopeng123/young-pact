package com.young.pact.dao.commongoods;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commongoods.CommonGoodsPicVO;

/**
 * 
* @ClassName: CommonGoodsPicDao
* @Description: TODO( 物品图片)
* @author HeZeMin
* @date 2018年8月2日 下午10:05:53
*
 */
public interface CommonGoodsPicDao {
    /**
     * 
    * @Title: saveCommonGoodsPic
    * @Description: TODO( 保存抄表图片集合)
    * @author HeZeMin
    * @param commonGoodsPicDOs  抄表图片集合
    * @return   返回插入条数
    * @throws DaoException;
    * @throws   异常
     */
    int saveCommonGoodsPic(List<CommonGoodsPicDO> commonGoodsPicDOs) throws DaoException;
    
    /**
     * 
    * @Title: updateCommonGoodsPic
    * @Description: 修改物品图片
    * @author LiuYuChi
    * @param commonGoodsPicDO
    * @return
    * @throws DaoException
    * @throws
     */
    int updateCommonGoodsPic(List<CommonGoodsPicDO> commonGoodsPicDO) throws DaoException;
    /**
    * @Title: listGoodsPicByGdIds
    * @Description: TODO( 根据物品id集合查询物品图片集合 )
    * @author GuoXiaoPeng
    * @param goodsIds 物品id集合
    * @return 物品图片集合
    * @throws DaoException;
    * @throws 异常
     */
    List<CommonGoodsPicVO> listGoodsPicByGdIds(List<Long> goodsIds)throws DaoException;
    /**
    * @Title: removeGoodsByGoodId
    * @Description: TODO(根据物品id删除物品图片 )
    * @author GuoXiaoPeng
    * @param id 物品id
    * @return  返回记录数
    * @throws DaoException
    * @throws 异常
     */
    int removeGoodsByGoodId(Long id)throws DaoException;
}
