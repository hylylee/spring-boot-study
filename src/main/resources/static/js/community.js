function buildHeaders() {
    var security_key = $($('meta[name="_csrf_key"]')[0]).attr('content')
    var security_value = $($('meta[name="_csrf_value"]')[0]).attr('content')
    var headers = {};
    headers[security_key] = security_value;
    return headers;
}

/**
 * 提交回复
 */
function post() {
    var questionId = $('#question_id').val();
    var content = $('#comment_content').val();
    comment2Target(questionId, 1, content);
}

function comment2Target(targetId, type, content) {
    var headers = buildHeaders();

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
            'parentId': targetId,
            'content': content,
            'type': type
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

function comment(e) {
    var commentId = e.getAttribute('data-id');
    var content = $('#input-' + commentId).val();
    comment2Target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute('data-id');
    var comments = $('#comment-' + id);
    var collapse = e.getAttribute('data-collapse');
    if (collapse) {
        comments.removeClass('in');
        e.removeAttribute('data-collapse');
        e.classList.remove('active');
    } else {
        $.getJSON('/comment/' + id, function(data) {
            console.log(data);
        })

        var items = [];
        var commentBody = $('comment-body-') + id;

        $.each(data.data, function(comment) {
            var c = $('<div/>', {
                'class': 'col-lg-12 col-md-12 col-sm-12 col-xs-12 comments',
                html: comment.content
            });
            items.push(c);
        });
        $('div', {
            'class': 'col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments',
            'id': 'comment-' + id,
            html: items.join("")
        }).appendTo(commentBody);

        comments.addClass('in');
        e.setAttribute('data-collapse', 'in');
        e.classList.add('active');
    }
}