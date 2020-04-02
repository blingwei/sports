<template>
  <div>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        stripe
        :data="users"
        style="width: 100%">
        <el-table-column
          type="selection"
          width="120">
        </el-table-column>
        <el-table-column
          type="index"
          label="序号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="name"
          label="名字"
          fit>
        </el-table-column>
        <el-table-column
          prop="sex"
          label="性别"
          fit>
        </el-table-column>
        <el-table-column
          prop="age"
          label="年龄"
          fit>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="240">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="view(scope.row.id)"
            >
              查看运动记录
            </el-button>
            <el-button
              type="text"
              size="small"
              @click="suggest(scope.row.id)"
            >
              建议
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "MyUser",
      data() {
        return {
          users: [],
        }
      },
      mounted() {
        this.initUsers();
      },
      computed: {
        tableHeight() {
          return window.innerHeight - 320
        }
      },
      methods: {
        initUsers() {
          let obj = this;
          obj.$axios.get('getMyUsers').then(res => {
            if (res && res.status === 200) {
              obj.users = res.data.data;
            }
          })
        },
        view(id){

        },
        suggest(id){
          let obj = this;
          this.$prompt('请输入建议', '建议', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(({ value }) => {
            obj.$axios.post('suggest', {
              id: id,
              object: value
            }).then(res => {
              if (res && res.status === 200) {
                this.$message({
                  type: 'success',
                  message: '消息以发送'
                });
              }
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });

        }
      }
    }
</script>

<style scoped>

</style>
