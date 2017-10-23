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
      <el-table-column align="center" label="姓名" sortable prop="name" width="140">
        <template scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="账户" width="110">
        <template scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="性别" width="80">
        <template scope="scope">
          <span>{{scope.row.sex}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="生日" width="140" prop="birthday">
        <template scope="scope">
          <span>{{scope.row.birthday | formatDate('yyyy-MM-dd')}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="邮箱" width="180">
        <template scope="scope">
          <span>{{scope.row.email}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机" width="180">
        <template scope="scope">
          <span>{{scope.row.mobilePhone}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="最近更新时间" width="180">
        <template scope="scope">
          <span>{{scope.row.updateTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="最后更新人" width="110">
        <template scope="scope">
          <span>{{scope.row.updateName}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" label="联系地址">
        <template scope="scope">
          <span>{{scope.row.address}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" label="备注">
        <template scope="scope">
          <span>{{scope.row.description}}</span>
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
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="账户" prop="username">
          <el-input v-if="dialogStatus=='create'" v-model="form.username" placeholder="请输入账户"></el-input>
          <el-input v-else v-model="form.username" placeholder="请输入账户" readonly></el-input>
        </el-form-item>
        <el-form-item v-if="dialogStatus=='create'" label="密码" placeholder="请输入密码" prop="password">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select class="filter-item" v-model="form.sex" style="width:340px" placeholder="请选择">
            <el-option v-for="item in  sexOptions" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生日" aria-placeholder="请输入生日" prop="birthday">
          <el-date-picker v-model="form.birthday" type="date" placeholder="请选择日期"
                          :picker-options="birthdayOptions" style="width: 340px"></el-date-picker>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="mobilePhone">
          <el-input v-model="form.mobilePhone" placeholder="请输入手机号码"></el-input>
        </el-form-item>
        <el-form-item label="联系地址">
          <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 5}" style="width: 340px;" placeholder="请输入联系地址"
                    v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 5}" style="width: 340px;" placeholder="请输入备注"
                    v-model="form.description"></el-input>
        </el-form-item>
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
    page, addObj, getObj, delObj, putObj} from 'api/admin/user/index'
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
        tableKey: 0
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
          username: undefined,
          name: undefined,
          sex: '男',
          password: undefined,
          email: undefined,
          birthday: '',
          address: '',
          mobilePhone: undefined,
          description: undefined
        }
      },
      handleClose(done) {
        this.cancel('form');
        done();
      },
      getList() {
        this.listLoading = true
        page(this.listQuery).then(response => {
          this.list = response.data.rows
          this.total = response.data.total
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
      }
    }
  }
</script>
