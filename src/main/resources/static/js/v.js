//Vue模板组件，mydiv是组件名称
Vue.component('mydiv',{
    template:`
        <div>
            <ul>
                <li v-for="name in arr" @click="show(name)">{{name}}</li>
            </ul>
        </div>
    `,
    data:function () {
        return {
            arr:["孙悟空","猪八戒","沙师弟"]
        }
    },
    methods:{
        show:function (name) {
            alert(name);
        }
    }
})
let v = new Vue({
    el:"mydiv"
})