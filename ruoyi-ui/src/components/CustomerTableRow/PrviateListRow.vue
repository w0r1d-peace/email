<template>
    <div>
        <div class="auto-assistant">
            <div class="header flex-middle space-between py-20 px-20">
                <div>
                </div>
                <el-row class="flex-middle">
                    <el-row class="flex-middle gap-16">
                        <CollageIcon :show="focusFlag" :default-hide="false" @click="onCollageIcon" />
                        <el-tooltip placement="top" content="编辑">
                            <i class="el-icon-edit pointer" @click="editVisible = true"></i>
                        </el-tooltip>
                        <el-tooltip placement="top" content="写邮件">
                            <i class="el-icon-message pointer"></i>
                        </el-tooltip>
                    </el-row>
                    <el-row class="ml-10">
                        <OperateMenu :row="rowData" :indexOpt="indexOpt" :commandList="commandList">
                            <el-tooltip placement="left" content="更多操作">
                                <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
                            </el-tooltip>
                        </OperateMenu>
                    </el-row>

                </el-row>
            </div>
            <div class="container">
                <div class="base-info flex-start px-30">
                    <div class="icon-w-50">
                        <el-avatar :size="50" shape="square" :src="rowData.companyLogo"></el-avatar>
                    </div>
                    <div class="pl-10 fs-14">
                        <label>{{ rowData.companyName || '---' }}</label>
                        <div class="my-10 flex-middle">
                            <span>{{ rowData.customerNo || '---' }}</span>
                            <span class="ml-10">
                                <CellOperate type="country" :text="rowData.countryRegion" :show-copy-icon="false"
                                    :show-edit-icon="false"></CellOperate>
                            </span>
                        </div>
                        <div class="mb-10">跟进入: {{ rowData.followPerson || '---' }}</div>
                        <TableRowTags :detail-id="row.id" :tag-list="rowData.tagList" :index-opt="indexOpt"
                            @reloadTag="reload" />
                    </div>
                </div>
                <div class="tabs mt-10">
                    <TableRowTabs :row="rowData" :options="options" :defaultCurTab="defaultCurTab"
                        :defaultTabs="defaultTabs" @reload="reload" @selectId="onSelectId" />
                </div>
            </div>
            <el-backtop target=".el-tabs__content" :visibility-height="100" />
        </div>
        <CreateCustomerDrawer :visible.sync="editVisible" :row.sync="rowData" :index-opt="indexOpt"
            @load="onHideCreateDrawer" />
    </div>
</template>

<script>
import TableRowTabs from '@/views/customer/list/TableRowTabs.vue'
import TableRowTags from "@/views/customer/list/TableRowTags.vue";
import CreateCustomerDrawer from "@/views/customer/list/CreateCustomerDrawer.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import CellOperate from "@/views/customer/list/CellOperate.vue";
import OperateMenu from "@/views/customer/list/OperateMenu.vue";
import { editFocusFlagCustomer, getCustomerDetail } from "@/api/customer/publicleads";
import { deepClone } from "@/utils";
// 客户列表选项
import { stageList } from "@/api/company/status";
import { getOriginList } from "@/api/company/origin";
import { reasonList } from "@/api/company/poolRule";
import { getCustomerTagList } from "@/api/customer/config";
import { getPrivateSegmentMenu, getTeamMembers, searchGroupsCustomer, getSetPacketList } from "@/api/customer/publicleads";
export default {
    props: {
        row: {
            type: Object,
            default: () => {
                return {
                    id: '',
                    focusFlag: false
                }
            },
            required: false
        },
        indexOpt: {
            type: Object,
            default: () => {
                return {
                    groupOption: [],
                }
            }
        },
    },
    components: {
        OperateMenu,
        TableRowTabs,
        TableRowTags,
        CollageIcon,
        CellOperate,
        CreateCustomerDrawer,
    },
    data() {
        return {
            editVisible: false,
            options: {
                isShowSchedule: true,
                isTabSetHeight: true,
                isShowInfo: true,
                indexOpt: this.indexOpt
            },
            commandList: ['share', 'cancel', 'movePool', 'reassign'],
            focusFlag: false,
            rowData: {
                followPerson: '',
                companyName: '',
                customerNoType: '',
                companyLogo: '',
                followUpPersonnelList: [],
                sourceIds: [],
                sourceList: [],
                tagIds: [],
                tagList: [],
                stageId: '',
                stage: [],
                packetId: '',
                packet: [],
            },
            defaultCurTab: 'TableRowDealingsEmailTab',
            defaultTabs: ['TableRowActivityTab', 'TableRowDatumTab', 'TableRowTradeTab', 'TableRowTipsTab', 'TableRowDocTab', 'TableRowDealingsEmailTab'],
            customerId: ''
        }
    },
    watch: {
        row: {
            handler(newVal) {
                this.focusFlag = Boolean(newVal.focusFlag)
                if (newVal?.id && newVal.id !== this.customerId) {
                    this.customerId = newVal.id
                    this.getDetailData()
                }
            },
            immediate: true,
        },
    },
    mounted() {
    },
    methods: {
        async getDetailData() {
            try {
                const res = await getCustomerDetail({
                    id: this.row.id
                })
                if (res.code === 200) {
                    this.rowData = res.data
                    this.rowData.countryRegion = this.rowData.countryRegion?.split('/') || []
                    this.rowData.customerId = this.rowData.id
                    let sourceList = deepClone(this.rowData.sourceList)
                    this.rowData.sourceIds = sourceList?.map(val => val.id)
                    let tagList = deepClone(this.rowData.tagList)
                    this.rowData.tagIds = tagList?.map(val => val.id)
                    this.rowData.stageId = this.rowData.stage?.id
                    this.rowData.packetId = this.rowData.packet?.id
                    this.rowData.sourceIds = this.rowData.sourceList.map(val => val.id)
                    this.rowData.timezone = +this.rowData.timezone
                    this.rowData.followPerson = this.rowData.followUpPersonnelList.map(val => val.nickName)?.join('、')
                    this.rowData.contactList = this.generateContactList(this.rowData?.contactList)
                    this.rowData.publicleadsGroupsId = this.rowData.publicleadsGroups?.id
                }
            } catch (e) {
                console.error(e)
            }
        },
        async onCollageIcon(status) {
            if (!this.row.id) {
                return
            }
            try {
                const res = await editFocusFlagCustomer({ id: this.row.id })
                if (res.code === 200) {
                    this.focusFlag = !status
                    this.$emit('load')
                }
            } catch {
            }
        },
        reload() {
            this.getDetailData()
            this.$emit('load')
        },
        onHideCreateDrawer() {
            this.editVisible = false
            setTimeout(() => {
                this.getDetailData()
                this.$emit('load')
            }, 400)
        },
        onSelectId(id) {
            this.$emit('selectId', id)
        },
        generateContactList(arr) {
            if (arr && !arr.length) {
                return []
            }
            return arr.map(val => {
                val.phone = val.phone ? JSON?.parse(val.phone) : []
                val.socialPlatform = val.socialPlatform ? JSON?.parse(val.socialPlatform) : []
                return val
            })
        },
    }

}
</script>

<style lang="scss" scoped>
.auto-assistant {
    .header {
        border-bottom: 1px solid #f0f0f0;
    }

    .base-info {
        max-height: 130px;
        overflow-y: auto;
    }
}
</style>