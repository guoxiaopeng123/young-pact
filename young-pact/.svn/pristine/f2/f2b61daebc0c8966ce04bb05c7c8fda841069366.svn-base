package com.young.pact.dao.rentcommonowner;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;

/**
 * @描述 : 托出租客共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月3日 下午6:37:32
 */
public interface CommonOwnerDao {

    
    /**
     * @Title: savaCommonOwner
     * @Description: TODO(保存托出租客共同居住人  )
     * @author GuoXiaoPeng
     * @param rentCommonOwnerDO 托出租客共同居住人集合
     * @return 插入条数
     */
    int savaCommonOwner(List<RentCommonOwnerDO> commonOwnerDOList)throws DaoException;
    /**
    * @Title: listCommonOwnerByRenterCode
    * @Description: TODO(根据托出租客编码查询托出租客共同居住人集合 )
    * @author GuoXiaoPeng
    * @param renterCode 托出租客编码
    * @return 共同居住人集合
    * @throws
     */
    List<RentCommonOwnerVO> listCommonOwnerByRenterCode(String renterCode)throws DaoException;
    /**
    * @Title: updateCommonOwner
    * @Description: TODO( 修改托出合同租客共同居住人)
    * @author GuoXiaoPeng
    * @param cohabitantList 托出合同租客共同居住人集合
    * @return 成功true 失败false
    * @throws 异常
     */
    int updateCommonOwner(List<RentCommonOwnerDO> cohabitantList)throws DaoException;
    /**
    * @Title: removeCommonOwner
    * @Description: TODO(根据租客编码删除租客共同居住人)
    * @author GuoXiaoPeng
    * @param renterCode 租客编码
    * @throws DaoException 异常
     */
    void removeCommonOwner(String renterCode)throws DaoException;
}
