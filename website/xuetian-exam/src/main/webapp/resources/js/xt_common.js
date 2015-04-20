if (!Object.create) {
	Object.create = function (o) {
		function F() {}
		F.prototype = o;
		return new F();
	};
}

String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};
/*判断该值是否是数字类型*/
String.prototype.isNumber = function () {
	if (!/^\d+(\.\d+)?$/.test(this)) {
		return false;
	}
	try {
		return parseFloat(this) == this;
	}catch (ex) {
		return false;
	}
};
/*去掉俩段的空格*/
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.endWith=function(oString){
  var   reg= new RegExp(oString+"$");   
  return   reg.test(this);
}

/**非空判断*/
function isNotBlank(o){
	return (o && o.trim() != '') ;
}
function isBlank(o){
	return !isNotBlank(o) ;
}

var XT = XT || {}
XT.Classify = {
	checkedCfg: {},
	
	getSelectData: function(){
		var select4 = $("#" + this.checkedCfg.select4Id);
		var value = select4.val();
		if(!value || isBlank(value) || value == -1) {
			return 0;
		}
		
		return value;
	},
	
	registClassify4Select: function(subchapterId,select1Id,select2Id,select3Id,select4Id) {
		this.checkedCfg.select1Id = select1Id;
		this.checkedCfg.select2Id = select2Id;
		this.checkedCfg.select3Id = select3Id;
		this.checkedCfg.select4Id = select4Id;
		
		var self = this;
		var classifyTree = this.findClassifyTree(subchapterId);
		if(!classifyTree) {
		} else {
			for(var idx in classifyTree) {
				this.checkedCfg[idx] = classifyTree[idx];
			}
		}
		
		$("#" + select1Id).bind("change", function(){
			self.checkedCfg.courses = self.findCourses(this.value);
			self.checkedCfg.specialty = null;
			self.checkedCfg.chapter = null;
			self.checkedCfg.subchapter = null;
			self.fillSpecialty();
		});
		
		$("#" + select2Id).bind("change", function(){
			for(var idx in self.checkedCfg.courses.specialtys) {
				var specialty = self.checkedCfg.courses.specialtys[idx]
				if(specialty.id == this.value) {
					self.checkedCfg.specialty = specialty;
				}
			}
			
			self.checkedCfg.chapter = null;
			self.checkedCfg.subchapter = null;
			self.fillChapter();
		});
		
		$("#" + select3Id).bind("change", function(){
			for(var idx in self.checkedCfg.specialty.chapters) {
				var chapter = self.checkedCfg.specialty.chapters[idx]
				if(chapter.id == this.value) {
					self.checkedCfg.chapter = chapter;
				}
			}
			
			self.checkedCfg.subchapter = null;
			self.fillSubChapter();
		});
		
		self.fillCourses();
	},
	
	fillSubChapter: function(){
		var select4 = $("#" + this.checkedCfg.select4Id);
		var chapter = this.checkedCfg.chapter;
		select4.empty();
		if(!chapter) {//如果没项目科目则清空
			
		} else {
			var checkedSubChapterId = this.checkedCfg.subchapter?this.checkedCfg.subchapter.id : -1;
			var defaultOption = $(document.createElement("option")).attr("value",-1).text("--please choose--");
			if(checkedSubChapterId == -1) {
				defaultOption.attr("selected", "true");
			}
			select4.append(defaultOption);
			
			for(var idx in chapter.subchapters) {
				var subchapter = chapter.subchapters[idx]
				var option = $(document.createElement("option")).attr("value",subchapter.id).text(subchapter.name);
				
				if(checkedSubChapterId == subchapter.id) {
					option.attr("selected", "true");
				}
				
				select4.append(option);
			}
		}
	},
	
	fillChapter: function(){
		var select3 = $("#" + this.checkedCfg.select3Id);
		var specialty = this.checkedCfg.specialty;
		select3.empty();
		if(!specialty) {//如果没项目科目则清空
			
		} else {
			var checkedChapterId = this.checkedCfg.chapter ? this.checkedCfg.chapter.id : -1;
			var defaultOption = $(document.createElement("option")).attr("value",-1).text("--please choose--");
			if(checkedChapterId == -1) {
				defaultOption.attr("selected", "true");
			}
			select3.append(defaultOption);
			
			for(var idx in specialty.chapters) {
				var chapter = specialty.chapters[idx]
				var option = $(document.createElement("option")).attr("value",chapter.id).text(chapter.name);
				
				if(checkedChapterId == chapter.id) {
					option.attr("selected", "true");
					this.checkedCfg.chapter = chapter;
				}
				
				select3.append(option);
			}
		}
		
		this.fillSubChapter();
		
	},
	
	fillSpecialty: function(){
		var select2 = $("#" + this.checkedCfg.select2Id);
		var courses = this.checkedCfg.courses;
		select2.empty();
		if(!courses) {//如果没项目科目则清空
		} else {
			var checkedSpecialtyId = this.checkedCfg.specialty? this.checkedCfg.specialty.id : -1;
			var defaultOption = $(document.createElement("option")).attr("value",-1).text("--please choose--");
			if(checkedSpecialtyId == -1) {
				defaultOption.attr("selected", "true");
			}
			
			select2.append(defaultOption);
			
			for(var idx in courses.specialtys) {
				var specialty = courses.specialtys[idx]
				var option = $(document.createElement("option")).attr("value",specialty.id).text(specialty.name);
				
				if(checkedSpecialtyId == specialty.id) {
					option.attr("selected", "true");
					this.checkedCfg.specialty = specialty;
				}
				
				select2.append(option);
			}
		}
		
		this.fillChapter();
	},
	
	
	fillCourses: function(){
		var select1 = $("#" + this.checkedCfg.select1Id);
		var checkedCoursesId = this.checkedCfg.courses ? this.checkedCfg.courses.id : -1;
		var defaultOption = $(document.createElement("option")).attr("value",-1).text("--please choose--");
		if(checkedCoursesId == -1) {
			defaultOption.attr("selected", "true");
		}
		select1.append(defaultOption);
		
		for(var coursesIndex in EXAM_STATIC_JSON) {
			var courses = EXAM_STATIC_JSON[coursesIndex];
			var option = $(document.createElement("option")).attr("value",courses.id).text(courses.name);
			
			if(checkedCoursesId == courses.id) {
				option.attr("selected", "true");
				this.checkedCfg.courses = courses;
			}
			
			select1.append(option);
		}
		
		this.fillSpecialty();
	},
	
	findCourses: function(courseId){
		if(!EXAM_STATIC_JSON) {
			return null;
		}
		
		for(var coursesIndex in EXAM_STATIC_JSON) {
			var courses = EXAM_STATIC_JSON[coursesIndex];
			if(courses.id == courseId) {
				return courses;
			}
		}
		
		return null;
	},
	
	findClassifyTree: function(subchapterId){
		if(!EXAM_STATIC_JSON) {
			return null;
		}
		
		for(var coursesIndex in EXAM_STATIC_JSON) {
			var courses = EXAM_STATIC_JSON[coursesIndex];
			var specialtys = courses.specialtys;
			
			for(var specialtyIndex in specialtys) {
				var specialty = specialtys[specialtyIndex];
				var chapters = specialty.chapters;
				
				for(var chapterIndex in chapters) {
					var chapter = chapters[chapterIndex];
					var subchapters = chapter.subchapters;
					
					for(var subchapterIndex in subchapters) {
						var subchapter = subchapters[subchapterIndex];
						if(subchapter.id == subchapterId) {
							// find
							return {courses: courses,
								specialty: specialty,
								chapter: chapter,
								subchapter: subchapter};
						}
					}
				}
			}
		}
		
		return null;
	}
}