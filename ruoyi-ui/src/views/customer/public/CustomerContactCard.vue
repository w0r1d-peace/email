<template>
  <div class="contact-card">
    <div class="fs-16 my-10">联系人{{ formList.length }}</div>
    <div>
      <div class="contact-card-ul" v-for="(item, index) in formList" :key="index">
        <div class="flex-middle space-between">
          <div class="card-title">
            <span>{{ item.nickName || `联系人${index + 1}` }}</span>
            <span v-if="index === 0" class="pl-6">
              <svg-icon icon-class="important" />
            </span>
          </div>
          <div v-if="index !== 0">
            <el-tooltip placement="top" content="设置为主联系人">
              <svg-icon class="pointer" icon-class="important-gray" @click="onSortContactForm(item.contactId)" />
            </el-tooltip>
            <el-tooltip placement="top" content="删除">
              <i class="el-icon-delete pl-10 pointer" @click="onDelContactForm(item.contactId)"></i>
            </el-tooltip>
          </div>
        </div>

        <el-form>
          <el-form-item label="昵称">
            <el-input v-model="item.nickName" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="item.email" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="社交平台" />
          <el-form-item class="special-item" v-for="(platform, idxP) in item.socialPlatform" :key="`platform-${idxP}`">
            <el-row type="flex" :gutter="4">
              <el-col :span="8">
                <el-select v-model="platform.type" placeholder="社交平台">
                  <el-option v-for="(opt, index) in platformOption" :key="index" :label="opt.label" :value="opt.value">
                    <span>
                      <svg-icon :icon-class="opt.svg" />
                      {{ opt.label }}
                    </span>
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="13">
                <el-input v-model="platform.account" placeholder="请输入" />
              </el-col>
              <el-col :span="3" class="flex-middle">
                <el-tooltip placement="top" content="删除">
                  <i v-if="idxP !== 0" class="fs-20 pl-6 el-icon-remove-outline pointer"
                    @click="onReduce(item.contactId, platform.id, 'socialPlatform')"></i>
                </el-tooltip>
                <el-tooltip placement="top" content="添加">
                  <i class="fs-20 pl-6 el-icon-circle-plus-outline pointer" @click="onAdd('socialPlatform')"></i>
                </el-tooltip>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="联系电话" />
          <el-form-item class="special-item" v-for="(contact, idxC) in item.phone" :key="`contact-${idxC}`">
            <el-row type="flex" :gutter="4">
              <el-col :span="8">
                <el-select v-model="contact.phone_prefix" placeholder="电话区号" filterable clearable>
                  <el-option v-for="(prefix, index) in phonePrefixList" :key="index" :label="prefix.label"
                    :value="prefix.value"></el-option>
                </el-select>
              </el-col>
              <el-col :span="13">
                <el-input v-model="contact.phone" type="number" placeholder="请输入" />
              </el-col>
              <el-col :span="3" class="flex-middle">
                <el-tooltip placement="top" content="删除">
                  <i v-if="idxC !== 0" class="fs-20 pl-6 el-icon-remove-outline pointer"
                    @click="onReduce(item.contactId, contact.id, 'phone')"></i>
                </el-tooltip>
                <el-tooltip placement="top" content="添加">
                  <i class="fs-20 pl-6 el-icon-circle-plus-outline pointer" @click="onAdd('phone')"></i>
                </el-tooltip>
              </el-col>
            </el-row>
          </el-form-item>

          <el-row v-if="item.show">
            <el-form-item label="职级">
              <el-select v-model="item.rank">
                <el-option v-for="(opt, index) in rankOption" :key="index" :label="opt.label" :value="opt.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="职位">
              <el-input v-model="item.position" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="生日">
              <el-date-picker style="width: 100%" v-model="item.birthday"></el-date-picker>
            </el-form-item>
            <el-form-item label="性别" style="width: 210px;">
              <el-row>
                <el-radio-group v-model="item.sex">
                  <el-radio v-for="(sexItem, index) in sexRadio" :key="index" :label="sexItem.value">
                    {{ sexItem.label }}
                  </el-radio>
                </el-radio-group>
              </el-row>
            </el-form-item>
            <el-form-item label="头像/名片">
              <el-row style="width:100%" />
              <image-upload :value.sync="item.avatarOrBusinessCard" :limit="1" :isShowTip="false">
              </image-upload>
            </el-form-item>
            <el-form-item label="联系人备注">
              <el-input v-model="item.contactRemarks" type="textarea" resize="none" :rows="3"></el-input>
            </el-form-item>
          </el-row>
        </el-form>
        <div>
          <div class="collapse flex-middle flex-center fs-12 mt-10 pointer" @click="onCollapsed(item.contactId)">
            {{ item.show ? '收起' : '展开全部(选填)' }}
            <i class="ml-6" :class="item.show ? 'el-icon-arrow-up' : 'el-icon-arrow-down'"></i>
          </div>
        </div>
      </div>
    </div>

    <div class="add-contact flex-middle flex-center fs-12 mt-10 pointer" @click="onAddContactForm">
      <i class="el-icon-circle-plus-outline"></i>
      <span>添加联系人</span>
    </div>
  </div>
</template>

<script>
import { generatePhone } from "@/utils/tools";
import { deepClone } from "@/utils";
import { platformOption, rankOption, sexRadio } from "@/constant/customer/ContactCard";

const addConstruct = {
  contactId: +new Date(),
  show: false,
  nickName: '',
  email: '',
  rank: '',
  position: '',
  birthday: '',
  sex: 1,
  avatarOrBusinessCard: '',
  contactRemarks: '',
  primaryContactFlag: true,
  socialPlatform: [
    {
      id: +new Date(),
      type: '',
      account: '',
    }
  ],
  phone: [
    {
      id: +new Date(),
      phone_prefix: '',
      phone: ''
    },
  ],
}

export default {
  props: {
    contactList: {
      type: Array,
      default: () => [],
      required: true,
    }
  },
  data() {
    return {
      formList: [
        { ...deepClone(addConstruct) }
      ],
      phonePrefixList: generatePhone(),
      platformOption,
      rankOption,
      sexRadio,
    }
  },
  watch: {
    contactList: {
      handler(newVal) {
        if (newVal && newVal.length) {
          let list = deepClone(newVal)
          this.formList = list.map(val => {
            val.show = false
            val.contactId = val.id
            // 添加id
            val.phone = val.phone?.map((p, index) => {
              p.id = index
              return p
            }) || []
            // 添加id
            val.socialPlatform = val.socialPlatform?.map((s, index) => {
              s.id = index
              return s
            }) || []
            return val
          })
        }
      },
      deep: true,
      immediate: true,
    },
  },
  methods: {
    onCollapsed(contactId) {
      this.formList.map((val) => {
        if (val.contactId === contactId) {
          val.show = !val.show
        }
        return val
      })
    },
    onAddContactForm() {
      this.formList.push({ ...deepClone(addConstruct), contactId: +new Date() })
    },
    onDelContactForm(contactId) {
      this.formList = this.formList.filter(val => val.contactId !== contactId)
    },
    onSortContactForm(contactId) {
      let tempValue = {}
      let filterResList = this.formList.filter(val => {
        if (val.contactId !== contactId) {
          val.primaryContactFlag = false
          return val
        } else {
          val.primaryContactFlag = true
          Object.assign(tempValue, val)
        }
      })
      filterResList.unshift(tempValue)
      this.formList = filterResList
    },
    // 添加平台或联系人    
    onAdd(type) {
      this.formList.map(val => {
        val[type].unshift({
          id: +new Date(),
        })
        return val
      })
    },
    // 删除平台或联系人
    onReduce(contactId, id, type) {
      this.formList.map(val => {
        if (val.id === contactId) {
          val[type] = val[type].filter(val => val.id !== id)
        }
        return val
      })
    },
    getInnerData() {
      let innerData = JSON.parse(JSON.stringify(this.formList))
      innerData.map(val => {
        delete val.contactId
        val.socialPlatform = val.socialPlatform.map(platform => {
          delete platform.id
          return platform
        })
        val.socialPlatform = JSON.stringify(val.socialPlatform)
        val.phone = val.phone.map(phone => {
          delete phone.id
          return phone
        })
        val.phone = JSON.stringify(val.phone)
        return val
      })
      return innerData
    },
  }
}
</script>

<style lang="scss" scoped>
.contact-card {
  .contact-card-ul {
    display: block;
    box-sizing: border-box;
    padding: 16px;
    margin-bottom: 20px;
    border-radius: 4px;
    background-color: rgba(247, 248, 251);

    &:hover {
      background-color: rgba(236, 249, 255);
    }

    .card-title {
      &:before {
        color: #ff4d4f;
        content: "*";
        display: inline-block;
        font-family: SimSun, sans-serif;
        font-size: 14px;
        line-height: 1;
        margin-right: 4px;
      }
    }

    ::v-deep .el-form-item {
      margin-bottom: 0;
      margin-top: 10px;
    }

    ::v-deep .special-item {
      margin-top: 0;
      margin-bottom: 10px;
    }

  }

  .collapse {
    height: 40px;
    color: rgba(104, 108, 115);
  }

  .add-contact {
    height: 40px;
    color: rgba(104, 108, 115);
    background-color: rgba(247, 248, 251);
  }

  ::v-deep .el-input__inner {
    line-height: 1px !important; // 解决光标漂移问题

    &::-webkit-inner-spin-button {
      -webkit-appearance: none;
    }
  }

}
</style>
