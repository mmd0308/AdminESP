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
          <div class="top-button">
            <el-button type="primary" size="small" native-type="submit"  @click="createOrg('orgFrom')">新增下级机构</el-button>
            <el-button type="primary" size="small">编辑当前机构</el-button>
            <el-button type="primary" size="small" @click="resetOrg('orgFrom')">重置</el-button>
          </div>
          <div class="tree-right-from">
            <el-form :model="orgFrom" :rules="rules" ref="orgFrom" label-width="120px" class="demo-ruleForm">
              <el-row>
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
              </el-row>
              <el-row>
                <el-col :span="11">
                  <el-form-item label="上级组织机构" prop="porg">
                    <el-input v-model="orgFrom.porg"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="2">
                  <div class="grid-content"></div>
                </el-col>
                <el-col :span="11">
                  <el-form-item label="单位简称" prop="unitA">
                    <el-input v-model="orgFrom.unitA"></el-input>
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
                  <el-form-item label="考核类别" prop="checkType">
                     <el-select v-model="orgFrom.checkType" placeholder="请选择考核类别">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
               <el-row>
                <el-col :span="11">
                  <el-form-item label="部门类别" prop="departType">
                    <el-select v-model="orgFrom.departType" placeholder="请选择部门类别">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="2">
                  <div class="grid-content"></div>
                </el-col>
                <el-col :span="11">
                  <el-form-item label="业务类别" prop="businessType">
                     <el-select v-model="orgFrom.businessType" placeholder="请选择业务类别">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="11">
                  <el-form-item label="分院管领导" prop="leader">
                      <el-radio-group v-model="orgFrom.leader">
                        <el-radio label="张三"></el-radio>
                        <el-radio label="李四"></el-radio>
                        <el-radio label="王五"></el-radio>
                      </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="2">
                  <div class="grid-content"></div>
                </el-col>
                <el-col :span="11">
                  <el-form-item label="机构编码" prop="orgCodeP">
                    <el-input v-model="orgFrom.orgCodeP"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="单位地址" prop="address">
                <el-input type="textarea" v-model="orgFrom.address"></el-input>
              </el-form-item>  

              <el-form-item label="机构说明" prop="note">
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
      clickTree(data) {
        console.log(data)
      },
      expandTree(data) {
        console.log(data)
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
          name: '',
          code: '',
          note: '',
          porg: '',
          unitA: '', // 单位简称
          orgType: '', // 机构类别
          checkType: '', // 考核类别
          businessType: '', // 业务类别
          departType: '', // 部门类别
          address: '', // 单位地址
          orgCodeP: '',  // 机构编码
          leader: '' // 分管院领导
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
