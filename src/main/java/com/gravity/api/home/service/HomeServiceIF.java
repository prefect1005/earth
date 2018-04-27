package com.gravity.api.home.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HomeServiceIF {
	
	public Map<String, Map<Integer, String>> getCategory();
	
	public Set<Map<Integer, Integer>> getServiceLocation();
	
	public List<String> getHomeCompanyLogoUrl();

}
