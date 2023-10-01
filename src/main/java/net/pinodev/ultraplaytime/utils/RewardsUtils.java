package net.pinodev.ultraplaytime.utils;

import org.xerial.snappy.BitShuffle;
import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.util.HashSet;

public class RewardsUtils {

    public byte[] compressed(int[] rewards) throws IOException {
        final byte[] shuffledRewards = BitShuffle.shuffle(rewards);
        return Snappy.compress(shuffledRewards);
    }

    public int[] decompressed(byte[] compressedRewards) throws IOException {
        final byte[] uncompressedRewards = Snappy.uncompress(compressedRewards);
        return BitShuffle.unshuffleIntArray(uncompressedRewards);
    }

    public HashSet<Integer> toHashSet(int[] array){
        HashSet<Integer> rewardSet = new HashSet<>(array.length);
        for(int rewardID : array){
            rewardSet.add(rewardID);
        }
        return rewardSet;
    }

    public int[] toArray(HashSet<Integer> set) {
        int[] array = new int[set.size()];
        int index = 0;
        for (int value : set) {
            array[index++] = value;
        }
        return array;
    }
}
