package com.young.pact.api.domain.result.rest.commonmeterread;

import java.io.Serializable;
/**
 * 
* @ClassName: CommonMeterReadPicResult
* @Description: TODO(抄表图片 )
* @author HeZeMin
* @date 2018年8月2日 下午2:53:53
*
 */
public class CommonMeterReadPicResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields meterReadId:抄表id*/
    private Long meterReadId;
    /*** @Fields url:url*/
    private String url;
    /*** @Fields sort:排序*/
    private Integer sort;
    
    
    public CommonMeterReadPicResult() {
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
