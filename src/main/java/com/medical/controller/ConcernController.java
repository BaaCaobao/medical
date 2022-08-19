package com.medical.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.medical.entity.User;
import com.medical.service.impl.ConcernServiceImpl;
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
 * 关注 前端控制器
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/medical/concern")
public class ConcernController {
    @Autowired
    ConcernServiceImpl concernService;

    /**
     *查询关注的用户
     * JiaJieTang
     * @param session
     * @return
     */
    @GetMapping("selectConcern")
    public Map<String, Object> selectConcern(HttpSession session, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageIndex") Integer pageIndex){
        User user=(User)session.getAttribute("user");
        List<User> users = concernService.selectConcern(user.getId());
        Page<Object> pageList = PageListUtil.pageList(users, pageSize, pageIndex);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",pageList);
        return map;
    }
}
