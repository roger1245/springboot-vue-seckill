
<template>
  <div id="details">
    
    <div class="page-header">
      <div class="title">
        <p>{{productDetails.product_name}}</p>
        <div class="list">
          <ul>
            <li>
              <router-link to>概述</router-link>
            </li>
            <li>
              <router-link to>参数</router-link>
            </li>
            <li>
              <router-link to>用户评价</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
    

    
    <div class="main">
      
      <div class="block">
        <el-carousel height="560px" v-if="productPicture.length>1">
          <el-carousel-item v-for="item in productPicture" :key="item.id">
            <img class="img_detail" style="height:560px;" :src="$target + item.product_picture" :alt="item.intro" />
          </el-carousel-item>
        </el-carousel>
        <div  v-if="productPicture.length==1">
          <img class="img_detail"
            style="height:560px;"
            :src="$target + productPicture[0].product_picture"
            :alt="productPicture[0].intro"
          />
        </div>
      </div>
      

      
      <div class="content">
        <h1 class="name">{{productDetails.product_name}}</h1>
        <p class="intro">{{productDetails.product_intro}}</p>
        <p class="store">小米自营</p>
        <div class="price">
          <span>{{productDetails.product_selling_price}}元</span>
          <span
            v-show="productDetails.product_price != productDetails.product_selling_price"
            class="del"
          >{{productDetails.product_price}}元</span>
        </div>
        <div class="pro-list">
          <span class="pro-name">{{productDetails.product_name}}</span>
          <span class="pro-price">
            <span
              v-show="productDetails.product_price != productDetails.product_selling_price"
              class="pro-del"
            >{{productDetails.product_price}}元</span>
          </span>
          <p class = "price-sum" style="line-height: 1.5rem;" v-html="secCountdown"></p>
          <p class="price-sum" v-if="!isSeckillProduct">总计 : {{productDetails.product_selling_price}}元</p>
          <p class="price-sum" v-if="isSeckillProduct">￥{{seckillProductDetail.seckill_price}}  <s>￥{{productDetails.product_selling_price}}</s></p>
        </div>
        
        <div class="button">
          <el-button class="shop-cart" :disabled="dis" @click="addShoppingCart">加入购物车</el-button>
          <el-button class="seckill" v-if="isSeckillProduct" @click="seckill">马上抢</el-button>
          <el-button class="seckill" v-if="!isSeckillProduct" @click="normalBuy">购买</el-button>
          <el-button class="like" @click="addCollect">喜欢</el-button>
          
        </div>
        
        <div class="pro-policy">
          <ul>
            <li>
              <i class="el-icon-circle-check"></i> 小米自营
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 小米发货
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 7天无理由退货
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 7天价格保护
            </li>
          </ul>
        </div>
      </div>
      
    </div>
    
  </div>
</template>
<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      dis: false, // 控制“加入购物车按钮是否可用”
      productID: "", // 商品id
      productDetails: "", // 商品详细信息
      productPicture: "", // 商品图片
      isSeckillProduct: false, //是否是秒杀商品
      seckillProductDetail: "", //如果是秒杀商品的话，那么则为秒杀商品详细信息
      secCountdown: '', //秒杀倒计时
      tickId: undefined,
    };
  },
  // 通过路由获取商品id,同时检测是否是秒杀商品
  activated() {
    if (this.$route.query.productID != undefined) {
      this.productID = this.$route.query.productID;
      this.$axios.get("secproduct/" + this.productID)
      .then(res => {
          if (res.data.msg === "exist") {
            this.isSeckillProduct = true;
            this.seckillProductDetail = res.data.data;
          } else {
            this.isSeckillProduct = false;
            this.seckillProductDetail = "";
          }
        })
        .catch(err => {
          return Promise.reject(err);
        });
    }
  },
  watch: {
    // 监听商品id的变化，请求后端获取商品数据
    productID: function(val) {
      this.getDetails(val);
      this.getDetailsPicture(val);
    },
    seckillProductDetail: function() {
      debugger
      if (this.seckillProductDetail) {
          this.$nextTick(() => {
            this.tickId = this.tick();
        })
      } else if (this.tickId) {
        this.secCountdown = "";
        clearInterval(this.tickId);
      }
    }
  },
  methods: {
    ...mapActions(["unshiftShoppingCart", "addShoppingCartNum", "refreshShoppingCart"]),
    // 获取商品详细信息
    getDetails(val) {
      this.$axios
        .post("/good/productDetail", {
          product_id: val
        })
        .then(res => {
          this.productDetails = res.data.data;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    },
    // 获取商品图片
    getDetailsPicture(val) {
      this.$axios
        .post("/good/productPicture", {
          product_id: val
        })
        .then(res => {
          this.productPicture = res.data.data;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    },
    // 加入购物车
    addShoppingCart() {
      // 判断是否登录,没有登录则显示登录组件
      if (!this.$store.getters.getUser) {
        this.$store.dispatch("setShowLogin", true);
        return;
      }
      let type = this.isSeckillProduct ? 2 : 0;
      const config = {
          headers: { Authorization: `Bearer ${this.$store.getters.getToken}` }
      };
      const userId = this.$store.getters.getUser.id;
      this.$axios
        .post("/shoppingcart/add", {
          userId: userId,
          productId: this.productID,
          type: type,
        }, config)
        .then(res => {
          switch (res.data.code) {
            case 200:
              this.refreshShoppingCart({
                token: this.$store.getters.getToken,
                user_id: userId
              });
              this.notifySucceed(res.data.msg);
              break;
            default:
              this.notifyError(res.data.msg);
          }
        })
        .catch(err => {
          return Promise.reject(err);
        });
    },
    addCollect() {
      // 判断是否登录,没有登录则显示登录组件
      if (!this.$store.getters.getUser) {
        this.$store.dispatch("setShowLogin", true);
        return;
      }
      this.$axios
        .post("/api/user/collect/addCollect", {
          user_id: this.$store.getters.getUser.user_id,
          product_id: this.productID
        })
        .then(res => {
          if (res.data.code ==  200) {
            // 添加收藏成功
            this.notifySucceed(res.data.msg);
          } else {
            // 添加收藏失败
            this.notifyError(res.data.msg);
          }
        })
        .catch(err => {
          return Promise.reject(err);
        });
    },
    seckill() {
      // 判断是否登录,没有登录则显示登录组件
      if (!this.$store.getters.getUser) {
        this.$store.dispatch("setShowLogin", true);
        return;
      }
      this.$router.push({ name: "ConfirmOrder", params: { 
        from: "Details",
        productNum: 1,
        originPrice: 1 * this.productDetails.product_selling_price,
        coupon: this.productDetails.product_selling_price - this.seckillProductDetail.seckill_price,
        finalPrice: this.seckillProductDetail.seckill_price,
        productName: this.productDetails.product_name,
        productImg: this.productDetails.product_picture,
        productId: this.productDetails.product_id,
         } });
    },
    normalBuy() {
      // 判断是否登录,没有登录则显示登录组件
      if (!this.$store.getters.getUser) {
        this.$store.dispatch("setShowLogin", true);
        return;
      }
      this.$router.push({ name: "ConfirmOrder", params: { 
        from: "Details",
        type: "normal",
        productNum: 1,
        originPrice: 1 * this.productDetails.product_selling_price,
        coupon: this.productDetails.product_selling_price - this.productDetails.product_price,
        finalPrice: this.productDetails.product_selling_price,
        productName: this.productDetails.product_name,
        productImg: this.productDetails.product_picture,
        productId: this.productDetails.product_id,
         } });
    },
    // 获得距离活动结束剩余的时间
    tick () {
      let timestamp = Math.abs(Date.parse(this.seckillProductDetail.end_date) - new Date());
      let remain = new Date(timestamp);
      const oneDay = 1000 * 60 * 60 * 24;
      let days = Math.round(remain.getTime() / oneDay);
      const str = `限时秒杀：<span>${days}</span>天<span>${remain.getHours()}</span>时<span>${remain.getMinutes()}</span>分<span>${remain.getSeconds()}</span>秒`
      this.secCountdown = str;
      var TimeDown = setInterval(() => {
        if (timestamp > 0) {
          timestamp -= 1000;
          let remain = new Date(timestamp);
          const oneDay = 1000 * 60 * 60 * 24;
          let days = Math.round(remain.getTime() / oneDay);
          const str = `限时秒杀：<span>${days}</span>天<span>${remain.getHours()}</span>时<span>${remain.getMinutes()}</span>分<span>${remain.getSeconds()}</span>秒`
          this.secCountdown = str;
        } else {
          this.secCountdown = '活动已结束';
          clearInterval(TimeDown);
        }
      }, 1000)
      return TimeDown;
    }
  }
};
</script>
<style>
/* 头部CSS */
#details .page-header {
  height: 64px;
  margin-top: -20px;
  z-index: 4;
  background: #fff;
  border-bottom: 1px solid #e0e0e0;
  -webkit-box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
  box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
}
#details .page-header .title {
  width: 1225px;
  height: 64px;
  line-height: 64px;
  font-size: 18px;
  font-weight: 400;
  color: #212121;
  margin: 0 auto;
}
#details .page-header .title p {
  float: left;
}
#details .page-header .title .list {
  height: 64px;
  float: right;
}
#details .page-header .title .list li {
  float: left;
  margin-left: 20px;
}
#details .page-header .title .list li a {
  font-size: 14px;
  color: #616161;
}
#details .page-header .title .list li a:hover {
  font-size: 14px;
  color: #ff6700;
}
/* 头部CSS END */

/* 主要内容CSS */
#details .main {
  width: 1225px;
  height: 560px;
  padding-top: 30px;
  margin: 0 auto;
}
#details .main .block {
  float: left;
  width: 560px;
  height: 560px;
}
#details .el-carousel .el-carousel__indicator .el-carousel__button {
  background-color: rgba(163, 163, 163, 0.8);
}
#details .main .content {
  float: left;
  margin-left: 25px;
  width: 640px;
}
#details .main .content .name {
  height: 30px;
  line-height: 30px;
  font-size: 24px;
  font-weight: normal;
  color: #212121;
}
#details .main .content .intro {
  color: #b0b0b0;
  padding-top: 10px;
}
#details .main .content .store {
  color: #ff6700;
  padding-top: 10px;
}
#details .main .content .price {
  display: block;
  font-size: 18px;
  color: #ff6700;
  border-bottom: 1px solid #e0e0e0;
  padding: 25px 0 25px;
}
#details .main .content .price .del {
  font-size: 14px;
  margin-left: 10px;
  color: #b0b0b0;
  text-decoration: line-through;
}
#details .main .content .pro-list {
  background: #f9f9fa;
  padding: 30px 60px;
  margin: 50px 0 50px;
}
#details .main .content .pro-list span {
  line-height: 30px;
  color: #616161;
}
#details .main .content .pro-list .pro-price {
  float: right;
}
#details .main .content .pro-list .pro-price .pro-del {
  margin-left: 10px;
  text-decoration: line-through;
}
#details .main .content .pro-list .price-sum {
  color: #ff6700;
  font-size: 24px;
  padding-top: 20px;
}
#details .main .content .button {
  height: 55px;
  margin: 10px 0 20px 0;
}
#details .main .content .button .el-button {
  float: left;
  height: 55px;
  font-size: 16px;
  color: #fff;
  border: none;
  text-align: center;
}
.img_detail {
  background-color: #ffffff;
}
#details .main .content .button .shop-cart {
  width: 340px;
  background-color: #ff6700;
}
#details .main .content .button .shop-cart:hover {
  background-color: #f25807;
}

#details .main .content .button .seckill {
  width: 260px;
  margin-left: 40px;
  background-color: #b0b0b0;
}
#details .main .content .button .seckill:hover {
  background-color: #757575;
}
#details .main .content .pro-policy li {
  float: left;
  margin-right: 20px;
  color: #b0b0b0;
}
/* 主要内容CSS END */
</style>