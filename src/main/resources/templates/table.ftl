<script type="text/javascript" src="../static/js/jquery.min.js">
	
</script>
<script type="text/javascript">
	/*function test() {
		$.ajax({
			url:"borrow?action=borrow",
			data:$("#form").serialize(),
			type:"post",
			dataType:"text",
			success:function (data) {
                if (data == "0") {
                    $("#btn").html("已注册过");
                }
                if (data == "1") {
                    $("#btn").html("注册成功");
                }
            }
		});
    }*/
</script>
<div id="contentwrapper">
	<div class="main_content">
		<#include "top.ftl">

		
		<div class="row-fluid">
			<div class="span12">
				<h4 class="heading">WELCOME俺们图书馆</h4>

				
					<div class="alert alert-error">
						<a class="close" data-dismiss="alert">×</a>
						<strong>操作信息:


						<#if info??>
						${info}
						<#else>
                            欢迎游客
						</#if>






						</strong>
						
					</div>


			<#if auser??||suser??>


                <div class="btn-group sepH_b">
                    <button data-toggle="dropdown" class="btn dropdown-toggle" >
                        行数 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">


                        <li><a href="book?action=queryall&row=5&cur=${page.number}">默认5行</a></li>
                        <li><a href="book?action=queryall&row=10&cur=${page.number}">每页10行</a></li>
                        <li><a href="book?action=queryall&row=2&cur=${page.number}">每页2行</a></li>
                    </ul>
                </div>

			</#if>


				<table class="table table-bordered table-striped table_vam"
					id="dt_gal">
					<thead>
						<tr>

							<th class="table_checkbox">序号</th>
							<th style="width: 50px">书名</th>
							<th style="width: 100px">作者</th>
							<th style="width: 420px">出版社</th>
							<th style="width: 60px">品种</th>
							<th style="width: 60px">数量</th>
						</tr>
					</thead>
					<tbody>

			<#list page.content as d>

								<tr>

									<td>${d_index+1}</td>
									<td>
									<#if auser??&&d.booknum!=0>
                                        借书人
                                        <form id="form" action="borrow?action=borrow1&id=${d.bookid}" method="post">
										<input id="borrowbook" name="borrowbook" type="text"  />

                                            <input type="submit" value="提交"/>
                                    </form>
									</#if>
`									${d.bookname}
										<span><font color="red">
											<br/>
										<#if d.booknum<=0>此书借没了不能借了</#if></font></span>

									</td>
									<td>
									${d.writer}
									</td>
									<td>${d.publish}</td>
									<td>${d.sort}</td>
									<td>
										${d.booknum}
										
									</td>
								</tr>
</#list>
					</tbody>
				</table>



			</div>
		</div>
		
			<#include "page.ftl">
		

	</div>


</div>