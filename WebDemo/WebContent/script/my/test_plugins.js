(function($){ 
   //对象方法1
   $.fn.format = function(options){ 
	   var opts = $.extend({},$.fn.format.defaults,options);
       $(this).css(opts);
   };
   
   //对象默认属性，开放api 1
   $.fn.format.defaults1 = {   
      background: 'yellow',
      color: 'red'  
   };
   
   
 //对象默认属性，开放api 2
   $.extend($.fn.format,{
	   defaults :{
		   background: 'yellow',
		   color: 'red'
	   }
   });
   
  //对象方法2
   $.extend($.fn, {
	   format_new : function(options){ 
		   this.format({});
		   $(this).method1();
	   },
	   method1 : function(){ 
	   }
   });
   
  //类方法1
   $.extend({
	   global_method : function(){
		   alert('global_method');
		   $.global_method1();
	   }
   });
   
 //类方法2
   $.global_method1 = function(){
	   alert('global_method1');
   };
}
)(jQuery);