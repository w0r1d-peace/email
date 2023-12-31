import Vue from 'vue'

import Cookies from 'js-cookie'

import Element from 'element-ui'
import './assets/styles/element-variables.scss'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import '@riophae/vue-treeselect/dist/vue-treeselect.min.css' // ruoyi tree css
import App from './App'
import store from './store'
import router from './router'
import directive from './directive' // directive
import plugins from './plugins' // plugins
import {download} from '@/utils/request'

import './assets/icons' // icon
import './permission' // permission control
import {getDicts} from "@/api/system/dict/data";
import {getConfigKey} from "@/api/system/config";
import {parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, handleTree} from "@/utils/ruoyi";
// 复制
import VueClipboard from 'vue-clipboard2'
// 分页组件
import Pagination from "@/components/Pagination";
// 自定义表格工具组件
import RightToolbar from "@/components/RightToolbar"
// 富文本组件
import Editor from "@/components/Editor"
// 文件上传组件
import FileUpload from "@/components/FileUpload"
// 图片上传组件
import ImageUpload from "@/components/ImageUpload"
// 图片预览组件
import ImagePreview from "@/components/ImagePreview"
// 字典标签组件
import DictTag from '@/components/DictTag'
// 头部标签组件
import VueMeta from 'vue-meta'
// 字典数据组件
import DictData from '@/components/DictData'
// JSON表单
import formCreate from '@form-create/element-ui'
// 图片上传组件
import UploadPicture from '@/components/UploadPicture'
// 拖拽状态栏
import splitPane from 'vue-splitpane'
// select tag
import TreeSelectNext from '@/components/TreeSelectNext'
// el-select-tree
import ElSelectTree from "el-select-tree";
// 国家筛选
import SelectCountry from '@/components/SelectCountry'

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

// 全局组件挂载
Vue.component('DictTag', DictTag)
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
Vue.component('Editor', Editor)
Vue.component('FileUpload', FileUpload)
Vue.component('ImageUpload', ImageUpload)
Vue.component('ImagePreview', ImagePreview)

Vue.component('upload-picture', UploadPicture)
Vue.component('split-pane', splitPane);
Vue.component('tree-select-next', TreeSelectNext)
Vue.component('el-select-tree', ElSelectTree);
Vue.component('select-country', SelectCountry)


Vue.use(directive)
Vue.use(plugins)
Vue.use(VueMeta)
DictData.install()
// form-create
Vue.use(formCreate)
// copy text
Vue.use(VueClipboard)


/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
    size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})
