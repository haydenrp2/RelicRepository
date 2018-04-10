package com.vcu.groupr.relicrepository;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcu.groupr.relicrepository.Artifact;
import com.vcu.groupr.relicrepository.R;

import java.util.List;

public class ArtifactAdapter extends ArrayAdapter<Artifact> {
    public ArtifactAdapter(Context context, int resource, List<Artifact> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_catalog, parent, false);
        }

        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        Artifact artifact = getItem(position);

        authorTextView.setText(artifact.getName());

        return convertView;
    }
}
