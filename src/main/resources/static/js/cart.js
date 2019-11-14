$.ajax({
    type:"GET",
    url:"getCart",
    dataType:"json",
    success:function(res){
        $(".cart-list").empty();
        for(var i in res){
            $(".cart-list").append('<tr class="cart-item">' +
                '<td>'+res[i].bookid+'</td>' +
                '<td>'+res[i].bookname+'</td>' +
                '<td>'+res[i].bookdescription+'</td>' +
                '<td>'+res[i].author+'</td>' +
                '<td>'+res[i].publisher+'</td>' +
                '<td>'+res[i].price+'</td>' +
                '</tr>');
        }
    }
})