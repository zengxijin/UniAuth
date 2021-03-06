// common function for cas
var context_path = $('#hidden_path_input').val();

// i18n lang set
function i18nset(localestr) {
	if (!localestr) {
		return

	}
	;
	var url = context_path + "/uniauth/i18n/setLanguage?locale=" + localestr;
	$.get(url, function(data) {
		// refresh
		window.location.href = window.location.href;
	});
}

// get window location string‘s parameter part
function getLocationParameterStr() {
	// copy current url
	var current_url = window.location.search;
	// 只取参数部分
	var pindex = current_url.indexOf("?");
	if (pindex > -1) {
		current_url = current_url.substring(pindex + 1);
	}
	return current_url;
}

// refresh capatcha
var refresh_verfypic = function(capatchElement) {
	if (!capatchElement) {
		return;
	}
	// context
	if (context_path == undefined) {
		return;
	}
	$(capatchElement).attr('src',
			context_path + '/uniauth/captcha?rnd=' + Math.random());
}

//cookie operation function
var cookieOperation = (function(){
	var cookie_set = function(name, value, exdays) {
		if (!name)return;
		// default, 10 year
		exdays = exdays || 365 * 10;
		var d = new Date();
		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toUTCString();
		var path='path=/';
		document.cookie = name + "=" + value + "; " + expires+';'+path;
	};
	var cookie_get = function(cname) {
		if(!cname)return;
		var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') 
	        	c = c.substring(1);
	        if (c.indexOf(name) != -1) 
	        		return c.substring(name.length, c.length);
	    }
	    return "";
	};
	var cookie_service = "cas_cookie_service";
	var cookie_tenancy_code = "cas_cookie_tenancy_code";
	var obj = {
			setService: function(service){cookie_set(cookie_service, service);},
			getService: function(){return cookie_get(cookie_service)},
			setTenancyCode:function(tenancyCode){cookie_set(cookie_tenancy_code, tenancyCode);},
			getTenancyCode:function(){return cookie_get(cookie_tenancy_code)}
	};
	return obj;
})();

// log
var logOperation = (function(){
	var obj = {
			log: function(msg){
				if (console && console.log) {
					console.log(msg);
				}
			},
			error: function(msg){
				if (console && console.error) {
					console.error(msg);
				}
			}
	};
	return obj;
})();

$(function() {
	var loadI18n = function() {
		var url = context_path + "/uniauth/i18n/getLanguage";
		var i18nName = "messages";
		$.get(url, function(data) {
			var i18npath = context_path + '/i18n/';
			jQuery.i18n.properties({
				name : i18nName,
				path : i18npath,
				mode : 'map',
				language : data
			});
		});
	}
	
	var set_tenancy_info = function(tenancy) {
		if(!tenancy){
			logOperation.error('set tenancy info, the tenancy can not be null');
			return;
		}
		$('#tenancy_set').text(tenancy.name);
		cookieOperation.setTenancyCode(tenancy.code);
	}
	
	// 租户code cookie校验
	var tenancy_code_cookie_init = function() {
		// 回调函数  用于设置查询到的租户信息
		var callBackInit = function(usefulTenancy) {
			set_tenancy_info(usefulTenancy);
			// enable login btn
			$('.enable-after-init-success').removeAttr('disabled');
		};
		var query_default_tenancy = function(){
			var url = context_path + "/uniauth/tenancy/getDefault";
			$.ajax({
				type: 'GET',
				url : url,
				dataType: 'json',
				success : function(data) {
					if(data.success && data.success !== 'false') {
						callBackInit(data.content);
					} else {
						logOperation.log('init failed, can not find default tenancy')
						alert('sever error, please refresh page');
					}
				},
				error: function() {
					alert('sever error, please refresh page');
				}
			});
		};
		var check_tenancy_code = function(tenancyCode){
			var url = context_path + "/uniauth/tenancy/check/"+tenancyCode;
			$.ajax({
				type: 'GET',
				url : url,
				dataType: 'json',
				success : function(data) {
					if(data && data.content) {
						callBackInit(data.content);
					} else {
						logOperation.log('init failed, server return wrong info, ' + data);
						alert('sever error, please refresh page');
					}
				},
				error: function() {
					alert('sever error, please refresh page');
				}
			});
		};
		// query tenancy_code from cookie
		var tenancy_code_cookie = cookieOperation.getTenancyCode();
		if(!tenancy_code_cookie) {
			// query default tenancy
			query_default_tenancy();
		} else {
			// check tenancy_code
			check_tenancy_code(tenancy_code_cookie);
		}
	};
	// 租户code选择编辑
	var bind_tenancy_select_event = function() {
		var tenancy_show = function(show_set) {
			if (show_set) {
				$('#tenancy_show').addClass('hiddenbtn');
				$('#tenancy_select').removeClass('hiddenbtn');
				$('#input_tenancy_code').focus();
			} else {
				$('#tenancy_select').addClass('hiddenbtn');
				$('#tenancy_show').removeClass('hiddenbtn');
			}
		}
		$('#tenancy_set').click(function(e) {
			tenancy_show(true);
			if (e && e.stopPropagation) {
				e.stopPropagation();
			}
		});
		$('#btn_confirm_tenancy').click(function(e) {
			// check tenancy_code
			var check_tenancy_code = $('#input_tenancy_code').val();
			if (!check_tenancy_code) {
				return;
			}
			// tenancy_code check
			var url = context_path + "/uniauth/tenancy/check/"+check_tenancy_code;
			$.get(url, function(data) {
				if(data.success && data.success !== 'false') {
					set_tenancy_info(data.content);
					tenancy_show(false);
					$('#input_tenancy_code').val('');
				} else {
					// failed, show the warn
					$('#warn_info').text(data.msg);
				}
				if (e && e.stopPropagation) {
					e.stopPropagation();
				}
			}, 'json');
		});
		$(document).bind("click", function(e) {
			if ($('#tenancy_select').hasClass('hiddenbtn')) {
				return;
			}
			e = e || window.event;
			var target = e.target || e.srcElement;
			target = $(target);
			if (!(target.parents('#tenancy_select').length > 0)) {
				tenancy_show(false);
			}
		});
	}
	// load i18n
	loadI18n();
	tenancy_code_cookie_init();
	bind_tenancy_select_event();
});
