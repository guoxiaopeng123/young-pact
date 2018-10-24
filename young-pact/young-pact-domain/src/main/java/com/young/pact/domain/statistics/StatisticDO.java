package com.young.pact.domain.statistics;
import java.util.Date;

import com.young.common.domain.BaseDomain;

/**
 * 
 * @ClassName: StatisticDO
 * @Description: 首页统计实体
 * @author HeFengNian
 * @date 2018年9月13日 下午1:44:53
 */
public class StatisticDO extends BaseDomain {

    private static final long serialVersionUID = 1L;
    
    /*** @Fields id : id */
    private Long id;
    /*** @Fields serviceCode : 资源编码 */
    private String serviceCode;
    /*** @Fields pactType : 合同类型 */
    private String pactType;
    /*** @Fields pin : 维护人pin */
    private String pin;
    /*** @Fields name : 维护人姓名 */
    private String name;
    /*** @Fields deptCode : 维护人部门编码 */
    private String deptCode;
    /*** @Fields deptName : 维护人部门名称  */
    private String deptName;
    /*** @Fields date : 时间  */
    private Date date;
    /*** @Fields year : 年  */
    private String year;
    /*** @Fields month : 月 */
    private String month;
    /*** @Fields day : 日 */
    private String day;
    
    public StatisticDO() {}

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

    public String getPactType() {
        return pactType;
    }

    public void setPactType(String pactType) {
        this.pactType = pactType;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    
    
}
