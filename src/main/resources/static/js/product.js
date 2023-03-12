let product_v = new Vue({
    el:".grid",
    data:{
        arr:[]
    },
    created:function () {
        // //local:8080/index.html?cid=1
        if(location.search.indexOf("cid")!=-1){//包含cid，查询某个分类的作品
            let cid =  location.search.split("=")[1];
            axios.get("/product/selectByCid?cid="+cid).then(function (response) {
                product_v.arr = response.data;
            })
        }else if(location.search.indexOf("wd")!=-1){//包含wd，根据搜索关键字查询相关作品
            let wd =  location.search.split("=")[1];
            axios.get("/product/selectByWd?wd="+wd).then(function (response) {
                product_v.arr = response.data;
            })
        } else{ //请求所有作品信息    local:8080/index.html
            axios.get("/product/select").then(function (response) {
                product_v.arr = response.data;
            })
        }
    },
    updated:function () {
        //此方法当Vue data里面的变量值改变时运行
        //通过瀑布流插件控制页面显示
        //初始化瀑布流插件
        $(".grid").masonry({
            itemSelector:".grid-item",  //告诉瀑布流插件 如何找到元素
            columnWidth:210 //设置每列宽度(200+10间距)
        })
        //图片加载完之后 重新调整一下图片布局  解决层叠问题
        $(".grid").imagesLoaded().progress(function () {
            $(".grid").masonry("layout");//让瀑布流重新计算布局
        })
        //给图片所在div添加鼠标移入移出事件，hover代表移入移出事件
        $(".grid-item").hover(function () {//移入触发
            //$(this):代表移入的div
            //得到移入div里面的子div(代表蓝条div)  fadeIn(500)：淡入，持续500毫秒
            $(this).children("div").fadeIn(500);
        },function () {//移出触发
            $(this).children("div").fadeOut(500);
        })
    }
})