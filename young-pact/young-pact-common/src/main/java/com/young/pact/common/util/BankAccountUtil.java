package com.young.pact.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: BankAccountUtil
* @Description: TODO( 青春界各部门下银行账号 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:40:44
*
 */
public class BankAccountUtil {

    /*** @Fields banks:银行账号集合*/
    private List<BankAccount> banks;
    /**
    * @ClassName: BankAccount
    * @Description: TODO( 银行账号)
    * @author GuoXiaoPeng
    * @date 2018年8月26日 下午1:57:59
    */
    public class BankAccount implements Serializable{
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
        public  BankAccount(){
            
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
    }
    public List<BankAccount> getBanks() {
        return banks;
    }
    public void setBanks(List<BankAccount> banks) {
        this.banks = banks;
    }
}
