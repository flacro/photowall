package org.flacro.po;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.flacro.po.Users;
import org.flacro.po.UsersExample;

public interface UsersMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int countByExample(UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int deleteByExample(UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int insert(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int insertSelective(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	List<Users> selectByExample(UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	Users selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByExampleSelective(@Param("record") Users record,
			@Param("example") UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByExample(@Param("record") Users record,
			@Param("example") UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByPrimaryKeySelective(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbggenerated  Thu Nov 03 16:23:43 CST 2011
	 */
	int updateByPrimaryKey(Users record);

	List<Users> selectAll();
}