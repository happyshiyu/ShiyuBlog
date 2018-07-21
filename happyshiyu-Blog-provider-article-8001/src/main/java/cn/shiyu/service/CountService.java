package cn.shiyu.service;

import javax.servlet.http.HttpServletRequest;


public interface CountService {
	public void increase(int id , HttpServletRequest request);
	
	public long getReadNumber(int id);
}
