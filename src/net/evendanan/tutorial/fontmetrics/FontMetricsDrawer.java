package net.evendanan.tutorial.fontmetrics;

import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class FontMetricsDrawer extends View {

	private final Paint mPaint;
	private final float mDemoTextSize;
	private final float mOSDTextSize;
	
	public FontMetricsDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //mPaint.setTextSize(context.getResources().getDimension(R.dimen.demo_text_size));
        mPaint.setTextAlign(Align.CENTER);
        mPaint.setAlpha(255);
        mPaint.setTypeface(Typeface.SERIF);
        mPaint.setStrokeWidth(1);
        setBackgroundColor(Color.BLACK);
        
        mDemoTextSize = context.getResources().getDimension(R.dimen.demo_text_size);
        mOSDTextSize = context.getResources().getDimension(R.dimen.osd_text_size);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		final DecimalFormat floatFormater = new DecimalFormat("#.##");
		
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(mDemoTextSize);
		
		final FontMetrics demoTextFontMetrics = mPaint.getFontMetrics();
		final int demoX = getWidth()/2;
		final int demoY = (int)(getHeight()/2 - demoTextFontMetrics.ascent/2);
		
		canvas.drawText("My \u2588", demoX, demoY, mPaint);
		//now to OSD
		mPaint.setColor(Color.LTGRAY);
		mPaint.setTypeface(Typeface.SANS_SERIF);
		mPaint.setTextSize(mOSDTextSize);
		//drawing baseline line
		final String baseline = "baseline";
		canvas.drawLine(0, demoY, getWidth(), demoY, mPaint);
		canvas.drawText(baseline, 0 + mPaint.measureText(baseline)/2, -1+demoY, mPaint);
		//drawing ascent line
		final String ascent = "ascent "+floatFormater.format(demoTextFontMetrics.ascent);
		canvas.drawLine(0, demoY+demoTextFontMetrics.ascent, getWidth(), demoY+demoTextFontMetrics.ascent, mPaint);
		canvas.drawText(ascent, 0 + mPaint.measureText(ascent)/2, -1+demoY+demoTextFontMetrics.ascent, mPaint);
		//drawing bottom line
		final String bottom = "bottom "+floatFormater.format(demoTextFontMetrics.bottom);
		final float bottomSize = mPaint.measureText(bottom);
		canvas.drawLine(0, demoY+demoTextFontMetrics.bottom, getWidth(), demoY+demoTextFontMetrics.bottom, mPaint);
		canvas.drawText(bottom, 0 + mPaint.measureText(bottom)/2, -1+demoY+demoTextFontMetrics.bottom, mPaint);
		//drawing descent line
		final String descent = "descent "+floatFormater.format(demoTextFontMetrics.descent);
		canvas.drawLine(bottomSize + 8, demoY+demoTextFontMetrics.descent, getWidth(), demoY+demoTextFontMetrics.descent, mPaint);
		canvas.drawText(descent, bottomSize + 8 + mPaint.measureText(descent)/2, -1+demoY+demoTextFontMetrics.descent, mPaint);
		/*
		//drawing leading line
		final String leading = "leading "+demoTextFontMetrics.leading;
		canvas.drawLine(0, demoY+demoTextFontMetrics.descent+demoTextFontMetrics.leading, getWidth(), demoY+demoTextFontMetrics.descent+demoTextFontMetrics.leading, mPaint);
		canvas.drawText(leading, mPaint.measureText(leading)/2, -1+demoY+demoTextFontMetrics.descent+demoTextFontMetrics.leading, mPaint);
		*/
		//drawing top line
		final String top = "top "+floatFormater.format(demoTextFontMetrics.top);
		canvas.drawLine(0, demoY+demoTextFontMetrics.top, getWidth(), demoY+demoTextFontMetrics.top, mPaint);
		canvas.drawText(top, 0 + mPaint.measureText(top)/2, -1+demoY+demoTextFontMetrics.top, mPaint);
	}
}
