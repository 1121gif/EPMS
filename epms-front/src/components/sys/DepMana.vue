<template>
  <div style="width: 500px">
    <div id="dataPage">
      <el-input
          size="small"
          placeholder="请输入部门名称进行搜索..."
          prefix-icon="el-icon-search"
          v-model="filterText">
      </el-input>

      <el-tree
          id="el-tree"
          class="filter-tree"
          :data="data"
          :props="defaultProps"
          @node-contextmenu="rightClick"
          default-expand-all
          :filter-node-method="filterNode"
          :expand-on-click-node="false"
          ref="tree"/>
      <vue-context-menu
          :target="contextMenuTarget"
          :show="contextMenuVisible"
          class="right-menu"
          @update:show="(show) => contextMenuVisible = show">
        <a href="javascript:;"
           @click="createDep">添加部门</a>
        <a href="javascript:;"
           @click="deleteDep">删除部门</a>
      </vue-context-menu>
    </div>
    <div>
      <el-tooltip class="item" effect="dark" content="使用右键对部门进行操作" placement="right">
        <el-button size="small">tips</el-button>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
import {component as VueContextMenu} from '@xunlei/vue-context-menu';

export default {
  name: "DepMana",
  components: {
    'vue-context-menu': VueContextMenu,
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },



  data() {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      treeNodeData: '', // 存当前节点数据
      contextMenuTarget: null,
      contextMenuVisible: false,// 让菜单显示
      addDep: {
        name: '',
        parentId: -1
      }

    }
  },

  mounted() {
    this.initDep();
  },

  methods: {
    rightClick(e, data) {
      this.treeNodeData = data;// 存当前数据
      this.contextMenuVisible = true;// 让菜单显示
      const ele = document.querySelector('.right-menu');
      ele.style.position = 'fixed';
      ele.style.top = `${e.clientY}px`;
      ele.style.left = `${e.clientX + 10}px`; //根据鼠标点击位置设置菜单出现
    },
    createDep() {
      this.contextMenuVisible = false;
      this.$prompt('请输入部门名称', '添加部门', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,32}$/,
        inputErrorMessage: '请输入不超过32个中英文字符或是数字',
      }).then(({value}) => {
        this.addDep.name = value;
        this.addDep.parentId = this.treeNodeData.id
        this.postRequest('/system/basic/department/', this.addDep).then(resp => {
          if (resp) {
            this.initDep();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    deleteDep() {
      this.contextMenuVisible = false;
      this.$confirm('此操作将永久删除['+this.treeNodeData.name+']部门, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest('/system/basic/department/'+this.treeNodeData.id).then(resp=>{
          if (resp){
            this.initDep();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    initDep() {
      this.getRequest('/system/basic/department/').then(resp => {
        if (resp) {
          this.data = resp;
        }
      })
    },

    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    }
  },

}
</script>

<style scoped>
#dataPage {
  margin: 0 0;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}

/*右键会选中文字，为了美观将它禁用*/
#el-tree {
  user-select: none;
  margin-top: 10px;
}

.right-menu {
  font-size: 14px;
  position: fixed;
  background: #fff;
  border: solid 1px rgba(0, 0, 0, .2);
  border-radius: 3px;
  z-index: 999;
  display: none;
}

.right-menu a {
  width: 150px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  display: block;
  color: #1a1a1a;
  padding: 2px;
}

.right-menu a:hover {
  background: #bbb;
  color: #fff;
}

.right-menu {
  border: 1px solid #eee;
  box-shadow: 0 0.5em 1em 0 rgba(0, 0, 0, .1);
  border-radius: 1px;
}

a {
  text-decoration: none;
}

.el-input {
  width: 470px;
  display: block;
  text-align: left;
}
.item {
  margin-top: 10px;
}

</style>