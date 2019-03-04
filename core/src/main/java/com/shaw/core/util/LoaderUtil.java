package com.shaw.core.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shaw.core.R;
import com.shaw.core.util.dimen.DimenUtil;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatDialog;

/**
 * @author shaw
 * @date 2017/8/31
 */

public class LoaderUtil {

	private static final int LOADER_SIZE_SCALE = 11;
	private static final int LOADER_OFFSET_SCALE = 10;

	private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

	public static void showLoading(Context context, String message) {
		showDialogLoader(context, message);
	}

	public static void showLoading(Context context) {
		showLoading(context, "");
	}

	public static void stopLoading() {
		for (AppCompatDialog dialog : LOADERS) {
			if (dialog != null) {
				if (dialog.isShowing()) {
					dialog.cancel();
				}
			}
		}
	}

	private static void showDialogLoader(Context context, String message) {
		final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

		final LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setGravity(Gravity.CENTER);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(layoutParams);

		final ProgressBar progressBar = new ProgressBar(context);
		progressBar.setLayoutParams(layoutParams);

		if (!TextUtils.isEmpty(message)) {
			final TextView tvMessage = new TextView(context);
			tvMessage.setText(message);
			tvMessage.setTextColor(Color.WHITE);
			tvMessage.setGravity(Gravity.CENTER);
			linearLayout.addView(tvMessage);
		}
		linearLayout.addView(progressBar);
		dialog.setContentView(linearLayout);
		dialog.setCancelable(false);
		int deviceWidth = DimenUtil.getScreenWidth();
		int deviceHeight = DimenUtil.getScreenHeight();

		final Window dialogWindow = dialog.getWindow();

		if (dialogWindow != null) {
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			lp.width = deviceWidth * 2 / LOADER_SIZE_SCALE;
			lp.height = deviceHeight / LOADER_SIZE_SCALE;
			lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
			lp.gravity = Gravity.CENTER;
		}
		LOADERS.add(dialog);
		dialog.show();
	}

}
