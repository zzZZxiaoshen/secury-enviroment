/**
 * 获取路径
 */
function getRootPath() {
    let curWwwPath = window.document.location.href;
    let pathName = window.document.location.pathname;
    let pos = curWwwPath.indexOf(pathName);
    let localhostPath = curWwwPath.substring(0, pos);
    let projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPath + projectName);
}

/**
 * 复写AJAX
 */
(function ($) {
    let _ajax = $.ajax;
    $.ajax = function (options) {
        let fn = {
            success: function (data, textStatus) {},
            complete: function (httpRequest, textStatus) {},
            error: function (httpRequest, textStatus, error) {},
            beforeSend: function (httpRequest) {}
        };
        if (options.error) {
            fn.error = options.error;
        }
        if (options.success) {
            fn.success = options.success;
        }
        if (options.complete) {
            fn.complete = options.complete;
        }
        if (options.beforeSend) {
            fn.beforeSend = options.beforeSend;
        }
        options.xhrFields = {
            withCredentials: true
        };
        let _opt = $.extend(options, {
            error: function (res) {
                if (typeof options.error === "function") {
                    fn.error(res);
                } else {
                    window.location.href = getRootPath() + "/view/system/error";
                }
            },
            success: function (data, textStatus) {
                if (data.code && data.code === 403) {
                    window.location.href = getRootPath() + "/view/not/auth";
                } else {
                    fn.success(data, textStatus);
                }
            },
            complete: function (httpRequest, textStatus) {
                if (options.loading !== false) {
                    $.removeLoading();
                }
                fn.complete(httpRequest, textStatus);
            },
            beforeSend: function (httpRequest) {
                if (options.loading !== false) {
                    $.createLoading({'color': '#fff', 'size': 80, 'isBg': true});
                }
                fn.beforeSend(httpRequest)
            },
        });
        return _ajax(_opt);
    };
})(jQuery);

/**
 * cookie操作
 */
jQuery.cookie = {
    // 设置cookie
    set: function (key, val, time) {
        let date = new Date();
        date.setTime(date.getTime() + time * 24 * 3600 * 1000);
        document.cookie = key + "=" + val + ";expires=" + date.toGMTString() + ";path=/";
    },
    // 获取cookie
    get: function (key) {
        let getCookie = document.cookie.replace(/[ ]/g, "");
        let arrCookie = getCookie.split(";");
        let tips;
        for (let i = 0; i < arrCookie.length; i++) {
            let arr = arrCookie[i].split("=");
            if (key == arr[0]) {
                tips = arr[1];
                break;
            }
        }
        return tips;
    },
    // 删除cookie
    delete: function (key) {
        let date = new Date();
        date.setTime(date.getTime() - 10000);
        document.cookie = key + "=v; expires =" + date.toGMTString() + ";path=/";
    }
};

/**
 * 登出
 */
function logout() {
    jQuery.cookie.delete("token");
    localStorage.clear();
    location.href = getRootPath() + "/view/login";
}

/**
 * 获取当前url的参数
 */
function getQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 * 获取用户信息
 */
$(function () {
    let userinfo;
    try {
        userinfo = JSON.parse(localStorage.getItem("userinfo"));
    } catch (e) {
        localStorage.removeItem("userinfo");
    }
    if (userinfo) {
        $(".user-panel .pull-left.info p").text(userinfo.nickName);
        $("li[class='dropdown user user-menu'] a span").text(userinfo.nickName);
        $("li.user-header p").text(userinfo.nickName);
        $("div[class='pull-left info']>p").text(userinfo.nickName);
        if (userinfo.portrait) {
            $("img.img-circle,img.user-image").attr("src", userinfo.portrait);
        }
    }
});


/**
 * 校验input是否为空
 */
function checkInput(inputDom) {
    if (!inputDom) return false;
    if (inputDom.val().trim().length === 0) {
        $.toast({"text": inputDom.attr("placeholder")});
        inputDom.focus();
        return false;
    }
    return true;
}

/**
 * 时间格式化处理
 */
function dateFormat(fmt, date) {
    if (typeof date !== "object") {
        date = new Date(date);
    }
    let o = {
        // 月份
        "M+": date.getMonth() + 1,
        // 日
        "d+": date.getDate(),
        // 小时
        "h+": date.getHours(),
        // 分
        "m+": date.getMinutes(),
        // 秒
        "s+": date.getSeconds(),
        // 季度
        "q+": Math.floor((date.getMonth() + 3) / 3),
        // 毫秒
        "S": date.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}