<div class="modal hide fade" id="alogin">
    <div class="modal-header">
        <button class="close" data-dismiss="modal"
                onclick="">×</button>
        <h2>登录用户</h2>


        <a href="javascript:void(0)" id="sticky_a"></a> <a
            href="javascript:void(0)" id="sticky_a1"></a> <a
            href="javascript:void(0)" id="sticky_a2"></a> <a
            href="javascript:void(0)" id="sticky_a3"></a> <a
            href="javascript:void(0)" id="sticky_a4"></a>
        <a
                href="javascript:void(0)" id="sticky_a5"></a>
        <a href="javascript:void(0)" id="sticky_a6"></a>
        <a href="javascript:void(0)" id="sticky_a7"></a>
        <a href="javascript:void(0)" id="sticky_a8"></a>
        <a href="javascript:void(0)" id="sticky_a9"></a>
        <a href="javascript:void(0)" id="sticky_a10"></a>
    </div>
    <div class="modal-body" data-backdrop="static">
        <form method="post" action="admin" name="alogin_form"
              id="alogin_form">


            <div class="alert alert-info">请登录易图书管理系统</div>


            <div class="cnt_b">
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-user"></i></span> <input
                            type="text" id="username" name="username" placeholder="用户名"
                            value="toto" />

                    </div>
                </div>
                <br />
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-lock"></i></span> <input
                            type="password" id="password" name="password" placeholder="密码"
                            value="" /> <input type="hidden" name="action" value="alogin">

                    </div>
                </div>
                <div class="formRow clearfix">
                    <label class="checkbox">
                        <input type="checkbox" id="sun" name="sun" />记住一星期</label>
                </div>

            </div>
            <div class="btm_b clearfix"></div>
        </form>

        <div class="modal-footer">
            <button class="btn btn-inverse pull-right" type="button"
                    onclick="alogin()">登录</button>

        </div>



    </div>
</div>

