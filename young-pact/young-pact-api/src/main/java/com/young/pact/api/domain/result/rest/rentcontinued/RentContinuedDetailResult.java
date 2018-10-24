package com.young.pact.api.domain.result.rest.rentcontinued;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;

/**
 * @描述 : 续签协议详情
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月11日 下午3:40:23
 */
public class RentContinuedDetailResult implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;

    private RentContinuedResult rentContinuedResult;
    /** @Fields urlList:协议照片(集合)**/
    private List<CommonPicResult> urlList;
    /** @Fields roomResult:转租房间**/
    private RoomResult roomResult;
    /** @Fields oldPact:老合同信息**/
    private RentBaseResult oldPact;
    /** @Fields newPact:新合同信息**/
    private RentBaseResult newPact;
    public RentContinuedDetailResult(){
        
    }
    public RentContinuedResult getRentContinuedResult() {
        return rentContinuedResult;
    }
    public void setRentContinuedResult(RentContinuedResult rentContinuedResult) {
        this.rentContinuedResult = rentContinuedResult;
    }
    public List<CommonPicResult> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicResult> urlList) {
        this.urlList = urlList;
    }
    public RoomResult getRoomResult() {
        return roomResult;
    }
    public void setRoomResult(RoomResult roomResult) {
        this.roomResult = roomResult;
    }
    public RentBaseResult getOldPact() {
        return oldPact;
    }
    public void setOldPact(RentBaseResult oldPact) {
        this.oldPact = oldPact;
    }
    public RentBaseResult getNewPact() {
        return newPact;
    }
    public void setNewPact(RentBaseResult newPact) {
        this.newPact = newPact;
    }
}
