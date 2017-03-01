/**
 * 匿名函数
 * jQuery的日期插件
 */
(function($){
	/** 添加对象的方法(批量) */
	$.fn.extend({
		/**
		 * 显示当前系统运行时间
		 */
		runtime: function(){
			// 2016年09月11日  星期日 10:10:11
			/** 创建日期对象 */
			var d = new Date();
			/** 定义数组 */
			var arr = new Array();
			/** 获取年 */
			arr.push(d.getFullYear() + "年");
			/** 获取月 0-11 */
			arr.push($.calc(d.getMonth() + 1) + "月");
			/** 获取日 */
			arr.push($.calc(d.getDate()) + "日");
			
			/** 星期几 0-6 (0:星期日 6:星期六) */
			arr.push("&nbsp;" + $.weeks[d.getDay()] + "&nbsp;");
			
			/** 获取小时 */
			arr.push($.calc(d.getHours()) + ":");
			/** 获取分钟 */
			arr.push($.calc(d.getMinutes()) + ":");
			/** 获取秒 */
			arr.push($.calc(d.getSeconds()));
			
			this.html(arr.join(""));
			
			var t = this;
			
			/** 开启定时器 */
			window.setTimeout(function(){
				// this : window
				t.runtime();
			}, 1000);
		},
		/**
		 * 倒计方法
		 * @param msg 消息
		 * @param time 时间
		 */
		downCount : function(msg, time){
			if (time >= 1){
				if (!$(this).attr("disabled")){
					$(this).attr("disabled", true);
				}
				time--;
				/** 获取消息 */
				var value = msg.replace("{0}", time);
				/** 设置显示的值 */
				$(this).val(value);
				var t = this;
				/** 开启延迟的定时器 */
				setTimeout(function(){
					t.downCount(msg, time);
				}, 1000);
			}else{
				$(this).attr("disabled", false).val("获取验证码");
			}
		}
	});
	
	/** 为jQuery添加静态方法 */
	$.extend({
		weeks : ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
		calc : function(str){
			return str > 9 ? str : "0" + str;
		}
	});
	/** 单个添加静态方法 */
	$.forEach = function(arr, callback){
		for (var i = 0; i < arr.length; i++){
			callback.call(window, i, arr[i]);
		}
	};
	
})(jQuery);