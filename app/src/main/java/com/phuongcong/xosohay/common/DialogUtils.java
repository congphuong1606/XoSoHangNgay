package com.phuongcong.xosohay.common;

import android.app.Activity;
import android.app.ProgressDialog;


/**
 * Created by congp on 11/4/2017.
 */

public class DialogUtils {
    ProgressDialog dialog;
    Activity activity;

    public DialogUtils(ProgressDialog dialog, Activity activity) {
        this.dialog = dialog;
        this.activity = activity;
    }

    public void hideLoading() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
//    public static void showInfor(Context context, String infor) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(infor);
//        builder.setIcon(R.drawable.logo_app);
//        builder.setCancelable(true);
//        final AlertDialog dialog = builder.create();
//        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

    public  void showLoading() {
        if (dialog != null) {
            if (dialog.isShowing()) dialog.dismiss();
            dialog.show();
            return;
        }
        dialog = ProgressDialog
                .show(activity, "", "Vui lòng đợi ...", true);
    }


}
