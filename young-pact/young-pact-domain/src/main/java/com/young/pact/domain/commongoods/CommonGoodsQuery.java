package com.young.pact.domain.commongoods;

import com.young.common.domain.BaseDomain;

/**
 * 
* @ClassName: CommonGoodsQuery
* @Description: 物品查询类
* @author LiuYuChi
* @date 2018年8月14日 下午12:00:20
*
 */
public class CommonGoodsQuery extends BaseDomain{
	
	private static final long serialVersionUID = 1L;
	
	/*** @Fields pactCode:合同编码*/
    private String pactCode;

	public String getPactCode() {
		return pactCode;
	}
	public void setPactCode(String pactCode) {
		this.pactCode = pactCode;
	}
}
