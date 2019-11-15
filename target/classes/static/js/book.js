var page = 1;
function getBookList(page){
    $.ajax({
        type:"POST",
        url:"getBookList",
        data:{
            "page":page
        },
        dataType:"json",
        success:function (res) {
            $(".local_page").html(page);
            if(res.length===0){
                alert("已经到达最后一页了！");
                return;
            }
            var ul = $(".product_ul");
            ul.empty();
            for(var i in res){
                ul.append('<li class="line"><a href="./bookdetails/'+res[i].bookid+'" target="_blank"><img src='+res[i].bookurl+'></a>'+
                    '<p class="name">'+'<a href="./bookdetails/'+res[i].bookid+'" target="_blank">'+ res[i].bookname +'</a></p><p class="author">'+
                    '<span class="author_t"></span>'+ res[i].author+'</p><p class="price"><span class="rob"><span class="sign">¥</span>'+
                    '<span class="num">'+res[i].price+'</span></span></p></li>');
            }
        }
    });
}
$(document).ready(function () {
    //请求第一页数据
    getBookList(page);
    //绑定分页事件
    $(".last_page").on('click',function () {
        if(page<=1){
            alert("当前已是第一页");
            return;
        }
        getBookList(--page);
    });
    $(".next_page").on('click',function () {
        getBookList(++page);
    });
})