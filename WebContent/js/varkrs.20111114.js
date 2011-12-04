;$(function(){
	var x_index = 0;
	var y_index = 0;
	var $container = $("section");
	var classArray = ['a','b','c','d','e','f','g','h','i'];
	var images = '';
	function randomClass(){
		var random = Math.round(Math.random() * 100);
		var length = classArray.length;
		return classArray[random % length];
	}
	function calStyle(){
		var style = "style=";
		style += "left:"+(x_index * 320 + 30)+"px;";
		style += "top:"+(y_index * 320 + 10)+"px;";
		x_index ++;
		if(x_index == 3){
			y_index ++;
			x_index = 0;
		}
		return style;
	}
	function addImage(logo,userid,name,style){
		var $person='<a '+style+' class='+randomClass()+' href='+'user.html?'+userid
		+'><article>'+'<img class="scrollloading" src=imgs/fake.png data-url='+photowall.allPhotosAPI() + '/' + logo+' />'+'<span>'+name
		+'</span>'+'</article></a>';
		images += $person;
	}
	function addImages(){
		$container.append(images);
	}
	$.ajax({
		url:photowall.allUsersAPI(),
		type:'get',
		dataType:'xml',
		timeout:1000,
		success:function(xml){
			$(xml).find('varkrs').each(function(i){
				var $this = $(this);
				var userid = $this.attr('id');
				var name = $this.attr('name');
				var logo = $this.attr('logo');
				var style = calStyle();
				addImage(logo,userid,name,style);
			});
			addImages();
			$("section a").click(function(){
				if(zpw.isLogin()){
					return true;
				}else{
					$("#needslogin").load("login.html");
					return false;
				}
			});
			$container.height((y_index + 1)*280);
			$(".scrollloading").scrollLoading();
		}
	});
	
});