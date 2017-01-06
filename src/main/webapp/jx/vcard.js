Jx().$package("vCard",function(J){
	
  function callback(data){
    if(data.status==200 && data.responseJSON){
      var wrapEl=J.dom.id('wrap');
      console.log(data.responseJSON);
      wrapEl.innerHTML=J.string.template('vcardTmpl',data.responseJSON);//模板
    }else{
      alert('出错了');
    }
  }
  function callback1(data){
	  console.log(data);  
  }
  
  J.http.ajax('http://localhost:8089/jx/vcard.json',{
    data:{
      id:12345
    },
    onSuccess:callback,
    onError:callback,
    onTimeout:callback,
    type:'json'
  });
  
  J.http.ajax('http://localhost:8081/CLS1.1/cdgl/tree.cs?root=MENUROOT',{
	    data:{
	     
	    },
	    onSuccess:callback1,
	    onError:callback1,
	    onTimeout:callback1,
	    type:'json'
	  });
  
});