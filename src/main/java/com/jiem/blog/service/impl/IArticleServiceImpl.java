package com.jiem.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiem.blog.entity.Article;
import com.jiem.blog.service.IArticleService;
import com.jiem.blog.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author Jiem
* @description 针对表【article(文章)】的数据库操作Service实现
* @createDate 2023-02-11 00:00:42
*/
@Service
public class IArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements IArticleService {

}




