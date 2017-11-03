<template>
  <div class="app-container calendar-list-container">
    <!--查询条件-->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="姓名或账户"
                v-model="listQuery.name"></el-input>
      <el-button class="filter-item" type="primary" icon="search" @click="handleFilter" title="搜索">搜索</el-button>
      <el-button class="filter-item" type="primary" icon="plus" v-if="userManager_btn_add" title="新增"
                 style="margin-left:10px;" @click="handleCreate">新增</el-button>
    </div>
    <!--列表-->
    <el-table :key="tableKey" :data="list" stripe v-loading.body="listLoading" fit highlight-current-row
              style="width: 100%">
      <el-table-column align="center" label="序号" type="index" width="65">
     <!--   <template scope="scope">
          <span>{{scope}}</span>
        </template>-->
      </el-table-column>
      <el-table-column align="center" label="服务名称" sortable prop="name">
        <template scope="scope">
          <span>{{scope.row.serverName}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="仓库">
        <template scope="scope">
          <span>{{scope.row.storeHouse}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="镜像名称">
        <template scope="scope">
          <span>{{scope.row.imageName}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="镜像版本" width="180">
        <template scope="scope">
          <span>{{scope.row.tag}}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" align="center" label="操作" width="150">
        <template scope="scope">
          <el-button v-if="userManager_btn_edit" size="small" type="success" @click="handleUpdate(scope.row)">编辑
          </el-button>
          <el-button v-if="userManager_btn_del" size="small" type="danger" @click="handleDelete(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page.sync="listQuery.page"
                     :page-sizes="[10.,20,30,50]" :page-size="listQuery.limit"
                     layout="total,sizes,prev,pager,next,jumper" :total="total">
      </el-pagination>
    </div>
    <!--编辑框-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form" :inline="true" :rules="rules" ref="form" label-width="90px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="服务名称" prop="serverName">
              <el-input v-model="form.serverName" placeholder="请输入服务名称" class="input-selects-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否可用" prop="enabled">
               <el-switch on-text="可用" off-text="禁用" on-value="1" off-value="0" v-model="form.enabled"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="镜像名称" placeholder="请输入密码" prop="imageName">
              <el-input  v-model="form.imageName" class="input-selects-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="镜像版本" placeholder="请输入密码" prop="tag">
              <el-input  v-model="form.tag" class="input-selects-width"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="仓库" prop="storeHouse">
              <el-input v-model="form.storeHouse" placeholder="请输入邮箱" class="input-selects-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注">
              <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 5}" class="input-selects-width" placeholder="请输入备注"
                        v-model="form.description"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="create('form')">确 定</el-button>
        <el-button v-else type="primary" @click="update('form')">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {
    page, addObj, getObj, delObj, putObj} from 'api/admin/server/index'
  import {mapGetters} from 'vuex'
  import {isvalidDate} from '@/utils/validate'
  export default {
    name: 'user',
    data() {
      const validateBirthday = (rule, value, callback) => {
        if (!isvalidDate(value)) {
          callback(new Error('请输入正确的日期格式'))
        } else {
          callback()
        }
      };
      return {
        form: this.initObj(),
        birthdayOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now() - 8.64e7;
          }
        },
        rules: {
          name: [
            {
              required: true,
              message: '请输入用户名',
              trigger: 'blur'
            }
          ],
          username: [
            {
              required: true,
              message: '请输入账户名',
              trigger: 'blur'
            },
            {
              min: 3,
              max: 20,
              message: '长度在3到20个字符',
              trigger: 'blur'
            }],
          password: [
            {
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 20,
              message: '长度在 5 到 20 个字符',
              trigger: 'blur'
            }
          ],
          email: [{
            type: 'email',
            message: '请输入有效的邮箱格式',
            trigger: 'blur'
          }],
          birthday: [{
            // required: true,
            // message: '请输入有效日期格式',
            trigger: 'blur',
            validator: validateBirthday
          }],
          mobilePhone: [{
            pattern: /^1[34578]\d{9}$/, message: '目前只支持中国大陆的手机号码', trigger: 'blur'
          }]
        },
        list: null,
        total: null,
        listLoading: true,
        dialogOrgTreeVisible: false,
        listQuery: {
          page: 1,
          limit: 20,
          name: undefined
        },
        sexOptions: ['男', '女'],
        dialogFormVisible: false,
        dialogStatus: '',
        userManager_btn_edit: false,
        userManager_btn_add: false,
        userManager_btn_del: false,
        textMap: {
          update: '更新',
          create: '创建'
        },
        tableKey: 0,
        treeNodes: ''
      }
    },
    created() {
      this.getList()
      /* this.userManager_btn_edit = this.elements['userManager:btn_edit']
       this.userManager_btn_add = this.elements['userManager:btn_add']
       this.userManager_btn_del = this.elements['userManager:btn_del']*/
      this.userManager_btn_edit = true;
      this.userManager_btn_add = true
      this.userManager_btn_del = true
    },
    computed: {
      ...mapGetters([
        'elements'
      ])
    },
    methods: {
      initObj() {
        return {
          serverName: '',
          storeHouse: '',
          imageName: '',
          tag: '',
          enabled: '',
          description: ''
        }
      },
      handleClose(done) {
        this.cancel('form');
        done();
      },
      getList() {
        this.listLoading = true
        page(this.listQuery).then(response => {
       //   debugger
       //   this.list = response.data.rows
        //  this.total = response.data.total
          this.list = response
          this.listLoading = false
        })
      },
      /* birthdayFormat(row, column) {
        var date = row[column.property];
        if (date === undefined || date === '') {
          return '';
        } else {
          return formatDate(date, 'yyyy-MM-dd');
        }
      }, */
      handleFilter() {
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getList()
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
      },
      handleUpdate(row) {
        getObj(row.id).then(response => {
          console.log(response)
          this.form = response.data
          this.dialogFormVisible = true
          this.dialogStatus = 'update'
        })
      },
      handleDelete(row) {
        this.$confirm('是否刪除该记录？', '记录', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delObj(row.id).then(() => {
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
            const index = this.list.indexOf(row)
            this.list.splice(index, 1)
          })
        })
      },
      create(formName) {
        const set = this.$refs
        set[formName].validate(valid => {
          if (valid) {
            addObj(this.form).then(() => {
              this.dialogFormVisible = false
              this.getList()
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
            })
          } else {
            return false
          }
        })
      },
      cancel(formName) {
        this.dialogFormVisible = false
        this.$refs[formName].resetFields()
      },
      update(formName) {
        const set = this.$refs
        set[formName].validate(valid => {
          if (valid) {
            this.dialogFormVisible = false
            this.form.password = undefined
            putObj(this.form.id, this.form).then(() => {
              this.dialogFormVisible = false
              this.getList()
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          } else {
            return false
          }
        })
      },
      resetTemp() {
        this.form = this.initObj();
      },
      checkOrg() {
        this.dialogOrgTreeVisible = true
      },
      getTreeNodes(msg) {
        var cid = '';
        var corg = '';
        cid = msg.map(item => item.id).join();
        corg = msg.map(item => item.label).join();
        this.treeNodes = msg
        this.form.corg = corg
        this.form.corgId = cid
        this.dialogOrgTreeVisible = false
      },
      orgCancelTree() {
        this.dialogOrgTreeVisible = false
      }
    }
  }
</script>
<style>
.input-selects-width{
  width: 340px;
}
</style>
