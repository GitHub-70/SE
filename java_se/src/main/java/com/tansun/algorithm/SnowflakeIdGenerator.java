package com.tansun.algorithm;

public class SnowflakeIdGenerator {

    private final long startTime;
    private long machineId;
    private long sequence = 0L;
    private final long machineIdBits = 10L;
    private final long maxMachineId = -1L ^ (-1L << machineIdBits);
    private final long sequenceBits = 12L;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private final long machineIdShift = sequenceBits;
    private final long timestampShift = sequenceBits + machineIdBits;

    public SnowflakeIdGenerator(long machineId) {
        if (machineId < 0 || machineId > maxMachineId) {
            throw new IllegalArgumentException("Machine ID must be between 0 and " + maxMachineId);
        }
        this.startTime = System.currentTimeMillis();
        this.machineId = machineId;
    }

    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < startTime) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID for " + (startTime - timestamp) + " milliseconds");
        }

        if (timestamp == startTime) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(timestamp);
            }
        } else {
            sequence = 0;
        }

        long id = ((timestamp - startTime) << timestampShift) | (machineId << machineIdShift) | sequence;
        return id;
    }

    private long tilNextMillis(long timestamp) {
        long currentTimestamp = System.currentTimeMillis();
        while (currentTimestamp <= timestamp) {
            currentTimestamp = System.currentTimeMillis();
        }
        return currentTimestamp;
    }

    public static void main(String[] args) {
        long seq = 4l;
        long seqMax = -1L ^ (-1 << (int)seq); // 相当于 2 的 多少次幂 - 1
        long startTime = 1575129600000L;
        long currentTime = 1692321830418L;
        long minSeq = 0l + 1l & seqMax;
        long maxSeq = 15l + 1l & seqMax;
        System.out.println(seqMax);
        System.out.println("maxSeq:"+maxSeq);
        System.out.println(currentTime - startTime);
        System.out.println("---------------------");
        System.out.println(103356<<2|0);
        System.out.println(103356<<2|50);
        System.out.println(currentTime - startTime << 12);
        System.out.println(currentTime - startTime << 12|minSeq);
        System.out.println(currentTime - startTime << 12|maxSeq);
    }
}
