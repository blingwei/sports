<template>
  <div>
    <div class="body">
      <el-card>
        <h1>{{condition.title}}</h1>

        <div class="content" v-html="condition.content"></div>
      </el-card>
      <comment ref="childComment"></comment>
    </div>
    <div class="badge">
      <el-badge :value="commentNum" :max="99" class="item">
        <el-button size="small" @click="isComment">评论</el-button>
      </el-badge>
    </div>
    <comment-item ref="commentItem"></comment-item>
    <div></div>
  </div>
</template>

<script>
  import Comment from "../comment/Comment";
  import CommentItem from "../comment/CommentItem";

  export default {
    name: "ConditionDisplay",
    components: {Comment, CommentItem},
    data() {
      return {
        comment: {
          conditionId: this.$route.query.id,
          pid: '0',
          replyId: '0',
          content: "",
        },
        commentNum: 0,
        condition: {}
      }
    },
    methods: {
      initCondition() {
        this.$axios.get("getConditionById", {
          params: {
            id: this.$route.query.id
          }
        }).then(res => {
          if (res && res.status === 200) {
            this.condition = res.data.data
          }
        })
      },
      //评论
      isComment() {
        this.$refs.commentItem.dialogVisible = true;
      },
      initComment() {
        this.$refs.childComment.initComment();
      },
      reset() {
        this.comment.pid = "0";
        this.comment.replyId = "0";
        this.comment.content = "";
      },
    },

    mounted() {
      this.initCondition();
      this.initComment();

    },
  }
</script>

<style scoped>
  .body {
    width: 80%;
    margin-left: 10%;
  }

  .content {
    border-top: 1px solid #eee;
    margin-top: 20px;
    text-align: left;
    min-height: 500px;
    width: 90%;
  }

  .badge {
    position: fixed;
    width: 2%;
    height: 200px;
    top: 120px;
    left: 90%;
  }

  .item {
    width: 70px;
    height: 40px;
  }
</style>
