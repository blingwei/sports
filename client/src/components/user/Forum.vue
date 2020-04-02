<template>
  <div>
      <div class="display" v-for="item in displayList">
        <el-card shadow="hover" style="height: 100%;">
          <div style="font-size: 14px; font-weight: bold; color: #78b6f7; margin-bottom: 5px">{{item.title}}</div>
          <div v-html="item.content" class="content"></div>
          <span style="font-size: 10px;  margin-right: 20px">来自{{item.userName}} </span>  <span @click="display(item.id)" class="click">前往文章</span>
        </el-card>
      </div>

  </div>
</template>

<script>
    export default {
        name: "Forum",
      data (){
          return {
            displayList: []
          }
      },
      mounted() {
          this.init()
      },
      methods: {
        init() {
          this.$axios.get("getAllConditions").then(res => {
            if (res.data.code === 200) {
              this.displayList = res.data.data;
              console.log(res.data.data);
            }
          })
        },
        display(id) {
          let routeData = this.$router.resolve({
            path: '/conditionDisplay',
            query: {id: id}
          });
          window.open(routeData.href, '_blank');
        },
      }
    }
</script>

<style scoped>
  .display {
    width: 100%;
    height: 180px;
    margin-bottom: 10px;
    text-align: left;
  }
  .content {
    font-size: 10px; height: 100px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .click{
    font-size: 10px;  color: #78b6f7;
  }
  .click:hover{
    cursor: pointer;
  }
</style>
