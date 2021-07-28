<template>
    <div align="center" style="width: 100%">
        <h3>차량 계약 정보</h3>

        <v-card class="card shadow" style="width: 750px; margin: 20px; padding: 60px 100px">
            <div align="right">
                <span>작성자 : {{ item.user.department }}&nbsp;{{ item.user.nickname }}</span>
            </div>
            <form class="row g-3" style="text-align: left;">
                <v-row>
                    <div class="col-md-3">
                        <label class="form-label" for="inpustDivision">구분</label>
                        <input id="inpustDivision" v-model="item.division" class="form-control" name="object"
                               readonly type="text">
                    </div>
                </v-row>
                <v-row>
                    <div class="col-md-4" style="display: block">
                        <label class="form-label" for="inpustObject">차량 정보</label>
                        <input id="inpustObject" v-model="item.object" class="form-control" name="object" readonly
                               type="text">
                    </div>&nbsp;
                    <div class="col-md-4" style="display: block">
                        <label class="form-label" for="inpustCarnum">차량 번호</label>
                        <input id="inpustCarnum" v-model="item.carnum" class="form-control" name="object" readonly
                               type="text">
                    </div>&nbsp;
                    <div class="col-md-3" style="display: block">
                        <label class="form-label" for="inpustCaruser">사용자 </label>
                        <input id="inpustCaruser" v-model="item.caruser" class="form-control" name="object" readonly
                               type="text">
                    </div>
                </v-row>
                <v-row style="display: flex">
                    <div class="col-md-4">
                        <label class="form-label" for="inputStardDate">계약 시작일</label>
                        <input id="inputStardDate" v-model="startdate" class="form-control" name="startDate"
                               readonly type="date">
                    </div>&nbsp;
                    <div class="col-md-4">
                        <label class="form-label" for="inputEndDate">계약 종료일</label>
                        <input id="inputEndDate" v-model="enddate" class="form-control" name="endDate" readonly
                               type="date">
                    </div>&nbsp;
                    <div class="col-md-3">
                        <label class="form-label" for="inputPeriod">계약 기간</label>
                        <input id="inputPeriod" v-model="item.period" class="form-control" name="endDate" readonly
                               type="text">
                    </div>&nbsp;
                </v-row>
                <v-row>
                    <div class="col-md-4">
                        <label class="form-label" for="inpustDiposit">월 비용</label>
                        <input id="inpustDiposit" v-model="monthlyfee" class="form-control" name="monthlyfee"
                               readonly style="text-align:right;" type="text">
                    </div>&nbsp;
                </v-row>
                <v-row>
                    <div class="col-md-4">
                        <label class="form-label" for="client">업체명</label>
                        <input id="client" v-model="item.client" class="form-control"
                               name="client" readonly type="text">
                    </div>&nbsp;
                    <div class="col-md-4">
                        <label class="form-label" for="clientNumber">업체 연락처</label>
                        <input id="clientNumber" v-model="item.clientnumber" class="form-control"
                               name="clientNumber" readonly type="text">
                    </div>
                </v-row>
                <!--File Upload-->
                <v-row style="margin: 30px 0px">
                    <!--    <div class="downloadList">-->
                    <!--        {{ item.files }}-->
                    <!--    </div>-->
                </v-row>
            </form>
            <div align="center" style="justify-content: space-between; display: flex">
                <div style="display: flex">
                    <v-btn elevation="2" @click="goBack">목록</v-btn>&nbsp;
                </div>
                <div v-if="isLoginUser(item.user.id)">
                    <v-btn elevation="2" @click="sendNotice">알림발송</v-btn>&nbsp;
                    <v-btn elevation="2" @click="goEdit">수정</v-btn>&nbsp;
                    <v-btn elevation="2" @click="deleteItem">삭제</v-btn>
                </div>
                <div v-else-if="this.noticeUser===false">
                    <v-btn elevation="2" @click="saveNotice">알림등록</v-btn>
                </div>
                <div v-else>
                    <v-btn elevation="2" @click="delNotice(noticeid)">알림취소</v-btn>
                </div>
            </div>

            <!--댓글 작성-->
            <div v-if="OnCmnt1Write" style="margin-top: 20px">
                <v-textarea v-model="writeCmnt1" name="input-7-4" placeholder="댓글을 입력하세요." rows="3" solo
                            style="width: 700px;"></v-textarea>
                <div align="right" style="margin-top: -10px">
                    <v-btn elevation="2" @click="saveCmnt1_Btn">댓글</v-btn>&nbsp;
                    <v-btn elevation="2" @click="saveCmnt1">저장</v-btn>
                </div>
            </div>

            <!--댓글 수정-->
            <div v-if="OnCmnt1Edit" style="margin-top: 20px">
                <v-textarea v-model="editCmnt1" name="input-7-4" rows="3" solo style="width: 700px;"></v-textarea>
                <div align="right" style="margin-top: -10px">
                    <v-btn elevation="2" @click="saveCmnt1_Btn">댓글</v-btn>&nbsp;
                    <v-btn elevation="2" @click="editCmnt1do">저장</v-btn>
                </div>
            </div>

            <!--대댓글 작성-->
            <div v-if="OnCmnt2Write" style="margin-top: 20px">
                <v-textarea v-model="writeCmnt2" name="input-7-4" placeholder="대댓글을 입력하세요." rows="3" solo
                            style="width: 700px;"></v-textarea>
                <div align="right" style="margin-top: -10px">
                    <v-btn elevation="2" @click="saveCmnt1_Btn">댓글</v-btn>&nbsp;
                    <v-btn elevation="2" @click="saveCmnt2do">저장</v-btn>
                </div>
            </div>

            <!--대댓글 수정-->
            <div v-if="OnCmnt2Edit" style="margin-top: 20px">
                <v-textarea v-model="editCmnt2" name="input-7-4" rows="3" solo style="width: 700px;"></v-textarea>
                <div align="right" style="margin-top: -10px">
                    <v-btn elevation="2" @click="saveCmnt2_Btn">댓글</v-btn>&nbsp;
                    <v-btn elevation="2" @click="editCmnt2do">저장</v-btn>
                </div>
            </div>
            <!--댓글-->
            <div style="margin-top: 20px">
                <table v-for="(item, idx) in cmnt1" class="table" style="font-size: 15px; margin-bottom:0px">
                    <tr>
                        <td style="width:120px; height : 35px; text-align: left">
                            {{ item.user ? item.user.nickname : '' }}
                        </td>
                        <td style="width: 300px">{{ item.content }}</td>
                        <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.created).format('YYYY-MM-DD')"
                            style="width: 100px; text-align: center">
                            {{ $moment(item.created).format('HH:mm:ss') }}
                        </td>
                        <td v-else style="width: 100px; text-align: center">{{
                                $moment(item.created).format('YYYY-MM-DD')
                            }}
                        </td>
                        <td style="width: 140px; height: 40px; text-align: right; vertical-align: center"
                        >
                            <button v-show="item.content !== deleteCmntMessage" @click="saveCmnt2_Btn(idx)"><i
                                class="material-icons"
                                style="font-size: 18px;">reply</i></button>
                            <button v-if="isLoginUser(item.user?item.user.id:'')"
                                    v-show="item.content !== deleteCmntMessage"
                                    @click="editCmnt1_Btn(idx)"><i
                                class="material-icons"
                                style="font-size: 18px;">edit</i></button>
                            <button v-if="isLoginUser(item.user?item.user.id:'')"
                                    v-show="item.content !== deleteCmntMessage"
                                    @click="delCmnt1_Btn(item.id)"><i
                                class="material-icons"
                                style="font-size: 18px;">clear</i></button>
                        </td>
                    </tr>
                    <!--대댓글-->
                    <tr v-for="(item, i) in cmnt2[idx]">
                        <td style="width: 120px; height: 35px; text-align: left">
                            <i class="material-icons" style="font-size: 13px;">subdirectory_arrow_right</i>
                            {{ item.user ? item.user.nickname : '' }}
                        </td>
                        <td style="width: 300px; ">{{ item.content }}</td>
                        <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.created).format('YYYY-MM-DD')"
                            style="width: 100px; text-align: center">
                            {{ $moment(item.created).format('HH:mm:ss') }}
                        </td>
                        <td v-else style="width: 100px; text-align: center">
                            {{ $moment(item.created).format('YYYY-MM-DD') }}
                        </td>
                        <td style="width: 140px; text-align: right">
                            <button v-if="isLoginUser(item.user?item.user.id:'')" @click="editCmnt2_Btn(idx, i)"><i
                                class="material-icons"
                                style="font-size: 18px;">edit</i></button>
                            <button v-if="isLoginUser(item.user?item.user.id:'')" @click="delCmnt2_Btn(item.id)"><i
                                class="material-icons"
                                style="font-size: 18px;">clear</i></button>
                        </td>
                    </tr>
                </table>
            </div>
        </v-card>
    </div>
</template>

<script>
import {convertDate} from "@/utils/convertDate";
import {api_checkCmnt2, api_delCmnt, api_saveCmnt} from "@/api/api_cmnt";
import {api_delNotice, api_saveNotice, api_sendMail} from "@/api/api_notice2";
import {api_deleteCar, api_getOneCar} from "@/api/api_car";

export default {
    data() {
        return {
            item      : {user: {}},
            user      : {},
            noticeUser: false,
            noticeid  : '',
            startdate : '',
            enddate   : '',
            cost      : '',
            precost   : '',
            diffcost  : '',

            //댓글
            cmnt1            : [],
            cmnt1user        : [],
            cmnt2            : [],
            cmnt2user        : [],
            checkcmnt        : true,
            deleteCmntMessage: '삭제된 댓글입니다.',

            cmntid   : '',
            subcmntid: '',

            writeCmnt1: '',
            writeCmnt2: '',
            editCmnt1 : '',
            editCmnt2 : '',

            OnCmnt1Write: true,
            OnCmnt1Edit : false,
            OnCmnt2Write: false,
            OnCmnt2Edit : false,
        };
    },
    methods: {
        async FETCH_DATA() {
            const {data} = await api_getOneCar(this.$route.params.id, this.$store.state.id)
            this.item = data.car;
            this.noticeUser = data.notice;
            this.noticeid = data.noticeid;
            this.cmnt1 = data.cmnt1;
            this.cmnt2 = data.cmnt2;
            this.startdate = convertDate(data.car.startdate);
            this.enddate = convertDate(data.car.enddate);
            this.monthlyfee = data.car.monthlyfee.toLocaleString() + "원";
        },
        async saveNotice() {
            const Data = {
                section  : 3,
                sectionid: this.$route.params.id,
                user     : {id: this.$store.state.id}
            }
            await api_saveNotice(Data);
            await this.FETCH_DATA()
        },
        async delNotice(noticeid) {
            await api_delNotice(noticeid);
            await this.FETCH_DATA()
        },
        async sendNotice() {
            await api_sendMail(3, this.$route.params.id)
            alert('알림을 발송했습니다.')
        },
        isLoginUser(id) {
            return this.$store.state.id === id;
        },
        goBack() {
            this.$router.push('/car');
        },
        goEdit() {
            this.$router.push(`/car/edit/${this.item.id}`);
        },
        async deleteItem() {
            //TODO: 삭제 확인 창
            await api_deleteCar(this.item.id);
            await this.$router.push('/car');
        },
        saveCmnt1_Btn() {
            this.OnCmnt1Write = true;
            this.OnCmnt1Edit = false;
            this.OnCmnt2Write = false;
            this.OnCmnt2Edit = false;
        },
        async saveCmnt1() {
            const Data = {
                section: 3,
                secid  : this.$route.params.id,
                user   : {id: this.$store.state.id},
                content: this.writeCmnt1
            }
            await api_saveCmnt(Data);
            this.writeCmnt1 = '';
            await this.FETCH_DATA();
        },
        editCmnt1_Btn(idx) {
            this.cmntid = this.cmnt1[idx].id;
            this.editCmnt1 = this.cmnt1[idx].content;

            this.OnCmnt1Write = false;
            this.OnCmnt1Edit = true;
            this.OnCmnt2Write = false;
            this.OnCmnt2Edit = false;
        },
        async delCmnt1_Btn(id) {
            await api_checkCmnt2(id).then((data) => this.checkcmnt = data.data);
            if (this.checkcmnt === false) {
                //TODO 삭제확인 창
                await api_delCmnt(id);
                await this.FETCH_DATA();
            } else {
                const Data = {
                    id     : id,
                    section: 3,
                    secid  : this.$route.params.id,
                    user   : {id: this.$store.state.id},
                    content: this.deleteCmntMessage
                }
                await api_saveCmnt(Data);
                await this.FETCH_DATA();
            }
        },
        async editCmnt1do() {
            //Logic
            const Data = {
                id     : this.cmntid,
                section: 3,
                secid  : this.$route.params.id,
                user   : {id: this.$store.state.id},
                content: this.editCmnt1
            }
            await api_saveCmnt(Data);
            this.editCmnt1 = '';
            await this.FETCH_DATA();

            this.OnCmnt1Write = true;
            this.OnCmnt1Edit = false;
        },
        saveCmnt2_Btn(idx) {
            this.subcmntid = this.cmnt1[idx].id;

            this.OnCmnt1Write = false;
            this.OnCmnt1Edit = false;
            this.OnCmnt2Write = true;
            this.OnCmnt2Edit = false;
        },
        async delCmnt2_Btn(id) {
            await api_delCmnt(id);
            await this.FETCH_DATA();
        },
        async saveCmnt2do() {
            //Logic
            const Data = {
                section: 3,
                secid  : this.$route.params.id,
                user   : {id: this.$store.state.id},
                cmntid : this.subcmntid,
                depth  : 1,
                content: this.writeCmnt2
            }
            await api_saveCmnt(Data);
            this.writeCmnt2 = '';
            await this.FETCH_DATA();
            this.OnCmnt1Write = true;
            this.OnCmnt2Write = false;
        },
        editCmnt2_Btn(idx, i) {
            this.subcmntid = this.cmnt1[idx].id;
            this.cmntid = this.cmnt2[idx][i].id;
            this.editCmnt2 = this.cmnt2[idx][i].content;
            this.OnCmnt1Write = false;
            this.OnCmnt1Edit = false;
            this.OnCmnt2Write = false;
            this.OnCmnt2Edit = true;
        },

        async editCmnt2do() {
            //Logic
            const Data = {
                id     : this.cmntid,
                section: 3,
                secid  : this.$route.params.id,
                user   : {id: this.$store.state.id},
                cmntid : this.subcmntid,
                depth  : 1,
                content: this.editCmnt2
            }
            await api_saveCmnt(Data);
            this.editCmnt2 = '';
            await this.FETCH_DATA();

            this.OnCmnt1Write = true;
            this.OnCmnt2Edit = false;
        },
    },
    created() {
        this.FETCH_DATA();
    }
}
</script>

<style scoped>

</style>