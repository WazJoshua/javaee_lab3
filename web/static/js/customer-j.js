var totalRecord, currentPage;
//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
$(function () {
    //去首页
    to_page(1);
});

function to_page(pn) {
    $.ajax({
        url: "/Lab3Demo/getAllCustomer",
        data: "pn="+pn,
        type: "POST",
        success: function (result) {
            //console.log(result);
            //1、解析并显示员工数据
            build_emps_table(result);
            //2、解析并显示分页信息
            build_page_info(result);
            //3、解析显示分页条数据
            build_page_nav(result);
        }
    });
}

function build_emps_table(result) {
    //清空table表格
    $("#tableBody").empty();
    var customers = result.pageInfo.list;
    $.each(customers, function (index, customer) {

        strTbl = `
                    <tr>
                        <th scope="row" id="customerid">` + customer.custId + `</th>
                        <th id="customername">` + customer.custName + `</th>
                        <th id="customersource">` + customer.custSource + `</th>
                        <th id="customerindustry">` + customer.custIndustry + `</th>
                        <th id="customerlevel">` + customer.custLevel + `</th>
                        <th id="customerphone">` + customer.custPhone + `</th>
                        <th id="customermobile">` + customer.custMobile + `</th>
                        <th>
                            <button class="btn btn-primary btn-xs"><span><svg width="1em" height="1em"
                                                                  viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor"
                                                                  xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
                                                  d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                                        </svg></span> 编辑</button>
                            <button class="btn btn-danger btn-xs"><span><svg width="1em" height="1em"
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
        /**
         <button class="">
         <span class="" aria-hidden="true"></span>
         编辑
         </button>
         */
        /*
         /* var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
                     var empIdTd = $("<td></td>").append(item.empId);
                     var empNameTd = $("<td></td>").append(item.empName);
                     var genderTd = $("<td></td>").append(item.gender=='M'?"男":"女");
                     var emailTd = $("<td></td>").append(item.email);
                     var deptNameTd = $("<td></td>").append(item.department.deptName);
        var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                        .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
                    //为编辑按钮添加一个自定义的属性，来表示当前员工id
                    editBtn.attr("edit-id", item.empId);
                    var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                        .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
                    //为删除按钮添加一个自定义的属性来表示当前删除的员工id
                    delBtn.attr("del-id", item.empId);
                    var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);*/
        //var delBtn =
        //append方法执行完成以后还是返回原来的元素
        /*$("<tr></tr>").append(checkBoxTd)
            .append(empIdTd)
            .append(empNameTd)
            .append(genderTd)
            .append(emailTd)
            .append(deptNameTd)
            .append(btnTd)
            .appendTo("#emps_table tbody");*/
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
