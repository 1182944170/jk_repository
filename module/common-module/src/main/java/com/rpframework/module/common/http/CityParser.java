package com.rpframework.module.common.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpframework.module.common.domain.City;
import com.rpframework.utils.NumberUtils;

public class CityParser {
	Logger logger = LoggerFactory.getLogger(getClass());
	protected String CITY_URI = "http://platform.eeeeee.org/info/city/";
	
	public String fomartText(String t) {
		return StringUtils.replace(t, "&nbsp;", "");
	}
	public List<City> parserAllCityByProvinceCode(String provinceCode) throws ParserException {
		 Parser parser = new Parser();
		 parser.setURL(CITY_URI + provinceCode);
		 AndFilter andFilter = new AndFilter(new NodeClassFilter(TableTag.class), new HasAttributeFilter("bordercolor","#663333"));
		 NodeList tables = parser.extractAllNodesThatMatch(andFilter);
		 TableTag tableTag = (TableTag)tables.elementAt(0);
		 List<City> list = new ArrayList<City>();
		 if(tableTag == null) {
			 System.out.println("parser url is nil:" + parser.getURL());
			 return list;
		 }
		 
		 for(int j = 1 ; j < tableTag.getRowCount(); j++) {   
             TableRow row = tableTag.getRow(j);   
             TableColumn[] columns = row.getColumns();   
             
             City city = new City(fomartText(columns[0].toPlainTextString()),
            		 fomartText(columns[1].toPlainTextString()),
            		 fomartText(columns[2].toPlainTextString()),
            		 NumberUtils.parseDouble(fomartText(columns[3].toPlainTextString())),
            		 fomartText(columns[4].toPlainTextString()),
            		 NumberUtils.parseInt(fomartText(columns[5].toPlainTextString())),
            		 NumberUtils.parseInt(fomartText(columns[6].toPlainTextString())),
            		 NumberUtils.parseDouble(fomartText(StringUtils.replace(columns[7].toPlainTextString(), "东经", ""))),
            		 NumberUtils.parseDouble(fomartText(StringUtils.replace(columns[8].toPlainTextString(), "北纬", ""))),
            		 
            		 provinceCode
            		 );
             System.out.println(city);
             list.add(city);
         }   
		return list;
	}
	
	public static void main(String[] args) throws ParserException {
		List<City> list = new CityParser().parserAllCityByProvinceCode("361000");
		System.out.println(ToStringBuilder.reflectionToString(list, ToStringStyle.MULTI_LINE_STYLE));
	}
}
