package pomobox.base;

import android.app.Dialog;
import android.content.Context;

public abstract class BaseCustomDialog extends Dialog {
    private Context mContext;
    private int mLayout;
    public abstract void onBindView(Dialog dialog);

    protected BaseCustomDialog(Context context, int layout) {
        super(context);
        this.mContext = context;
        this.mLayout = layout;
    }

    public void showDialog(){
        Dialog dialog = new Dialog(mContext);
        dialog.setContentView(mLayout);
        onBindView(dialog);
        dialog.show();
    }
}
