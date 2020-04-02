<template>
  <div>
    <el-row>
      <el-col :span="6">
        <el-input
          v-model="title"
          style="margin: 10px 0px;font-size: 18px;"
          placeholder="请输入标题"></el-input>
      </el-col>
      <el-col :span="6" >
        <el-button type="" style="margin: 10px 0px" @click="submit"> 发布</el-button>
      </el-col>
    </el-row>
    <mavon-editor v-model="content" ref="md" @change="change" style="height: 600px">
    </mavon-editor>
  </div>
</template>

<script>
  import { mavonEditor } from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'
  export default {
    name: "Creation",
    components: {
      mavonEditor,
    },
    data() {
      return {
        content:'',
        html:'',
        title: '',
      }
    },
    methods: {
      // 将图片上传到服务器，返回地址替换到md中
      $imgAdd(pos, $file){
        let formdata = new FormData();
        this.$upload.post('/上传接口地址', formdata).then(res => {
          console.log(res.data);
          this.$refs.md.$img2Url(pos, res.data);
        }).catch(err => {
          console.log(err)
        })
      },
      // 所有操作都会被解析重新渲染
      change(value, render){
        // render 为 markdown 解析后的结果[html]
        this.html = render;
      },
      // 提交
      submit(){
        console.log(this.content);
        console.log(this.html);
        this.$confirm('是否保存并发布动态?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post('submitCondition', {
                title: this.title,
                content: this.html,
              }).then(resp => {
              if (resp && resp.status === 200) {
                this.content = '';
                this.title = '';
                this.html = '';
                this.$message({
                  type: 'info',
                  message: '发布成功'
                })
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发布'
          })
        })

      },

    },
    mounted() {

    }
  }
</script>

<style scoped>

</style>
