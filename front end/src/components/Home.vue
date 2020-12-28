<template>
    <div class="all">
        <el-button type="text" style="margin-left: 10px" @click="userInfo"
            >用户信息</el-button
        >
        <el-button type="text" @click="logout">退出登录</el-button>

        <img
            class="controlLing"
            src="../assets/control.png"
            alt="凌志西控制柜"
            @click="controlLing = true"
        />
        <el-dialog :visible.sync="controlLing" width="1350px" top="5vh">
            <img src="../assets/lingMachine.jpg" alt="凌志西道口设备布置图" />
        </el-dialog>

        <img
            class="controlVillage"
            src="../assets/control.png"
            alt="石头村控制柜"
            @click="controlVillage = true"
        />
        <el-dialog :visible.sync="controlVillage" width="1350px" top="5vh">
            <img src="../assets/villageMachine.jpg" alt="石头村道口设备布置图" />
        </el-dialog>

        <img
            class="controlOil"
            src="../assets/control.png"
            alt="石头油库控制柜"
            @click="controlOil = true"
        />
        <el-dialog :visible.sync="controlOil" width="1350px" top="5vh">
            <img src="../assets/oilMachine.jpg" alt="石头油库道口设备布置图" />
        </el-dialog>

        <div class="lingMode">
            <el-tag effect="plain"> 凌志西 </el-tag>
            <br />
            <el-button
                type="primary"
                :loading="operatingLing"
                @click="clickLingMode"
                >{{ lingMode }}</el-button
            >
        </div>

        <div class="villageMode">
            <el-tag effect="plain"> 石头村 </el-tag>
            <br />
            <el-button
                type="primary"
                :loading="operatingVillage"
                @click="clickVillageMode"
                >{{ villageMode }}</el-button
            >
        </div>

        <div class="oilMode">
            <el-tag effect="plain"> 石头油库 </el-tag>
            <br />
            <el-button
                type="primary"
                :loading="operatingOil"
                @click="clickOilMode"
                >{{ oilMode }}</el-button
            >
        </div>

        <div class="lingUp" :style="{ backgroundImage: 'url(' + lingUpUrl + ')' }"></div>
        <div class="lingDown" :style="{ backgroundImage: 'url(' + lingDownUrl + ')' }"></div>

        <div class="oilUp" :style="{ backgroundImage: 'url(' + oilUpUrl + ')' }"></div>
        <div class="oilDown" :style="{ backgroundImage: 'url(' + oilDownUrl + ')' }"></div>

        <div class="villageLeft" :style="{ backgroundImage: 'url(' + villageLeftUrl + ')' }"></div>
        <div class="villageRight" :style="{ backgroundImage: 'url(' + villageRightUrl + ')' }"></div>

        <div class="lingLeftLight" :style="{ backgroundColor: lingLeftLightColor }"></div>
        <div class="lingRightLight" :style="{ backgroundColor: lingRightLightColor }"></div>
        <div class="lingUpLight" :style="{ backgroundColor: lingUpLightColor }"></div>
        <div class="lingDownLight" :style="{ backgroundColor: lingDownLightColor }"></div>

        <div class="villageLeftLight" :style="{ backgroundColor: villageLeftLightColor }"></div>
        <div class="villageRightLight" :style="{ backgroundColor: villageRightLightColor }"></div>
        <div class="villageUpLight" :style="{ backgroundColor: villageUpLightColor }"></div>
        <div class="villageDownLight" :style="{ backgroundColor: villageDownLightColor }"></div>

        <div class="oilLeftLight" :style="{ backgroundColor: oilLeftLightColor }"></div>
        <div class="oilRightLight" :style="{ backgroundColor: oilRightLightColor }"></div>
        <div class="oilUpLight" :style="{ backgroundColor: oilUpLightColor }"></div>
        <div class="oilDownLight" :style="{ backgroundColor: oilDownLightColor }"></div>

        <div class="train" @click="trainInfo">
            <div :style="{ color: trainConnect }">WIFI</div>
            <img src="../assets/train.png" alt="火车" />
            <div class="trainNumber">
                {{ trainNumber }}
            </div>
        </div>

        <div class="linkStates">
            <div
                v-for="linkState in linkStates"
                :key="linkState.label"
                class="linkState"
            >
                <img
                    :style="{ backgroundColor: linkState.state }"
                    src="../assets/wifi.png"
                    alt="wifi"
                />
                <div :style="{ color: linkState.state }">
                    {{ linkState.label }}
                </div>
            </div>
        </div>

        <div class="alarmBox">
            <span class="alarmBoxTitle">报警消息</span>
            <el-select
                v-model="selectedOption"
                size="mini"
                @change="changeAlarm($event)"
            >
                <el-option
                    v-for="item in options"
                    :key="item.key"
                    :label="item.label"
                    :value="item.key"
                >
                </el-option>
            </el-select>

            <div v-show="showRFIDAlarm">
                <el-divider>RFID</el-divider>
                <ul>
                    <li v-for="item in rfidAlarms" :key="item">
                        {{ item }}
                    </li>
                </ul>
            </div>

            <div v-show="showLingAlarm">
                <el-divider>凌志西</el-divider>
                {{ lingAlarm }}
            </div>

            <div v-show="showVillageAlarm">
                <el-divider>石头村</el-divider>
                {{ villageAlarm }}
            </div>

            <div v-show="showOilAlarm">
                <el-divider>石头油库</el-divider>
                {{ oilAlarm }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Home",
    data() {
        return {
            userName: "",
            loginTime: null,

            controlVillage: false,
            controlLing: false,
            controlOil: false,

            operatingLing: false,
            operatingVillage: false,
            operatingOil: false,

            lingMode: null,
            villageMode: null,
            oilMode: null,

            lingUpUrl:require("../assets/yellowLing.png"),
            lingDownUrl:require("../assets/yellowLing.png"),

            oilUpUrl:require("../assets/yellowOil.png"),
            oilDownUrl:require("../assets/yellowOil.png"),

            villageLeftUrl:require("../assets/yellowVillage.png"),
            villageRightUrl:require("../assets/yellowVillage.png"),

            lingLeftLightColor:"red",
            lingRightLightColor:"green",
            lingUpLightColor:"red",
            lingDownLightColor:"green",

            villageLeftLightColor:"red",
            villageRightLightColor:"green",
            villageUpLightColor:"red",
            villageDownLightColor:"green",

            oilLeftLightColor:"red",
            oilRightLightColor:"green",
            oilUpLightColor:"red",
            oilDownLightColor:"green",

            lingUpState:"yellow",
            lingDownState:"yellow",

            villageUpState:"yellow",
            villageDownState:"yellow",

            oilUpState:"yellow",
            oilDownState:"yellow",

            trainName: null,
            trainNumber: null,
            trainConnect: null,

            linkStates: [
                { state: "red", label: "石头村" },
                { state: "red", label: "凌志西" },
                { state: "red", label: "石头油库" },
                { state: "green", label: "数据库" },
            ],

            options: [
                {
                    key: "option1",
                    label: "全部",
                },
                {
                    key: "option2",
                    label: "RFID",
                },
                {
                    key: "option3",
                    label: "凌志西",
                },
                {
                    key: "option4",
                    label: "石头村",
                },
                {
                    key: "option5",
                    label: "石头油库",
                },
            ],
            selectedOption: "option1",

            showRFIDAlarm: true,
            showLingAlarm: true,
            showVillageAlarm: true,
            showOilAlarm: true,

            rfidAlarms: [
                "RFID电子标签异常E3E-4异常 2020-12-24 20:12:24",
                "RFID电子标签异常E3C-2异常 2020-12-24 20:12:24",
                "RFID电子标签异常E3D-0异常 2020-12-24 20:12:24",
                "RFID电子标签异常E3A-0异常 2020-12-24 20:12:24",
                "RFID电子标签异常E3A-3异常 2020-12-24 20:12:24",
            ],
            lingAlarm: "与道口断开连接 2020-12-24 21:46:59",
            villageAlarm: "与道口断开连接 2020-12-24 21:46:59",
            oilAlarm: "与道口断开连接 2020-12-24 21:46:59",
        };
    },
    created: function () {
        var data = new Date();
        this.loginTime =
            data.getFullYear() +
            "/" +
            (data.getMonth() + 1) +
            "/" +
            data.getDate() +
            " " +
            data.getHours() +
            ":" +
            data.getMinutes() +
            ":" +
            data.getSeconds();
        this.userName = this.$route.query.userName;

        this.lingMode = "远控自动";
        this.villageMode = "远控自动";
        this.oilMode = "远控自动";

        this.trainName = "东风4123";
        this.trainNumber = this.trainName.substring(
            this.trainName.length - 4,
            this.trainName.length
        );
        this.trainConnect = "greenyellow";

        this.villageLinkState = "red";
    },
    methods: {
        userInfo() {
            var date2 = new Date();
            var date3 = date2.getTime() - new Date(this.loginTime).getTime(); //时间差的毫秒数
            //计算出相差天数
            var days = Math.floor(date3 / (24 * 3600 * 1000));

            //计算出小时数
            var leave1 = date3 % (24 * 3600 * 1000); //计算天数后剩余的毫秒数
            var hours = Math.floor(leave1 / (3600 * 1000));
            //计算相差分钟数
            var leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数
            var minutes = Math.floor(leave2 / (60 * 1000));
            //计算相差秒数
            var leave3 = leave2 % (60 * 1000); //计算分钟数后剩余的毫秒数
            var seconds = Math.round(leave3 / 1000);
            this.$alert(
                "用 户 名 ：" +
                    this.userName +
                    "</br>登录时间：" +
                    this.loginTime +
                    "</br>登录时长：" +
                    days +
                    "天 " +
                    hours +
                    "小时 " +
                    minutes +
                    " 分钟" +
                    seconds +
                    " 秒",
                "用户信息",
                {
                    dangerouslyUseHTMLString: true,
                    confirmButtonText: "确定",
                }
            );
        },

        logout() {
            this.$confirm("确认退出登录吗?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    this.$message({
                        type: "success",
                        message: "退出成功!",
                    });
                    this.$router.replace("/Login");
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消退出",
                    });
                });
        },

        clickLingMode() {
            if (this.lingMode == "就地手动") {
                this.$message({
                    showClose: true,
                    message: "该道口为就地手动模式，无法操作！",
                    type: "warning",
                    center: true,
                });
            } else if (this.lingMode == "远控自动") {
                let routeData = this.$router.resolve({
                    path: "/LingHandle",
                    query: {
                        lingUpState:this.lingUpState,
                        lingDownState:this.lingDownState,
                    },
                });
                this.lingMode = "操作中";
                this.operatingLing = true;
                var _this = this;
                window.addEventListener("message", function (e) {
                    if (e.data.msg.message == "complete") {
                        _this.lingMode = "远控自动";
                        _this.operatingLing = false;
                        var lan12State=e.data.msg.lan12State;
                        var lan34State=e.data.msg.lan34State;
                        var lan56State=e.data.msg.lan56State;
                        var lan78State=e.data.msg.lan78State;
                        if(lan12State=="red"||lan34State=="red"){
                            _this.lingDownState="red";
                            _this.lingDownUrl=require("../assets/redLing.png");
                        }else if(lan12State=="yellow"||lan34State=="yellow"){
                            _this.lingDownState="yellow";
                            _this.lingDownUrl=require("../assets/yellowLing.png");
                        }else{
                            _this.lingDownState="green";
                            _this.lingDownUrl=null;
                        }

                        if(lan56State=="red"||lan78State=="red"){
                            _this.lingUpState="red";
                            _this.lingUpUrl=require("../assets/redLing.png");
                        }else if(lan56State=="yellow"||lan78State=="yellow"){
                            _this.lingUpState="yellow";
                            _this.lingUpUrl=require("../assets/yellowLing.png");
                        }else{
                            _this.lingUpState="green";
                            _this.lingUpUrl=null;
                        }
                    }
                });
                window.open(routeData.href, "_blank");
            } else {
                this.$message({
                    showClose: true,
                    message: "该道口正在操作！",
                    type: "warning",
                    center: true,
                });
            }
        },

        clickVillageMode() {
            if (this.villageMode == "就地手动") {
                this.$message({
                    showClose: true,
                    message: "该道口为就地手动模式，无法操作！",
                    type: "warning",
                    center: true,
                });
            } else if (this.villageMode == "远控自动") {
                let routeData = this.$router.resolve({
                    path: "/VillageHandle",
                    query: {
                        villageUpState:this.villageUpState,
                        villageDownState:this.villageDownState,
                    },
                });
                this.villageMode = "操作中";
                this.operatingVillage = true;
                var _this = this;
                window.addEventListener("message", function (e) {
                    if (e.data.msg.message == "village") {
                        _this.villageMode = "远控自动";
                        _this.operatingVillage = false;
                        var lan12State=e.data.msg.lan12State;
                        var lan34State=e.data.msg.lan34State;
                        var lan56State=e.data.msg.lan56State;
                        var lan78State=e.data.msg.lan78State;
                        if(lan12State=="red"||lan34State=="red"){
                            _this.villageDownState="red";
                            _this.villageLeftUrl=require("../assets/redVillage.png");
                        }else if(lan12State=="yellow"||lan34State=="yellow"){
                            _this.villageDownState="yellow";
                            _this.villageLeftUrl=require("../assets/yellowVillage.png");
                        }else{
                            _this.villageDownState="green";
                            _this.villageLeftUrl=null;
                        }

                        if(lan56State=="red"||lan78State=="red"){
                            _this.villageUpState="red";
                            _this.villageRightUrl=require("../assets/redVillage.png");
                        }else if(lan56State=="yellow"||lan78State=="yellow"){
                            _this.villageUpState="yellow";
                            _this.villageRightUrl=require("../assets/yellowVillage.png");
                        }else{
                            _this.villageUpState="green";
                            _this.villageRightUrl=null;
                        }
                    }
                });
                window.open(routeData.href, "_blank");
            } else {
                this.$message({
                    showClose: true,
                    message: "该道口正在操作！",
                    type: "warning",
                    center: true,
                });
            }
        },

        clickOilMode() {
            if (this.oilMode == "就地手动") {
                this.$message({
                    showClose: true,
                    message: "该道口为就地手动模式，无法操作！",
                    type: "warning",
                    center: true,
                });
            } else if (this.oilMode == "远控自动") {
                let routeData = this.$router.resolve({
                    path: "/OilHandle",
                    query: {
                        oilUpState:this.oilUpState,
                        oilDownState:this.oilDownState,
                    },
                });
                this.oilMode = "操作中";
                this.operatingOil = true;
                var _this = this;
                window.addEventListener("message", function (e) {
                    if (e.data.msg.message == "oil") {
                        _this.oilMode = "远控自动";
                        _this.operatingOil = false;
                        var lan12State=e.data.msg.lan12State;
                        var lan34State=e.data.msg.lan34State;
                        var lan56State=e.data.msg.lan56State;
                        var lan78State=e.data.msg.lan78State;
                        if(lan12State=="red"||lan34State=="red"){
                            _this.oilDownState="red";
                            _this.oilDownUrl=require("../assets/redLing.png");
                        }else if(lan12State=="yellow"||lan34State=="yellow"){
                            _this.oilDownState="yellow";
                            _this.oilDownUrl=require("../assets/yellowLing.png");
                        }else{
                            _this.oilDownState="green";
                            _this.oilDownUrl=null;
                        }

                        if(lan56State=="red"||lan78State=="red"){
                            _this.oilUpState="red";
                            _this.oilUpUrl=require("../assets/redLing.png");
                        }else if(lan56State=="yellow"||lan78State=="yellow"){
                            _this.oilUpState="yellow";
                            _this.oilUpUrl=require("../assets/yellowLing.png");
                        }else{
                            _this.oilUpState="green";
                            _this.oilUpUrl=null;
                        }
                    }
                });
                window.open(routeData.href, "_blank");
            } else {
                this.$message({
                    showClose: true,
                    message: "该道口正在操作！",
                    type: "warning",
                    center: true,
                });
            }
        },

        trainInfo() {
            const trainPhoto = require("../assets/trainPhoto.png");
            this.$alert(
                "<img src=" +
                    trainPhoto +
                    "></br>当 前 列 车 ： " +
                    this.trainName +
                    "</br>前 方 道 口 ： 未检测出道口" +
                    "</br>股 道 编 码 ： E3A" +
                    "</br>机车应答状态：不通过",
                "列车详情",
                {
                    dangerouslyUseHTMLString: true,
                    showConfirmButton: false,
                }
            );
        },

        changeAlarm(value) {
            switch (value) {
                case "option1":
                    this.showRFIDAlarm = true;
                    this.showLingAlarm = true;
                    this.showVillageAlarm = true;
                    this.showOilAlarm = true;
                    break;
                case "option2":
                    this.showRFIDAlarm = true;
                    this.showLingAlarm = false;
                    this.showVillageAlarm = false;
                    this.showOilAlarm = false;
                    break;
                case "option3":
                    this.showRFIDAlarm = false;
                    this.showLingAlarm = true;
                    this.showVillageAlarm = false;
                    this.showOilAlarm = false;
                    break;
                case "option4":
                    this.showRFIDAlarm = false;
                    this.showLingAlarm = false;
                    this.showVillageAlarm = true;
                    this.showOilAlarm = false;
                    break;
                default:
                    this.showRFIDAlarm = false;
                    this.showLingAlarm = false;
                    this.showVillageAlarm = false;
                    this.showOilAlarm = true;
                    break;
            }
        },
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.all {
    width: 100%;
    background: url("../assets/map.png") no-repeat;
    background-size: 100% auto;
    padding-bottom: 47%;
    height: 0px;
}

.controlLing {
    position: absolute;
    width: 30px;
    left: 800px;
    top: 240px;
    cursor: pointer;
}

.controlVillage {
    position: absolute;
    width: 30px;
    left: 700px;
    top: 490px;
    cursor: pointer;
}

.controlOil {
    position: absolute;
    width: 30px;
    left: 480px;
    top: 620px;
    cursor: pointer;
}

.lingMode {
    position: absolute;
    left: 900px;
    top: 290px;
}

.villageMode {
    position: absolute;
    left: 550px;
    top: 360px;
}

.oilMode {
    position: absolute;
    left: 300px;
    top: 490px;
}

.lingUp{
    position: absolute;
    top: 200px;
    left: 850px;
    width: 50px;
    height: 50px;
}

.lingDown{
    position: absolute;
    top: 280px;
    left: 850px;
    width: 50px;
    height: 50px;
}

.oilUp{
    position: absolute;
    top: 590px;
    left: 380px;
    width: 50px;
    height: 50px;
}

.oilDown{
    position: absolute;
    top: 640px;
    left: 410px;
    width: 50px;
    height: 50px;
}

.villageLeft{
    position: absolute;
    top: 430px;
    left: 650px;
    width: 31px;
    height: 50px;
}

.villageRight{
    position: absolute;
    top: 400px;
    left: 730px;
    width: 31px;
    height: 50px;
}

.lingLeftLight{
    position: absolute;
    top: 280px;
    left: 850px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.lingRightLight{
    position: absolute;
    top: 244px;
    left: 900px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.lingUpLight{
    position: absolute;
    top: 200px;
    left: 870px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.lingDownLight{
    position: absolute;
    top: 310px;
    left: 880px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.villageLeftLight{
    position: absolute;
    top: 460px;
    left: 640px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.villageRightLight{
    position: absolute;
    top: 410px;
    left: 760px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.villageUpLight{
    position: absolute;
    top: 400px;
    left: 695px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.villageDownLight{
    position: absolute;
    top: 470px;
    left: 695px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.oilLeftLight{
    position: absolute;
    top: 660px;
    left: 370px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.oilRightLight{
    position: absolute;
    top: 610px;
    left: 460px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.oilUpLight{
    position: absolute;
    top: 580px;
    left: 390px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.oilDownLight{
    position: absolute;
    top: 680px;
    left: 440px;
    width: 14px;
    height: 14px;
    border-radius: 7px;
}

.train {
    position: absolute;
    left: 1000px;
    top: 100px;
    background-color: skyblue;
    cursor: pointer;
}

.trainNumber {
    color: whitesmoke;
}

.linkStates {
    position: absolute;
    left: 0;
    bottom: 0;
}

.linkState {
    float: left;
    font-size: 13px;
    text-align: center;
    margin-left: 10px;
    margin-bottom: 10px;
}

.alarmBox {
    width: 400px;
    padding: 10px 10px 10px 10px;
    background-color: whitesmoke;
}

.alarmBoxTitle {
    color: red;
    margin-right: 7em;
}
</style>