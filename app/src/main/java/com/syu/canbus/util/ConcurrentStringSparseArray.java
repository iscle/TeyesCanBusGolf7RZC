package com.syu.canbus.util;

public class ConcurrentStringSparseArray<E> implements Cloneable {
    private static final Object DELETED = new Object();
    private static final Object[] EMPTY_OBJECTS = new Object[0];
    private static final String[] EMPTY_STRINGS = new String[0];
    private boolean mGarbage;
    private String[] mKeys;
    private int mSize;
    private Object[] mValues;

    public ConcurrentStringSparseArray() {
        this(10);
    }

    public ConcurrentStringSparseArray(int initialCapacity) {
        this.mGarbage = false;
        if (initialCapacity == 0) {
            this.mKeys = EMPTY_STRINGS;
            this.mValues = EMPTY_OBJECTS;
        } else {
            int initialCapacity2 = idealIntArraySize(initialCapacity);
            this.mKeys = new String[initialCapacity2];
            this.mValues = new Object[initialCapacity2];
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
    public synchronized com.syu.canbus.util.ConcurrentStringSparseArray<E> clone() {
        /*
            r3 = this;
            monitor-enter(r3)
            r1 = 0
            java.lang.Object r2 = super.clone()     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r0 = r2
            com.syu.canbus.util.ConcurrentStringSparseArray r0 = (com.syu.canbus.util.ConcurrentStringSparseArray) r0     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r1 = r0
            java.lang.String[] r2 = r3.mKeys     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.Object r2 = r2.clone()     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            r1.mKeys = r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.Object[] r2 = r3.mValues     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.Object r2 = r2.clone()     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
            java.lang.Object[] r2 = (java.lang.Object[]) r2     // Catch:{ CloneNotSupportedException -> 0x0023, all -> 0x0020 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.syu.canbus.util.ConcurrentStringSparseArray.clone():com.syu.canbus.util.ConcurrentStringSparseArray");
    }

    public E get(String key) {
        return get(key, null);
    }

    public synchronized E get(String key, E valueIfKeyNotFound) {
        int i = binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0 && this.mValues[i] != DELETED) {
            valueIfKeyNotFound = (E) this.mValues[i];
        }
        return valueIfKeyNotFound;
    }

    private int binarySearch(String[] array, int size, String value) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            String midVal = array[mid];
            if (midVal == null) {
                return mid;
            }
            int result = midVal.compareTo(value);
            if (result < 0) {
                lo = mid + 1;
            } else if (result <= 0) {
                return mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo ^ -1;
    }

    public synchronized void delete(String key) {
        int i = binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0 && this.mValues[i] != DELETED) {
            this.mValues[i] = DELETED;
            this.mGarbage = true;
        }
    }

    public void remove(String key) {
        delete(key);
    }

    public synchronized void removeAt(int index) {
        if (this.mValues[index] != DELETED) {
            this.mValues[index] = DELETED;
            this.mGarbage = true;
        }
    }

    public synchronized void removeAtRange(int index, int size) {
        int end = Math.min(this.mSize, index + size);
        for (int i = index; i < end; i++) {
            removeAt(i);
        }
    }

    private void gc() {
        int n = this.mSize;
        int o = 0;
        String[] keys = this.mKeys;
        Object[] values = this.mValues;
        for (int i = 0; i < n; i++) {
            Object val = values[i];
            if (val != DELETED) {
                if (i != o) {
                    keys[o] = keys[i];
                    values[o] = val;
                    values[i] = null;
                }
                o++;
            }
        }
        this.mGarbage = false;
        this.mSize = o;
    }

    public synchronized void put(String key, E value) {
        int i = binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            this.mValues[i] = value;
        } else {
            int i2 = i ^ -1;
            if (i2 >= this.mSize || this.mValues[i2] != DELETED) {
                if (this.mGarbage && this.mSize >= this.mKeys.length) {
                    gc();
                    i2 = binarySearch(this.mKeys, this.mSize, key) ^ -1;
                }
                if (this.mSize >= this.mKeys.length) {
                    int n = idealIntArraySize(this.mSize + 1);
                    String[] nkeys = new String[n];
                    Object[] nvalues = new Object[n];
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
            } else {
                this.mKeys[i2] = key;
                this.mValues[i2] = value;
            }
        }
    }

    public synchronized int size() {
        if (this.mGarbage) {
            gc();
        }
        return this.mSize;
    }

    public synchronized String keyAt(int index) {
        if (this.mGarbage) {
            gc();
        }
        return this.mKeys[index];
    }

    public synchronized E valueAt(int index) {
        if (this.mGarbage) {
            gc();
        }
        return (E) this.mValues[index];
    }

    public synchronized void setValueAt(int index, E value) {
        if (this.mGarbage) {
            gc();
        }
        this.mValues[index] = value;
    }

    public synchronized int indexOfKey(String key) {
        if (this.mGarbage) {
            gc();
        }
        return binarySearch(this.mKeys, this.mSize, key);
    }

    public synchronized int indexOfValue(E value) {
        int i;
        if (this.mGarbage) {
            gc();
        }
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
        int n = this.mSize;
        Object[] values = this.mValues;
        for (int i = 0; i < n; i++) {
            values[i] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    private boolean StringGreater(String left, String right) {
        if (left != null && left.compareTo(right) > 0) {
            return true;
        }
        return false;
    }

    public synchronized void append(String key, E value) {
        if (this.mSize == 0 || StringGreater(key, this.mKeys[this.mSize - 1])) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                gc();
            }
            int pos = this.mSize;
            if (pos >= this.mKeys.length) {
                int n = idealIntArraySize(pos + 1);
                String[] nkeys = new String[n];
                Object[] nvalues = new Object[n];
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
                Object value = valueAt(i);
                if (value != this) {
                    buffer.append(value);
                } else {
                    buffer.append("(this Map)");
                }
            }
            buffer.append('}');
            sb = buffer.toString();
        }
        return sb;
    }
}
