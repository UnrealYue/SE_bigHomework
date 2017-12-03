<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2017/11/28
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>




<html>
<head>
    <title>VIP</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/resources/css/buttons.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/bootstrap-table2.css" >

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="/resources/js/bootstrap-table.js"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

    <script>
        var flag=0;
        var rows;
        $(document).ready(function(){

            $("#btn_edit").click(function flagAdd() {
                flag=1;
                rows = $("#VIP_table").bootstrapTable('getSelections');
                if (rows.length== 0) {
                    alert("请先选择要修改的记录!");
                    return;
                }
                if (rows.length> 1) {
                    alert("请选择一个会员");
                    return;
                }
                $('#guestId').attr("disabled",true);
                $('#exampleModalLabel').text("会员修改");
                $('#guestId').attr("value",rows[0]['1']);
                $('#birthday').attr("value",rows[0]['7']);
                $('#job').attr("value",rows[0]['8']);
                $('#balance').attr("value",rows[0]['6']);
                $('#exampleModal').modal('show');

            });

            $("#btn_add").click(function flagAdd() {
                flag=0;
                $('#guestId').attr("disabled",false);
                $('#exampleModalLabel').text("会员注册");
                $('#guestId').attr("value",'');
                $('#birthday').attr("value",'');
                $('#job').attr("value",'');
                $('#balance').attr("value",'');
                $('#exampleModal').modal('show')
            });

            $("#subTable").click(function sub() {

                if(flag==0){
                    $.ajax({
                        type: "POST",
                        url:"/VIP/AddVIP",
                        traditional:"true",
                        data:$('#memb').serialize(),
                        success: function () {

                            //重新加载记录
                            //重新加载数据
                            $('#exampleModal').modal('hide');
                            $("#VIP_table").bootstrapTable('refresh');
                        }
                    });
                }

                if(flag==1){

                    var dataaa=new Array() ;
                    for (var i = 0; i < rows.length; i++) {
                        dataaa[i] = rows[i]['1'];
                    }
                    editUser(dataaa);

                    function editUser(ids) {
                        var msg = "您真的确定要修改吗？";
                        if (confirm(msg) == true) {

                            $.ajax({
                                url: "/VIP/EditVIPs",
                                type: "post",
                                traditional:"true",
                                data:
                                    $('#memb').serialize()+"&"+"ids=" + ids,
                                success: function () {

                                    //重新加载记录
                                    //重新加载数据
                                    $('#guestId').attr("disabled",false);
                                    $('#exampleModal').modal('hide')
                                    $("#VIP_table").bootstrapTable('refresh');
                                },
                                error:function () {
                                    $('#guestId').attr("disabled",false);
                                }
                            });
                        }
                    }
                }

            });

            $("#btn_delete").click(function () {
                    rows = $("#VIP_table").bootstrapTable('getSelections');
                    if (rows.length== 0) {
                        alert("请先选择要删除的记录!");
                        return;
                    }
                    var dataaa=new Array() ;
                    for (var i = 0; i < rows.length; i++) {
                        dataaa[i] = rows[i]['1'];
                    }

                    deleteUser(dataaa);
                }
            );

            function deleteUser(ids) {
                var msg = "您真的确定要删除吗？";
                if (confirm(msg) == true) {
                    $.ajax({
                        url: "/VIP/deleteVIPs",
                        type: "post",
                        traditional:"true",
                        data: {
                            ids: ids
                        },
                        success: function () {

                            //重新加载记录
                            //重新加载数据
                            $("#VIP_table").bootstrapTable('refresh');
                        }
                    });
                }
            }

        });
    </script>


</head>
<body>

<div class="col-md-offset-3  col-md-6">
    <div class="card" >
        <div class="panel-heading">
            <h3 class="card-title text-center"style="font-family: 等线;font-size: xx-large">VIP管理</h3>
        </div>
        <div class="card-body">
            <div id="toolbar" class="btn-group">
                <button id="btn_add" type="button" class="btn btn-default"  >
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>注册
                </button>
                <button id="btn_edit" type="button" class="btn btn-default" >
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
                <button id="btn_delete" type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
            </div>
            <table id="VIP_table" data-toggle="table" data-url="/VIP/GetAllMembers" data-method="post"
                   data-toolbar="#toolbar"
                   data-click-to-select="true"
                   data-checkbox-header="true"
                   data-show-refresh="true"
                   data-search="true"
                   data-show-toggle="true"
<%-- data-show-columns="true"--%>
 data-checkbox="true"
 data-pagination="true"
>
<thead>
<tr>
  <th data-checkbox="true" data-field="false"></th>
  <th data-field="1">会员ID</th>
  <th data-field="2">姓名</th>
  <th data-field="3">性别</th>
  <th data-field="4">身份证号</th>
  <th data-field="5">电话</th>
  <th data-field="6">余额</th>
  <th data-field="7">生日</th>
  <th data-field="8">职业</th>
  <th data-field="9">累计消费</th>
</tr>
</thead>
</table>
</div>
</div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header " >
<h4 class="modal-title" id="exampleModalLabel">会员注册</h4>
<button type="button" class="close" data-dismiss="modal" aria-label="Close">
  <span aria-hidden="true">&times;</span>
</button>
</div>
<div class="modal-body">
<form id="memb"   method="post" >

  <div id="guestIdINPUT" class="form-group">
      <label for="guestId">顾客ID</label>
      <input type="number" class="form-control" name="guestId" id="guestId">
  </div>

  <div class="form-group">
      <label for="birthday">生日</label>
      <input type="date" class="form-control" name="birthday" id="birthday">
  </div>

  <div class="form-group">
      <label for="job">职业</label>
      <input type="text" class="form-control" name="job" id="job">
  </div>

  <div class="form-group">
      <label for="balance">余额</label>
      <input type="number" class="form-control" name="balance" id="balance" min="0">
  </div>


</form>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
<button id="subTable" type="button" class="btn btn-primary "  >Send message</button>
</div>
</div>
</div>
</div>


<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
          <form>

          </form>
        </div>
    </div>
</div>

</body>
</html>
