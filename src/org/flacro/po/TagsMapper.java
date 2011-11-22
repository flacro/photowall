package org.flacro.po;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.flacro.po.Tags;
import org.flacro.po.TagsExample;

public interface TagsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int countByExample(TagsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int deleteByExample(TagsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int insert(Tags record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int insertSelective(Tags record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	List<Tags> selectByExample(TagsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	Tags selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByExampleSelective(@Param("record") Tags record,
			@Param("example") TagsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByExample(@Param("record") Tags record,
			@Param("example") TagsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByPrimaryKeySelective(Tags record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tags
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByPrimaryKey(Tags record);
}