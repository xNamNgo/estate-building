<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="customerAPI" value="/api/customer"/>


<html>

<head>
    <title>Quản lý khách hàng</title>
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
                    <a href="<c:url value=" /admin/home" />">Trang chủ</a>
                </li>
                <li class="active"><strong>Quản lý khách hàng</strong></li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <br class="page-content"/>

        <div class="page-header">
        </div>
        <!-- /.page-header -->

        <!-- Tìm kiếm tòa nhà -->
        <div class="widget-box" style="margin-left: 20px; margin-right: 15px">
            <div class="widget-header">
                <h4 class="widget-title">Tìm kiếm khách hàng </h4>

                <div class="widget-toolbar">
                    <a href="#" data-action="collapse">
                        <i class="ace-icon fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>

            <!-- widget body -->
            <div class="widget-body">
                <div class="widget-main">
                    <form:form modelAttribute="customer" action="${customerListUrl}" id="listForm"
                               method="get">
                        <div class="row">
                            <div class="col-xs-12"><!-- PAGE CONTENT BEGINS -->
                                <div class="col-xs-4">
                                    <div>
                                        <label for="fullName"><strong>Tên khách
                                            hàng</strong></label>
                                        <input type="text" id="fullName" class="form-control"
                                               name="fullName" value="${customer.fullName}" />
                                    </div>
                                </div>

                                <div class="col-xs-4">
                                    <div>
                                        <label for="phone"><strong>Di động</strong></label>
                                        <input type="text" id="phone" class="form-control" name="phone"
                                               value="${customer.phone}" />
                                    </div>
                                </div>

                                <div class="col-xs-4">
                                    <div>
                                        <label for="email"><strong>Email</strong></label>
                                        <input type="text" id="email" class="form-control" name="email"
                                               value="${customer.email}" />
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-xs-4" style="margin-top: 10px">
                                <label class="col-sm-112 no-padding-left">
                                    <b>Chọn nhân viên phụ trách</b>
                                </label>
                                <div class="form-group">
                                    <div class="col-sm-6">
                                        <form:select path="staffId" class="form-control">
                                            <form:option value="" label="---Chọn nhân viên phụ trách---" />
                                            <form:options items="${staffs}" />
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="margin-top: 25px; margin-left: 8px">
                            <button type="button" class="btn btn-sm btn-success" id="btnSearch">
                                Tìm kiếm
                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <!-- icon thêm tòa nhà,xóa tòa nhà -->
        <div class="pull-right" style="margin-right: 15px">
            <a href="<c:url value=" /admin/customer-edit" />">
                <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà">
                    <i class="fa fa-plus-circle" aria-hidden="true"></i>
                </button>
            </a>
            <button class="btn btn-white btn-info btn-bold" id="btnDelete" data-toggle="tooltip"
                    title="Xóa tòa nhà" disabled>
                <i class="fa fa-trash-o" aria-hidden="true"></i>
            </button>
        </div>

        <%-- LIST--%>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <display:table name="customers.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${customerListUrl}" partialList="true" sort="external"
                                   size="${customers.totalItem}" defaultsort="2" defaultorder="ascending"
                                   id="tableList" pagesize="${customers.maxPageItems}" export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">

                        <display:column title="" class="center select-cell"
                                        headerClass="center select-cell">
                            <fieldset>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>

                        <display:column headerClass="text-left" property="fullName" title="Tên"/>
                        <display:column headerClass="text-left" property="phone" title="Di động"/>
                        <display:column headerClass="text-left" property="email" title="Email"/>
                        <display:column headerClass="text-left" property="need" title="Nhu cầu"/>
                        <display:column headerClass="text-left" property="createdBy" title="Người nhập"/>
                        <display:column headerClass="text-left" property="createdDate" title="Ngày nhập"/>
                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <button type="button" class="btn btn-primary btn-sm" data-toggle="tooltip"
                                    title="Giao tòa nhà" onclick="assignmentCustomer(${tableList.id})">
                                <i class="fa fa-users" aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-primary btn-sm" data-toggle="tooltip"
                                    title="Chỉnh sửa tòa nhà" onclick="updateCustomer(${tableList.id})">
                                <i class="fa fa-pencil" aria-hidden="true"></i>
                            </button>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div>
    </div>
    <!-- /.page-content -->
</div>

<div class="modal fade" id="customerAssignmentModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Giao khách hàng cho nhân viên quản lý</h5>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>


                    </tbody>
                    <input type="hidden" id="customerId" name="customerId" value="">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnAssignCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentCustomer(customerId) {
        $("#customerAssignmentModal").modal();
        loadStaff(customerId);
        $("#customerId").val(customerId);
    }


    // load danh sách nhân viên quản lý tòa nhà
    function loadStaff(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/staffs" + "?customer_id=" + customerId,
            dataType: "json",
            success: function (response) {
                console.log("success");
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class ="text-center">' + '<input type="checkbox" value = "' + item.staffId + '" id ="checkbox_' + item.staffId + '" class ="check-box-assignment" ' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            },
        });
    }

    // Giao khách cho nhân viên quản lý.
    $("#btnAssignCustomer").click(function (e) {
        e.preventDefault();
        var data = {};
        data['customerId'] = $("#customerId").val();
        var staffIdList = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val(); // đứng ở thằng modal - lấy tất cả value của checkbox có "checked"
        }).get();
        data['staffIdList'] = staffIdList;
        assignStaff(data);
    });

    // Giao khách cho nhân viên quản lý.
    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}/assignment",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("success");
                alert("Giao tòa nhà thành công!");
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            },
        });
    }


    // request an array of customer id
    $("#btnDelete").click(function (e) {
        showAlertBeforeDelete(function () {
            e.preventDefault();
            // Lấy danh sách id có checkbox là checked
            var data = $('#tableList').find('.select-cell [type=checkbox]:checked').map(function () {
                return $(this).val(); // đứng ở thằng modal - lấy tất cả value của checkbox có "checked"
            }).get();
            deleteCustomer(data);
        });
    });

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("success");
                window.location.href = "<c:url value='/admin/customer-list'/>";
                alert("Xóa khách hàng thành công!");
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
                alert("Xóa tòa nhà thất bại!");
            },
        });
    }

    function updateCustomer(customerId) {
        var url = "/admin/customer-edit?customer_id=" + customerId;
        window.location.href = url;
    }

    $("#btnSearch").click(function (e) {
        // ngăn chặn việc submit vào link hiện tại, thay vì link khác
        // action="192.168,../building-list"
        // nhưng không khai báo prevent default thì nó sẽ gọi trang hiện tại là admin/building-list
        e.preventDefault();
        $('#listForm').submit();
    });


</script>
</body>

</html>