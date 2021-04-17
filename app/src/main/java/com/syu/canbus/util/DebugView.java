package com.syu.canbus.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.WindowManager;
import java.util.Locale;

public class DebugView extends View {
    private int CELL_HEIGHT = 35;
    int[] COLOR = {-65536, -1, -16711936, -256, -16776961};
    private final int MAX = 16;
    private final int TEXT_SIZE = 23;
    private int[] mColors = new int[16];
    private int mCount;
    private boolean mDbg = false;
    private int mLastIndex;
    private WindowManager.LayoutParams mLp = ToolkitApp.buildOverlayLayoutParams(-1, -1);
    private int mMsgCnt;
    private String[] mMsgs = new String[16];
    private Paint mPaint = new Paint();

    public DebugView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextSize(23.0f);
        this.mPaint.setColor(-1);
    }

    public void setDbg(boolean flag) {
        this.mDbg = flag;
    }

    public boolean isDbg() {
        return this.mDbg;
    }

    public WindowManager.LayoutParams getWindowLayoutParams() {
        return this.mLp;
    }

    public void msg(String msg) {
        if (this.mDbg && msg != null) {
            HandlerUI.getInstance().post(new MessageHelper(msg));
        }
    }

    public void msg2(String msg) {
        if (this.mDbg && msg != null) {
            HandlerUI.getInstance().post(new MessageHelper(msg));
        }
    }

    public void msgHex(String str, byte[] data, int start, int length) {
        if (this.mDbg && data != null) {
            if (data.length - start < length) {
                length = data.length - start;
            }
            String msg = String.valueOf(str) + " * ";
            for (int i = 0; i < length; i++) {
                String c = Integer.toHexString(data[start + i] & 255).toUpperCase(Locale.CHINA);
                if (c.length() < 2) {
                    c = "0" + c;
                }
                msg = String.valueOf(msg) + c + " ";
            }
            HandlerUI.getInstance().post(new MessageHelper(msg));
        }
    }

    public void msgHex(String str, int[] data, int start, int length) {
        if (this.mDbg && data != null) {
            if (data.length - start < length) {
                length = data.length - start;
            }
            String msg = String.valueOf(str) + " * ";
            for (int i = 0; i < length; i++) {
                String c = Integer.toHexString(data[start + i] & 255).toUpperCase(Locale.CHINA);
                if (c.length() < 2) {
                    c = "0" + c;
                }
                msg = String.valueOf(msg) + c + " ";
            }
            HandlerUI.getInstance().post(new MessageHelper(msg));
        }
    }

    private class MessageHelper implements Runnable {
        private String mMessage;

        public MessageHelper(String msg) {
            this.mMessage = msg;
        }

        public void run() {
            DebugView.this.mLastIndex++;
            DebugView.this.mCount++;
            if (DebugView.this.mLastIndex > 15) {
                DebugView.this.mLastIndex = 0;
            }
            if (DebugView.this.mCount > 16) {
                DebugView.this.mCount = 16;
            }
            DebugView.this.mMsgCnt++;
            DebugView.this.mMsgs[DebugView.this.mLastIndex] = String.format("%06d @ %s", Integer.valueOf(DebugView.this.mMsgCnt), this.mMessage);
            DebugView.this.mColors[DebugView.this.mLastIndex] = DebugView.this.COLOR[DebugView.this.mLastIndex % DebugView.this.COLOR.length];
            DebugView.this.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mCount != 0) {
            int count = this.mCount;
            int firstIndex = (this.mLastIndex - count) + 1;
            if (firstIndex < 0) {
                firstIndex += 16;
            }
            if (firstIndex + count > 16) {
                int rightCount = 16 - firstIndex;
                int leftCount = count - rightCount;
                for (int i = 0; i < rightCount; i++) {
                    int index = firstIndex + i;
                    this.mPaint.setColor(this.mColors[index]);
                    canvas.drawText(this.mMsgs[index], (float) 5, (float) ((i + 1) * this.CELL_HEIGHT), this.mPaint);
                }
                for (int i2 = 0; i2 < leftCount; i2++) {
                    this.mPaint.setColor(this.mColors[i2]);
                    canvas.drawText(this.mMsgs[i2], (float) 5, (float) ((rightCount + i2 + 1) * this.CELL_HEIGHT), this.mPaint);
                }
                return;
            }
            for (int i3 = 0; i3 < count; i3++) {
                int index2 = firstIndex + i3;
                this.mPaint.setColor(this.mColors[index2]);
                canvas.drawText(this.mMsgs[index2], (float) 5, (float) ((i3 + 1) * this.CELL_HEIGHT), this.mPaint);
            }
        }
    }
}
