package com.young.pact.domain.commonbelonger;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: CommonBelongerVO
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月1日 下午9:05:39
*
 */
public class CommonBelongerVO extends BaseDomain {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields pactCode:合同编码*/
    private String pactCode;
    /*** @Fields pactType:合同类型*/
    private String pactType;
    /*** @Fields userPin:负责人pin*/
    private String userPin;
    /*** @Fields userName:负责人姓名*/
    private String userName;
    /*** @Fields userTel:负责人电话*/
    private String userTel;
    /*** @Fields userRole:负责人角色*/
    private String userRole;
    /*** @Fields deptCode:负责人部门编码*/
    private String deptCode;
    /*** @Fields deptName:负责人部门*/
    private String deptName;
    
    
    public CommonBelongerVO() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPactCode() {
        return pactCode;
    }
    public void setPactCode(String pactCode) {
        this.pactCode = pactCode;
    }
    public String getPactType() {
        return pactType;
    }
    public String getDeptCode() {
        return deptCode;
    }


    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }


    public void setPactType(String pactType) {
        this.pactType = pactType;
    }
    public String getUserPin() {
        return userPin;
    }
    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserTel() {
        return userTel;
    }
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
