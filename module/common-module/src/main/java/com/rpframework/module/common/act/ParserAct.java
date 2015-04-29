package com.rpframework.module.common.act;

import java.util.List;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.rpframework.module.common.domain.County;
import com.rpframework.module.common.domain.Province;
import com.rpframework.module.common.domain.City;
import com.rpframework.module.common.http.CityParser;
import com.rpframework.module.common.http.CountryParser;
import com.rpframework.module.common.http.ProvinceParser;
import com.rpframework.module.common.service.CountyService;
import com.rpframework.module.common.service.ProvinceService;
import com.rpframework.module.common.service.CityService;

@Controller
@RequestMapping("/common/parser/country")
public class ParserAct extends CommonBaseAct {
	@Resource ProvinceService provinceService;
	@Resource CityService cityService;
	@Resource CountyService countyService;
	
	@RequestMapping("/synchttp")
	public @ResponseBody String synchttp(final CountryParser countryParser, final ProvinceParser provinceParser, final CityParser cityParser) throws ParserException, InterruptedException{
		new Thread() {
			@Override
			public void run() {
				// Country country = countryService.countryDao.select("11");
				try {
					List<Province> list = countryParser.parserAllCountry();

					for (Province country : list) {
						boolean insert = provinceService.provinceDao.insert(country);
						if (insert) {
							List<City> allProvinceByCountryCode = provinceParser.parserAllProvinceByCountryCode(country.getCode());
							for (City province : allProvinceByCountryCode) {
								boolean insert2 = cityService.cityDao.insert(province);
								if (insert2) {
									List<County> parserAllCityByProvinceCode = cityParser.parserAllCityByProvinceCode(province .getCode());
									for (County city : parserAllCityByProvinceCode) {
										boolean insert3 = countyService.countyDao.insert(city);
										if (!insert3) {
											logger.info("city insert fail: {}",city);
											break;
										}
									}
								} else {
									logger.info("province insert fail: {}", province);
									break;
								}

								Thread.sleep(50);
							}
						} else {
							logger.info("country insert fail: {}", country);
							break;
						}

						Thread.sleep(50);
					}
					
					
					logger.info("SUCCESS.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.run();
				
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("tips", "请求已经接受，请等待!!!");
		return jsonObject.toString();
	}
}