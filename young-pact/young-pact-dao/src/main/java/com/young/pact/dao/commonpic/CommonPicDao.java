package com.young.pact.dao.commonpic;

import java.util.List;
import java.util.Map;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;

/**
 * 
* @ClassName: CommonPicDao
* @Description: TODO( 合同公共图片)
* @author HeZeMin
* @date 2018年8月2日 下午10:03:55
*
 */
public interface CommonPicDao {
    /**
     * 
    * @Title: saveCommonPic
    * @Description: TODO( 保存合同照片)
    * @author HeZeMin
    * @param commonPicDOs   合同照片集合
    * @return   返回插入条数
    * @throws DaoException;
    * @throws   异常
     */
    int saveCommonPic(List<CommonPicDO> commonPicDOs) throws DaoException;
    /**
     * 
    * @Title: listCommonPic
    * @Description: TODO( 根据合同编码查询合同照片)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回合同照片集合
    * @throws DaoException;
    * @throws   异常
     */
    List<CommonPicVO> listCommonPic(String pactCode) throws DaoException;
    /**
     * 
    * @Title: removeCommonPic
    * @Description: TODO( 根据合同编码删除合同照片)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int removeCommonPic(String pactCode) throws DaoException;
    /**
     * 
    * @Title: removeNotCommonPic
    * @Description: TODO( 删除 不包含 图片集合数据 的图片！！)
    * @author HeZeMin
    * @param commonPicList  合同图片集合
    * @param pactCode   合同编码
    * @return   返回删除结果
    * @throws DaoException;
    * @throws   异常
     */
    int removeNotCommonPic(Map<String, Object> map) throws DaoException;
    /**
     * 
    * @Title: updateCommonPic
    * @Description: TODO( 批量修改合同图片)
    * @author HeZeMin
    * @param commonPicDOs   合同图片集合
    * @return   返回修改结果
    * @throws DaoException;
    * @throws   异常
     */
    int updateCommonPic(List<CommonPicDO> commonPicDOs) throws DaoException;
}
