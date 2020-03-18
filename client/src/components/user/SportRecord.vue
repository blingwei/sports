<template>
  <div>
    <el-row style="margin: 18px 2%;width: 95%" >
      <el-col :span="6">
        <el-input v-model="input" placeholder="请输入内容" />
      </el-col>
      <el-col :span="6">
        <el-select v-model="status" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-button @click="initPublishes">搜索</el-button>
      </el-col>

    </el-row>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        stripe
        :data="records"
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
          prop="authorName"
          label="日期"
          fit>
        </el-table-column>
        <el-table-column
          prop="publishName"
          label="消耗的卡路里"
          fit>
        </el-table-column>
        <el-table-column
          prop="status"
          label="距离"
          fit>
        </el-table-column>
        <el-table-column
          prop="status"
          label="运动趋势"
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
              @click=""
            >
              查看轨迹
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
        name: "SportRecord",
      data() {
        return {
          size: 8,
          input: "",
          records: [],
          nums: 0,
          curPage: 1,
          options: [{
            value: '1',
            label: '按天统计'
          }, {
            value: '2',
            label: '按月统计'
          }, {
            value: '3',
            label: '逐条统计'
          },],
          status: '1'
        }
      },
      mounted() {
        // this.initRecords;
      },
      computed: {
        tableHeight() {
          return window.innerHeight - 320
        }
      },
      methods: {
        initRecords() {
          let obj = this;
          obj.$axios.get('getMyCoach', {
            params: {
              start: (obj.curPage - 1) * obj.size,
              size: obj.size,
              input: obj.input,
              status: obj.status
            }
          }).then(res => {
            if (res && res.status === 200) {
              obj.records = res.data.data.records;
              obj.nums = res.data.data.nums
            }
          })
        },
        handleCurrentChange(val){
          this.curPage = val;
          this.initRecords();
          console.log(`当前页: ${val}`);
        },
      }
    }
</script>

<style scoped>

</style>
