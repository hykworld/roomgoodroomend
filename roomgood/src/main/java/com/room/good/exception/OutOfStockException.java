package com.room.good.exception;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message){
        // 상품의 주문수량보다 재고의 수가 적을 때 메세지 띄움
        super(message);
    }
}
