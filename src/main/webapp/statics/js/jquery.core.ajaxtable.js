var dataIds = [];
var trBackgroundUnChecked = {
	"background" : ""
};
var background = "rgb(239, 239, 224)";
var trBackgroundChecked = {
	"background" : "rgb(239, 239, 224)"
};

//按升序排列
var _sort = function (arr) {
	for (i = 0; i < arr.length - 1; i++) {
		for (j = 0; j < arr.length - 1 - i; j++) {
			if (arr[j] > arr[j + 1]) {
				var temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}
	return arr;
}

function sortarr(data) {
	var arrs = [], datas = [];
	for (var index in data) {
		if (!data[index]) arrs.push(-1);
		else arrs.push(data[index]);
	}
	_sort(arrs);
	for (var i = 0; i < arrs.length; i++) {
		for ( var d in data) {
			if (!data[d] && i == 0) {
				datas.push({ name : d, value : '' })
				continue;
			}
			if (arrs[i] == data[d]) {
				datas.push({ name : d, value : arrs[i] });
				continue;
			}
		}
	}
	return datas;
}

(function($) {
	
	/**
	 * 下拉选择
	 */
	$.fn.select = function(options) {
		var settings = $.extend({
			data : {},
			selectId : '',
			checkValue:''
		}, options || {});
		var html = "";
		var datas = sortarr(settings.data);
		for (var i = 0; i < datas.length; i++) {
			var data = datas[i];
			if(settings.checkValue && data.value == settings.checkValue) {
				html += "<option value=\"" + data.value + "\" selected=\"selected\">" + data.name + "</>";
			} else
				html += "<option value=\"" + data.value + "\">" + data.name + "</>";
		}
		$(this).html(html);
	}
	/**
	 * 列表组件
	 */
	$.fn.dataGrid = function(options) {
		var settings = $.extend({
			title : '',
			url : '',
			method : 'POST',
			pageSize : 10,
			tableId : '',
			searchButtonId : '',
			pageShow : false,
			dataType : 'JSON',
			checkbox : false,
			single: false,
			checkedIds: [],
			orderField : 'id',
			sort : 'desc',
			queryParamsId : [],
			columns : [ {
				field : '',
				className : 'text-l',
				description : '',
				sort : false,
				paramFormatter : function() {
					return '';
				}
			} ],
			onLoadSuccess : undefined
		}, options || {});
		var array = settings.checkedIds;
		for (var i = 0; i < array.length; i++) {
			var a = array[i];
			if (a && a != null && a != "null") dataIds.push(a); 
		}
		dataIds = settings.checkedIds;
		var tableId = settings.tableId.substring(1);
		var title = tableId + "-tableTitle";
		$("#" + title).remove();
		$(this).before("<div id=\"" + title + "\" style=\"background:#f2f2f2;height:30px;line-height:30px;\"><span style=\"margin-left:7px;font-weight:bold\">" + settings.title + "</span><input type=\"hidden\" value=\"1\" id=\"" + tableId + "_currentPage\" name=\"" + tableId + "_currentPage\"/></div>");
		_create_table_head(settings);
		if (settings.searchButtonId) {
			$(settings.searchButtonId).click(function() {
				$.openLoading();
				var tableId = settings.tableId.substring(1);
				$("#" + tableId + "_currentPage").val(1);
				setTimeout(function() {
					_query_data_ajxa(settings);
					_select_checkbox_all();
				}, 200);
			});
		}
		$.openLoading('正在加载数据，请稍等...');
		setTimeout(function() {
			_query_data_ajxa(settings);
			_select_checkbox_all();
		}, 500);
		var queryParamsIds = settings.queryParamsId;
		console.log(queryParamsIds.length);
		for(var index in queryParamsIds) {
			$(queryParamsIds[index]).keyup(function() {
				$.openLoading();
				_query_data_ajxa(settings);
				_select_checkbox_all();
			});
			$(queryParamsIds[index]).change(function() {
				$.openLoading();
				_query_data_ajxa(settings);
				_select_checkbox_all();
			});
		}
	}
})(jQuery);

var _create_table_head = function(settings) {
	var trStr = "",
		th = "";
	if (settings.checkbox && !settings.single) {
		if (settings.checkedIds.length == settings.pageSize) {
			th += "<th style=\"width:25px;\"><input style=\"width: 15px;height: 18px;\" checked=\"checked\" type=\"checkbox\" id=\"ckAll\"/></th>";
		} else
			th += "<th style=\"width:25px;\"><input style=\"width: 15px;height: 18px;\" type=\"checkbox\" id=\"ckAll\"/></th>";
	} else if(settings.checkbox){
		th += "<th style=\"width:25px;\"></th>";
	}
	var array = settings.columns;
	for (var i = 0; i < array.length; i++) {
		var className = array[i].className;
		var key = array[i].field;
		var description = array[i].description ? array[i].description : "";
		if ('id' != key) {
			if (array[i].sort) {
				th += "<th class=\"" + className + "\">" + description + "&nbsp;&nbsp;<a data-field=\"" + key + "\" class=\"sort sorting\" href=\"#\"></a></th>";
			} else
				th += "<th class=\"" + className + "\">" + description + "</th>";
		}
	}
	var tableId = settings.tableId.substring(1);
	var theadId = tableId + "-data-head";
	trStr += "<thead id=\"" + theadId + "\" class=\"data-head\"><tr>" + th + "</tr></thead>";
	var _body_id = tableId + "-data-body";
	trStr += "<tbody id=\"" + _body_id + "\"></tbody>";
	$(settings.tableId).html(trStr);
	_order_by_sort(settings, theadId);
};

/**
 * 排序
 */
var _order_by_sort = function(settings, theadId) {
	var dex = -1;
	$("#" + theadId).find('tr>th').each(function(index) {
		var a = $(this).find('a');
		if ($(a).hasClass('sort')) {
			$(a).click(function() {
				if (dex != index) {
					$("#" + theadId).find('tr>th').each(function(d) {
						var i = $(this).index();
						if (i == dex) {
							$(this).find('a').addClass('sorting').removeClass('sorting_desc').removeClass('sorting_asc').removeClass('actived');
						}
					});
					dex = index;
				}
				if ($(this).hasClass('sorting_asc')) {
					$(this).removeClass('sorting_asc').removeClass('sorting').addClass('sorting_desc actived');
					$(this).parent().addClass('actCls');
					$(this).parent().siblings().removeClass('actCls');
					settings.sort = 'desc';
				} else if ($(this).hasClass('sorting_desc')) {
					$(this).removeClass('sorting_desc').removeClass('sorting').addClass('sorting_asc actived');
					$(this).parent().addClass('actCls');
					$(this).parent().siblings().removeClass('actCls');
					settings.sort = 'asc';
				} else if ($(this).hasClass('sorting')) {
					$(this).removeClass('sorting').addClass('sorting_desc actived');
					$(this).parent().addClass('actCls');
					$(this).parent().siblings().removeClass('actCls');
					settings.sort = 'desc';
				}
				settings.orderField = $(a).attr('data-field');
				$.openLoading('正在加载数据，请稍等...');
				setTimeout(function() {
					_query_data_ajxa(settings);
					_select_checkbox_all();
				}, 200);
			});
		}
	});
}
//获取所有的查询条件
var _get_query_condition = function(settings) {
	var ids = settings.queryParamsId;
	var jsonStr = "{";
	for (var i in ids) {
		var ff = undefined;
		try {
			ff = ids[i].substring(1);
			var val = $(ids[i]).val();
			if (!val) {
				val = "";
			}
			jsonStr += '"' + ff + '":"' + val + '",';
		} catch (e) {
			
		}
		
	}
	jsonStr = (jsonStr.length > 1 ? jsonStr.substring(0, jsonStr.length - 1) : jsonStr) + "}";
	var object = eval("(" + jsonStr + ")");
	object.order = settings.orderField;
	object.reorder = settings.sort;
	return object;
};
// 查询服务器数据
var _query_data_ajxa = function(settings) {
	var params = _get_query_condition(settings);
	var tableId = settings.tableId.substring(1);
	params.pageSize = settings.pageSize;
	params.currentPage = $("#" + tableId + "_currentPage").val();
	$.ajax({
		method : settings.method,
		url : settings.url,
		async : false,
		data : params,
		dataType : settings.dataType,
		success : function(data) {
			$.closeLoading();
			if (data.responseCode == 200) {
				data = data.object;
				_create_grid_table(data, settings);
				var func = settings.onLoadSuccess;
				if(func) {
					var fn = eval(func);
					fn.call(this, data.content ? data.content : data.map, $(settings.tableId));
				}
			} else
				$.openTip(data.responseMessage, true, function() {
					$.closeLoading();
				});
		},
		error : function(err) {
			$.openTip('获取数据失败，请稍后再试...', true, function() {
				$.closeLoading();
			});
		}
	});
};

var _each_value = function(key, json) {
	var ks = key.split(".");
	if (ks.length > 1) {
		var k = ks[0];
		var jsonStr = json[k];
		if (json instanceof Object) {
			var v = _each_value(ks[1], jsonStr);
			return v;
		}
	} else
		return json ? json[key] : "";
}
// 创建表格
var _create_grid_table = function(data, settings) {
	var tableId = settings.tableId.substring(1);
	var objs;
	try {
		objs = data.content
		if (!objs)
			objs = data.maps;
	} catch (e) {
		objs = data.maps;
	}
	var trStr = "";
	var array = settings.columns;
	var num = 0,
		len;
	for (var j = 0; len = objs.length, j < len; j++) {
		var jsonStr = eval(objs[j]);
		var bool = _is_exsit(dataIds, jsonStr['id']);
		if(bool)
			trStr += '<tr data=\'' + JSON.stringify(jsonStr) + '\' style=\'background:rgb(239, 239, 224)\' data-id ="' + jsonStr["id"] + '">';
		else 
			trStr += '<tr data=\'' + JSON.stringify(jsonStr) + '\' data-id ="' + jsonStr["id"] + '">';
		var tdObj = "";
		for (var i = 0; i < array.length; i++) {
			var key = array[i].field;
			var value = _each_value(key, jsonStr);
			var paramVal_ = _each_value(key, jsonStr);
			if (array[i].paramFormatter) {
				value = array[i].paramFormatter;
				var fn = eval(value);
				value = fn.call(this, jsonStr, paramVal_);
			}
			if (settings.checkbox && i == 0) {
				var checked = value ? '' : '';
				if (!settings.single) {
					if (bool)
						tdObj += "<td style=\"width:25px;\"><input checked=\"checked\" data-id=\"" + jsonStr['id'] + "\" " + checked + " style=\"width: 15px;height: 18px;\" type=\"checkbox\" name=\"checkOne\"/></td>";
					else
						tdObj += "<td style=\"width:25px;\"><input data-id=\"" + jsonStr['id'] + "\" " + checked + " style=\"width: 15px;height: 18px;\" type=\"checkbox\" name=\"checkOne\"/></td>";
				} else {
					if (bool)
						tdObj += "<td style=\"width:25px;\"><input checked=\"checked\" data-id=\"" + jsonStr['id'] + "\" " + checked + " style=\"width: 15px;height: 18px;\" type=\"radio\" name=\"checkOne\"/></td>";
					else
						tdObj += "<td style=\"width:25px;\"><input data-id=\"" + jsonStr['id'] + "\" " + checked + " style=\"width: 15px;height: 18px;\" type=\"radio\" name=\"checkOne\"/></td>";
				}
			}
			if (key != 'id') {
				if (!value) {
					value = "";
				}
				tdObj += "<td style=\"text-overflow: clip;white-space: pre-wrap;\" class=\"" + array[i].className + "\">" + value + "</td>";
			}
		}
		trStr += tdObj + "</tr>";
	}
	if (settings.pageSize == 0) {
		throw "pageSize 必须大于0,初始化datagrid时必须设定pageSize的值";
	}
	var pagerId = tableId + "-box"
	if (data.totalPage > 0) {
		var curr = (data.currentPage - 1) * settings.pageSize + 1;
		var a = data.currentPage * settings.pageSize > data.totalRecord ? data.totalRecord : data.currentPage * settings.pageSize;
		var pos = data.currentPage > 0 ? a : (1 * settings.pageSize);
		trStr += "<tr class=\"borpage-none\"><td class=\"bor-none\" colspan=\"2\">显示 " + curr + " 到 " + pos + " ，共 " + data.totalRecord + " 条</td><td class=\"bor-none tdpage-pos\" colspan=\"" + (array.length - 1) + "\"><div id=" + pagerId + " style=\"height: 30px;\" class=\"M-box r\"></div></td></tr>"
	} else if (objs.length == 0)
		trStr += "<tr class=\"borpage-none\"><td class=\"bor-none tdpage-pos text-r\" style=\"\" colspan=\"" + (array.length + 1) + "\">没有符合条件的数据</div></td></tr>"
	var _tb_id = tableId + "-data-body";
	/*$("#" + tableId +"_currentPage").val('');*/
	/*$("#" + _tb_id).empty();*/
	$("#" + _tb_id).html(trStr);
	$.closeLoading();
	$('#' + pagerId).pagination({
		totalData : data.totalRecord,
		showData : settings.pageSize,
		coping : true,
		isHide : false,
		current : data.currentPage,
		callback : function(api) {
			var currentPage = api.getCurrent();
			$("#" + tableId + "_currentPage").val(currentPage);
			$.openLoading('正在加载数据，请稍等...');
			setTimeout(function() {
				_query_data_ajxa(settings);
				_select_checkbox_all();
				var num = 0;
				$('input[name="checkOne"]').each(function() {
					var bo = $(this).is(":checked");
					if(bo) num ++;
				})
				if (num == settings.pageSize) {
					$('#ckAll').prop("checked", true);
				} else {
					$('#ckAll').prop("checked", false);
				}
			}, 200)
		}
	});
	jQuery("input[name='checkOne']").each(function() {
		$(this).click(function() {
			var id = $(this).attr('data-id');
			if ($(this).is(":checked") && settings.single) {
				dataIds = [];
				dataIds.push(id);
			} else {
				if ($(this).is(":checked")) {
					if (!_is_exsit(dataIds, id)) {
						$(this).parent().parent().css(trBackgroundChecked);
						dataIds.push(id);
					}
				} else {
					dataIds = removeObjToArray(dataIds, id);
					$(this).parent().parent().css(trBackgroundUnChecked);
				}
			}
		})
	})
};

var removeObjToArray = function(array, obj) {
	var arrs = [];
	if (array.length > 0) {
		for (var i = 0; i < array.length; i++) {
			var id = array[i];
			if (id != obj) {
				arrs.push(id);
			}
		}
	}
	return arrs;
}

/**
 * 判断ID是否存在
 */
var _is_exsit = function(array, obj) {
	var isEqual = false;
	if (array.length > 0) {
		for (var i = 0; i < array.length; i++) {
			var id = array[i];
			if (id == obj) {
				isEqual = true;
				break;
			}
		}
	}
	return isEqual;
}

/**
 * checkbox全选/反选
 */
var _select_checkbox_all = function() {
	$("#ckAll").click(function() {
		if ($(this).is(":checked")) {
			var that = this;
			$("input[name='checkOne']").each(function() {
				var id = $(this).attr('data-id');
				var disabled = $(this).attr('disabled');
				if (disabled == undefined) {
					$(this).prop("checked", that.checked);
					$(this).parent().parent().css(trBackgroundChecked)
					if ($(this).is(":checked")) {
						if (!_is_exsit(dataIds, id)) {
							dataIds.push(id);
						}
					} else {
						dataIds = removeObjToArray(dataIds, id)
					}
				}
			});
		} else {
			$("input[name='checkOne']").parent().parent().css(trBackgroundUnChecked);
			$("input[name='checkOne']").prop("checked", false);
			$("input[name='checkOne']").each(function() {
				var id = $(this).attr('data-id');
				dataIds = removeObjToArray(dataIds, id);
			});
		}
	});

	$("input[name='checkOne']").click(function() {
		var $subs = $("input[name='checkOne']");
		$("#ckAll").prop("checked", $subs.length == $subs.filter(":checked").length ? true : false);
	});
};
