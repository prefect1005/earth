function _cancelBubble(event){
  var ie =navigator.appName=="Microsoft Internet Explorer"?true:false;
  e=event?event:window.event;
  if(ie) e.cancelBubble=true;
  else e.stopPropagation();
}
var Sys={};
var ua=navigator.userAgent.toLowerCase();
var s;
(s=ua.match(/msie ([\d.]+)/))?Sys.ie=s[1] :
(s=ua.match(/firefox\/([\d.]+)/))?Sys.firefox=s[1] :
(s=ua.match(/chrome\/([\d.]+)/))?Sys.chrome=s[1] :
(s=ua.match(/opera.([\d.]+)/))?Sys.opera=s[1] :
(s=ua.match(/version\/([\d.]+).*safari/))?Sys.safari=s[1]:0;
//以下进行测试
//if(Sys.ie) document.write('IE:'+Sys.ie);
//if(Sys.firefox) document.write('Firefox:'+Sys.firefox);
//if(Sys.chrome) document.write('Chrome:'+Sys.chrome);
//if(Sys.opera) document.write('Opera:'+Sys.opera);
//if(Sys.safari) document.write('Safari:'+Sys.safari);

function g(id){return document.getElementById(id);}

function YmdHi(){ 
	var now=new Date();
	var year=now.getFullYear();//年
	var month=now.getMonth()+1;//月
	var day=now.getDate();//日
	var hh=now.getHours();//时
	var mm=now.getMinutes();//分
	var clock=year+"-";
	if(month<10) clock+="0";
	clock+=month+"-";
	if(day<10) clock+="0";
	clock+=day+"";
	if(hh<10) clock+="0";
	clock+=hh+":";
	if(mm<10)clock+='0';
	clock+=mm;
	return(clock);
}

function getElementsByClassName(className,tagName){
	var classTarget;
	if(document.getElementsByClassName){
		classTarget = document.getElementsByClassName(className);
		return classTarget;
	}else{
		if(tagName == null){ tagName = "*"; }
		var tags = document.getElementsByTagName(tagName);
		var classTarget = new Array();
		for(var i=0,j=0; i<tags.length; i++){
			var attr_class_name = " " + tags[i].className + " ";        //加上" "只是为了给原class属性值左右加上一个空格符
			if(attr_class_name.indexOf(" " + className + " ") != -1){        //这里加上" "是为了让寻找的class是一个单独的class，避免出现找div3，却出现div345的现象。
				classTarget[j++] = tags[i];
			}
		}
		return classTarget;
	}
}

function Nsb(o){//兼容nextSibling
	if(!+[1,]) return o.nextSibling;
	else return o.nextSibling.nextSibling;
}
function Psb(o){//兼容previousSibling
	if(!+[1,]) return o.previousSibling;
	else return o.previousSibling.previousSibling;
}

function nodo(tb){
	if(typeof(tb)=="undefined"){
		var tbs=document.getElementsByTagName("table");
		var obj=tbs[0];
	}else var obj=g(tb);
	var hang=obj.rows.length;
	var lie=obj.rows[0].cells.length;
	for(var j=0;j<hang;j++){
		obj.rows[j].cells[lie-1].style.display='none';
	}
}

function pertotal(total){
	var url=window.location.href;
	window.location.href=url+'&perpage='+total+'&page=1';
}

function ocp(o){
	var page=o.value+'?'+o.id+'='+o.value
	window.location.href=addSID(page);
}

function addSID(url){
	if(url.indexOf('sid')!=-1) return url;
	if(url.indexOf('?')!=-1) return url+='&sid='+SID;
	else if(url.indexOf('htm')!=-1) return url+='?sid='+SID;
	else return url;
}

function close_if_open(id,parent){
	if(!g(id)) return;
	switch(parent){
		case 0:{document.body.removeChild(g(id));break;}
		case 2:{g(id).parentNode.parentNode.removeChild(g(id));break;}
		default:{g(id).parentNode.removeChild(g(id));break;}
	}	
}

function tr_add(id){
	var o=document.getElementById(id); 
	var newTR=o.cloneNode(true); 
	//newTR.id=id+(iii++);
	var input=newTR.getElementsByTagName('input');
	for(i=0;i<input.length;i++){
		input[i].value='';input[i].checked=false;
	}
	o.parentNode.appendChild(newTR); 
}
function tr_del(o,num){
	if(typeof(num)=="undefined") num=1;
	var tr=o.parentNode.parentNode;
	var trs=tr.parentNode.getElementsByTagName('tr');
	if(trs.length>num) tr.parentNode.removeChild(tr);
	else msg('不能再删啦！',o,0);
}

function setCookie(name,value,sec){
	if(sec>0){//没定时间则为随浏览器关闭而过期
		var exp=new Date();
		exp.setTime(exp.getTime() + sec*1000);
		document.cookie=name + "="+ escape (value) + ";path=/;expires=" + exp.toGMTString();
	}else document.cookie=name + "="+ escape (value);
}
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)) return unescape(arr[2]);
	else return null;
}
function getts(){
	var exp=new Date().getTime();
	return parseInt(exp/1000);
}
function online(sec){
	return;
	//if(typeof(sec)=="undefined") sec=5;
	setCookie('online','222',sec);
	timer_online=setInterval(function(){
			if(getCookie('online')!='222'){
				if(self!=top){
					if(self.name=='im') parent.win('login_win.htm','登录超时！',1);
					//如果im里面的页面调用了win，win里面的页面超时，则不用弹出，因为main.htm会先弹出
				}else win('../../login_win.htm','登录超时！',1);
				clearInterval(timer_online);
			}
		},(sec+5)*1000);
}//online(6000);
var win_online=false;//定义超时的win是否打开
function online_set(){
	if(typeof(SID_N)!="undefined"&&SID_N=="CNCQ") sec=6000;else sec=600;
	setCookie(SID,'online',sec);
}
function online_v2(){
	online_set();
	timer_online=setInterval(function(){
			if(getCookie(SID)!='online'){
				if(self!=top){
					if(!parent.win_online&&self.name=='im'){
						parent.win('login_win.htm','登录超时！',1);parent.win_online=true;
					}
					//else alert('3');
					//如果im里面的页面调用了win，win里面的页面超时，则不用弹出，因为main.htm会先弹出
				}else if(!win_online){
					win('../../login_win.htm','登录超时！',1);win_online=true;
				}
				//else alert('1');
			}else{//重新登录后
				if(self!=top){
					if(parent.win_online&&self.name=='im'){
						parent.closediv();	parent.win_online=false;
					}
					//else alert('4');
				}else if(win_online){
					closediv();win_online=false;
				}
				//else alert('2');
			}
		},3000);//每3秒检测一次在线状态
}//online_v2();

function daochu(o,v){
	if(typeof(v)=="undefined") v='1';
	g('dc').value=v;
	sblock(o);
	g('dc').value="";//需要重置该值，否则点 查看 时还是 导出
}

function sboth(o,v){
	if(typeof(v)=="undefined") v='dc';
	g('sbnt').value=v;
	sblock(o);
	g('sbnt').value="";//需要重置该值，否则点 查看 时还是 导出
}

function sblock(o,sec,fid){
	if(typeof(sec)=="undefined") sec=60;
	if(typeof(fid)=="undefined") o.parentNode.submit();
	else g(fid).submit();
	o.disabled=true;
	o.id='sblockid';
	setTimeout("g('sblockid').disabled=false",sec*1000);
}

function sleep(numberMillis){ 
	var now=new Date(); 
	var exitTime=now.getTime()+numberMillis;
	while(true){ 
		now=new Date(); 
		if(now.getTime()>exitTime) 
		return; 
	} 
} 

function getPosition(o){
	var temp={};
	var X=o.getBoundingClientRect().left+document.documentElement.scrollLeft;
	if(!+[1,]) var Y=o.getBoundingClientRect().top+document.documentElement.scrollTop;
	else var Y=o.getBoundingClientRect().top+document.body.scrollTop;	
	temp.left=X;temp.top=Y;
	return temp;
}

var total=0;//记录分页总数
function adel(o,pv,parent){
	if(typeof(msg_add)=="undefined") msg_add="";
	if(!confirm(msg_add+'确定？')) return false;
	/*ajax.send("post","?",pv,function(data){
		if(data=='OK'){
			msg('操作成功！');
			hideone(o,parent);
		}else msg('操作失败！'+data,0,0);
		//删除成功后再调用用户选择器时，会蹦出操作失败的提示。应该是再执行了一次adel,原因不知,暂用ajax.get替代。
	});*/
	var data=ajax.get('?',pv);
	if(data=='OK'){
		msg('操作成功！',o,1);
		hideone(o,parent);
	}else msg('操作失败！'+data,o,0);
}
function hideone(o,parent){
	if(g('pagett')){//有分页的才进行分页数字的处理
		var pagett=Number(g('pagett').innerHTML);
		if(!total) total=pagett;
		var newtotal=pagett-1;
		var last=Number(g('lend').innerHTML)-1;
		if(last<Number(g('lstart').innerHTML)){
			var url=window.location.href;
			if(url.indexOf('total')>0) window.location.href=url.replace('total='+total,'total='+newtotal);//url+'&retotal=1';
			else window.location.href=url.indexOf('?')>0?url+'&total='+newtotal:url+'?total='+newtotal;
			return;
		}
		g('lend').innerHTML=last;
		g('pagett').innerHTML=newtotal;
		var A=g('pages').getElementsByTagName("a");
		for(var i=0;i<A.length;i++){
			A[i].href=A[i].href.replace('total='+pagett,'total='+newtotal);
		}
	}
	if(parent==1){o.parentNode.style.display='none';trdiff();}
	else if(parent==2){o.parentNode.parentNode.style.display='none';trdiff();}
	else if(parent==3){o.parentNode.parentNode.parentNode.style.display='none';}
	else if(parent==4){o.parentNode.parentNode.parentNode.parentNode.style.display='none';}
}
function msg(t,o,ok){
	if(ok==0) ms=5000;else ms=1200;
	var style='';
	var swidth=parseInt(document.body.scrollWidth);
	var obj=document.createElement("span");
	obj.id='msg';
	if(typeof(o)!="object"){
		var top=parseInt(document.documentElement.scrollTop)+200;//考虑滚动后的情况
		var left=parseInt(document.documentElement.scrollLeft)+280;//考虑滚动后的情况
		style='top:'+top+'px;left:'+left+'px;';
	}else{
		var pos=getPosition(o);
		if(swidth-pos.left<200) style='top:'+pos.top+'px;right:30px;';
		else{
			var top=pos.top+20;
			var left=pos.left+20;
			style='top:'+top+'px;left:'+left+'px;';
		}
	}
	if(ok==2){
		t='<div class="alert_c" onclick="close_if_open(\'msg\',0);"></div>'+t;//加上关闭按钮
		style+='padding-right:15px;';
	}
	obj.innerHTML='<div class="alert" style="'+style+'" onclick="_cancelBubble(event);">'+t+'</div>';
	document.body.appendChild(obj);
	if(ok==3) setTimeout("window.location.reload();",ms);
	if(ok!=2) setTimeout("if(g('msg')) document.body.removeChild(g('msg'))",ms);
}

function countChar(o,tipid,limit){
	if(!o) return;//由于权限原因，有的未被显示出来
	if(g(tipid)) g(tipid).innerHTML=o.value.length+'/'+limit;
	if(o.value.length>limit) msg('最多允许'+limit+'字！',o,0);
} 

document.ondblclick=function(event){
	if(g('closediv')) g('closediv').click();
}

function tips(tipid,oL,oT){
	/*var browser=navigator.appName 
	var b_version=navigator.appVersion 
	var version=b_version.split(";"); 
	var trim_Version=version[1].replace(/[ ]/g,""); 
	if(browser=="Microsoft Internet Explorer"&&trim_Version!="MSIE8.0") { 
		oL=oL?oL:0;
		oT=oT?oT:38;
	}else{
		oL=oL?oL:0;
		oT=oT?oT:28;
	}*/
	g(tipid).onmouseover=function(){
		this.nextSibling.style.display='block';
		this.nextSibling.style.left=this.offsetLeft +'px';
		//this.nextSibling.nextSibling.style.top=this.offsetTop+oT +'px';;
	}
	g(tipid).onmouseout=function(){
		this.nextSibling.style.display="none";
	}
}

function ask(o){
	if(typeof(msg_add)=="undefined") msg_add="";
	if(!confirm(msg_add+"确认删除？")) window.event.returnValue=false;
	else o.href += "&ask=1";
}

Array.prototype.in_array=function(e) { 
	for(ii=0;ii<this.length&&this[ii]!=e;ii++); 
	return !(ii==this.length); 
}
//Object.prototype.hide=function(){
//	this.style.display='none';
//}

function getFileName(){
	var url=this.location.pathname
	var pos=url.lastIndexOf("/");
	if(pos==-1) pos=url.lastIndexOf("\\")
	var filename=url.substr(pos+1);
	return filename;
}
function initlists(id,index){
	g(id).selectedIndex=index;
}
function initlists_v(id,v){
	if(id=='soc'&&!v) v=getFileName();
	var o=g(id);
	if(!o) return;//由于权限原因，有的下拉列表可能没输出
	if(o.nodeName.toLowerCase()=='input'){
		if(v!='') o.setAttribute("checked","true");
		return;
	}else if(o.nodeName.toLowerCase()=='select'){//有时候select和input hidden共用id
		for(var i=0;i<o.options.length;i++){
			if(o.options[i].value==v){
				o.selectedIndex=i;break;return;
			}
		}
	}
}
function ck_selected(name){
  var names=document.getElementsByName(name);
  var len=names.length;
  if(len>0)
  {
var i=0;
for(i=0;i<len;i++)
	if(names[i].checked==true) return true;
	return false; 
  }
}

function checkEvent(name,allCheckId)
{
  var allCk=document.getElementById(allCheckId);
  if(allCk.checked==true)
  checkAll(name);
  else
  checkAllNo(name);
  
}

//全选
function checkAll(name)
{
  var names=document.getElementsByName(name);
  var len=names.length;
  if(len>0)
  {
var i=0;
for(i=0;i<len;i++)
names[i].checked=true;
 
  }
}

//全不选
function checkAllNo(name)
{
  var names=document.getElementsByName(name);
 var len=names.length;
 if(len>0)
  {
 var i=0;
 for(i=0;i<len;i++)
 names[i].checked=false;
  }
}