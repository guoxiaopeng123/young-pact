package com.young.pact.manager.rentcommonowner;

import java.util.List;

import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;

/**
 * @描述 : 托出合同租客共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月5日 下午3:50:54
 */
public interface CommonOwnerManager {

    /**
     * @Title: listCommonOwnerByRenterCode
     * @Description: TODO(根据托出租客编码查询托出租客共同居住人集合 )
     * @author GuoXiaoPeng
     * @param renterCode 托出租客编码
     * @return 共同居住人集合
     * @throws
     */
     List<RentCommonOwnerVO> listCommonOwnerByRenterCode(String renterCode);
}
