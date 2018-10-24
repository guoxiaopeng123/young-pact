package com.young.pact.domain.financereceiptpay;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: financeReceiptPayQuery
* @Description: TODO( 收支)
* @author HeZeMin
* @date 2018年8月1日 下午8:42:37
*
 */
public class FinanceReceiptPayQuery extends BaseDomain {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields pid:pid*/
    private Long pid;
    /*** @Fields type:收支类型(0 应收 1实收 2 应支 3 实支)*/
    private Integer type;
    /*** @Fields pactCode:合同编号*/
    private String pactCode;
    /*** @Fields pactType:合同类型*/
    private String pactType;
    /*** @Fields incomeExpendType:收支大类*/
    private String incomeExpendType;
    /*** @Fields costType:收支费用类型*/
    private String costType;
    /*** @Fields costSubject:收支费用类型科目*/
    private String costSubject;
    /*** @Fields costAmount:费用金额*/
    private Double costAmount;
    /*** @Fields startTime:收支期间开始时间*/
    private Date startTime;
    /*** @Fields endTime:收支期间结束时间*/
    private Date endTime;
    /*** @Fields describe:收支描述*/
    private String describe;
    /*** @Fields costTime:收支时间*/
    private Date costTime;
    /*** @Fields houseCode:相关房源编号*/
    private String houseCode;
    /*** @Fields roomCode:相关房间号*/
    private String roomCode;
    /*** @Fields address:相关房源物业地址*/
    private String address;
    /*** @Fields personnelCode:相关业主/租客编号*/
    private String personnelCode;
    /*** @Fields customerRole:相关客户角色*/
    private String customerRole;
    /*** @Fields payeeObject:收付款人对象*/
    private String payeeObject;
    /*** @Fields name:收付款人姓名*/
    private String name;
    /*** @Fields tel:收付款人电话*/
    private String tel;
    /*** @Fields account:收付款人账号*/
    private String account;
    /*** @Fields bank:收付款人银行*/
    private String bank;
    /*** @Fields openBank:收付款人开户行*/
    private String openBank;
    /*** @Fields costWay:收支方式*/
    private String costWay;
    /*** @Fields costState:费用状态(1代收款 2部分收款 3已收款 4待付款 5部分付款 6已付款 7待审核 8已审核 9已驳回)*/
    private Integer costState;
    /*** @Fields remark:审核备注*/
    private String remark;
    /*** @Fields overdueDay:逾期天数*/
    private String overdueDay;
    /*** @Fields isDelete:删除状态(0未删除 1已删除)*/
    private Integer isDelete;
    /*** @Fields isValid:是否生效(0未生效 1已生效)*/
    private Integer isValid;
    /*** @Fields costTimeMin:收支时间最小*/
    private String costTimeMin;
    /*** @Fields costTimeMax:收支时间最大*/
    private String costTimeMax;
    /*** @Fields keyword:关键字*/
    private String keyword;
    /*** @Fields deptCode:部门编码*/
    private String deptCode;
    /*** @Fields overdueDayMin:逾期天数最小*/
    private String overdueDayMin;
    /*** @Fields overdueDayMax:逾期天数最大*/
    private String overdueDayMax;
    /*** @Fields decoration:装修类型*/
    private String decoration;
    /*** @Fields costTimeStr:收支时间*/
    private String costTimeStr;
    /*** @Fields pinList:本人及下属PIN集合*/
    private List<String> pinList;
    /*** @Fields deptList:本部门及下属部门编码集合*/
    private List<String> deptList;
    /*** @Fields scope:作用域*/
    private Integer scope;
    /*** @Fields userPin:登录人PIN*/
    private String userPin;
    /*** @Fields position:职位*/
    private Integer position;
    /*** @Fields costList:费用状态集合*/
    private List<Integer> costList;
    public FinanceReceiptPayQuery() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDeptCode() {
        return deptCode;
    }


    public Integer getIsValid() {
        return isValid;
    }


    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }


    public String getDecoration() {
        return decoration;
    }


    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }


    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }


    public String getOverdueDayMin() {
        return overdueDayMin;
    }


    public void setOverdueDayMin(String overdueDayMin) {
        this.overdueDayMin = overdueDayMin;
    }


    public String getOverdueDayMax() {
        return overdueDayMax;
    }


    public void setOverdueDayMax(String overdueDayMax) {
        this.overdueDayMax = overdueDayMax;
    }


    public Long getPid() {
        return pid;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }
    public String getCostTimeMin() {
        return costTimeMin;
    }


    public String getKeyword() {
        return keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public void setCostTimeMin(String costTimeMin) {
        this.costTimeMin = costTimeMin;
    }


    public String getCostTimeMax() {
        return costTimeMax;
    }


    public void setCostTimeMax(String costTimeMax) {
        this.costTimeMax = costTimeMax;
    }


    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
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
    public void setPactType(String pactType) {
        this.pactType = pactType;
    }
    public String getIncomeExpendType() {
        return incomeExpendType;
    }
    public void setIncomeExpendType(String incomeExpendType) {
        this.incomeExpendType = incomeExpendType;
    }
    public String getCostType() {
        return costType;
    }
    public void setCostType(String costType) {
        this.costType = costType;
    }
    public String getCostSubject() {
        return costSubject;
    }
    public void setCostSubject(String costSubject) {
        this.costSubject = costSubject;
    }
    public Double getCostAmount() {
        return costAmount;
    }
    public void setCostAmount(Double costAmount) {
        this.costAmount = costAmount;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public Date getCostTime() {
        return costTime;
    }
    public void setCostTime(Date costTime) {
        this.costTime = costTime;
    }
    public String getHouseCode() {
        return houseCode;
    }
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPersonnelCode() {
        return personnelCode;
    }
    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }
    public String getCustomerRole() {
        return customerRole;
    }
    public void setCustomerRole(String customerRole) {
        this.customerRole = customerRole;
    }
    public String getPayeeObject() {
        return payeeObject;
    }
    public void setPayeeObject(String payeeObject) {
        this.payeeObject = payeeObject;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public String getOpenBank() {
        return openBank;
    }
    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }
    public String getCostWay() {
        return costWay;
    }
    public void setCostWay(String costWay) {
        this.costWay = costWay;
    }
    public Integer getCostState() {
        return costState;
    }
    public void setCostState(Integer costState) {
        this.costState = costState;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getOverdueDay() {
        return overdueDay;
    }
    public void setOverdueDay(String overdueDay) {
        this.overdueDay = overdueDay;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }


    public String getCostTimeStr() {
        return costTimeStr;
    }
    public void setCostTimeStr(String costTimeStr) {
        this.costTimeStr = costTimeStr;
    }


    public List<String> getPinList() {
        return pinList;
    }


    public void setPinList(List<String> pinList) {
        this.pinList = pinList;
    }


    public List<String> getDeptList() {
        return deptList;
    }


    public void setDeptList(List<String> deptList) {
        this.deptList = deptList;
    }


    public Integer getScope() {
        return scope;
    }


    public void setScope(Integer scope) {
        this.scope = scope;
    }


    public String getUserPin() {
        return userPin;
    }


    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }


    public Integer getPosition() {
        return position;
    }


    public void setPosition(Integer position) {
        this.position = position;
    }


    public List<Integer> getCostList() {
        return costList;
    }


    public void setCostList(List<Integer> costList) {
        this.costList = costList;
    }
    
}
