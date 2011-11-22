package org.flacro.service;

import java.util.List;

import org.flacro.po.Tags;
import org.flacro.po.Users;


public interface UserService {
	Boolean login(String username, String password);
	int createUser(Users u);
	Users getUsers(int id);
	List<Users> getUsersAll();
	
	List<Tags> getTags(int userid);
	Tags getTag(int tagid);
	int createTag(Tags t);
	int updateTag(Tags t);
	int deleteTag(int id);
}
