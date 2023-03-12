Vue.component('myright',{
    template:`
         <div class="col-md-3" id="right_div">
            <!--浏览最多开始-->
            <ul class="list-group">
                <li class="list-group-item list-head">
                    <h4>
                        <i class="fa fa-eye">浏览最多</i>
                    </h4>
                </li>
                <li v-for="p in view_arr" class="list-group-item">
                    <div class="media">
                        <div class="media-left">
                            <a :href="'/detail.html?id='+p.id">
                                <img :src="p.url" class="media-object" style="width:60px;height: 50px">
                            </a>
                        </div>
                        <div class="media-body">
                            <a :href="'/detail.html?id='+p.id">
                                <h4 class="media-heading">{{p.title}}</h4>
                            </a>
                            <p>{{p.intro}}</p>
                        </div>
                    </div>
                </li>
            </ul>
            <!--浏览最多结束-->

            <!--最受欢迎开始-->
            <ul class="list-group">
                <li class="list-group-item list-head">
                    <h4>
                        <i class="fa fa-thumbs-o-up">最受欢迎</i>
                    </h4>
                </li>
                <li v-for="p in like_arr" class="list-group-item">
                    <div class="media">
                        <div class="media-left">
                            <a :href="'/detail.html?id='+p.id">
                                <img :src="p.url" class="media-object" style="width:60px;height: 50px;">
                            </a>
                        </div>
                        <div class="media-body">
                            <a :href="'/detail.html?id='+p.id">
                                <h4 class="media-heading">{{p.title}}</h4>
                            </a>
                            <p>{{p.intro}}</p>
                        </div>
                    </div>
                </li>
            </ul>
            <!--最受欢迎结束-->
        </div>
    `,
    data:function () {
        return{
            view_arr:[],
            like_arr:[]
        }
    },
    created:function () {
        let v = this;
        axios.get("/product/select/view").then(function (response) {
            v.view_arr = response.data;
        })
        axios.get("/product/select/like").then(function (response) {
            v.like_arr = response.data;
        })
    }
})

let right_v = new Vue({
    el:"myright"
})