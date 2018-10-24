package com.young.pact.domain.commonmeterread;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: CommonMeterReadPicDO
* @Description: TODO( 抄表图片)
* @author HeZeMin
* @date 2018年8月1日 下午9:23:22
*
 */
public class CommonMeterReadPicVO extends BaseDomain {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields meterReadId:抄表id*/
    private Long meterReadId;
    /*** @Fields url:url*/
    private String url;
    /*** @Fields sort:排序*/
    private Integer sort;
    
    
    public CommonMeterReadPicVO() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMeterReadId() {
        return meterReadId;
    }
    public void setMeterReadId(Long meterReadId) {
        this.meterReadId = meterReadId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
