<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="apiCustomer" value="/api/customer"/>
<c:url var="saveTransactionAPI" value="/api/customer/transaction"/>
<html>
<head>
    <title>Thao tác với tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">Trang chủ</a>
                </li>
                <li class="active">
                    <a href="<c:url value="/admin/building-list"/>"></a>
                </li>
                <li class="active">
                    Thao tác với khách hàng
                </li>

            </ul>
        </div>
        <br class="page-content"/>

        <!-- /.page-header -->

        <!-- page-content -->
        <div class="page-content">
            <h3>Thông tin khách hàng</h3>
            <br>
            <div class="row">
                <form class="form-horizontal" role="form" id="formEdit">
                    <input type="hidden" id="customerId" name="customerId" value="${customer.id}">
                    <div class="text-center">
                        <div class="form-group row">
                            <label for="fullName" class="col-sm-1 control-label">Tên đầy đủ</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="fullName" value="${customer.fullName}"
                                       name="fullName"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="phone" class="col-sm-1 control-label">Số điện thoại</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="phone" value="${customer.phone}"
                                       name="phone"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="email" class="col-sm-1 control-label">Email</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="email" value="${customer.email}"
                                       name="email"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="companyName" class="col-sm-1 control-label">Tên công ty</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="companyName" value="${customer.companyName}"
                                       name="companyName"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="need" class="col-sm-1 control-label">Nhu cầu</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="need" value="${customer.need}"
                                       name="need"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="note" class="col-sm-1 control-label">Ghi chú</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="note" value="${customer.note}"
                                       name="note"/>
                            </div>
                        </div>

                        <div class="col-md-9">
                            <button value="${customer.id}" class="btn btn-info" id="saveCustomer"
                                    type="button">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                <c:choose>
                                    <c:when test="${customer.id !=null}">
                                        Cập Nhập
                                    </c:when>
                                    <c:otherwise>
                                        Thêm Khách Hàng
                                    </c:otherwise>
                                </c:choose>
                            </button>&nbsp; &nbsp; &nbsp;
                            <input class="btn" id="btnBack" style="width:10%" value="Trở về"/>
                        </div>
                    </div>
                </form>
            </div>
            <br>
            <c:choose>
                <c:when test="${customer.id !=null}">
                    <div class="row mt-4">
                        <div class="col-xs-12">
                            <div>
                                <c:forEach var="item" items="${mapTransactionTypes}">
                                    <table style="width:100%" id="dynamic-table"
                                           class="table table-striped table-bordered table-hover">
                                        <caption id="${item.key}" value="${item.key}">
                                            <h3 style="color: blue">${item.value}
                                                <button class="btn btn-white btn-info btn-bold"
                                                        data-toggle="tooltip"
                                                        data-value="${item.key}" onclick="addTransaction(this)"
                                                        title="Thêm Giao Dịch">
                                                    <i class="fa fa-plus-circle	"></i>
                                                </button>
                                            </h3>
                                        </caption>
                                        <thead>
                                        <tr>
                                            <th style="width:30%">Ngày Tạo</th>
                                            <th style="width:70%">Ghi Chú</th>

                                        </tr>
                                        </thead>
                                        <tbody id="tbody">
                                        <c:forEach var="mapTransaction" items="${mapTransaction}">

                                            <c:choose>
                                                <c:when test="${mapTransaction.key == item.key}">
                                                    <c:forEach var="transaction" items="${mapTransaction.value}">
                                                        <tr>
                                                            <td>${transaction.createdDate}</td>
                                                            <td>${transaction.note}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose>


                                        </c:forEach>
                                        <tr>
                                            <td></td>
                                            <td><input style="width:70%" type="text"
                                                       id="noteTransaction_${item.key}"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </div>
        <!-- /.page-content -->
    </div>
</div>
<!-- /.main-content -->

<!-- basic scripts -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<script>
    $("#btnBack").click(function () {
        window.location.href = "/admin/customer-list";
    });

    function addTransaction(e) {
        var id = $('#customerId').val();
        var code = $(e).closest('caption').attr('value');
        var note = $('#noteTransaction_' + code).val();
        var transactionDTO = {
            customerId: id,
            code: code,
            note: note,
        }
        $.ajax({
            type: 'POST',
            url: '${saveTransactionAPI}',
            data: JSON.stringify(transactionDTO),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("sucess" + response);
                location.reload();
            },
            error: function (response) {
                console.log("failed" + response);
                location.reload();
            },
        });
    }

    $("#saveCustomer").click(function () {
        var formData = $("#formEdit").serializeArray();
        var data = {};
        $.each(formData, function (index, element) {
            data['' + element.name + ''] = element.value;
        });
        saveCustomer(data);
    });

    function saveCustomer(data) {
        $.ajax({
            type: "POST",
            url: "${apiCustomer}",
            data: JSON.stringify(data), // parse js object to json type.
            dataType: "json", // server respone JSON.
            contentType: "application/json", //client request to server JSON type
            success: function (response) {
                console.log("success");
                alert("Thêm khách hàng thành công!");
                window.location.href = "/admin/customer-list";
            },
            error: function (response) {
                alert("Thêm khách hàng thất bại");
                console.log("failed");
                console.log(respone);
            },
        });
    }


</script>
</body>
</html>
