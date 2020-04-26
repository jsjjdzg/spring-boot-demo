package com.dzg.project.service.impl;

import com.dzg.project.entity.Demo;
import com.dzg.project.dao.DemoMapper;
import com.dzg.project.service.IDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DZG
 * @since 2020-04-26
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

}
