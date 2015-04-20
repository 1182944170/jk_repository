package com.rpframework.website.xtexam.domain;

public enum ExamClassifyLevelEnum {

	COURSES(1) /** 科目 */
	, SPECIALTY(2)/** 专业*/
	, CHAPTER(3)/** 章节 */
	, SUB_CHAPTER(4); /** 试卷 */
	
	public Integer levelNum;
	ExamClassifyLevelEnum(Integer levelNum) {
		this.levelNum = levelNum;
	}
	
	public static ExamClassifyLevelEnum getLevelEnum(Integer levelNum) {
		if(levelNum == ExamClassifyLevelEnum.COURSES.levelNum) {
			return ExamClassifyLevelEnum.COURSES;
		} else if(levelNum == ExamClassifyLevelEnum.SPECIALTY.levelNum) {
			return ExamClassifyLevelEnum.SPECIALTY;
		} else if(levelNum == ExamClassifyLevelEnum.CHAPTER.levelNum) {
			return ExamClassifyLevelEnum.CHAPTER;
		} else if(levelNum == ExamClassifyLevelEnum.SUB_CHAPTER.levelNum) {
			return ExamClassifyLevelEnum.SUB_CHAPTER;
		}
		
		return null;
	}
}
