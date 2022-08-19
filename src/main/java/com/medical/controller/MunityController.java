package com.medical.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.medical.entity.Munity;
import com.medical.mapper.MunityMapper;
import com.medical.service.MunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 社区 前端控制器
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/medical/munity")
public class MunityController {
    @Autowired
    private MunityService munityService;


    @Autowired
    private MunityMapper munityMapper;


    /**
     * 修改文章状态
     * @param munity 社区文章类
     * @return 是否修改成功
     */
    @PutMapping("/updateMunityCtate")
    public boolean updateMunity(@RequestBody Munity munity) {
        UpdateWrapper<Munity> munityUpdateWrapper = new UpdateWrapper<>();
        munityUpdateWrapper.eq("id", munity.getId()).set("c_state", "下架");
        boolean update = munityService.update(munityUpdateWrapper);
        return update;
    }

    /**
     * 查询文章数量
     * @return 返回文章数量
     */

    @GetMapping("/selectAllnumber")
    public int selectAllnumber() {
        QueryWrapper<Munity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("COUNT(*) as id");
        List<Munity> list = munityService.list(queryWrapper);
        int id = list.get(0).getId();
        System.out.println(id);
        return id;
    }

}
