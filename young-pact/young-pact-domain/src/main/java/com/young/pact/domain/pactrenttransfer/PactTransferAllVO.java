package com.young.pact.domain.pactrenttransfer;

import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonpic.CommonPicVO;

public class PactTransferAllVO extends BaseDomain{

	private static final long serialVersionUID = 1L;
	/*** @Fields pactRentTransferDO:协议信息*/
	private PactRentTransferVO pactRentTransferVO;
	/*** @Fields commonPicList:协议图片*/
	private List<CommonPicVO> commonPicList;
	public PactRentTransferVO getPactRentTransferVO() {
		return pactRentTransferVO;
	}
	public void setPactRentTransferVO(PactRentTransferVO pactRentTransferVO) {
		this.pactRentTransferVO = pactRentTransferVO;
	}
	public List<CommonPicVO> getCommonPicList() {
		return commonPicList;
	}
	public void setCommonPicList(List<CommonPicVO> commonPicList) {
		this.commonPicList = commonPicList;
	}
	
}
