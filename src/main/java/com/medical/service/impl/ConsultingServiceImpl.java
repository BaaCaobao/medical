package com.medical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.medical.entity.Consulting;
import com.medical.mapper.ConsultingMapper;
import com.medical.service.ConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 咨询表 服务实现类
 * </p>
 *
 * @author JiaJieTang
 * @since 2022-08-11
 */
@Service
public class ConsultingServiceImpl extends ServiceImpl<ConsultingMapper, Consulting> implements ConsultingService {

    @Autowired
    private ConsultingMapper consultingMapper;

    @Override
    public List selectConsulting(int uid) {
        return consultingMapper.selectConsulting(uid);
    }

    public List<Consulting> servedNumber(){
        QueryWrapper<Consulting> wrapper = new QueryWrapper<>();
        wrapper.select("count(doid) as id,doid");
        wrapper.groupBy("doid");
        List<Consulting> consultings = consultingMapper.selectList(wrapper);
        return consultings;
    }
}
