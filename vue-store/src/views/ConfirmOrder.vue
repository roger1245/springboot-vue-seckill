<template>
  <div class="confirmOrder">
    <div class="confirmOrder-header">
      <div class="header-content">
        <p>
          <i class="el-icon-s-order"></i>
        </p>
        <p>确认订单</p>
        <router-link to></router-link>
      </div>
    </div>

    <div class="content">
      <div class="section-address">
        <p class="title">收货地址</p>
        <div class="address-body">
          <ul>
            <li
              :class="item.id == confirmAddress ? 'in-section' : ''"
              v-for="item in address"
              :key="item.id"
            >
              <h2>{{ item.name }}</h2>
              <p class="phone">{{ item.phone }}</p>
              <p class="address">{{ item.address }}</p>
            </li>
            <li class="add-address">
              <i class="el-icon-circle-plus-outline"></i>
              <p>添加新地址</p>
            </li>
          </ul>
        </div>
      </div>

      <div class="section-goods">
        <p class="title">商品及优惠券</p>
        <div class="goods-list">
          <ul>
            <li v-for="item in getGood()" :key="item.id">
              <img :src="$target + item.productImg" />
              <span class="pro-name">{{ item.productName }}</span>
              <span class="pro-price">{{ item.price }}元 x {{ item.num }}</span>
              <span class="pro-status"></span>
              <span class="pro-total">{{ item.price * item.num }}元</span>
            </li>
          </ul>
        </div>
      </div>

      <div class="section-shipment">
        <p class="title">配送方式</p>
        <p class="shipment">包邮</p>
      </div>

      <div class="section-invoice">
        <p class="title">发票</p>
        <p class="invoice">电子发票</p>
        <p class="invoice">个人</p>
        <p class="invoice">商品明细</p>
      </div>

      <div class="section-count">
        <div class="money-box">
          <ul>
            <li>
              <span class="title">商品件数：</span>
              <span class="value">{{ this.$route.params.productNum }}件</span>
            </li>
            <li>
              <span class="title">商品总价：</span>
              <span class="value">{{ this.$route.params.originPrice }}元</span>
            </li>
            <li>
              <span class="title">活动优惠：</span>
              <span class="value">-{{ this.$route.params.coupon }}元</span>
            </li>
            <li>
              <span class="title">优惠券抵扣：</span>
              <span class="value">-0元</span>
            </li>
            <li>
              <span class="title">运费：</span>
              <span class="value">0元</span>
            </li>
            <li class="total">
              <span class="title">应付总额：</span>
              <span class="value">
                <span class="total-price">{{
                  this.$route.params.finalPrice
                }}</span
                >元
              </span>
            </li>
          </ul>
        </div>
      </div>

      <div class="section-bar">
        <div class="btn">
          <router-link to="/shoppingCart" class="btn-base btn-return"
            >返回购物车</router-link
          >
          <a
            href="javascript:void(0);"
            @click="addOrder"
            class="btn-base btn-primary"
            >结算</a
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mapActions } from "vuex";
export default {
  name: "",
  data() {
    return {
      // 虚拟数据
      confirmAddress: 1, // 选择的地址id
      // 地址列表
      address: [
        {
          id: 1,
          name: "罗同学",
          phone: "123456789000",
          address: "重庆市 南岸区 崇文路2号",
        },
        {
          id: 2,
          name: "罗同学",
          phone: "123456789000",
          address: "重庆市 南岸区 崇文路2号",
        },
      ],
      from: "",
      type: "",
    };
  },
  computed: {
    // 结算的商品数量; 结算商品总计; 结算商品信息
    ...mapGetters(["getCheckNum", "getTotalPrice", "getCheckGoods"]),
  },
  methods: {
    ...mapActions([
      "deleteShoppingCart",
      "setShoppingCart",
      "refreshShoppingCart",
    ]),
    getGood() {
      this.from = this.$route.params.from;
      this.type = this.$route.params.type;
      if (this.from === "ShoppingCart") {
        return this.$store.state.shoppingCart.shoppingCart.map(function (item) {
          let ret = {};
          ret.productId = item.product_id;
          ret.productImg = item.product_img;
          ret.productName = item.product_name;
          ret.price = item.price;
          ret.num = item.num;
          ret.userId = item.user_id;
          ret.check = item.check;
          ret.id = item.id;
          return ret;
        });
      } else {
        return [
          {
            productId: this.$route.params.productId,
            productImg: this.$route.params.productImg,
            productName: this.$route.params.productName,
            price: this.$route.params.finalPrice,
            num: this.$route.params.productNum,
          },
        ];
      }
    },
    addOrder() {
      const config = {
        headers: { Authorization: `Bearer ${this.$store.getters.getToken}` },
      };
      const userId = this.$store.getters.getUser.id;
      if (this.from === "ShoppingCart") {
        let body = this.getGood().map(function (item) {
          let ret = {};
          ret.product_id = item.productId;
          ret.product_price = item.price;
          ret.product_num = item.num;
          ret.user_id = item.userId;
          ret.check = item.check;
          ret.id = item.id;
          return ret;
        });
        this.$axios
          .post(
            this.$target + "order/insertList",
            {
              list: body,
            },
            config
          )
          .then((res) => {
            let ids = body
              .filter((item) => item.check == true)
              .map(function (item) {
                return item.id;
              });
            if (res.data.code == 200) {
              return this.$axios.post(
                this.$target + "shoppingcart/deleteListById",
                {
                  list: ids,
                },
                config
              );
            } else {
              this.notifyError(res.data.msg);
            }
          })
          .then((res) => {
            if (res.data.code == 200) {
              this.refreshShoppingCart({
                token: this.$store.getters.getToken,
                user_id: this.$store.getters.getUser.id,
              });
              this.notifySucceed(res.data.msg);
              setTimeout(() => {
                this.$router.push({ path: "/order" });
              }, 2000);
            } else {
              this.notifyError(res.data.msg);
            }
          })
          .catch((err) => {
            return Promise.reject(err);
          });
      } else if (this.type === "normal") {
        let body = this.getGood().map(function (item) {
          let ret = {};
          ret.product_id = item.productId;
          ret.product_price = item.price;
          ret.product_num = item.num;
          ret.user_id = userId;
          return ret;
        });
        this.$axios
          .post(
            this.$target + "order/insertList",
            {
              list: body,
            },
            config
          )
          .then((res) => {
            if (res.data.code == 200) {
              this.notifySucceed(res.data.msg);
              setTimeout(() => {
                this.$router.push({ path: "/order" });
              }, 2000);
            } else {
              this.notifyError(res.data.msg);
            }
          })
          .catch((err) => {
            return Promise.reject(err);
          });
      } else {
        this.$axios
          .get(this.$target + "exposer/" + this.$route.params.productId, config)
          .then((res) => {
            if (res.data.code == 200) {
              const md5 = res.data.data.md5;
              const seckillId = res.data.data.seckillId;
              return this.$axios.post(
                this.$target + "seckill",
                {
                  secId: seckillId,
                  userId: this.$store.getters.getUser.id,
                  md5: md5,
                  productId: this.$route.params.productId,
                  productNum: this.$route.params.productNum,
                  productPrice: this.$route.params.finalPrice,
                },
                config
              );
            } else {
              this.notifyError(res.data.msg);
            }
          })
          .then((res) => {
            if (res.data.code == 200) {
              this.notifySucceed(res.data.msg);
              setTimeout(() => {
                this.$router.push({
                  path: "/goods/details",
                  query: { productID: this.$route.params.productId },
                });
              }, 2000);
            } else {
              this.notifyError(res.data.msg);
            }
          })
          .catch((err) => {
            return Promise.reject(err);
          });
      }
    },
  },
};
</script>
<style scoped>
.confirmOrder {
  background-color: #f5f5f5;
  padding-bottom: 20px;
}

.confirmOrder .confirmOrder-header {
  background-color: #fff;
  border-bottom: 2px solid #ff6700;
  margin-bottom: 20px;
}
.confirmOrder .confirmOrder-header .header-content {
  width: 1225px;
  margin: 0 auto;
  height: 80px;
}
.confirmOrder .confirmOrder-header .header-content p {
  float: left;
  font-size: 28px;
  line-height: 80px;
  color: #424242;
  margin-right: 20px;
}
.confirmOrder .confirmOrder-header .header-content p i {
  font-size: 45px;
  color: #ff6700;
  line-height: 80px;
}

.confirmOrder .content {
  width: 1225px;
  margin: 0 auto;
  padding: 48px 0 0;
  background-color: #fff;
}

.confirmOrder .content .section-address {
  margin: 0 48px;
  overflow: hidden;
}
.confirmOrder .content .section-address .title {
  color: #333;
  font-size: 18px;
  line-height: 20px;
  margin-bottom: 20px;
}
.confirmOrder .content .address-body li {
  float: left;
  color: #333;
  width: 220px;
  height: 178px;
  border: 1px solid #e0e0e0;
  padding: 15px 24px 0;
  margin-right: 17px;
  margin-bottom: 24px;
}
.confirmOrder .content .address-body .in-section {
  border: 1px solid #ff6700;
}
.confirmOrder .content .address-body li h2 {
  font-size: 18px;
  font-weight: normal;
  line-height: 30px;
  margin-bottom: 10px;
}
.confirmOrder .content .address-body li p {
  font-size: 14px;
  color: #757575;
}
.confirmOrder .content .address-body li .address {
  padding: 10px 0;
  max-width: 180px;
  max-height: 88px;
  line-height: 22px;
  overflow: hidden;
}
.confirmOrder .content .address-body .add-address {
  text-align: center;
  line-height: 30px;
}
.confirmOrder .content .address-body .add-address i {
  font-size: 30px;
  padding-top: 50px;
  text-align: center;
}

.confirmOrder .content .section-goods {
  margin: 0 48px;
}
.confirmOrder .content .section-goods p.title {
  color: #333;
  font-size: 18px;
  line-height: 40px;
}
.confirmOrder .content .section-goods .goods-list {
  padding: 5px 0;
  border-top: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
}
.confirmOrder .content .section-goods .goods-list li {
  padding: 10px 0;
  color: #424242;
  overflow: hidden;
}
.confirmOrder .content .section-goods .goods-list li img {
  float: left;
  width: 30px;
  height: 30px;
  margin-right: 10px;
}
.confirmOrder .content .section-goods .goods-list li .pro-name {
  float: left;
  width: 650px;
  line-height: 30px;
}
.confirmOrder .content .section-goods .goods-list li .pro-price {
  float: left;
  width: 150px;
  text-align: center;
  line-height: 30px;
}
.confirmOrder .content .section-goods .goods-list li .pro-status {
  float: left;
  width: 99px;
  height: 30px;
  text-align: center;
  line-height: 30px;
}
.confirmOrder .content .section-goods .goods-list li .pro-total {
  float: left;
  width: 190px;
  text-align: center;
  color: #ff6700;
  line-height: 30px;
}

.confirmOrder .content .section-shipment {
  margin: 0 48px;
  padding: 25px 0;
  border-bottom: 1px solid #e0e0e0;
  overflow: hidden;
}
.confirmOrder .content .section-shipment .title {
  float: left;
  width: 150px;
  color: #333;
  font-size: 18px;
  line-height: 38px;
}
.confirmOrder .content .section-shipment .shipment {
  float: left;
  line-height: 38px;
  font-size: 14px;
  color: #ff6700;
}

.confirmOrder .content .section-invoice {
  margin: 0 48px;
  padding: 25px 0;
  border-bottom: 1px solid #e0e0e0;
  overflow: hidden;
}
.confirmOrder .content .section-invoice .title {
  float: left;
  width: 150px;
  color: #333;
  font-size: 18px;
  line-height: 38px;
}
.confirmOrder .content .section-invoice .invoice {
  float: left;
  line-height: 38px;
  font-size: 14px;
  margin-right: 20px;
  color: #ff6700;
}

.confirmOrder .content .section-count {
  margin: 0 48px;
  padding: 20px 0;
  overflow: hidden;
}
.confirmOrder .content .section-count .money-box {
  float: right;
  text-align: right;
}
.confirmOrder .content .section-count .money-box .title {
  float: left;
  width: 126px;
  height: 30px;
  display: block;
  line-height: 30px;
  color: #757575;
}
.confirmOrder .content .section-count .money-box .value {
  float: left;
  min-width: 105px;
  height: 30px;
  display: block;
  line-height: 30px;
  color: #ff6700;
}
.confirmOrder .content .section-count .money-box .total .title {
  padding-top: 15px;
}
.confirmOrder .content .section-count .money-box .total .value {
  padding-top: 10px;
}
.confirmOrder .content .section-count .money-box .total-price {
  font-size: 30px;
}

.confirmOrder .content .section-bar {
  padding: 20px 48px;
  border-top: 2px solid #f5f5f5;
  overflow: hidden;
}
.confirmOrder .content .section-bar .btn {
  float: right;
}
.confirmOrder .content .section-bar .btn .btn-base {
  float: left;
  margin-left: 30px;
  width: 158px;
  height: 38px;
  border: 1px solid #b0b0b0;
  font-size: 14px;
  line-height: 38px;
  text-align: center;
}
.confirmOrder .content .section-bar .btn .btn-return {
  color: rgba(0, 0, 0, 0.27);
  border-color: rgba(0, 0, 0, 0.27);
}
.confirmOrder .content .section-bar .btn .btn-primary {
  background: #ff6700;
  border-color: #ff6700;
  color: #fff;
}
</style>
