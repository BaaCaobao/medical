package com.medical.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.medical.entity.Registered;
import com.medical.entity.User;
import com.medical.service.impl.RegisteredServiceImpl;
import com.medical.util.PageListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预约挂号 前端控制器
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/medical/registered")
public class RegisteredController {
    @Autowired
    RegisteredServiceImpl registeredService;

    /**
     * 查询挂号预约
     * @param session
     * @return
     */
    @GetMapping("selRegistered")
    public Map<String, Object> selRegistered(HttpSession session, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        User user = (User) session.getAttribute("user");
        List<Registered> registereds = registeredService.selRegistered(user.getId());
        Page<Object> pageList = PageListUtil.pageList(registereds, pageSize, pageIndex);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",pageList);
        return map;
    }
}
