<template>
  <div class="flex-middle">
    <el-button type="text" round>跨页全选</el-button>
    <span class="fs-14 mr-10">已选&nbsp;{{ checkedLen.length }}&nbsp;个客户</span>
    <el-button round v-if="checkedLen.length === 1" @click="writeEmail">写邮件</el-button>
    <!-- <el-button round v-else-if="checkedLen.length > 1" @click="sendSMS">发送群发单显</el-button> -->
<!--    <el-button round>写营销邮件</el-button>-->
    <!--    <el-button round>设置标签</el-button>-->
    <!--    <el-button round>编辑字段</el-button>-->
    <!--    <OperateMenu :ids="ids" class="ml-10">-->
    <!--      <el-button round>更多<i class="el-icon-arrow-down ml-6"></i></el-button>-->
    <!--    </OperateMenu>-->
  </div>
</template>

<script>
import OperateMenu from "./OperateMenu.vue";
import { targetBlank } from '@/utils/tools'

export default {
  props: {
    ids: {
      type: Array,
      default: () => [],
      required: false
    }
  },
  components: {
    OperateMenu
  },
  data() {
    return {
      checkedLen: []
    }
  },
  watch: {
    ids: {
      handler(newVal) {
        this.checkedLen = newVal
      },
      immediate: true
    }
  },
  methods: {
    writeEmail() {
      targetBlank(`/email/index?type=write_email&id=${this.ids[0]}`)
    },
    sendSMS() {
      this.$router.push('/email/index?type=write_email')
    }
  }
}
</script>

<style lang="scss" scoped></style>
