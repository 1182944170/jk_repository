<title><#if subChapter??>试卷名 [${subChapter.name}] -></#if> 试题列表</title>

<form class="form-horizontal" role="form" id="validation-form" method="GET" action="${ctx}/admin/subject/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>选择分类:</label>
	<select class="width-10" name="coursesSelect" id="coursesSelect"></select>
	<select class="width-10" name="speecialtySelect" id="speecialtySelect"></select>
	<select class="width-10" name="chapterSelect" id="chapterSelect"></select>
	<select class="width-20" name="subchapterSelect" id="subchapterSelect"></select>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>题目</th>
						<th>所属试卷名</th>
						<th>试题类型</th>
						<th>点赞数</th>
						<th>评论数</th>
						<th><i class="icon-time bigger-110"></i> 状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.title}</span></td>
						<td>${xtUtils.getExamClassifyPath(u.examClassify.id)}</td>
						<td class="hidden-480">
							<#if u.subjectType == 1>
								<span class="label label-sm label-success arrowed">选择题</span>
							<#else>
								<span class="label label-sm label-warning arrowed">简答题</span>
							</#if>
						</td>
						<td>${u.supportNum}</td>
						<td>${u.commentNum}</td>
						<td>
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">正常状态</span>
							<#else>
								<span class="label label-sm label-warning arrowed">禁用状态</span>
							</#if>
						</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/subject/${u.id}/edit" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/subject/${u.id}/edit" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/subject/list" />
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}</span>
	</h4>
</#if>

<#if infoMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer green"></i>
		<span class="pink">${infoMsg}</span>
	</h4>
</#if>
<script>
var classify = Object.create(XT.Classify);
function fromSearch(f){
	if(classify.getSelectData()) {
		window.location.href = ctx + "/admin/subchapter/" + classify.getSelectData() +"/subjects";
	}
	return false;
}

$(document).ready(function() {
	RP.addBreadcrumb([{name:"题库"},<#if subChapter??> {name:"${subChapter.name} 试卷名", linkUrl:"${ctx}/admin/subchapter/${subChapter.id}/subjects"},</#if>{name:"试题列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/subject/add<#if subChapter??>?subChapterId=${subChapter.id}</#if>"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增题目</span></a>');
	
	var subChapterId = -1;
	<#if subChapter??>subChapterId = ${subChapter.id}</#if>
	classify.registClassify4Select(subChapterId, "coursesSelect","speecialtySelect","chapterSelect","subchapterSelect");
});
</script>

