package pomobox.base;

import android.app.Dialog;
import android.content.Context;

public abstract class BaseCustomDialog {
    private Context mContext;
    private int mLayout;
    protected abstract void onBindView(Dialog dialog);

    public BaseCustomDialog(Context context, int layout) {
        mContext = context;
        mLayout = layout;
    }

    public void showDialog(){
        Dialog dialog = new Dialog(mContext);
        dialog.setContentView(mLayout);
        onBindView(dialog);
        dialog.show();
    }
}
