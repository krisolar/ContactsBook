import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Android on 12.01.2017.
 */
public class ContactsAdapter extends ArrayAdapter{

    public ContactsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return super.getView(position, convertView, parent);
    }
}
