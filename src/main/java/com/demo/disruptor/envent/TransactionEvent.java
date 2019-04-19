package com.demo.disruptor.envent;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/11
 * @Description
 */
public class TransactionEvent {
    /**
     * 流水号
     */
    private long seq;
    /**
     * 金额
     */
    private double amount;

    /**
     * 商品个数
     */
    private long callNumber;

    public long getCallNumber() {
        return callNumber;
    }

    @Override
    public String toString() {
        return "TransactionEvent [seq=" + seq + ", amount=" + amount + ", callNumber=" + callNumber + "]";
    }

    public void setCallNumber(long callNumber) {
        this.callNumber = callNumber;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
