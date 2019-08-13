function post() {
    var questionId = $('#question_id').val();
    var content = $('#comment_content').val();
    var security_key = $($('meta[name="_csrf_key"]')[0]).attr('content')
    var security_value = $($('meta[name="_csrf_value"]')[0]).attr('content')
    var headers = {};
    headers[security_key] = security_value;
    console.log(headers)
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
                $('#comment_section').hide();
            } else {
                alert(response.message);
            }
        },
        dataType: 'json'
    });
}