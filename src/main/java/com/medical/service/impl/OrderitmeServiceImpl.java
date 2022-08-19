package com.medical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medical.entity.Orderitme;
import com.medical.entity.Orders;
import com.medical.mapper.OrderitmeMapper;
import com.medical.mapper.OrdersMapper;
import com.medical.service.OrderitmeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 订单项 服务实现类
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
@Service
public class OrderitmeServiceImpl extends ServiceImpl<OrderitmeMapper, Orderitme> implements OrderitmeService {
@Autowired
OrderitmeMapper orderitmeMapper;
    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Orderitme> selectOrderItme() {
        return orderitmeMapper.selectOrderItme();
    }

    @Override
    public List<Orderitme> select(int uid) {
        return  orderitmeMapper.select(uid);
    }

    /**
     * 以随机数创建订单号
     * @return
     */
    public Integer setOrder(){
        ThreadLocalRandom random=ThreadLocalRandom.current();
        String s =new String();
        for(int i=0;i<5;i++){
            Integer number=random.nextInt(10);
            s+=number;
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(date);
        String OrderNumber=format+s;
        Integer orderNum=Integer.valueOf(OrderNumber);

        Orders orders = new Orders();
        orders.setOrderState("未付款");
        orders.setOrderNumber(orderNum);
        ordersMapper.insert(orders);

        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("order_number",OrderNumber);
        Orders order = ordersMapper.selectOne(wrapper);
        return order.getId();
    }
}
