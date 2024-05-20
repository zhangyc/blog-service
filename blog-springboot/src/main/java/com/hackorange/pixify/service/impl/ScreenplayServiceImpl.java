package com.hackorange.pixify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hackorange.pixify.dao.ScreenplayDao;
import com.hackorange.pixify.entity.Screenplay;
import com.hackorange.pixify.service.ScreenplayService;
import org.springframework.stereotype.Service;

@Service
public class ScreenplayServiceImpl extends ServiceImpl<ScreenplayDao, Screenplay> implements ScreenplayService {
}
