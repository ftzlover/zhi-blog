<template>
  <div class="app-container home">
    <el-row :gutter="20">
      websocket推送
      <WebSocket></WebSocket>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
import WebSocket from "@/components/WebSocket/WebSocket";
export default {
  name: "index",
  components: {
    WebSocket
  },


};
</script>
<style lang="css" scoped>

</style>
