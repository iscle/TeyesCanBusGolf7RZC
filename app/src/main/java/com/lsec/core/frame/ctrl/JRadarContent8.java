package com.lsec.core.frame.ctrl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.syu.canbus.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class JRadarContent8 extends View {
    int[] PT_FL;
    int[] PT_FML;
    int[] PT_FMR;
    int[] PT_FR;
    int[] PT_RL;
    int[] PT_RML;
    int[] PT_RMR;
    int[] PT_RR;
    int[][] PX_FL;
    int[][] PX_FML;
    int[][] PX_FMR;
    int[][] PX_FR;
    int[][] PX_RL;
    int[][] PX_RML;
    int[][] PX_RMR;
    int[][] PX_RR;
    int[] RC_FL;
    int[] RC_FML;
    int[] RC_FMR;
    int[] RC_FR;
    int[] RC_RL;
    int[] RC_RML;
    int[] RC_RMR;
    int[] RC_RR;
    private int[] VALUE;
    private final int[] VALUE_BAK;
    private Bitmap mBitmap;
    private WeakReference<Bitmap> mContentBitmap;
    private boolean mIsPixelGet;
    public int mLintCnt;
    private final Paint mPaint;

    public void initDataLint() {
        this.mLintCnt = 10;
        this.PX_FL = new int[this.mLintCnt][];
        this.PX_FML = new int[this.mLintCnt][];
        this.PX_FMR = new int[this.mLintCnt][];
        this.PX_FR = new int[this.mLintCnt][];
        this.PX_RL = new int[this.mLintCnt][];
        this.PX_RML = new int[this.mLintCnt][];
        this.PX_RMR = new int[this.mLintCnt][];
        this.PX_RR = new int[this.mLintCnt][];
    }

    public int[][] getPX(int i) {
        switch (i) {
            case 0:
                return this.PX_FL;
            case 1:
                return this.PX_FML;
            case 2:
                return this.PX_FMR;
            case 3:
                return this.PX_FR;
            case 4:
                return this.PX_RL;
            case 5:
                return this.PX_RML;
            case 6:
                return this.PX_RMR;
            case 7:
                return this.PX_RR;
            default:
                return this.PX_RR;
        }
    }

    public JRadarContent8(Context context) {
        super(context);
        this.mContentBitmap = new WeakReference<>(null);
        this.mPaint = new Paint();
        this.mLintCnt = -1;
        this.mIsPixelGet = false;
        this.VALUE_BAK = new int[8];
        //this.VALUE = Main.DATA_RADAR;
        this.VALUE = new int[16];
        init();
    }

    public JRadarContent8(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContentBitmap = new WeakReference<>(null);
        this.mPaint = new Paint();
        this.mLintCnt = -1;
        this.mIsPixelGet = false;
        this.VALUE_BAK = new int[8];
        //this.VALUE = Main.DATA_RADAR;
        this.VALUE = new int[16];
        init();
    }

    public JRadarContent8(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContentBitmap = new WeakReference<>(null);
        this.mPaint = new Paint();
        this.mLintCnt = -1;
        this.mIsPixelGet = false;
        this.VALUE_BAK = new int[8];
        //this.VALUE = Main.DATA_RADAR;
        this.VALUE = new int[16];
        init();
    }

    public void initData() {
        this.PT_FL = new int[]{275, 373, 259, 374, 242, 377, 226, 379, 210, 381, 197, 385, 178, 385, 159, 385, 145, 386, 131, 378};
        this.RC_FL = new int[]{256, 359, 293, 395, 241, 362, 286, 405, 226, 364, 275, 411, 209, 366, 262, 415, 195, 368, 245, 416, 179, 370, 228, 416, 163, 372, 207, 415, 147, 375, 187, 413, 132, 376, 164, 408, 131, 378, 132, 379};
        this.PT_FML = new int[]{262, 343, 247, 342, 232, 341, 220, 342, 202, 345, 186, 353, 171, 354, 152, 358, 141, 360, 128, 361};
        this.RC_FML = new int[]{256, 306, 277, 361, 241, 306, 256, 363, 226, 306, 241, 365, 209, 306, 226, 368, 194, 306, 209, 370, 179, 306, 194, 371, 163, 306, 179, 374, 147, 306, 163, 376, 132, 306, 147, 378, 119, 306, 132, 378};
        this.PT_FMR = new int[]{263, 262, 247, 257, 234, 255, 217, 251, 201, 250, 185, 245, 173, 242, 155, 239, 141, 236, 128, 237};
        this.RC_FMR = new int[]{256, 252, 273, 306, 241, 248, 256, 306, 226, 244, 241, 306, 209, 240, 226, 306, 194, 237, 209, 306, 179, 232, 194, 306, 163, 229, 179, 306, 147, 225, 163, 306, 132, 221, 147, 306, 119, 220, 132, 306};
        this.PT_FR = new int[]{281, 234, 264, 230, 246, 226, 229, 224, 214, 219, 196, 215, 183, 213, 162, 210, 147, 210, 131, 219};
        this.RC_FR = new int[]{256, 210, 297, 256, 241, 199, 289, 251, 226, 192, 278, 247, 209, 187, 266, 243, 194, 185, 250, 239, 179, 185, 231, 236, 163, 185, 214, 232, 147, 186, 195, 228, 132, 190, 173, 224, 131, 219, 132, 220};
        this.PT_RL = new int[]{734, 375, 748, 379, 764, 383, 782, 385, 798, 388, 813, 389, 832, 390, 848, 392, 867, 387, 880, 378};
        this.RC_RL = new int[]{719, 359, 756, 395, 726, 362, 771, 405, 737, 364, 786, 411, 750, 366, 803, 415, 767, 368, 817, 416, 784, 370, 833, 416, 805, 372, 849, 415, 825, 375, 865, 413, 848, 376, 880, 408, 880, 378, 881, 379};
        this.PT_RML = new int[]{750, 336, 761, 336, 781, 336, 796, 336, 813, 336, 825, 336, 840, 336, 857, 336, 872, 336, 886, 336};
        this.RC_RML = new int[]{735, 306, 756, 361, 756, 306, 771, 363, 771, 306, 786, 365, 786, 306, 803, 368, 803, 306, 818, 370, 818, 306, 833, 371, 833, 306, 849, 374, 849, 306, 865, 376, 865, 306, 880, 377, 880, 306, 893, 378};
        this.PT_RMR = new int[]{750, 316, 761, 316, 781, 316, 796, 316, 813, 316, 825, 316, 840, 316, 857, 316, 872, 316, 886, 316};
        this.RC_RMR = new int[]{739, 252, 756, 306, 756, 248, 771, 306, 771, 244, 786, 306, 786, 240, 803, 306, 803, 237, 818, 306, 818, 232, 833, 306, 833, 229, 849, 306, 849, 225, 865, 306, 865, 221, 880, 306, 880, 220, 893, 306};
        this.PT_RR = new int[]{730, 230, 745, 228, 763, 226, 778, 222, 799, 219, 813, 218, 828, 215, 847, 212, 865, 212, 880, 219};
        this.RC_RR = new int[]{715, 211, 756, 256, 723, 199, 771, 251, 734, 192, 786, 247, 746, 187, 803, 243, 762, 185, 818, 239, 781, 185, 833, 236, 798, 185, 849, 232, 817, 186, 865, 228, 839, 190, 888, 224, 880, 219, 881, 220};
    }

    public void initStyle() {
        initDataLint();
        this.mIsPixelGet = false;
        this.mContentBitmap.clear();
        initData();
        invalidate();
    }

    private void init() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setAlpha(225);
        initStyle();
    }

    private Bitmap getBitmap() {
        Bitmap b = this.mContentBitmap.get();
        if (b == null) {
            b = Bitmap.createBitmap(1024, 600, Bitmap.Config.ARGB_8888);
            this.mContentBitmap = new WeakReference<>(b);
            Bitmap drawable = BitmapFactory.decodeResource(getResources(), R.drawable.radar_content);
            if (drawable != null)
                new Canvas(b).drawBitmap(drawable, 0.0f, 0.0f, this.mPaint);
            for (int i = 0; i < 8; i++) {
                this.VALUE_BAK[i] = -1;
            }
            if (!this.mIsPixelGet) {
                this.mIsPixelGet = true;
                getPixel(b, this.PT_FL, this.RC_FL, this.PX_FL);
                getPixel(b, this.PT_FML, this.RC_FML, this.PX_FML);
                getPixel(b, this.PT_FMR, this.RC_FMR, this.PX_FMR);
                getPixel(b, this.PT_FR, this.RC_FR, this.PX_FR);
                getPixel(b, this.PT_RL, this.RC_RL, this.PX_RL);
                getPixel(b, this.PT_RML, this.RC_RML, this.PX_RML);
                getPixel(b, this.PT_RMR, this.RC_RMR, this.PX_RMR);
                getPixel(b, this.PT_RR, this.RC_RR, this.PX_RR);
            }
        }
        return b;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshRadarVisible();
        //App.mRadarContent8s.add(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //App.mRadarContent8s.remove(this);
        this.mBitmap = null;
    }

    public void refreshRadarVisible() {
        //setVisibility(Main.DATA[27] == 0 ? GONE : VISIBLE);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        initDataLint();
        this.mBitmap = getBitmap();
        for (int i = 0; i < 8; i++) {
            if (this.VALUE_BAK[i] != this.VALUE[i]) {
                this.VALUE_BAK[i] = this.VALUE[i];
                render(this.mBitmap, this.VALUE[i], getPX(i));
            }
        }
        canvas.drawColor(Color.BLACK);
        float scaleX = ((float) getWidth()) / 1024.0f;
        float scaleY = ((float) getHeight()) / 600.0f;
        if (scaleX < scaleY) {
            canvas.scale(scaleX, scaleX);
            canvas.translate(0.0f, (((float) getHeight()) - (600.0f * scaleX)) / 2.0f);
        } else {
            canvas.scale(scaleY, scaleY);
            canvas.translate((((float) getWidth()) - (1024.0f * scaleY)) / 2.0f, 0.0f);
        }
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mPaint);
    }

    private void render(Bitmap bitmap, int distance, int[][] pixel) {
        int lintCnt = 10;
        if (distance < 0) {
            distance = 0;
        } else if (distance > lintCnt) {
            distance = lintCnt;
        }
        for (int i = 0; i < distance; i++) {
            if (bitmap.getPixel(pixel[i][0], pixel[i][1]) != Color.TRANSPARENT) {
                renderPixel(bitmap, pixel[i], Color.TRANSPARENT);
            }
        }
        if (distance == 0) {
            if (bitmap.getPixel(pixel[distance][0], pixel[distance][1]) != -65536) {
                renderPixel(bitmap, pixel[distance], -65536); // red
            }
        } else if (distance < lintCnt && bitmap.getPixel(pixel[distance][0], pixel[distance][1]) != -3094464) {
            renderPixel(bitmap, pixel[distance], -3094464); // yellow
        }
        for (int i2 = distance + 1; i2 < lintCnt; i2++) {
            if (bitmap.getPixel(pixel[i2][0], pixel[i2][1]) != -7315440) {
                renderPixel(bitmap, pixel[i2], -7315440); // brown
            }
        }
    }

    private void renderPixel(Bitmap bitmap, int[] pixel, int color) {
        int iStep = 2;
        int length = pixel.length;
        for (int i = 0; i < length; i += iStep) {
            bitmap.setPixel(pixel[i], pixel[i + 1], color);
        }
    }

    public void getPixel(Bitmap bitmap, int[] pt, int[] rc, int[][] pixel) {
        int lintCnt = 10;
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < lintCnt; i++) {
            int ptIndex = i << 1;
            int rcIndex = i << 2;
            int findColor = bitmap.getPixel(pt[ptIndex], pt[ptIndex + 1]);
            int left = rc[rcIndex];
            int top = rc[rcIndex + 1];
            int right = rc[rcIndex + 2];
            int bottom = rc[rcIndex + 3];
            array.clear();
            for (int x = left; x < right; x++) {
                for (int y = top; y < bottom; y++) {
                    if (bitmap.getPixel(x, y) == findColor) {
                        array.add(x);
                        array.add(y);
                    }
                }
            }
            pixel[i] = new int[array.size()];
            for (int j = 0; j < array.size(); j++) {
                pixel[i][j] = array.get(j);
            }
        }
    }

    private boolean close(int colorLeft, int colorRight) {
        return Math.abs((colorLeft & 0xFF) - (colorRight & 0xFF)) <= 10 && Math.abs((colorLeft & 0xFFFF) - (colorRight & 0xFFFF)) <= 2560 && Math.abs((colorLeft & 0xFFFFFF) - (colorRight & 0xFFFFFF)) <= 655360;
    }

    private void renderPixel(Bitmap bitmap, int[] pixel) {
        int length = pixel.length;
        for (int i = 0; i < length; i += 3) {
            bitmap.setPixel(pixel[i], pixel[i + 1], pixel[i + 2]);
        }
    }

    public void setData(int[] data) {
        this.VALUE = data;
    }
}
