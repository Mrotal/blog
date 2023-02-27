package com.jiem.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiem.blog.entity.Article;
import com.jiem.blog.service.IArticleService;
import com.jiem.blog.util.Result;
import com.jiem.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;


    @GetMapping("{id}")
    public Result getDetail(@PathVariable String id) {
        Article article = iArticleService.getById(id);
        System.out.println(article);
        return Result.success(article);
    }

    @GetMapping("page")
    public Result page(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam Integer pageindex, @RequestParam Integer pagesize) {

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotBlank(title)){
            queryWrapper.like("title", title);
        }
        if (StringUtil.isNotBlank(author)){
            queryWrapper.like("author", author);
        }


        IPage<Article> page = new Page<>();
        page.setCurrent(pageindex);
        page.setSize(pagesize);
        page.maxLimit();
        page = iArticleService.page(page, queryWrapper);

//        Page<Article> page1 = PageHelper.startPage(1, 10).doSelectPage(()-> countryMapper.selectGroupBy());

        Map map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());

        return Result.success(map);
    }

    @PostMapping
    public Result insert(@RequestBody Article article){

        article.setCreatetime(new Date());
        article.setAuthor("ceshi");
        iArticleService.save(article);
        return Result.ok("新增成功！");
    }

    @PutMapping
    public Result update(@RequestBody Article article){
        iArticleService.updateById(article);
        return Result.ok("修改成功！");
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        iArticleService.removeById(id);
        return Result.ok("删除成功");
    }

    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Integer> ids){
        iArticleService.removeBatchByIds(ids);
        return Result.ok("删除成功");
    }

}
