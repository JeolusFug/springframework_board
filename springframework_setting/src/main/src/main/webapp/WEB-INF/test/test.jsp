<script>
    const emailCheckForMember = () => {
        const email = document.getElementById("memberEmail").value;
        const checkResult = document.getElementById("check-result");
        console.log("입력한 이메일", email);
        $.ajax({
            type : "post",
            url : "/member/email-check",
            data : {
                "memberEmail" : email
            },
            success: function(res) {
                console.log("요청 성공", res);
                if (res == "nooneuse") {
                    console.log("사용가능한 이메일 입니다.");
                    checkResult.style.color = "green";
                    checkResult.innerHTML = "사용가능한 이메일 입니다.";
                } else {
                    console.log("이미 사용중인 이메일 입니다.");
                    checkResult.style.color = "red";
                    checkResult.innerHTML = "이미 사용중인 이메일 입니다.";

                }
            },
            error: function (err) {
                console.log("에러발생", err);
            }
        });
    }
</script>