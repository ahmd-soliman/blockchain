package com.bytesnapper.blockchain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockChainTest {

    @Test
    void validChain() {
        BlockChain blockChain = new BlockChain();
        Block secondBlock = new Block("Second Block", blockChain.getLatestBlock().getHash());
        blockChain.addBlock(secondBlock);
        Block thirdBlock = new Block("Third Block", blockChain.getLatestBlock().getHash());
        blockChain.addBlock(thirdBlock);
        assertTrue(blockChain.isValidChain());
    }

    @Test
    void invalidChain() {
        BlockChain blockChain = new BlockChain();
        Block secondBlock = new Block("Second Block", blockChain.getLatestBlock().getHash());
        blockChain.addBlock(secondBlock);
        Block thirdBlock = new Block("Third Block", blockChain.getLatestBlock().getHash());
        blockChain.addBlock(thirdBlock);
        // manipulate hash
        thirdBlock.setHash("123");
        assertFalse(blockChain.isValidChain());
    }
}