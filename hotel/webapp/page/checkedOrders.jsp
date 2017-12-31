<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2017/12/1
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CheckedOrders</title>
    <link rel="stylesheet" href="/resources/css/buttons.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/bootstrap-table2.css" >

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="/resources/js/bootstrap-table.js"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
    
    
    <script>
        var row;
        $(document).ready(function(){


            $('#btn_CheckOut').click(function SelectAndCheckOut() {
                    var rows = $("#CheckedOrderInfoTable").bootstrapTable('getSelections');
                    if (rows.length== 0) {
                        alert("请先选择要退房的订单!");
                        return;
                    }


                     row=rows[0];
                    $('#guestName').attr("value",row['guestEntity']['guestName']);
                    $('#guestIdcard').attr("value",row['guestEntity']['idCard']);
                    $('#guestTeleNumber').attr("value",row['guestEntity']['phoneNum']);
                    $('#guestGender').attr("value",row['guestEntity']['gendar']);
                    $('#guestType').attr("value",row['GuestType']);
                    $('#guestCheckInTime').attr("value",row['checkInTime']);
                    $('#guestCheckOutTime').attr("value",row['checkOutTime']);
                    $('#guestStayDays').attr("value",row['orderEntity']['stayDays']);
                    $('#guestNum').attr("value",row['orderEntity']['numOfPeople']);
                    $('#note').attr("value",row['orderEntity']['notes']);
                var moneyPay=row['orderEntity']['stayDays']*row['roomEntity']['roomPrice'];
                $('#money').attr("value",moneyPay);

                var checkedOrderInfo=$("#CheckedOrderInfoTable").bootstrapTable('getData');
                var rooms=new Array();
                checkedOrderInfo.forEach(function (item, index) {
                    if(item['orderEntity']['orderId']==row['orderEntity']['orderId']){
                        rooms.push(item['roomEntity']);
                    }
                });
                $('#CheckOutRoomTable').bootstrapTable('load',rooms);
                readyForcheckOutByRoomID();
                }
            );

            function readyForcheckOutByRoomID() {
                var msg = "您真的确定要退房吗？";
                if (confirm(msg) == true) {
                    $.ajax({
                        url: "/CheckedOrders/getTimeForCheckOut",
                        type: "post",
                        traditional:"true",
                        success: function (data) {
                            $('#guestCheckOutTime').attr("value",data);
                            $('#CheckOutModel').modal('show');
                        }
                    })

                }
            }

            $('#subTable').click(sendCheckOutInfo);

            function sendCheckOutInfo() {

                $.ajax({
                    url: "/CheckedOrders/CheckOut",
                    type: "post",
                    traditional:"true",
                    data: {
                        ids: row['orderEntity']['orderId'],
                        DATE:$('#guestCheckOutTime').attr("value")  ,
                        method:$('#payMethod option:selected').val(),
                        note:$('#note').attr("value")
                    },
                    success: function () {

                        //重新加载数据
                        $('#CheckOutModel').modal('hide');
                        $("#CheckedOrderInfoTable").bootstrapTable('refresh');

                    }
                });
            }


            $('#btn_GiveMoney').click(function () {
                var rows = $("#CheckedOrderInfoTable").bootstrapTable('getSelections');
                if (rows.length== 0) {
                    alert("请先选择要续房的订单!");
                    return;
                }
                row=rows[0];
                $('#addDaysModal').modal('show');
            });

            $('#addDaysConfirm').click(function () {
                if($('#addDays').val().length==0||$('#addDays').val()<1||$('#addDays').val()>20){
                    alert("天数错误");
                    return;
                }
                $.ajax({
                    url: "/CheckedOrders/addOrderDays",
                    type: "post",
                    traditional:"true",
                    data: {
                        id:row['orderEntity']['orderId'],
                        days:document.getElementById("addDays").value
                    },
                    success: function () {

                        //重新加载数据
                        $('#addDaysModal').modal('hide');
                        $("#CheckedOrderInfoTable").bootstrapTable('refresh');

                    }
                });
            });




        });
        
    </script>
</head>
<body>

<div class="col-md-offset-2  col-md-8">
    <div class="card-view">
        <div class="panel-heading">
            <h4 class="card-title text-center"style="font-family: 等线;font-size: xx-large">已入住订单管理</h4>
        </div>
        <div id="toolbar" class="btn-group">
            <button id="btn_GiveMoney" type="button" class="btn btn-default" >
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>续房
            </button>
            <button id="btn_CheckOut" type="button" class="btn btn-default"  >
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>退房
            </button>
        </div>

        <table id="CheckedOrderInfoTable" data-toggle="table" data-url="/CheckedOrders/GetCheckedOrdersInfo" data-method="post"
       data-toolbar="#toolbar"
       data-click-to-select="true"
       data-checkbox-header="true"
       data-show-refresh="true"
       data-search="true"
  data-show-toggle="true"
<%--data-show-columns="true"--%>
data-checkbox="true"
data-pagination="true"
data-single-select="true">
<thead>
<tr>
    <th data-checkbox="true" data-field="false"></th>
 <th data-field="roomEntity.roomId">房号</th>
 <th data-field="checkInTime">入住时间</th>
 <th data-field="checkOutTime">预定退房时间</th>
 <th data-field="guestEntity.guestName">客户名称</th>
 <th data-field="guestEntity.idCard">客户身份证号</th>
 <th data-field="guestEntity.phoneNum">客户电话号码</th>
 <th data-field="GuestType">客户类型</th>
 <th data-field="roomEntity.roomPrice">房间价格</th>
 <th data-field="RoomState">房间状态</th>
</tr>
</thead>
</table>
    </div>
</div>



<div class="modal fade" id="CheckOutModel" tabindex="-1" role="dialog" aria-labelledby="CheckOutModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" >退房办理</h3>
            </div>
            <div class="modal-body">

                <form id="CheckOutGuest"   method="post" >
                    <div  class="form-group">
                        <div class="row">
                        <div class=" col-xs-6">
                            <label for="guestName">客户姓名</label>
                            <input type="text" class="form-control" name="guestName" id="guestName" disabled="disabled">
                        </div>
                        <div class=" col-xs-6">
                            <label for="guestIdcard">客户身份证号码</label>
                            <input type="number" class="form-control" name="guestIdcard" id="guestIdcard" disabled="disabled">
                        </div>
                        </div>
                    </div>

                    <div  class="form-group">
                        <div class="row">
                            <div class=" col-xs-6">
                            <label for="guestTeleNumber">客户电话号码</label>
                            <input type="text" class="form-control" name="guestTeleNumber" id="guestTeleNumber" disabled="disabled">
                        </div>
                            <div class=" col-xs-6">
                            <label for="guestGender">客户性别</label>
                                <select id="guestGender" class="form-control"disabled="disabled">
                                    <option value="0">男</option>
                                    <option value="1">女</option>
                                </select>


                        </div>
                        </div>
                    </div>

                    <div  class="form-group">

                            <label for="guestType">客户类型</label>
                            <input type="text" class="form-control" name="guestType" id="guestType" disabled="disabled">

                    </div>

                    <div  class="form-group">
                        <div class="row">
                        <div class=" col-xs-6">
                            <label for="guestCheckInTime">入住时间</label>
                            <input type="text" class="form-control" name="guestCheckInTime" id="guestCheckInTime" disabled="disabled">
                        </div>
                        <div class=" col-xs-6">
                            <label for="guestCheckOutTime">退房时间</label>
                            <input type="text" class="form-control" name="guestCheckOutTime" id="guestCheckOutTime" disabled="disabled">
                        </div>
                        </div>
                    </div>

                    <div  class="form-group">
                        <div class="row">
                        <div class=" col-xs-6">
                            <label for="guestStayDays">入住天数</label>
                            <input type="text" class="form-control" name="guestStayDays" id="guestStayDays" disabled="disabled">
                        </div>
                        <div class=" col-xs-6">
                            <label for="guestNum">入住人数</label>
                            <input type="number" class="form-control" name="guestNum" id="guestNum" disabled="disabled">
                        </div>
                        </div>
                    </div>

                    <div  class="form-group">
                            <label for="note">备注</label>
                            <input type="text" class="form-control" name="note" id="note" maxlength="20">
                    </div>

                    <div  class="form-group">
                        <div class="row">
                        <div class=" col-xs-6">
                            <label for="money">总金额</label>
                            <input type="text" class="form-control" name="money" id="money" disabled="disabled">
                        </div>
                        <div class=" col-xs-6">
                            <label for="payMethod">付款方式</label>
                            <select name="payMethod" id="payMethod" class="form-control" style=" border-color: #66afe9; outline: 0;  -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, .6);  //添加了阴影效果 box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, .6);">
                                <option value="0">现金</option>
                                <option value="1">银行卡</option>
                                <option value="2">微信/支付宝</option>
                            </select>
                        </div>
                        </div>
                    </div>

                </form>





                <table id="CheckOutRoomTable" data-toggle="table">
                    <thead>
                    <tr>
                        <th data-field="roomId">房号</th>
                        <th data-field="roomStyle">房间类型</th>
                        <th data-field="roomPrice" >房间价格</th>
                    </tr>
                    </thead>
                    <tbody id="dataTable"></tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="subTable" type="button" class="btn btn-primary" >Send message</button>
            </div>
        </div>
    </div>
</div>



<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="addDaysModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" >续房办理</h5>
            </div>
            <div class="modal-body">

               <form class="form-inline">
                   <div class="form-group">
                       <input type="number" class="form-control" style="width: 220px" name="addDays" placeholder="在此输入续房天数" id="addDays" min="0" step="1">
                   </div>
                   <div class="form-group">
                       <button class="button button-3d button-box button-rounded" id="addDaysConfirm">确定</button>
                   </div>

           </form>
           </div>
        </div>
    </div>
</div>


</body>
</html>
