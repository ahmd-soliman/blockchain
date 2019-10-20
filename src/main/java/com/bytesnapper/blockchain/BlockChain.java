package com.bytesnapper.blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private  final List<Block> blockChain = new ArrayList<Block>();
    private final int difficulty = 3;

    public BlockChain() {
        blockChain.add(createGenesisBlock());
    }

    public void addBlock(Block block) {
        block.minBlock(this.difficulty);
        block.setPreviousHash(getLatestBlock().getHash());
        block.setHash(block.calculateHash());
        blockChain.add(block);
    }

    public Block getLatestBlock() {
        return blockChain.get(blockChain.size() - 1);
    }


    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");

    }

    public boolean isValidChain() {
        for (int i = 1; i < blockChain.size(); i++) {
            final Block currentBlock = blockChain.get(i);
            final Block previousBlock = blockChain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

}


