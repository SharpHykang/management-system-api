package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Goods;
import com.hykang.management.entity.GoodsAttribute;
import com.hykang.management.entity.GoodsPicture;
import com.hykang.management.entity.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

import java.net.UnknownHostException;
import java.util.List;

public interface GoodsService extends IService<Goods> {
    List<Goods> getGoodsByPage(Integer startPage,Integer pageSize,String goodsName,Integer isPromote,Integer goodsState);
    long getGoodsCount(String goodsName,Integer isPromote,Integer goodsState);
    GoodsVo getGoodsById(Integer id) throws UnknownHostException;
    boolean deleteGoodsById(Integer id);
    boolean addGoods(GoodsVo goodsVo);
    boolean addBatchAttribute(List<GoodsAttribute> attributes);
    boolean addBatchPicture(List<GoodsPicture> pictures);
    Goods getGoodsByName(String goodsName);
}
