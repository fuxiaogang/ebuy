(function($){ 
   //���󷽷�1
   $.fn.format = function(options){ 
	   var opts = $.extend({},$.fn.format.defaults,options);
       $(this).css(opts);
   };
   
   //����Ĭ�����ԣ�����api 1
   $.fn.format.defaults1 = {   
      background: 'yellow',
      color: 'red'  
   };
   
   
 //����Ĭ�����ԣ�����api 2
   $.extend($.fn.format,{
	   defaults :{
		   background: 'yellow',
		   color: 'red'
	   }
   });
   
  //���󷽷�2
   $.extend($.fn, {
	   format_new : function(options){ 
		   this.format({});
		   $(this).method1();
	   },
	   method1 : function(){ 
	   }
   });
   
  //�෽��1
   $.extend({
	   global_method : function(){
		   alert('global_method');
		   $.global_method1();
	   }
   });
   
 //�෽��2
   $.global_method1 = function(){
	   alert('global_method1');
   };
}
)(jQuery);