package com.medical.controller;


import com.medical.entity.Orderitme;
import com.medical.entity.User;
import com.medical.service.impl.OrderitmeServiceImpl;
import com.medical.util.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 订单项 前端控制器
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/medical/orderitme")
public class OrderitmeController {
    @Autowired
    private OrderitmeServiceImpl orderitmeService;

    private AlipayUtil alipayUtil;

    @Autowired
    public void setAlipayUtil(AlipayUtil alipayUtil) {
        this.alipayUtil = alipayUtil;
    }

    /**
     * 创建订单
     * @param session
     * @param orderitmes
     * @return
     */
    @PostMapping("generateOrder")
    public Boolean generateOrder(HttpSession session, @RequestBody List<Orderitme> orderitmes){
        User user = (User) session.getAttribute("user");
        Integer uId = user.getId();
        Integer orderId = orderitmeService.setOrder();
        for (Orderitme orderitme : orderitmes) {
            orderitme.setOid(orderId);
            orderitme.setUid(uId);
            orderitmeService.save(orderitme);
        }
        return true;
    }

    /**
     * 返回支付界面
     * @param orderNumber
     * @param price
     * @param title
     * @return
     */
    @PostMapping("goPay")
    public String goPay(String orderNumber,String price,String title){
        String pay = alipayUtil.pay(orderNumber, price, title);
        return pay;
    }
}
