package com.rpframework.utils;

import java.util.ArrayList;
import java.util.List;

public class UserLinked {
//	Integer parentUserId;
	int deep;
	int userId;
	List<UserLinked> children;
	
	public UserLinked(){
		deep = 1;
		children = new ArrayList<UserLinked>();
	}
	
	public UserLinked findChild(int userId) {
		for (UserLinked userLinked : children) {
			if(userLinked.userId == userId) {
				return userLinked;
			}
		}
		
		return null;
	}
	public void addChild(UserLinked ul) {
		children.add(ul);
	}
	public static void main(String[] args) {
		
	}
}

