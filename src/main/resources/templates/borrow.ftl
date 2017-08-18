<div class="modal hide fade" id="borrowform">
    <div class="modal-header">
        <button class="close" data-dismiss="modal"
                onclick="">×</button>

<div id="contentwrapper">
    <div class="main_content">



        <div class="row-fluid">

            <h4 class="heading">WELCOME俺们图书馆</h4>


            <strong>操作信息:


            <#if info??>
            ${info}
            <#else>
                欢迎游客
            </#if>


            </strong>




            <form action="borrow?action=huanshu">
            <table class="table table-bordered table-striped table_vam"
                   id="borrowform">
                <thead>
                <tr>

                    <th class="table_checkbox">序号</th>
                    <th style="width: 250px">书名</th>
                    <th style="width: 100px">时间</th>
                    <th style="width: 240px">借书人</th>
                    <th style="width: 220px">还书</th>
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
                    ${d.bookname}

                    </td>
                    <td>${d.time}</td>
                    <td>
                    ${d.username}

                    </td>
                    <td>
                    ${d.writer}

                    </td>
                    <td>${d.publish}</td>
                    <td>${d.sort}</td>
                    <td>
                    ${d.num}

                    </td>
                    <td>
                <#if auser??>
                    <a href="borrow?action=huanshu&id=${d.id}"/>还书</#if>

                    </td>
                </tr>
                </#list>
                </tbody>
            </table>


            </form>




        </div>
    <#include "page.ftl">

    </div>

</div>
       </div>