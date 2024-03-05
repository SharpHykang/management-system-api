package com.hykang.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hykang.management.entity.Goods;
import com.hykang.management.entity.GoodsAttribute;
import com.hykang.management.entity.GoodsPicture;
import com.hykang.management.entity.Order;
import com.hykang.management.entity.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    // 商品分页查询
    List<Goods> getGoodsByPage(@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize,@Param("goodsName") String goodsName);

    // 商品总数查询
    long getGoodsCount();

    // 通过商品id详情查询
    GoodsVo getGoodsById(Integer id);

    // 删除商品：逻辑删除
    boolean deleteGoodsById(@Param("goodsId") Integer goodsId,@Param("deleteTime") String deleteTime);

    // 添加商品
    boolean addGoods(Goods goods);

    // 批量添加商品对应属性
    boolean addBatchAttribute(List<GoodsAttribute> attributes);

    // 批量添加商品对应照片
    boolean addBatchPicture(List<GoodsPicture> pictures);

    // 通过商品名查询商品
    Goods getGoodsByName(String goodsName);
}
