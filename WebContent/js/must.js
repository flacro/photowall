window.zpw = {
		isLogin:function(){
			if(!!$.cookie('user')){
				return true;
			}
			return false;
		}
};