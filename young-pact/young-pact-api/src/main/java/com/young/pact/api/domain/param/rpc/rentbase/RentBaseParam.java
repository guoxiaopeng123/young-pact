package com.young.pact.api.domain.param.rpc.rentbase;

import java.io.Serializable;

/**
 * @描述 : 托出合同
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月15日 下午4:48:58
 */
public class RentBaseParam implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields state:合同状态*/
    private Integer state;
    /*** @Fields isEffective:是否生效*/
    private Integer isEffective;
    
    public RentBaseParam(){}

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }
}
