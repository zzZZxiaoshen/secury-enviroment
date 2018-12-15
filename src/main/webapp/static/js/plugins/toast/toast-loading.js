'use strict';
(function ($, window) {
    //动态加载animate
    let loadStyles = function (url) {
        let hasSameStyle = false;
        let links = $('link');
        for (let i = 0; i < links.length; i++) {
            if (links.eq(i).attr('href') == url) {
                hasSameStyle = true;
                return
            }
        }

        if (!hasSameStyle) {
            let link = document.createElement("link");
            link.type = "text/css";
            link.rel = "stylesheet";
            link.href = url;
            document.getElementsByTagName("head")[0].appendChild(link);
        }
    };


    //显示提示信息    toast
    $.fn.toast = function (options) {
        let $this = $(this);
        let _this = this;
        return this.each(function () {
            $(this).css({
                position: 'relative'
            });
            let top = '';		//bottom的位置
            let translateInfo = ''; 	//居中和不居中时的tarnslate

            let box = '';   //消息元素
            let defaults = {
                position: "absolute", 				//不是body的话就absolute
                animateIn: "fadeIn",					//进入的动画
                animateOut: "fadeOut",				//结束的动画
                padding: "20px 30px",              //padding
                background: "rgba(7,17,27,0.66)",     //背景色
                borderRadius: "6px",                    //圆角
                duration: 3000,                     //定时器时间
                animateDuration: 500, 						//执行动画时间
                fontSize: 14,                   	//字体大小
                content: "这是一个提示信息",       //提示内容
                color: "#fff",                   //文字颜色
                top: "80%",                	//bottom底部的位置    具体的数值 或者center  垂直居中
                zIndex: 9999000001,                	//层级
                isCenter: true, 					//是否垂直水平居中显示
                closePrev: true, 					//在打开下一个toast的时候立即关闭上一个toast
            };

            let opt = $.extend(defaults, options || {});
            let t = '';


            top = opt.isCenter === true ? '50%' : opt.top;

            defaults.isLowerIe9 = function () {
                return (!window.FormData);
            };


            defaults.createMessage = function () {
                if (opt.closePrev) {
                    $('.cpt-toast').remove();
                }
                box = $("<span class='animated " + opt.animateIn + " cpt-toast'></span>").css({
                    "position": opt.position,
                    "padding": opt.padding,
                    "background": opt.background,
                    "font-size": opt.fontSize,
                    "-webkit-border-radius": opt.borderRadius,
                    "-moz-border-radius": opt.borderRadius,
                    "border-radius": opt.borderRadius,
                    "color": opt.color,
                    "top": top,
                    "z-index": opt.zIndex,
                    "-webkit-transform": 'translate3d(-50%,-50%,0)',
                    "-moz-transform": 'translate3d(-50%,-50%,0)',
                    "transform": 'translate3d(-50%,-50%,0)',
                    '-webkit-animation-duration': opt.animateDuration / 1000 + 's',
                    '-moz-animation-duration': opt.animateDuration / 1000 + 's',
                    'animation-duration': opt.animateDuration / 1000 + 's',
                    'line-height': opt.fontSize + 'px'
                }).html(opt.content).appendTo($this);
                defaults.colseMessage();
            };

            defaults.colseMessage = function () {
                let isLowerIe9 = defaults.isLowerIe9();
                if (!isLowerIe9) {
                    t = setTimeout(function () {
                        box.removeClass(opt.animateIn).addClass(opt.animateOut).on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            box.remove();
                        });
                    }, opt.duration);
                } else {
                    t = setTimeout(function () {
                        box.remove();
                    }, opt.duration);
                }
            };

            defaults.createMessage();
        })
    };
})(jQuery, window);

jQuery.extend({
    /**
     * 显示toast
     * @param text 显示内容
     */
    toast: function (options) {
        let defaults = {
            //打印的内容
            'text': '服务器繁忙，请稍后再试',
            'isCenter': true,
            'fontSize': 24,
        };

        // 初始化设置
        let initSettings = $.extend(defaults, options);


        $('body').toast({
            position: 'fixed',
            content: initSettings.text,
            duration: 2000,
            isCenter: initSettings.isCenter,
            animateIn: 'bounceIn-hastrans',
            animateOut: 'bounceOut-hastrans',
            fontSize: initSettings.fontSize
        });
        $.removeLoading();
    }

});


(function ($) {

    $.fn.shCircleLoader = function (first, second) {

        let defaultNamespace = "shcl",
            id = 1,
            sel = $(this);

        // Destroy the loader
        if (first === "destroy") {
            sel.find("." + defaultNamespace).detach();
            return;

            // Show progress status into the center
        } else if ((first === "progress") && (typeof second !== "undefined")) {
            sel.each(function () {
                let el = $(this),
                    outer = el.find('.' + defaultNamespace);
                if (!outer.get(0))
                    return;
                if (!el.find('span').get(0))
                    outer.append("<span></span>");
                let span = outer.find('span').last();
                span.html(second).css({
                    position: "absolute",
                    display: "block",
                    left: Math.round((outer.width() - span.width()) / 2) + "px",
                    top: Math.round((outer.height() - span.height()) / 2) + "px"
                });
            });
            return;
        }

        // Default options
        let o = {
            namespace: defaultNamespace,
            radius: "auto", // "auto" - calculate from selector's width and height
            dotsRadius: "auto",
            color: "auto", // "auto" - get from selector's color CSS property; null - do not set
            dots: 12,
            duration: 1,
            clockwise: true,
            externalCss: false, // true - don't apply CSS from the script
            keyframes: '0%{{prefix}transform:scale(1)}80%{{prefix}transform:scale(.3)}100%{{prefix}transform:scale(1)}',
            uaPrefixes: ['o', 'ms', 'webkit', 'moz', '']
        };

        $.extend(o, first);

        // Usable options (for better YUI compression)
        let cl = o.color,
            ns = o.namespace,
            dots = o.dots,
            eCss = o.externalCss,
            ua = o.uaPrefixes,

            // Helper functions
            no_px = function (str) {
                return str.replace(/(.*)px$/i, "$1");
            },

            parseCss = function (text) {
                let i, prefix, ret = "";
                for (i = 0; i < ua.length; i++) {
                    prefix = ua[i].length ? ("-" + ua[i] + "-") : "";
                    ret += text.replace(/\{prefix\}/g, prefix);
                }
                return ret;
            },

            prefixedCss = function (property, value) {
                let ret = {};
                if (!property.substr) {
                    $.each(property, function (p, v) {
                        $.extend(ret, prefixedCss(p, v));
                    });
                } else {
                    let i, prefix;
                    for (i = 0; i < ua.length; i++) {
                        prefix = ua[i].length ? ("-" + ua[i] + "-") : "";
                        ret[prefix + property] = value;
                    }
                }
                return ret;
            };

        // Get unexisting ID
        while ($('#' + ns + id).get(0)) {
            id++;
        }

        // Create animation CSS
        if (!eCss) {
            let kf = o.keyframes.replace(/\s+$/, "").replace(/^\s+/, "");

            // Test if the first keyframe (0% or "from") has visibility property. If not - add it.
            if (!/(\;|\{)\s*visibility\s*\:/gi.test(kf))
                kf = /^(0+\%|from)\s*\{/i.test(kf)
                    ? kf.replace(/^((0+\%|from)\s*\{)(.*)$/i, "$1visibility:visible;$3")
                    : (/\s+(0+\%|from)\s*\{/i.test(kf)
                        ? kf.replace(/(\s+(0+\%|from)\s*\{)/i, "$1visibility:visible;")
                        : ("0%{visibility:visible}" + kf));

            $($('head').get(0) ? 'head' : 'body').append('<style id="' + ns + id + '" type="text/css">' + parseCss('@{prefix}keyframes ' + ns + id + '_bounce{' + kf + '}') + '</style>');
        }

        // Create loader
        sel.each(function () {
            let r, dr, i, dot, rad, x, y, delay, offset, css, cssBase = {}, el = $(this),
                l = el.find('.' + defaultNamespace);

            // If loader exists, destroy it before creating new one
            if (l.get(0))
                l.shCircleLoader("destroy");

            el.html('<div class="' + ns + ((ns != defaultNamespace) ? (" " + defaultNamespace) : "") + '"></div>');

            if (eCss)
                el = el.find('div');

            x = el.innerWidth() - no_px(el.css('padding-left')) - no_px(el.css('padding-right'));
            y = el.innerHeight() - no_px(el.css('padding-top')) - no_px(el.css('padding-bottom'));

            r = (o.radius == "auto")
                ? ((x < y) ? (x / 2) : (y / 2))
                : o.radius;

            if (!eCss) {
                r--;
                if (o.dotsRadius == "auto") {
                    dr = Math.abs(Math.sin(Math.PI / (1 * dots))) * r;
                    dr = (dr * r) / (dr + r) - 1;
                } else
                    dr = o.dotsRadius;

                el = el.find('div');

                i = Math.ceil(r * 2);
                css = {
                    position: "relative",
                    width: i + "px",
                    height: i + "px"
                };

                if (i < x)
                    css.marginLeft = Math.round((x - i) / 2);
                if (i < y)
                    css.marginTop = Math.round((y - i) / 2);

                el.css(css);

                i = Math.ceil(dr * 2) + "px";
                cssBase = {
                    position: "absolute",
                    visibility: "hidden",
                    width: i,
                    height: i
                };

                if (cl !== null)
                    cssBase.background = (cl == "auto") ? el.css('color') : cl;

                $.extend(cssBase, prefixedCss({
                    'border-radius': Math.ceil(dr) + "px",
                    'animation-name': ns + id + "_bounce",
                    'animation-duration': o.duration + "s",
                    'animation-iteration-count': "infinite",
                    'animation-direction': "normal"
                }));
            }

            for (i = 0; i < dots; i++) {
                el.append("<div></div>");
                if (eCss && (typeof dr === "undefined"))
                    dr = (no_px(el.find('div').css('width')) / 2);
                dot = el.find('div').last();
                delay = (o.duration / dots) * i;
                rad = (2 * Math.PI * i) / dots;
                offset = r - dr;
                x = offset * Math.sin(rad);
                y = offset * Math.cos(rad);

                if (o.clockwise) y = -y;

                css = {
                    left: Math.round(x + offset) + "px",
                    top: Math.round(y + offset) + "px"
                };

                if (delay)
                    $.extend(css, prefixedCss('animation-delay', delay + 's'));

                $.extend(css, cssBase);
                dot.css(css);
            }
            ;
        });
    }

})(jQuery);


jQuery.extend({
    /**
     * 创建loading框
     * @param color 颜色
     * @param width 宽度
     * @param height 高度
     */
    createLoading: function (options) {
        let defaults = {
            'color': '#33CD5F',
            'size': '150',
            'isBg': false
        };
        // 初始化设置
        let initSettings = $.extend(defaults, options);

        let loadingHtml = "<div id='loading' style=\"font-size: " + initSettings.size + "px; position: fixed;top: 0;bottom: 0;height: 100%;z-index: 999999999999;width: 100%;display: flex;align-items: center;justify-content:center;\">\n" +
            "    <div style=\"width: 2em;height: 2em; background-color: rgba(0,0,0," + (initSettings.isBg ? '0.8' : '0') + ");border-radius: 10px;position: absolute;\">\n" +
            "\n" +
            "    </div>\n" +
            "    <div id=\"shclColor\" style=\"width: 1em; height: 1em; margin: 0 auto;position: absolute;\"></div>\n" +
            "</div>";

        $.removeLoading();
        $("span[class='animated bounceIn-hastrans cpt-toast']").remove();
        $("body").append(loadingHtml).css({"height": "100vh", "overflow": "hidden"});
        $('#shclColor').shCircleLoader({color: initSettings.color});

    },
    removeLoading: function () {
        $("body").css({"height": "", "overflow": ""});
        $('#loading').remove();
    }
});