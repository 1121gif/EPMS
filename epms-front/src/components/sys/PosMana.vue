<template>
  <div>
    <div>
      <el-input
          size="small"
          class="addPosInput"
          placeholder="添加职位..."
          suffix-icon="el-icon-plus"
          v-model="pos.name"
          @keydown.enter.native="addPosition">
      </el-input>
      <el-button size="small" icon="el-icon-plus" type="primary"  @click="addPosition">添加</el-button>
    </div>
    <div class="posManaMain">
      <el-table
          ref="multipleTable"
          :data="positions"
          stripe
          border
          size="small"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            prop="id"
            label="id"
            type="index"
            width="80">
        </el-table-column>
        <el-table-column
            prop="name"
            label="职位"
            width="120">
        </el-table-column>
        <el-table-column
            prop="createDate"
            label="创建时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="enabled"
            label="是否启用"
            width="120">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.enabled" type="">已启用</el-tag>
            <el-tag v-else type="danger">未启用</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        <el-button :disabled="this.multipleSelection.length==0" @click="toggleSelection()">取消选择</el-button>
        <el-button type="danger" :disabled="this.multipleSelection.length==0" @click="batchDeletion">批量删除</el-button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "PosMana",
  data() {
    return {
      pos: {
        name: ''
      },
      positions: [],
      multipleSelection: [],
      updatePos: {
        name: '',
        id: ''
      }
    }
  },
  mounted() {
    this.initPositions();
  },
  methods: {
    addPosition() {
      if (this.pos.name) {
        this.postRequest('/system/basic/position/', this.pos).then(resp => {
          if (resp) {
            this.initPositions();
            this.pos.name = '';
          }
        })
      } else {
        this.$message.error('职位名称不能为空！');
      }
    },
    initPositions() {
      this.getRequest('/system/basic/position/').then(resp => {
        if (resp) {
          this.positions = resp;
        }
      })
    },
    handleEdit(index, data) {
      this.$prompt('请输入职位名称', '编辑职位', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        //判断职位是否为空,32个字符以内
        inputPattern: /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,32}$/,
        inputErrorMessage: '请输入不超过32个中英文字符或是数字',
        inputValue: data.name
      }).then(({value}) => {
        this.updatePos.id = data.id;
        this.updatePos.name = value;
        this.putRequest('/system/basic/position/', this.updatePos).then(resp => {
          if (resp) {
            this.initPositions();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    handleDelete(index, data) {
      this.$confirm('此操作将永久删除[' + data.name + ']职位, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest('/system/basic/position/' + data.id).then(resp => {
          if (resp) {
            this.initPositions();
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    batchDeletion() {
      this.$confirm('此操作将永久删除[' + this.multipleSelection.length + ']条记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = '?';
        this.multipleSelection.forEach(item => {
          ids += 'ids=' + item.id + '&';
        })
        this.deleteRequest('/system/basic/position/' + ids).then(resp => {
          if (resp) {
            this.initPositions();
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    toggleSelection() {
      this.$refs.multipleTable.clearSelection();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    }
  }
}
</script>

<style>
.addPosInput {
  width: 300px;
  margin-right: 8px;
}

.posManaMain {
  margin-top: 10px;
}

</style>