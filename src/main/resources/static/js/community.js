function post() {
    var questionId = $('#question_id').val();
    var content = $('#comment_content').val();
    var security_key = $($('meta[name="_csrf_key"]')[0]).attr('content')
    var security_value = $($('meta[name="_csrf_value"]')[0]).attr('content')
    var headers = {};
    headers[security_key] = security_value;

    if (!content) {
        alert("不能回复空内容!");
        return;
    }

    $.ajax({
        type: 'post',
        url: '/comment',
        contentType: 'application/json',
        headers: headers,
        data: JSON.stringify({
            'parentId': questionId,
            'content': content,
            'type': 1
        }),
        success: function(response) {
            if (response.code == 200) {
                location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message)
                    if (isAccepted) {
                        window.open('https://github.com/login/oauth/authorize?client_id=70a34622407c41973e42&redirect_id=http://localhost:8887/callback&scope=user&state=1');
                        window.localStorage.setItem('closable', 'true');
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: 'json'
    });
}