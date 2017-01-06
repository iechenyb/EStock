var url = 'ws://localhost:8085/BackServer/websocket?code=000001&exchange=sz&type=minqutoes';
ws = new WebSocket(url);
ws.onopen = function () {
    log('Info: websocket connection success.');
};
ws.onmessage = function (event) {
	var data=eval("("+event.data+")");
	 if(data.msgType=='minqutoes'){
		 log(data.content);
	 }
};
ws.onclose = function(event) {
    var user = document.getElementById('username').value;
    log('System tips:connection closed!');
    log(user+",you have exit the chat room,you can login agin to chatting!");
};
function disconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}
function send(){
	var data = "{msg:'"+message+"',"+"toUser:'"+toUser+"'}";
    ws.send(data);
}
function log(txt) {
    var console = document.getElementById('console');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(txt));
    console.appendChild(p);
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;
}
