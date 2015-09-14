/*上传图片 传参数 setImagePreview(input,img,div)三个id */
var pages =0;
function setImagePreview(fileIds,imageIds,cardIds) {
        var docObj=document.getElementById(fileIds);
        var imgObjPreview=document.getElementById(imageIds);
        if(docObj.files != '' && docObj.files[0] != null) {
            imgObjPreview.style.display = 'block';
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        } else {
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById(cardIds);
            localImagId.style.width = "166px";
            localImagId.style.height = "127px";
            try{
                localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            } catch(e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
}
/*验证form提交表单*/
function checkCommit(names){
	var msg = "不能为空！"
	if(names != '' && names != null){
		if(names.indexOf(";")>0){
			var arr =names.split(";");
			for(i = 0; i < arr.length; i++){
				if($("#"+arr[i].split(",")[0]+"").val()==''){
					alert(arr[i].split(",")[1]+""+msg);
					return false;				
				}
			}
			return true;
		}else{
			if($("#"+names).val() == ''){
				var arr = names.split(",");
				alert(arr[1]+""+msg);
				return false;
			}else{
				return true;
			}
		}
	}
	return false;
}
//验证电话号码
function checkTel(value,showId){
	if(!checkTelphone(value,'电话号码',showId))return false;
}
function checkTelphone(id,msg,showId){
	var norm = /\d{3}-?\d{8}|\d{4}-?\d{7}/;
	return checkObj(id,norm,msg,showId);
}

//验证身份证号(15-18位数字)
function checkCard(value,showId){
	if(!checkIdentityCard(value,'身份证号',showId))return false;
 }
function checkIdentityCard(id,msg,showId){
	var norm = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	return checkObj(id,norm,msg,showId);
}

// 验证用户名
function checkName(value,showId){
	if(!checkAccount(value,'名称',showId))return false;
}
function checkAccount(id,msg,showId){	
	var norm = /^[\u4e00-\u9fa5a-zA-Z0-9_]{1,30}$/;
	return checkObj(id,norm,msg,showId);
}

/*校验电话及身份证号*/
function checkObj(id,norm,msg,showId){
	if(!norm.test(id)){
		document.getElementById(showId).innerHTML=msg+"格式错误";
 		return false;
	}
	document.getElementById(showId).innerHTML="";
	return true;
}
/*解析图片*/
/*	
 * 
  取详情图（多个） 追加图片
	$(function(){
		var photoBook ="${photoBook.photos!}";
		initDetail('pclass',photoBook);
	})
	function initDetail(o,c){
		var u = "";
		if(c.indexOf(",")>0){
		 var len = c.split(",").length;
			for(i = 0; i < len; i++){
				u = aet();
				$("."+o+"").children().append(u);
			} 
		}
		$("."+o+"").children().children().each(function(i){
				if(c != '' && c != null){
					if(c.indexOf(",")>0){
					var	cc = c.split(",");
					if(i < cc.length){
						var	p=cc[i].substring(1,cc[i].length);
					}
					}
					$(this).find("img").removeAttr("src");
					$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
				}
		})
	}
	function aet(){
		var html ="";
		var name = "${photoBook.name}";
		var time = "${tagUtils.formatDate(photoBook.createTime)!}";
		html ="<li><a href=\"\"><img width=\"228\" height=\"137\" \/><\/a><span>"+name+"<\/span><div class=\"listimg\"><div class=\"summary\"><p><em><\/em>"+time+"<\/p> <\/div></div><\/li>";
		return html;
	} 
 解析详情图片 一个photos里多张图 detail
	$(function(){
		var cars ="${hotel.photos!}";
		initDetail('detailclass',cars);
	})
	// 取详情图（多个）
	function initDetail(o,c){
		$("."+o+"").children().children().each(function(i){
				if(c != '' && c != null){
					if(c.indexOf(",")>0){
					var	cc = c.split(",");
					if(i < cc.length){
						var	p=cc[i].substring(1,cc[i].length);
					}
					}
					$(this).find("img").removeAttr("src");
					$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
				}
		})
	}
	
	解析 多个对象图片 每个图片div加上 class="pclass" list
	$(function(){
		initList('oclass');
	})
	function initList(oclass){
		var str="";
		<#list rcarPager.itemList as u>
			str +="${u.photos}"+";";
		</#list>
		var photoArray=str.substring(0,str.length-1);
		$("."+oclass+"").each(function(i){
				var p = photoArray.split(";")[i];
				if(p != '' && p != null){
					if(p.indexOf(",")>0){
						p = p.split(",");
						p = p[0];
						p = p.substring(1,p.length);
					}
					$(this).find("img").removeAttr("src");
					$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
				}
		})
	}
	解析多个组图  一个类 多个对象里  多组photos的多张图片 及带评分**的图
		$(function(){
			var score = "";
			var str ='' ;
			<#list foodList as u>
				str +="${u.photos!}"+";";
			</#list>
			init("foodListClass","foodListChild",str,score);
			str = (str=='') ? '' : str='';
		})
		function init(oclass,childclass,str,score){
		var score = score.split(",");
		var photoArray=str.substring(0,str.length-1);
		$("."+oclass+"").each(function(i){
			var p = photoArray.split(";")[i];
			if(p != '' && p != null){
				if(p.indexOf(",")>0){
					p = p.split(",");
					p = p[0];
					p = p.substring(1,p.length);
				}
				$(this).find("img").removeAttr("src");
				$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
			}
			$("."+childclass+"").each(function(k){
					if(i == k){
					 	appendScore($(this),parseInt(score[i]),yellowStar,darkStar);
					 	return false;
					 }
					
			})
		})
	
	}
	function appendScore(obj,score,y,d){
		if(score == 'NaN' || score == '' ) score = 0;
		for(i=0;i<5;i++){
			if(i<score){
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+y+"");
			}else{
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+d+"");
			}
		}
	}
*/
//评论分页
function getPages(n){
	pages += n;
	return pages;
}