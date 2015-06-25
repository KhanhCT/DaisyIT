var _lang = getUrlParm('lang');

var _aryLang_EN = {
    'Submit': 'Submit',
    'Clear': 'Clear',
    'PartNo': 'PartNo',
    'Qty': 'Qty',
    'Item' : 'No',
    'Option' : 'Opt',
    'Qty_Req' : 'Qty is required!',
    'DB_OK' : 'DB connect successfully.',
    'DB_Clear' : 'DB clear successfully.' ,
    'Submit_OK' : 'Submit successfully.',
    'Submit_ERR' : 'Please input data to submit.',
    'Query_OK' : 'Query successfully.' ,
    'Insert_OK' : 'Insert successfully.' ,
    'Delete_OK' : 'Delete successfully.'
};
var _aryLang_TW = {
    'Submit': '送出',
    'Clear': '清除',
    'PartNo': '品號',
    'Qty': '數量',
    'Item' : '項次',
    'Option' : '選項',
    'Qty_Req' : '請輸入數量!',
    'DB_OK' : '資料庫連接成功',
    'DB_Clear' : '資料庫清除成功',
    'Submit_OK' : '資料送出成功',
    'Submit_ERR' : '請正確輸入資料' ,
    'Query_OK' : '查詢成功' ,
    'Insert_OK' : '新增成功',
    'Delete_OK' : '刪除成功' 
};
var _aryLang_CN = {
    'Submit': '送出',
    'Clear': '清除',
    'PartNo': '品号',
    'Qty': '数量',
    'Item' : '项次',
    'Option' : '选项',
    'Qty_Req' : '请输入数量!',
    'DB_OK' : '数据库连接成功',
    'DB_Clear' : '数据库清除成功',
    'Submit_OK' : '资料送出成功',
    'Submit_ERR' : '请正确输入数据' ,
    'Query_OK' : '查询成功' ,
    'Insert_OK' : '新增成功',
    'Delete_OK' : '删除成功' 
};


function UpdateLanguage(){ 
    var aryLang = document.getElementsByClassName('lang');
    var LangObj; 
    
    for(i=0; i < aryLang.length; i++) 
    {
        var LangObj = aryLang[i];
        if (LangObj == null) continue;
        
        if (LangObj.type != null 
            && (LangObj.type == 'button' || LangObj.type == 'submit'))
            LangObj.value = getLangStr(LangObj.lang);
        else
            LangObj.innerText = getLangStr(LangObj.lang); 
    }
}
function setLanguage(v){
    _lang = v;
    UpdateLanguage();
}
function getLangStr(LangKey){ 
    var RStr='';
    
    switch(_lang){
        case 'EN':
            RStr = _aryLang_EN[LangKey];
            break;
        case 'CN':
            RStr = _aryLang_CN[LangKey];
            break;
        case 'TW':
            RStr = _aryLang_TW[LangKey];
            break;
        default:
            break;
    }
    
    return RStr;
}
function getUrlParm(parm) {
    var Value = "EN";
    var tmp = "";
    var url = window.location.search;

    if (url.indexOf("?") > -1) {
        var ary = url.split("?")[1].split("&");
        for (var i in ary) {
            tmp = ary[i].split("=")[0];
            if (tmp == parm)
                Value = decodeURI(ary[i].split("=")[1]);
        }
    }
    
    return Value;
}
function getElementsByClass( searchClass, domNode, tagName) { 
    if (domNode == null) domNode = document;
    if (tagName == null) tagName = '*';
    
    var el = new Array();
    var tags = domNode.getElementsByTagName(tagName);
    var tcl = " "+searchClass+" ";
    
    for(i=0,j=0; i<tags.length; i++) { 
        var test = " " + tags[i].className + " ";
        if (test.indexOf(tcl) != -1) 
        el[j++] = tags[i];
    } 
    return el;
} 
 