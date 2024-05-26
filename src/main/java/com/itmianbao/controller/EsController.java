package com.itmianbao.controller;

/**
 * @Description: demo
 * @Author:bread
 * @Date: 2024-04-29 17:00
 */
import com.itmianbao.mapper.BlogRepository;
import com.itmianbao.pojo.BlogData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/es")
public class EsController {
    @Resource
    private BlogRepository blogRepository;

    @PostMapping("addBlog")
    public BlogData addDocument(@RequestBody BlogData blogData) {
        BlogData blog = new BlogData();
        blog=blogData;
        if(blogData.getAllowIssue().equals("1")){
            blog.setAllowIssue("true");
        }else{
            blog.setAllowIssue("false");
        }
        System.out.println(blog);
        System.out.println("6666666666666666666666666666666");
        return blogRepository.save(blog);
    }
/*

    @PostMapping("addDocuments")
    public Object addDocuments(Integer count) {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Long id = (long) i;
            Blog blog = new Blog();
            blog.setBlogId(id);
            blog.setTitle("Spring Data ElasticSearch学习教程" + id);
            blog.setContent("这是添加单个文档的实例" + id);
            blog.setAuthor("Tony");
            blog.setCategory("ElasticSearch");
            blog.setCreateTime(new Date());
            blog.setStatus(1);
            blog.setSerialNum(id.toString());
            blogs.add(blog);
        }

        return blogRepository.saveAll(blogs);
    }

    */
/**
     * 跟新增是同一个方法。若id已存在，则修改。
     * 无法只修改某个字段，只能覆盖所有字段。若某个字段没有值，则会写入null。
     *
     * @return 成功写入的数据
     *//*

    @PostMapping("editDocument")
    public Blog editDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是修改单个文档的实例" + id);
        // blog.setAuthor("Tony");
        // blog.setCategory("ElasticSearch");
        // blog.setCreateTime(new Date());
        // blog.setStatus(1);
        // blog.setSerialNum(id.toString());

        return blogRepository.save(blog);
    }

    @GetMapping("findById")
    public Blog findById(Long id) {
        return blogRepository.findById(id).get();
    }
*/

    @PostMapping("deleteDocument")
    public String deleteDocument(Long id) {
        blogRepository.deleteById(id);
        return "success";
    }

    @PostMapping("deleteDocumentAll")
    public String deleteDocumentAll() {
        blogRepository.deleteAll();
        return "success";
    }
}
