package com.young.pact.domain.pactrenttermination;

import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
/**
 * 
* @ClassName: PactRentTerminationAllVO
* @Description:解约协议内容+图片
* @author LiuYuChi
* @date 2018年8月12日 下午4:21:06
*
 */
public class PactRentTerminationAllVO extends BaseDomain{

	private static final long serialVersionUID = 1L;
	
	/*** @Fields pactRentTerminationVO:解约协议信息*/
	private PactRentTerminationVO pactRentTerminationVO;
	/*** @Fields commonPicVO:解约协议图片*/
	private List<CommonPicVO> commonPicVO;
	/*** @Fields financeReceiptPayList:收支*/
    private List<FinanceReceiptPayVO> financeReceiptPayList;
	
	public PactRentTerminationVO getPactRentTerminationVO() {
		return pactRentTerminationVO;
	}
	public void setPactRentTerminationVO(PactRentTerminationVO pactRentTerminationVO) {
		this.pactRentTerminationVO = pactRentTerminationVO;
	}
	public List<CommonPicVO> getCommonPicVO() {
		return commonPicVO;
	}
	public void setCommonPicVO(List<CommonPicVO> commonPicVO) {
		this.commonPicVO = commonPicVO;
	}
	public List<FinanceReceiptPayVO> getFinanceReceiptPayList() {
		return financeReceiptPayList;
	}
	public void setFinanceReceiptPayList(
			List<FinanceReceiptPayVO> financeReceiptPayList) {
		this.financeReceiptPayList = financeReceiptPayList;
	}
}
