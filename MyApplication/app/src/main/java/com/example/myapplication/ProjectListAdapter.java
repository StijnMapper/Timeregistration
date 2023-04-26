package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.data.model.Project;

import java.util.List;
    public class ProjectListAdapter extends ArrayAdapter<Project> {

        private List<Project> projects;
        private LayoutInflater inflater;

        public ProjectListAdapter(@NonNull Context context, @NonNull List<Project> objects) {
            super(context, 0, objects);
            projects = objects;
            inflater = LayoutInflater.from(context);
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            TextView textView = new TextView(getContext());
            textView.setText("Project " + position);
            return textView;
        }


        private static class ViewHolder {
            ListView projectNameListView;
        }
    }
