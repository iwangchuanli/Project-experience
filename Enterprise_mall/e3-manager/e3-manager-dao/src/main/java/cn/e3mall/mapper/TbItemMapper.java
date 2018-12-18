package cn.e3mall.mapper;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>商品信息对应数据库中的tb_item商品表
 * @author Snailclimb
 */
public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
}