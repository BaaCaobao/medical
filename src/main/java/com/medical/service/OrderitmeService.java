package com.medical.service;

import com.medical.entity.Orderitme;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单项 服务类
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
public interface OrderitmeService extends IService<Orderitme> {
    List<Orderitme> selectOrderItme();
    List<Orderitme> select( int uid);


}
