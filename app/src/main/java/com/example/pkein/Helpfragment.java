package com.example.pkein;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Helpfragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_help, container, false);
        MediaController mc= new MediaController(getActivity());

        VideoView view = (VideoView)rootview.findViewById(R.id.video);
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video;
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);
        view.start();
        return rootview;
    }
}
