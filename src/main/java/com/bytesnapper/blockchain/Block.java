package com.bytesnapper.blockchain;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;


public class Block {


    private String hash;

    private String previousHash;

    // timestamp in milliseconds since 1970
    private long timestamp;

    private String data;

    private long nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
        this.nonce = 0;
    }

    public String calculateHash() {
        return DigestUtils.sha256Hex(this.data + String.valueOf(this.timestamp) + String.valueOf(this.nonce) + this.previousHash);
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }


    public void setHash(String hash) {
        this.hash = hash;
    }


    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }


    public void minBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace("\0", "0");
        while (!this.hash.substring(0, difficulty).equals(target)) {
            this.nonce++;
            this.hash = calculateHash();
        }
    }
}
