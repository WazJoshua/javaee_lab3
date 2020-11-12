var totalRecord, currentPage;
//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
$(function () {
    //去首页
    to_page(1);
    getops("customerFrom");     //页面加载时将下拉项渲染进页面
    getops("custIndustry");
    getops("custLevel");

});

/**************************************************分页操作的js*********************************************************/


function build_cust_table(result) {         //渲染用户信息进入表格
    //清空table表格
    $("#tableBody").empty();
    var customers = result.pageInfo.list;
    $.each(customers, function (index, customer) {

        strTbl = `
                    <tr>
                        <th scope="row" id="customerid"> ${customer.custId}</th>
                        <th id="customername">${customer.custName}</th>
                        <th id="customersource">${customer.custSource} </th>
                        <th id="customerindustry">${customer.custIndustry}</th>
                        <th id="customerlevel">${customer.custLevel}</th>
                        <th id="customerphone">${customer.custPhone}</th>
                        <th id="customermobile">${customer.custMobile}</th>
                        <th>
                            <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#customerEditDialog" onclick= "editCustomer(` + customer.custId + `)"><span><svg width="1em" height="1em"
                                                                  viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor"
                                                                  xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
                                                  d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                                        </svg></span> 编辑</button>
                            <button class="btn btn-danger btn-xs" onclick="deleteCustomer(` + customer.custId + `)"><span><svg width="1em" height="1em"
                                                                 viewBox="0 0 16 16" class="bi bi-x-circle" fill="currentColor"
                                                                 xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
                                                  d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                            <path fill-rule="evenodd"
                                                  d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                                        </svg></span> 删除</button>
                        </th>
                    </tr>`;
        $("#tableBody").append(strTbl);

    });
}

//解析显示分页信息
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.pageInfo.pageNum + "页,总" +
        result.pageInfo.pages + "页,总" +
        result.pageInfo.total + "条记录");
    totalRecord = result.pageInfo.total;
    currentPage = result.pageInfo.pageNum;
}

//解析显示分页条，点击分页要能去下一页....
function build_page_nav(result) {
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    //构建元素
    var firstPageLi = $("<li class='page-item'></li>").append($("<a class='page-link'></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li  class='page-item'></li>").append($("<a class='page-link'></a>").append("&laquo;"));
    if (result.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            to_page(1);
        });
        prePageLi.click(function () {
            to_page(result.pageInfo.pageNum - 1);
        });
    }


    var nextPageLi = $("<li class='page-item'></li>").append($("<a class='page-link'></a>").append("&raquo;"));
    var lastPageLi = $("<li class='page-item>'</li>").append($("<a class='page-link'></a>").append("末页").attr("href", "#"));
    if (result.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.pageInfo.pages);
        });
    }

    0
    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.pageInfo.navigatepageNums, function (index, item) {

        var numLi = $("<li class='page-item>'</li>").append($("<a class='page-link'></a>").append(item));
        if (result.pageInfo.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    //把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

/*//清空表单样式及内容
function reset_form(ele) {
    $(ele)[0].reset();
    //清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}*/
/**********************************************************************************************************************/

//获得下拉项
function getops(loc) {
    $.ajax({
        url: "/Lab3Demo/get" + loc,
        type: "POST",
        success: function (success) {
            intoCusSource(loc, success.sources);
        }
    })
}

//渲染进页面
function intoCusSource(location, sources) {

    for (let i = 0; i < sources.length; i++) {
        /*opStr = ;*/
        $("#" + location).append("<option value='" + sources[i].dictId + "'>" + sources[i].dictItemName + "</option>");
        $("#edit_" + location).append("<option value='edit_" + sources[i].dictId + "'>" + sources[i].dictItemName + "</option>");
        $("#new_" + location).append("<option value='new_" + sources[i].dictId + "'>" + sources[i].dictItemName + "</option>");
    }
}


/*****************************************************查询操作的js*******************************************************/

function to_page(pn) {
    let cusName = $("#customerName").val();
    let cusSour = $("#customerFrom").val().replace(/[^0-9]/ig, "");
    let cusIndus = $("#custIndustry").val().replace(/[^0-9]/ig, "");
    let cusLevel = $("#custLevel").val().replace(/[^0-9]/ig, "");
    var s = JSON.stringify({
        "pn": pn,
        "customer": {
            "custName": cusName,
            "custSource": cusSour,
            "custIndustry": cusIndus,
            "custLevel": cusLevel
        }
    });
    //console.log(s);
    $.ajax({
        url: "/Lab3Demo/getCustomerBySel",
        contentType: "application/json",
        data: s,
        type: "POST",
        success: function (result) {
            // console.log(result.pageInfo);
            //1、解析并显示用户数据
            build_cust_table(result);
            //2、解析并显示分页信息
            build_page_info(result);
            //3、解析显示分页条数据
            build_page_nav(result);
        }
    });
}


$("#selectCus").click(
    function () {
        to_page(1);
    }
)

/******************************************************新建客户*********************************************************/

// 创建客户
function createCustomer() {

    var newCusCreateId = document.cookie.replace(/(?:(?:^|.*;\s*)userid\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    var newCusName = $("#new_customerName").val();
    var newCusFrom = $("#new_customerFrom").val().replace(/[^0-9]/ig, "");
    var newCusIndustry = $("#new_custIndustry").val().replace(/[^0-9]/ig, "");
    var newCusLevel = $("#new_custLevel").val().replace(/[^0-9]/ig, "");
    var newCusLinkMan = $("#new_linkMan").val();
    var newPhone = $("#new_phone").val();
    var newMobile = $("#new_mobile").val();
    var newZipcode = $("#new_zipcode").val();
    var newAddress = $("#new_address").val();
    if (newCusName == "" || newCusFrom == "" || newCusIndustry == "" || newCusLevel == "" || newCusLinkMan == ""
        || newPhone == "" || newMobile == "" || newZipcode == "" || newAddress == "") {
        alert("请填写完整信息!");
        return;
    }
    var cusInfo = JSON.stringify({
        "custCreateId": newCusCreateId,
        "custName": newCusName,
        "custSource": newCusFrom,
        "custIndustry": newCusIndustry,
        "custLevel": newCusLevel,
        "custLinkman": newCusLinkMan,
        "custPhone": newPhone,
        "custMobile": newMobile,
        "custZipcode": newZipcode,
        "custAddress": newAddress
    });
    //console.log(cusInfo)
    $.ajax({
        url: "/Lab3Demo/createNewCus",
        type: "POST",
        contentType: "application/json",
        data: cusInfo,
        success: function (result) {
            if (result.code == 200) {
                alert("新建成功!");
                window.location.reload();
            } else {
                alert("创建失败!")
                window.location.reload();
            }
        }
    })
}

function clearNewCustomer() {
    $("#new_customerName").val("");
    $("#new_customerFrom").val("")
    $("#new_custIndustry").val("")
    $("#new_custLevel").val("")
    $("#new_linkMan").val("");
    $("#new_phone").val("");
    $("#new_mobile").val("");
    $("#new_zipcode").val("");
    $("#new_address").val("");
}

function clearEditCustomer() {
    $("#edit_customerName").val("");
    $("#edit_customerFrom").val("");
    $("#edit_custIndustry").val("");
    $("#edit_custLevel").val("");
    $("#edit_linkMan").val("");
    $("#edit_phone").val("");
    $("#edit_mobile").val("");
    $("#edit_zipcode").val("");
    $("#edit_address").val("");
}

// 删除客户
function deleteCustomer(id) {
    /*if (confirm('确实要删除该客户吗?')) {
        $.post("<%=basePath%>customer/delete.action", {"id": id},
            function (data) {
                if (data == "OK") {
                    alert("客户删除成功！");
                    window.location.reload();
                } else {
                    alert("删除客户失败！");
                    window.location.reload();
                }
            });
    }*/
    if (confirm('确实要删除该客户吗?')) {
        $.ajax({
            url: "/Lab3Demo/deleteCusById",
            type: "POST",
            data: "custId=" + id,
            success: function (result) {
                if (result.code == 200) {
                    alert("删除成功!");
                    window.location.reload();
                } else {
                    alert("删除失败!");
                    window.location.reload();
                }
            }
        })
    }

}

var username = document.cookie.replace(/(?:(?:^|.*;\s*)username\s*\=\s*([^;]*).*$)|^.*$/, '$1');
$("#user-name-label-loc").html(username);