package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Category;
import com.hykang.management.entity.vo.CategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<CategoryVo> getCategoryTreeByType(Integer type);
    List<CategoryVo> getCategoryTreeByPage(Integer startPage,Integer pageSize);
    long getCategoryCount();
    Boolean addCategory(Category category);
    Boolean updateCategory(Category category);
    Boolean deleteCategory(Integer id);
    Category getCategoryById(Integer id);
}
