document.addEventListener("DOMContentLoaded", () => {
    const cartItems = document.getElementById("cart-items");
    const totalPriceElem = document.getElementById("total-price");
    const receivedAmountElem = document.getElementById("received-amount");
    const changeAmountElem = document.getElementById("change-amount");
    const checkoutButton = document.getElementById("checkout");

    let totalPrice = 0;
    let receivedAmount = 0;
    let cart = {};  // { productId: { name, price, quantity } }

    // CSRFトークン取得
    const csrfTokenMeta = document.querySelector('meta[name="csrf-token"]');
    const csrfHeaderMeta = document.querySelector('meta[name="csrf-header"]');

    const csrfToken = csrfTokenMeta ? csrfTokenMeta.content : null;
    const csrfHeader = csrfHeaderMeta ? csrfHeaderMeta.content : null;

    // 商品追加処理
    document.querySelectorAll(".product-btn").forEach(button => {
        button.addEventListener("click", () => {
            const productId = button.dataset.id;
            const productName = button.dataset.name;
            const price = parseInt(button.dataset.price);

            if (cart[productId]) {
                cart[productId].quantity += 1;
            } else {
                cart[productId] = { name: productName, price: price, quantity: 1 };
            }

            updateCartDisplay();
        });
    });

    // もらった金額の入力
    document.querySelectorAll(".num-btn").forEach(button => {
        button.addEventListener("click", () => {
            receivedAmount = receivedAmount * 10 + parseInt(button.dataset.value);
            receivedAmountElem.textContent = receivedAmount;
            updateChangeDisplay();
        });
    });

    // クリアボタン
    document.getElementById("clear").addEventListener("click", () => {
        receivedAmount = 0;
        receivedAmountElem.textContent = receivedAmount;
        updateChangeDisplay();
    });

    // 会計処理
    checkoutButton.addEventListener("click", () => {
        if (Object.keys(cart).length === 0) {
            alert("カートが空です！");
            return;
        }

        if (receivedAmount < totalPrice) {
            alert("受け取った金額が不足しています！");
        } else {
            const changeAmount = receivedAmount - totalPrice;

            // 送信データの作成
            const orderData = {
                totalPrice: totalPrice,
                receivedAmount: receivedAmount,
                changeAmount: changeAmount,
                cart: cart
            };

            console.log("送信データ:", orderData);

            const headers = {
                "Content-Type": "application/json",
            };
            if (csrfHeader && csrfToken) {
                headers[csrfHeader] = csrfToken;
            }

            // サーバーへ送信
            fetch("/checkout", {
                method: "POST",
                headers: headers,
                body: JSON.stringify(orderData)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("サーバーエラーが発生しました！");
                    }
                    return response.text();  // HTMLレスポンスを取得
                })
                .then(html => {
                    // `checkout.html` のページに遷移（直接HTMLを描画）
                    document.open();
                    document.write(html);
                    document.close();
                })
                .catch(error => {
                    console.error("エラー:", error);
                    alert("会計処理に失敗しました！もう一度試してください。");
                });
        }
    });

    function updateCartDisplay() {
        cartItems.innerHTML = "";
        totalPrice = 0;

        Object.keys(cart).forEach(productId => {
            const item = cart[productId];
            const li = document.createElement("li");
            li.textContent = `${item.name} × ${item.quantity} - ¥${item.price * item.quantity}`;
            cartItems.appendChild(li);
            totalPrice += item.price * item.quantity;
        });

        totalPriceElem.textContent = totalPrice;
        updateChangeDisplay();
    }

    function updateChangeDisplay() {
        let change = receivedAmount - totalPrice;
        changeAmountElem.textContent = change >= 0 ? change : 0;
    }
});