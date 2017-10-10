<template>
  <div id="org" class="app-container calendar-list-container">
    <el-row>
      <el-col :span="4">
        <el-input placeholder="输入关键字进行过滤" v-model="filterText">
        </el-input>
        <el-tree class="filter-tree" :data="data2" :props="defaultProps"  :filter-node-method="filterNode" ref="tree2">
        </el-tree>
      </el-col>
      <el-col :span="20">
        <div class="right-layout-from">
          <div class="top-button">
            <el-button type="primary" size="small" native-type="submit"  @click="createOrg('orgFrom')">新增下级机构</el-button>
            <el-button type="primary" size="small">编辑当前机构</el-button>
            <el-button type="primary" size="small" @click="resetOrg('orgFrom')">重置</el-button>
          </div>
          <div class="tree-right-from">
            <el-form :model="orgFrom" :rules="rules" ref="orgFrom" label-width="120px" class="demo-ruleForm">
              <el-col :span="11">
                <el-form-item label="组织机构名称" prop="name">
                  <el-input v-model="orgFrom.name"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <div class="grid-content"></div>
              </el-col>
              <el-col :span="11">
                <el-form-item label="组织机构编码" prop="code">
                  <el-input v-model="orgFrom.code"></el-input>
                </el-form-item>
              </el-col>
              <el-form-item label="备注" prop="note">
                <el-input type="textarea" v-model="orgFrom.note"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default {
    watch: {
      filterText(val) {
        this.$refs.tree2.filter(val);
      }
    },
    methods: {
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      createOrg(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            alert('fj')
          } else {
            return false
          }
        })
      },
      resetOrg(formName) {
        this.$refs[formName].resetFields();
      }
    },
    data() {
      return {
        filterText: '',
        data2: [{
          id: 1,
          label: '一级 1',
          children: [{
            id: 4,
            label: '二级 1-1',
            children: [{
              id: 9,
              label: '三级 1-1-1'
            }, {
              id: 10,
              label: '三级 1-1-2'
            }]
          }]
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
          name: '',
          code: '',
          note: ''
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
