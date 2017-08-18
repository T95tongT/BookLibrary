<script type="text/javascript" >
    function addbook() {
        if (document.all.username.value == '') {

            document.getElementById("sticky_a1").click();
            return;
        }
        if (document.all.password.value == '') {

            document.getElementById("sticky_a2").click();
            return;
        }

        document.all.add_form.submit();

    }
</script>
<div class="modal hide fade" id="addbook">
    <div class="modal-header">
        <button class="close" data-dismiss="modal"
                onclick="">×</button>
        <form method="post" action="book?action=addbook" name="add_form"
              id="add_form">


            <div class="alert alert-info">图书管理系统</div>


            <div class="cnt_b">
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-user"></i></span> <input
                            type="text" id="bookname" name="bookname" placeholder="书名"
                            value="" />

                    </div>
                </div>
                <br />
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-lock"></i></span> <input
                            type="type" id="writer" name="writer" placeholder="作者"
                            value="" />

                    </div>
                </div>
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-lock"></i></span> <input
                            type="type" id="publish" name="publish" placeholder="出版社"
                            value="" />

                    </div>
                </div>
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-lock"></i></span> <input
                            type="type" id="sort" name="sort" placeholder="品类"
                            value="" />

                    </div>
                </div>
                <div class="formRow">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-lock"></i></span> <input
                            type="type" id="booknum" name="booknum" placeholder="数量"
                            value="" />

                    </div>
                </div>
            </div>
        </form>
        <div class="modal-footer">
            <button class="btn btn-inverse pull-right" type="button"
                    onclick="addbook()">提交</button>




    </div>
</div>

