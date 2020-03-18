<template>
  <div>
    <el-card style="text-align: left;margin: 18px 2%;width: 95%" >
    <div v-if="myCoach.name !== null">
      <div class="info">教练名字：{{myCoach.name}} </div>
      <div class="info">教练建议：{{myCoach.suggest}} </div>
    </div>
    <div v-else>
      <div class="info">您还没有教练，请选择教练</div>
    </div>
    </el-card>
    <el-row style="margin: 18px 2%;width: 95%">
      <el-col :span="6">
        <el-input v-model="input" placeholder="请输入内容"/>
      </el-col>
      <el-col :span="6">
        <el-button @click="initCoaches">搜索</el-button>
      </el-col>

    </el-row>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        stripe
        :data="coaches"
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
          prop="status"
          label="状态"
          fit>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="selectCoach(scope.row.id)"
            >
              选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        style="text-align: right"
        @current-change="handleCurrentChange"
        :page-size="size"
        layout="total,prev, pager, next, jumper"
        :total="nums"
      >
      </el-pagination>
    </el-card>
  </div>

</template>

<script>
  export default {
    name: "MyCoach",
    data() {
      return {
        size: 8,
        input: "",
        coaches: [],
        nums: 0,
        curPage: 1,
        myCoach: {},
      }
    },
    mounted() {
      this.initCoaches();
      this.initMyCoach();
    },
    computed: {
      tableHeight() {
        return window.innerHeight - 320
      }
    },
    methods: {
      initMyCoach() {
        let obj = this;
        this.$axios.get('getMyCoach').then(res => {
          if (res && res.status === 200) {
            obj.myCoach = res.data.data;
            console.log(res.data.data)
            console.log(obj.myCoach.name)
          }
        })
      },
      selectCoach(id){
        let obj = this;
        this.$confirm('您确定要选择吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          obj.$axios.post("selectCoach", {
            id: id
          }).then(res => {
            if (res && res.status === 200) {
              obj.initMyCoach();
              obj.$message({
                type: 'info',
                message: '选择成功'
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      },
      initCoaches() {
        let obj = this;
        obj.$axios.get('getCoaches', {
          params: {
            start: (obj.curPage - 1) * obj.size,
            size: obj.size,
            input: obj.input
          }
        }).then(res => {
          if (res && res.status === 200) {
            obj.coaches = res.data.data.coaches;
            obj.nums = res.data.data.nums
          }
        })
      },
      handleCurrentChange(val) {
        this.curPage = val;
        this.initCoaches();
        console.log(`当前页: ${val}`);
      },
    }
  }
</script>

<style scoped>
.info{
  width: 100%;
  height: 30px;
  line-height: 30px;
  font-size: 16px;
  text-align: left;
  color: #6a737d;
}
</style>
