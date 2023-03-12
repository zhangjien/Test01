let banner_v = new Vue({
    el:"#myCarousel",
    data:{
        arr:[]
    },
    created:function () {
        //发起请求所有轮播的数据
        axios.get("/banner/select").then(function (response) {
            banner_v.arr = response.data;
        })
    }
})