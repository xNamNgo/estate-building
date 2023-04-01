<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="assignmentAPI" value="/api/building/assignment-building"/>
<html>
<head>
    <title>Title</title>
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
                <li class="active"><strong>Danh sách tòa nhà</strong></li>
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
                <h4 class="widget-title">Tìm kiếm tòa nhà</h4>

                <div class="widget-toolbar">
                    <a href="#" data-action="collapse">
                        <i class="ace-icon fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>

            <!-- widget body -->
            <div class="widget-body">
                <div class="widget-main">
                    <form:form modelAttribute ="searchBox" action="${buildingListUrl}" id="listForm" method="get">
                        <div class="row">
                            <div class="col-xs-12"><!-- PAGE CONTENT BEGINS -->
                                <div class="col-xs-6">
                                    <div>
                                        <label for="name"><strong>Tên tòa nhà</strong></label>
                                        <input type="text" id="name" class="form-control" name="name"
                                               value="${searchBox.name}"/>
                                    </div>
                                </div>

                                <div class="col-xs-6">
                                    <div>
                                        <label for="floorArea"><strong>Diện tích sàn</strong></label>
                                        <input type="number" id="floorArea"
                                               class="form-control" name="floorArea" value="${searchBox.floorArea}"
                                        />
                                    </div>
                                </div>

                                    <%--//_client: load quận --%>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label><strong>Quận hiện có</strong></label>
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <form:select path="district" class="form-control">
                                                            <form:option value="" label="---Chọn quận---"/>
                                                            <form:options items="${districts}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="ward"><strong>Phường</strong></label>
                                                <input type="text" id="ward" name="ward" value="${searchBox.ward}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="street"><strong>Đường</strong></label>
                                                <input type="text" id="street" name="street" value="${searchBox.street}"
                                                       class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="numberOfBasement"><strong>Số tầng hầm</strong></label>
                                                <input type="text" id="numberOfBasement" name="numberOfBasement"
                                                       value="${searchBox.numberOfBasement}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="direction"><strong>Hướng</strong></label>
                                                <input type="text" id="direction" name="direction"
                                                       value="${searchBox.direction}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="level"><strong>Hạng</strong></label>
                                                <input type="text" id="level" name="level" class="form-control"
                                                       value="${searchBox.level}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-xs-3">
                                            <div style="margin-top: 10px">
                                                <label for="rentAreaFrom"><strong>Diện tích từ</strong></label>
                                                <input type="number" id="rentAreaFrom" name="rentAreaFrom"
                                                       value="${searchBox.rentAreaFrom}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-3">
                                            <div style="margin-top: 10px">
                                                <label for="rentAreaTo"><strong>Diện tích đến</strong></label>
                                                <input type="number" id="rentAreaTo" name="rentAreaTo"
                                                       value="${searchBox.rentAreaTo}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-3">
                                            <div style="margin-top: 10px">
                                                <label for="costRentFrom"><strong>Giá thuê từ</strong></label>
                                                <input type="number" id="costRentFrom" name="costRentFrom"
                                                       value="${searchBox.costRentFrom}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-3">
                                            <div style="margin-top: 10px">
                                                <label for="costRentTo"><strong>Giá thuê đến</strong></label>
                                                <input type="number" id="costRentTo" name="costRentTo"
                                                       value="${searchBox.costRentTo}"
                                                       class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="managerName"><strong>Tên quản lý</strong></label>
                                                <input type="text" id="managerName" name="managerName"
                                                       value="${searchBox.managerName}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-xs-4">
                                            <div style="margin-top: 10px">
                                                <label for="phoneNumber"><strong>Điện thoại quản lý</strong></label>
                                                <input type="text" id="phoneNumber" name="phoneNumber"
                                                       value="${searchBox.phoneNumber}"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                            <%-- //_client: load nhân viên phụ trách--%>
                                        <div class="col-xs-4" style="margin-top: 10px">
                                            <label class="col-sm-112 no-padding-left">
                                                <b>Chọn nhân viên phụ trách</b>
                                            </label>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="" label="---Chọn nhân viên---"/>
                                                        <form:options items="${staffMaps}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                                    <%--                                hiển thị types --%>
                                <div class="col-xs-11">
                                    <c:forEach var="type" items="${types}">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" value="${type.key}" name="types"
                                                   <c:if test="${searchBox.types.contains(type.key)}">checked</c:if>
                                            />${type.value}</label>
                                    </c:forEach>
                                </div>

                                <div style="margin-top: 25px; margin-left: 8px">
                                    <button type="button" class="btn btn-sm btn-success" id="btnSearch">
                                        Tìm kiếm
                                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                    </button>
                                </div>
                                <!-- PAGE CONTENT ENDS -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </form:form>


                </div>
            </div>
        </div>

        <!-- icon thêm tòa nhà,xóa tòa nhà -->
        <div class="pull-right" style="margin-right: 15px">
            <a href="<c:url value="/admin/building-edit"/>">
                <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà">
                    <i class="fa fa-plus-circle" aria-hidden="true"></i>
                </button>
            </a>
            <button class="btn btn-white btn-info btn-bold" id="btnDelete" data-toggle="tooltip" title="Xóa tòa nhà"
                    disabled>
                <i class="fa fa-trash-o" aria-hidden="true"></i>
            </button>
        </div>

        <div class="row" style="margin-top: 60px; margin-right: 15px; margin-left: 15px">
            <div class="col-xs-12">
                <table id="buildingList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Ngày</th>
                        <th>Tên sản phẩm</th>
                        <th>Địa chỉ</th>
                        <th>Tên quản lý</th>
                        <th>Số điện thoại</th>
                        <th>D.T sàn</th>
                        <th>D.T trống</th>
                        <th>Giá thuê</th>
                        <%--                        <th>Phí dịch vụ</th>--%>
                        <%--                        <th>Phí MG</th>--%>
                        <th>Thao tác</th>
                    </thead>

                    <%-- //_client: load all fields của building  ra table --%>
                    <tbody>
                    <c:forEach var="item" items="${buildings}">
                        <tr>
                            <td><input type="checkbox" value="${item.id}"
                                       id="checkbox_${item.id}"></td>
                            <td>${item.modifiedDate}</td>
                            <td>${item.name}</td>
                            <td>${item.address}</td>
                            <td>${item.managerName}</td>
                            <td>${item.phoneNumber}</td>
                            <td>${item.floorArea}</td>
                            <td>${item.rentArea}</td>
                            <td>${item.rentPrice}</td>
                                <%--                            <td>${buildingListItem.serviceFee}</td>--%>
                                <%--                            <td>${buildingListItem.brokerageFee}</td>--%>
                            <td>
                                <button type="button" class="btn btn-primary btn-sm" data-toggle="tooltip"
                                        title="Giao tòa nhà" onclick="assignmentBuilding(${item.id})">
                                    <i class="fa fa-users" aria-hidden="true"></i>
                                </button>
                                <button type="button" class="btn btn-primary btn-sm" data-toggle="tooltip"
                                        title="Chỉnh sửa tòa nhà" onclick="updateBuilding(${item.id})">
                                    <i class="fa fa-pencil" aria-hidden="true"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
            <!-- /.span -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.page-content -->
</div>

<div class="modal fade" id="buildingAssignmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Giao tòa nhà cho nhân viên quản lý</h5>
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
                    <input type="hidden" id="buildingId" name="buildingId" value="">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        openModalAssignmentBuilding();
        loadStaff(buildingId);
        $("#buildingId").val(buildingId); // set value cho buildingId
        console.log("building id : " + $("#buildingId").val());
    }

    function openModalAssignmentBuilding() {
        $("#buildingAssignmentModal").modal();
    }

    //_ajax load danh sách nhân viên giao tòa nhà .
    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/staffs",
            //data: JSON.stringify(data),
            dataType: "json",
            //contentType: "application/json",
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

    $("#btnAssignBuilding").click(function (e) {
        e.preventDefault();
        var data = {};
        // buildingId đang là 1 hidden input , cho nên ta get value bằng cách này .
        data['buildingId'] = $("#buildingId").val();
        // $('#staffList').find('tbody input[type=checkbox]:checked');
        var staffIdList = $('#staffList').find('tbody input[type=checkbox]:checked').map(function (){
            return $(this).val(); // đứng ở thằng modal - lấy tất cả value của checkbox có "checked"
        }).get();
        data['staffIdList'] = staffIdList;

        // call api
        assignStaff(data);
    });

    function assignStaff(data){
        $.ajax({
            type: "POST",
            url: "${assignmentAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("success");
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            },
        });
    }

    $("#btnDelete").click(function (e) {
        e.preventDefault();
        var data = {};

        // Lấy danh sách id có checkbox là checked
        var idList = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function (){
            return $(this).val(); // đứng ở thằng modal - lấy tất cả value của checkbox có "checked"
        }).get();
        data["idList"] = idList;

        // call api
        deleteBuilding(data);
    });

    function deleteBuilding(data){
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("success");
                location.reload();
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            },
        });
    }

    function updateBuilding(buildingId) {
        var url = "/admin/building-edit?building_id=" + buildingId;
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
