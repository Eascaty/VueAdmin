import Vue from 'vue'

Vue.mixin({
    methods: {
        hasAuth(perm) {
            var authority = this.$store.state.menus.permList
            console.log(authority)
            //true就有权限,false就没有权限
            return authority.indexOf(perm) > -1
        }
    }
})