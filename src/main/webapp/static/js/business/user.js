// 图表配置
var options ={
    chart: {
        type: 'column'
    },
    colors:["#FF983F"],
    title: {
        text:'用户注册量'
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation:0
        }
    },
    yAxis: {
        allowDecimals:false,
        min: 0,
        title: {
            text:"近七天用户注册人数"
        },
        tickPositions: [0, 4, 8,12,16,20]
    },
    legend: {
        enabled: false,
    },
    tooltip: {

    },
    series: [{
        data: [],
        zones: [{
            value: 0,
            color: '#FF983F'
        }],
        dataLabels: {
            enabled: true,
            rotation:0,
            color: '#333',
            align: 'center',
            y:0
        }
    }]
};
$(function () {
    /**
     * 获取用户信息
     */
    let userinfo  = JSON.parse(window.localStorage.getItem("userinfo"));
    if(userinfo){
        $(".userName").text(userinfo.nickName);
        $(".profilePhoto").css("background-image","url("+(userinfo.portrait||'https://wxapp.marsfood.cn/Fppo1xVq8_KpOG14XBhAe24PnThL')+")");
    }

    /**
     * 初始化数据
     */
    getData();

    /**
     * 点击刷新数据
     */
    $(".refresh").on("click",function () {
        getData();
    });

    /**
     * 侧边导航显示
     */
    $("#nav").on("click",function (event) {
        event.stopPropagation();
        $("#navbar").animate({right:"0"})
    });
    $("body").on("click",function () {
        $("#navbar").animate({right:"-3.34rem"})
    })

    /**
     * 退出
     */
    $(".button").on("click", function () {
        jQuery.cookie.delete("token");
        localStorage.clear();
        location.href = getRootPath() + "/view/phone/login";
    })
});
/**
 * 获取图表数据
 */
function getData() {
    $.ajax({
        method:"get",
        url:`${rootPath}/phone/user/register/list`,
        contentType:"application/json",
        success:function (res) {
            if(res.code === 200){
                let data =res.data;
                let userArr=[];
                let time;
                let userNum;
                let allUser;
                let year;
                let today = dateFormat("yyyy-MM-dd",new Date(new Date(new Date()-24*60*60*1000)));
                let date;
                $.each(data,function (index ,val) {
                    allUser = val.allUser;
                    time = val.registeDate.substring(5,10);
                    time = time.replace("-",".");
                    year = val.registeDate.substring(0 ,4);
                    date = val.registeDate.substring(0 ,11);
                    if( date = today){
                        $(".todayNum").text("今日注册人数："+val.countUser+"/人")
                    }
                    userNum = val.countUser;
                    userArr.push(time,userNum);
                    options.series[0].data.push(userArr);
                    userArr = [];
                });
                $(".userNum").text("小程序注册总数："+allUser+"/人");
                $(".year").text(year);
                Highcharts.chart('container', options);
            }
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status == 403){
                location.href = `${rootPath}/view/login`;
            }
        }
    })
}