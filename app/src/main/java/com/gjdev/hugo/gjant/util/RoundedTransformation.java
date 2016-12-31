package com.gjdev.hugo.gjant.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.squareup.picasso.Transformation;

/**
 * Created by Hugo on 31/12/2016.
 * Project: GjAnt2
 */

public class RoundedTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        /*RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(null, source);
        mRoundedBitmapDrawable.setCircular(true);

        mRoundedBitmapDrawable.setCornerRadius(Math.max(source.getWidth(), source.getHeight()) / 2f);

        return mRoundedBitmapDrawable.getBitmap();*/

        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "rounded";
    }
}
