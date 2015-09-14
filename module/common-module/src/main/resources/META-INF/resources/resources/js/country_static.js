B.Country = {
	checkedCfg: {},
	
	getSelectData: function(){
		var select4 = $("#" + this.checkedCfg.select3Id);
		var value = select4.val();
		if(!value || isBlank(value) || value == -1) {
			return 0;
		}
		
		return value;
	},
	
	regist4Select: function(countyCode,select1Id,select2Id,select3Id) {
		this.checkedCfg.select1Id = select1Id;
		this.checkedCfg.select2Id = select2Id;
		this.checkedCfg.select3Id = select3Id;
		
		var self = this;
		var tree = this.findTree(countyCode);
		if(!tree) {
		} else {
			for(var idx in tree) {
				this.checkedCfg[idx] = tree[idx];
			}
		}
		
		$("#" + select1Id).bind("change", function(){
			self.checkedCfg.province = self.findProvince(this.value);
			self.checkedCfg.city = null;
			self.checkedCfg.county = null;
			self.fillCity();
		});
		
		$("#" + select2Id).bind("change", function(){
			for(var idx in self.checkedCfg.province.citys) {
				var city = self.checkedCfg.province.citys[idx]
				if(city.code == this.value) {
					self.checkedCfg.city = city;
				}
			}
			
			self.checkedCfg.county = null;
			self.fillCounty();
		});
		
		self.fillProvince();
	},
	
	fillCounty: function(){
		var select3 = $("#" + this.checkedCfg.select3Id);
		var city = this.checkedCfg.city;
		select3.empty();
		if(!city) {//
			
		} else {
			var checkedCityCode = this.checkedCfg.county ? this.checkedCfg.county.code : -1;
			var defaultOption = $(document.createElement("option")).attr("value",-1).text("--请选择--");
			if(checkedCityCode == -1) {
				defaultOption.attr("selected", "true");
			}
			select3.append(defaultOption);
			
			for(var idx in city.countys) {
				var county = city.countys[idx]
				var option = $(document.createElement("option")).attr("value",county.code).text(county.name);
				
				if(checkedCityCode == county.code) {
					option.attr("selected", "true");
					this.checkedCfg.county = county;
				}
				
				select3.append(option);
			}
		}
		
	},
	
	fillCity: function(){
		var select2 = $("#" + this.checkedCfg.select2Id);
		var province = this.checkedCfg.province;
		select2.empty();
		if(!province) {//如果没项目科目则清空
		} else {
			var checkProvinceCode = this.checkedCfg.city? this.checkedCfg.city.code : -1;
			var defaultOption = $(document.createElement("option")).attr("value",-1).text("--请选择--");
			if(checkProvinceCode == -1) {
				defaultOption.attr("selected", "true");
			}
			
			select2.append(defaultOption);
			
			for(var idx in province.citys) {
				var city = province.citys[idx]
				var option = $(document.createElement("option")).attr("value",city.code).text(city.name);
				
				if(checkProvinceCode == city.code) {
					option.attr("selected", "true");
					this.checkedCfg.city = city;
				}
				
				select2.append(option);
			}
		}
		
		this.fillCounty();
	},
	
	
	fillProvince: function(){
		var select1 = $("#" + this.checkedCfg.select1Id);
		select1.empty();
		
		var checkedCountryCode = this.checkedCfg.province ? this.checkedCfg.province.code : -1;
		var defaultOption = $(document.createElement("option")).attr("value",-1).text("--请选择--");
		if(checkedCountryCode == -1) {
			defaultOption.attr("selected", "true");
		}
		select1.append(defaultOption);
		
		for(var provinceIdx in COUNTRY_STATIC_JSON) {
			var province = COUNTRY_STATIC_JSON[provinceIdx];
			var option = $(document.createElement("option")).attr("value",province.code).text(province.name);
			
			if(checkedCountryCode == province.code) {
				option.attr("selected", "true");
				this.checkedCfg.province = province;
			}
			
			select1.append(option);
		}
		
		this.fillCity();
	},
	
	findProvince: function(provinceId){
		if(!COUNTRY_STATIC_JSON) {
			return null;
		}
		
		for(var provinceIdx in COUNTRY_STATIC_JSON) {
			var province = COUNTRY_STATIC_JSON[provinceIdx];
			if(province.code == provinceId) {
				return province;
			}
		}
		
		return null;
	},
	
	findTree: function(countyCode){
		if(!COUNTRY_STATIC_JSON) {
			return null;
		}
		
		for(var provinceIdx in COUNTRY_STATIC_JSON) {
			var province = COUNTRY_STATIC_JSON[provinceIdx];
			var citys = province.citys;
			
			for(var cityIndex in citys) {
				var city = citys[cityIndex];
				var countys = city.countys;
				
				for(var countyId in countys) {
					var county = countys[countyId];
					if(county.code == countyCode) {
						// find
						return {province: province,
								city: city,
								county: county
							};
					}
				}
			}
		}
		
		return null;
	}
}