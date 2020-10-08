package com.sydl.console.service.impl;

import com.sydl.console.model.NewsMessage;
import com.sydl.console.mapper.NewsMessageMapper;
import com.sydl.console.service.NewsMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2020-10-08
 */
@Service
public class NewsMessageServiceImpl extends ServiceImpl<NewsMessageMapper, NewsMessage> implements NewsMessageService {

}
