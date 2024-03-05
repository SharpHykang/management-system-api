package com.hykang.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hykang.management.entity.Attribute;
import com.hykang.management.entity.Goods;
import com.hykang.management.entity.GoodsPicture;
import com.hykang.management.entity.dto.AttributeCustom;
import com.hykang.management.entity.dto.GoodsPictureCustom;
import lombok.Data;

import java.util.List;

@Data
public class GoodsVo extends Goods {
    // 存分类id：逗号隔开
    @TableField(exist = false)
    private String goodsCat;
    @TableField(exist = false)
    private List<GoodsPictureCustom> pics;
    @TableField(exist = false)
    private List<AttributeCustom> attrs;
}
