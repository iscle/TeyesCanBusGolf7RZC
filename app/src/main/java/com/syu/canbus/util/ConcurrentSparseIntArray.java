package com.syu.canbus.util;

public class ConcurrentSparseIntArray implements Cloneable {
    private static final int[] EMPTY_INTS = new int[0];
    private int[] mKeys;
    private int mSize;
    private int[] mValues;

    public ConcurrentSparseIntArray() {
        this(10);
    }

    public ConcurrentSparseIntArray(int initialCapacity) {
        if (initialCapacity == 0) {
            this.mKeys = EMPTY_INTS;
            this.mValues = EMPTY_INTS;
        } else {
            int initialCapacity2 = idealIntArraySize(initialCapacity);
            this.mKeys = new int[initialCapacity2];
            this.mValues = new int[initialCapacity2];
        }
        this.mSize = 0;
    }

    private int idealByteArraySize(int need) {
        for (int i = 4; i < 32; i++) {
            if (need <= (1 << i) - 12) {
                return (1 << i) - 12;
            }
        }
        return need;
    }

    private int idealIntArraySize(int need) {
        return idealByteArraySize(need << 2) >> 2;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    @Override // java.lang.Object
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.syu.canbus.util.ConcurrentSparseIntArray clone() {
        /*
            r3 = this;
            monitor-enter(r3)
            r1 = 0
            java.lang.Object r2 = super.clone()     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r0 = r2
            com.syu.canbus.util.ConcurrentSparseIntArray r0 = (com.syu.canbus.util.ConcurrentSparseIntArray) r0     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r1 = r0
            int[] r2 = r3.mKeys     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.Object r2 = r2.clone()     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            int[] r2 = (int[]) r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r1.mKeys = r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            int[] r2 = r3.mValues     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.Object r2 = r2.clone()     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            int[] r2 = (int[]) r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r1.mValues = r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
        L_0x001e:
            monitor-exit(r3)
            return r1
        L_0x0020:
            r2 = move-exception
            monitor-exit(r3)
            throw r2
        L_0x0023:
            r2 = move-exception
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.syu.canbus.util.ConcurrentSparseIntArray.clone():com.syu.canbus.util.ConcurrentSparseIntArray");
    }

    public synchronized int get(int key) {
        return get(key, 0);
    }

    public synchronized int get(int key, int valueIfKeyNotFound) {
        int i = binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            valueIfKeyNotFound = this.mValues[i];
        }
        return valueIfKeyNotFound;
    }

    private int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal <= value) {
                return mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo ^ -1;
    }

    public synchronized void delete(int key) {
        int i = binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            removeAt(i);
        }
    }

    public synchronized void removeAt(int index) {
        System.arraycopy(this.mKeys, index + 1, this.mKeys, index, this.mSize - (index + 1));
        System.arraycopy(this.mValues, index + 1, this.mValues, index, this.mSize - (index + 1));
        this.mSize--;
    }

    public synchronized void put(int key, int value) {
        int i = binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            this.mValues[i] = value;
        } else {
            int i2 = i ^ -1;
            if (this.mSize >= this.mKeys.length) {
                int n = idealIntArraySize(this.mSize + 1);
                int[] nkeys = new int[n];
                int[] nvalues = new int[n];
                System.arraycopy(this.mKeys, 0, nkeys, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, nvalues, 0, this.mValues.length);
                this.mKeys = nkeys;
                this.mValues = nvalues;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.mKeys, i2, this.mKeys, i2 + 1, this.mSize - i2);
                System.arraycopy(this.mValues, i2, this.mValues, i2 + 1, this.mSize - i2);
            }
            this.mKeys[i2] = key;
            this.mValues[i2] = value;
            this.mSize++;
        }
    }

    public synchronized int size() {
        return this.mSize;
    }

    public synchronized int keyAt(int index) {
        return this.mKeys[index];
    }

    public synchronized int valueAt(int index) {
        return this.mValues[index];
    }

    public synchronized int indexOfKey(int key) {
        return binarySearch(this.mKeys, this.mSize, key);
    }

    public synchronized int indexOfValue(int value) {
        int i;
        i = 0;
        while (true) {
            if (i < this.mSize) {
                if (this.mValues[i] == value) {
                    break;
                }
                i++;
            } else {
                i = -1;
                break;
            }
        }
        return i;
    }

    public synchronized void clear() {
        this.mSize = 0;
    }

    public synchronized void append(int key, int value) {
        if (this.mSize == 0 || key > this.mKeys[this.mSize - 1]) {
            int pos = this.mSize;
            if (pos >= this.mKeys.length) {
                int n = idealIntArraySize(pos + 1);
                int[] nkeys = new int[n];
                int[] nvalues = new int[n];
                System.arraycopy(this.mKeys, 0, nkeys, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, nvalues, 0, this.mValues.length);
                this.mKeys = nkeys;
                this.mValues = nvalues;
            }
            this.mKeys[pos] = key;
            this.mValues[pos] = value;
            this.mSize = pos + 1;
        } else {
            put(key, value);
        }
    }

    public synchronized String toString() {
        String sb;
        if (size() <= 0) {
            sb = "{}";
        } else {
            StringBuilder buffer = new StringBuilder(this.mSize * 28);
            buffer.append('{');
            for (int i = 0; i < this.mSize; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }
                buffer.append(keyAt(i));
                buffer.append('=');
                buffer.append(valueAt(i));
            }
            buffer.append('}');
            sb = buffer.toString();
        }
        return sb;
    }
}
