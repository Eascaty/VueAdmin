import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default  {
    state: {

        menuList: [],
       permList: [],

        hasRoutes: sessionStorage.getItem("hasRoute")
    },

    mutations: {
        setMenuList(state, menus){
          state.menuList =menus
        },

        setPermList(state, perms){
           state.permList =perms
        },

        changeRouteStatus( state,hasRoutes ){
                state.hasRoutes = hasRoutes

                sessionStorage.setItem("hasRoute",hasRoutes)
        }

    },

    modules: {}
}



