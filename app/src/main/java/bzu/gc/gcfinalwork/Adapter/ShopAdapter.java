package bzu.gc.gcfinalwork.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import bzu.gc.gcfinalwork.R;
import bzu.gc.gcfinalwork.entity.ShopInfo;

public class ShopAdapter extends ArrayAdapter<ShopInfo> {
    int resource;

    public ShopAdapter(Context context, int resource, List<ShopInfo> lists) {
        super(context, resource, lists);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null)
            return convertView;
        ShopInfo shopInfo = getItem(position);
        View view = View.inflate(getContext(), resource, null);
        SmartImageView img = view.findViewById(R.id.shop_img);
        TextView tittle = view.findViewById(R.id.shop_tittle);
        img.setImageUrl(shopInfo.getImg());
        tittle.setText(shopInfo.getTittle());
        return view;
    }
}
