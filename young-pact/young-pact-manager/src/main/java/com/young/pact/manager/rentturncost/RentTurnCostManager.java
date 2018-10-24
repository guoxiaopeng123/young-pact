package com.young.pact.manager.rentturncost;

import java.util.List;

import com.young.pact.domain.rentturncost.RentTurnCostVO;

/**
 * @描述 :  转租协议转让费用
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月9日 下午1:49:26
 */
public interface RentTurnCostManager {

    List<RentTurnCostVO> listTurnCostByReletCode(String reletCode);
}
