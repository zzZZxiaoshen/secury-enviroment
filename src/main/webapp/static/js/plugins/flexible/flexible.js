/**
 * 动态计算font-size
 * @param {Object} doc
 * @param {Object} win
 */
(function (doc, win) {
    // 得到文档的根元素html，计算网页所打开时屏幕的真实宽度
    let docEl = doc.documentElement,
        // 屏幕垂直、水平方向有改变进行html的根元素计算
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            // clientWidth 是我们页面打开时所得到的屏幕（可看见页面的真实宽度）宽度真实的宽度值
            let clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            // 750是我们默认的手机屏幕
            docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
        };
    if (!doc.addEventListener) return;
    // 改变方向执行函数
    win.addEventListener(resizeEvt, recalc, false);
    // DOMContentLoaded，当dom加载完成时执行函数
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);