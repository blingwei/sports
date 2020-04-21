<template>
  <div>
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
          prop="time"
          label="日期"
          fit>
        </el-table-column>
        <el-table-column
          prop="calorie"
          label="消耗的卡路里"
          fit>
        </el-table-column>
        <el-table-column
          prop="distance"
          label="距离"
          fit>
        </el-table-column>
        <el-table-column
          prop="trend"
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
              @click="viewMap(scope.row.line)"
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

    <el-dialog title="查看" :visible.sync="dialogFormVisible">
      <el-amap vid="map" :zoom="12" class="amap-demo" :events="events" :center="center">
        <el-amap-polyline :path="polyline.path"></el-amap-polyline>
      </el-amap>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: "SportRecord",
    data() {
      let self = this;
      return {
        size: 8,
        records: [],
        nums: 0,
        curPage: 1,

        dialogFormVisible: false,

        map: {},
        center: [],
        events: {
          init(o) {
            self.map = o
          }
        },
        polyline: {
          path: []
        }
      }
    },
    mounted() {
      this.initRecords();
    },
    computed: {
      tableHeight() {
        return window.innerHeight - 320
      }
    },
    methods: {
      initRecords() {
        let obj = this;
        obj.$axios.get('getRecords', {
          params: {
            start: (obj.curPage - 1) * obj.size,
            size: obj.size,
            input: obj.input
          }
        }).then(res => {
          if (res && res.status === 200) {
            obj.records = res.data.data.records;
            obj.nums = res.data.data.nums
          }
        })
      },
      handleCurrentChange(val) {
        this.curPage = val;
        this.initRecords();
        console.log(`当前页: ${val}`);
      },
      viewMap(line) {
        console.log(line);
        let path = [];
        let l  = line.split(":");
        l.forEach(item =>{
          let data = item.split(",");
          if(data.length === 2){
            let p = [data[0], data[1]];
            path.push(p)
          }
        });
        console.log(path);
        this.polyline.path = path;
        this.center = path[1];
        this.dialogFormVisible = true;
      }
    }
  }
</script>

<style scoped>
  .amap-demo {
    height: 400px;
  }
</style>
