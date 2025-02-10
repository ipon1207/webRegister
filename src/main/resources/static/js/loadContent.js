document.addEventListener("DOMContentLoaded", function () {
    const buttons = document.querySelectorAll(".load-page");
    const content = document.querySelector(".content");

    buttons.forEach(button => {
        button.addEventListener("click", function () {
            const url = this.getAttribute("data-url");

            if (url === "/main/cashRegister") {
                // レジ画面だけ画面遷移する
                window.location.href = url;
            } else {
                // fetch APIでコンテンツを非同期取得
                fetch(url)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Network response was not ok");
                        }
                        return response.text();
                    })
                    .then(html => {
                        content.innerHTML = html; // コンテンツ部分を更新
                    })
                    .catch(error => {
                        console.error("Error fetching page:", error);
                        content.innerHTML = "<p>エラーが発生しました</p>";
                    });
            }
        });
    });
});