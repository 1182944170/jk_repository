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

import com.rpframework.module.common.domain.Province;
import com.rpframework.utils.NumberUtils;

public class CountryParser {
	Logger logger = LoggerFactory.getLogger(getClass());
	protected String COUNTRY_URI = "http://platform.eeeeee.org/info/country/";
	
	public String fomartText(String t) {
		return StringUtils.replace(t, "&nbsp;", "");
	}
	public List<Province> parserAllCountry() throws ParserException {
		 Parser parser = new Parser();
		 parser.setURL(COUNTRY_URI);
		 AndFilter andFilter = new AndFilter(new NodeClassFilter(TableTag.class), new HasAttributeFilter("bordercolor","#663333"));
		 NodeList tables = parser.extractAllNodesThatMatch(andFilter);
		 TableTag tableTag = (TableTag)tables.elementAt(0);
		 
		 if(tableTag == null) {
			 System.out.println("parser url is nil:" + parser.getURL());
		 }
		 
		 List<Province> list = new ArrayList<Province>();
		 for(int j = 1 ; j < tableTag.getRowCount(); j++) {   
             TableRow row = tableTag.getRow(j);   
             TableColumn[] columns = row.getColumns();   
             
             Province country = new Province(fomartText(columns[0].toPlainTextString()),
            		 fomartText(columns[1].toPlainTextString()),
            		 fomartText(columns[2].toPlainTextString()),
            		 fomartText(columns[3].toPlainTextString()),
            		 fomartText(columns[4].toPlainTextString()),
            		 NumberUtils.parseDouble(fomartText(columns[5].toPlainTextString())),
            		 NumberUtils.parseDouble(fomartText(columns[6].toPlainTextString())),
            		 fomartText(columns[7].toPlainTextString()),
            		 fomartText(columns[8].toPlainTextString())
            		 );
             System.out.println(country);
             list.add(country);
         }   
		return list;
	}
	
	public static void main(String[] args) throws ParserException {
		List<Province> list = new CountryParser().parserAllCountry();
		System.out.println(ToStringBuilder.reflectionToString(list, ToStringStyle.MULTI_LINE_STYLE));
	}
}
