/**
 * Created by Anna on 2015/9/22.
 * Common functions
 */
$(function(){
    $(".cancel").click(function(){
        var popdivid = $(this).parents('div').parents('div').attr("id");
        closePop('.cover','#'+popdivid);
    });
});
/**
 * Pop the cover div and pop div
 * @param cover is cover div id or class
 * @param popivid is pop div id or class
 */
function popdiv(cover, popivid){
    var bh = $("body").outerHeight();
    var bw = $("body").outerWidth();
    $(cover).css({
        height:bh,
        width:bw,
        display:"block"
    }).fadeIn("slow");
    var $pop = $(popivid);
    $pop.css({
        left: ($("body").width() - $pop.outerWidth())/2,
        top: ($("body").height() - $pop.outerHeight())/2 + $(document).scrollTop()
    }).show();
    $("body").append($(cover),$pop);
}
/**
 * Close the cover div and pop div
 * @param cover is cover div id or class
 * @param popdivid is pop div id or class
 */
function closePop(cover,popdivid){
    $(cover).hide();
    $(popdivid).hide();
}

