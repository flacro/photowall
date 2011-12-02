package org.flacro.service;

import java.util.List;

import org.flacro.po.Tags;
import org.flacro.po.TagsExample;
import org.flacro.po.TagsExample.Criteria;
import org.flacro.po.TagsMapper;
import org.flacro.po.Users;
import org.flacro.po.UsersMapper;
import org.mybatis.guice.transactional.Transactional;

import util.LogDetail;

import com.google.inject.Inject;

public class UserServiceImpl implements UserService {

	@Inject
	private TagsMapper tagsmapper;
	@Inject
	private UsersMapper usersmapper;

	public Boolean login(String username, String password) {
		if (password != null && password.equals("varkrs")) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public int createUser(Users u) {
		try {
			return usersmapper.insert(u);
		} catch (Exception e) {
			LogDetail.logexception(e);
			return -1;
		}
	}

	@Override
	public Users getUsers(int id) {
		try {
			Users record = usersmapper.selectByPrimaryKey(id);
			return record;
		} catch (Exception e) {
			LogDetail.logexception(e);
			return null;
		}
	}

	@Override
	public List<Users> getUsersAll() {
		try {
			List<Users> records = usersmapper.selectAll();
			return records;
		} catch (Exception e) {
			LogDetail.logexception(e);
			return null;
		}
	}

	@Override
	public List<Tags> getTags(int userid) {
		try {
			TagsExample example = new TagsExample();
			Criteria c = example.createCriteria();
			c.andUseridEqualTo(userid);
			List<Tags> tlist = tagsmapper.selectByExample(example);
			return tlist;
		} catch (Exception e) {
			LogDetail.logexception(e);
			return null;
		}

	}
	@Override
	public Tags getTag(int tagid){
		try {
			return tagsmapper.selectByPrimaryKey(tagid);
		} catch (Exception e) {
			LogDetail.logexception(e);
			return null;
		}
	}

	@Override
	@Transactional
	public int createTag(Tags t) {
		try {
			return tagsmapper.insert(t);
		} catch (Exception e) {
			LogDetail.logexception(e);
			return -1;
		}
	}

	@Override
	@Transactional
	public int updateTag(Tags t) {
		try {
			return tagsmapper.updateByPrimaryKey(t);
		} catch (Exception e) {
			LogDetail.logexception(e);
			return -1;
		}
	}

	@Override
	@Transactional
	public int deleteTag(int id) {
		try {
			return tagsmapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			LogDetail.logexception(e);
			return -1;
		}
	}
	
	
	
}
