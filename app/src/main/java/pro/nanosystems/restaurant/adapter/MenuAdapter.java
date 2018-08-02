package pro.nanosystems.restaurant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pro.nanosystems.restaurant.R;
import pro.nanosystems.restaurant.model.Menu;

public class MenuAdapter extends ArrayAdapter<Menu> {
    private Context context;
    private List<Menu> values;

    public MenuAdapter(@NonNull Context context,  @NonNull List<Menu> values) {
        super(context, R.layout.menu_rows, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.menu_rows, parent, false);
        }

        TextView titleView =  row.findViewById(R.id.titleView);
        TextView createAt = row.findViewById(R.id.createAt);

        Menu menu = values.get(position);

        titleView.setText(menu.getName());
        createAt.setText(String.valueOf(menu.getCreatedAt()));

        return row;
    }
}
