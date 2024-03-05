package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Attribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttributeService extends IService<Attribute> {
    List<Attribute> getAttributeByCatId(Attribute attribute);
    Boolean addAttribute(Attribute attribute);
    Boolean deleteAttribute(Integer attrId);
    Attribute getAttributeById(Integer attrId);
    Boolean updateAttrbute(Attribute attribute);
}
