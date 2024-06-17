package com.tansun;
import java.util.BitSet;
public class TestMain {

    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter(10, 3);
        filter.add("apple");
        filter.add("banana");

        System.out.println(filter.contains("apple")); // true
        System.out.println(filter.contains("orange")); // false
    }
}


     class BloomFilter {
        private BitSet bitSet;
        private int size;
        private int hashFuncNum;

        public BloomFilter(int size, int hashFuncNum) {
            this.size = size;
            this.hashFuncNum = hashFuncNum;
            this.bitSet = new BitSet(size);
        }

        public void add(String element) {
            for (int i = 0; i < hashFuncNum; i++) {
                int hash = hash(element, i);
                bitSet.set(hash, true);
            }
        }

        public boolean contains(String element) {
            for (int i = 0; i < hashFuncNum; i++) {
                int hash = hash(element, i);
                if (!bitSet.get(hash)) {
                    return false;
                }
            }
            return true;
        }

        private int hash(String element, int index) {
            int hash = 0;
            for (int i = 0; i < element.length(); i++) {
                hash = (hash * 31 + element.charAt(i)) % size;
            }
            return (hash + index) % size;
        }


}
