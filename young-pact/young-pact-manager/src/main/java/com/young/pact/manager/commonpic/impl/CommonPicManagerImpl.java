package com.young.pact.manager.commonpic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.manager.commonpic.CommonPicManager;

/**
 * @描述 : 照片
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月12日 下午6:24:56
 */
@Component("commonPicManager")
public class CommonPicManagerImpl implements CommonPicManager {

    private static final Log LOG = LogFactory.getLog(CommonPicManagerImpl.class);
    private CommonPicDao commonPicDao;
    @Override
    public List<CommonPicVO> listCommonPicByPactCode(String code) {
        try {
            List<CommonPicVO> commonPicVOs = commonPicDao.listCommonPic(code);
            return commonPicVOs;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_COMMON_PIC_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_COMMON_PIC_ERROR, e);
        }
    }
    public CommonPicDao getCommonPicDao() {
        return commonPicDao;
    }
    public void setCommonPicDao(CommonPicDao commonPicDao) {
        this.commonPicDao = commonPicDao;
    }
}
