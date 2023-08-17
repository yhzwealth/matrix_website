package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Bbs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.bbs.BbsListPO;
import com.chuang.bootplus.po.bbs.BbsSendPO;
import com.chuang.bootplus.vo.bbs.BbsListVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBG
 * @since 2021-09-01
 */
public interface BbsService extends IService<Bbs> {

    ApiResponse<BbsListVO> bbsList(BbsListPO po);

    ApiResponse<Void> bbsSend(BbsSendPO po);

}
