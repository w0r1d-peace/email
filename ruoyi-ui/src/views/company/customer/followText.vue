<template>
  <div>
    <!-- 写跟进时间 -->
    <el-card class="mb-20" shadow="hover">
      <div class="mb-20 flex-middle">
        <i class="vertical-line mr-5"></i>
        <span class="bold">写跟进时间</span>
      </div>
      <el-radio-group v-model="followTextRadio" @change="editSettings">
        <el-radio :label="1">提交跟进记录时默认提交时间，不允许修改</el-radio>
        <el-radio :label="2">提交跟进记录时默认提交时间，可手动修改为历史时间</el-radio>
      </el-radio-group>
    </el-card>
    <!-- 写跟进模板 -->
    <el-card class="mb-20" shadow="hover">
      <div class="space-between">
        <div>
          <div class="mb-6 flex-middle">
            <i class="vertical-line mr-5"></i>
            <span class="bold">写跟进模板</span>
          </div>
          <div class="fs-14">
            可设置公司统一的模板，业务员在写跟进时可根据模板填写对应内容。
          </div>
        </div>
        <div class="flex-middle">
          <el-button class="kind-button" size="medium" round @click="followTextDialog=true">添加模板</el-button>
        </div>

      </div>
      <div class="mt-16 flex-start flex-wrap gap-16" v-loading="templateLoading">
        <template v-if="followTextList.length">
          <div class="follow-text-template radius-5" style="width:32.333%" v-for="item in followTextList"
               :key="item.id">
            <div class="wrap px-16 py-14 radius-4 flex-column">
              <div class="card-head flex-middle space-between">
                <div class="fs-14 bold">模板名称：{{ item.name }}</div>
                <el-row :gutter="2">
                  <el-button type="text" size="mini" @click="onEdit('followText',item)">编辑</el-button>
                  <DelPopover :id="item.id" @onDelete="confirmDelTemplate"/>
                </el-row>
              </div>
              <div class="card-main fs-14 gray-text">
                {{ item.content }}
              </div>
            </div>
          </div>
        </template>
        <el-empty style="width: 100%" v-else :image-size="100"></el-empty>
      </div>
    </el-card>
    <!--  跟进模板表单  -->
    <el-dialog title="添加快捷模板" width="500px" style="margin-top: 25vh" :visible.sync="followTextDialog"
               destroy-on-close @close="onCancel('followText')">
      <el-form :model="followTextForm" ref="followTextRef" :rules="followTextRules">
        <el-form-item label="模板名称" prop="name">
          <el-input v-model="followTextForm.name" autocomplete="off" placeholder="请输入模板名称"></el-input>
        </el-form-item>
        <el-form-item label="模板内容" prop="content">
          <el-input type="textarea" v-model="followTextForm.content" :rows="4" resize="none"
                    autocomplete="off" placeholder="请输入模板内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnTemplateLoading" @click="onCancel('followText')">取 消</el-button>
        <el-button type="primary" round :loading="btnTemplateLoading" @click="onConfirm('followText','followTextRef')">确
          定
        </el-button>
      </div>
    </el-dialog>

    <!-- 写跟进快捷文本 -->
    <el-card class="mb-20" shadow="hover">
      <div class="space-between">
        <div>
          <div class="mb-6 flex-middle">
            <i class="vertical-line mr-5"></i>
            <span class="bold">写跟进快捷文本</span>
          </div>
          <div class="fs-14">
            可设置公司统一的常用文本，业务员在写跟进时可快速选择。
          </div>
        </div>
        <div class="flex-middle">
          <el-button class="kind-button" size="medium" round @click="fastTextDialog=true">添加文本分组</el-button>
        </div>

      </div>
      <div class="mt-16 flex-wrap gap-16" v-loading="quickTextLoading">
        <template v-if="fastTextList.length">
          <div class="follow-text-template radius-5 mb-20" v-for="item in fastTextList" :key="item.id">
            <div class="px-16 py-14 radius-4 flex-column">
              <div class="card-head flex-middle space-between">
                <div class="fs-14 bold">模板名称：{{ item.name }}</div>
                <el-row :gutter="2">
                  <el-button type="text" size="mini" @click="onEdit('fastText',item)">编辑</el-button>
                  <DelPopover :id="item.id" @onDelete="confirmDelText"/>
                </el-row>
              </div>
              <div class="flex-middle gap-8">
                <el-tag effect="plain" v-for="(tag,idx) in item.label" :key="idx">{{ tag }}</el-tag>
              </div>
            </div>
          </div>
        </template>

        <el-empty style="width: 100%" v-else :image-size="100"></el-empty>
      </div>
    </el-card>


    <!--  快捷文本表单  -->
    <el-dialog title="添加快捷文本" width="500px" style="margin-top: 25vh" :visible.sync="fastTextDialog"
               ref="fastTextRef" destroy-on-close @close="onCancel('fastText')">
      <el-form :model="fastTextForm" ref="fastTextRef" :rules="fastTextRules">
        <el-form-item label="文本分组名称" prop="name">
          <el-input v-model="fastTextForm.name" autocomplete="off" placeholder="请输入文本分组名称"></el-input>
        </el-form-item>
        <el-form-item label="文本标签" prop="label">
          <el-select
              style="width: 100%"
              multiple
              clearable
              filterable
              allow-create
              default-first-option
              ref="selectRef"
              autocomplete="off"
              class="customer-select"
              popper-class="hide-option"
              v-model="fastTextForm.label"
              placeholder="请输入文本标签"
              :popper-append-to-body="false"
          >
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel('fastText')" :loading="btnQuickTextLoading">取 消</el-button>
        <el-button type="primary" round :loading="btnQuickTextLoading" @click="onConfirm('fastText','fastTextRef')">确
          定
        </el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import DelPopover from "./DelPopover.vue";
import {mapState} from "vuex";
import {
  followTextQuickAdd,
  followTextQuickDelete, followTextQuickEdit,
  followTextQuickList, followTextTemplateAdd,
  followTextTemplateDelete, followTextTemplateEdit,
  followTextTemplateList
} from "@/api/company/followText";
import {deepClone} from "@/utils";

const iniFollowTextForm = {
  name: '',
  content: ''
}

const iniFastTextForm = {
  name: '',
  label: ''
}

export default {
  components: {
    DelPopover
  },
  data() {
    return {
      // 写跟进时间
      followTextRadio: 1,
      followTextList: [],
      followTextDialog: false,
      followTextForm: {...deepClone(iniFollowTextForm)},
      followTextRules: {
        name: [
          {required: true, message: '请输入文本分组名称', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入文本标签', trigger: 'blur'},
        ],
      },
      // 快捷文本
      fastTextList: [],
      fastTextDialog: false,
      fastTextForm: {...deepClone(iniFastTextForm)},
      fastTextRules: {
        name: [
          {required: true, message: '请输入文本分组名称', trigger: 'blur'},
        ],
        label: [
          {required: true, message: '请添加文本标签', trigger: 'change'},
        ],
      },
      templateLoading: false,
      btnTemplateLoading: false,
      quickTextLoading: false,
      btnQuickTextLoading: false
    }
  },
  mounted() {
    this.$watch('settings', (newVal) => {
      const {followupTime} = newVal
      this.followTextRadio = followupTime
    }, {immediate: true})
    this.getTemplateList()
    this.getQuickTextList()
  },
  computed: {
    ...mapState({
      settings: state => state.company.settings
    }),
  },
  methods: {
    async getTemplateList() {
      this.templateLoading = true
      try {
        const res = await followTextTemplateList().finally(() => {
          this.templateLoading = false
        })
        if (res.code === 200) {
          this.followTextList = res.data
        }
      } catch {
      }
    },
    async getQuickTextList() {
      this.quickTextLoading = true
      try {
        const res = await followTextQuickList().finally(() => {
          this.quickTextLoading = false
        })
        if (res.code === 200) {
          this.fastTextList = res.data.map(val => {
            val.label = val.label?.split(';') || ''
            return val
          })
        }
      } catch {
      }
    },
    // 模板添加和修改
    templateCommOperate(row) {
      return new Promise(async resolve => {
        this.btnTemplateLoading = true
        try {
          if (!row.id) {
            const res = await followTextTemplateAdd({...row}).finally(() => {
              this.btnTemplateLoading = false
            })
            if (res.code === 200) {
              this.$message({
                type: 'success',
                message: '添加成功'
              })
              resolve(true)
            }
          } else {
            const res = await followTextTemplateEdit({...row})
            if (res.code === 200) {
              this.$message({
                type: 'success',
                message: '修改成功'
              })
              resolve(true)
            }
          }
        } catch {
          this.btnTemplateLoading = false
          resolve(false)
        }
      })

    },
    // 快捷文本添加和修改
    quickCommOperate(row) {
      return new Promise(async resolve => {
        this.btnQuickTextLoading = true
        try {
          const data = {...row, label: row?.label.join(';')}
          if (!row.id) {
            const res = await followTextQuickAdd(data).finally(() => {
              this.btnQuickTextLoading = false
            })
            if (res.code === 200) {
              this.$message({
                type: 'success',
                message: '添加成功'
              })
              resolve(true)
            }
          } else {
            const res = await followTextQuickEdit(data)
            if (res.code === 200) {
              this.$message({
                type: 'success',
                message: '修改成功'
              })
              resolve(true)
            }
          }
        } catch {
          this.btnQuickTextLoading = false
          resolve(false)
        }
      })
    },
    // 删除文本模板
    async confirmDelTemplate(id) {
      try {
        const res = await followTextTemplateDelete({id})
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          await this.getTemplateList()
        }
      } catch {
      }
    },
    // 删除快捷文本
    async confirmDelText(id) {
      try {
        const res = await followTextQuickDelete({id})
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          await this.getQuickTextList()
        }
      } catch {
      }
    },
    // 编辑
    onEdit(type, item) {
      if (type === 'followText') {
        this.followTextForm = {...item}
        this.followTextDialog = true
      } else if (type === 'fastText') {
        this.fastTextForm = {...item}
        this.fastTextDialog = true
      }
    },
    // 确认
    onConfirm(type, formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (type === 'followText') {
            this.templateCommOperate(this.followTextForm).then(res => {
              if (res) {
                this.getTemplateList()
                this.onCancel('followText')
              }
            })
          } else if (type === 'fastText') {
            this.quickCommOperate(this.fastTextForm).then(res => {
              if (res) {
                this.getQuickTextList()
                this.onCancel('fastText')
              }
            })
          }
        } else {
          return false;
        }
      });
    },
    // 取消
    onCancel(type) {
      if (type === 'followText') {
        this.followTextForm = {...deepClone(iniFollowTextForm)}
        this.followTextDialog = false
        this.btnTemplateLoading = false
      } else if (type === 'fastText') {
        this.fastTextForm = {...deepClone(iniFastTextForm)}
        this.fastTextDialog = false
        this.btnQuickTextLoading = false
      }
    },
    editSettings() {
      this.$store.dispatch('company/EditCompanyCustomerSettings', {
        ...this.settings,
        followupTime: this.followTextRadio
      }).then(res => {
        if (res) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        }
      })
    },

  }
}
</script>

<style lang="scss" scoped>
.vertical-line {
  display: inline-block;
  width: 4px;
  height: 14px;
  border-radius: 4px;
  background-color: #017aff;
}

.kind-button {
  color: #0064ff;
  border-color: #0064ff;
}

.follow-text-template {
  border: 1px solid #dfe1e5;
  background-color: #f7f8fb;

  .wrap {
    height: 148px;
  }
}

.customer-select {
  ::v-deep .el-icon-arrow-up,
  ::v-deep .hide-option {
    display: none;
  }
}


</style>
