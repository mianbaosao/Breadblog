package com.itmianbao.controller;

import com.itmianbao.pojo.FormData;
import com.itmianbao.pojo.Result;
import com.itmianbao.service.FormdataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 表单数据控制器
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class FormdataController {

    @Autowired
    private FormdataService formdataService;

    /**
     * 展示所有表单数据
     *
     * @return 返回表单数据列表
     */
    @GetMapping("/show")
    public Result list() {
        List<FormData> formDataList = formdataService.list2();
        return Result.success(formDataList);
    }

    /**
     * 根据分类ID展示表单数据
     *
     * @param categoryId 分类ID
     * @return 返回表单数据列表
     */
    @GetMapping("/show2/{categoryId}")
    public Result list2(@PathVariable Integer categoryId) {
        log.info("查询指定分类下的表单数据");
        List<FormData> formDataList = formdataService.list7(categoryId);
        return Result.success(formDataList);
    }

    /**
     * 根据分类名称删除表单数据
     *
     * @param categoryName 分类名称
     * @return 返回操作结果
     */
    @DeleteMapping("/delete/{categoryName}")
    public Result deleteCategory(@PathVariable String categoryName) {
        formdataService.deleteform(categoryName);
        log.info("根据名称删除表单数据: {}", categoryName);
        return Result.success();
    }

    /**
     * 更新表单数据
     *
     * @param formData 表单数据信息
     * @return 返回操作结果
     */
    @PutMapping("/update")
    public Result updateData(@RequestBody FormData formData) {
        formdataService.update(formData);
        return Result.success();
    }

    /**
     * 根据旧的分类ID更新表单数据的分类
     *
     * @param oldId 旧的分类ID
     * @return 返回操作结果
     */
    @PutMapping("/updateCategory/{oldId}")
    public Result updateCategory(@PathVariable Integer oldId) {
        formdataService.updateCategory(oldId);
        return Result.success();
    }

    /**
     * 根据新的分类ID更新表单数据的分类
     *
     * @param newId 新的分类ID
     * @return 返回操作结果
     */
    @PutMapping("/updateCategory2/{newId}")
    public Result updateCategory2(@PathVariable Integer newId) {
        formdataService.updateCategory2(newId);
        return Result.success();
    }
}
