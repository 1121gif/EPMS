<template>
  <div>
    <el-container>
      <el-header class="homeHeader">
        <div class="title">E P M S</div>
        <el-dropdown class="userInfo" @command="handleCommand">
          <span class="el-dropdown-link">
<!--            {{ this.$store.state.user.name }}<i> <img :src="this.$store.state.user.userFace"></i>-->
            <i> <img :src="user.userFace"></i><i style="font-size:5px;display:flex;align-items: center;vertical-align: center" >{{ user.name }}</i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
<!--            <el-dropdown-item command="settings">设置</el-dropdown-item>-->
            <el-dropdown-item command="logout">注销登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu router unique-opened :default-active="$route.path">
            <el-submenu :index="index+''" v-for="(item,index) in routes" :key="index" v-if="!item.hidden">
              <template slot="title"><i :class="item.iconCls" style="color: #262626;margin-right: 5px"></i>
                <span>{{ item.name }}</span>

              </template>
              <el-menu-item-group>
                <el-menu-item :index="children.path" v-for="(children,indexj) in item.children">
                  <span>{{ children.name }}</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="javascript:;">{{this.$router.currentRoute.name}}</a></el-breadcrumb-item>
          </el-breadcrumb>
          <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">
            <div style="padding-bottom: 20px">
              欢迎使用EPMS！
            </div>
            <div>
              <template>
                <el-carousel :interval="3000" type="card" height="380px">
                  <el-carousel-item v-for="item in imageList" :key="item">
                    <img :src="item" class="image">
                  </el-carousel-item>
                </el-carousel>
              </template>
            </div>
            <div style="margin-top: 10px;text-align: right;">
              开启今日办公吧！
            </div>
          </div>
          <router-view class="homeRouterview"/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      // user: this.$store.state.user,
      imageList: [
        "https://img.zcool.cn/community/01ac5f5d4aa7a5a8012187f4e57fe2.jpg@1280w_1l_2o_100sh.jpg",
        "https://img.zcool.cn/community/01cafc5dcb7bbfa8012053c02f581f.jpg@1280w_1l_2o_100sh.jpg",
        "https://img.zcool.cn/community/01a28058bd103aa801219c77706d7d.jpg@1280w_1l_2o_100sh.jpg",
        "https://img.zcool.cn/community/01f0a556f339216ac7257d20a341c5.jpg@1280w_1l_2o_100sh.jpg"]
    }
  },
  computed: {
    routes() {
      return this.$store.state.routes;
    },
    user(){
      return this.$store.state.currentAdmin;
    }
  },
  methods:{
    handleCommand(command){
      console.log(command);
      if (command == 'logout') {

        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '正在注销登录...'
          });
          // 注销登录
          this.postRequest('/logout');
          // 清空用户信息
          window.sessionStorage.removeItem('tokenStr');
          window.sessionStorage.removeItem('user');
          this.$store.commit('initRoutes', []);
          // this.$store.commit('initUser', []);
          //页面跳转
          this.$router.replace('/')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消注销'
          });
        });



      }
      if (command == 'userinfo') {
        this.$router.push('/userinfo');
      }
    }
  }
}
</script>

<style scoped>
.homeHeader {
  background: #001529;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 40px 15px;
  box-sizing: border-box;
}

.homeHeader .title {
  font-size: 30px;
  font-family: "Arial Black";
  color: white;
}
.homeHeader .userInfo{
  cursor: pointer;
  color: white;
}
.el-dropdown-link img {
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-left: 8px;
}
.homeWelcome {
  text-align: left;
  font-size: 30px;
  font-family: 华文楷体;
  color: #409EFF;
}
.homeRouterview{
  margin-top: 10px;
}
</style>