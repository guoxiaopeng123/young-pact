package com.young.pact.api.domain.result.rest.common;

import java.io.Serializable;

/**
 * @描述 : 银行账号
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月26日 下午1:56:51
 */
public class BankAccountResult implements Serializable{
    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields name:姓名*/
    private String name;
    /*** @Fields tel:电话*/
    private String tel;
    /*** @Fields account:账号*/
    private String account;
    /*** @Fields bank:银行*/
    private String bank;
    /*** @Fields openBank:开户行*/
    private String openBank;
    public BankAccountResult(){}
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
}
