$("body").before("<div class=\"header\">\n" +
    "    <div class=\"logo\">图书销售系统</div>\n" +
    "    <div class=\"search\">\n" +
    "            <input type=\"text\" placeholder=\"请输入你想搜索的图书...\" class=\"search-input\" id=\"search-input\" autocomplete=\"off\">\n" +
    "            <input type=\"button\" class=\"search-btn\" onclick=\"javascript:void(0);\">\n" +
    "    </div>\n" +
    "</div>");
$("body").append("<div class=\"footer\">\n" +
    "    Copyright &copy; yangsanhe 2019 , All Rights Reserved.\n" +
    "</div>");