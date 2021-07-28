<template>
    <div align="center">
        <h3>임대차 계약관리</h3>
        <div style="margin:30px; width: 1100px; ">
            <div v-if="isAdmin('admin')" align="left" style="margin: 30px">
                <v-btn elevation="2" style="text-decoration: none; ">
                    <router-link style="text-decoration: none; color: black" to="/property/write/">신규 작성</router-link>
                </v-btn>
            </div>
            <div style="margin: 30px; ">
                <v-card>
                    <v-card-title>
                        <v-text-field
                            v-model="search"
                            append-icon="mdi-magnify"
                            hide-details
                            label="Search"
                            single-line
                        ></v-text-field>
                    </v-card-title>
                    <v-data-table :headers="headers" :items="list" :items-per-page="itemsPerPage"
                                  :loading="loading"
                                  :page.sync="page"
                                  :search="search"
                                  :sort-by="sortBy"
                                  :sort-desc="sortDesc"
                                  :total-visible="totalVisible"
                                  hide-default-footer
                                  loading-text="Loading... Please wait"
                                  @page-count="pageCount = $event">
                        <template #item.object="{ item }">
                            <a :href="`/property/post/${item.id}`"
                               style="color: black; text-decoration: none">
                                {{ item.object }}
                            </a>
                        </template>
                        <template #item.startdate="{ item }">
                            <a target="_blank">
                                {{ convertDate(item.startdate) }}
                            </a>
                        </template>
                        <template #item.enddate="{ item }">
                            <a target="_blank">
                                {{ convertDate(item.enddate) }}
                            </a>
                        </template>
                        <template #item.deposit="{ item }">
                            <a target="_blank">
                                {{ item.deposit.toLocaleString() }}원
                            </a>
                        </template>
                        <template #item.monthlyfee="{ item }">
                            <a target="_blank">
                                {{ item.monthlyfee.toLocaleString() }}원
                            </a>
                        </template>
                        <!--<template #item.files="{ item }">-->
                        <!--    <a target="_blank">-->
                        <!--        다운로드-->
                        <!--    </a>-->
                        <!--</template>-->
                    </v-data-table>
                    <template>
                        <div class="text-center">
                            <v-pagination v-model="page"
                                          :length="pageCount" :next-icon="'>'"
                                          :prev-icon="'<'"
                                          :total-visible="totalVisible"
                                          class="foot_page"
                                          style="margin: 20px; padding-bottom: 20px"
                            ></v-pagination>
                        </div>
                    </template>
                </v-card>
            </div>
        </div>
    </div>
</template>

<script>
import {api_propertyList} from "@/api/api_property";

export default {
    data() {
        return {
            loading     : true,
            page        : 1,
            pageCount   : 5,
            itemsPerPage: 10,
            totalVisible: 10,
            search      : '',
            sortBy      : 'enddate',
            sortDesc    : false,
            list        : [],
            headers     : [{text: 'No', value: 'id', align: 'center'},
                {text: '구분', value: 'division', align: 'center',},
                {text: '대상', value: 'object', align: 'center'},
                {text: '계약시작일', value: 'startdate', align: 'center'},
                {text: '계약종료일', value: 'enddate', align: 'center'},
                {text: '보증금', value: 'deposit', align: 'end'},
                {text: '월임차료', value: 'monthlyfee', align: 'end'},
                {text: '임대인', value: 'client', align: 'center'}],
//                {text: '계약서', value: 'files', align: 'center'}],
        }
    },
    methods: {
        dateTime(value) {
            return moment(value).format('YYYY-MM-DD')
        },
        isAdmin(id) {
            return this.$store.state.username === id;
        },
        async FETCH_DATA() {
            const {data} = await api_propertyList();
            this.list = data;
            this.loading = false;
        },
        convertDate(milliSecond) {
            const days = ['일', '월', '화', '수', '목', '금', '토'];
            const data = new Date(milliSecond);  //Date객체 생성
            const year = data.getFullYear();    //0000년 가져오기
            const month = data.getMonth() + 1;  //월은 0부터 시작하니 +1하기
            const date = data.getDate();        //일자 가져오기
            const day = days[data.getDay()];    //요일 가져오기

            if (month < 10 && date < 10) {
                return `${year}-0${month}-0${date}`;
            } else if (month < 10) {
                return `${year}-0${month}-${date}`;
            } else if (date < 10) {
                return `${year}-${month}-0${date}`;
            } else {
                return `${year}-${month}-${date}`;
            }
        }
    },
    created() {
        this.FETCH_DATA()
    },
    //TODO routerlink to 동적 변수 입력 수정
}
</script>

<style>
.theme--light v-pagination {
    color: #262626 !important;
    font-size: 14px;
}

.v-pagination__item--active {
    font-size: 14px;
    color: #3e3e3e !important
}

.v-pagination__item {
    background: #f3f3f3 !important;
    font-size: 14px;
}

.v-pagination__navigation {
    background: #f3f3f3 !important;
    font-size: 14px;
}

.v-pagination__navigation {
    font-size: 14px;
}

.v-pagination__navigation--disabled {
    font-size: 14px;
}
</style>