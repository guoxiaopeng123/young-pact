package com.young.pact.api.domain.param.rest.financereceiptpay;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @ClassName: FinanceReceiptPayQueryParam
* @Description: TODO( 收支)
* @author HeZeMin
* @date 2018年8月2日 上午10:29:12
*
 */
public class FinanceReceiptPayQueryParam implements Serializable {
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
    /*** @Fields keyword:关键字*/
    private String keyword;
    /*** @Fields pageIndex:第几页*/
    private int pageIndex = 1;
    /*** @Fields pageSize:每页个数*/
    private int pageSize = 10;
    /*** @Fields costTimeMin:收支时间最小*/
    private String costTimeMin;
    /*** @Fields costTimeMax:收支时间最大*/
    private String costTimeMax;
    /*** @Fields deptCode:部门编码*/
    private String deptCode;
    /*** @Fields decoration:装修类型*/
    private String decoration;
    /*** @Fields overdueDayMin:逾期天数最小*/
    private String overdueDayMin;
    /*** @Fields overdueDayMax:逾期天数最大*/
    private String overdueDayMax;
    /*** @Fields createName:创建人pin*/
    private String createName;
    /*** @Fields modifiedName:修改人pin*/
    private String modifiedName;
    /*** @Fields ip:ip地址*/
    private String ip;
    /*** @Fields scope:数据权限作用域*/
    private Integer scope;
    public FinanceReceiptPayQueryParam() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPid() {
        return pid;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }
    public String getKeyword() {
        return keyword;
    }


    public String getCreateName() {
        return createName;
    }


    public String getDecoration() {
        return decoration;
    }


    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }


    public void setCreateName(String createName) {
        this.createName = createName;
    }


    public String getModifiedName() {
        return modifiedName;
    }


    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getCostTimeMin() {
        return costTimeMin;
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


    public String getDeptCode() {
        return deptCode;
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


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public int getPageIndex() {
        return pageIndex;
    }


    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
    public Integer getScope() {
        return scope;
    }
    public void setScope(Integer scope) {
        this.scope = scope;
    }

}
