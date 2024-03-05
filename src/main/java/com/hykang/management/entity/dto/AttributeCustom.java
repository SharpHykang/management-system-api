package com.hykang.management.entity.dto;

import com.hykang.management.entity.Attribute;
import com.hykang.management.entity.GoodsAttribute;
import lombok.Data;

/**
 * 自定义商品属性类：goods_attribute与attribute连表查询
 */
@Data
public class AttributeCustom extends GoodsAttribute {
    private String attrName;
    private String attrSel;
    private String attrWrite;
    private String attrVals;
}
