const host = 'http://' + window.location.host;
let targetId;
let folderTargetId;

$(document).ready(function () {
    const auth = getToken();

    if (auth !== undefined && auth !== '') {
        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader('Authorization', auth);
        });
    } else {
        window.location.href = host + '/api/user/login-page';
        return;
    }

    function fetchAndDisplayUserInfo() {
        $.ajax({
            type: 'GET',
            url: '/api/user-info', // 서버에서 설정한 사용자 정보 조회 API 경로
            success: function(userInfo) {
                // 사용자 정보를 페이지에 표시
                $('#name').text(userInfo.name);
                $('#sex').text(userInfo.sex);
                $('#phoneNumber').text(userInfo.phoneNumber);
                $('#address').text(userInfo.address);
                if (userInfo.isAdmin) {
                    $('#admin').show(); // 관리자인 경우 관련 UI 요소 보이기
                } else {
                    $('#admin').hide(); // 관리자가 아닌 경우 숨기기
                }
            },
            error: function(error) {
                console.error("사용자 정보를 불러오는데 실패했습니다.", error);
                logout(); // 에러 시 로그아웃 처리
            }
        });
    }

// 로그인 성공 후 사용자 정보 표시
    fetchAndDisplayUserInfo();
})

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}



function logout() {
    // 토큰 삭제
    Cookies.remove('Authorization', {path: '/'});
    window.location.href = host + '/api/user/login-page';
}

function getToken() {
    let auth = Cookies.get('Authorization');

    if(auth === undefined) {
        return '';
    }

    return auth;
}