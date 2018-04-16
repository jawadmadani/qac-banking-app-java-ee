$(document).ready(function(){

	$(".body_content").addClass("content_open_menu")

	$(".fa-times").click(function(){
		$(".sidebar_menu").addClass("hide_menu");
		$(".toggle_menu").addClass("opacity_one");
        $(".body_content").removeClass("content_open_menu");
	});

	$(".toggle_menu").click(function(){
		$(".sidebar_menu").removeClass("hide_menu");
		$(".toggle_menu").removeClass("opacity_one");
        $(".body_content").addClass("content_open_menu");

	});
});