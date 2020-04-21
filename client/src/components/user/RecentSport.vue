<template>
    <div class="amap-page-container">
        <el-amap-search-box class="search-box" :search-option="searchOption"
                            :on-search-result="onSearchResult"></el-amap-search-box>
        <el-amap vid="map"  :zoom="12" class="amap-demo" :plugin="plugin" :events="events">
        </el-amap>

        <el-row style="margin-top: 10px; height: 30px; line-height: 30px">
            <el-col :span="10">
                <el-date-picker
                        v-model="date"
                        type="datetimerange"
                        range-separator="至"
                        start-placeholder="开始时间"
                        end-placeholder="结束时间">
                </el-date-picker>
            </el-col>
            <el-col :span="4">
                <span>绘制轨迹</span>
                <el-switch
                        v-model="flag"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        @change="darwMap"
                >
                </el-switch>
            </el-col>
            <el-col :span="4">
                <el-button @click="createRecord">发布</el-button>
            </el-col>
        </el-row>
    </div>

</template>

<script>


    export default {
        name: "RecentSport",
        data() {
          let self = this;
            return {
                markers: [],
                searchOption: {
                    city: '上海',
                    citylimit: false
                },
                mapCenter: [121.59996, 31.197646],
                mouseTool:{},
                lines:[],
                distance: 0,
                plugin: [
                ],
                events: {
                  init(o){
                    self.mouseTool = new AMap.MouseTool(o);
                    self.mouseTool.on('draw', function (e) {
                        self.distance = e.obj.getLength();
                        self.lines = e.obj.getPath();
                    });
                  }
                },
                date: [],
                flag: false
            }

        },
        mounted() {

        },
      methods: {
          darwMap(flag){
            if(flag){
              this.mouseTool.rule();
            }else{
              this.mouseTool.close();
            }
          },
          onSearchResult(pois) {
            let latSum = 0;
            let lngSum = 0;
            if (pois.length > 0) {
              pois.forEach(poi => {
                let {lng, lat} = poi;
                lngSum += lng;
                latSum += lat;
                this.markers.push([poi.lng, poi.lat]);
              });
              let center = {
                lng: lngSum / pois.length,
                lat: latSum / pois.length
              };
              this.mapCenter = [center.lng, center.lat];
            }
          },
          createRecord() {
            let self = this;
            let line = "";
            self.lines.forEach(point =>{
              if(point  !== 'undefined'){
                let data = point.lng + "," + point.lat;
                line += data + ":";
              }
            });
            this.$confirm('您确定要发布吗?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$axios.post("creatRecord", {
                line: line,
                distance: self.distance,
                creatTime: self.date[0],
                endTime:self.date[1],
              }).then(res =>{
                if(res && res.status){
                  this.$message({
                    type: 'success',
                    message: '发布成功!'
                  });
                }else{
                  this.$message({
                    type: 'info',
                    message: '发布失败!'
                  });
                }

              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消发布'
              });
            });


          }
        }
    }
</script>

<style scoped>
    .amap-demo {
        height: 500px;
    }

    .search-box {
        position: absolute;
        top: 25px;
        left: 20px;
    }

    .amap-page-container {
        position: relative;
    }
</style>
