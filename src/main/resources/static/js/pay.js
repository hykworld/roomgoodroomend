async function requestPayment() {
    try {
        const response = await Bootpay.requestPayment({
            "application_id": "59a4d323396fa607cbe75de4",
            "price": 5000,
            "order_name": "테스트결제",
            "order_id": "TEST_ORDER_ID",
            "pg": "다날",
            "method": "카드",
            "tax_free": 0,
            "user": {
                "id": "회원아이디",
                "username": "회원이름",
                "phone": "01000000000",
                "email": "test@test.com"
            },
            "items": [
                {
                    "id": "item_id",
                    "name": "테스트아이템",
                    "qty": 1,
                    "price": 5000
                }
            ],
            "extra": {
                "open_type": "iframe",
                "card_quota": "0,2,3",
                "escrow": false
            }
        });
        console.log(response); // 결제 응답 처리
    } catch (error) {
        console.error(error); // 오류 처리
    }
}