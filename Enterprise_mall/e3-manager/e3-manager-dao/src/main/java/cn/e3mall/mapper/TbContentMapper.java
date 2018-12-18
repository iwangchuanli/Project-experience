package cn.e3mall.mapper;

import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 内容列表中的内容的描述的tb_content表比如大广告、小广告下的内容
 * 
 * @author Snailclimb
 */
public interface TbContentMapper {
	int countByExample(TbContentExample example);

	int deleteByExample(TbContentExample example);

	int deleteByPrimaryKey(Long id);

	int insert(TbContent record);

	int insertSelective(TbContent record);

	List<TbContent> selectByExampleWithBLOBs(TbContentExample example);

	List<TbContent> selectByExample(TbContentExample example);

	TbContent selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") TbContent record, @Param("example") TbContentExample example);

	int updateByExampleWithBLOBs(@Param("record") TbContent record, @Param("example") TbContentExample example);

	int updateByExample(@Param("record") TbContent record, @Param("example") TbContentExample example);

	int updateByPrimaryKeySelective(TbContent record);

	int updateByPrimaryKeyWithBLOBs(TbContent record);

	int updateByPrimaryKey(TbContent record);
}