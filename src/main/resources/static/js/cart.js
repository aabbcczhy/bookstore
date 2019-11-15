function removeFromCart(bookid,success,error) {
    $.ajax({
        type:"POST",
        url:"/removeFromCart",
        data:{
            bookid:bookid
        },
        success:success,
        error:error
    });
}
function calculatePayment(){
    let total = 0;
    $("tbody .item-total").each(function (index, item) {
        total += parseFloat(item.textContent.substr(1,item.textContent.length-1));
    });
    $("#payAmount").text('¥'+decimal(total,2));
}
function calculateItemTotal(num_change){
    let currentBookNum = parseInt(num_change.siblings("input").val());
    let priceText = num_change.parents("tr").find("span.price_n").text();
    let price = parseFloat(priceText.substr(1,priceText.length-1));
    num_change.parents("tr").find("span.item-total").text('¥'+decimal(currentBookNum*price,2));
}
//对多位小数进行四舍五入
function decimal(num,v){
    let vv = Math.pow(10,v);
    return Math.round(num*vv)/vv;
}
function checkIfEmpty(){
    if($(".shopping_list table").children().length===0){
        $("#empty").show();
        $(".cart-container").hide();
        return true;
    }
    return false;
}
$(document).ready(function () {
    //还没选购商品，隐藏购物车
    if(checkIfEmpty()){
        return;
    }
    //计算当前购物车总价
    calculatePayment();
    $(".num_add").on('click',function () {
        let numAdd = $(this);
        let bookid = $(this).parent("span").attr("data");
        $.ajax({
            type: "POST",
            url: "/addToCart/"+bookid,
            data: {
                booknum:1
            },
            success:function(res) {
                numAdd.siblings("input").val(parseInt(numAdd.siblings("input").val())+1);
                calculateItemTotal(numAdd);
                calculatePayment();
            }
        });

    });
    $(".num_del").on('click',function () {
        let numDel = $(this);
        let bookid = $(this).parent("span").attr("data");
        if(parseInt(numDel.siblings("input").val())<=1){
            return;
        }
        $.ajax({
            type: "POST",
            url: "/addToCart/"+bookid,
            data: {
                booknum:-1
            },
            success:function(res) {
                numDel.siblings("input").val(parseInt(numDel.siblings("input").val())-1);
                calculateItemTotal(numDel);
                calculatePayment();
            }
        });
    });
    $(".fn-remove-product").on('click',function () {
        let removeElement = $(this);
        let bookid = removeElement.attr("data");
        removeFromCart(bookid,function () {
                removeElement.parents("tbody").remove();
                calculatePayment();
                checkIfEmpty();
            },
            function () {
                alert("删除失败！");
            });
    });
    $("#checkout_btn").on('click',function () {
        //TODO 结算
        //发ajax清空购物车
        //跳转结算成功页面
    });
});
