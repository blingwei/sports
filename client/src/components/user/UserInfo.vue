<template>
  <div>
    <el-card>

      <div class="information">姓名: <span v-if="userInfo.name !== null">{{userInfo.name}}</span> <span
        v-else>未设置</span></div>
      <div class="information">年龄: <span v-if="userInfo.age !== null">{{userInfo.age}}</span> <span
        v-else>未设置</span></div>
      <div class="information">性别: <span v-if="userInfo.sex !== null">
        <span v-if="userInfo.sex === '0'">男性</span>
        <span v-else>女性</span>
      </span> <span
        v-else>未设置</span></div>
      <div class="information">简介: <span v-if="userInfo.introduce !== null">{{userInfo.introduce}}</span><span
        v-else>未设置</span></div>
      <div class="information"><el-button @click="editUserInfo" >编 辑 资 料</el-button></div>


    </el-card>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="姓名" :label-width="width">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="width">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="width">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="简介" :label-width="width">
          <el-input v-model="form.introduce"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="edit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "UserInfo",
    data() {
      return {
        userInfo: {
          name: " ",
          sex: " ",
          age: "",
          url: " ",
          introduce: " ",
          status: false,
          id: ''
        },
        form: {
          name: " ",
          sex: " ",
          age: " ",
          introduce: " "
        },
        options: [{
          value: '0',
          label: '男性'
        }, {
          value: '1',
          label: '女性'
        }],
        dialogFormVisible: false,
        width: '40px'
      }
    },
    methods: {
      init() {
        this.$axios.get("getUserInfo", {
          params: {
            userName: this.$store.state.user.username
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.userInfo = res.data.data;
            console.log(res.data.data);
          }
        })
      },
      editUserInfo() {
        let obj = this;
        obj.dialogFormVisible = true;
        obj.form.name = obj.userInfo.name;
        obj.form.age = obj.userInfo.age;
        obj.form.sex = obj.userInfo.sex;
        obj.form.introduce = obj.userInfo.introduce;
      },
      edit() {
        let obj = this;
        obj.dialogFormVisible = false;
        console.log(obj.form.sex);
        obj.$axios.post("editUserInfo", {
            name: obj.form.name,
            age: obj.form.age,
            sex: obj.form.sex,
            introduce: obj.form.introduce,
            id: obj.userInfo.id
          }
        ).then(res => {
          if (res.data.code === 200) {
            obj.init();
            obj.$message({
              type: 'success',
              message: '修改成功!'
            });
          }
          if (res.data.code === 400) {
            obj.$message.error("出現異常")
            console.log(res.data.message)
          }
        })
      },
    },
    mounted() {
      this.init()
    }
  }
</script>

<style scoped>
.information {
   width: 80%;
  height: 40px;
  line-height: 40px;
  font-size: 24px;
  margin-top: 60px;
  text-align: left;
  margin-left: 5%;
  color: #6a737d;
}
</style>
