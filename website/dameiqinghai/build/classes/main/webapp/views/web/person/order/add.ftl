<title>app订单新增</title>

<#include "../../public/top.ftl">

<script type="text/javascript">
	function checkSub(){
		var names = "name,订单名";
		return checkCommit(names);
	}
</script> 
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                 <#include "../public.ftl">           
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>发布订单</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/order/dosave${suffix}" method="POST" enctype="multipart/form-data">
                    		<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
    						<ul>
                                <li><label>订单编号：</label><input type="text" id="name" readonly="readonly" name="orderpageNo" value="" class="i_btn" /></li>
                               	<input type="hidden" name="supplierId" value=""/>
                                <li><label>商品名：</label><input type="text" id="name" readonly="readonly" name="" value="" class="i_btn" /></li>
                                <li><label>总价：</label><input type="text" id="name" readonly="readonly" name="" value="" class="i_btn" /></li>
                                <li><label>数量：</label><input type="text" id="name" readonly="readonly" name="" value="" class="i_btn" /></li>
                                <li><label>订单类型：</label><input type="text" readonly="readonly" id="name" name="orderpageType" value="" class="i_btn" /></li>
                                <li><label>付款状态：</label><input type="text" readonly="readonly" id="name" name="type" value="" class="i_btn" /></li>
                               <li>
                                	<label>订单详情：</label>
                                    <div class="shixj">
                                    	<textarea readonly="readonly" id="memo" name="memo"></textarea>
                                    </div>
                                </li>
                                <li><label>下单时间：</label><input type="text" readonly="readonly" id="name" name="createTime" value="" class="i_btn" /></li>
                                <li><label>购买手机号：</label><input type="text" readonly="readonly" id="name" name="" value="" class="i_btn" /></li>
                                <li><label>原价：</label><input type="text" readonly="readonly" id="name" name="" value="100" class="i_btn" /></li>
                                <li><label>现价：</label><input type="text" readonly="readonly" id="name" name="" value="85" class="i_btn" /></li>
                            </ul>
                          <div class="xiug_btn">
                                <input type="submit" value="提交" onclick="return checkSub(this.form)"/>
                          </div>    
                        </form>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">
