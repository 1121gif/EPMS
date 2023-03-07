<template>
  <div>
    <el-row :gutter="20">
      <el-col :md="24" :lg="7">
        <el-card shadow="always">
          <div class="userInfo">
            <span>
              <el-upload
                  action="/admin/userface"
                  :headers="headers"
                  :data="user"
                  :show-file-list="false"
                  :on-success="onSuccess">
                  <img title="点击修改头像" :src="user.userFace">
              </el-upload>
              </span>
            <div class="username">
              {{ user.name }}
            </div>
            <div class="sign">
              人生有梦，各自精彩
            </div>
            <i class="el-icon-phone"> {{ user.phone }} </i>
            <i class="el-icon-s-home"> {{ user.address }} </i>
          </div>
          <el-divider></el-divider>
          <div style="font-weight: bold">
            <span>用户标签</span>
          </div>
          <div class="tag">
            <el-tag
                :key="tag"
                v-for="tag in dynamicTags"
                closable
                :disable-transitions="false"
                @close="handleClose(tag)">
              {{ tag }}
            </el-tag>
            <el-input
                class="input-new-tag"
                v-if="inputVisible"
                v-model="inputValue"
                ref="saveTagInput"
                size="small"
                @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm"
            >
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInput">+</el-button>
          </div>
          <el-divider></el-divider>
          <div>
            <div style="font-weight: bold">
              <span>团队信息</span>
            </div>
            <div class="tag">
              <span>雨纷纷</span>
              <el-divider direction="vertical"></el-divider>
              <span>旧故里</span>
              <el-divider direction="vertical"></el-divider>
              <span>草木深</span>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :md="24" :lg="17">
        <el-card shadow="always">
          <div slot="header">
            基本资料
          </div>
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="基本资料" name="first">
              <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                       class="demo-ruleForm">
                <el-form-item label="用户名" prop="name">
                  <el-input v-model="ruleForm.name"/>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                  <el-input v-model="ruleForm.phone"/>
                </el-form-item>
                <el-form-item label="住宅电话" prop="telephone">
                  <el-input v-model="ruleForm.telephone"/>
                </el-form-item>
                <el-form-item label="联系地址" prop="address">
                  <el-input v-model="ruleForm.address"/>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitAdminInfo('ruleForm')">保存</el-button>
                  <el-button @click="resetForm('ruleForm')">重置</el-button>
                  <el-button type="danger" @click="closePage">关闭</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="second">
              <el-form :model="checkPass" status-icon :rules="checkPassRules" ref="checkPass" label-width="100px"
                       class="demo-ruleForm">
                <el-form-item label="旧密码" prop="oldPass">
                  <el-input v-model="checkPass.oldPass" placeholder="请输入旧密码" show-password></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="pass">
                  <el-input v-model="checkPass.pass" placeholder="请输入新密码" show-password></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPass">
                  <el-input v-model="checkPass.confirmPass" placeholder="请输入确认密码" show-password></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitPass('checkPass')">保存</el-button>
                  <el-button @click="resetForm('checkPass')">重置</el-button>
                  <el-button type="danger" @click="closePage">关闭</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "AdminInfo",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.checkPass.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      headers:{
        Authorization:window.sessionStorage.getItem('tokenStr')
      },
      user: JSON.parse(window.sessionStorage.getItem('user')),
      dynamicTags: ['管理员', '程序员', '开发者', '秃头ing', '我真是服了这个老六'],
      inputVisible: false,
      inputValue: '',
      activeName: 'first',
      phone: '',
      formLabelWidth: '120px',
      ruleForm: {
        name: '',
        phone: '',
        telephone: '',
        address: '',
      },
      rules: {
        name: [
          {required: true, message: '请输入正确的用户名', trigger: 'blur'},
          {min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入正确的手机号码', trigger: 'blur'},
          {min: 11, max: 11, message: '长度 11 个字符', trigger: 'blur'}
        ],
        telephone: [
          {required: true, message: '请输入正确的住宅电话', trigger: 'blur'},
          {min: 8, max: 16, message: '长度 8 到 16 个字符', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '联系地址不能为空', trigger: 'blur'},
          {min: 8, max: 64, message: '长度 8 到 64 个字符', trigger: 'blur'}
        ],
      },
      checkPass: {
        oldPass: '',
        pass: '',
        confirmPass: '',
        adminId: ''
      },
      checkPassRules: {
        oldPass: [
          {required: true, message: '旧密码不能为空', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 2 到 6 个字符', trigger: 'blur'}
        ],
        pass: [
          {required: true, message: '新密码不能为空', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
        confirmPass: [
          {required: true, validator: validatePass, trigger: 'blur'}
        ]
      }
    }
  },
  mounted() {
    this.initUser();
  },
  methods: {
    onSuccess() {
      this.initUser();
      this.$store.commit('INIT_ADMIN');
    },
    initUser() {
      this.getRequest('/admin/info').then(resp => {
        if (resp) {
          this.user = resp;
          Object.assign(this.ruleForm, this.user);
          window.sessionStorage.setItem('user', JSON.stringify(resp));
          this.$store.commit('INIT_ADMIN',resp);
          this.resetSetItem('user', JSON.stringify(resp));
        }
      })
    },
    closePage() {
      this.$router.push('/home');
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    submitAdminInfo() {
      this.$confirm('此操作将更新个人信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.putRequest('/admin/info', this.ruleForm).then(resp => {
          if (resp) {
            this.initUser();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    submitPass() {
      this.checkPass.adminId = this.user.id;
      this.putRequest('/admin/pass', this.checkPass).then(resp => {
        if (resp) {
          this.checkPass.pass = '';
          this.checkPass.oldPass = '';
          this.checkPass.confirmPass = '';
          this.$message({
            type: 'success',
            message: '当前用户凭证已过期请重新登录...'
          });
          //清空用户信息
          window.sessionStorage.removeItem('tokenStr');
          window.sessionStorage.removeItem('user');
          //清空菜单
          this.$store.commit('initRoutes', []);
          //跳转到登录页
          this.$router.replace('/')
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.initUser();
    }
  }
}
</script>

<style scoped>
.userInfo {
  margin: 0 0;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}

.username {
  margin-top: 8px;
  font-weight: bold;
}

.userInfo img {
  width: 80px;
  height: 80px;
  border-radius: 40px;
}

.sign {
  font-size: small;
  margin-top: 10px;
  color: #938f8f;
}

.el-icon-phone {
  margin-top: 20px;
  font-size: small;
  display: block;
  text-align: left;
  color: #938f8f;
}

.el-icon-s-home {
  margin-top: 12px;
  font-size: small;
  display: block;
  text-align: left;
  color: #938f8f;
}

.tag {
  margin-top: 20px;
}

.el-tag {
  margin-top: 8px;
  margin-right: 10px;
}

.button-new-tag {
  margin-top: 8px;
  margin-right: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  margin-top: 8px;
  margin-right: 10px;
  width: 90px;
  vertical-align: bottom;
}
</style>