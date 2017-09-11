/**
 * Created by lxh on 2017/7/7.
 */



var sock = new SockJS('chat');

sock.onopen = function() {
    console.log('open');
    sock.send('test');
};

sock.onmessage = function(e) {
    console.log('message', e.data);
    sock.close();
};

sock.onclose = function() {
    console.log('close');
};

$("#chat_input").click(function() {
    var input = $('#msg');
    var inputValue = input.val();
    sock.send(inputValue);
    input.val('');
});
