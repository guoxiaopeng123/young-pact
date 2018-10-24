package com.young.pact.api.domain.param.rest.commongoods;

import java.io.Serializable;
/**
 * 
* @ClassName: CommonGoodsPicParam
* @Description: TODO( 物品图片)
* @author HeZeMin
* @date 2018年8月2日 下午3:10:39
*
 */
public class CommonGoodsPicParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields goodsId:物业交接物品id*/
    private Long goodsId;
    /*** @Fields url:url*/
    private String url;
    /*** @Fields sort:排序*/
    private Integer sort;
    
    
    public CommonGoodsPicParam() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
