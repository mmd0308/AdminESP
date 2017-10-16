<template>
  <div id="org" class="app-container calendar-list-container">
    <el-row>
      <el-col :span="4">
        <el-input placeholder="输入关键字进行过滤" v-model="filterText">
        </el-input>
        <el-tree class="filter-tree" :data="data2" :props="defaultProps"  :filter-node-method="filterNode" ref="tree2" @node-click="clickTree" @node-expand="expandTree">
        </el-tree>
      </el-col>
      <el-col :span="20">
        <div class="right-layout-from">
          <div v-if="this.state == 'see'" class="top-button">
            <el-button type="primary" size="small" native-type="submit"  @click="toCreate()">新增下级机构</el-button>
            <el-button type="primary" size="small" @click="toUpdate()">编辑当前机构</el-button>
            <el-button type="primary" size="small" @click="resetOrg('orgFrom')">重置</el-button>
          </div>
          <div  class="top-button">
            <el-button v-if="this.state == 'add'" type="primary" size="small" native-type="submit"  @click="createOrg('orgFrom')">保存</el-button>
            <el-button v-if="this.state == 'edit'" type="primary" size="small" native-type="submit"  @click="updateOrg('orgFrom')">保存</el-button>
          </div>

          <div class="tree-right-from">
            <el-form :model="orgFrom" :rules="rules" ref="orgFrom" label-width="120px" class="demo-ruleForm">
              <el-row aria-disabled="">
                <el-col :span="11">
                  <el-form-item  label="组织机构名称" prop="name">
                    <el-input v-model="orgFrom.name" v-if="this.state == 'see'" :readonly="true" ></el-input>
                    <el-input v-model="orgFrom.name" v-else ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="2">
                  <div class="grid-content"></div>
                </el-col>
                <el-col :span="11">
                  <el-form-item label="组织机构编码" prop="code">
                    <el-input v-model="orgFrom.code" v-if="this.state == 'see'" :readonly="true" ></el-input>
                    <el-input v-model="orgFrom.code" v-else></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="11">
                  <el-form-item label="上级组织机构" prop="porg">
                    <el-input v-model="orgFrom.porg" :readonly="true"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="2">
                  <div class="grid-content"></div>
                </el-col>
                <el-col :span="11">
                  <el-form-item label="单位简称" prop="unitA">
                    <el-input v-model="orgFrom.unitA" v-if="this.state == 'see'" :readonly="true"></el-input>
                    <el-input v-model="orgFrom.unitA" v-else></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="11">
                  <el-form-item label="机构类别" prop="orgType">
                    <el-select v-model="orgFrom.orgType" placeholder="请选择机构类别">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="2">
                  <div class="grid-content"></div>
                </el-col>
                <el-col :span="11">
                  <el-form-item label="部门类别" prop="departType">
                    <el-select v-model="orgFrom.departType" placeholder="请选择部门类别">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="机构说明" prop="note">
                <el-input type="textarea" v-model="orgFrom.note" v-if="this.state == 'see'" :readonly="true"></el-input>
                <el-input type="textarea" v-model="orgFrom.note" v-else></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { orgTree, getObj, addObj, putObj } from 'api/admin/org/index'
  export default {
    watch: {
      filterText(val) {
        this.$refs.tree2.filter(val);
      }
    },
    created() {
      // 初始化左侧树
      this.getOrgTree()
      debugger
    },
    methods: {
      initObj() {
        return {
          name: '',
          code: '',
          levelCode: '',
          orgType: '', // 机构类别
          deleted: '',
          note: '',
          parentid: ''
        }
      },
      getOrgTree() {
        var that = this
        orgTree(this.treeForm).then(response => {
          console.log(response.data)
          that.$set(that, 'data2', JSON.parse(response.data))
           // 获取左侧树第一个节点，初始化页面
          this.getObj(JSON.parse(response.data)[0].id)
        })
      },
      getObj(id) {
        var that = this
        getObj(id).then(response => {
          console.log(response.data)
          that.$set(that, 'orgFrom', response.data)
        })
      },
      toCreate() {
        var yid = this.orgFrom.id
        this.resetTemp()
        this.orgFrom.parentid = yid
        this.state = 'add'
      },
      toUpdate() {
        this.state = 'edit'
      },
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      clickTree(data) {
        debugger
        console.log(data)
      },
      expandTree(data) {
        console.log(data)
      },
      updateOrg(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            putObj(this.orgFrom.id, this.orgFrom).then(() => {
              this.getOrgTree()
              this.state = 'see'
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
      createOrg(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            addObj(this.orgFrom).then(() => {
              this.getOrgTree()
              this.state = 'see'
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
      resetOrg(formName) {
        this.$refs[formName].resetFields();
      },
      resetTemp() {
        this.orgFrom = this.initObj();
      }
    },
    data() {
      return {
        filterText: '',
        state: 'see',
        data2: [{
          id: 1,
          label: '一级 1'
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 3,
          label: '一级 3',
          children: [{
            id: 7,
            label: '二级 3-1'
          }, {
            id: 8,
            label: '二级 3-2'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        orgFrom: {
          id: '',
          name: '',
          code: '',
          levelCode: '',
          orgType: '', // 机构类别
          deleted: '',
          note: '',
          parentid: ''
        },
        treeForm: {
          parentid: 0
        },
        rules: {
          name: [
            { required: true, message: '请输入组织机构名称', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          code: [
            { required: true, message: '请输入编码', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注', trigger: 'blur' }
          ]
        }
      };
    }
  }
</script>
<style>
.right-layout-from{
  margin-left: 20px;
}
.top-button{
  margin-bottom: 20px;
}
.tree-right-from{
  width: 70%;
  margin: 3%;
}
.grid-content{
    border-radius: 4px;
    min-height: 36px;
}
</style>
