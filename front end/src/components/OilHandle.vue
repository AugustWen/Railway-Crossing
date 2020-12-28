<template>
    <div class="all">
        <span class="crossingName">石头油库道口</span>
        <span class="crossingState">当前状态：{{ lingMode }}</span>
        <el-button
            type="primary"
            plain
            size="medium"
            :disabled="operating"
            @click="changeLingState"
            >切换状态</el-button
        >

        <el-divider></el-divider>

        <el-radio-group class="doubleRadio" v-model="lingRadio">
            <el-radio :disabled="lingRadioDisabled" :label="1"
                >开启道口（全部抬起）</el-radio
            >
            <el-radio :disabled="lingRadioDisabled" :label="2"
                >关闭道口（全部放下）</el-radio
            >
        </el-radio-group>
        <el-button
            type="primary"
            plain
            :disabled="lingRadioDisabled"
            :loading="operating"
            size="medium"
            @click="lingOrder"
            >发送指令</el-button
        >

        <img src="../assets/oilMap.jpg" />

        <div
            class="lan34"
            :style="{ backgroundImage: 'url(' + lan34Url + ')' }"
            @click="clickLan34 = true"
        ></div>

        <el-dialog
            title="发送命令"
            :show-close="!operating"
            :visible.sync="clickLan34"
            width="300px"
        >
            <h2>拦木机3-拦木机4</h2>
            <br />
            <el-radio-group v-model="dialog34Radio">
                <el-radio :disabled="operating" :label="1">抬起</el-radio>
                <el-radio :disabled="operating" :label="2">停止</el-radio>
                <el-radio :disabled="operating" :label="3">放下</el-radio>
            </el-radio-group>
            <div class="dialogButtonBox">
                <el-button
                    type="primary"
                    plain
                    :disabled="operating"
                    :loading="operating"
                    @click="lan34Order"
                    >发送指令</el-button
                >
            </div>
        </el-dialog>

        <div
            class="lan56"
            :style="{ backgroundImage: 'url(' + lan56Url + ')' }"
            @click="clickLan56 = true"
        ></div>

        <div
            class="lan78"
            :style="{ backgroundImage: 'url(' + lan78Url + ')' }"
            @click="clickLan78 = true"
        ></div>

        <el-dialog
            title="发送命令"
            :show-close="!operating"
            :visible.sync="clickLan78"
            width="300px"
        >
            <h2>拦木机7-拦木机8</h2>
            <br />
            <el-radio-group v-model="dialog78Radio">
                <el-radio :disabled="operating" :label="1">抬起</el-radio>
                <el-radio :disabled="operating" :label="2">停止</el-radio>
                <el-radio :disabled="operating" :label="3">放下</el-radio>
            </el-radio-group>
            <div class="dialogButtonBox">
                <el-button
                    type="primary"
                    plain
                    :disabled="operating"
                    :loading="operating"
                    @click="lan78Order"
                    >发送指令</el-button
                >
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "LingHandle",
    data() {
        return {
            lingMode: "远控自动",
            lingRadioDisabled: true,
            lingRadio: null,
            operating: false,

            clickLan34: false,
            clickLan78: false,

            dialog34Radio: null,
            dialog78Radio: null,

            lan34Url: null,
            lan78Url: null,

            lan34State: null,
            lan78State: null,
        };
    },

    created: function () {
        var lingUpState = this.$route.query.oilUpState;
        var lingDownState = this.$route.query.oilDownState;
        if (lingUpState == "yellow") {
            this.lan78Url = require("../assets/yellow200.png");
            this.lan78State = "yellow";
        } else if (lingUpState == "red") {
            this.lan78Url = require("../assets/red200.png");
            this.lan78State = "red";
        } else {
            this.lan78Url = require("../assets/green200.png");
            this.lan78State = "green";
        }

        if (lingDownState == "yellow") {
            this.lan34Url = require("../assets/yellow200.png");
            this.lan34State = "yellow";
        } else if (lingDownState == "red") {
            this.lan34Url = require("../assets/red200.png");
            this.lan34State = "red";
        } else {
            this.lan34Url = require("../assets/green200.png");
            this.lan34State = "green";
        }
    },

    mounted() {
        window.addEventListener("beforeunload", (e) =>
            this.beforeunloadHandler(e)
        );
    },

    methods: {
        beforeunloadHandler(e) {
            var msg = {
                message: "oil",
                lan34State: this.lan34State,
                lan78State: this.lan78State,
            };
            window.opener.postMessage(
                {
                    msg
                },
                "*"
            );
        },

        changeLingState() {
            if (this.lingMode == "远控自动") {
                this.lingMode = "远控手动";
                this.lingRadioDisabled = false;
            } else {
                this.lingMode = "远控自动";
                this.lingRadioDisabled = true;
            }
        },

        lingOrder() {
            var _this = this;
            if (this.lingRadio == "1") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan34Url = require("../assets/yellow200.png");
                this.lan78Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    _this.lan34Url = require("../assets/green200.png");
                    _this.lan78Url = require("../assets/green200.png");
                    this.lan34State = "green";
                    this.lan78State = "green";
                }, 3000);
            } else if (this.lingRadio == "2") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan34Url = require("../assets/yellow200.png");
                this.lan78Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    this.lan34Url = require("../assets/red200.png");
                    this.lan78Url = require("../assets/red200.png");
                    this.lan34State = "red";
                    this.lan78State = "red";
                }, 3000);
            }
        },

        lan34Order() {
            var _this = this;
            if (this.dialog34Radio == "1") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan34Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    _this.lan34Url = require("../assets/green200.png");
                    _this.lan34State = "green";
                }, 3000);
            } else if (this.dialog34Radio == "2") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan34Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    _this.lan34State = "yellow";
                }, 1000);
            } else if (this.dialog34Radio == "3") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan34Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    this.lan34Url = require("../assets/red200.png");
                    _this.lan34State = "red";
                }, 3000);
            }
        },

        lan78Order() {
            var _this = this;
            if (this.dialog78Radio == "1") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan78Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    _this.lan78Url = require("../assets/green200.png");
                    _this.lan78State = "green";
                }, 3000);
            } else if (this.dialog78Radio == "2") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan78Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    _this.lan78State = "yellow";
                }, 1000);
            } else if (this.dialog78Radio == "3") {
                this.lingRadioDisabled = true;
                this.operating = true;
                this.lan78Url = require("../assets/yellow200.png");
                setTimeout(() => {
                    _this.operating = false;
                    _this.lingRadioDisabled = false;
                    this.lan78Url = require("../assets/red200.png");
                    _this.lan78State = "red";
                }, 3000);
            }
        },
    },

    beforeDestroy: function () {
        window.removeEventListener("beforeunload", (e) =>
            this.beforeunloadHandler(e)
        );
    },
};
</script>

<style  scoped>
.all {
    margin: 0 auto;
    width: 1034px;
    padding-top: 10px;
}

.crossingName {
    color: blue;
    font-size: 30px;
    margin-right: 400px;
    margin-left: 3em;
}

.crossingState {
    font-size: 20px;
    margin-right: 1em;
}

.doubleRadio {
    margin-left: 280px;
}

img {
    margin-top: 20px;
}

.lan34 {
    position: absolute;
    left: 490px;
    top: 405px;
    width: 150px;
    height: 150px;
    cursor: pointer;
}

.lan78 {
    position: absolute;
    left: 795px;
    top: 405px;
    width: 150px;
    height: 150px;
    cursor: pointer;
}

.dialogButtonBox {
    margin-top: 20px;
    text-align: center;
}
</style>