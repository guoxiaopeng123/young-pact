package com.young.pact.domain.statistics;
import java.util.Date;

import com.young.common.domain.BaseDomain;

/**
 * 
 * @ClassName: AgendaDO
 * @Description: 首页待办数据统计实体
 * @author HeFengNian
 * @date 2018年9月13日 下午1:56:55
 */
public class AgendaDO extends BaseDomain {

    private static final long serialVersionUID = 1L;
    
    /*** @Fields id : id */
    private Long id;
    /*** @Fields serviceCode : 资源编码 */
    private String serviceCode;
    /*** @Fields type : 类型 */
    private String type;
    /*** @Fields isDo : 是否办完 */
    private Integer isDo;
    /*** @Fields pin : 相关人pin */
    private String pin;
    /*** @Fields name : 相关人姓名 */
    private String name;
    /*** @Fields deptCode : 相关人部门编码 */
    private String deptCode;
    /*** @Fields deptName : 相关人部门名称  */
    private String deptName;
    /*** @Fields date : 时间  */
    private Date date;
    
    public AgendaDO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsDo() {
        return isDo;
    }

    public void setIsDo(Integer isDo) {
        this.isDo = isDo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }  
    
    
}
