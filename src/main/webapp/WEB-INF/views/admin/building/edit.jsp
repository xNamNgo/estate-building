<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="apiBuilding" value="/api/building"/>
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
                    <a href="<c:url value="/admin/building-list"/>">Danh sách tòa nhà</a>
                </li>
                <li class="active">
                    Thao tác với tòa nhà
                </li>

            </ul>
        </div>
        <br class="page-content"/>

        <!-- /.page-header -->

        <!-- page-content -->
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-horizontal" role="form" id="formEdit">

                        <input type="hidden" id="id" name="id" value="${building.id}">

                        <!-- Tên tòa nhà -->
                        <div class="form-group">
                            <label for="name" class="col-sm-1 control-label">Tên tòa nhà</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="name" value="${building.name}"
                                       name="name"/>
                            </div>
                        </div>

                        <!-- Quận -->
                        <div class="form-group">
                            <label class="col-sm-1 col-form-label control-label">Quận</label>
                            <div class="col-sm-2">
                                <form:select path="building.district" class="form-control">
                                    <form:option value="" label="---Chọn quận---"/>
                                    <form:options items="${districts}"/>
                                </form:select>
                            </div>
                        </div>


                        <!-- Phường -->
                        <div class="form-group">
                            <label for="ward" class="col-sm-1 control-label">Phường</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="ward" value="${building.ward}" name="ward"/>
                            </div>
                        </div>

                        <!-- Đường -->
                        <div class="form-group">
                            <label for="street" class="col-sm-1 control-label">Đường</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="street" value="${building.street}"
                                       name="street"/>
                            </div>
                        </div>

                        <!-- Kết cấu -->
                        <div class="form-group">
                            <label for="structure" class="col-sm-1 control-label">Kết cấu</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="structure" value="${building.structure}"
                                       name="structure"/>
                            </div>
                        </div>

                        <!-- Số tầng hầm -->
                        <div class="form-group">
                            <label for="numberOfBasement" class="col-sm-1 control-label">Số tầng hầm</label>
                            <div class="col-sm-11">
                                <input type="number" class="form-control" id="numberOfBasement"
                                       value="${building.numberOfBasement}" name="numberOfBasement"/>
                            </div>
                        </div>

                        <!-- Diện tích sàn -->
                        <div class="form-group">
                            <label for="floorArea" class="col-sm-1 control-label">Diện tích sàn</label>
                            <div class="col-sm-11">
                                <input type="number" class="form-control" id="floorArea" value="${building.floorArea}"
                                       name="floorArea"/>
                            </div>
                        </div>

                        <!-- Hướng -->
                        <div class="form-group">
                            <label for="direction" class="col-sm-1 control-label">Hướng</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="direction" value="${building.direction}"
                                       name="direction"/>
                            </div>
                        </div>

                        <!-- Hạng -->
                        <div class="form-group">
                            <label for="level" class="col-sm-1 control-label">Hạng</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="level" value="${building.level}"
                                       name="level"/>
                            </div>
                        </div>

                        <!-- Diện tích thuê -->
                        <div class="form-group">
                            <label for="rentArea" class="col-sm-1 control-label">Diện tích thuê</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="rentArea" value="${building.rentArea}"
                                       name="rentArea"/>
                            </div>
                        </div>

                        <!-- Mô tả diện tích -->
                        <%--                        <div class="form-group">--%>
                        <%--                            <label for="areaDescription" class="col-sm-1 control-label">Mô tả diện tích</label>--%>
                        <%--                            <div class="col-sm-11">--%>
                        <%--                                <input type="text" class="form-control" id="areaDescription"--%>
                        <%--                                       placeholder="areaDescription" name="areaDescription"/>--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>

                        <!-- Giá thuê -->
                        <div class="form-group">
                            <label for="rentPrice" class="col-sm-1 control-label">Giá thuê</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="rentPrice" value="${building.rentPrice}"
                                       name="rentPrice"/>
                            </div>
                        </div>

                        <!-- Mô tả giá -->
                        <div class="form-group">
                            <label for="rentPriceDescription" class="col-sm-1 control-label">Mô tả giá</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="rentPriceDescription"
                                       value="${building.rentPriceDescription}"
                                       name="rentPriceDescription"/>
                            </div>
                        </div>

                        <!-- Phí dịch vụ-->
                        <div class="form-group">
                            <label for="serviceFee" class="col-sm-1 control-label">Phí dịch vụ</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="serviceFee" value="${building.serviceFee}"
                                       name="serviceFee"/>
                            </div>
                        </div>

                        <!-- Phí ô tô -->
                        <div class="form-group">
                            <label for="carFee" class="col-sm-1 control-label">Phí ô tô</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="carFee" value="${building.carFee}"
                                       name="carFee"/>
                            </div>
                        </div>

                        <!-- Phí mô tô -->
                        <div class="form-group">
                            <label for="motoFee" class="col-sm-1 control-label">Phí mô tô</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="motoFee" value="${building.motoFee}"
                                       name="motoFee"/>
                            </div>
                        </div>

                        <!-- Phí ngoài giờ -->
                        <div class="form-group">
                            <label for="overtimeFee" class="col-sm-1 control-label">Phí ngoài giờ</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="overtimeFee" value="${building.overtimeFee}"
                                       name="overtimeFee"/>
                            </div>
                        </div>

                        <!-- Tiền điện -->
                        <div class="form-group">
                            <label for="electricityFee" class="col-sm-1 control-label">Tiền điện</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="electricityFee"
                                       value="${building.electricityFee}" name="electricityFee"/>
                            </div>
                        </div>

                        <!-- Đặt cọc -->
                        <div class="form-group">
                            <label for="payment" class="col-sm-1 control-label">Đặt cọc</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="payment" value="${building.payment}"
                                       name="payment"/>
                            </div>
                        </div>

                        <!-- Thanh toán -->
                        <div class="form-group">
                            <label for="deposit" class="col-sm-1 control-label">Thanh toán</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="deposit" value="${building.deposit}"
                                       name="deposit"/>
                            </div>
                        </div>

                        <!-- Thời hạn thuê -->
                        <div class="form-group">
                            <label for="rentTime" class="col-sm-1 control-label">Thời hạn thuê</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="rentTime" value="${building.rentTime}"
                                       name="rentTime"/>
                            </div>
                        </div>

                        <!-- Thời gian trang trí -->
                        <div class="form-group">
                            <label for="decorationTime" class="col-sm-1 control-label">Thời gian trang trí</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="decorationTime"
                                       value="${building.decorationTime}"
                                       name="decorationTime"/>
                            </div>
                        </div>

                        <!-- Tên quản lý -->
                        <div class="form-group">
                            <label for="managerName" class="col-sm-1 control-label">Tên quản lý</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="managerName" value="${building.managerName}"
                                       name="managerName"/>
                            </div>
                        </div>

                        <!-- Số điện thoại quản lý -->
                        <div class="form-group">
                            <label for="phoneNumber" class="col-sm-1 control-label">Số điện thoại quản lý</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="phoneNumber" value="${building.phoneNumber}"
                                       name="phoneNumber"/>
                            </div>
                        </div>

                        <!-- Phí môi giới -->
                        <div class="form-group">
                            <label for="bokerageFee" class="col-sm-1 control-label">Phí môi giới</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="bokerageFee" value="${building.bokerageFee}"
                                       name="bokerageFee"/>
                            </div>
                        </div>

                        <!-- types -->
                        <div class="form-group">
                            <label class="col-sm-1 control-label">Loại tòa nhà</label>
                            <div class="col-sm-11">
                                <c:forEach var="type" items="${types}">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="${type.key}" name="types"
                                               <c:if test="${building.type.contains(type.key)}">checked</c:if>
                                        />${type.value}</label>
                                </c:forEach>
                            </div>
                        </div>

                        <!-- ghi chú  JSTL-->
                        <div class="form-group">
                            <label for="note" class="col-sm-1 control-label">Ghi chú</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="note" value="${building.note}" name="note"/>
                            </div>
                        </div>

                        <!-- link sản phẩm -->
                        <div class="form-group">
                            <label for="linkOfBuilding" class="col-sm-1 control-label">Link sản phẩm</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="linkOfBuilding"
                                       value="${building.linkOfBuilding}"
                                       name="linkOfBuilding"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="linkOfBuilding" class="col-sm-1 control-label">Hình đại diện</label>
                            <div class="col-sm-3" style="margin-top:7px">
                                <input type="file" id="uploadImage"/>
                            </div>
                            <div class="col-sm-9" style="margin-top: 10px; margin-left: 140px">
                                <c:if test="${not empty building.image}">
                                    <c:set var="imagePath" value="/repository${building.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px"
                                         style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty building.image}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>

                    </form>
                    <!-- button thêm/hủy -->
                    <div style="text-align: center">
                        <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm</button>
                        <button type="button" class="btn btn-primary" id="btnUpdateBuilding">Cập nhật</button>
                        <button type="button" class="btn btn-secondary" id="btnBack">Trở về</button>
                    </div>
                    <script>
                        // lấy giá trị params
                        function gup(name, url) {
                            if (!url) url = location.href;
                            name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
                            var regexS = "[\\?&]" + name + "=([^&#]*)";
                            var regex = new RegExp(regexS);
                            var results = regex.exec(url);
                            return results == null ? null : results[1];
                        }

                        // nếu param building_id != null thì sẽ hiển thị nút update
                        if (gup("building_id", location.href)) { // nếu đang ở page update
                            $("#btnAddBuilding").hide();
                            $("#btnUpdateBuilding").show();
                        } else {  // nếu đang ở page add .
                            $("#btnAddBuilding").show();
                            $("#btnUpdateBuilding").hide();
                        }
                    </script>
                </div>
            </div>
        </div>
        <!-- /.page-content -->
    </div>
</div>
<!-- /.main-content -->

<!-- basic scripts -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<script>
    $("#btnBack").click(function () {
        window.location.href = "/admin/building-list";
    });

    $("#btnAddBuilding").click(function () {
        let data = getData();
        saveBuilding(data);
    });

    $("#btnUpdateBuilding").click(function () {
        let data = getData();
        updateBuilding(data);
    });

    function getData() {
        // get "name","value" properties of html , id=formEdit
        var formData = $("#formEdit").serializeArray();
        var data = {}; // js obj - js dinationaryz
        var buildingTypes = [];
        $.each(formData, function (index, element) {

            // 'element' [ "name","value"]
            if (element.name == "types") {
                buildingTypes.push(element.value);
            }
            if ('' !== element.value && null != element.value) {
                data['' + element.name + ''] = element.value;
            }
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        data["types"] = buildingTypes;

        return data;
    }


    function saveBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${apiBuilding}",
            data: JSON.stringify(data), // parse js object to json type.
            dataType: "json", // server respone JSON.
            contentType: "application/json", //client request to server JSON type
            success: function (response) {
                console.log("success");
                alert("Thêm tòa nhà thành công!");
                window.location.href = "/admin/building-list";
            },
            error: function (response) {
                console.log("failed");
                console.log(respone);
            },
        });
    }

    function updateBuilding(data) {
        $.ajax({
            type: "PUT",
            url: "${apiBuilding}", // add the building ID as a parameter in the URL
            data: JSON.stringify(data), // parse js object to json type.
            dataType: "json", // server respone JSON.
            contentType: "application/json", //client request to server JSON type
            success: function (response) {
                console.log("success");
                alert("Cập nhật tòa nhà thành công!");
                window.location.href = "/admin/building-list";
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            },
        });
    }

    var imageBase64 = '';
    var imageName = '';
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function (e) {
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

</script>
</body>
</html>
