import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import dict from './modules/dict'
import user from './modules/user'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import company from './modules/company'
import emailSetting from './modules/emailSetting'
import otherSetting from './modules/otherSetting'
import emailList from './modules/emailList'

import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    dict,
    user,
    tagsView,
    permission,
    settings,
    company,
    emailSetting,
    otherSetting,
    emailList
  },
  getters
})

export default store
