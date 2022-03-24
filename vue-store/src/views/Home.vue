
<template>
  <div class="home" id="home" name="home">
    
    <div class="block">
      <el-carousel height="460px">
        <el-carousel-item v-for="item in carousel" :key="item.carousel_id">
          <img style="height:460px;" :src="item.imgPath" :alt="item.describes" />
        </el-carousel-item>
      </el-carousel>
    </div>
    
    <div class="main-box">
      <div class="main">
        
        <div class="phone">
          <div class="box-hd">
            <div class="title">手机</div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <router-link to>
                <img :src="$target +'public/imgs/phone.webp'" />
              </router-link>
            </div>
            <div class="list">
              <MyList :list="phoneList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        
        
        
        <div class="appliance" id="promo-menu">
          <div class="box-hd">
            <div class="title">家电</div>
            <div class="more" id="more">
              <MyMenu :val="2" @fromChild="getChildMsg">
                <span slot="1">热门</span>
                <span slot="2">电视影音</span>
              </MyMenu>
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <ul>
                <li>
                  <img :src="$target +'public/imgs/appliance-promo1.webp'" />
                </li>
                <li>
                  <img :src="$target +'public/imgs/appliance-promo2.webp'" />
                </li>
              </ul>
            </div>
            <div class="list">
              <MyList :list="applianceList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        

        
        <div class="accessory" id="promo-menu">
          <div class="box-hd">
            <div class="title">智能穿戴</div>
            <div class="more" id="more">
              <MyMenu :val="3" @fromChild="getChildMsg2">
                <span slot="1">热门</span>
                <span slot="2">耳机</span>
                <span slot="3">手表</span>
              </MyMenu>
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <router-link to>
                <img :src="$target +'public/imgs/wearing.webp'" />
              </router-link>
            </div>
            <div class="list">
              <MyList :list="wearingShowingList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      carousel: "", // 轮播图数据
      phoneList: "", // 手机商品列表
      miTvList: "", // 小米电视商品列表
      applianceList: "", // 家电商品列表
      applianceHotList: "", //热门家电商品列表
      wearingShowingList: "",
      hotWearingList: "", //热门穿戴商品列表
      earList: "", //耳机商品列表
      watchList: "", // 手表商品列表
      applianceActive: 1, // 家电当前选中的商品分类
      wearingActive: 1 // 配件当前选中的商品分类
    };
  },
  watch: {
    // 家电当前选中的商品分类，响应不同的商品数据
    applianceActive: function(val) {
      // 页面初始化的时候把applianceHotList(热门家电商品列表)直接赋值给applianceList(家电商品列表)
      // 所以在切换商品列表时判断applianceHotList是否为空,为空则是第一次切换,把applianceList赋值给applianceHotList
      if (this.applianceHotList == "") {
        this.applianceHotList = this.applianceList;
      }
      if (val == 1) {
        // 1为热门商品
        this.applianceList = this.applianceHotList;
        return;
      }
      if (val == 2) {
        // 2为电视商品
        this.applianceList = this.miTvList;
        return;
      }
    },
    wearingActive: function(val) {
      if (this.hotWearingList == "") {
        this.hotWearingList = this.wearingShowingList;
      }
      if (val == 1) {
        // 1为热门商品
        this.wearingShowingList = this.hotWearingList;
        return;
      }
      if (val == 2) {
        // 2为ear
        this.wearingShowingList = this.earList;
        return;
      }
      if (val == 3) {
        //3 为watchList
        this.wearingShowingList = this.watchList;
        return;
      }
    }
  },
  created() {
    // 获取轮播图数据
    this.$axios
      .post("/resources/carousel", {})
      .then(res => {
        this.carousel = res.data.data;
      })
      .catch(err => {
        return Promise.reject(err);
      });
    // 获取各类商品数据
    this.getProductList(["1"], "phoneList");
    this.getProductList(["4"], "miTvList");
    this.getProductList(["6"], "watchList");
    this.getProductList(["5"], "earList");
    this.getProductList(
      ["3", "4"],
      "applianceList",
      "/good/getHotProduct"
    );
    this.getPromo(
      ["5", "6"],
      "wearingShowingList",
      "/good/getHotProduct"
    );
  },
  methods: {
    // 获取家电模块子组件传过来的数据
    getChildMsg(val) {
      this.applianceActive = val;
    },
    // 获取配件模块子组件传过来的数据
    getChildMsg2(val) {
      this.wearingActive = val;
    },
    // 获取各类商品数据方法封装
    getPromo(categoryId, val, api) {
      api = api != undefined ? api : "/good/getProduct";
      this.$axios
        .post(api, {
          "category_ids": categoryId,
        })
        .then(res => {
          this[val] = res.data.data;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    },
    getProductList(categoryIds, val) {
      const api = "/good/getProduct";
      this.$axios.post(api, {
        "category_ids": categoryIds,
      })
      .then(res => {
          this[val] = res.data.data.product_list;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    }
  }
};
</script>
<style scoped>
@import "../assets/css/index.css";
</style>