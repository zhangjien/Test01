Vue.component('myheader',{
    template: `
        <header class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/">
                            <img alt="Brand" src="images/logo.png">
                        </a>
                    </div>
                    <!--分类开始-->
                    <ul class="nav navbar-nav">
                        <li><a href="/">首页</a></li>
                        <li v-for="c in arr"><a :href="'/index.html?cid='+c.id">{{c.name}}</a></li>
                        <li>
                            <!--action="javascript:void(0)"：废除按回车键，form表单原有的发起同步请求功能，
                            @keydown.enter="search()"：当按下回车键时，执行search方法-->
                            <form action="javascript:void(0)" @keydown.enter="search()">
                                <input type="text" v-model="wd" placeholder="Search">
                                <button type="button" @click="search()">
                                    <i class="fa fa-search"></i>
                                </button>
                            </form>
                        </li>
                    </ul>
                    <!--分类结束-->
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="fa fa-user-o" href="javascript:void(0)" @click="login()">管理员入口</a></li>
                    </ul>
                </div>
            </nav>
        </header>
    `,
    data:function () {
        return{
            arr:[],
            wd:""
        }
    },
    created:function () {
        //此处的this代表的是Vue对象，让v记住Vue对象
        let v = this;
        //发起异步请求所有分类信息
        axios.get("/category/select").then(function (response) {
            v.arr = response.data;
        })
    },
    methods:{
        login:function () {
            axios.get("/currentUser").then(function (response) {
                location.href=response.data==""?"/login.html":"/admin.html";
            })
        },
        search:function () {
            //点击搜索，还是跳转到首页，但是带着搜索参数wd
            location.href="/index.html?wd="+this.wd;
        }
    }
})
let header_v = new Vue({
    el:"myheader"
})
