package com.rpframework.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author <a href="mailto:rplees.i.ly@gmail.com">rplees</a>
 * date 2010-03-29
 * {@code} 分页实体类
 * @param <T> 实体
 */
public class Pager<T> implements Serializable {
	
	private static final int DEFAULT_PAGE_SIZE = 15;
	private static final long serialVersionUID = 8841335917497389135L;
	public static final String LUCENE_ALL_KEY = "_lucene_all_key_";
	private int pageSize = DEFAULT_PAGE_SIZE;    				//每页显示数 
	private int currentPage = 1;  				//当前页码，即要跳转的页码
	private int totalCount = 100;   			//数据的总数（？条）
	private T searchObj ;					//查询时所带的条件（T 类型）
	private Map<String, String> searchMap ;	//查询时所带的map条件（即T类型中没有的字段应放在searchMap中）
	private List<T> itemList ;				//查询返回的结果集
	
	private long costTime = -1;
	public static <K> Pager<K> convertStringToPager(String value) {
		Pager<K> pager = new Pager<K>();
		if(StringUtils.isBlank(value)) {
			return pager;
		}
		
		String[] params = value.split("_") ;
		int parseInt = NumberUtils.parseInt(params[0], 1);
		if(NumberUtils.isNotValid(parseInt)) {// <＝0 的都算 重置为1
			parseInt = 1;
		}
		pager.setCurrentPage(parseInt) ;
		
		if(params.length < 2) return pager ;
		
		String[] strMaps =StringUtils.split(params[1], "$$") ;
		if(strMaps.length >= 1){
			String[] m;
			Map<String, String> paramMap = new HashMap<String, String>();
			for (int i = 0; i < strMaps.length; i++) {
				if(StringUtils.isBlank(strMaps[i])) continue ;
				m = strMaps[i].split("--");
				if(m.length < 2 || StringUtils.isBlank(m[1])) continue ;
				
				//是乱码就需要转码
				if(CodeUtils.isMessyCode(m[1])) {
					m[1] = CodeUtils.changeISO2GBK(m[1]) ;
				}
				if(m.length > 1){
					paramMap.put(m[0], m[1]) ;
				}
			}
			if(!CollectionUtils.isEmpty(paramMap)) pager.setSearchMap(paramMap) ;
		}
		
		return pager ;
	}
	
	public static <K> Pager<K> clonePager(Pager<?> pager) {
		Pager<K> p = new Pager<K>();
		p.searchMap = pager.searchMap;
		p.currentPage = pager.currentPage;
		p.pageSize = pager.pageSize;
		p.totalCount = pager.totalCount;
		return p;
	}
	
	public Pager() {
		searchMap = new HashMap<String, String>();
	}

	public Pager( int pageSize , int currentPage ){
		this.pageSize = pageSize ;
		this.currentPage = currentPage ;
	}
	//~============getting setting====================
	
	public Map<String, Object> getIbatisMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchObj", this.searchObj);
		map.put("searchMap", this.searchMap);
		map.put("currentPage", this.currentPage);
		//mysql 需要起始行这个参数
		map.put("startIndex", this.pageSize * (this.currentPage - 1));
		map.put("pageSize", this.pageSize);
		return map;
	}
	
	public JSONObject toJson() throws Exception {
		JSONObject j = new JSONObject();
		JSONArray arr = new JSONArray();
		j.put("pagerWebString", this.getPagerWebString());
		j.put("Rows", arr);
		j.put("totalPages", this.getTotalPages());
		j.put("totalCount", this.getTotalCount());
		if(CollectionUtils.isNotEmpty(getItemList())) {
			for (T t : getItemList()) {
				arr.put(JsonObjUtils.parseObjToJson(t));
			}
		}
		return j;
	}
	
	public String getPagerWebString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.getCurrentPage()).append("_");
		Map<String,String> paramMap = this.getSearchMap() ;
		if(CollectionUtils.isNotEmpty(paramMap)){
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				if (StringUtils.isBlank(entry.getValue())) {
					continue;
				}
				buffer.append("$$").append(entry.getKey()).append("--").append(entry.getValue());
			}
		}
		return buffer.toString() ;
	}
	

    public boolean isFirstPage() {
        return currentPage <= 1;
    }

    public boolean isLastPage() {
        return currentPage >= getTotalPages();
    }

    public int getNextPage(){
        if(isLastPage())
            return currentPage;
        else
            return currentPage + 1;
    }

    public int getPrePage() {
        if(isFirstPage())
            return currentPage;
        else
            return currentPage - 1;
    }
    
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 *  计算页码
	 * @return
	 */
	public int getTotalPages() {
		return ((totalCount - 1) / pageSize) + 1;
	}

	
	public T getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(T searchObj) {
		this.searchObj = searchObj;
	}

	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}
	
}