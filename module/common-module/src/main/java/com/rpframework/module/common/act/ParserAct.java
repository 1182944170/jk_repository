package com.rpframework.module.common.act;

import java.util.List;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.rpframework.module.common.domain.City;
import com.rpframework.module.common.domain.Country;
import com.rpframework.module.common.domain.Province;
import com.rpframework.module.common.http.CityParser;
import com.rpframework.module.common.http.CountryParser;
import com.rpframework.module.common.http.ProvinceParser;
import com.rpframework.module.common.service.CityService;
import com.rpframework.module.common.service.CountryService;
import com.rpframework.module.common.service.ProvinceService;

@Controller
@RequestMapping("/common/parser/country")
public class ParserAct extends CommonBaseAct {
	@Resource CountryService countryService;
	@Resource ProvinceService provinceService;
	@Resource CityService cityService;
	
	@RequestMapping("/synchttp")
	public @ResponseBody String synchttp(final CountryParser countryParser, final ProvinceParser provinceParser, final CityParser cityParser) throws ParserException, InterruptedException{
		new Thread() {
			@Override
			public void run() {
				// Country country = countryService.countryDao.select("11");
				try {
					List<Country> list = countryParser.parserAllCountry();

					for (Country country : list) {
						boolean insert = countryService.countryDao.insert(country);
						if (insert) {
							List<Province> allProvinceByCountryCode = provinceParser.parserAllProvinceByCountryCode(country.getCode());
							for (Province province : allProvinceByCountryCode) {
								boolean insert2 = provinceService.provinceDao.insert(province);
								if (insert2) {
									List<City> parserAllCityByProvinceCode = cityParser.parserAllCityByProvinceCode(province .getCode());
									for (City city : parserAllCityByProvinceCode) {
										boolean insert3 = cityService.cityDao.insert(city);
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