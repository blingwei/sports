<template>
    <div class="amap-page-container">
        <el-amap-search-box class="search-box" :search-option="searchOption"
                            :on-search-result="onSearchResult"></el-amap-search-box>
        <el-amap vid="map" :center="mapCenter" :zoom="12" class="amap-demo" :plugin="plugin" :events="events">
            <el-amap-marker v-for="(marker, index) in markers" :position="marker" :key="index"></el-amap-marker>
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
                        inactive-color="#ff4949">
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
                walking:{},
                plugin: [{
                    pName: 'Walking',
                    events: {
                    init(instance) {
                      // self.walking = instance;
                      // console.log(instance)
                    }
                  }},
                  {
                    pName: 'RangingTool',
                    events: {
                      init(instance) {
                        console.log(instance)
                        instance.turnOn()
                      }
                    }},


                ],
                events: {
                },
                date: [],
                flag: true
            }

        },
        mounted() {
        },
        methods: {
          addMarker() {
            let lng = 121.5 + Math.round(Math.random() * 1000) / 10000;
            let lat = 31.197646 + Math.round(Math.random() * 500) / 10000;
            this.markers.push([lng, lat]);
          }
          ,
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
            let vm = this;
            // this.walking.search([116.399028, 39.845042], [116.436281, 39.880719], function (status, result) {
            //   // result即是对应的步行路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_WalkingResult
            //   if (status === 'complete') {
            //     console.log('绘制步行路线完成'+result)
            //   } else {
            //     console.logr('步行路线数据查询失败' + result)
            //   }
            // });

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
