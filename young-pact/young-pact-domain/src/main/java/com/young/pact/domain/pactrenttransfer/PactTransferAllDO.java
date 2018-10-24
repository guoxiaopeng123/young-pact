package com.young.pact.domain.pactrenttransfer;

import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonpic.CommonPicDO;

/**
 * 
* @ClassName: PactTransferAllDO
* @Description: 调房协议所有属性集合
* @author LiuYuChi
* @date 2018年8月10日 下午2:49:25
*
 */
public class PactTransferAllDO extends BaseDomain{

	private static final long serialVersionUID = 1L;
	/*** @Fields pactRentTransferDO:协议信息*/
	private PactRentTransferDO pactRentTransferDO;
	/*** @Fields commonPicList:协议图片*/
	private List<CommonPicDO> commonPicList;
	public PactRentTransferDO getPactRentTransferDO() {
		return pactRentTransferDO;
	}
	public void setPactRentTransferDO(PactRentTransferDO pactRentTransferDO) {
		this.pactRentTransferDO = pactRentTransferDO;
	}
	public List<CommonPicDO> getCommonPicList() {
		return commonPicList;
	}
	public void setCommonPicList(List<CommonPicDO> commonPicList) {
		this.commonPicList = commonPicList;
	}
	
}
