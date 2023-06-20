package com.minzheng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzheng.blog.dao.ScreenplayDao;
import com.minzheng.blog.entity.Screenplay;
import com.minzheng.blog.service.ScreenplayService;
import org.springframework.stereotype.Service;

@Service
public class ScreenplayServiceImpl extends ServiceImpl<ScreenplayDao, Screenplay> implements ScreenplayService {
}
