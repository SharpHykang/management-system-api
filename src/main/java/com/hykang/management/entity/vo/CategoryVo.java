package com.hykang.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hykang.management.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends Category {
    @TableField(exist = false)
    private Integer type;
    @TableField(exist = false)
    private List<CategoryVo> children;
}
